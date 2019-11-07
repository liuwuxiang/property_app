package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/26 10:34
 * 
 */
public interface WnkBusinessAccountMapper {

    /**
     *
     * 功能描述: 通过手机号以及登录密码查询商家
     *
     * @param   mobile
     * @param   login_pwd
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:56
     */
    WnkBusinessAccount selectByMobileAndLoginPWD(String mobile,String login_pwd);
    
    /** 
     *
     * 功能描述: 通过手机号查询商家
     *
     * @param   mobile
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:56
     */
    WnkBusinessAccount selectByMobile(String mobile);
    
    /** 
     *
     * 功能描述: 通过id查询商家
     *
     * @param   business_id
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:57
     */
    WnkBusinessAccount selectById(Integer business_id);
    
    /** 
     *
     * 功能描述: 修改登录密码
     *
     * @param   business_id
     * @param   new_login_pwd
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:57
     */
    int updateLoginPwd(Integer business_id,String new_login_pwd);

    /** 
     *
     * 功能描述: 修改支付密码
     *
     * @param   business_id
     * @param   new_pay_pwd
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:57
     */
    int updatePayPwd(Integer business_id,String new_pay_pwd);

    /**
     *
     * 功能描述: 获取所有商户
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:58
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     *
     * 功能描述: 添加商户
     *
     * @param   wnkBusinessAccount
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:58
     */
    int insertBusiness(WnkBusinessAccount wnkBusinessAccount);

    /**
     *
     * 功能描述: 修改商户账号信息
     *
     * @param   wnkBusinessAccount
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:59
     */
    int updateAccountInformation(WnkBusinessAccount wnkBusinessAccount);

    /**
     *
     * 功能描述: 更新账户余额
     *
     * @param   business_id
     * @param   balance
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:59
     */
    int updateBalance(Integer business_id,Double balance);

    /**
     *
     * 功能描述: 更新账户相关余额
     *
     * @param   wnkBusinessAccount
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 11:59
     */
    int updateRelevantBalance(WnkBusinessAccount wnkBusinessAccount);

    /**
     *
     * 功能描述: 查询所有特别推荐商户信息
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/23 0023 12:00
     */
    List<Map<Object,Object>> selectAllEspciallyRecommendRecord();

    /**
     * 功能描述: 根据条件查询商户信息
     *
     * @param maps
     * @return:
     * @author: 杨新杰
     * @date: 12:25 2018/11/29
     */
    List<Map<Object,Object>> adminSearchBusinessInfoByConditions(Map<String,Object> maps);

    /**
     * 功能描述: 更新商家等级积分余额
     *
     * @param   map
     * @return:
     * @author: 杨新杰
     * @date: 11:00 2018/11/30
     */
    int updateBusinessLevelIntegral(Map<String,Object> map);

    /**
     *
     * 功能描述: 查询所有已下架的商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 9:32 PM
     */
    List<Map<Object,Object>> selectAllLowerRecord();

    /**
     * 功能描述: 查询所有商家(排除已删除的商家)
     *
     * @param map 分页查询map
     * @return 返回查询结果
     * @author 杨新杰
     * @date 10:20 2019/1/9
     */
    List<Map<String,Object>> selectBusinessAllExcludeDelete(Map<String,Object> map);

    /**
     *
     * 功能描述: 根据条件查询特别推荐商户信息
     *
     * @param   maps
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/14 0014 15:01
     */
    List<Map<Object,Object>> adminSearchEspeciallyRecommendWnkBusiness(Map<String,Object> maps);
}
