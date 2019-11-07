package com.springmvc.service.impl;

import com.springmvc.dao.PropertyAccountMapper;
import com.springmvc.entity.PropertyAccount;
import com.springmvc.service.IPropertyAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("/propertyAccountService")
public class PropertyAccountService implements IPropertyAccountService{
    @Resource
    private PropertyAccountMapper propertyAccountMapper;

    @Override
    public PropertyAccount selectByResidentialsId(Integer residentials_id) {
        return this.propertyAccountMapper.selectByResidentialsId(residentials_id);
    }

    @Override
    public PropertyAccount selectByAccount(String account) {
        return this.propertyAccountMapper.selectByAccount(account);
    }

    @Override
    public int addPropertyPrimaryAccount(PropertyAccount propertyAccount) {
        return this.propertyAccountMapper.addPropertyPrimaryAccount(propertyAccount);
    }

    @Override
    public int updateNameAndMobile(PropertyAccount propertyAccount) {
        return this.propertyAccountMapper.updateNameAndMobile(propertyAccount);
    }
}
