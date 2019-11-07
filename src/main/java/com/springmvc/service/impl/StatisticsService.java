package com.springmvc.service.impl;

import com.springmvc.dao.StatisticsMapper;
import com.springmvc.service.IStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: 杨新杰
 * @date: 2018/11/30 15:05
 */
@Service("/statisticsService")
public class StatisticsService implements IStatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;
    /**
     * 功能描述: 查询建议反馈统计信息
     *
     * @return:
     * @author: 杨新杰
     * @date: 15:08 2018/11/30
     */
    @Override
    public Map<String,Object> selectSuggestStatisticsInfo() {
        return statisticsMapper.selectSuggestStatisticsInfo();
    }

    /**
     * 功能描述:  获取用户信息
     *
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @Override
    public Map<String, Object> selectUserStatisticsInfo() {
        return this.statisticsMapper.selectUserStatisticsInfo();
    }

    /**
     * 功能描述:  获取商家信息
     *
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @Override
    public Map<String, Object> selectBusinessStatisticsInfo() {
        return this.statisticsMapper.selectBusinessStatisticsInfo();
    }

    /**
     * 功能描述:  根据商家等级获取当前等级商户数量
     *
     * @param id
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @Override
    public Map<String, Object> StatisticsLevelNumber(Integer id) {
        return this.statisticsMapper.StatisticsLevelNumber(id);
    }

    /**
     * 功能描述: 玫瑰兑换 - 商家
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectBusinessRoseDetailStatisticsInfo() {
        return this.statisticsMapper.selectBusinessRoseDetailStatisticsInfo();
    }

    /**
     * 功能描述: 玫瑰兑换 - 用户
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectUserRoseDetailStatisticsInfo() {
        return this.statisticsMapper.selectUserRoseDetailStatisticsInfo();
    }

    /**
     * 功能描述: 充值统计 - 商家
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectBusinessBalanceStatisticsInfo() {
        return this.statisticsMapper.selectBusinessBalanceStatisticsInfo();
    }

    /**
     * 功能描述: 充值统计 - 用户
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectUserBalanceStatisticsInfo() {
        return this.statisticsMapper.selectUserBalanceStatisticsInfo();
    }


    /**
     * 功能描述: 提现统计 - 用户
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectUserWithdrawStatisticsInfo() {
        return this.statisticsMapper.selectUserWithdrawStatisticsInfo();
    }

    /**
     * 功能描述: 提现统计 - 商家
     *
     * @return:
     * @author: 杨新杰
     * @date: 10:30 2018/12/3
     */
    @Override
    public Map<String, Object> selectBusinessWithdrawStatisticsInfo() {
        return this.statisticsMapper.selectBusinessWithdrawStatisticsInfo();
    }

    /**
     *
     * 功能描述: 商品订单记录 - 商家
     *
     * @return:
     * @author: 刘武祥
     * @Date: 15:56 2018/12/12
     */
    @Override
    public Map<String, Object> selectOrdersStatisticsInfo() {
        return this.statisticsMapper.selectOrdersStatisticsInfo();
    }
}
