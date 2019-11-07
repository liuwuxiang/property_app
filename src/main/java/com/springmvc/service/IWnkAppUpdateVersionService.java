package com.springmvc.service;

import com.springmvc.entity.WnkAppUpdateVersion;

/**
 * @author: zhangfan
 * @Date: 2018/11/20 21:38
 * @Description:万能卡app版本更新记录服务接口类
 */
public interface IWnkAppUpdateVersionService {
    /**
     *
     * 功能描述: 查询某个端口的新版本
     *
     * @param type 端口(0-万能卡用户端,1-万能卡商家端)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/20 9:37 PM
     */
    WnkAppUpdateVersion selectNewVersionByType(Integer type);
}
