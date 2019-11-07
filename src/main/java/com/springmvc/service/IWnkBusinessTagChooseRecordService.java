package com.springmvc.service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 03:07
 * @Description:用于记录万能卡商家选择的标签Service接口类
 */
public interface IWnkBusinessTagChooseRecordService {
    //查询某个级别下的所有商家
    List<Map<Object,Object>> selectByTagId(Integer tag_id,Double lat,Double longt,Integer sort_type);

    /**
     *
     * 功能描述: 根据商家ID 查询他已经选择的标签信息
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/5 15:49
     */
    List<Map<Object,Object>> selectTowByBusinessId(Integer business_id);

    /**
     * 更新商家标签状态
     * @param towTag        二级标签id
     * @param towTagStatus  二级标签状态
     * @return
     */
    int updateBusinessTagStatus(Integer business_id ,Integer towTag, Boolean towTagStatus);

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
     * 功能描述: 删除商户标签
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    13:20 2018/12/30
     */
    int deleteBusinessOneTag(Integer business_id);

    /**
     *
     * 功能描述: 插入新的几率
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    13:28 2018/12/30
     */
    int insertBusinessTag(Integer business_id, Integer one_tag_id, Integer two_tag_id);
}
