package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessRegisterMapper;
import com.springmvc.entity.WnkBusinessRegister;
import com.springmvc.service.IWnkBusinessRegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkBusinessRegisterService")
public class WnkBusinessRegisterService implements IWnkBusinessRegisterService{
    @Resource
    private WnkBusinessRegisterMapper wnkBusinessRegisterMapper;

    @Override
    public int insertBusinessRegister(WnkBusinessRegister wnkBusinessRegister) {
        return this.wnkBusinessRegisterMapper.insertBusinessRegister(wnkBusinessRegister);
    }

    @Override
    public List<Map<Object, Object>> selectByMobile(String login_account) {
        return this.wnkBusinessRegisterMapper.selectByMobile(login_account);
    }

    @Override
    public List<Map<Object, Object>> getAllNoAuditedBusiness() {
        return this.wnkBusinessRegisterMapper.getAllNoAuditedBusiness();
    }

    @Override
    public WnkBusinessRegister selectById(Integer record_id) {
        return this.wnkBusinessRegisterMapper.selectById(record_id);
    }

    @Override
    public int updateInformation(WnkBusinessRegister wnkBusinessRegister) {
        return this.wnkBusinessRegisterMapper.updateInformation(wnkBusinessRegister);
    }

    /**
     *
     * 功能描述: 根据条件查询待审核商家信息
     *
     * @param   maps 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/12 0012 16:44
     */
    @Override
    public List<Map<Object, Object>> adminSearchNoAuditedWnkBusiness(Map<String, Object> maps) {
        return this.wnkBusinessRegisterMapper.adminSearchNoAuditedWnkBusiness(maps);
    }
}
