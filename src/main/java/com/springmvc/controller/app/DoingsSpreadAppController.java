package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.DoingSpreadUserReadRecord;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.DoingSpreadUserReadRecordService;
import com.springmvc.service.impl.DoingsSpreadService;
import com.springmvc.service.impl.LoginSessionIdsService;
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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/11/3 14:45
 * @Description:活动推广用户端Appcontroller
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class DoingsSpreadAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private DoingsSpreadService doingsSpreadService;
    @Resource
    private DoingSpreadUserReadRecordService doingSpreadUserReadRecordService;

    // 记录用户文本推广信息已读
    @RequestMapping(value = "/userTextDoingsSpreadRead", method = RequestMethod.POST,params = {"user_id","message_id"})
    @ResponseBody
    public Result userTextDoingsSpreadRead(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer message_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                    DoingSpreadUserReadRecord doingSpreadUserReadRecord = this.doingSpreadUserReadRecordService.selectByUserIdAndMessageId(user_id,message_id);
                    if (doingSpreadUserReadRecord == null){
                        doingSpreadUserReadRecord = new DoingSpreadUserReadRecord();
                        doingSpreadUserReadRecord.setUser_id(user_id);
                        doingSpreadUserReadRecord.setMessage_id(message_id);
                        this.doingSpreadUserReadRecordService.insertNewOrder(doingSpreadUserReadRecord);
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 用户查询文本推广信息
    @RequestMapping(value = "/userSelectTextDoingsSpread", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result userSelectTextDoingsSpread(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                    //所有会员和商家均可查看的推广消息
                   List<Map<Object,Object>> allUserCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
                   //只有商家会员可查看的推广消息
                    List<Map<Object,Object>> businessMemeberCanLookList = null;
                    if (users.getRecommend_type() == 1 && users.getRecommend_user_id() != -1){
                        businessMemeberCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(users.getRecommend_user_id());
                        for (Integer index = 0;index < businessMemeberCanLookList.size();index++){
                            allUserCanLookList.add(businessMemeberCanLookList.get(index));
                        }
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
                        for (Integer index = 0;index < allUserCanLookList.size();index++){
                            Map<Object,Object> map = allUserCanLookList.get(index);
                            Long message_id = (Long) map.get("id");
                            DoingSpreadUserReadRecord doingSpreadUserReadRecord = this.doingSpreadUserReadRecordService.selectByUserIdAndMessageId(user_id,message_id.intValue());
                            if (doingSpreadUserReadRecord == null){
                                map.put("read_status",0);
                            }
                            else{
                                map.put("read_status",1);
                            }
                        }
                        result.setData(allUserCanLookList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 用户查询轮播图推广信息
    @RequestMapping(value = "/userSelectImgDoingsSpread", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result userSelectImgDoingsSpread(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object,Object>> allUserCanLookList = this.doingsSpreadService.selectNotDownImgMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
                    //只有商家会员可查看的推广消息
                    List<Map<Object,Object>> businessMemeberCanLookList = null;
                    if (users.getRecommend_type() == 1 && users.getRecommend_user_id() != -1){
                        businessMemeberCanLookList = this.doingsSpreadService.selectNotDownImgMessageAndExaminePassJurisdictionForBusinessMember(users.getRecommend_user_id());
                        for (Integer index = 0;index < businessMemeberCanLookList.size();index++){
                            allUserCanLookList.add(businessMemeberCanLookList.get(index));
                        }
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
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
}
