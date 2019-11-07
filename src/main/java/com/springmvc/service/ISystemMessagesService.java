package com.springmvc.service;

import com.springmvc.entity.SystemMessages;
import com.springmvc.entity.SystemNoticeMessage;

import java.util.List;
import java.util.Map;

public interface ISystemMessagesService {
    /**
     * 新增系统消息
     * @param systemMessages
     * @return
     */
    int insertSystemMessage(SystemMessages systemMessages);

    /**
     * 查询某个用户的所有系统消息
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectUserAllMessage(Integer user_id);

    /**
     * 新增团队兑换银币收益消息(exchange_user_id:兑换银币的用户id,send_user_id:接收消息的用户id,silver_coin_number:兑换银币个数)
     * @param exchange_user_id   兑换银币的用户id
     * @param send_user_id       接收消息的用户id
     * @param silver_coin_number 兑换银币个数
     * @return
     */
    int addTeamExchangeSliverCoinMessage(Integer exchange_user_id,Integer send_user_id,Integer silver_coin_number);

    /**
     * 新增成功推荐注册消息(register_user_id:注册的用户id,send_user_id:接收消息的用户id)
     * @param register_user_id 注册的用户id
     * @param send_user_id     接收消息的用户id
     * @return
     */
    int addRecommendSuccessMessgae(Integer register_user_id,Integer send_user_id);

    /**
     * 新增银币兑换消息(send_user_id:接收消息的用户id,integral_number:积分个数,soliver_number:银币个数)
     * @param send_user_id     接收消息的用户id
     * @param integral_number  积分个数
     * @param soliver_number   银币个数
     * @return
     */
    int addSoliverExahngeSuccessMessgae(Integer send_user_id,Integer integral_number,Integer soliver_number);

    /**
     * 会员升级提醒(send_user_id:接收消息的用户id,level_name:等级名称)
     * @param send_user_id 接收消息的用户id
     * @param level_name   等级名称
     * @return
     */
    int addMemberLevelUpdateMessgae(Integer send_user_id,String level_name);

    /**
     * 根据消息id查询消息
     * @param message_id 消息id
     * @return
     */
    SystemMessages selectMessageDetailByMessageId(Integer message_id);

    /**
     * 更新消息为已读状态
     * @param message_id 消息id
     * @return
     */
    int updateMessageForRead(Integer message_id);
}
