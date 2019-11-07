package com.springmvc.service;

import com.springmvc.entity.UsersCertificates;

import java.util.List;
import java.util.Map;

public interface IUsersCertificatesService {
    /**
     * 通过证书编号查询证书信息
     * @param number
     * @return
     */
    UsersCertificates selectByNumber(String number);

    /**
     * 查询某个用户的所有证书
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectUserRecord(Integer user_id);

    /**
     * 新增用户证书
     * @param usersCertificates
     * @return
     */
    int insertUserCertificate(UsersCertificates usersCertificates);

    /**
     * 生成证书
     * @param user_name
     * @param user_id
     * @param intergal_number
     * @param silver_coin_number
     * @param record_id
     * @return
     */
    int generateCertificate(String user_name,Integer user_id,Integer intergal_number,Integer silver_coin_number,Integer record_id);
}
