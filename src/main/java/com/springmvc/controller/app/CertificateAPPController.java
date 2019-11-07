package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersCertificatesService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WnkStoreInformationService;
import com.springmvc.utils.BusinessUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class CertificateAPPController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersCertificatesService usersCertificatesService;

    // 查询用户的用户的证书
    @RequestMapping(value = "/getUserCertificates", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserCertificates(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    List<Map<Object,Object>> list = this.usersCertificatesService.selectUserRecord(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> objMap = list.get(index);
                            objMap.put("certificate_photo", ImageToolsController.certificateShowURL+"?imageid="+objMap.get("certificate_photo_id"));
                        }
                        map.put("list",list);
                        result.setData(map);
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


    // 查询所有万能卡商家
    @RequestMapping(value = "/selectBusinesss", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result selectBusinesss(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    Map<Object,Object> map = BusinessUtil.getAllBusiness();

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
}
