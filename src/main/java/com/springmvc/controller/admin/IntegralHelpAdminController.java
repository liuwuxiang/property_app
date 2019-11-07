package com.springmvc.controller.admin;

import com.springmvc.entity.IntegralHelp;
import com.springmvc.service.impl.IntegralHelpService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 积分帮助说明后台管理控制器
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 15:07
 */
@Controller
@RequestMapping(value = "/admin")
public class IntegralHelpAdminController {
    @Resource
    private IntegralHelpService integralHelpService;

    /**
     *
     * 功能描述: 进入积分帮助说明管理页面
     *
     * @param type 积分说明类型(0-用户端平台积分、1-用户端商家积分、2-用户端赠送积分、3-商家端等级积分、4-商家端推广积分说明数据,5-商家端玫瑰说明帮助,6-用户端玫瑰说明帮助)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 1:43 AM
     */
    @RequestMapping(value = "/joinIntegralHelpManagerPage",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView joinIntegralHelpManagerPage(HttpServletRequest request,Integer type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral_help/integral_help");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取某个类别的积分帮助说明内容
     *
     * @param type 积分说明类型(0-用户端平台积分、1-用户端商家积分、2-用户端赠送积分、3-商家端等级积分、4-商家端推广积分说明数据,5-商家端玫瑰说明帮助,6-用户端玫瑰说明帮助,7-通用积分)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/11/14 1:45 AM
     */
    @RequestMapping(value = "/getIntegralHelpContentByType", method = RequestMethod.POST,params = {"type"})
    @ResponseBody
    public Result getIntegralHelpContentByType(HttpServletRequest request, HttpServletResponse response,Integer type){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }
            else if (type != 0 && type != 1 && type != 2 && type != 3 && type != 4 && type != 5 && type != 6 && type != 7){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                IntegralHelp integralHelp = this.integralHelpService.selectContentByType(type);
                if (integralHelp == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂未设置此说明");
                }
                else{
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("content",integralHelp.getContent());
                    map.put("open_type",integralHelp.getOpen_type());

                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
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
     * 功能描述: 新增或修改积分帮助说明内容
     *
     * @param type 积分说明类型(0-用户端平台积分、1-用户端商家积分、2-用户端赠送积分、3-商家端等级积分、4-商家端推广积分说明数据,5-商家端玫瑰说明帮助,6-用户端玫瑰说明帮助,7-通用积分)
     * @param content 说明内容
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 1:48 AM
     */
    @RequestMapping(value = "/addOrUpdateIntegralHelpContent", method = RequestMethod.POST,params = {"type","content","open_way"})
    @ResponseBody
    public Result addOrUpdateIntegralHelpContent(HttpServletRequest request, HttpServletResponse response,Integer type,String content,Integer open_way){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }
            else if (type != 0 && type != 1 && type != 2 && type != 3 && type != 4 && type != 5 && type != 6 && type != 7){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                IntegralHelp integralHelp = this.integralHelpService.selectContentByType(type);
                if (integralHelp == null){
                    integralHelp = new IntegralHelp();
                    integralHelp.setContent(content);
                    integralHelp.setType(type);
                    integralHelp.setOpen_type(open_way);

                    int addState = this.integralHelpService.insertTypeContent(integralHelp);
                    if (addState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("保存失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("保存成功");
                    }
                }
                else{
                    integralHelp.setContent(content);
                    integralHelp.setOpen_type(open_way);
                    int updateState = this.integralHelpService.updateTypeContent(integralHelp);
                    if (updateState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("保存失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("保存成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
