package com.springmvc.controller.admin.wnk_business_region;

import com.springmvc.entity.AboutUs;
import com.springmvc.entity.WnkBusinessRegion;
import com.springmvc.service.impl.WnkBusinessRegionService;
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
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/30 15:40
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminWnkBusinessRegionController {

    @Resource
    private WnkBusinessRegionService wnkBusinessRegionService;

    /**
     *
     * 功能描述: 进入店铺区域管理界面
     *
     * @param  request  HttpServletRequest
     * @return 返回管理员管理页面
     * @date   2018/10/22 0022 16:28
     */
    @RequestMapping(value = "/joinWnkBusinessRegion")
    @ResponseBody
    public ModelAndView joinWnkBusinessRegion(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_region");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 获取所有区域
     *
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/selectWnkBusinessRegionAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectWnkBusinessRegionAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else{
                List<Map<String,Object>> list = this.wnkBusinessRegionService.selectWnkBusinessRegionAll();
                if (list.size() > 0){
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
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
     * 功能描述: 根据条件查询商户区域管理
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页
     * @param   name        名称
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/21 0021 10:47
     */
    @RequestMapping(value = "/adminSearchWnkBusinessRegionInfoByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchWnkBusinessRegionInfoByConditions(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Integer limit,
                                                               Integer page,
                                                               String name){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("后台暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index",limit * (page - 1));
                map.put("limit",limit);
                map.put("page",page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%"+name+"%");
                }
                List<Map<Object,Object>> list = this.wnkBusinessRegionService.adminSearchWnkBusinessRegionInfoByConditions(map);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据!");
                }else {
                    result.setData(list);
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

    /**
     *
     * 功能描述: 新增区域
     *
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/insertWnkBusinessRegion", method = RequestMethod.POST)
    @ResponseBody
    public Result insertWnkBusinessRegion(HttpServletRequest request, WnkBusinessRegion wnkBusinessRegion){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else{
                if (this.wnkBusinessRegionService.insertWnkBusinessRegion(wnkBusinessRegion) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("新增成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("新增失败");
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
     * 功能描述: 更新区域
     *
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/updateWnkBusinessRegion", method = RequestMethod.POST)
    @ResponseBody
    public Result updateWnkBusinessRegion(HttpServletRequest request,WnkBusinessRegion wnkBusinessRegion){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else{
                if (this.wnkBusinessRegionService.updateWnkBusinessRegion(wnkBusinessRegion) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("更新失败");
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
     * 功能描述: 删除区域
     *
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 1:52 AM
     */
    @RequestMapping(value = "/deleteWnkBusinessRegion", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteWnkBusinessRegion(HttpServletRequest request,Integer region_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else{
                if (this.wnkBusinessRegionService.deleteWnkBusinessRegion(region_id) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("删除成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("删除失败");
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
