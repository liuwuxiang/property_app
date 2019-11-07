package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTypeQuickInputMapper;
import com.springmvc.entity.WnkBusinessTypeQuickInput;
import com.springmvc.service.IWnkBusinessTypeQuickInputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2019/1/7 14:34
 */
@Service
public class WnkBusinessTypeQuickInputServiceImpl implements IWnkBusinessTypeQuickInputService {

    @Resource
    private WnkBusinessTypeQuickInputMapper wnkBusinessTypeQuickInputMapper;

    /**
     *
     * 功能描述: 根据分类ID查询对应分类下所有的快捷输入字段
     *
     * @param   businessTypeId 商家ID
     * @return  返回查询到的结果
     * @author  杨新杰
     * @date    14:42 2019/1/7
     */
    @Override
    public List<Map<String, Object>> selectBusinessTypeQuickInputByBusinessTypeId(Integer businessTypeId) {
        return this.wnkBusinessTypeQuickInputMapper.selectBusinessTypeQuickInputByBusinessTypeId(businessTypeId);
    }

    /**
     * 功能描述: 后台查询所有快捷输入字段
     *
     * @author 杨新杰
     * @date 15:01 2019/1/7
     */
    @Override
    public List<Map<String, Object>> selectAdminBusinessTypeQuickInputInfoAll() {
        return this.wnkBusinessTypeQuickInputMapper.selectAdminBusinessTypeQuickInputInfoAll();
    }

    /**
     * 功能描述: 通过ID查询所有快速输入信息
     *
     * @param quickId 快速输入ID
     * @return 查询结果
     * @author 杨新杰
     * @date 15:32 2019/1/7
     */
    @Override
    public Map<String, Object> selectBusinessTypeQuickInputInfoById(Integer quickId) {
        return this.wnkBusinessTypeQuickInputMapper.selectBusinessTypeQuickInputInfoById(quickId);
    }

    /**
     * 功能描述: 新增快速输入
     *
     * @param wnkBusinessTypeQuickInput 快速输入实体类
     * @return 返回结果
     * @author 杨新杰
     * @date 15:48 2019/1/7
     */
    @Override
    public int insertBusinessTypeQuickInput(WnkBusinessTypeQuickInput wnkBusinessTypeQuickInput) {
        return this.wnkBusinessTypeQuickInputMapper.insertBusinessTypeQuickInput(wnkBusinessTypeQuickInput);
    }

    /**
     * 功能描述: 更新快速输入
     *
     * @param wnkBusinessTypeQuickInput 快速输入实体类
     * @return 返回结果
     * @author 杨新杰
     * @date 15:48 2019/1/7
     */
    @Override
    public int updateBusinessTypeQuickInput(WnkBusinessTypeQuickInput wnkBusinessTypeQuickInput) {
        return this.wnkBusinessTypeQuickInputMapper.updateBusinessTypeQuickInput(wnkBusinessTypeQuickInput);
    }

    /**
     *
     * 功能描述: 根据条件查询所有快速输入信息
     *
     * @param   map
     * @return: 返回所有快速输入信息
     * @author: 刘武祥
     * @date: 2019/1/25 0025 15:01
     */
    @Override
    public List<Map<String, Object>> adminSearchBusinessTypeQuickInput(Map<String, Object> map) {
        return this.wnkBusinessTypeQuickInputMapper.adminSearchBusinessTypeQuickInput(map);
    }
}
