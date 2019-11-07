package com.springmvc.controller.wnk_business_app.commodities;


import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkCommoditiesService;
import com.springmvc.service.impl.WnkCommoditySpecificationsService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.*;

/**
 *
 * 万能卡商品管理接口层(商家)
 *
 *
 * @author 杨新杰
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkCommoditiesBusinessController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;

    /**
     *
     * 方法说明:获取商家所有商品信息(不包括禁用和删除的)
     *
     * @author 杨新杰
     * @Date  2019/1/21
     * @Param [request, response, business_id, type]
     * @return com.springmvc.utils.Result
     **/
    @RequestMapping(value = "/selectCommoditiesInfoByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCommoditiesInfoByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer businessId){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId,3,request,response,this.sessionIdsService) == null){
                return  new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            if (wnkBusinessAccount == null){
                return  new Result(Result.FAIL,"此商家不存在",null);
            }

            List<Map<String, Object>> maps = this.wnkCommoditiesService.selectCommoditiesInfoAllExcludeDelAndUnderById(businessId);
            if (maps.size() > 0 ){
                return  new Result(Result.SUCCESS,"查询成功",maps);
            } else {
                return  new Result(Result.FAIL,"暂无商品",null);
            }
        }catch (Exception e){
            return  new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }

    /**
     *
     * 方法说明:查询商品库存 根据商品ID 排序(排除删除和下架的商品)
     *
     * @author 杨新杰
     * @Date  2019/1/21
     * @Param [request, response, businessId]
     * @return com.springmvc.utils.Result
     **/
    @RequestMapping(value = "/selectCommoditiesInventoryByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCommoditiesInventoryByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer businessId,Integer start_time_stamp){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId,3,request,response,this.sessionIdsService) == null){
                return  new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            if (wnkBusinessAccount == null){
                return  new Result(Result.FAIL,"此商家不存在",null);
            }

            List<Map<String, Object>> maps = this.wnkCommoditiesService.selectCommoditiesInfoAllExcludeDelAndUnderById(businessId);
            if (maps.size() <= 0 ){
                return  new Result(Result.FAIL,"暂无商品",null);
            }
            // 用作返回的list
            List<List<Object>> returnList = new LinkedList<>();
            // 获取当前时间
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH,start_time_stamp);

            // 获取是否是本月
            Calendar newClendar = Calendar.getInstance();
            // 获取本月有多少天
            int actualMaximum = calendar.getActualMaximum(Calendar.DATE);

            // 判断是否是当前月份
            if (newClendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)){
                actualMaximum = actualMaximum - calendar.get(Calendar.DATE) + 1;
            } else if ((newClendar.get(Calendar.MONTH) + 6) == calendar.get(Calendar.MONTH)){
                // 获取是否是第六个月?
                actualMaximum = calendar.get(Calendar.DATE) - 1;
            } else {
                // 把日期设置为当月一号
                calendar.set(Calendar.DATE,1);
                calendar.set(Calendar.MONTH,start_time_stamp);
                actualMaximum = calendar.getActualMaximum(Calendar.DATE);
            }

            Date date = calendar.getTime();

            // 每次查询一个月
            for (int i = 0 ;i < actualMaximum ; i++){
                List<Object> dayList = new LinkedList<>();
                for (Map<String, Object> map : maps){
                    Map<Object, Object> commoditiesInventoryMap = this.wnkCommoditySpecificationsService.selectCommoditiesInventoryByDateAndCommoditiesId(date.getTime(), (Integer) map.get("id"),(Integer) map.get("business_id"));
                    dayList.add(commoditiesInventoryMap.get("inventory"));
                }
                returnList.add(dayList);
                calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
                date = calendar.getTime();
                String daystrs = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);
            }
            return  new Result(Result.SUCCESS,"查询成功",returnList);
        }catch (Exception e){
            return  new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }


}
