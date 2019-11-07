package com.springmvc.controller.app.withdraw_setting;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WithdrawSetting;
import com.springmvc.service.impl.AboutUsService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WithdrawSettingService;
import com.springmvc.utils.LoginSessionCheckUtil;
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

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/30 11:47
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class AppWithdrawSettingController {

    @Resource
    private UsersService usersService;

    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 提现业务层
    @Resource
    private WithdrawSettingService withdrawSettingService;

    /**
     *
     * 功能描述: 查询指定设置
     *
     * @param   withdraw_type    更新或修改类型 0-商家 1-用户 2-用户端平台积分
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    12:25 2018/12/22
     */
    @RequestMapping(value = "/selectWithdrawSetting", method = RequestMethod.POST)
    @ResponseBody
    public Result selectWithdrawSetting(HttpServletRequest request, HttpServletResponse response, Integer user_id,String  withdraw_type){
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
                    WithdrawSetting withdrawSetting =  this.withdrawSettingService.adminSelectWithdrawSetting(withdraw_type);
                    if (withdrawSetting != null){
                        result.setData(withdrawSetting);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("暂未设置");
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
