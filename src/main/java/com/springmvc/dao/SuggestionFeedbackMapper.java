package com.springmvc.dao;

import com.springmvc.entity.SuggestionFeedback;

import java.util.List;
import java.util.Map;

public interface SuggestionFeedbackMapper {

    /**
     *
     * 功能描述: 新增反馈
     *
     * @param   suggestionFeedback
     * @return: 返回新增的反馈信息
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:03
     */
    int addFeedback(SuggestionFeedback suggestionFeedback);

    /**
     *
     * 功能描述: 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     *
     * @param   user_id
     * @param   isUser_id
     * @return: 返回重新修改记录所属用户
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:03
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     *
     * 功能描述: 后台查询所有反馈内容
     *
     * @param
     * @return: 返回查询到的所有反馈内容
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:03
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: 返回查询记录
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:03
     */
    SuggestionFeedback selectById(Integer record_id);

    /**
     *
     * 功能描述: 处理反馈信息
     *
     * @param   suggestionFeedback
     * @return: 返回处理反馈信息
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:02
     */
    int updateFeedDealMessage(SuggestionFeedback suggestionFeedback);

    /**
     *
     * 功能描述: 获取所有未处理的建议反馈
     *
     * @param
     * @return: 返回所有未处理的建议反馈
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:02
     */
    List<Map<String,Object>> selectNoHandleAll();

    /**
     *
     * 功能描述: 根据条件查询反馈信息
     *
     * @param   map  查询条件
     * @return: 返回所查询到的反馈信息
     * @author: 刘武祥
     * @date: 2019/1/11 0011 16:11
     */
    List<Map<String,Object>> adminSearchSuggestionFeedByConditions(Map<String,Object> map);
}
