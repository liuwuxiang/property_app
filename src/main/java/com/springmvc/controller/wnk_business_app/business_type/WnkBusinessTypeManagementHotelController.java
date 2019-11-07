package com.springmvc.controller.wnk_business_app.business_type;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessTypeManagementHotel;
import com.springmvc.service.WnkBusinessTypeManagementHotelService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
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
 * 功能描述:酒店类商家房态管理控制器
 *
 * @author  杨新杰
 * @date 2019/1/7 14:27
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessTypeManagementHotelController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessTypeManagementHotelService wnkBusinessTypeManagementHotelService;

    /**
     *
     * 方法说明: 插入或者更新房态管理
     *
     * @author 杨新杰
     * @Date  2019/1/22
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return com.springmvc.utils.Result
     **/
    @RequestMapping(value = "/insertOrUpdateHotelManagement", method = RequestMethod.POST)
    @ResponseBody
    public Result insertOrUpdateHotelManagement(HttpServletRequest request, HttpServletResponse response, WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(wnkBusinessTypeManagementHotel.getBusiness_id(),3,request,response,this.sessionIdsService) == null){
                return  new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessTypeManagementHotel.getBusiness_id());
            if (wnkBusinessAccount == null){
                return  new Result(Result.FAIL,"此商家不存在",null);
            }

            // 查询是否存在设置过的数据
            // 先把时间戳去除时分秒
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            date.setTime(wnkBusinessTypeManagementHotel.getTime_stamp());
            String format = sdf.format(date);
            wnkBusinessTypeManagementHotel.setTime_stamp(new Date(format).getTime());

            int i;
            // 查询是否有对应记录
            WnkBusinessTypeManagementHotel hotelManagement = this.wnkBusinessTypeManagementHotelService.selectHotelManagement(wnkBusinessTypeManagementHotel);
            if(hotelManagement != null){
                i = this.wnkBusinessTypeManagementHotelService.updateHotelManagement(wnkBusinessTypeManagementHotel);
            } else {
                i = this.wnkBusinessTypeManagementHotelService.insertHotelManagement(wnkBusinessTypeManagementHotel);
            }
            if (i > 0){
                return  new Result(Result.SUCCESS,"操作成功",null);
            } else {
                return  new Result(Result.FAIL,"操作失败",null);
            }
        }catch (Exception e){
            return  new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }


}
