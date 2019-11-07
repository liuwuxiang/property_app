package com.springmvc.service;

import com.springmvc.entity.AboutUs;

import java.util.Map;

/**
 * 关于用户Service
 */
public interface IAboutUsService {
    /**
     * 查询关于我们的富文本信息
     * @return 返回查询结果
     */
    AboutUs selectAboutUs();

    /**
     * 查询通用积分以及消费积分相关说明<br>
     * @param type <br>
     *        1-消费积分如何使用<br>
     *        2-消费积分如何获得<br>
     *        3-消费积分扣减规则<br>
     *        4-通用积分如何使用<br>
     *        5-通用积分如何获得<br>
     *        6-通用积分扣减规则<br>
     * @return 返回查询结果
     */
    AboutUs selectIntegralAbout(Integer type);

    /**
     * 设置积分与银币的兑换比例
     * @param content
     * @return 成功返回1 失败返回0
     */
    int setIntegralAndCoin(String content);

    /**
     * 设置积分与人民币的兑换比例
     * @param content
     * @return 成功返回1 失败返回0
     */
    int setIntegralAndRMB(String content);

    /**
     *
     * 功能描述: 新增记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:34 AM
     */
    int addRecord(AboutUs aboutUs);

    /**
     *
     * 功能描述: 通过type更新记录信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:37 AM
     */
    int updateRecordInformation(AboutUs aboutUs);

    /**
     * 根据类型查询 法律声明、价格声明、隐私政策、餐饮安全管理
     * @param type
     * @return
     */
    Map<String,Object> selectAboutByType(String type);

    /**
     *
     * 功能描述: 更新或者新增 法律声明/价格声明/隐私政策/餐饮安全管理内容
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    16:54 2018/12/11
     */
    int updateAboutInfo(String type, String about_content);

    /**
     *
     * 功能描述: 平台积分兑换信息保存/更新
     *
     * @param   exchange_bili       兑换比例
     * @param   exchange_start_time 开发时间
     * @param   exchange_shop_time  结束时间
     * @param   min_exchange_number 最低兑换数量
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    11:34 2018/12/12
     */
    int updateIntegralExchangeInfo(String exchange_bili, String exchange_start_time, String exchange_shop_time, String min_exchange_number);
}
