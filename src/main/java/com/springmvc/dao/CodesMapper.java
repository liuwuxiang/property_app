package com.springmvc.dao;

import com.springmvc.entity.Codes;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;

import java.util.List;
import java.util.Map;

public interface CodesMapper {
    //新增手机短信验证码
    int addMobileCode(Codes codes);
    //新增邮箱短信验证码
    int addEmailCode(Codes codes);
    //通过手机号、验证码、类型查询手机验证码
    List<Map<Object,Object>> selectMobileCode(String send_number,String code,Integer make_type);
    //通过邮箱号、验证码查询邮箱验证码
    List<Map<Object,Object>> selectEmailCode(String send_number,String code);
    //标记验证码为已使用
    int updateCodesState(Integer id);
}
