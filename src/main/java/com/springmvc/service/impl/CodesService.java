package com.springmvc.service.impl;

import com.springmvc.dao.CodesMapper;
import com.springmvc.entity.Codes;
import com.springmvc.service.ICodesService;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/codesService")
public class CodesService implements ICodesService{
    @Resource
    private CodesMapper codesMapper;

    @Override
    public int addMobileCode(Codes codes) {
        return this.codesMapper.addMobileCode(codes);
    }

    @Override
    public int addEmailCode(Codes codes) {
        return this.codesMapper.addEmailCode(codes);
    }

    @Override
    public List<Map<Object, Object>> selectMobileCode(String send_number, String code, Integer make_type) {
        return this.codesMapper.selectMobileCode(send_number, code, make_type);
    }

    @Override
    public List<Map<Object, Object>> selectEmailCode(String send_number, String code) {
        return this.codesMapper.selectEmailCode(send_number, code);
    }

    @Override
    public int updateCodesState(Integer id) {
        return this.codesMapper.updateCodesState(id);
    }
}
