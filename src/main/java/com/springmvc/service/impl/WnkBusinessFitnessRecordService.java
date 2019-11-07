package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessFitnessRecordMapper;
import com.springmvc.entity.WnkBusinessFitnessRecord;
import com.springmvc.service.IWnkBusinessFitnessRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/29 17:18
 */
@Service("/wnkBusinessFitnessRecordService")
public class WnkBusinessFitnessRecordService implements IWnkBusinessFitnessRecordService {

    @Resource
    private WnkBusinessFitnessRecordMapper wnkBusinessFitnessRecordMapper;

    /**
     * 功能描述: 查询用户进/出门状态
     *
     * @param user_id     用户ID
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 17:25 2018/12/29
     */
    @Override
    public Map<String, Object> selectUserGymCardStatusByUserId(Integer user_id, Integer business_id) {
        return this.wnkBusinessFitnessRecordMapper.selectUserGymCardStatusByUserId(user_id,business_id);
    }

    /**
     * 功能描述:  插入用户进/出门记录
     *
     * @param w
     * @return
     * @author 杨新杰
     * @date 18:03 2018/12/29
     */
    @Override
    public int insertUserGymCardStatusByUserId(WnkBusinessFitnessRecord w) {
        return this.wnkBusinessFitnessRecordMapper.insertUserGymCardStatusByUserId(w);
    }

    /**
     * 功能描述: 获取健身卡使用记录
     *
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    @Override
    public List<Map<String, Object>> selectFitnessCardDetailById(Integer user_id, Integer business_id) {
        return this.wnkBusinessFitnessRecordMapper.selectFitnessCardDetailById(user_id,business_id);
    }
}
