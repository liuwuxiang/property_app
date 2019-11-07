package com.springmvc.service.impl;

import com.springmvc.dao.UsersCertificatesMapper;
import com.springmvc.entity.UsersCertificates;
import com.springmvc.service.IUsersCertificatesService;
import com.springmvc.utils.PhotoSynthesisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/usersCertificatesService")
public class UsersCertificatesService implements IUsersCertificatesService{
    @Resource
    private UsersCertificatesMapper usersCertificatesMapper;

    @Override
    public UsersCertificates selectByNumber(String number) {
        return this.usersCertificatesMapper.selectByNumber(number);
    }

    @Override
    public List<Map<Object, Object>> selectUserRecord(Integer user_id) {
        return this.usersCertificatesMapper.selectUserRecord(user_id);
    }

    @Override
    public int insertUserCertificate(UsersCertificates usersCertificates) {
        return this.usersCertificatesMapper.insertUserCertificate(usersCertificates);
    }

    @Override
    public int generateCertificate(String user_name, Integer user_id, Integer intergal_number, Integer silver_coin_number, Integer record_id) {
        Date nowDate = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateStr = formatter.format(nowDate);
            Map<Object,Object> certificateMap = PhotoSynthesisUtil.synthesisPhoto(user_name,intergal_number.toString(),silver_coin_number.toString(),nowDateStr);
            if ((Boolean) certificateMap.get("status") == true){
                //,user_id,user_name,amount,option_equity,create_time,record_id
                UsersCertificates usersCertificates = new UsersCertificates();
                usersCertificates.setNumber((String)certificateMap.get("number"));
                usersCertificates.setCertificate_photo_id((String)certificateMap.get("photo_id"));
                usersCertificates.setUser_id(user_id);
                usersCertificates.setUser_name(user_name);
                usersCertificates.setAmount(intergal_number);
                usersCertificates.setOption_equity(silver_coin_number);
                usersCertificates.setCreate_time(nowDate);
                usersCertificates.setRecord_id(record_id);
                int addState = this.usersCertificatesMapper.insertUserCertificate(usersCertificates);
                if (addState >= 1){
                    return 1;
                }
                else{
                    return 0;
                }

            }
            else{
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
