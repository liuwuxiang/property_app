package com.springmvc.service.impl;

import com.springmvc.dao.WnkCommoditiesMapper;
import com.springmvc.entity.WnkCommodities;
import com.springmvc.service.IWnkCommoditiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkCommoditiesService")
public class WnkCommoditiesService implements IWnkCommoditiesService{
    @Resource
    private WnkCommoditiesMapper wnkCommoditiesMapper;

    @Override
    public List<Map<Object, Object>> selectAllCommodityByTypeId(Integer type_id) {
        return this.wnkCommoditiesMapper.selectAllCommodityByTypeId(type_id);
    }

    @Override
    public WnkCommodities selectById(Integer record_id) {
        return this.wnkCommoditiesMapper.selectById(record_id);
    }

    @Override
    public int addCommodity(WnkCommodities wnkCommodities) {
        return this.wnkCommoditiesMapper.addCommodity(wnkCommodities);
    }

    @Override
    public int updateCommodity(WnkCommodities wnkCommodities) {
        return this.wnkCommoditiesMapper.updateCommodity(wnkCommodities);
    }

    @Override
    public int deleteCommodity(Integer type_id) {
        return this.wnkCommoditiesMapper.deleteCommodity(type_id);
    }

    /**
     *
     * 功能描述: 根据商家ID查询商家商品总量
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:43
     */
    @Override
    public int selectCommodityTotalById(Integer business_id) {
        return this.wnkCommoditiesMapper.selectCommodityTotalById(business_id);
    }

    /**
     * 查询商家所有商品信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:43
     */
    @Override
    public List<Map<String, Object>> selectCommoditiesInfoAllById(Integer business_id) {
        return this.wnkCommoditiesMapper.selectCommoditiesInfoAllById(business_id);
    }

    /**
     *
     * 功能描述: 更新商品标签
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/20 12:27 AM
     */
    @Override
    public int updateCommodityTag(WnkCommodities wnkCommodities) {
        return this.wnkCommoditiesMapper.updateCommodityTag(wnkCommodities);
    }

    /**
     *
     * 功能描述: 用户端查询某个分类下的商品
     *
     * @param type_id 商品分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 4:12 PM
     */
    @Override
    public List<Map<Object, Object>> userSelectAllCommodityByTypeId(Integer type_id) {
        return this.wnkCommoditiesMapper.userSelectAllCommodityByTypeId(type_id);
    }

    /**
     *
     * 功能描述: 通过商品名称模糊查询商家
     *
     * @param lat 纬度
     * @param longt 经度
     * @param fuzz_commodity_name 商品模糊名称
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 6:28 PM
     */
    @Override
    public List<Map<Object, Object>> selectBusinessByFuzzCommodityName(Double lat, Double longt, String fuzz_commodity_name) {
        return this.wnkCommoditiesMapper.selectBusinessByFuzzCommodityName(lat, longt, fuzz_commodity_name);
    }

    /**
     *
     * 功能描述:根据分类ID修改商品删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param type_id 商品分类ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:18 PM
     */
    @Override
    public int updateDeleteStateByTypeId(Integer delete_state, Integer type_id) {
        return this.wnkCommoditiesMapper.updateDeleteStateByTypeId(delete_state, type_id);
    }

    /**
     *
     * 功能描述: 根据商品ID修改商品删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param commodity_id 商品ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:53 PM
     */
    @Override
    public int updateDeleteStateByCommodityId(Integer delete_state, Integer commodity_id) {
        return this.wnkCommoditiesMapper.updateDeleteStateByCommodityId(delete_state, commodity_id);
    }

    /**
     * 查询商家商品中赠送额度最高的商品的赠送额度
     *
     * @param businessId 商家ID
     * @return 返回查询结果
     * @author 杨新杰
     */
    @Override
    public Map<String, Object> selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(Integer businessId) {
        return this.wnkCommoditiesMapper.selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(businessId);
    }

    /**
     *
     * 方法说明:查询商家的所有商品信息(排除下架的和禁用的)
     *
     * @author 杨新杰
     * @param businessId 商家ID
     * @return  查询结果
     **/
    @Override
    public List<Map<String, Object>> selectCommoditiesInfoAllExcludeDelAndUnderById(Integer businessId) {
        return this.wnkCommoditiesMapper.selectCommoditiesInfoAllExcludeDelAndUnderById(businessId);
    }
}
