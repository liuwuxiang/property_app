package com.springmvc.service;

import com.springmvc.entity.UserCoupons;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:50
 * @Description:用户优惠券服务接口类
 */
public interface IUserCouponsService {
    /**
     *
     * 功能描述: 获取某用户的所有物料(优惠券)
     *
     * @param: user_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 3:07 AM
     */
    List<Map<Object,Object>> selectAllRecordByUserId(Integer user_id);

    /**
     *
     * 功能描述: 通过物料id以及用户id查询
     *
     * @param: user_id,materiel_id
     * @return: MyMateriel
     * @author: zhangfan
     * @date: 2018/10/30 4:12 AM
     */
    UserCoupons selectByMaterielIdAndUserId(Integer user_id, Integer materiel_id);

    /**
     *
     * 功能描述: 新增物料收入信息
     *
     * @param: userCoupons
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:14 AM
     */
    int insertMaterielBuyRecord(UserCoupons userCoupons);

    /**
     *
     * 功能描述: 更新用户物料剩余量
     *
     * @param: userCoupons
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:15 AM
     */
    int updateSurplusNumber(UserCoupons userCoupons);

    /**
     *
     * 功能描述: 通过商家分类id以及用户id查询用户优惠券信息
     *
     * @param business_type_id 商户分类id
     * @param user_id 用户id
     * @return: UserCoupons
     * @author: zhangfan
     * @date: 2018/11/3 6:25 PM
     */
    UserCoupons selectByBusinessTypeIdAndUserId(Integer business_type_id,Integer user_id);

    /**
     *
     * 功能描述:通过参数方式更新用户优惠券余额
     *
     * @param surplus_number 优惠券余额
     * @param record_id 记录id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/21 4:04 PM
     */
    int updateSurplusNumberByCS(Integer surplus_number,Integer record_id);
}
