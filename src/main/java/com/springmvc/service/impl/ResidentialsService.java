package com.springmvc.service.impl;

import com.springmvc.dao.ResidentialsMapper;
import com.springmvc.entity.Residentials;
import com.springmvc.service.IResidentialsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/residentialsService")
public class ResidentialsService implements IResidentialsService{
    @Resource
    private ResidentialsMapper residentialsMapper;

    @Override
    public List<Map<Object, Object>> selectResidentialsByCityId(Integer city_id) {
        return this.residentialsMapper.selectResidentialsByCityId(city_id);
    }

    @Override
    public Residentials selectById(Integer residentials_id) {
        return this.residentialsMapper.selectById(residentials_id);
    }

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.residentialsMapper.selectAllAdmin();
    }

    @Override
    public int addProperty(Residentials residentials) {
        return this.residentialsMapper.addProperty(residentials);
    }

    @Override
    public int updateResidentialsInformation(Residentials residentials) {
        return this.residentialsMapper.updateResidentialsInformation(residentials);
    }

    /**
     *
     * 功能描述: 根据条件查询物业中心信息
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/11 0011 11:48
     */
    @Override
    public List<Map<String, Object>> adminSearchPropertyByConditions(Map<String, Object> map) {
        return this.residentialsMapper.adminSearchPropertyByConditions(map);
    }
}
