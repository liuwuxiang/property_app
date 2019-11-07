package com.springmvc.service;

import com.springmvc.utils.WX.HttpXmlClient;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {
    /**
     *
     * 功能描述: 查询建议反馈统计信息
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 15:08 2018/11/30
     */
    Map<String,Object> selectSuggestStatisticsInfo();

    /**
     *
     * 功能描述:  获取用户信息
     *
     * @author 杨新杰
     * @date   2018/10/22 0022 16:11
     */
    Map<String,Object> selectUserStatisticsInfo();

    /**
     *
     * 功能描述:  获取商家信息
     *
     * @author 杨新杰
     * @date   2018/10/22 0022 16:11
     */
    Map<String,Object> selectBusinessStatisticsInfo();

    /**
     *
     * 功能描述:  根据商家等级获取当前等级商户数量
     *
     * @author 杨新杰
     * @date   2018/10/22 0022 16:11
     */
    Map<String,Object> StatisticsLevelNumber(Integer id);


    /**
     *
     * 功能描述: 玫瑰兑换 - 商家
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String,Object> selectBusinessRoseDetailStatisticsInfo();

    /**
     *
     * 功能描述: 玫瑰兑换 - 用户
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String,Object> selectUserRoseDetailStatisticsInfo();

    /**
     *
     * 功能描述: 充值统计 - 商家
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String,Object> selectBusinessBalanceStatisticsInfo();
    /**
     *
     * 功能描述: 充值统计 - 用户
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String,Object> selectUserBalanceStatisticsInfo();

    /**
     *
     * 功能描述: 提现统计 - 用户
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String,Object> selectUserWithdrawStatisticsInfo();

    /**
     * 功能描述: 提现统计 - 商家
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    Map<String, Object> selectBusinessWithdrawStatisticsInfo();

    /** 
     *
     * 功能描述: 商品订单记录 - 商家
     *
     * @param
     * @return: 
     * @author: 刘武祥
     * @Date: 16:04 2018/12/12
     */
    Map<String, Object> selectOrdersStatisticsInfo() ;
}
