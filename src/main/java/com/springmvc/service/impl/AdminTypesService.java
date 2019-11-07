package com.springmvc.service.impl;

import com.springmvc.dao.AdminTypesMapper;
import com.springmvc.entity.AdminTypes;
import com.springmvc.service.IAdminTypesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/25 16:29
 * 
 */
@Service("/adminTypesService")
public class AdminTypesService implements IAdminTypesService{
    @Resource
    private AdminTypesMapper adminTypesMapper;

    /**
     *
     * 功能描述: 通过id查询类别
     *
     * @param  id 类别ID
     * @return 返回id查询对应的类别
     * @author 刘武祥
     * @date   2018/10/22 0022 17:08
     */
    @Override
    public AdminTypes selectById(Integer id) {
        return this.adminTypesMapper.selectById(id);
    }

    /**
     *
     * 功能描述: 查询所有管理员类别
     *
     * @param  Map 集合
     * @return 返回所有管理员类别
     * @author 刘武祥
     * @date   2018/10/22 0022 17:12
     */
    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.adminTypesMapper.selectAllAdmin();
    }

    @Override
    public int addRecord(AdminTypes adminTypes) {
        return this.adminTypesMapper.addRecord(adminTypes);
    }

    @Override
    public int updateAdminsTypeInformation(AdminTypes adminTypes) {
        return this.adminTypesMapper.updateAdminsTypeInformation(adminTypes);
    }

    /**
     *
     * 功能描述: 根据条件查询管理员类别
     *
     * @param   map
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/12 0012 11:53
     */
    @Override
    public List<Map<String, Object>> adminSearchAdminsTypeByConditions(Map<String, Object> map) {
        return this.adminTypesMapper.adminSearchAdminsTypeByConditions(map);
    }
}
