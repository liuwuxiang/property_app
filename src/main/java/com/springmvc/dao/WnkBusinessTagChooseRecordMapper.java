package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessTagChooseRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 03:06
 * @Description:用于记录万能卡商家选择的标签Mapper
 */
public interface WnkBusinessTagChooseRecordMapper {
    //查询某个级别下的所有商家
    List<Map<Object,Object>> selectByTagId(Integer tag_id,Double lat,Double longt,@Param(value="sort_type") Integer sort_type);

    /**
     *
     * 功能描述: 根据商家ID 查询他已经选择的标签信息
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/5 15:49
     */
    List<Map<Object,Object>> selectTowByBusinessId(@Param("business_id") Integer business_id);

    /**
     * 功能描述:根据商家ID 和二级标签ID删除对应记录
     *
     * @param business_id 商家ID
     * @param twoTagId    二级标签ID
     * @return
     */
    int deleteBusinessTag(@Param("business_id") Integer business_id,@Param("twoTagId") Integer twoTagId);

    /**
     * 插入记录
     * @param business_id 商家ID
     * @param one_tag_id  一级标签ID
     * @param two_tag_id  二级标签ID
     * @return
     */
    int insertBusinessTag(@Param("business_id") Integer business_id,@Param("one_tag_id") Integer one_tag_id,@Param("two_tag_id") Integer two_tag_id);

    /**
     * 查询对应二级标签的记录是否存在
     * @param towTag
     * @return
     */
    WnkBusinessTagChooseRecord selectByTwoTagId(@Param("business_id") Integer business_id,@Param("towTag") Integer towTag);

    /**
     *
     * 功能描述:获取某个标签下的推荐商家
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/6 8:07 PM
     */
    List<Map<Object,Object>> selectRecommendByTagOneId(Integer tag_id,Double lat,Double longt);

    /**
     *
     * 功能描述:更新商家标签
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/6 8:07 PM
     */
    int updateBusinessTagById(@Param("last_id") Integer last_id,@Param("towTag") Integer towTag, @Param("business_id") Integer business_id);

    /**
     *
     * 功能描述:根据商家id
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/18 2:08 PM
     */
    List<Map<Object,Object>> selectByBusinessId(Integer business_id);

    /**
     * 功能描述: 删除商户标签
     *
     * @param business_id
     * @param id
     * @return
     * @author 杨新杰
     * @date 13:20 2018/12/30
     */
    int deleteBusinessOneTag(@Param("business_id") Integer business_id);
}
