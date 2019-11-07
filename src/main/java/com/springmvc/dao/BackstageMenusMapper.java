package com.springmvc.dao;

import com.springmvc.entity.BackstageMenus;

import java.util.List;
import java.util.Map;

public interface BackstageMenusMapper {
    /**
     *
     * 功能描述: 通过id查询菜单
     *
     * @param  id
     * @return 返回查询id的对应菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 17:38
     */
    BackstageMenus selectById(Integer id);
    /**
     *
     * 功能描述: 通过一级菜单id查询出所有二级菜单
     *
     * @param  oneMenuId  一级菜单id
     * @return 返回一级菜单id查询对应的所有二级菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 17:42
     */
    List<Map<Object,Object>> selectAllTwoMenusByOneMenuId(Integer oneMenuId);

    /**
     *
     * 功能描述: 获取所有的一级菜单
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/11 4:59 PM
     */
    List<Map<Object,Object>> selectAllOneMenus();
}
