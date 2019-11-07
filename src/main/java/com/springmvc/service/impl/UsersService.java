package com.springmvc.service.impl;

import com.springmvc.dao.UsersMapper;
import com.springmvc.entity.MemberLevels;
import com.springmvc.entity.UserRecommendChange;
import com.springmvc.entity.Users;
import com.springmvc.service.IUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员管理实现类
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 15:43
 */
@Service("/usersService")
public class UsersService implements IUsersService{
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private UserOwnerAuthenticationService userOwnerAuthenticationService;
    @Resource
    private UserCardAuthenticationService userCardAuthenticationService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;
    @Resource
    private UserSecurityQuestionService userSecurityQuestionService;
    @Resource
    private UserReceivingAddressService userReceivingAddressService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private SuggestionFeedbackService suggestionFeedbackService;
    @Resource
    private ConsumptionIntegralIncomeService consumptionIntegralIncomeService;
    @Resource
    private ConsumptionIntegralExpenditureService consumptionIntegralExpenditureService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private SilverCoinExchangeService silverCoinExchangeService;
    @Resource
    private UsersWithdrawOrderService usersWithdrawOrderService;
    @Resource
    private MyMembershipCardService myMembershipCardService;
    @Resource
    private MyCouponService myCouponService;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private UserRecommendChangeService userRecommendChangeService;

    @Override
    public Users selectByMobileAndLoginPWD(String mobile, String login_pwd) {
        return this.usersMapper.selectByMobileAndLoginPWD(mobile, login_pwd);
    }

    @Override
    public Users selectById(Integer user_id) {
        if (user_id == null){
            return null;
        }
        return this.usersMapper.selectById(user_id);
    }

    @Override
    public Users selectByWxUnionid(String wx_unionid) {
        return this.usersMapper.selectByWxUnionid(wx_unionid);
    }

    @Override
    public Users selectByMobile(String mobile) {
        return this.usersMapper.selectByMobile(mobile);
    }

    @Override
    public int userMobileRegister(Users users) {
        users.setNick_name("无名");
        users.setHeader("default_header.jpg");
        users.setSex(2);
        MemberLevels defaultMemberLevel = this.memberLevelsService.selectDefaultLevel();
        if (defaultMemberLevel == null){
            users.setMember_level_id(-1);
        }
        else{
            users.setMember_level_id(defaultMemberLevel.getId());
        }
        users.setUser_qrcode_url("icon_ewm.png");
        users.setRegister_time(new Date());
        if (users.getRecommend_user_id() != -1 && users.getRecommend_type() != 1){
            Users recommendUsers = this.usersMapper.selectById(users.getRecommend_user_id());
            if (recommendUsers.getMember_star() < 5){
                recommendUsers.setMember_star(recommendUsers.getMember_star()+1);
                this.usersMapper.updateUserMemberStar(recommendUsers);
            }
        }

        return this.usersMapper.userMobileRegister(users);
    }

    @Override
    public int updateUserNickName(Users users) {
        return this.usersMapper.updateUserNickName(users);
    }

    @Override
    public int updateUserMobile(Users users) {
        return this.usersMapper.updateUserMobile(users);
    }

    @Override
    public int updateUserEmail(Users users) {
        return this.usersMapper.updateUserEmail(users);
    }

    @Override
    public int updateUserSex(Users users) {
        return this.usersMapper.updateUserSex(users);
    }

    @Override
    public int updateUserHeader(Users users) {
        return this.usersMapper.updateUserHeader(users);
    }

    @Override
    public int updateLoginPwd(Users users) {
        return this.usersMapper.updateLoginPwd(users);
    }

    @Override
    public int updatePayPwd(Users users) {
        return this.usersMapper.updatePayPwd(users);
    }

    @Override
    public int updateIsMicrofinance(Users users) {
        return this.usersMapper.updateIsMicrofinance(users);
    }

    @Override
    public List<Map<Object, Object>> selectUserByRecommendUserId(Integer user_id) {
        return this.usersMapper.selectUserByRecommendUserId(user_id);
    }

    @Override
    public int relationAccount(Integer relation_user_id, Integer isrelation_user_id) {
        this.userOwnerAuthenticationService.deleteRecordByUserId(isrelation_user_id);
        this.userCardAuthenticationService.deleteRecordByUserId(isrelation_user_id);
        this.userIdCardAuthenticationService.deleteRecordByUserId(isrelation_user_id);
        this.userSecurityQuestionService.deleteRecordByUserId(isrelation_user_id);
        this.userReceivingAddressService.deleteRecordByUserId(isrelation_user_id);
        this.generalIntegralIncomeService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.silverCoinDetailedService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.suggestionFeedbackService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.usersMapper.selectUserUpdateRecommendUserId(relation_user_id,isrelation_user_id);
        this.consumptionIntegralIncomeService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.consumptionIntegralExpenditureService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.generalIntegralExpenditureService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.silverCoinExchangeService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.usersWithdrawOrderService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.myMembershipCardService.updateRecordUserId(relation_user_id, isrelation_user_id);
        this.myCouponService.updateRecordUserId(relation_user_id, isrelation_user_id);
        return this.usersMapper.deleteRecordByUserId(isrelation_user_id);
    }

    @Override
    public int relationAccountUpdateInformation(Users users) {
        return this.usersMapper.relationAccountUpdateInformation(users);
    }

    @Override
    public int updateUserTYAndXFAndSilverCoinBalance(Users users) {
        return this.usersMapper.updateUserTYAndXFAndSilverCoinBalance(users);
    }

    @Override
    public int userWXRegister(Users users) {
        return this.usersMapper.userWXRegister(users);
    }

    @Override
    public int updateUserQrcode(Users users) {
        return this.usersMapper.updateUserQrcode(users);
    }

    @Override
    public int updateUserMemberLevelId(Users users) {
        return this.usersMapper.updateUserMemberLevelId(users);
    }

    @Override
    public int userExchangeSlivreCoinUpdateUserLevle(Integer integralNumber, Users users) {
        List<Map<Object,Object>> list = this.memberLevelsService.selectLevelBySJTJ(integralNumber);
        Integer maxId = this.memberLevelsService.selectMaxValueId();
        if (list.size() > 0){
            if (users.getMember_level_id() != maxId){
                Map<Object,Object> map = list.get(0);
                users.setMember_level_id((Integer) map.get("id"));
                this.systemMessagesService.addMemberLevelUpdateMessgae(users.getId(),(String)map.get("name"));
                String Level_name = (String)map.get("name");
                if (Level_name.equals("钻石会员")){
                    Integer canWithdrawSilverCoinBalance = this.silverCoinDetailedService.getCanWithdrawSilverCoinBalance(users.getId());
                    Integer noCanSilverCoinNumber = (users.getSilver_coin_balance() - canWithdrawSilverCoinBalance) / 2;
                    users.setSilver_coin_balance(noCanSilverCoinNumber+canWithdrawSilverCoinBalance);
                }
                return this.usersMapper.updateUserMemberLevelId(users);
            }
            else{
                return 0;
            }

        }
        else{
            return 0;
        }
    }

    @Override
    public List<Map<Object, Object>> selectUserRecommendBeforeFiveUsersLevelName(Integer recommend_user_id) {
        return this.usersMapper.selectUserRecommendBeforeFiveUsersLevelName(recommend_user_id);
    }

    @Override
    public List<Map<Object, Object>> getAllUsersAdmin() {
        return this.usersMapper.getAllUsersAdmin();
    }

    @Override
    public int updateUserWXOpenId(Users users) {
        return this.usersMapper.updateUserWXOpenId(users);
    }

    @Override
    public int updateMemberCardLevel(Users users) {
        return this.usersMapper.updateMemberCardLevel(users);
    }

    @Override
    public List<Map<Object, Object>> selectByMemberCardByRecomendIdAndMemberCardLevel(Integer recomend_id, Integer member_card_level) {
        return this.usersMapper.selectByMemberCardByRecomendIdAndMemberCardLevel(recomend_id, member_card_level);
    }

    @Override
    public List<Map<Object, Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelYKOrJK(Integer recomend_id) {
        return this.usersMapper.selectUserRecommendBeforeFiveUsersMemberCardLevelYKOrJK(recomend_id);
    }

    @Override
    public List<Map<Object, Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelJK(Integer recomend_id) {
        return this.usersMapper.selectUserRecommendBeforeFiveUsersMemberCardLevelJK(recomend_id);
    }

    @Override
    public int updateUserIntegralShoppingIntegral(Users users) {
        return this.usersMapper.updateUserIntegralShoppingIntegral(users);
    }

    /**
     *  更新用户积分 (用户现在的积分减去扣除的积分)
     * @param map 用户ID和用户剩余积分
     * @return 返回影响行数
     *
     * @author 杨新杰
     */
    @Override
    public int updateUserIntegralById(Map<String,Object> map) {
        return this.usersMapper.updateUserIntegralById(map);
    }

    @Override
    public List<Map<Object, Object>> selectUserByBusinessId(Integer business_id) {
        return this.usersMapper.selectUserByBusinessId(business_id);
    }

    /**
     *
     * 功能描述: 更新用户信息 (后台管理员更新用户信息专用)
     *
     * @param   users 用户信息实体类
     * @return  成功返回 > 0 失败返回 < 0
     * @author: 杨新杰
     * @date: 16:50 2018/11/28
     */
    @Override
    public int adminUpdateUserInfoById(Users users) {
        return this.usersMapper.adminUpdateUserInfoById(users);
    }

    /**
     *
     * 功能描述:更新用户推荐人id及推荐类别
     *
     * @param:  recommend_user_id
     * @param:  recommend_type
     * @param:  user_id
     * @return: 更新用户推荐人id及推荐类别
     * @author: zhangfan
     * @date: 2018/12/2 12:25 AM
     */
    @Override
    public int updateUserRecommendIdAndRecommendType(Integer recommend_user_id, Integer recommend_type, Integer user_id) {
        return this.usersMapper.updateUserRecommendIdAndRecommendType(recommend_user_id, recommend_type, user_id);
    }

    /**
     *
     * 功能描述: 商家变更用户推荐人
     *
     * @param recommend_user_id 推荐人ID
     * @param recommend_type 推荐人类型(0-用户,1-商家)
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/16 10:08 PM
     */
    @Override
    public int businessChangeUserRecommend(Integer recommend_user_id, Integer recommend_type, Integer user_id) {
        Users users = this.usersMapper.selectById(user_id);
        if (users != null){
            UserRecommendChange userRecommendChange = new UserRecommendChange();
            userRecommendChange.setBefore_type(users.getRecommend_type());
            userRecommendChange.setBefore_recommend_user_id(users.getRecommend_user_id());
            userRecommendChange.setAfter_type(recommend_type);
            userRecommendChange.setAfter_recommend_user_id(recommend_user_id);
            userRecommendChange.setChange_date(new Date());
            userRecommendChange.setUser_id(user_id);
            this.userRecommendChangeService.insertChangeRecord(userRecommendChange);
            return this.usersMapper.changeUserRecommend(recommend_user_id, recommend_type,user_id);
        }
        else{
            return 0;
        }
    }

    /**
     *
     * 功能描述: 更新用户个推APPID
     *
     * @param:  users 用户实体类
     * @return:
     * @author: zhangfan
     * @date: 2018/12/19 12:42 AM
     */
    @Override
    public int updateUserGeTuiAppId(Users users) {
        return this.usersMapper.updateUserGeTuiAppId(users);
    }

    /**
     *
     * 功能描述: 根据条件查询搜索会员用户
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/9 0009 16:35
     */
    @Override
    public List<Map<String, Object>> adminSearchMemberByConditions(Map<String, Object> map) {
        return this.usersMapper.adminSearchMemberByConditions(map);
    }


}
