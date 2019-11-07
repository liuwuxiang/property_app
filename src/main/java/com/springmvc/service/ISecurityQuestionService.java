package com.springmvc.service;

import java.util.List;
import java.util.Map;

public interface ISecurityQuestionService {
    /**
     * 获取所有可选择的密保问题
     * @return
     */
    List<Map<Object,Object>> selectAllSecurityQuestion();
}
