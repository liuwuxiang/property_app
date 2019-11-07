package com.springmvc.dao;

import com.springmvc.entity.BusinessOpenCardProtocol;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 商家会员卡开卡协议Mapper
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 10:24
 */
public interface WnkBusinessOpenCardProtocolMapper {

    /**
     * 功能描述: 后台查询所有商家会员卡开卡协议 (除去已删除)
     *
     * @return List<Map < String , Object>>
     * @author 杨新杰
     * @date 14:48 2019/1/2
     */
    List<Map<String,Object>> adminSelectWnkBusinessOpenCardProtocolAll();

    /**
     * 功能描述: 新增商家会员卡开卡协议
     *
     * @param businessOpenCardProtocol
     * @return int
     * @author 杨新杰
     * @date 16:16 2019/1/2
     */
    int insertBusinessOpenCardProtocol(BusinessOpenCardProtocol businessOpenCardProtocol);

    /**
     * 功能描述: 修改商家会员卡开卡协议
     *
     * @param businessOpenCardProtocol
     * @return int
     * @author 杨新杰
     * @date 16:16 2019/1/2
     */
    int updateBusinessOpenCardProtocol(BusinessOpenCardProtocol businessOpenCardProtocol);

    /**
     * 功能描述:  通过ID查询商家协议内容(包括禁用的协议内容)
     *
     * @param protocol_id
     * @return 商家协议内容(包括禁用的协议内容)
     * @author 杨新杰
     * @date 14:45 2019/1/2
     */
    Map<String,Object> selectBusinessOpenCardProtocolById(@Param("protocol_id") Integer protocol_id);

    /**
     * 功能描述:  通过商家ID查询商家协议内容
     *
     * @param business_id
     * @return 商家协议内容
     * @author 杨新杰
     * @date 14:45 2019/1/2
     */
    Map<String,Object> selectBusinessOpenCardProtocolByBusinessId(@Param("business_id") Integer business_id);

    /**
     *
     * 功能描述: 根据条件查询会员卡办理和使用说明
     *
     * @param   map 查询条件
     * @return: 返回会员卡办理和使用说明
     * @author: 刘武祥
     * @date: 2019/1/25 0025 11:32
     */
    List<Map<String,Object>> adminSearchWnkBusinessOpenCardProtocol(Map<String,Object> map);
}
