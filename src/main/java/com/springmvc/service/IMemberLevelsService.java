package com.springmvc.service;

import com.springmvc.entity.MemberLevels;

import java.util.List;
import java.util.Map;

/**
 * 会员等级操作Service
 */
public interface IMemberLevelsService {
    /**
     * 通过id查询会员等级
     * @param id
     * @return
     */
    MemberLevels selectById(Integer id);

    /**
     * 查询默认等级
     * @return
     */
    MemberLevels selectDefaultLevel();

    /**
     * 查询用户可选择升级的所有会员等级
     * @param recharge_consumption_integral
     * @return
     */
    List<Map<Object,Object>> selectUserCanChooseLevel(Integer recharge_consumption_integral);

    /**
     * 查询升级所需最大值的记录id
     * @return
     */
    int selectMaxValueId();

    /**
     * 通过升级条件查询记录
     * @param recharge_consumption_integral
     * @return
     */
    List<Map<Object,Object>> selectLevelBySJTJ(Integer recharge_consumption_integral);

    /**
     * 后台查询所有会员等级
     * @return
     */
    List<Map<Object,Object>> selectAllLevelAdmin();

    /**
     * 更新会员等级信息
     * @param memberLevels
     * @return
     */
    int updateMemberLevelById(MemberLevels memberLevels);

    /**
     *
     * 功能描述: 通过会员等级名称查询会员等级
     *
     * @param   memberLevelName
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:48
     */
    MemberLevels selectByMembrLevelName(String memberLevelName);

    /** 
     *
     * 功能描述: 根据条件查询搜索会员等级
     *
     * @param   map 条件
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:47
     */
    List<Map<String,Object>> adminSearchMemberLevelByConditions(Map<String,Object> map);
}
