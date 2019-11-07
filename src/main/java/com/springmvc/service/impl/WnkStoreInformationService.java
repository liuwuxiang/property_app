package com.springmvc.service.impl;

import com.springmvc.dao.WnkStoreInformationMapper;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.IWnkStoreInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/27 16:14
 * 
 */
@Service("/wnkStoreInformationService")
public class WnkStoreInformationService implements IWnkStoreInformationService{
    @Resource
    private WnkStoreInformationMapper wnkStoreInformationMapper;

    @Override
    public WnkStoreInformation selectByBusinessId(Integer business_id) {
        return this.wnkStoreInformationMapper.selectByBusinessId(business_id);
    }

    @Override
    public int updateStoreInformation(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.updateStoreInformation(wnkStoreInformation);
    }

    @Override
    public List<Map<Object, Object>> selectByTypeId(Integer business_type_id,Double lat,Double longt,Integer sort_type) {
        return this.wnkStoreInformationMapper.selectByTypeId(business_type_id,lat,longt,sort_type);
    }

    @Override
    public int insertBusinessInformation(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.insertBusinessInformation(wnkStoreInformation);
    }

    @Override
    public int adminUpdateStoreInformation(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.adminUpdateStoreInformation(wnkStoreInformation);
    }

    @Override
    public List<Map<Object, Object>> selectNearbyBusiness(Double lat, Double longt) {
        return this.wnkStoreInformationMapper.selectNearbyBusiness(lat, longt);
    }

    @Override
    public List<Map<Object, Object>> selectRecommendBusiness() {
        return this.wnkStoreInformationMapper.selectRecommendBusiness();
    }

    @Override
    public List<Map<Object, Object>> selectByStoreName(String store_name,Double lat,Double longt) {
        return this.wnkStoreInformationMapper.selectByStoreName(store_name,lat,longt);
    }

    @Override
    public List<Map<Object, Object>> selectBusinessByRecommendBusinessId(Integer recommend_business_id) {
        return this.wnkStoreInformationMapper.selectBusinessByRecommendBusinessId(recommend_business_id);
    }

    @Override
    public int updateBusinessLevel(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.updateBusinessLevel(wnkStoreInformation);
    }

    /**
     *
     * 功能描述: 获取所有商家
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/29 7:53 AM
     */
    @Override
    public List<Map<Object, Object>> selectAllBusiness() {
        return this.wnkStoreInformationMapper.selectAllBusiness();
    }

    /**
     *
     * 功能描述: 更新商家二维码
     *
     * @param: recommend_qr_code,pay_qr_code,business_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 7:55 AM
     */
    @Override
    public int updateBusinessQrCode(String recommend_qr_code, String pay_qr_code, Integer business_id) {
        return this.wnkStoreInformationMapper.updateBusinessQrCode(recommend_qr_code, pay_qr_code, business_id);
    }

    @Override
    public int updateBusinessWnkBuyNumber(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.updateBusinessWnkBuyNumber(wnkStoreInformation);
    }

    /**
     * 功能描述: 更新商家服务/特色标签
     *
     * @param business_id
     * @param fuwu_label
     * @param tese_label
     * @param:
     * @return:
     * @author: 杨新杰
     * @date: 2018/11/3 8:08 PM
     */
    @Override
    public int updateBusinessLabel(String business_id, String fuwu_label, String tese_label) {
        return this.wnkStoreInformationMapper.updateBusinessLabel(business_id,fuwu_label,tese_label);
    }

    /**
     * 功能描述: 更新商家营业状态
     *
     * @param type
     * @param:
     * @return:
     * @author: 杨新杰
     * @date: 2018/11/3 8:08 PM
     */
    @Override
    public int updateBusinessOperateStatus(Integer type,Integer business_id) {
        return this.wnkStoreInformationMapper.updateBusinessOperateStatus(type,business_id);
    }

    /**
     * 功能描述: 查询营业状态
     *
     * @param business_id 商家ID
     * @return:
     * @author: 杨新杰
     * @date: 2018/11/3 8:08 PM
     */
    @Override
    public Map<String, Object> selectBusinessOperateStatus(Integer business_id) {
        return this.wnkStoreInformationMapper.selectBusinessOperateStatus(business_id);
    }

    /**
     *
     * 功能描述: 更新商户特别推荐状态
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 9:54 PM
     */
    @Override
    public int updateBusinessEspeciallyRecommendState(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.updateBusinessEspeciallyRecommendState(wnkStoreInformation);
    }

    /**
     *
     * 功能描述: 用户端获取特别推荐商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 10:16 PM
     */
    @Override
    public List<Map<Object, Object>> selectEspeciallyRecommendBusiness() {
        return this.wnkStoreInformationMapper.selectEspeciallyRecommendBusiness();
    }

    /**
     *
     * 功能描述: 根据商家名称搜索商家
     *
     * @param store_name 商家名称
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:14 PM
     */
    @Override
    public List<Map<Object, Object>> selectBusinessByStoreName(String store_name) {
        return this.wnkStoreInformationMapper.selectBusinessByStoreName(store_name);
    }

    /**
     *
     * 功能描述: 模糊查询商户
     *
     * @param lat 纬度
     * @param longt 经度
     * @param fuzzy_store_name 模糊查询的商户名称
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:56 PM
     */
    @Override
    public List<Map<Object, Object>> fuzzyQueryBusiness(Double lat, Double longt, String fuzzy_store_name, Integer sort_type) {
        return this.wnkStoreInformationMapper.fuzzyQueryBusiness(lat, longt, fuzzy_store_name, sort_type);
    }

    /**
     *
     * 功能描述: 修改店铺个推APPID
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/19 1:53 AM
     */
    @Override
    public int updateStoreGeTuiAppId(WnkStoreInformation wnkStoreInformation) {
        return this.wnkStoreInformationMapper.updateStoreGeTuiAppId(wnkStoreInformation);
    }

    /**
     *
     * 功能描述: 上架/下架商户
     *
     * @param is_lower 是否已下架(0-未下架,1-已下架)
     * @param store_id 店铺ID(注：此处店铺ID不是商户账号ID,而是此表中的ID)
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 9:11 PM
     */
    @Override
    public int upperOrLowerBusiness(Integer is_lower, Integer store_id) {
        return this.wnkStoreInformationMapper.upperOrLowerBusiness(is_lower, store_id);
    }
}
