package com.springmvc.service.impl;

import com.springmvc.dao.WnkAppUpdateVersionMapper;
import com.springmvc.entity.WnkAppUpdateVersion;
import com.springmvc.service.IWnkAppUpdateVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/11/20 21:39
 * @Description:万能卡app版本更新记录服务实现类
 */
@Service("/wnkAppUpdateVersionService")
public class WnkAppUpdateVersionService implements IWnkAppUpdateVersionService {
    @Resource
    private WnkAppUpdateVersionMapper wnkAppUpdateVersionMapper;

    @Override
    public WnkAppUpdateVersion selectNewVersionByType(Integer type) {
        return this.wnkAppUpdateVersionMapper.selectNewVersionByType(type);
    }
}
