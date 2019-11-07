package com.springmvc.service.impl;

import com.springmvc.dao.MyMaterielMapper;
import com.springmvc.entity.MyMateriel;
import com.springmvc.service.IMyMaterielService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:09
 * @Description:万能卡商家购买的物料服务实现类
 */
@Service("/myMaterielService")
public class MyMaterielService implements IMyMaterielService {
    @Resource
    private MyMaterielMapper myMaterielMapper;

    /**
     *
     * 功能描述: 获取某商家的所有物料
     *
     * @param: business_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 3:07 AM
     */
    @Override
    public List<Map<Object, Object>> selectAllRecordByUserId(Integer business_id) {
        return this.myMaterielMapper.selectAllRecordByUserId(business_id);
    }

    /**
     *
     * 功能描述: 通过物料id以及商家id查询
     *
     * @param: business_id,materiel_id
     * @return: MyMateriel
     * @author: zhangfan
     * @date: 2018/10/30 4:12 AM
     */
    @Override
    public MyMateriel selectByMaterielIdAndBusinessId(Integer business_id, Integer materiel_id) {
        return this.myMaterielMapper.selectByMaterielIdAndBusinessId(business_id, materiel_id);
    }

    /**
     *
     * 功能描述: 新增物料套餐购买记录
     *
     * @param: myMateriel
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:14 AM
     */
    @Override
    public int insertMaterielBuyRecord(MyMateriel myMateriel) {
        return this.myMaterielMapper.insertMaterielBuyRecord(myMateriel);
    }

    /**
     *
     * 功能描述: 更新商家物料剩余量
     *
     * @param: myMateriel
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/30 4:15 AM
     */
    @Override
    public int updateSurplusNumber(MyMateriel myMateriel) {
        return this.myMaterielMapper.updateSurplusNumber(myMateriel);
    }

    /**
     * 功能描述: 扣除商家物料
     *
     * @param materiel_id
     * @param back_number
     * @return
     * @author 杨新杰
     * @date 15:27 2019/1/5
     */
    @Override
    public int backMaterielNumber(Integer business_id,Integer materiel_id, Integer back_number) {
        return this.myMaterielMapper.backMaterielNumber(business_id,materiel_id,back_number);
    }
}
