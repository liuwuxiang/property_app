package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessRegionMapper;
import com.springmvc.entity.WnkBusinessRegion;
import com.springmvc.service.IWnkBusinessRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 商户管理
 *
 * @author 杨新杰
 * @date 2018/12/30 15:44
 */
@Service("/wnkBusinessRegionService")
public class WnkBusinessRegionService implements IWnkBusinessRegionService {

    @Resource
    private WnkBusinessRegionMapper wnkBusinessRegionMapper;

    /**
     * 功能描述: 获取所有区域
     *
     * @return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    @Override
    public List<Map<String, Object>> selectWnkBusinessRegionAll() {
        return this.wnkBusinessRegionMapper.selectWnkBusinessRegionAll();
    }

    /**
     * 功能描述: 新增区域
     *
     * @param wnkBusinessRegion@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    @Override
    public int insertWnkBusinessRegion(WnkBusinessRegion wnkBusinessRegion) {
        return this.wnkBusinessRegionMapper.insertWnkBusinessRegion(wnkBusinessRegion);
    }

    /**
     * 功能描述: 更新区域
     *
     * @param wnkBusinessRegion@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    @Override
    public int updateWnkBusinessRegion(WnkBusinessRegion wnkBusinessRegion) {
        return this.wnkBusinessRegionMapper.updateWnkBusinessRegion(wnkBusinessRegion);
    }

    /**
     * 功能描述: 删除区域
     *
     * @param id@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    @Override
    public int deleteWnkBusinessRegion(Integer id) {
        return this.wnkBusinessRegionMapper.deleteWnkBusinessRegion(id);
    }

    /**
     *
     * 功能描述: 根据条件查询商户区域管理
     *
     * @param   map  查询条件
     * @return: 返回查询到的商户区域管理信息
     * @author: 刘武祥
     * @date: 2019/1/21 0021 11:07
     */
    @Override
    public List<Map<Object, Object>> adminSearchWnkBusinessRegionInfoByConditions(Map<String, Object> map) {
        return this.wnkBusinessRegionMapper.adminSearchWnkBusinessRegionInfoByConditions(map);
    }
}
