package com.springmvc.service.impl;

import com.springmvc.dao.UserOpenBusinessMemberCardWXOrdersMapper;
import com.springmvc.entity.UserOpenBusinessMemberCardWXOrders;
import com.springmvc.service.IUserOpenBusinessMemberCardWXOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/11/6 23:31
 * @Description:用户向商家开卡微信支付订单服务实现类
 */
@Service("/userOpenBusinessMemberCardWXOrdersService")
public class UserOpenBusinessMemberCardWXOrdersService implements IUserOpenBusinessMemberCardWXOrdersService {
    @Resource
    private UserOpenBusinessMemberCardWXOrdersMapper userOpenBusinessMemberCardWXOrdersMapper;

    /**
     *
     * 功能描述: 插入新订单
     *
     * @param userOpenBusinessMemberCardWXOrders 订单信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/6 11:28 PM
     */
    @Override
    public int insertNewOrder(UserOpenBusinessMemberCardWXOrders userOpenBusinessMemberCardWXOrders) {
        return this.userOpenBusinessMemberCardWXOrdersMapper.insertNewOrder(userOpenBusinessMemberCardWXOrders);
    }

    /**
     *
     * 功能描述: 通过订单号查询订单
     *
     * @param order_no 订单号
     * @return: UserOpenBusinessMemberCardWXOrders
     * @author: zhangfan
     * @date: 2018/11/6 11:29 PM
     */
    @Override
    public UserOpenBusinessMemberCardWXOrders selectByOrderNo(String order_no) {
        return this.userOpenBusinessMemberCardWXOrdersMapper.selectByOrderNo(order_no);
    }

    /**
     *
     * 功能描述:通过订单号更新订单状态
     *
     * @param state-订单状态(0-待支付,1-已支付,0-支付失败)
     * @param order_no-订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/11/6 11:29 PM
     */
    @Override
    public int updateOrderStateByOrderNo(Integer state, String order_no) {
        return this.userOpenBusinessMemberCardWXOrdersMapper.updateOrderStateByOrderNo(state, order_no);
    }
}
