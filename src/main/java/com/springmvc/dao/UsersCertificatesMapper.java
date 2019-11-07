package com.springmvc.dao;

import com.springmvc.entity.UsersCertificates;

import java.util.List;
import java.util.Map;

public interface UsersCertificatesMapper {
    //通过证书编号查询证书信息
    UsersCertificates selectByNumber(String number);
    //查询某个用户的所有证书
    List<Map<Object,Object>> selectUserRecord(Integer user_id);
    //新增用户证书
    int insertUserCertificate(UsersCertificates usersCertificates);

}
