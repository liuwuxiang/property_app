package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessSearchRecordMapper;
import com.springmvc.entity.WnkBusinessSearchRecord;
import com.springmvc.service.IWnkBusinessSearchRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 17:09
 * @Description:商家搜索记录服务实现类，用于提取热门搜索商家
 */
@Service("/wnkBusinessSearchRecordService")
public class WnkBusinessSearchRecordService implements IWnkBusinessSearchRecordService {
    @Resource
    private WnkBusinessSearchRecordMapper wnkBusinessSearchRecordMapper;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 通过商家ID查询记录
     *
     * @param business_id 商家ID
     * @return: WnkBusinessSearchRecord
     * @author: zhangfan
     * @date: 2018/12/17 5:06 PM
     */
    @Override
    public WnkBusinessSearchRecord selectByBusinessId(Integer business_id) {
        return this.wnkBusinessSearchRecordMapper.selectByBusinessId(business_id);
    }

    /**
     *
     * 功能描述: 获取搜索次数最大的十条记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    @Override
    public List<Map<Object, Object>> selectSearchMaxTenRecord() {
        return this.wnkBusinessSearchRecordMapper.selectSearchMaxTenRecord();
    }

    /**
     *
     * 功能描述: 插入记录
     *
     * @param search_business_name 搜索的商家名字
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    @Override
    public int insertRecord(String search_business_name) {
        List<Map<Object,Object>> list = this.wnkStoreInformationService.selectBusinessByStoreName(search_business_name);
        if (list.size() <= 0){
            return 1;
        }
        else{
            Map<Object,Object> businessMap = list.get(0);
            Integer businessId = (Integer) businessMap.get("business_id");
            WnkBusinessSearchRecord wnkBusinessSearchRecord = this.wnkBusinessSearchRecordMapper.selectByBusinessId(businessId);
            if (wnkBusinessSearchRecord == null){
                wnkBusinessSearchRecord = new WnkBusinessSearchRecord();
                wnkBusinessSearchRecord.setBusiness_id(businessId);
                wnkBusinessSearchRecord.setSearch_number(1);
                this.wnkBusinessSearchRecordMapper.insertRecord(wnkBusinessSearchRecord);
            }
            else{
                this.wnkBusinessSearchRecordMapper.updateSearchNumber(wnkBusinessSearchRecord.getSearch_number()+1,businessId);
            }

            return 1;
        }
    }

    /**
     *
     * 功能描述: 更新搜索次数
     *
     * @param search_number 搜索次数
     * @param business_id 商家ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    @Override
    public int updateSearchNumber(Integer search_number, Integer business_id) {
        return this.wnkBusinessSearchRecordMapper.updateSearchNumber(search_number, business_id);
    }
}
