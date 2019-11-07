package com.springmvc.service;

import com.springmvc.entity.Codes;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;

import java.util.List;
import java.util.Map;

/**
 * 验证码操作Service
 */
public interface ICodesService {
    /**
     * 新增手机短信验证码
     * @param codes 验证码
     * @return 返回新增结果
     */
    int addMobileCode(Codes codes);

    /**
     * 新增邮箱短信验证码
     * @param codes 验证码
     * @return 返回新增结果
     */
    int addEmailCode(Codes codes);

    /**
     * 通过手机号、验证码、类型查询手机验证码
     * @param send_number  手机号码
     * @param code         验证码
     * @param make_type    查询类型
     * @return 返回查询到的验证码list
     */
    List<Map<Object,Object>> selectMobileCode(String send_number, String code, Integer make_type);

    /**
     * 通过邮箱号、验证码查询邮箱验证码
     * @param send_number 邮箱
     * @param code        验证码
     * @return 返回查询到的验证码list
     */
    List<Map<Object,Object>> selectEmailCode(String send_number,String code);

    /**
     * 标记验证码为已使用
     * @param id 验证码记录ID
     * @return 成功返回1 失败返回0
     */
    int updateCodesState(Integer id);
}
