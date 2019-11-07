package com.springmvc.dao;

import com.springmvc.entity.MemberLevels;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员等级管理
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 11:30
 */
public interface MemberLevelsMapper {

    /**
     *
     * 功能描述: 通过id查询会员等级
     *
     * @param   id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:46
     */
    MemberLevels selectById(Integer id);

    /**
     *
     * 功能描述:查询默认等级
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:46
     */
    MemberLevels selectDefaultLevel();

    /**
     *
     * 功能描述: 查询用户可选择升级的所有会员等级
     *
     * @param   recharge_consumption_integral
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:45
     */
    List<Map<Object,Object>> selectUserCanChooseLevel(Integer recharge_consumption_integral);

    /**
     *
     * 功能描述: 查询升级所需最大值的记录id
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:45
     */
    int selectMaxValueId();

    /**
     *
     * 功能描述: 通过升级条件查询记录
     *
     * @param   recharge_consumption_integral
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:45
     */
    List<Map<Object,Object>> selectLevelBySJTJ(Integer recharge_consumption_integral);

    /**
     *
     * 功能描述: 后台查询所有会员等级
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:44
     */
    List<Map<Object,Object>> selectAllLevelAdmin();

    /**
     *
     * 功能描述: 更新会员等级信息
     *
     * @param   memberLevels
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:44
     */
    int updateMemberLevelById(MemberLevels memberLevels);

    /**
     *
     * 功能描述: 通过会员等级名称查询会员等级
     *
     * @param   memberLevelName
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:44
     */
    MemberLevels selectByMembrLevelName(String memberLevelName);

    /**
     *
     * 功能描述: 根据条件查询搜索会员等级
     *
     * @param   map 条件
     * @return: 返回查询到的会员等级
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:51
     */
    List<Map<String,Object>> adminSearchMemberLevelByConditions(Map<String,Object> map);
}
