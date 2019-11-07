package com.springmvc.dao;

import com.springmvc.entity.WnkStoreInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/27 12:09
 *
 */
public interface WnkStoreInformationMapper {

    /**
     *
     * 功能描述：通过商家id查询店铺信息
     *
     * @param   business_id
     * @return: 返回商家id查询店铺信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:08
     */
    WnkStoreInformation selectByBusinessId(Integer business_id);

    /**
     *
     * 功能描述：修改店铺信息
     *
     * @param    wnkStoreInformation
     * @return:  返回修改店铺信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:09
     */
    int updateStoreInformation(WnkStoreInformation wnkStoreInformation);
    /**
     *
     * 功能描述: 查询某个分类下的所有商家
     *
     * @param business_type_id 类别id
     * @param lat 纬度
     * @param longt 经度
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:20 PM
     */
    List<Map<Object,Object>> selectByTypeId(Integer business_type_id, Double lat, Double longt,@Param(value="sort_type") Integer sort_type);

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
    List<Map<Object,Object>> fuzzyQueryBusiness(Double lat, Double longt,@Param(value = "fuzzy_store_name") String fuzzy_store_name,@Param(value="sort_type") Integer sort_type);

    /**
     *
     * 功能描述：添加商户信息
     *
     * @param   wnkStoreInformation
     * @return: 返回添加商户信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:10
     */
    int insertBusinessInformation(WnkStoreInformation wnkStoreInformation);

    /**
     *
     * 功能描述：后台更新商户信息
     *
     * @param   wnkStoreInformation
     * @return: 返回更新商户信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:11
     */
    int adminUpdateStoreInformation(WnkStoreInformation wnkStoreInformation);

    /**
     *
     * 功能描述：查询附近十家商户
     *
     * @param   lat
     * @param   longt
     * @return: 返回查询附近十家商户
     * @auther: 刘武祥
     * @date：  2019/2/27 12:12
     */
    List<Map<Object,Object>> selectNearbyBusiness(Double lat,Double longt);

    /**
     *
     * 功能描述：获取推荐商户
     *
     * @return: 返回获取推荐商户信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:14
     */
    List<Map<Object,Object>> selectRecommendBusiness();
    //
    /**
     *
     * 功能描述：搜索商家
     *
     * @param   store_name
     * @param   lat
     * @param   longt
     * @return: 返回搜索商家信息
     * @auther: 刘武祥
     * @date：  2019/2/27 12:14
     */
    List<Map<Object,Object>> selectByStoreName(String store_name,Double lat,Double longt);
    /**
     *
     * 功能描述: 根据商家id查询商家推荐的商家
     *
     * @param: recommend_business_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/27 6:48 PM
     */
    List<Map<Object,Object>> selectBusinessByRecommendBusinessId(Integer recommend_business_id);
    /**
     *
     * 功能描述: 更新商家等级信息
     *
     * @param: wnkStoreInformation
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:11 AM
     */
    int updateBusinessLevel(WnkStoreInformation wnkStoreInformation);

    /**
     *
     * 功能描述: 获取所有商家
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/29 7:53 AM
     */
    List<Map<Object,Object>> selectAllBusiness();

    /**
     *
     * 功能描述: 更新商家二维码
     *
     * @param: recommend_qr_code,pay_qr_code,business_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 7:55 AM
     */
    int updateBusinessQrCode(String recommend_qr_code,String pay_qr_code,Integer business_id);

    /**
     *
     * 功能描述: 更新商家万能卡物料购买次数
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/3 8:08 PM
     */
    int updateBusinessWnkBuyNumber(WnkStoreInformation wnkStoreInformation);

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
    int updateBusinessLabel(@Param("business_id") String business_id, @Param("fuwu_label") String fuwu_label, @Param("tese_label") String tese_label);

    /**
     * 功能描述: 更新商家营业状态
     *
     * @param type
     * @param business_id
     * @param:
     * @return:
     * @author: 杨新杰
     * @date: 2018/11/3 8:08 PM
     */
    int updateBusinessOperateStatus(@Param("operate_status") Integer type,@Param("business_id") Integer business_id);

    /**
     * 功能描述: 查询营业状态
     *
     * @param business_id 商家ID
     * @return:
     * @author: 杨新杰
     * @date: 2018/11/3 8:08 PM
     */
    Map<String,Object> selectBusinessOperateStatus(@Param("business_id") Integer business_id);

    /**
     *
     * 功能描述: 更新商户特别推荐状态
     *
     * @param:  wnkStoreInformation
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 9:54 PM
     */
    int updateBusinessEspeciallyRecommendState(WnkStoreInformation wnkStoreInformation);

    /**
     *
     * 功能描述: 用户端获取特别推荐商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 10:16 PM
     */
    List<Map<Object,Object>> selectEspeciallyRecommendBusiness();

    /**
     *
     * 功能描述: 根据商家名称搜索商家
     *
     * @param store_name 商家名称
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:14 PM
     */
    List<Map<Object,Object>> selectBusinessByStoreName(String store_name);

    /**
     *
     * 功能描述: 修改店铺个推APPID
     *
     * @param:  wnkStoreInformation
     * @return:
     * @author: zhangfan
     * @date: 2018/12/19 1:53 AM
     */
    int updateStoreGeTuiAppId(WnkStoreInformation wnkStoreInformation);

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
    int upperOrLowerBusiness(Integer is_lower,Integer store_id);

}
