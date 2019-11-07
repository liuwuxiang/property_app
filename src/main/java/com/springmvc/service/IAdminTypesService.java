package com.springmvc.service;

import com.springmvc.entity.AdminTypes;

import java.util.List;
import java.util.Map;

/**
 * 管理员类别操作Service
 */
public interface IAdminTypesService {
    /**
     *
     * 功能描述: 通过id查询类别
     *
     * @param  id 类别ID
     * @return 返回id查询对应的类别
     * @author 刘武祥
     * @date   2018/10/22 0022 17:08
     */
    AdminTypes selectById(Integer id);

    /**
     *
     * 功能描述: 查询所有管理员类别
     *
     * @param  Map 集合
     * @return 返回所有管理员类别
     * @author 刘武祥
     * @date   2018/10/22 0022 17:12
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 插入新记录
     *
     * @param adminTypes 记录信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/11 5:27 PM
     */
    int addRecord(AdminTypes adminTypes);

    /**
     *
     * 功能描述: 更新管理员类别信息
     *
     * @param:  adminTypes
     * @return:
     * @author: zhangfan
     * @date: 2018/11/11 5:56 PM
     */
    int updateAdminsTypeInformation(AdminTypes adminTypes);

    /**
     *
     * 功能描述: 根据条件查询管理员类别
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/12 0012 11:50
     */
    List<Map<String,Object>> adminSearchAdminsTypeByConditions(Map<String,Object> map);
}
