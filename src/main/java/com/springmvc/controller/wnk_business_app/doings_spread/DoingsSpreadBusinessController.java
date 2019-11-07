package com.springmvc.controller.wnk_business_app.doings_spread;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.DoingsSpread;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商家推广活动
 *
 * @author 杨新杰
 * @Date 2018/11/2 14:57
 */
@Controller()
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class DoingsSpreadBusinessController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private DoingsSpreadService doingsSpreadService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessSystemMessageService wnkBusinessSystemMessageService;

    // 通过商家ID查询商家发布的活动 - 系统消息
    @RequestMapping(value = "/selectSystemMsgDoingsSpreadById", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result selectSystemMsgDoingsSpreadById(HttpServletRequest request, HttpServletResponse response,Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object,Object>> allUserCanLookList = this.doingsSpreadService.selectSystemMsgDoingsSpreadByIdAll();
                    //只有商家会员可查看的推广消息
                    List<Map<Object,Object>> businessMemeberCanLookList = new ArrayList<>();
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation != null && wnkStoreInformation.getRecommend_business_id() != -1){
                        businessMemeberCanLookList = this.doingsSpreadService.selectSystemMsgDoingsSpreadByRecommend(wnkStoreInformation.getRecommend_business_id());
                    }
                    for (Integer index = 0;index < businessMemeberCanLookList.size();index++){
                        allUserCanLookList.add(businessMemeberCanLookList.get(index));
                    }
                    Collections.sort(allUserCanLookList, new Comparator<Map<Object,Object>>() {
                        @Override
                        public int compare(Map<Object,Object> o1, Map<Object,Object> o2) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                String dateStr1 = (String)o1.get("create_time");
                                String dateStr2 = (String)o2.get("create_time");
                                Date dt1 = sdf.parse(dateStr1);
                                Date dt2 = sdf.parse(dateStr2);
                                if (dt1.getTime() < dt2.getTime()) {
                                    return 1;
                                } else if (dt1.getTime() > dt2.getTime()) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    if (allUserCanLookList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(allUserCanLookList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 通过商家ID获取商家系统消息
    @RequestMapping(value = "/selectWnkBusinessSystemMessageByBusinessId", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result selectWnkBusinessSystemMessageByBusinessId(HttpServletRequest request, HttpServletResponse response,Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkBusinessSystemMessageService.selectBusinessAllMessage(business_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 通过商家ID查询商家发布的活动 - 轮播图-长页
    @RequestMapping(value = "/selectGalleryChangYeDoingsSpreadById", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result selectGalleryChangYeDoingsSpreadById(HttpServletRequest request, HttpServletResponse response,Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object,Object>> allUserCanLookList = this.doingsSpreadService.selectGalleryChangYeDoingsSpreadByIdAll();
                    //只有商家会员可查看的推广消息
                    List<Map<Object,Object>> businessMemeberCanLookList = new ArrayList<>();
                    WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation != null && wnkStoreInformation.getRecommend_business_id() != -1){
                        businessMemeberCanLookList = this.doingsSpreadService.selectGalleryChangYeDoingsSpreadByRecommendBusinessId(wnkStoreInformation.getRecommend_business_id());
                    }
                    for (Integer index = 0;index < businessMemeberCanLookList.size();index++){
                        allUserCanLookList.add(businessMemeberCanLookList.get(index));
                    }
                    for (Integer index = 0;index < allUserCanLookList.size();index++){
                        Map<Object,Object> map = allUserCanLookList.get(index);
                        String lunboPhoto = (String)map.get("gallery_img");
                        String changtu = (String)map.get("gallery_content_img");
                        if (lunboPhoto != null && !lunboPhoto.equals("")){
                            map.put("gallery_img", ImageToolsController.imageShowURL+"?imageid="+lunboPhoto);
                        }
                        if (changtu != null && !changtu.equals("")){
                            map.put("gallery_content_img", ImageToolsController.imageShowURL+"?imageid="+changtu);
                        }
                    }
                    if (allUserCanLookList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(allUserCanLookList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 通过商家ID查询商家发布的活动
    @RequestMapping(value = "/getBusinessIdAdById", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getBusinessIdAdById(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object,Object>> list = this.doingsSpreadService.getBusinessIdAdById(business_id,type);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商家不存在");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    // 增加活动
    @RequestMapping(value = "/insertDoingsSpread", method = RequestMethod.POST)
    @ResponseBody
    public Result insertBusinessIdDoingsSpread(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Integer business_id,
                                               DoingsSpread doingsSpread,
                                               Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    int i = 0;
                    if (type == 0){
                        i = doingsSpreadService.insertDoingsSpreadLunBoChangTu(doingsSpread);
                    } else if (type == 1){
                        i = doingsSpreadService.insertDoingsSpreadLunBoZhuye(doingsSpread);
                    } else {
                        i = doingsSpreadService.insertDoingsSpreadSystemMsg(doingsSpread);
                    }
                    if (i <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("操作失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("操作成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 获取未读数量 - 推广活动
    @RequestMapping(value = "/getAdUnread", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getAdUnread(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    Map<String,Object> map = this.doingsSpreadService.getAdUnread(business_id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取未读数量 - 系统消息
    @RequestMapping(value = "/getSystemMsg", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getSystemMsg(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    Map<String,Object> map = this.doingsSpreadService.getSystemMsg(business_id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 标记未读消息为已读状态
    @RequestMapping(value = "/updateReadByBusinessId", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result updateReadByBusinessId(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    //所有会员和商家均可查看的推广消息
                    int i = this.doingsSpreadService.updateReadByBusinessId(business_id);
                    result.setData(i);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 更新未读数量 - 推广活动
    @RequestMapping(value = "/updateAdMsgUnread", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result updateAdMsgUnread(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    // 获取所有未读消息
                    List<Map<String,Object>> list = this.doingsSpreadService.selectDoingsSpreadReadFalseByAd(business_id);
                    for (Map<String,Object> map : list){
                        if (map.get("message_id") == null){
                            this.doingsSpreadService.UpdateDoingsSpreadReadTrue(map.get("id").toString(),wnkBusinessAccount.getId().toString(),"1");
                        }
                    }
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 更新未读数量 - 系统消息
    @RequestMapping(value = "/updateSystemMsgUnread", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result updateSystemMsgUnread(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    // 获取所有未读消息
                    List<Map<String,Object>> list = this.doingsSpreadService.selectSystemMsgReadFalseByAd(business_id);
                    for (Map<String,Object> map : list){
                        if (map.get("message_id") == null) {
                            this.doingsSpreadService.UpdateDoingsSpreadReadTrue(map.get("id").toString(), wnkBusinessAccount.getId().toString(), "0");
                        }
                    }
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }




}
