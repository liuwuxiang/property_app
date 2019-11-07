package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessLegalize;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/11/7 16:23
 */
public interface WnkBusinessLegalizeMapper {

    /**
     *
     * 功能描述: 审核通过插入未认证信息
     *
     * @param  wnkBusinessLegalize 商家认证信息实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/7 16:27
     */
    int insertLegalizeInfo(WnkBusinessLegalize wnkBusinessLegalize);

    /**
     *
     * 功能描述: 更新万能卡商家认证信息
     *
     * @param  wnkBusinessLegalize 万能卡商家认证实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/9 10:59
     */
    int updateBusinessLegalizeInfoById(WnkBusinessLegalize wnkBusinessLegalize);

    /**
     * 功能描述: 根据商家ID查询商家认证状态
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/9 10:59
     */
    Map<String,Object> selectBusinessLegalizeStatusById(@Param("business_id") Integer business_id);

    /**
     * 功能描述: 查询所有未认证信息
     *
     * @return
     * @author 杨新杰
     * @date 2018/11/9 10:59
     */
    List<Map<String,Object>> selectBusinessLegalizeAll();

    /**
     *
     * 功能描述: 获取所有已审核的商家信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 6:35 PM
     */
    List<Map<String,Object>> selectBusinessAuditedLegalizeAll();

    /**
     * 功能描述: 更新商家认证状态
     *
     * @param legalize_id 认证记录ID
     * @param business_id 商家ID
     * @param type        1=认证通过 2=认证拒绝
     * @return
     * @author 杨新杰
     * @date 2018/11/9 10:59
     */
    int updateBusinessLegalize(@Param("legalize_id") Integer legalize_id, @Param("business_id") Integer business_id, @Param("type") Integer type);

    /**
     *
     * 功能描述: 通过商家id查询记录
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/12 3:32 PM
     */
    WnkBusinessLegalize selectByBusinessId(Integer business_id);

    /**
     * 功能描述: 获取商家审核通过时间
     *
     * @param business_id
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 6:35 PM
     */
    Map<String,Object> selectStopTimeByBusinessId(@Param("business_id") Integer business_id);

    /**
     *
     * 功能描述:通过条件查询符合条件的待审核认证商家信息
     *
     * @return maps 查询条件
     * @author 杨新杰
     * @date   2018/11/10 16:11
     */
    List<Map<String,Object>> adminSearchWnkBusinessLegalizeInfoByConditions(Map<String,Object> maps);

    /**
     * 功能描述: 根据商家认证记录ID查询商家认证信息
     *
     * @param id@return:
     * @author: 杨新杰
     * @date: 15:01 2018/11/29
     */
    Map<String,Object> selectBusinessLegalizeInfoById(@Param("id") Integer id);

    /**
     *
     * 功能描述:通过条件查询符合条件的已审核认证商家信息
     *
     * @param   maps 查询条件
     * @return: 返回已审核认证商家信息
     * @author: 刘武祥
     * @date: 2019/1/4 0004 17:55
     */
    List<Map<String,Object>> adminSearchWnkBusinessAuditedLegalizeInfoByConditions(Map<String,Object> maps);
}
