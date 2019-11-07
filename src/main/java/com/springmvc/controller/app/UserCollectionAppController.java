package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.UserCollection;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UserCollectionService;
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

/**
 * @author: zhangfan
 * @Date: 2018/10/27 15:01
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UserCollectionAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UserCollectionService userCollectionService;

    // 获取用户的关注记录
    @RequestMapping(value = "/getUserCollectionRecord", method = RequestMethod.POST,params = {"user_id","lat","longt"})
    @ResponseBody
    public Result getUserCollectionRecord(HttpServletRequest request, HttpServletResponse response, Integer user_id,Double lat,Double longt){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
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
                    List<Map<Object,Object>> list = this.userCollectionService.selectCollectionByUserId(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            String fm_photo = (String)map.get("banners_photoid");
                            if (fm_photo == null || fm_photo.equals("")){
                                fm_photo = "logo.jpg";
                            }
                            else{
                                String[] sourceStrArray = fm_photo.split(",");
                                if (sourceStrArray.length <= 0){
                                    fm_photo = "logo.jpg";
                                }
                                else{
                                    fm_photo = sourceStrArray[0];
                                }
                            }
                            map.put("fm_photo", ImageToolsController.imageShowURL+"?imageid="+fm_photo);
                            map.put("juli","");
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
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


    // 对商家进行收藏/取消收藏
    @RequestMapping(value = "/collectionOrCancelBusiness", method = RequestMethod.POST,params = {"user_id","business_id"})
    @ResponseBody
    public Result collectionOrCancelBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
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
                    //操作类型(0-收藏,1-取消收藏)
                    Integer optionType = -1;
                    //操作提示前缀
                    String optionMsg = "";
                    //操作状态
                    Integer optionState = 0;
                    List<Map<Object,Object>> list = this.userCollectionService.selectCollectionByUserIdAndBusinessId(user_id, business_id);
                    if (list.size() <= 0){
                        optionType = 0;
                        optionMsg = "收藏";
                        UserCollection userCollection = new UserCollection();
                        userCollection.setBusiness_id(business_id);
                        userCollection.setUser_id(user_id);
                        optionState = this.userCollectionService.addCollection(userCollection);

                    }
                    else{
                        optionType = 1;
                        optionMsg = "取消收藏";
                        optionState = this.userCollectionService.deleteCollection(user_id, business_id);
                    }
                    if (optionState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg(optionMsg+"失败");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("collection_state",optionType);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(optionMsg+"成功");
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
