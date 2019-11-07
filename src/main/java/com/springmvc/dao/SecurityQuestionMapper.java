package com.springmvc.dao;

import java.util.List;
import java.util.Map;

public interface SecurityQuestionMapper {
    //获取所有可选择的密保问题
    List<Map<Object,Object>> selectAllSecurityQuestion();
}
