package com.springmvc.dao;

import com.springmvc.entity.MyMateriel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:02
 * @Description:商家购买的物料信息Mapper
 */
public interface MyMaterielMapper {
    /**
     *
     * 功能描述: 获取某商家的所有物料
     *
     * @param: business_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 3:07 AM
     */
    List<Map<Object,Object>> selectAllRecordByUserId(Integer business_id);

    /**
     *
     * 功能描述: 通过物料id以及商家id查询
     *
     * @param: business_id,materiel_id
     * @return: MyMateriel
     * @author: zhangfan
     * @date: 2018/10/30 4:12 AM
     */
    MyMateriel selectByMaterielIdAndBusinessId(Integer business_id,Integer materiel_id);

    /**
     *
     * 功能描述: 新增物料套餐购买记录
     *
     * @param: myMateriel
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:14 AM
     */
    int insertMaterielBuyRecord(MyMateriel myMateriel);
    
    /**
     *
     * 功能描述: 更新商家物料剩余量
     *
     * @param: myMateriel
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:15 AM
     */
    int updateSurplusNumber(MyMateriel myMateriel);

    /**
     * 功能描述: 扣除商家物料
     *
     * @param materiel_id
     * @param back_number
     * @return
     * @author 杨新杰
     * @date 15:27 2019/1/5
     */
    int backMaterielNumber(@Param("business_id") Integer business_id, @Param("materiel_id") Integer materiel_id, @Param("back_number") Integer back_number);
}
