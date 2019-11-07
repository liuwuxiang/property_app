package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.SuggestionFeedback;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.SuggestionFeedbackService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class SuggestionFeedbackWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private SuggestionFeedbackService suggestionFeedbackService;

    // 获取店铺信息
    @RequestMapping(value = "/userFeedBack", method = RequestMethod.POST,params = {"business_id","content","photos"})
    @ResponseBody
    public Result userFeedBack(HttpServletRequest request, HttpServletResponse response, Integer business_id, String content,String photos){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (content == null || content.equals("")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入反馈内容");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    if (photos == null || photos.equals("")){
                        photos = "";
                    }
                    SuggestionFeedback suggestionFeedback = new SuggestionFeedback();
                    suggestionFeedback.setContent(content);
                    suggestionFeedback.setUser_id(business_id);
                    suggestionFeedback.setPhotos(photos);
                    suggestionFeedback.setUser_type(1);
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
