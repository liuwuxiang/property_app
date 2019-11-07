package com.springmvc.service.impl;

import com.springmvc.dao.IntegralHelpMapper;
import com.springmvc.entity.IntegralHelp;
import com.springmvc.service.IIntegralHelpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/11/14 01:39
 * @Description:积分帮助说明服务实现类(包含用户端平台积分、用户端商家积分、用户端赠送积分、商家端等级积分、商家端推广积分说明数据)
 */
@Service("/integralHelpService")
public class IntegralHelpService implements IIntegralHelpService {
    @Resource
    private IntegralHelpMapper integralHelpMapper;

    /**
     *
     * 功能描述: 查询某个类别的说明信息
     *
     * @param type 说明类别(0-用户端平台积分、1-用户端商家积分、2-用户端赠送积分、3-商家端等级积分、4-商家端推广积分说明数据)
     * @return: IntegralHelp
     * @author: zhangfan
     * @date: 2018/11/14 1:37 AM
     */
    @Override
    public IntegralHelp selectContentByType(Integer type) {
        return this.integralHelpMapper.selectContentByType(type);
    }

    /**
     *
     * 功能描述: 更新某个类型的信息
     *
     * @param integralHelp 类型信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/14 1:38 AM
     */
    @Override
    public int updateTypeContent(IntegralHelp integralHelp) {
        return this.integralHelpMapper.updateTypeContent(integralHelp);
    }

    /**
     *
     * 功能描述: 新增某个类型的说明信息
     *
     * @param integralHelp 类型信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/14 1:38 AM
     */
    @Override
    public int insertTypeContent(IntegralHelp integralHelp) {
        return this.integralHelpMapper.insertTypeContent(integralHelp);
    }
}
