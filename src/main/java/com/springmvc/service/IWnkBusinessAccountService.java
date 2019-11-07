package com.springmvc.service;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessRegister;

import java.util.List;
import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/26 17:25
 * 
 */
public interface IWnkBusinessAccountService {
    /**
     * 通过手机号以及登录密码查询商家
     * @param mobile
     * @param login_pwd
     * @return
     */
    WnkBusinessAccount selectByMobileAndLoginPWD(String mobile, String login_pwd);

    /**
     * 通过手机号查询商家
     * @param mobile
     * @return
     */
    WnkBusinessAccount selectByMobile(String mobile);

    /**
     * 通过id查询商家
     * @param business_id
     * @return
     */
    WnkBusinessAccount selectById(Integer business_id);

    /**
     * 修改登录密码
     * @param business_id
     * @param new_login_pwd
     * @return
     */
    int updateLoginPwd(Integer business_id,String new_login_pwd);

    /**
     * 修改支付密码
     * @param business_id
     * @param new_pay_pwd
     * @return
     */
    int updatePayPwd(Integer business_id,String new_pay_pwd);

    /**
     * 获取所有商户
     * @return
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     * 添加商户
     * @param wnkBusinessAccount
     * @return
     */
    int insertBusiness(WnkBusinessAccount wnkBusinessAccount);

    /**
     * 修改商户账号信息
     * @param wnkBusinessAccount
     * @return
     */
    int updateAccountInformation(WnkBusinessAccount wnkBusinessAccount);

    /**
     * 更新账户余额
     * @param business_id
     * @param balance
     * @return
     */
    int updateBalance(Integer business_id,Double balance);

    /**
     *
     * 功能描述: 更新账户相关余额
     *
     * @param: wnkBusinessAccount
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/28 10:51 PM
     */
    int updateRelevantBalance(WnkBusinessAccount wnkBusinessAccount);

    /**
     *
     * 功能描述: 查询所有特别推荐商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 9:40 PM
     */
    List<Map<Object,Object>> selectAllEspciallyRecommendRecord();

    /**
     *
     * 功能描述: 商户注册完成自动添加注册信息
     *
     * @param wnkBusinessRegister 注册信息
     * @param login_pwd 默认登录密码
     * @return int = 0 ：商户已存在;
     * @return int = -1 ：创建账号失败;
     * @return int = -2 ：注册成功，但添加商户信息失败;
     * @return int = 1 ：注册成功
     * @author: zhangfan
     * @date: 2018/11/21 5:08 PM
     */
    int addWnkBusinessZDRegister(WnkBusinessRegister wnkBusinessRegister,String login_pwd);

    /**
     *
     * 功能描述: 根据条件查询商户信息
     *
     * @param maps 查询条件
     * @return:
     * @author: 杨新杰
     * @date: 12:25 2018/11/29
     */
    List<Map<Object,Object>> adminSearchBusinessInfoByConditions(Map<String,Object> maps);

    /**
     *
     * 功能描述: 更新商家等级积分余额
     *
     * @param i
     * @param business_id
     * @return:
     * @author: 杨新杰
     * @date: 11:00 2018/11/30
     */
    int updateBusinessLevelIntegral(Double i,Integer business_id);

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
     *
     * 功能描述: 查询所有商家(排除已删除的商家)
     *
     * @param limit 查询多少条
     * @param total  从第几条开始
     * @param store_name
     * @return  返回查询结果
     * @author  杨新杰
     * @date    10:20 2019/1/9
     */
    List<Map<String,Object>> selectBusinessAllExcludeDelete(Integer total,Integer limit,String store_name);

    /**
     *
     * 功能描述: 根据条件查询特别推荐商户信息
     *
     * @param   maps 查询条件
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/14 0014 14:59
     */
    List<Map<Object,Object>> adminSearchEspeciallyRecommendWnkBusiness(Map<String,Object> maps);
}
