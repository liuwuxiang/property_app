package com.springmvc.service.impl;

import com.springmvc.dao.WnkIntegralGoodsMapper;
import com.springmvc.entity.WnkIntegralGoods;
import com.springmvc.service.IWnkIntegralGoodsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/11 14:45
 */
@Service("wnkIntegralGoodsServer")
public class WnkIntegralGoodsServer implements IWnkIntegralGoodsServer {

    @Resource
    private WnkIntegralGoodsMapper wnkIntegralGoodsMapper;

    /**
     *
     * 功能描述: 新增商品
     *
     * @param  wnkIntegralGoods 商品实体类
     * @return 返回新增条目数  正确时候应该返回1
     * @author 杨新杰
     * @date   2018/10/11 14:49
     */
    @Override
    public int addIntegralGoods(WnkIntegralGoods wnkIntegralGoods) {
        return wnkIntegralGoodsMapper.addIntegralGoods(wnkIntegralGoods);
    }

    /**
     *
     * 功能描述: 修改商品信息
     *
     * @param  wnkIntegralGoods 商品实体类
     * @return 修改成功返回1 失败返回0
     * @author 杨新杰
     * @date   2018/10/11 16:14
     */
    @Override
    public int editIntegralGoods(WnkIntegralGoods wnkIntegralGoods) {
        return wnkIntegralGoodsMapper.editIntegralGoods(wnkIntegralGoods);
    }

    /**
     *
     * 功能描述: 根据ID获取商品信息
     *
     * @param  business_id  商家ID
     * @param  id           商品ID
     * @return
     * @author 杨新杰
     * @date   2018/10/11 15:20
     */
    @Override
    public Map<String, Object> getIntegralGoodsById(Integer business_id, Integer id) {
        return wnkIntegralGoodsMapper.getIntegralGoodsById(business_id,id);
    }

    /**
     *
     * 功能描述:获取商家所有的商品
     *
     * @param  business_id 商家ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date   2018/10/11 16:37
     */
    @Override
    public List<Map<String, Object>> getIntegralGoodsAll(Integer business_id) {
        return wnkIntegralGoodsMapper.getIntegralGoodsAll(business_id);
    }

    /**
     * 功能描述:获取商家所有的已经启用商品
     *
     * @param business_id 商家ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date 2018/10/11 16:37
     */
    @Override
    public List<Map<String, Object>> getIntegralGoodsAllAndTrue(Integer business_id) {
        return wnkIntegralGoodsMapper.getIntegralGoodsAllAndTrue(business_id);
    }

    /**
     * 功能描述: 根据商品ID查询商品的信息
     *
     * @param business_id 商家ID
     * @param goods_id    商品ID
     * @return 返回商品信息
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @Override
    public Map<String, Object> getIntegralByIdAndWnk(Integer business_id, Integer goods_id) {
        return wnkIntegralGoodsMapper.getIntegralByIdAndWnk(business_id,goods_id);
    }

    /**
     *
     * 功能描述: 获取推荐商品
     *
     * @param: List
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 1:18 AM
     */
    @Override
    public List<Map<String,Object>> getRecommednGoods() {
        return this.wnkIntegralGoodsMapper.getRecommednGoods();
    }

    @Override
    public List<WnkIntegralGoods> getGoodsByTypeId(Integer type_id) {
        return this.wnkIntegralGoodsMapper.getGoodsByTypeId(type_id);
    }

    /**
     * 功能描述: 获取所有积分商品
     *
     * @return
     * @author 杨新杰
     * @date 17:36 2018/12/31
     */
    @Override
    public List<Map<String, Object>> getAllIntegralBusinessGoods() {
        return this.wnkIntegralGoodsMapper.getAllIntegralBusinessGoods();
    }

    /**
     * 功能描述: 更新商家积分商品推荐状态
     *
     * @param status
     * @param goods_id
     * @return
     * @author 杨新杰
     * @date 18:01 2018/12/31
     */
    @Override
    public int updateBusinessIntegralGoodsRecommendStatus(Integer status, Integer goods_id) {
        return this.wnkIntegralGoodsMapper.updateBusinessIntegralGoodsRecommendStatus(status,goods_id);
    }

    /**
     * 功能描述: 更新商家积分商品上架状态
     *
     * @param status
     * @param goods_id
     * @return
     * @author 杨新杰
     * @date 18:01 2018/12/31
     */
    @Override
    public int updateBusinessIntegralGoodsCheckedStatus(Integer status, Integer goods_id) {
        return this.wnkIntegralGoodsMapper.updateBusinessIntegralGoodsCheckedStatus(status,goods_id);
    }

    /**
     *
     * 功能描述: 查询商家积分商品管理
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/16 0016 10:53
     */
    @Override
    public List<Map<Object, Object>> adminSearchIntegralBusinessGoods(Map<String, Object> map) {
        return this.wnkIntegralGoodsMapper.adminSearchIntegralBusinessGoods(map);
    }


}
