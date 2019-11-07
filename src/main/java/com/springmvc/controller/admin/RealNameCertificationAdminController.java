package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.UserCardAuthentication;
import com.springmvc.entity.UserIdCardAuthentication;
import com.springmvc.service.impl.UserIdCardAuthenticationService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class RealNameCertificationAdminController {
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;

    /**
     *
     * 功能描述: 进入实名认证管理界面
     *
     * @param   request 请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:41
     */
    @RequestMapping(value = "/realNameCertificationManager")
    @ResponseBody
    public ModelAndView realNameCertificationManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/real_name_certification/real_name_certification");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入实名认证详情界面
     *
     * @param   request     请求
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:40
     */
    @RequestMapping(value = "/realNameCertificationDetail",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView realNameCertificationDetail(HttpServletRequest request,Integer record_id){
        
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/real_name_certification/real_name_certification_message");
            UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectById(record_id);
            if (userIdCardAuthentication == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("mobile","");
                modelAndView.addObject("real_name","");
                modelAndView.addObject("id_card_number","");
                modelAndView.addObject("card_effective_deadline","");
                modelAndView.addObject("submit_date","");
                modelAndView.addObject("state","");
                modelAndView.addObject("handheld_identity_card_photo","");
            }
            else{
                modelAndView.addObject("record_id",userIdCardAuthentication.getId());
                modelAndView.addObject("mobile",userIdCardAuthentication.getMobile());
                modelAndView.addObject("real_name",userIdCardAuthentication.getReal_name());
                modelAndView.addObject("id_card_number",userIdCardAuthentication.getId_card_number());
                modelAndView.addObject("handheld_identity_card_photo", ImageToolsController.imageShowURL+"?imageid="+userIdCardAuthentication.getHandheld_identity_card_photo());
                if (userIdCardAuthentication.getState() == 0){
                    modelAndView.addObject("state","待审核");
                }
                else if (userIdCardAuthentication.getState() == 1){
                    modelAndView.addObject("state","认证通过");
                }
                else{
                    modelAndView.addObject("state","认证不通过");
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                modelAndView.addObject("card_effective_deadline",formatter.format(userIdCardAuthentication.getCard_effective_deadline()));
                modelAndView.addObject("submit_date",formatter.format(userIdCardAuthentication.getSubmit_date()));

            }
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 获取所有身份证认证信息
     *
     * @param   request  请求
     * @param   response 响应
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:28
     */
    @RequestMapping(value = "/getAllIdCardCertification", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIdCardCertification(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.userIdCardAuthenticationService.getAllIdCardAuthenticationAdmin();
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
     * 功能描述: 通过条件查询实名认证信息
     *
     * @param   request                 请求
     * @param   response                响应
     * @param   limit                   分页条目数
     * @param   page                    分页数
     * @param   mobile                  电话号码
     * @param   real_name               真实姓名
     * @param   id_card_number          身份证号
     * @param   card_effective_deadline 身份证有效截止日期
     * @param   state                   状态
     * @param   submit_date_str         提交日期
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:18
     */
    @RequestMapping(value = "/adminSearchRealNameCertificationByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchRealNameCertificationByConditions(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Integer limit,
                                                           Integer page,
                                                           String mobile,
                                                           String real_name,
                                                           String id_card_number,
                                                           String card_effective_deadline,
                                                           Integer state,
                                                           String submit_date_str
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
                if (mobile != null && !"".equals(mobile)){
                    map.put("mobile", "%"+mobile+"%");
                }
                if (real_name != null && !"".equals(real_name)) {
                    map.put("real_name", "%" + real_name + "%");
                }
                if (id_card_number != null && !"".equals(id_card_number)) {
                    map.put("id_card_number", "%"+id_card_number+"%");
                }
                if (card_effective_deadline != null && !"".equals(card_effective_deadline)) {
                    map.put("card_effective_deadline", card_effective_deadline);
                }
                map.put("state", state);
                map.put("submit_date_str", submit_date_str);
                List<Map<String, Object>> List = this.userIdCardAuthenticationService.adminSearchRealNameCertificationByConditions(map);
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
     * 功能描述: 身份证认证信息审核通过/不通过(state=1认证通过,state=2认证不通过)
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @param   state
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:43
     */
    @RequestMapping(value = "/setIdCardCertificationPass", method = RequestMethod.POST, params = {"record_id", "state"})
    @ResponseBody
    public Result setIdCardCertificationPass(HttpServletRequest request, HttpServletResponse response, Integer record_id, Integer state) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                if (state != 1 && state != 2) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                } else {
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectById(record_id);
                    if (userIdCardAuthentication == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    } else {
                        if (userIdCardAuthentication.getState() != 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此记录已审核");
                        } else {
                            userIdCardAuthentication.setState(state);
                            userIdCardAuthentication.setAuthentication_finish_date(new Date());
                            int updateState = this.userIdCardAuthenticationService.updateState(userIdCardAuthentication);
                            if (updateState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("更新失败");
                            } else {
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("更新成功");
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
