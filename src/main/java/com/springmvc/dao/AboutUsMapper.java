package com.springmvc.dao;

import com.springmvc.entity.AboutUs;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/27 14:11
 * 
 */
public interface AboutUsMapper {
    /**
     *
     * 功能描述：查询关于我们的富文本信息
     * 
     * @param   
     * @return: 返回查询关于我们的富文本信息
     * @auther: 刘武祥
     * @date：  2019/2/27 14:11
     */
    AboutUs selectAboutUs();

    /**
     *
     * 功能描述：查询通用积分以及消费积分相关说明(1-消费积分如何使用,2-消费积分如何获得,3-消费积分扣减规则,4-通用积分如何使用,5-通用积分如何获得,6-通用积分扣减规则)
     * 
     * @param   type
     * @return: 返回查询通用积分以及消费积分相关说明
     * @auther: 刘武祥
     * @date：  2019/2/27 14:12
     */
    AboutUs selectIntegralAbout(Integer type);

    /**
     *
     * 功能描述：设置积分与银币的兑换比例
     * 
     * @param   content
     * @return: 返回设置积分与银币的兑换比例信息
     * @auther: 刘武祥
     * @date：  2019/2/27 14:13
     */
    int setIntegralAndCoin(String content);
    //设置积分与人民币的兑换比例
    /**
     *
     * 功能描述：设置积分与人民币的兑换比例
     * 
     * @param   content
     * @return: 返回设置积分与人民币的兑换比例
     * @auther: 刘武祥
     * @date：  2019/2/27 14:14
     */
    int setIntegralAndRMB(String content);
    /**
     *
     * 功能描述: 新增记录
     *
     * @param:  aboutUs
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:34 AM
     */
    int addRecord(AboutUs aboutUs);

    /**
     *
     * 功能描述: 通过type更新记录信息
     *
     * @param:  aboutUs
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:37 AM
     */
    int updateRecordInformation(AboutUs aboutUs);

    /**
     * 根据类型查询 法律声明、价格声明、隐私政策、餐饮安全管理
     *
     * @param type
     * @return
     */
    Map<String,Object> selectAboutByType(String type);

    /**
     *
     * 功能描述: 插入法律声明/价格声明/隐私政策/餐饮安全管理内容
     *
     * @param   type
     * @param   about_content
     * @return
     * @author  杨新杰
     * @date    16:56 2018/12/11
     */
    int insertAboutInfo(@Param("about_type") String type, @Param("about_content") String about_content);

    /**
     *
     * 功能描述: 更新法律声明/价格声明/隐私政策/餐饮安全管理内容
     *
     * @param   type
     * @param   about_content
     * @return
     * @author  杨新杰
     * @date    16:56 2018/12/11
     */
    int updateAboutInfo(@Param("about_type") String type, @Param("about_content") String about_content);
}
