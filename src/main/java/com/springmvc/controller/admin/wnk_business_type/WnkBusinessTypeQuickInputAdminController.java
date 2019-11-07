package com.springmvc.controller.admin.wnk_business_type;

import com.springmvc.entity.WnkBusinessTypeQuickInput;
import com.springmvc.service.IWnkBusinessTypeQuickInputService;
import com.springmvc.service.impl.WnkBusinessTypeQuickInputServiceImpl;
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
 * 功能描述:万能卡商家规格快捷输入后台控制器
 *
 * @author 杨新杰
 * @date 2019/1/7 14:55
 */
@Controller
@RequestMapping(value = "/admin")
public class WnkBusinessTypeQuickInputAdminController {

    @Resource
    private IWnkBusinessTypeQuickInputService wnkBusinessTypeQuickInputService;

    /**
     *
     * 功能描述: 进入商家快速输入管理界面
     *
     * @param  request  HttpServletRequest
     * @return 返回对应界面
     * @author 杨新杰
     * @date   2018/10/22 0022 16:28
     */
    @RequestMapping(value = "/joinBusinessTypeQuickInputManager")
    @ResponseBody
    public ModelAndView joinBusinessTypeQuickInputManager(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_type/wnk_business_type_quick_input");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入商家快速输入管理界面
     *
     * @param  request  HttpServletRequest
     * @return 返回对应界面
     * @author 杨新杰
     * @date   2018/10/22 0022 16:28
     */
    @RequestMapping(value = "/joinBusinessTypeQuickInputEdit")
    @ResponseBody
    public ModelAndView joinBusinessTypeQuickInputEdit(HttpServletRequest request,Integer quickId){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_type/wnk_business_type_quick_input_edit");
            modelAndView.addObject("quickId",quickId);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述:  查询所有快速输入
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回所有管理员状态信息
     * @author 刘武祥
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectAdminBusinessTypeQuickInputInfoAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAdminBusinessTypeQuickInputInfoAll(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
               List<Map<String,Object>> list =  wnkBusinessTypeQuickInputService.selectAdminBusinessTypeQuickInputInfoAll();
                if (list.size() > 0 ){
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
     * 功能描述: 根据条件查询所有快速输入
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   name        输入内容
     * @param   business_type_name  所属分类
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/25 0025 14:58
     */
    @RequestMapping(value = "/adminSearchBusinessTypeQuickInput", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBusinessTypeQuickInput(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Integer limit,
                                                         Integer page,
                                                         String name,
                                                         String business_type_name) {


        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (name != null && !"".equals(name)){
                    map.put("name", "%" + name + "%");
                }
                if (business_type_name != null && !"".equals(business_type_name)) {
                    map.put("business_type_name", "%"+business_type_name+"%");
                }
                List<Map<String, Object>> list = this.wnkBusinessTypeQuickInputService.adminSearchBusinessTypeQuickInput(map);
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
    /**
     *
     * 功能描述:  通过ID查询所有快速输入信息
     *
     * @param  quickId   快速输入ID
     * @return 返回所有管理员状态信息
     * @author 杨新杰
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectBusinessTypeQuickInputInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessTypeQuickInputInfoById(HttpServletRequest request, Integer quickId){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> retMap =  wnkBusinessTypeQuickInputService.selectBusinessTypeQuickInputInfoById(quickId);
                if (retMap != null ){
                    result.setData(retMap);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("查询失败");
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
     * 功能描述:  插入或更新快速输入内容
     *
     * @param  wnkBusinessTypeQuickInput
     * @return 返回所有管理员状态信息
     * @author 杨新杰
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/insertOrUpdateBusinessTypeQuickInputInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result insertOrUpdateBusinessTypeQuickInputInfo(HttpServletRequest request,WnkBusinessTypeQuickInput wnkBusinessTypeQuickInput){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Integer i = -1;

                if (wnkBusinessTypeQuickInput.getId() == null){
                    i  = this.wnkBusinessTypeQuickInputService.insertBusinessTypeQuickInput(wnkBusinessTypeQuickInput);
                } else {
                    i  = this.wnkBusinessTypeQuickInputService.updateBusinessTypeQuickInput(wnkBusinessTypeQuickInput);
                }
                if (i > 0 ){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("操作失败");
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
