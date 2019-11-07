package com.springmvc.service.impl;

import com.springmvc.dao.BackstageMenusMapper;
import com.springmvc.entity.BackstageMenus;
import com.springmvc.service.IBackstageMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/backstageMenusService")
public class BackstageMenusService implements IBackstageMenusService{
    @Resource
    private BackstageMenusMapper backstageMenusMapper;

    /**
     *
     * 功能描述: 通过id查询菜单
     *
     * @param  id
     * @return 返回id查询出的对应菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 17:38
     */
    @Override
    public BackstageMenus selectById(Integer id) {
        return this.backstageMenusMapper.selectById(id);
    }

    /**
     *
     * 功能描述: 通过一级菜单id查询出所有二级菜单
     *
     * @param  oneMenuId  一级菜单id
     * @return 返回一级菜单id查询对应的所有二级菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 17:42
     */
    @Override
    public List<Map<Object, Object>> selectAllTwoMenusByOneMenuId(Integer oneMenuId) {
        return this.backstageMenusMapper.selectAllTwoMenusByOneMenuId(oneMenuId);
    }

    @Override
    public List<Map<Object, Object>> selectAllOneMenus() {
        return this.backstageMenusMapper.selectAllOneMenus();
    }
}
