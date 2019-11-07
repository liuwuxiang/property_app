package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrdersTwoMapper;
import com.springmvc.entity.WnkOrdersTwo;
import com.springmvc.service.IWnkOrdersTwoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:01
 * @Description:
 */
@Service("/wnkOrdersTwoService")
public class WnkOrdersTwoService implements IWnkOrdersTwoService{
    @Resource
    private WnkOrdersTwoMapper wnkOrdersTwoMapper;

    /**
     *
     * 功能描述: 插入新订单
     *
     * @param wnkOrdersTwo 订单信息
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 4:55 PM
     */
    @Override
    public int insertNewOrder(WnkOrdersTwo wnkOrdersTwo) {
        return this.wnkOrdersTwoMapper.insertNewOrder(wnkOrdersTwo);
    }

    /**
     *
     * 功能描述: 更新订单支付时间
     *
     * @param pay_time 支付时间
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 4:57 PM
     */
    @Override
    public int updateOrderPayDateByOrderNo(Date pay_time, String order_no) {
        return this.wnkOrdersTwoMapper.updateOrderPayDateByOrderNo(pay_time, order_no);
    }

    /**
     *
     * 功能描述: 更新订单支付状态
     *
     * @param state 支付状态(0-待支付,1-待使用,2-完成)
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 4:59 PM
     */
    @Override
    public int updateOrderStateByOrderNo(Integer state, String order_no) {
        return this.wnkOrdersTwoMapper.updateOrderStateByOrderNo(state, order_no);
    }

    /**
     *
     * 功能描述: 更新订单二维码
     *
     * @param order_qrcode 订单二维码
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:00 PM
     */
    @Override
    public int updateOrderQrCodeByOrderNo(String order_qrcode, String order_no) {
        return this.wnkOrdersTwoMapper.updateOrderQrCodeByOrderNo(order_qrcode, order_no);
    }

    /**
     *
     * 功能描述: 通过订单号查询订单
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 2:20 AM
     */
    @Override
    public WnkOrdersTwo selectByOrderNo(String order_no) {
        return this.wnkOrdersTwoMapper.selectByOrderNo(order_no);
    }

    /**
     *
     * 功能描述: 通过订单id查询订单
     *
     * @param order_id 订单id
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 2:47 AM
     */
    @Override
    public List<Map<Object, Object>> selectByIdReturnMap(Integer order_id) {
        return this.wnkOrdersTwoMapper.selectByIdReturnMap(order_id);
    }

    /**
     *
     * 功能描述: 通过用户id以及状态查询用户订单
     *
     * @param user_id 用户ID
     * @param state 订单状态
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 3:09 AM
     */
    @Override
    public List<Map<Object, Object>> userSelectUserOrder(Integer user_id, Integer state) {
        return this.wnkOrdersTwoMapper.userSelectUserOrder(user_id, state);
    }

    /**
     *
     * 功能描述: 通过订单id查询订单
     *
     * @param order_id 订单ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 3:41 AM
     */
    @Override
    public WnkOrdersTwo selectByOrderId(Integer order_id) {
        return this.wnkOrdersTwoMapper.selectByOrderId(order_id);
    }

    /**
     * 查询某个商家的订单通过商家id以及状态
     * @param business_id
     * @param state
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectOrderByBusinessIdAndState(Integer business_id, Integer state) {
        return this.wnkOrdersTwoMapper.selectOrderByBusinessIdAndState(business_id, state);
    }

    /**
     * 按用户查询消费用户数量(查询消费用户总数列表)
     * @param business_id
     * @param state
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectUserNumberByBusinessId(Integer business_id, Integer state) {
        return this.wnkOrdersTwoMapper.selectUserNumberByBusinessId(business_id, state);
    }

    /**
     * 功能描述: 查询用户当日在对应商家购买商品数量
     *
     * @param user_id     用户id
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 11:44 2019/1/7
     */
    @Override
    public Map<String,Object> selectUserToDaysBusinessOrderNumber(Integer user_id, Integer business_id) {
        return this.wnkOrdersTwoMapper.selectUserToDaysBusinessOrderNumber(user_id,business_id);
    }

    /**
     * 方法说明:查询所有酒店类商家未确认的订单
     *
     * @return
     * @author 杨新杰
     * @Date 2019/1/25
     * @Param
     **/
    @Override
    public List<WnkOrdersTwo> selectHotelBusinessNotConfirm() {
        return this.wnkOrdersTwoMapper.selectHotelBusinessNotConfirm();
    }
}
