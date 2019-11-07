package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.WnkBusinessType;
import com.springmvc.service.impl.WnkBusinessTypeService;
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
 *
 * 功能描述: 万能卡分类后台管理控制器
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:41
 */
@Controller
@RequestMapping(value = "/admin")
public class WnkBusinessTypeAdminController {
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;

    /**
     *
     * 功能描述: 进入万能卡分类管理界面
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 3:51 AM
     */
    @RequestMapping(value = "/joinWnkTypeManagerPage")
    @ResponseBody
    public ModelAndView joinWnkTypeManagerPage(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_type/wnk_business_type");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述:进入万能卡分类新增界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2018/12/26 0026 16:40
     */
    @RequestMapping(value = "/addWnkBusinessType")
    @ResponseBody
    public ModelAndView addWnkBusinessType(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_type/add_wnk_business_type");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入万能卡分类修改界面
     *
     * @param type_id 万能卡分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 3:50 AM
     */
    @RequestMapping(value = "/joinWnkTypeSetPage",method = RequestMethod.GET,params = {"type_id"})
    @ResponseBody
    public ModelAndView joinWnkTypeSetPage(HttpServletRequest request,Integer type_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_type/set_wnk_business_type");
            modelAndView.addObject("type_id",type_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 后台查询所有万能卡分类
     *
     * @param:  request  请求
     * @param:  response 响应
     * @return: 返回所有万能卡分类
     * @author: zhangfan
     * @date: 2018/11/14 3:55 AM
     */
    @RequestMapping(value = "/getAllWnkBusinessType", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllWnkBusinessType(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.wnkBusinessTypeService.adminSelectAllRecord();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("photo_url", ImageToolsController.imageShowURL+"?imageid="+map.get("logo_photo_id"));
                    }
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
     * 功能描述: 根据条件查询商家分类管理
     *
     * @param   request                 请求
     * @param   response                响应
     * @param   limit                   页
     * @param   page                    分页
     * @param   name                    分类名称
     * @param   commdity_charge_way     资费方式
     * @param   commodifty_price        会员价格
     * @param   make_wnk_state          万能卡权益
     * @param   discount_type           优惠方式
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 12:12
     */
    @RequestMapping(value = "/adminSearchWnkBusinessType", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchWnkBusinessType(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Integer limit,
                                          Integer page,
                                          String name,
                                          Integer commdity_charge_way,
                                          Double commodifty_price,
                                          Integer make_wnk_state,
                                          Integer discount_type){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                map.put("commdity_charge_way", ("".equals(commdity_charge_way) ? null : commdity_charge_way) );
                if (commodifty_price != null && !"".equals(commodifty_price)){
                    map.put("commodifty_price","%"+commodifty_price+"%");
                }
                map.put("make_wnk_state", ("".equals(make_wnk_state) ? null : make_wnk_state) );
                map.put("discount_type", ("".equals(discount_type) ? null : discount_type) );
                List<Map<Object,Object>> list = this.wnkBusinessTypeService.adminSearchWnkBusinessType(map);

                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> maps = list.get(index);
                        maps.put("photo_url", ImageToolsController.imageShowURL+"?imageid="+maps.get("logo_photo_id"));
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取万能卡商家分类详情
     *
     * @param type_id 商家分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 3:56 AM
     */
    @RequestMapping(value = "/getWnkBusinessTypeDetailAction", method = RequestMethod.POST,params = {"type_id"})
    @ResponseBody
    public Result getWnkBusinessTypeDetailAction(HttpServletRequest request, HttpServletResponse response,Integer type_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);
                if (wnkBusinessType == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此分类不存在");
                }
                else{
                    wnkBusinessType.setLogo_photo_id(ImageToolsController.imageShowURL+"?imageid="+wnkBusinessType.getLogo_photo_id());
                    result.setData(wnkBusinessType);
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
     * 功能描述: 新增万能卡商家分类信息
     *
     * @param type_id 商家分类id
     * @param name 商家分类名称
     * @param logo_photo 商家分类图标
     * @param commdity_charge_way 资费方式(0-按次,1-按年)
     * @param commodifty_price 会员价格
     * @param make_wnk_state 是否执行万能卡权益(0-不执行,1-执行)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2018/12/26 0026 17:42
     */
    @RequestMapping(value = "/insertWnkBusinessInformationAction",method = RequestMethod.POST,params = {"type_id","name","logo_photo","commdity_charge_way","commodifty_price","make_wnk_state","discount_type"})
    @ResponseBody
    public Result insertWnkBusinessInformationAction(HttpServletRequest request, HttpServletResponse response,Integer type_id,String name,String logo_photo,Integer commdity_charge_way,Double commodifty_price,Integer make_wnk_state,Integer discount_type){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WnkBusinessType BusinessType = this.wnkBusinessTypeService.selectById(type_id);
                if (BusinessType == null){
                    WnkBusinessType wnkBusinessType = new WnkBusinessType();
                    wnkBusinessType.setName(name);
                    wnkBusinessType.setCommdity_charge_way(commdity_charge_way);
                    wnkBusinessType.setCommodifty_price(commodifty_price);
                    wnkBusinessType.setMake_wnk_state(make_wnk_state);
                    String photoQZ = ImageToolsController.imageShowURL+"?imageid=";
                    logo_photo = logo_photo.replace(photoQZ,"");
                    wnkBusinessType.setLogo_photo_id(logo_photo);
                    wnkBusinessType.setDiscount_type(discount_type);
                    int addState = this.wnkBusinessTypeService.addWnkBusinessInformationAction(wnkBusinessType);
                    if (addState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("更新失败");
                    }
                    else{
                        result.setData(addState);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("暂未登录");
        }
        return result;
    }

    /**
     *
     * 功能描述: 更新万能卡商家分类信息
     *
     * @param type_id 商家分类id
     * @param name 商家分类名称
     * @param logo_photo 商家分类图标
     * @param commdity_charge_way 资费方式(0-按次,1-按年)
     * @param commodifty_price 会员价格
     * @param make_wnk_state 是否执行万能卡权益(0-不执行,1-执行)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 3:56 AM
     */
    @RequestMapping(value = "/updateWnkBusinessInformationAction", method = RequestMethod.POST,params = {"type_id","name","logo_photo","commdity_charge_way","commodifty_price","make_wnk_state","discount_type"})
    @ResponseBody
    public Result updateWnkBusinessInformationAction(HttpServletRequest request, HttpServletResponse response,Integer type_id,String name,String logo_photo,Integer commdity_charge_way,Double commodifty_price,Integer make_wnk_state,Integer discount_type){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }
            else if(commdity_charge_way != 0 && commdity_charge_way != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else if(make_wnk_state != 0 && make_wnk_state != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else if(discount_type != 0 && discount_type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);
                if (wnkBusinessType == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此分类不存在");
                }
                else{
                    wnkBusinessType.setName(name);
                    wnkBusinessType.setCommdity_charge_way(commdity_charge_way);
                    wnkBusinessType.setCommodifty_price(commodifty_price);
                    wnkBusinessType.setMake_wnk_state(make_wnk_state);
                    String photoQZ = ImageToolsController.imageShowURL+"?imageid=";
                    logo_photo = logo_photo.replace(photoQZ,"");
                    wnkBusinessType.setLogo_photo_id(logo_photo);
                    wnkBusinessType.setDiscount_type(discount_type);
                    int updateState = this.wnkBusinessTypeService.updateWnkTypeInformation(wnkBusinessType);

                    if (updateState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("更新失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新成功");
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 商家分类标记删除
     *
     * @param   request, id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2018/12/30 0030 15:59
     */
    @RequestMapping(value = "/deleteWnkBusinessTypeById", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteWnkBusinessTypeById(HttpServletRequest request, String id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                if (this.wnkBusinessTypeService.deleteWnkBusinessTypeById(id) > 0) {
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("删除成功");
                } else {
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("删除失败");
                }
//                List<Map<String, Object>> list = problemService.selectProblemAll();
//                result.setData(list);
//                result.setStatus(Result.SUCCESS);
////                result.setMsg("查询成功");
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
