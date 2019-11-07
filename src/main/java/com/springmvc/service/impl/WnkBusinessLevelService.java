package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessLevelMapper;
import com.springmvc.entity.WnkBusinessLevel;
import com.springmvc.service.IWnkBusinessLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 19:39
 * @Description:万能卡商家服务类
 */
@Service("/wnkBusinessLevelService")
public class WnkBusinessLevelService implements IWnkBusinessLevelService{
    @Resource
    private WnkBusinessLevelMapper wnkBusinessLevelMapper;

    @Override
    public WnkBusinessLevel selectById(Integer record_id) {
        return this.wnkBusinessLevelMapper.selectById(record_id);
    }

    @Override
    public WnkBusinessLevel selectDefaultLevel() {
        return this.wnkBusinessLevelMapper.selectDefaultLevel();
    }

    @Override
    public List<Map<Object, Object>> selectNoDefaultLevel() {
        return this.wnkBusinessLevelMapper.selectNoDefaultLevel();
    }

    /**
     * 功能描述: 获取所有商家等级
     *
     * @return:
     * @author: 杨新杰
     * @date: 16:50 2018/11/30
     */
    @Override
    public List<Map<String, Object>> selectAll() {
        return this.wnkBusinessLevelMapper.selectAll();
    }

}
