package com.springmvc.service.impl;

import com.springmvc.dao.WnkMaintainMapper;
import com.springmvc.entity.WnkMaintain;
import com.springmvc.service.IWnkMaintainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 系统维护员管理实现层
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 15:24
 */
@Service("/wnkMaintainService")
public class WnkMaintainService implements IWnkMaintainService {

    @Resource
    private WnkMaintainMapper wnkMaintainMapper;

    /**
     *
     * 功能描述: 根据商家类别ID查询对应分类维护员信息.(已启用的维护员信息)
     *
     * @param  business_type_id 商家类别ID
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    @Override
    public WnkMaintain selectMaintainInfoByBusinessTypeId(Integer business_type_id) {
        return this.wnkMaintainMapper.selectMaintainInfoByBusinessTypeId(business_type_id);
    }

    /**
     * 功能描述: 查询所有维护员信息
     *
     * @return
     * @author 杨新杰
     * @date 2018/11/14 14:15
     */
    @Override
    public List<Map<String, Object>> selectMaintainInfoAll() {
        return this.wnkMaintainMapper.selectMaintainInfoAll();
    }

    /**
     * 功能描述: 查询所有维护员信息
     *
     * @param maintain_id 维护员ID
     * @return
     * @author 杨新杰
     * @date 2018/11/14 14:15
     */
    @Override
    public Map<String, Object> selectMaintainInfoById(String maintain_id) {
        return this.wnkMaintainMapper.selectMaintainInfoById(maintain_id);
    }

    /**
     * 功能描述: 新增维护员
     *
     * @param wnkMaintain 维护员实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/14 14:15
     */
    @Override
    public int insertMaintain(WnkMaintain wnkMaintain) {
        return this.wnkMaintainMapper.insertMaintain(wnkMaintain);
    }

    /**
     * 功能描述: 更新维护员
     *
     * @param wnkMaintain 维护员实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/14 14:15
     */
    @Override
    public int updateMaintain(WnkMaintain wnkMaintain) {
        return this.wnkMaintainMapper.updateMaintain(wnkMaintain);
    }

    /**
     * 功能描述: 查询此分类ID是否有维护员
     *
     * @param business_type_id 商家分类ID
     * @return
     * @author 杨新杰
     * @date 2018/11/14 14:15
     */
    @Override
    public WnkMaintain selectMaintainInfoByBusinessTypeIdAll(Integer business_type_id) {
        return this.wnkMaintainMapper.selectMaintainInfoByBusinessTypeIdAll(business_type_id);
    }

    /**
     *
     * 功能描述: 根据条件查询系统维护员管理
     *
     * @param   map 查询条件
     * @return: 返回系统维护员管理
     * @author: 刘武祥
     * @date: 2019/1/18 0018 16:55
     */
    @Override
    public List<Map<Object, Object>> adminSearchMaintain(Map<String, Object> map) {
        return this.wnkMaintainMapper.adminSearchMaintain(map);
    }
}
