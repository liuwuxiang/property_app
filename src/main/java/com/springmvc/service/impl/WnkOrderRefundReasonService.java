package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrderRefundReasonMapper;
import com.springmvc.service.IWnkOrderRefundReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/10 01:41
 * @Description:2.0版本万能卡订单退款原因选项表Service实现类
 */
@Service("/wnkOrderRefundReasonService")
public class WnkOrderRefundReasonService implements IWnkOrderRefundReasonService {
    @Resource
    private WnkOrderRefundReasonMapper wnkOrderRefundReasonMapper;

    /**
     *
     * 功能描述: 查询所有已启用的记录
     *
     * @param:
     * @return:List<Map<Object,Object>>
     * @author: zhangfan
     * @date: 2018/12/10 1:40 AM
     */
    @Override
    public List<Map<Object, Object>> selectQYRecord() {
        return this.wnkOrderRefundReasonMapper.selectQYRecord();
    }
}
