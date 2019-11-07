package com.springmvc.controller.app;

import com.springmvc.entity.UserSecurityQuestion;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.SecurityQuestionService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UserSecurityQuestionService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class SecurityQuestionAppControler {
    @Resource
    private UsersService usersService;
    @Resource
    private SecurityQuestionService securityQuestionService;
    @Resource
    private UserSecurityQuestionService userSecurityQuestionService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 获取所有可供选择的密保问题
    @RequestMapping(value = "/getAllSecurityQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllSecurityQuestion(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            Map<Object,Object> map = new HashMap<Object,Object>();
            List<Map<Object,Object>> list = this.securityQuestionService.selectAllSecurityQuestion();
            if (list.size() <= 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂无数据");
            }
            else{
                map.put("list",list);
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("获取成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 获取用户的密保问题(code-验证码)
    @RequestMapping(value = "/getUserSecurityQuestion", method = RequestMethod.POST,params = {"code","user_id"})
    @ResponseBody
    public Result getUserSecurityQuestion(HttpServletRequest request, HttpServletResponse response, String code,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (!code.equals("123456")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("验证码不正确");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                UserSecurityQuestion userSecurityQuestion = this.userSecurityQuestionService.selectUserSecurityQuestion(user_id);
                if (userSecurityQuestion == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("未设置密保问题");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("one_security_question_id",userSecurityQuestion.getOne_security_question_id());
                    map.put("one_security_question_answer",userSecurityQuestion.getOne_security_question_answer());
                    map.put("two_security_question_id",userSecurityQuestion.getTwo_security_question_id());
                    map.put("two_security_question_answer",userSecurityQuestion.getTwo_security_question_answer());
                    map.put("three_security_question_id",userSecurityQuestion.getThree_security_question_id());
                    map.put("three_security_question_answer",userSecurityQuestion.getThree_security_question_answer());
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 设置用户的密保问题(code-验证码)
    @RequestMapping(value = "/setUserSecurityQuestion", method = RequestMethod.POST,params = {"code","user_id","one_security_question_id","one_security_question_answer","two_security_question_id","two_security_question_answer","three_security_question_id","three_security_question_answer"})
    @ResponseBody
    public Result setUserSecurityQuestion(HttpServletRequest request, HttpServletResponse response, String code,Integer user_id,Integer one_security_question_id, String one_security_question_answer, Integer two_security_question_id, String two_security_question_answer, Integer three_security_question_id, String three_security_question_answer){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (code.equals("")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("验证码不正确");
            }
            else {
                //操作类型(0-添加,1-修改)
                Integer operationType = 1;
                Users users = this.usersService.selectById(user_id);
                UserSecurityQuestion userSecurityQuestion = this.userSecurityQuestionService.selectUserSecurityQuestion(user_id);
                if (userSecurityQuestion == null){
                    userSecurityQuestion = new UserSecurityQuestion();
                    operationType = 0;
                }
                userSecurityQuestion.setOne_security_question_id(one_security_question_id);
                userSecurityQuestion.setOne_security_question_answer(one_security_question_answer);
                userSecurityQuestion.setTwo_security_question_id(two_security_question_id);
                userSecurityQuestion.setTwo_security_question_answer(two_security_question_answer);
                userSecurityQuestion.setThree_security_question_id(three_security_question_id);
                userSecurityQuestion.setThree_security_question_answer(three_security_question_answer);
                userSecurityQuestion.setUser_id(user_id);
                Integer updateState = 0;
                if (operationType == 0){
                    updateState = this.userSecurityQuestionService.addUserSecurityQuestion(userSecurityQuestion);
                }
                else{
                    updateState = this.userSecurityQuestionService.updateUserSecurityQuestion(userSecurityQuestion);
                }
                result.setData("");
                result.setStatus(updateState >= 1?Result.SUCCESS:Result.FAIL);
                result.setMsg(updateState >= 1?"设置成功":"设置失败");

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("设置失败");
        }
        return result;
    }


}
