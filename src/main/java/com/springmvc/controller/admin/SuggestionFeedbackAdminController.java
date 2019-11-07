package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.SuggestionFeedback;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.SuggestionFeedbackService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class SuggestionFeedbackAdminController {
    @Resource
    private SuggestionFeedbackService suggestionFeedbackService;
    @Resource
    private UsersService usersService;

    /**
     *
     * 功能描述:进入建议反馈管理界面
     *
     * @param   request         请求
     * @return: ModelAndView    建议反馈管理界面
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:24
     */
    @RequestMapping(value = "/suggestionFeedbackManager")
    @ResponseBody
    public ModelAndView suggestionFeedbackManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/suggestion_feedback/suggestion_feedback");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入建议查看界面
     *
     * @param   request record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:26
     */
    @RequestMapping(value = "/suggestionFeedbackDetail",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView suggestionFeedbackDetail(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/suggestion_feedback/suggestion_feedback_detail");
            SuggestionFeedback suggestionFeedback = this.suggestionFeedbackService.selectById(record_id);
            if (suggestionFeedback == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("name","");
                modelAndView.addObject("mobile","");
                modelAndView.addObject("submit_date","");
                modelAndView.addObject("state","");
                modelAndView.addObject("remark","");
                modelAndView.addObject("content","");
            }
            else{
                Users users = this.usersService.selectById(suggestionFeedback.getUser_id());
                modelAndView.addObject("record_id",suggestionFeedback.getId());
                modelAndView.addObject("name",users.getNick_name());
                modelAndView.addObject("mobile",users.getMobile());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                modelAndView.addObject("submit_date",formatter.format(suggestionFeedback.getFeedback_date()));
                if (suggestionFeedback.getState() == 0){
                    modelAndView.addObject("state","待处理");
                }
                else if (suggestionFeedback.getState() == 1){
                    modelAndView.addObject("state","处理中");
                }
                else{
                    modelAndView.addObject("state","完成");
                }
                modelAndView.addObject("remark",suggestionFeedback.getRemark());
                modelAndView.addObject("content",suggestionFeedback.getContent());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入建议处理界面
     *
     * @param   request, record_id
     * @return: ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:26
     */
    @RequestMapping(value = "/dealSuggestionFeedback",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView dealSuggestionFeedback(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/suggestion_feedback/deal_suggestion_feedback");
            SuggestionFeedback suggestionFeedback = this.suggestionFeedbackService.selectById(record_id);
            if (suggestionFeedback == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("state",0);
                modelAndView.addObject("remark","");
            }
            else{
                modelAndView.addObject("record_id",suggestionFeedback.getId());
                modelAndView.addObject("state",suggestionFeedback.getState());
                modelAndView.addObject("remark",suggestionFeedback.getRemark());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有反馈信息
     *
     * @param   request, response
     * @return: Result
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:27
     */
    @RequestMapping(value = "/getAllSuggestionFeed", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllSuggestionFeed(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.suggestionFeedbackService.selectAllAdmin();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询反馈信息
     *
     * @param   request, response, limit, page, user_name, user_mobile, content, feedback_date, state, remark
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 16:08
     */
    @RequestMapping(value = "/adminSearchSuggestionFeedByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchSuggestionFeedByConditions(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String user_name,
                                                      String user_mobile,
                                                      String content,
                                                      String feedback_date,
                                                      Integer state,
                                                      String remark
    ){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                map.put("user_name", "%"+user_name+"%");
                map.put("user_mobile", user_mobile);
                map.put("content", "%"+content+"%");
                map.put("feedback_date", feedback_date);
                map.put("state",("".equals(state) || state == null) ? null : state);
//                map.put("state",state);
                map.put("remark", remark);
                List<Map<String, Object>> List = this.suggestionFeedbackService.adminSearchSuggestionFeedByConditions(map);
                result.setData(List);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取反馈信息的图片
     *
     * @param   request, response, record_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:28
     */
    @RequestMapping(value = "/getFeedPhotos", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result getFeedPhotos(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                SuggestionFeedback suggestionFeedback = this.suggestionFeedbackService.selectById(record_id);
                ArrayList<String> photoList = new ArrayList<String>();
                String photoStr = suggestionFeedback.getPhotos();
                String[] photos = photoStr.substring(1, photoStr.length()).split(",");
                for (Integer index = 0;index < photos.length;index++){
                    String photoId = photos[index];
                    photoList.add(ImageToolsController.imageShowURL+"?imageid="+photoId);
                }
                result.setData(photoList);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 建议反馈处理
     *
     * @param   request, response, record_id, state, remark
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/7 0007 16:28
     */
    @RequestMapping(value = "/feedDealAction", method = RequestMethod.POST,params = {"record_id","state","remark"})
    @ResponseBody
    public Result feedDealAction(HttpServletRequest request, HttpServletResponse response,Integer record_id,Integer state,String remark){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (state != 0 && state != 1 && state != 2){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    SuggestionFeedback suggestionFeedback = this.suggestionFeedbackService.selectById(record_id);
                    if (suggestionFeedback == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        suggestionFeedback.setState(state);
                        suggestionFeedback.setRemark(remark);
                        int updateState = this.suggestionFeedbackService.updateFeedDealMessage(suggestionFeedback);
                        if (updateState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("更新成功");
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
