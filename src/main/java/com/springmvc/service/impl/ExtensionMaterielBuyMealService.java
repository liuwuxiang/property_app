package com.springmvc.service.impl;

import com.springmvc.dao.ExtensionMaterielBuyMealMapper;
import com.springmvc.entity.ExtensionMaterielBuyMeal;
import com.springmvc.service.IExtensionMaterielBuyMealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:28
 * @Description:物料购买套餐服务实现类
 */
@Service("/extensionMaterielBuyMealService")
public class ExtensionMaterielBuyMealService implements IExtensionMaterielBuyMealService {
    @Resource
    private ExtensionMaterielBuyMealMapper extensionMaterielBuyMealMapper;

    /**
     *
     * 功能描述: 获取某个物料的所有套餐
     *
     * @param: materiel_id-物料id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 3:27 AM
     */
    @Override
    public List<Map<Object, Object>> selectAllRecordByMaterielId(Integer materiel_id) {
        return this.extensionMaterielBuyMealMapper.selectAllRecordByMaterielId(materiel_id);
    }

    /**
     *
     * 功能描述: 通过套餐id查询套餐
     *
     * @param: record_id
     * @return: ExtensionMaterielBuyMeal
     * @author: zhangfan
     * @date: 2018/10/30 4:08 AM
     */
    @Override
    public ExtensionMaterielBuyMeal selectById(Integer record_id) {
        return this.extensionMaterielBuyMealMapper.selectById(record_id);
    }
}
