package com.springmvc.service;

import com.springmvc.entity.WnkBusinessRecommendUserRecord;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 04:31
 * @Description:商家推荐用户记录器服务接口类-用于记录万能卡商家推荐用户记录(记录商家推荐了多少个，每条记录满3状态变更为已兑换，依次循环)
 */
public interface IWnkBusinessRecommendUserRecordService {
    /**
     *
     * 功能描述: 添加记录
     *
     * @param: business_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:29 AM
     */
    int insertRecord(Integer business_id);

    /**
     *
     * 功能描述: 查询商家未兑换的记录
     *
     * @param: business_id
     * @return: WnkBusinessRecommendUserRecord
     * @author: zhangfan
     * @date: 2018/10/29 4:29 AM
     */
    WnkBusinessRecommendUserRecord selectNoExchangeByBusinessId(Integer business_id);

    /**
     *
     * 功能描述: 更新商家推荐数量
     *
     * @param: wnkBusinessRecommendUserRecord
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:30 AM
     */
    int updateInformation(WnkBusinessRecommendUserRecord wnkBusinessRecommendUserRecord);

    /**
     *
     * 功能描述: 更新兑换状态为已兑换
     *
     * @param: record_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:30 AM
     */
    int updateStateIsFinish(Integer record_id);
}
