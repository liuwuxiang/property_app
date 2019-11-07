package com.springmvc.dao;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/10 01:37
 * @Description:2.0版本万能卡订单退款原因选项表Mapper
 */
public interface WnkOrderRefundReasonMapper {
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
