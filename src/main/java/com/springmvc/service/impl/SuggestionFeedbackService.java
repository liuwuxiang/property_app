package com.springmvc.service.impl;

import com.springmvc.dao.SuggestionFeedbackMapper;
import com.springmvc.entity.SuggestionFeedback;
import com.springmvc.service.ISuggestionFeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/suggestionFeedbackService")
public class SuggestionFeedbackService implements ISuggestionFeedbackService{
    @Resource
    private SuggestionFeedbackMapper suggestionFeedbackMapper;

    /** 
     *
     * 功能描述: 新增反馈
     *
     * @param   suggestionFeedback
     * @return: 返回新增记录
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:10
     */
    @Override
    public int addFeedback(SuggestionFeedback suggestionFeedback) {
        return this.suggestionFeedbackMapper.addFeedback(suggestionFeedback);
    }

    /** 
     *
     * 功能描述: 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     *
     * @param   user_id
     * @param   isUser_id
     * @return: 返回所属用户
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:09
     */
    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.suggestionFeedbackMapper.updateRecordUserId(user_id, isUser_id);
    }

    /**
     *
     * 功能描述: 后台查询所有反馈内容
     *
     * @param
     * @return: 返回所有查询反馈内容
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:08
     */
    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.suggestionFeedbackMapper.selectAllAdmin();
    }

    /** 
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: 返回u查询记录
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:07
     */
    @Override
    public SuggestionFeedback selectById(Integer record_id) {
        return this.suggestionFeedbackMapper.selectById(record_id);
    }

    /** 
     *
     * 功能描述: 处理反馈信息
     *
     * @param   suggestionFeedback
     * @return: 返回处理反馈信息
     * @author: 刘武祥
     * @date: 2019/2/5 0005 12:05
     */
    @Override
    public int updateFeedDealMessage(SuggestionFeedback suggestionFeedback) {
        return this.suggestionFeedbackMapper.updateFeedDealMessage(suggestionFeedback);
    }

    /**
     * 功能描述: 获取所有未处理的建议反馈
     *
     * @return 返回所有未处理的建议反馈
     * @author 杨新杰
     * @date 10:49 2018/12/19
     */
    @Override
    public List<Map<String, Object>> selectNoHandleAll() {
        return this.suggestionFeedbackMapper.selectNoHandleAll();
    }

    /**
     *
     * 功能描述: 根据条件查询反馈信息
     *
     * @param   map
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/11 0011 16:10
     */
    @Override
    public List<Map<String, Object>> adminSearchSuggestionFeedByConditions(Map<String, Object> map) {
        return this.suggestionFeedbackMapper.adminSearchSuggestionFeedByConditions(map);
    }
}
