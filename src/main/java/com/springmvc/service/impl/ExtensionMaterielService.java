package com.springmvc.service.impl;

import com.springmvc.dao.ExtensionMaterielMapper;
import com.springmvc.entity.ExtensionMateriel;
import com.springmvc.service.IExtensionMaterielService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 02:27
 * @Description:推广物料(用于供商家选择)服务实现类
 */
@Service("/extensionMaterielService")
public class ExtensionMaterielService implements IExtensionMaterielService {
    @Resource
    private ExtensionMaterielMapper extensionMaterielMapper;

    /**
     *
     * 功能描述: 获取所有记录
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 2:25 AM
     */
    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.extensionMaterielMapper.selectAllRecord();
    }

    @Override
    public ExtensionMateriel selectById(Integer record_id) {
        return this.extensionMaterielMapper.selectById(record_id);
    }

    @Override
    public ExtensionMateriel selectWnkInformation() {
        return this.extensionMaterielMapper.selectWnkInformation();
    }

    /**
     *
     * 功能描述:后台查询物料信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 7:03 PM
     */
    @Override
    public List<Map<String, Object>> selectAdminWnkInformation() {
        return this.extensionMaterielMapper.selectAdminWnkInformation();
    }

    /**
     *
     * 功能描述: 通过物料id修改物料信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 7:54 PM
     */
    @Override
    public int updateMaterielById(ExtensionMateriel extensionMateriel) {
        return this.extensionMaterielMapper.updateMaterielById(extensionMateriel);
    }

    @Override
    public List<Map<Object, Object>> selectMatensionByTypeId(Integer business_type_id) {
        return this.extensionMaterielMapper.selectMatensionByTypeId(business_type_id);
    }

    /**
     *
     * 功能描述: 根据条件查询物料管理信息
     *
     * @param   map 查询条件
     * @return: 返回物料管理信息
     * @author: 刘武祥
     * @date: 2019/1/18 0018 14:47
     */
    @Override
    public List<Map<Object, Object>> adminSearchExtensionMateriel(Map<String, Object> map) {
        return this.extensionMaterielMapper.adminSearchExtensionMateriel(map);
    }
}
