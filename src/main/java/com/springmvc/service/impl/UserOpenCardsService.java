package com.springmvc.service.impl;

import com.springmvc.dao.UserOpenCardsMapper;
import com.springmvc.dao.WnkBuyMealMapper;
import com.springmvc.entity.*;
import com.springmvc.service.IUserOpenCardsService;
import com.springmvc.utils.GeTuiBusinessUtil;
import com.springmvc.utils.GeTuiUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/userOpenCardsService")
public class UserOpenCardsService implements IUserOpenCardsService{
    @Resource
    private UserOpenCardsMapper userOpenCardsMapper;
    @Resource
    private WnkBuyMealMapper wnkBuyMealMapper;
    @Resource
    private UsersService usersService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private WnkBusinessRecommendUserRecordService wnkBusinessRecommendUserRecordService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessRoseDetailService wnkBusinessRoseDetailService;
    @Resource
    private UserRecommendUserRecordService userRecommendUserRecordService;
    @Resource
    private UsersRoseDetailService usersRoseDetailService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    @Override
    public int addRecord(UserOpenCards userOpenCards) {
        return this.userOpenCardsMapper.addRecord(userOpenCards);
    }

    @Override
    public UserOpenCards selectByUserId(Integer user_id) {
        return this.userOpenCardsMapper.selectByUserId(user_id);
    }

    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.userOpenCardsMapper.selectAllRecord();
    }

    @Override
    public int updateTermByUserId(Date term_validity, Integer user_id) {
        return this.userOpenCardsMapper.updateTermByUserId(term_validity, user_id);
    }

    @Override
    public int userOpenCardOrRenew(Integer user_id, Integer type,Integer user_type) {
        WnkBuyMeal wnkBuyMeal = this.wnkBuyMealMapper.selectById(type);
        if (wnkBuyMeal == null){
            return 0;
        }
        else{
            UserOpenCards userOpenCards = this.userOpenCardsMapper.selectByUserId(user_id);
            if (userOpenCards == null){
                userOpenCards = new UserOpenCards();
                List<Map<Object,Object>> list = this.userOpenCardsMapper.selectAllRecord();
                String card_no = "";
                if (list.size() <= 0){
                    card_no = "000000";
                }
                else{
                    Map<Object,Object> map = list.get(0);
                    Integer card_no_int = Integer.parseInt((String)map.get("card_no"));
                    card_no_int++;
                    card_no = "00000"+card_no_int.toString();
                }
                userOpenCards.setUser_id(user_id);
                userOpenCards.setCard_type_id(type);
                userOpenCards.setCard_no(card_no);
                userOpenCards.setOpen_card_date(new Date());
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);//设置起时间
                cal.add(Calendar.MONTH, wnkBuyMeal.getDuration_validity_period());//增加月份
                userOpenCards.setTerm_validity(cal.getTime());
                userOpenCards.setUser_type(user_type);
                return this.userOpenCardsMapper.addRecord(userOpenCards);
            }
            else{
                Date termValidity = null;
                //如果想比较日期则写成"yyyy-MM-dd"就可以了
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                //将字符串形式的时间转化为Date类型的时间
                Date currentDate = new Date();
                if (!currentDate.before(userOpenCards.getTerm_validity())){
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);//设置起时间
                    cal.add(Calendar.MONTH, wnkBuyMeal.getDuration_validity_period());//增加月份
                    termValidity = cal.getTime();
                }
                else{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(userOpenCards.getTerm_validity());//设置起时间
                    cal.add(Calendar.MONTH, wnkBuyMeal.getDuration_validity_period());//增加月份
                    termValidity = cal.getTime();
                }
                return this.userOpenCardsMapper.updateTermByUserId(termValidity,user_id);
            }
        }
    }

    @Override
    public int userOpenCardRecommendOption(Integer user_id, WnkBuyMeal wnkBuyMeal) {
        Users users = this.usersService.selectById(user_id);
        if (users == null){
            return 0;
        }
        else{
            try {
            if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1){
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                if (wnkBusinessAccount != null){
                    WnkBusinessRecommendUserRecord wnkBusinessRecommendUserRecord = this.wnkBusinessRecommendUserRecordService.selectNoExchangeByBusinessId(users.getRecommend_user_id());
                    if (wnkBusinessRecommendUserRecord == null){
                        this.wnkBusinessRecommendUserRecordService.insertRecord(wnkBusinessAccount.getId());
                    }
                    else{
                        wnkBusinessRecommendUserRecord.setNumber(wnkBusinessRecommendUserRecord.getNumber() + 1);
                        int updateNumberState = this.wnkBusinessRecommendUserRecordService.updateInformation(wnkBusinessRecommendUserRecord);
                        if (updateNumberState > 0 && wnkBusinessRecommendUserRecord.getNumber() >= 3){
                            WnkBusinessRoseDetail wnkBusinessRoseDetail = new WnkBusinessRoseDetail();
                            wnkBusinessRoseDetail.setBusiness_id(wnkBusinessAccount.getId());
                            wnkBusinessRoseDetail.setName("用户推广收益");
                            wnkBusinessRoseDetail.setIntegral_number(1);
                            wnkBusinessRoseDetail.setTransactions_type(0);
                            wnkBusinessRoseDetail.setTransactions_date(new Date());
                            int addState = this.wnkBusinessRoseDetailService.insertNewRecord(wnkBusinessRoseDetail);
                            if (addState > 0){
                                wnkBusinessAccount.setRose_number(wnkBusinessAccount.getRose_number() + 1);
                                this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                this.wnkBusinessRecommendUserRecordService.updateStateIsFinish(wnkBusinessRecommendUserRecord.getId());

                                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                if (wnkStoreInformation != null){
                                    if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                        String pushTitle = "我的玫瑰收入";
                                        String pushContent = "你有个成熟的团队了，你已经长大了，奖励你一枝玫瑰,请在猛戳-我的玫瑰查看";
                                        GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                Users recommentUsers = this.usersService.selectById(users.getRecommend_user_id());
                if (recommentUsers != null && recommentUsers.getMember_card_level() != -1){
                    UserRecommendUserRecord userRecommendUserRecord = this.userRecommendUserRecordService.selectNoExchangeByUserId(users.getRecommend_user_id());
                    if (userRecommendUserRecord == null){
                        this.userRecommendUserRecordService.insertRecord(recommentUsers.getId());
                    }
                    else{
                        userRecommendUserRecord.setNumber(userRecommendUserRecord.getNumber() + 1);
                        int updateNumberState = this.userRecommendUserRecordService.updateInformation(userRecommendUserRecord);
                        if (updateNumberState > 0 && userRecommendUserRecord.getNumber() >= 3){
                            UsersRoseDetail usersRoseDetail = new UsersRoseDetail();
                            usersRoseDetail.setUser_id(recommentUsers.getId());
                            usersRoseDetail.setName("用户推广收益");
                            usersRoseDetail.setIntegral_number(1);
                            usersRoseDetail.setTransactions_type(0);
                            usersRoseDetail.setTransactions_date(new Date());
                            int addState = this.usersRoseDetailService.insertNewRecord(usersRoseDetail);
                            if (addState > 0){
                                this.userRecommendUserRecordService.updateStateIsFinish(userRecommendUserRecord.getId());
                                recommentUsers.setSilver_coin_balance(users.getSilver_coin_balance() + 1);
                                this.usersService.updateUserTYAndXFAndSilverCoinBalance(recommentUsers);
                                if (recommentUsers.getGetui_appid() != null && !recommentUsers.getGetui_appid().equals("")){
                                    String pushTitle = "玫瑰收入";
                                    String pushContent = "你有个成熟的团队，你已经长大了，奖励你一枝玫瑰,请在猛戳-我的玫瑰查看";
                                    GeTuiUserUtil.pushUser(recommentUsers.getGetui_appid(),pushTitle,pushContent);
                                }
                            }
                        }
                    }
                }
            }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return 0;
    }
}
