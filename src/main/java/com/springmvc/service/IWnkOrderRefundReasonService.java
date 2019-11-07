package com.springmvc.service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/10 01:41
 * @Description:2.0版本万能卡订单退款原因选项表Service接口类
 */
public interface IWnkOrderRefundReasonService {
    /**
     *
     * 功能描述: 查询所有已启用的记录
     *
     * @param:
     * @return:List<Map<Object,Object>>
     * @author: zhangfan
     * @date: 2018/12/10 1:40 AM
     */
    List<Map<Object,Object>> selectQYRecord();
}
