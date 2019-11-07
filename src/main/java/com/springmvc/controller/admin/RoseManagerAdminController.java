package com.springmvc.controller.admin;

import com.springmvc.entity.AboutUs;
import com.springmvc.service.impl.AboutUsService;
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
 * @author: zhangfan
 * @Date: 2018/11/22 01:51
 * @Description:后台玫瑰管理控制器
 */
@Controller
@RequestMapping(value = "/admin")
public class RoseManagerAdminController {
    @Resource
    private AboutUsService aboutUsService;

    /**
     *
     * 功能描述: 进入玫瑰兑换设置管理页面
     *
     * @param type 设置类型(type=0:设置商家玫瑰兑换信息,type=1设置用户玫瑰兑换信息)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/joinRoseExchangeSetPage",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView joinRoseExchangeSetPage(HttpServletRequest request,Integer type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/rose_exchange/rose_exchange");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取玫瑰兑换设置数据
     *
     * @param type 设置类型(type=0:设置商家玫瑰兑换信息,type=1设置用户玫瑰兑换信息)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/getRoseExchangeSetData", method = RequestMethod.POST,params = {"type"})
    @ResponseBody
    public Result getRoseExchangeSetData(HttpServletRequest request, HttpServletResponse response,Integer type){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }
            else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                //兑换比例type值
                Integer biliType = -1;
                //兑换时间限制Type值
                Integer dateType = -1;
                //兑换最低数量Type值
                Integer minNumberType = -1;
                if (type == 0){
                    biliType = 13;
                    dateType = 17;
                    minNumberType = 15;
                }
                else{
                    biliType = 14;
                    dateType = 18;
                    minNumberType = 16;
                }
                AboutUs biliAbout = this.aboutUsService.selectIntegralAbout(biliType);
                AboutUs dateAbout = this.aboutUsService.selectIntegralAbout(dateType);
                AboutUs minNumberAbout = this.aboutUsService.selectIntegralAbout(minNumberType);
                Map<Object,Object> map = new HashMap<Object,Object>();
                if (biliAbout == null){
                    map.put("bili","");
                }
                else{
                    map.put("bili",biliAbout.getContent());
                }
                if (dateAbout == null){
                    map.put("start_day","");
                    map.put("end_day","");
                }
                else{
                    String[] startAndEndDay = dateAbout.getContent().split("-");
                    if (startAndEndDay.length < 2){
                        map.put("start_day","");
                        map.put("end_day","");
                    }
                    else{
                        map.put("start_day",startAndEndDay[0]);
                        map.put("end_day",startAndEndDay[1]);
                    }
                }
                if (minNumberAbout == null){
                    map.put("min_number","");
                }
                else{
                    map.put("min_number",minNumberAbout.getContent());
                }
                result.setData(map);
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
     * 功能描述: 设置玫瑰兑换设置数据
     *
     * @param type 设置类型(type=0:设置商家玫瑰兑换信息,type=1设置用户玫瑰兑换信息)
     * @param bili 兑换比例
     * @param start_date 可兑换开始日期
     * @param end_date 可兑换结束日期
     * @param min_number 允许的最低兑换量
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/setRoseExchangeSetData", method = RequestMethod.POST,params = {"type","bili","start_date","end_date","min_number"})
    @ResponseBody
    public Result setRoseExchangeSetData(HttpServletRequest request, HttpServletResponse response,Integer type,Integer bili,Integer start_date,Integer end_date,Integer min_number){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }
            else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else if (bili == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入兑换比例");
            }
            else if (bili <= 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("兑换比例不可小于等于0");
            }
            else if (start_date == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入兑换开始时间");
            }
            else if (start_date < 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("兑换开始时间不可小于");
            }
            else if (end_date == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入兑换结束时间");
            }
            else if (end_date > 30){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("兑换结束时间不可大于30");
            }
            else if (min_number == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入允许最低兑换量");
            }
            else if (min_number <= 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("最低兑换量不可小于等于0");
            }
            else {
                //兑换比例type值
                Integer biliType = type == 0?13:14;
                //兑换时间限制Type值
                Integer dateType = type == 0?17:18;
                //兑换最低数量Type值
                Integer minNumberType = type == 0?15:16;
                AboutUs biliAbout = this.aboutUsService.selectIntegralAbout(biliType);
                AboutUs dateAbout = this.aboutUsService.selectIntegralAbout(dateType);
                AboutUs minNumberAbout = this.aboutUsService.selectIntegralAbout(minNumberType);
                if (biliAbout == null){
                    biliAbout = new AboutUs();
                    biliAbout.setType(biliType);
                    biliAbout.setContent(bili.toString());

                    this.aboutUsService.addRecord(biliAbout);
                }
                else{
                    biliAbout.setType(biliType);
                    biliAbout.setContent(bili.toString());
                    this.aboutUsService.updateRecordInformation(biliAbout);
                }

                if (dateAbout == null){
                    dateAbout = new AboutUs();
                    dateAbout.setType(dateType);
                    dateAbout.setContent(start_date.toString()+"-"+end_date.toString());
                    this.aboutUsService.addRecord(dateAbout);
                }
                else{
                    dateAbout.setType(dateType);
                    dateAbout.setContent(start_date.toString()+"-"+end_date.toString());
                    this.aboutUsService.updateRecordInformation(dateAbout);
                }

                if (minNumberAbout == null){
                    minNumberAbout = new AboutUs();
                    minNumberAbout.setType(minNumberType);
                    minNumberAbout.setContent(min_number.toString());
                    this.aboutUsService.addRecord(minNumberAbout);
                }
                else{
                    minNumberAbout.setType(minNumberType);
                    minNumberAbout.setContent(min_number.toString());
                    this.aboutUsService.updateRecordInformation(minNumberAbout);
                }

                result.setData("");
                result.setStatus(Result.SUCCESS);
                result.setMsg("保存成功");

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
