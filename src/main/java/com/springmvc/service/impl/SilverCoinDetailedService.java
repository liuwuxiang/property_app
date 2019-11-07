package com.springmvc.service.impl;

import com.springmvc.dao.SilverCoinDetailedMapper;
import com.springmvc.dao.UsersMapper;
import com.springmvc.entity.MemberLevels;
import com.springmvc.entity.SilverCoinDetailed;
import com.springmvc.entity.Users;
import com.springmvc.service.ISilverCoinDetailedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/silverCoinDetailedService")
public class SilverCoinDetailedService implements ISilverCoinDetailedService{
    @Resource
    private SilverCoinDetailedMapper silverCoinDetailedMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private MemberLevelsService memberLevelsService;

    @Override
    public List<Map<Object, Object>> selectSilverCoinDetailByUser(Integer user_id) {
        return this.silverCoinDetailedMapper.selectSilverCoinDetailByUser(user_id);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.silverCoinDetailedMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public int insertExchangeRecord(SilverCoinDetailed silverCoinDetailed) {
        return this.silverCoinDetailedMapper.insertExchangeRecord(silverCoinDetailed);
    }

    @Override
    public int deleteRecordById(Integer id) {
        return this.silverCoinDetailedMapper.deleteRecordById(id);
    }

    @Override
    public int insertTeamExchangeRecord(Integer user_id, Integer silver_coin_number) {
        Users users = this.usersMapper.selectById(user_id);
        if (users == null){
            return 0;
        }
        else if (users.getRecommend_user_id() == -1){
            return 0;
        }
        else{
            Users recommendUsers = this.usersMapper.selectById(users.getRecommend_user_id());
            if (recommendUsers == null){
                return 0;
            }
            else{
                MemberLevels memberLevels = this.memberLevelsService.selectById(recommendUsers.getMember_level_id());
                if (memberLevels.getName().equals("普通会员")){
                    return 0;
                }
                else{
                    recommendUsers.setSilver_coin_balance(recommendUsers.getSilver_coin_balance() + silver_coin_number);
                    SilverCoinDetailed silverCoinDetailed = new SilverCoinDetailed();
                    silverCoinDetailed.setName("团队兑换银币");
                    silverCoinDetailed.setRecord_date(new Date());
                    silverCoinDetailed.setTransaction_amount(silver_coin_number);
                    silverCoinDetailed.setTransaction_after_balance(recommendUsers.getSilver_coin_balance());
                    silverCoinDetailed.setIncome_expenditure_type(1);
                    silverCoinDetailed.setTransaction_type(4);
                    silverCoinDetailed.setUser_id(recommendUsers.getId());
                    int insertState = this.silverCoinDetailedMapper.insertExchangeRecord(silverCoinDetailed);
                    if (insertState >= 1){
                        int updateState = this.usersMapper.updateUserTYAndXFAndSilverCoinBalance(recommendUsers);
                        if (updateState >= 1){
                            this.systemMessagesService.addTeamExchangeSliverCoinMessage(user_id,recommendUsers.getId(),silver_coin_number);
                            return 1;
                        }
                        else{
                            this.silverCoinDetailedMapper.deleteRecordById(silverCoinDetailed.getId());
                            return 0;
                        }
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
    }

    @Override
    public List<Map<Object, Object>> getCanWithdrawSilverCoinDetail(Integer user_id) {
        return this.silverCoinDetailedMapper.getCanWithdrawSilverCoinDetail(user_id);
    }

    @Override
    public Integer getCanWithdrawSilverCoinBalance(Integer user_id) {
        return this.silverCoinDetailedMapper.getCanWithdrawSilverCoinBalance(user_id);
    }

    @Override
    public int updateCanWithdrawState(Integer user_id) {
        return this.silverCoinDetailedMapper.updateCanWithdrawState(user_id);
    }

    @Override
    public int insertWithdrawRecord(SilverCoinDetailed silverCoinDetailed) {
        silverCoinDetailed.setName("银币提现");
        silverCoinDetailed.setRecord_date(new Date());
        silverCoinDetailed.setIncome_expenditure_type(0);
        silverCoinDetailed.setTransaction_type(5);
        return this.silverCoinDetailedMapper.insertWithdrawRecord(silverCoinDetailed);
    }
}
