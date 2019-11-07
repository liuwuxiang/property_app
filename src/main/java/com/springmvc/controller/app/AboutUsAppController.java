package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.AboutUs;
import com.springmvc.entity.Users;
import com.springmvc.entity.UsersCertificates;
import com.springmvc.entity.WithdrawSetting;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.PhotoSynthesisUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class AboutUsAppController {
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private UsersCertificatesService usersCertificatesService;
    @Resource
    private WithdrawSettingService withdrawSettingService;
    
    
    // 查询关于我们富文本介绍内容
    @RequestMapping(value = "/testC", method = RequestMethod.GET)
    @ResponseBody
    public Result testC(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            List<Map<Object,Object>> list = this.usersService.getAllUsersAdmin();
            for (Integer index = 0;index < list.size();index++){
                Map<Object,Object> map = list.get(index);
                Users users = this.usersService.selectById((Integer) map.get("id"));
                if (users != null){
                    //万能卡二维码
                    Map<Object,Object> wnkQrcodeMap = new HashMap<Object,Object>();
                    wnkQrcodeMap.put("user_id",users.getId());
                    wnkQrcodeMap.put("type",1);
                    JSONObject json = JSONObject.fromObject(wnkQrcodeMap);
                    String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                    String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                    users.setWnk_qrcode(wnkQrcodeName+".png");
                    this.usersService.updateUserQrcode(users);
                }

            }
            result.setData("");
            result.setStatus(Result.SUCCESS);
            result.setMsg("生成成功");

        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("生成失败");
        }
        return result;
    }

    // 查询关于我们富文本介绍内容
    @RequestMapping(value = "/aboutUsData", method = RequestMethod.POST)
    @ResponseBody
    public Result aboutUsData(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            AboutUs aboutUs = this.aboutUsService.selectAboutUs();
            if (aboutUs == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂无介绍");
            }
            else{
                Map<Object,Object> map = new HashMap<Object,Object>();
                map.put("content",aboutUs.getContent());
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

    // 获取通用积分以及消费积分相关说明(type-查询类别,1-消费积分如何使用,2-消费积分如何获得,3-消费积分扣减规则,4-通用积分如何使用,5-通用积分如何获得,6-通用积分扣减规则)
    @RequestMapping(value = "/getIntegralAbout", method = RequestMethod.POST,params = {"type"})
    @ResponseBody
    public Result getIntegralAbout(HttpServletRequest request, HttpServletResponse response,Integer type){
        Result result = new Result();
        try {
            if (type != 1 && type != 2 && type != 3 && type != 4 && type != 5 && type != 6){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else{
                AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(type);
                if (aboutUs == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无介绍");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("content",aboutUs.getContent());
                    result.setData(map);
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


    // 获取银币兑换比例以及人民币兑换比例和提现费率信息
    @RequestMapping(value = "/getSubscriptionRatioInformation", method = RequestMethod.POST)
    @ResponseBody
    public Result getSubscriptionRatioInformation(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            Map<Object,Object> map = new HashMap<Object,Object>();
            //消费积分兑换银币比例
            AboutUs aboutUsXFAndYB = this.aboutUsService.selectIntegralAbout(7);
            //通用积分兑换银币比例
            AboutUs aboutUsTYAndYB = this.aboutUsService.selectIntegralAbout(8);
            //消费积分与人民币兑换比例
            AboutUs aboutUsXFAndRMB = this.aboutUsService.selectIntegralAbout(9);
            //通用积分与人民币兑换比例
            AboutUs aboutUsTYAndRMB = this.aboutUsService.selectIntegralAbout(10);
            //通用积分提现手续费率
            AboutUs aboutUsTYTXFL = this.aboutUsService.selectIntegralAbout(11);
            //允许提现时间段
            AboutUs aboutUsTimeSlot = this.aboutUsService.selectIntegralAbout(12);
            map.put("xfandyb",aboutUsXFAndYB==null?0:Integer.valueOf(aboutUsXFAndYB.getContent()));
            map.put("tyandyb",aboutUsTYAndYB==null?0:Integer.valueOf(aboutUsTYAndYB.getContent()));
            map.put("xfandrmb",aboutUsXFAndRMB==null?0:Integer.valueOf(aboutUsXFAndRMB.getContent()));
            map.put("tyandrmb",aboutUsTYAndRMB==null?0:Integer.valueOf(aboutUsTYAndRMB.getContent()));
//            map.put("tytxfl",aboutUsTYTXFL==null?0:Integer.valueOf(aboutUsTYTXFL.getContent()));
            map.put("tytxfl","1");
            if (aboutUsTimeSlot == null){
                map.put("withdraw_time_slot","不限");
            }
            else if (aboutUsTimeSlot.getContent().equals("1")){
                map.put("withdraw_time_slot","不限");
            }
            else{
                String timeSlot = aboutUsTimeSlot.getContent().replaceAll("月", "");
                map.put("withdraw_time_slot",timeSlot);
            }

            result.setData(map);
            result.setStatus(Result.SUCCESS);
            result.setMsg("获取成功");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 
     *
     * @param   type 获取类型
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    15:42 2018/12/22
     */
    @RequestMapping(value = "/getSubscriptionRatioInformationTwo", method = RequestMethod.POST)
    @ResponseBody
    public Result getSubscriptionRatioInformationTwo(String type){
     
        Result result = new Result();
        try {
            Map<String,Object> map = new HashMap<>();
            WithdrawSetting withdrawSetting = withdrawSettingService.adminSelectWithdrawSetting(type);
            String withdraw_time_slot = "每月"+withdrawSetting.getWithdraw_start_time()+"日至"+withdrawSetting.getWithdraw_end_time()+"日";
            if (withdrawSetting.getIs_any_time() == 1){
                withdraw_time_slot = "不限";
            }
            map.put("withdraw_time_slot",withdraw_time_slot);
            map.put("withdraw_min_number",withdrawSetting.getMin_number());
            map.put("withdraw_get_time",withdrawSetting.getGet_time());
            map.put("tytxfl",withdrawSetting.getService_charge_proportion());
            map.put("tyandrmb",withdrawSetting.getWithdraw_proportion());
            result.setData(map);
            result.setStatus(Result.SUCCESS);
            result.setMsg("获取成功");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }



}
