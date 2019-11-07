package com.springmvc.service.impl;

import com.springmvc.dao.SystemMessagesMapper;
import com.springmvc.dao.UsersMapper;
import com.springmvc.entity.SystemMessages;
import com.springmvc.entity.SystemNoticeMessage;
import com.springmvc.entity.Users;
import com.springmvc.service.ISystemMessagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/systemMessagesService")
public class SystemMessagesService implements ISystemMessagesService{
    @Resource
    private SystemMessagesMapper systemMessagesMapper;
    @Resource
    private UsersMapper usersMapper;


    @Override
    public int insertSystemMessage(SystemMessages systemMessages) {
        return this.systemMessagesMapper.insertSystemMessage(systemMessages);
    }

    @Override
    public List<Map<Object, Object>> selectUserAllMessage(Integer user_id) {
        return this.systemMessagesMapper.selectUserAllMessage(user_id);
    }

    //新增团队兑换银币收益消息(exchange_user_id:兑换银币的用户id,send_user_id:接收消息的用户id,silver_coin_number:兑换银币个数)
    @Override
    public int addTeamExchangeSliverCoinMessage(Integer exchange_user_id, Integer send_user_id, Integer silver_coin_number) {
        Users users = this.usersMapper.selectById(send_user_id);
        Users exchangeUsers = this.usersMapper.selectById(exchange_user_id);
        if (users == null || exchangeUsers == null){
            return 0;
        }
        else{
            SystemMessages systemMessages = new SystemMessages();
            systemMessages.setTitle("团队兑换银币收益提醒");
            systemMessages.setContent("您的团队成员："+exchangeUsers.getMobile()+"成功兑换"+silver_coin_number+"个银币，您同时获得"+silver_coin_number+"个银币奖励!");
            systemMessages.setUser_id(send_user_id);
            systemMessages.setSend_date(new Date());
            systemMessages.setType(0);
            int addState = this.systemMessagesMapper.insertSystemMessage(systemMessages);
            return addState;
        }
    }

    //新增成功推荐注册消息(register_user_id:注册的用户id,send_user_id:接收消息的用户id)
    @Override
    public int addRecommendSuccessMessgae(Integer register_user_id, Integer send_user_id) {
        Users users = this.usersMapper.selectById(send_user_id);
        Users registerUsers = this.usersMapper.selectById(register_user_id);
        if (users == null || registerUsers == null){
            return 0;
        }
        else{
            SystemMessages systemMessages = new SystemMessages();
            systemMessages.setTitle("成功推荐注册提醒");
            systemMessages.setContent("恭喜您成功推荐："+registerUsers.getMobile()+"注册为会员，您的团队又新增一位成员啦!");
            systemMessages.setUser_id(send_user_id);
            systemMessages.setSend_date(new Date());
            systemMessages.setType(1);

            int addState = this.systemMessagesMapper.insertSystemMessage(systemMessages);
            return addState;

        }
    }

    @Override
    public int addSoliverExahngeSuccessMessgae(Integer send_user_id, Integer integral_number, Integer soliver_number) {
        SystemMessages systemMessages = new SystemMessages();
        systemMessages.setTitle("兑换银币成功提醒");
        systemMessages.setContent("恭喜您使用："+integral_number+"个积分成功兑换"+soliver_number+"个银币!");
        systemMessages.setUser_id(send_user_id);
        systemMessages.setSend_date(new Date());
        systemMessages.setType(1);

        int addState = this.systemMessagesMapper.insertSystemMessage(systemMessages);
        return addState;
    }

    @Override
    public int addMemberLevelUpdateMessgae(Integer send_user_id, String level_name) {
        SystemMessages systemMessages = new SystemMessages();
        systemMessages.setTitle("会员等级升级提醒");
        systemMessages.setContent("恭喜您已升级为："+level_name+"!");
        systemMessages.setUser_id(send_user_id);
        systemMessages.setSend_date(new Date());
        systemMessages.setType(1);

        int addState = this.systemMessagesMapper.insertSystemMessage(systemMessages);
        return addState;
    }

    @Override
    public SystemMessages selectMessageDetailByMessageId(Integer message_id) {
        return this.systemMessagesMapper.selectMessageDetailByMessageId(message_id);
    }

    @Override
    public int updateMessageForRead(Integer message_id) {
        return this.systemMessagesMapper.updateMessageForRead(message_id);
    }
}
