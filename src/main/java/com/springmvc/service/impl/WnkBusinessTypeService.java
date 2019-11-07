package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTypeMapper;
import com.springmvc.entity.WnkBusinessType;
import com.springmvc.service.IWnkBusinessTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 万能卡分类后台管理实现层
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:47
 */
@Service("/wnkBusinessTypeService")
public class WnkBusinessTypeService implements IWnkBusinessTypeService{
    @Resource
    private WnkBusinessTypeMapper wnkBusinessTypeMapper;

    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.wnkBusinessTypeMapper.selectAllRecord();
    }

    @Override
    public WnkBusinessType selectById(Integer record_id) {
        return this.wnkBusinessTypeMapper.selectById(record_id);
    }

    /**
     *
     * 功能描述: 后台查询所有记录
     *
     * @param:
     * @return: List<Map<Object,Object>>
     * @author: zhangfan
     * @date: 2018/11/14 3:33 AM
     */
    @Override
    public List<Map<Object, Object>> adminSelectAllRecord() {
        return this.wnkBusinessTypeMapper.adminSelectAllRecord();
    }

    @Override
    public int addWnkBusinessInformationAction(WnkBusinessType wnkBusinessType) {
        return this.wnkBusinessTypeMapper.addWnkBusinessInformationAction(wnkBusinessType);
    }

    /**
     *
     * 功能描述: 更新万能卡商家分类信息
     *
     * @param wnkBusinessType 更新信息
     * @return:int
     * @author: zhangfan
     * @date: 2018/11/14 4:07 AM
     */
    @Override
    public int updateWnkTypeInformation(WnkBusinessType wnkBusinessType) {
        return this.wnkBusinessTypeMapper.updateWnkTypeInformation(wnkBusinessType);
    }

    /**
     *
     * 功能描述: 标记删除
     *
     * @param   id
     * @return: int
     * @author: 刘武祥
     * @date: 2018/12/30 0030 15:58
     */
    @Override
    public int deleteWnkBusinessTypeById(String id) {
        return this.wnkBusinessTypeMapper.deleteWnkBusinessTypeById(id);
    }

    /**
     *
     * 功能描述: 根据条件查询商家分类管理
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/18 0018 12:19
     */
    @Override
    public List<Map<Object, Object>> adminSearchWnkBusinessType(Map<String, Object> map) {
        return this.wnkBusinessTypeMapper.adminSearchWnkBusinessType(map);
    }
}
