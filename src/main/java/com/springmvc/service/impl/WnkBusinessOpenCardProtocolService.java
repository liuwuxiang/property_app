package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessOpenCardProtocolMapper;
import com.springmvc.entity.BusinessOpenCardProtocol;
import com.springmvc.service.IWnkBusinessOpenCardProtocolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 商家会员卡开卡协议业务层
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 10:22
 */
@Service
public class WnkBusinessOpenCardProtocolService implements IWnkBusinessOpenCardProtocolService {

    @Resource
    private WnkBusinessOpenCardProtocolMapper wnkBusinessOpenCardProtocolMapper;

    /**
     * 功能描述: 后台查询所有商家会员卡开卡协议
     *
     * @return List<Map < String , Object>>
     * @author 杨新杰
     * @date 14:48 2019/1/2
     */
    @Override
    public List<Map<String, Object>> adminSelectWnkBusinessOpenCardProtocolAll() {
        return this.wnkBusinessOpenCardProtocolMapper.adminSelectWnkBusinessOpenCardProtocolAll();
    }

    /**
     * 功能描述: 新增商家会员卡开卡协议
     *
     * @param businessOpenCardProtocol
     * @return int
     * @author 杨新杰
     * @date 16:16 2019/1/2
     */
    @Override
    public int insertBusinessOpenCardProtocol(BusinessOpenCardProtocol businessOpenCardProtocol) {
        return this.wnkBusinessOpenCardProtocolMapper.insertBusinessOpenCardProtocol(businessOpenCardProtocol);
    }

    /**
     * 功能描述: 修改商家会员卡开卡协议
     *
     * @param businessOpenCardProtocol
     * @return int
     * @author 杨新杰
     * @date 16:16 2019/1/2
     */
    @Override
    public int updateBusinessOpenCardProtocol(BusinessOpenCardProtocol businessOpenCardProtocol) {
        return this.wnkBusinessOpenCardProtocolMapper.updateBusinessOpenCardProtocol(businessOpenCardProtocol);
    }

    /**
     * 功能描述:  通过ID查询商家协议内容 (包括禁用的协议内容)
     *
     * @param protocol_id
     * @author 杨新杰
     * @date 14:45 2019/1/2
     */
    @Override
    public Map<String, Object> selectBusinessOpenCardProtocolById(Integer protocol_id) {
        return this.wnkBusinessOpenCardProtocolMapper.selectBusinessOpenCardProtocolById(protocol_id);
    }

    /**
     * 功能描述:  通过商家ID查询商家协议内容
     *
     * @param business_id
     * @author 杨新杰
     * @date 14:45 2019/1/2
     */
    @Override
    public Map<String, Object> selectBusinessOpenCardProtocolByBusinessId(Integer business_id) {
        return this.wnkBusinessOpenCardProtocolMapper.selectBusinessOpenCardProtocolByBusinessId(business_id);
    }

    /**
     *
     * 功能描述: 根据条件查询会员卡办理和使用说明
     *
     * @param   map 查询条件
     * @return: 返回会员卡办理和使用说明
     * @author: 刘武祥
     * @date: 2019/1/25 0025 11:32
     */
    @Override
    public List<Map<String, Object>> adminSearchWnkBusinessOpenCardProtocol(Map<String, Object> map) {
        return this.wnkBusinessOpenCardProtocolMapper.adminSearchWnkBusinessOpenCardProtocol(map);
    }
}
