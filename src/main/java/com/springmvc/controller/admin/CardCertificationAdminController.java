package com.springmvc.controller.admin;

import com.springmvc.entity.UserCardAuthentication;
import com.springmvc.entity.UserOwnerAuthentication;
import com.springmvc.service.impl.UserCardAuthenticationService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class CardCertificationAdminController {
    @Resource
    private UserCardAuthenticationService userCardAuthenticationService;

    /**
     *
     * 功能描述: 进入车主认证管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:12
     */
    @RequestMapping(value = "/cardCertificationManager")
    @ResponseBody
    public ModelAndView owerCertificationManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/card_certification/card_certification");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有车主认证信息
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:12
     */
    @RequestMapping(value = "/getAllCardCertification", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllCardCertification(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.userCardAuthenticationService.getAllCardAuthenticationAdmin();
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
     * 功能描述:根据条件查询车主认证信息
     *
     * @param   request         请求
     * @param   response        响应
     * @param   limit           分页条目数
     * @param   card_number     车牌号
     * @param   mobile          手机号码
     * @param   real_name       真实姓名
     * @param   state           审核状态
     * @param   submit_date_str 提交时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:27
     */
    @RequestMapping(value = "/adminSearchCardCertificationByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchCardCertificationByConditions(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Integer limit,
                                                            Integer page,
                                                            String card_number,
                                                            String mobile,
                                                            String real_name,
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
                map.put("card_number","%"+ card_number+"%");
                map.put("mobile","%"+ mobile+"%");
                map.put("real_name", "%"+real_name+"%");
                map.put("state", ("".equals(state) ? null : state));
                map.put("submit_date_str", submit_date_str);
                List<Map<String, Object>> List = this.userCardAuthenticationService.adminSearchCardCertificationByConditions(map);
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
     * 功能描述: 车主认证信息审核通过/不通过(state=1认证通过,state=2认证不通过)
     *
     * @param   request, response, record_id, state
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 14:32
     */
    @RequestMapping(value = "/setCardCertificationPass", method = RequestMethod.POST,params = {"record_id","state"})
    @ResponseBody
    public Result setCardCertificationPass(HttpServletRequest request, HttpServletResponse response,Integer record_id,Integer state){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (state != 1 && state != 2){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    UserCardAuthentication userCardAuthentication = this.userCardAuthenticationService.selectById(record_id);
                    if (userCardAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        if (userCardAuthentication.getState() != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此记录已审核");
                        }
                        else{
                            userCardAuthentication.setState(state);
                            userCardAuthentication.setAuthentication_finish_date(new Date());
                            int updateState = this.userCardAuthenticationService.updateState(userCardAuthentication);
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

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
