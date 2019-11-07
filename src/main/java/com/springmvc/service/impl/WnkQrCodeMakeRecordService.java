package com.springmvc.service.impl;

import com.springmvc.dao.WnkQrCodeMakeRecordMapper;
import com.springmvc.entity.WnkQrCodeMakeRecord;
import com.springmvc.service.IWnkQrCodeMakeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkQrCodeMakeRecordService")
public class WnkQrCodeMakeRecordService implements IWnkQrCodeMakeRecordService{
    @Resource
    private WnkQrCodeMakeRecordMapper qrCodeMakeRecordMapper;

    @Override
    public int insertMakeRecord(WnkQrCodeMakeRecord wnkQrCodeMakeRecord) {
        return this.qrCodeMakeRecordMapper.insertMakeRecord(wnkQrCodeMakeRecord);
    }

    @Override
    public int selectCurrentMakeNumber(Integer user_id, Integer business_type_id) {
        List<Map<Object,Object>> list = this.qrCodeMakeRecordMapper.selectCurrentMakeNumberReturnList(user_id, business_type_id);
        Integer count = 0;
        for (Integer index = 0;index < list.size();index++){
            Map<Object,Object> map = list.get(index);
            Integer number = (Integer) map.get("make_number");
            count = count+number;
        }
        return count;
    }

    @Override
    public List<Map<Object, Object>> selectByBusinessId(Integer business_id) {
        return this.qrCodeMakeRecordMapper.selectByBusinessId(business_id);
    }

    @Override
    public List<Map<Object, Object>> selectUserNumberByBusinessId(Integer business_id) {
        return this.qrCodeMakeRecordMapper.selectUserNumberByBusinessId(business_id);
    }

    @Override
    public List<Map<Object, Object>> selectByUserId(Integer user_id) {
        return this.qrCodeMakeRecordMapper.selectByUserId(user_id);
    }

    @Override
    public int selectCurrentMakeNumberReturnListByBusinessId(Integer business_id) {
        List<Map<Object,Object>> list = this.qrCodeMakeRecordMapper.selectCurrentMakeNumberReturnListByBusinessId(business_id);
        Integer count = 0;
        for (Integer index = 0;index < list.size();index++){
            Map<Object,Object> map = list.get(index);
            Integer number = (Integer) map.get("make_number");
            count = count+number;
        }
        return count;
    }

    @Override
    public WnkQrCodeMakeRecord selectById(Integer record_id) {
        return this.qrCodeMakeRecordMapper.selectById(record_id);
    }

    @Override
    public WnkQrCodeMakeRecord selectByOrderNo(String order_no) {
        return this.qrCodeMakeRecordMapper.selectByOrderNo(order_no);
    }

    @Override
    public int updateOrderStateByOrderNo(Integer state, String order_no) {
        return this.qrCodeMakeRecordMapper.updateOrderStateByOrderNo(state, order_no);
    }
}
