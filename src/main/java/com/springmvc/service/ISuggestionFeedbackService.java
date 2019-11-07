package com.springmvc.service;

import com.springmvc.entity.SuggestionFeedback;

import java.util.List;
import java.util.Map;

public interface ISuggestionFeedbackService {
    /**
     * 新增反馈
     * @param suggestionFeedback
     * @return
     */
    int addFeedback(SuggestionFeedback suggestionFeedback);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 后台查询所有反馈内容
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    SuggestionFeedback selectById(Integer record_id);

    /**
     * 处理反馈信息
     * @param suggestionFeedback
     * @return
     */
    int updateFeedDealMessage(SuggestionFeedback suggestionFeedback);

    /**
     *
     * 功能描述: 获取所有未处理的建议反馈
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    10:49 2018/12/19
     */
    List<Map<String,Object>> selectNoHandleAll();

    /**
     *
     * 功能描述: 根据条件查询反馈信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/11 0011 16:09
     */
    List<Map<String,Object>> adminSearchSuggestionFeedByConditions(Map<String,Object> map);
}
