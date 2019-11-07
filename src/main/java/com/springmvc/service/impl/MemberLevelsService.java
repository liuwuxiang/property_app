package com.springmvc.service.impl;

import com.springmvc.dao.MemberLevelsMapper;
import com.springmvc.entity.MemberLevels;
import com.springmvc.service.IMemberLevelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员等级管理
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 11:30
 */
@Service("/memberLevelsService")
public class MemberLevelsService implements IMemberLevelsService{
    @Resource
    private MemberLevelsMapper memberLevelsMapper;

    @Override
    public MemberLevels selectById(Integer id) {
        return this.memberLevelsMapper.selectById(id);
    }

    @Override
    public MemberLevels selectDefaultLevel() {
        return this.memberLevelsMapper.selectDefaultLevel();
    }

    @Override
    public List<Map<Object, Object>> selectUserCanChooseLevel(Integer recharge_consumption_integral) {
        return this.memberLevelsMapper.selectUserCanChooseLevel(recharge_consumption_integral);
    }

    @Override
    public int selectMaxValueId() {
        return this.memberLevelsMapper.selectMaxValueId();
    }

    @Override
    public List<Map<Object, Object>> selectLevelBySJTJ(Integer recharge_consumption_integral) {
        return this.memberLevelsMapper.selectLevelBySJTJ(recharge_consumption_integral);
    }

    @Override
    public List<Map<Object, Object>> selectAllLevelAdmin() {
        return this.memberLevelsMapper.selectAllLevelAdmin();
    }

    @Override
    public int updateMemberLevelById(MemberLevels memberLevels) {
        return this.memberLevelsMapper.updateMemberLevelById(memberLevels);
    }

    @Override
    public MemberLevels selectByMembrLevelName(String memberLevelName) {
        return this.memberLevelsMapper.selectByMembrLevelName(memberLevelName);
    }

    /**
     *
     * 功能描述: 根据条件查询搜索会员等级
     *
     * @param   map 条件
     * @return: 返回查询到的会员等级
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:50
     */
    @Override
    public List<Map<String, Object>> adminSearchMemberLevelByConditions(Map<String, Object> map) {
        return this.memberLevelsMapper.adminSearchMemberLevelByConditions(map);
    }
}
