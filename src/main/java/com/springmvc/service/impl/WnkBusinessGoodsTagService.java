package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessGoodsTagMapper;
import com.springmvc.entity.WnkBusinessGoodsTag;
import com.springmvc.service.IWnkBusinessGoodsTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author 杨新杰
 * @Date 2018/11/17 14:24
 */
@Service("/wnkBusinessGoodsTagService")
public class WnkBusinessGoodsTagService implements IWnkBusinessGoodsTagService {

    @Resource
    private WnkBusinessGoodsTagMapper wnkBusinessGoodsTagMapper;

    /**
     * 功能描述: 根据商家ID查询商品标签
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/17 14:28
     */
    @Override
    public List<Map<String, Object>> selectBusinessGoodsTagById(Integer business_id,Integer type) {
        return this.wnkBusinessGoodsTagMapper.selectBusinessGoodsTagById(business_id,type);
    }

    /**
     * 功能描述: 新增商品标签
     *
     * @param wnkBusinessGoodsTag 商品标签实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/17 14:41
     */
    @Override
    public int insertBusinessGoodsTag(WnkBusinessGoodsTag wnkBusinessGoodsTag) {
        return this.wnkBusinessGoodsTagMapper.insertBusinessGoodsTag(wnkBusinessGoodsTag);
    }

    /**
     * 功能描述: 更新商品标签
     *
     * @param wnkBusinessGoodsTag 商品标签实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/17 14:41
     */
    @Override
    public int updateBusinessGoodsTag(WnkBusinessGoodsTag wnkBusinessGoodsTag) {
        return this.wnkBusinessGoodsTagMapper.updateBusinessGoodsTag(wnkBusinessGoodsTag);
    }

    /**
     *
     * 功能描述: 根据标签ID查询商品标签
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    16:33 2018/12/27
     */
    public WnkBusinessGoodsTag selectGoodsTagInfoById (Integer tag_id){
        return this.wnkBusinessGoodsTagMapper.selectGoodsTagInfoById(tag_id);
    }

}
