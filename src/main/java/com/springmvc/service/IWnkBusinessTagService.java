package com.springmvc.service;

import com.springmvc.entity.WnkBusinessTag;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 00:27
 * @Description:万能卡商户标签Service接口类
 */
public interface IWnkBusinessTagService {

    /**
     *
     * 功能描述: 查询所有一级标签
     *
     * @param
     * @return: 返回所有一级标签
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:50
     */
    List<Map<Object,Object>> selectAllOneTag();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: 返回id查询记录
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:51
     */
    WnkBusinessTag selectById(Integer record_id);

    /**
     *
     * 功能描述: 新增一级标签
     *
     * @param   wnkBusinessTag 标签实体
     * @return: 返回新增一级标签
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:51
     */
    int insertOneTag(WnkBusinessTag wnkBusinessTag);

    /**
     *
     * 功能描述: 修改一级标签
     *
     * @param   wnkBusinessTag 标签实体
     * @return: 返回修改的一级标签
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:52
     */
    int updateOneTag(WnkBusinessTag wnkBusinessTag);

    /**
     *
     * 功能描述: 查询某个一级标签下的所有二级标签
     *
     * @param   one_tag_id  一级标签
     * @return: 返回一级标签下的所有二级标签
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:52
     */
    List<Map<Object,Object>> selectTwoTagByOneTagId(Integer one_tag_id);

    /**
     *
     * 功能描述: 新增二级标签
     *
     * @param   wnkBusinessTag 标签实体类
     * @return: 返回二级标签
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:53
     */
    int insertTwoTag(WnkBusinessTag wnkBusinessTag);
    /**
     *
     * 功能描述: 查询所有二级标签
     *
     * @return 返回查询到的所有二级标签
     * @author 杨新杰
     * @date   2018/11/5 15:30
     */
    List<Map<Object,Object>> selectAllTowTag();

    /**
     *
     * 功能描述: 更新标签序号
     *
     * @param record_id 标签id
     * @param sort_index 标签序号
     * @return:
     * @author: zhangfan
     * @date: 2018/11/18 12:09 AM
     */
    int updateTagSortIndex(Integer record_id,Integer sort_index);

    /**
     *
     * 功能描述: 通过商户分类ID查询记录
     *
     * @param business_type_id 商户分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/23 1:33 PM
     */
    WnkBusinessTag selectByBusinessTypeId(Integer business_type_id);

    /**
     *
     * 功能描述: 删除商户标签 - 逻辑删除
     *
     * @param   business_type_id 标签ID
     * @return
     * @author  杨新杰
     * @date    16:54 2018/12/12
     */
    int deleteBusinessTag(Integer business_type_id);

    /**
     *
     * 功能描述: 后台查询所有商户一级标签(仅排除已删除的一级标签)
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    11:03 2019/1/2
     */
    List<Map<Object,Object>> selectAdminAllOneTag();

    /**
     *
     * 功能描述: 获取商家分类对应的商家标签
     *
     * @param   businessTypeID  商户分类ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    10:16 2019/1/7
     */
    Map<String,Object> selectBusinessTagOneByBusinessTypeId(Integer businessTypeID);

    /**
     *
     * 功能描述: 根据条件查询标签管理信息
     *
     * @param   map 查询条件
     * @return: 返回标签管理信息
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:28
     */
    List<Map<Object,Object>> adminSearchBusinessTag(Map<String,Object> map);
}
