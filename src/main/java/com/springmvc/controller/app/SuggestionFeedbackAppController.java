package com.springmvc.controller.app;

import com.springmvc.entity.SuggestionFeedback;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.SuggestionFeedbackService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class SuggestionFeedbackAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private SuggestionFeedbackService suggestionFeedbackService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 用户反馈建议
    @RequestMapping(value = "/userFeedBack", method = RequestMethod.POST,params = {"user_id","content","photos"})
    @ResponseBody
    public Result userFeedBack(HttpServletRequest request, HttpServletResponse response, Integer user_id, String content,String photos){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (content == null || content.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入反馈内容");
                }
                else{
                    if (photos == null || photos.equals("")){
                        photos = "";
                    }
                    SuggestionFeedback suggestionFeedback = new SuggestionFeedback();
                    suggestionFeedback.setContent(content);
                    suggestionFeedback.setUser_id(user_id);
                    suggestionFeedback.setPhotos(photos);
                    suggestionFeedback.setUser_type(0);
                    suggestionFeedback.setFeedback_date(new Date());
                    int state = this.suggestionFeedbackService.addFeedback(suggestionFeedback);
                    if (state >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("反馈成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("反馈失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("反馈失败");
        }
        return result;
    }
}
