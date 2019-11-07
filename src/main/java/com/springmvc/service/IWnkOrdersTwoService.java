package com.springmvc.service;

import com.springmvc.entity.WnkOrdersTwo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:01
 * @Description:
 */
public interface IWnkOrdersTwoService {
    /**
     *
     * 功能描述: 插入新订单
     *
     * @param wnkOrdersTwo 订单信息
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 4:55 PM
     */
    int insertNewOrder(WnkOrdersTwo wnkOrdersTwo);

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
    int updateOrderPayDateByOrderNo(Date pay_time, String order_no);

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
    int updateOrderStateByOrderNo(Integer state,String order_no);

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
    int updateOrderQrCodeByOrderNo(String order_qrcode,String order_no);

    /**
     *
     * 功能描述: 通过订单号查询订单
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 2:20 AM
     */
    WnkOrdersTwo selectByOrderNo(String order_no);

    /**
     *
     * 功能描述: 通过订单id查询订单
     *
     * @param order_id 订单id
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 2:47 AM
     */
    List<Map<Object,Object>> selectByIdReturnMap(Integer order_id);

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
    List<Map<Object,Object>> userSelectUserOrder(Integer user_id,Integer state);

    /**
     *
     * 功能描述: 通过订单id查询订单
     *
     * @param order_id 订单ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 3:41 AM
     */
    WnkOrdersTwo selectByOrderId(Integer order_id);

    /**
     * 查询某个商家的订单通过商家id以及状态
     * @param business_id
     * @param state
     * @return
     */
    List<Map<Object,Object>> selectOrderByBusinessIdAndState(Integer business_id, Integer state);

    /**
     * 按用户查询消费用户数量(查询消费用户总数列表)
     * @param business_id
     * @param state
     * @return
     */
    List<Map<Object,Object>> selectUserNumberByBusinessId(Integer business_id,Integer state);

    /**
     *
     * 功能描述: 查询用户当日在对应商家购买商品数量
     *
     * @param   user_id 用户id
     * @param   business_id  商家ID
     * @return
     * @author  杨新杰
     * @date    11:44 2019/1/7
     */
    Map<String,Object> selectUserToDaysBusinessOrderNumber(Integer user_id, Integer business_id);

    /**
     *
     * 方法说明:查询所有酒店类商家未确认的订单
     *
     * @author 杨新杰
     * @Date  2019/1/25
     * @Param
     * @return
     **/
    List<WnkOrdersTwo> selectHotelBusinessNotConfirm();
}
