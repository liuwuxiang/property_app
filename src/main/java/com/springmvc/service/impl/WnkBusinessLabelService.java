package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessLabelMapper;
import com.springmvc.entity.WnkBusinessLabel;
import com.springmvc.service.IWnkBusinessLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/11/1 09:48
 */
@Service("/wnkBusinessLabelService")
public class WnkBusinessLabelService implements IWnkBusinessLabelService {

    @Resource
    private WnkBusinessLabelMapper wnkBusinessLabelMapper;

    /**
     * 功能描述:查询商家服务内容或者特色内容
     *
     * @param type 0:服务内容 1:特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public List<Map<String, Object>> getBusinessLabel(String type) {
        return wnkBusinessLabelMapper.getBusinessLabel(type);
    }

    /**
     * 功能描述:更具ID商家服务内容或者特色内容
     *
     * @param id 服务内容/特色内容ID
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public Map<String, Object> selectLabel(String id) {
        return wnkBusinessLabelMapper.selectLabelById(id);
    }

    /**
     * 功能描述:更具ID商家服务内容或者特色内容
     *
     * @param labelType 查询类型 0 - 服务内容 1-特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public List<Map<String, Object>> selectLabelContentAll(Integer labelType) {
        return this.wnkBusinessLabelMapper.selectLabelContentAll(labelType);
    }

    /**
     * 功能描述:根据ID获取特色/服务内容
     *
     * @param content_id   服务/特色内容ID
     * @param content_type 0-服务内容 1-特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public Map<String, Object> selectContentInfoById(Integer content_id, Integer content_type) {
        return this.wnkBusinessLabelMapper.selectContentInfoById(content_id,content_type);
    }

    /**
     * 功能描述:更新特色/服务内容
     *
     * @param wnkBusinessLabel 特色/服务内容实体类
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public int updateContentLabel(WnkBusinessLabel wnkBusinessLabel) {
        return this.wnkBusinessLabelMapper.updateContentLabel(wnkBusinessLabel);
    }

    /**
     * 功能描述:新增特色/服务内容
     *
     * @param wnkBusinessLabel 特色/服务内容实体类
     * @return 返回查询到的信息, 查询失败返回null
     * @author 杨新杰
     * @date 2018/11/1 9:51
     */
    @Override
    public int insertContentLabel(WnkBusinessLabel wnkBusinessLabel) {
        return this.wnkBusinessLabelMapper.insertContentLabel(wnkBusinessLabel);
    }

    /**
     *
     * 功能描述: 根据条件查询特色标签管理
     *
     * @param   map 查询条件
     * @return: 返回特色标签管理信息
     * @author: 刘武祥
     * @date: 2019/1/21 0021 14:44
     */
    @Override
    public List<Map<Object, Object>> adminSearchContentInfoByConditions(Map<String, Object> map) {
        return this.wnkBusinessLabelMapper.adminSearchContentInfoByConditions(map);
    }

    /**
     *
     * 功能描述: 根据条件查询服务标签管理
     *
     * @param   map 查询条件
     * @return: 返回特色标签管理信息
     * @author: 刘武祥
     * @date: 2019/1/21 0021 14:44
     */
    @Override
    public List<Map<Object, Object>> adminSearchFContentInfoByConditions(Map<String, Object> map) {
        return this.wnkBusinessLabelMapper.adminSearchFContentInfoByConditions(map);
    }
}
