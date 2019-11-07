package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTagChooseRecordMapper;
import com.springmvc.dao.WnkBusinessTagMapper;
import com.springmvc.entity.WnkBusinessTag;
import com.springmvc.service.IWnkBusinessTagChooseRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 03:07
 * @Description:用于记录万能卡商家选择的标签Service实现类
 */
@Service("/wnkBusinessTagChooseRecordService")
public class WnkBusinessTagChooseRecordService implements IWnkBusinessTagChooseRecordService {
    @Resource
    private WnkBusinessTagChooseRecordMapper wnkBusinessTagChooseRecordMapper;
    @Resource
    private WnkBusinessTagMapper wnkBusinessTagMapper;

    @Override
    public List<Map<Object, Object>> selectByTagId(Integer tag_id, Double lat, Double longt, Integer sort_type) {
        return this.wnkBusinessTagChooseRecordMapper.selectByTagId(tag_id, lat, longt, sort_type);
    }

    /**
     * 功能描述: 根据商家ID 查询他已经选择的标签信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/5 15:49
     */
    @Override
    public List<Map<Object, Object>> selectTowByBusinessId(Integer business_id) {

        return this.wnkBusinessTagChooseRecordMapper.selectTowByBusinessId(business_id);
    }

    /**
     * 更新商家标签状态
     *
     * @param towTag       二级标签id
     * @param towTagStatus 二级标签状态
     * @return
     */
    @Override
    public int updateBusinessTagStatus(Integer business_id, Integer towTag, Boolean towTagStatus) {
//        if (towTagStatus) {
//            if(wnkBusinessTagChooseRecordMapper.selectByTwoTagId(business_id,towTag) == null){
//                // 查询一级标签ID
//                Map<String, Object> map = this.wnkBusinessTagMapper.selectOneTagIdByTwoTagId(towTag);
//                // 插入标签
//                i = this.wnkBusinessTagChooseRecordMapper.insertBusinessTag(business_id, (Integer) map.get("last_id"), towTag);
//            } else {
//                i =1;
//            }
//        } else {
//            // 先查询对应记录是否存在
//           if (wnkBusinessTagChooseRecordMapper.selectByTwoTagId(business_id,towTag) != null){
//               i = this.wnkBusinessTagChooseRecordMapper.deleteBusinessTag(business_id, towTag);
//           } else {
//               i = 1;
//           }
//        }
        // 查询一级标签ID
        Map<String, Object> map = this.wnkBusinessTagMapper.selectOneTagIdByTwoTagId(towTag);
        if (map != null){
            Integer oneTagId = (Integer) map.get("last_id");
            List<Map<Object,Object>> list = this.wnkBusinessTagChooseRecordMapper.selectByBusinessId(business_id);
            if (list.size() <= 0){
                return this.wnkBusinessTagChooseRecordMapper.insertBusinessTag(business_id,oneTagId,towTag);
            }
            else{
                return this.wnkBusinessTagChooseRecordMapper.updateBusinessTagById(oneTagId,towTag,business_id);
            }

        }
        else{
            return 0;
        }
        // 更新二级标签

    }

    /**
     * 功能描述:获取某个标签下的推荐商家
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/6 8:07 PM
     */
    @Override
    public List<Map<Object, Object>> selectRecommendByTagOneId(Integer tag_id, Double lat, Double longt) {
        return this.wnkBusinessTagChooseRecordMapper.selectRecommendByTagOneId(tag_id, lat, longt);
    }

    /**
     * 功能描述: 删除商户标签
     *
     * @param business_id
     * @param id
     * @return
     * @author 杨新杰
     * @date 13:20 2018/12/30
     */
    @Override
    public int deleteBusinessOneTag(Integer business_id) {
        return this.wnkBusinessTagChooseRecordMapper.deleteBusinessOneTag(business_id);
    }

    /**
     * 功能描述: 插入新的几率
     *
     * @param business_id
     * @return
     * @author 杨新杰
     * @date 13:28 2018/12/30
     */
    @Override
    public int insertBusinessTag(Integer business_id, Integer one_tag_id, Integer two_tag_id) {
        return this.wnkBusinessTagChooseRecordMapper.insertBusinessTag(business_id,one_tag_id,two_tag_id);
    }
}
