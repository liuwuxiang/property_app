package com.springmvc.controller.admin.wnk_maintain;

import com.springmvc.entity.WnkMaintain;
import com.springmvc.service.impl.AdminsService;
import com.springmvc.service.impl.WnkBusinessTypeService;
import com.springmvc.service.impl.WnkMaintainService;
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
 * 功能描述: 系统维护员管理控制器
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 15:37
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminWnkMaintainController {

    @Resource
    private AdminsService adminsService;
    @Resource
    private WnkMaintainService wnkMaintainService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;


    /**
     *
     * 功能描述:  进入系统维护员管理界面
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:46
     */
    @RequestMapping(value = "/joinWnkMaintainIndex")
    @ResponseBody
    public ModelAndView adminsManager(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_maintain/wnk_maintain");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述:  进入系统维护员管理界面
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:46
     */
    @RequestMapping(value = "/joinWnkMaintainEdit")
    @ResponseBody
    public ModelAndView joinWnkMaintainEdit(HttpServletRequest request,String maintain_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_maintain/wnk_maintain_edit");
            if (maintain_id != null){
                modelAndView.addObject("maintain_id",maintain_id);
            }
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 获取所有管理员信息
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:55
     */
    @RequestMapping(value = "/getMaintainAll", method = RequestMethod.POST)
    @ResponseBody
    public Result getMaintainAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<String,Object>> list = this.wnkMaintainService.selectMaintainInfoAll();
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
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
     * 功能描述: 根据条件查询系统维护员管理
     *
     * @param   request         请求
     * @param   response        响应
     * @param   limit           条目数
     * @param   page            分页
     * @param   maintain_name   维护员姓名
     * @param   maintain_phone  联系电话
     * @param   maintain_type   所属分类名称
     * @param   create_time     创建时间
     * @param   status          状态
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 16:49
     */
    @RequestMapping(value = "/adminSearchMaintain", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchMaintain(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Integer limit,
                                         Integer page,
                                         String maintain_name,
                                         String maintain_phone,
                                         String maintain_type,
                                         Integer status,
                                         String create_time){

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
                if (maintain_name != null && !"".equals(maintain_name)) {
                    map.put("maintain_name", "%" + maintain_name + "%");
                }
                if (maintain_phone != null && !"".equals(maintain_phone)){
                    map.put("maintain_phone","%"+maintain_phone+"%");
                }
                if (maintain_type != null && !"".equals(maintain_type)){
                    map.put("maintain_type","%" + maintain_type + "%");
                }
                map.put("status", ("".equals(status) ? null : status) );
                map.put("create_time", create_time);
                List<Map<Object,Object>> list = this.wnkMaintainService.adminSearchMaintain(map);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
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
     * 功能描述: 根据维护员ID查询维护员信息
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:55
     */
    @RequestMapping(value = "/selectMaintainInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectMaintainInfoById(HttpServletRequest request,String maintain_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = this.wnkMaintainService.selectMaintainInfoById(maintain_id);
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
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
     * 功能描述: 查询所有商家分类
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:55
     */
    @RequestMapping(value = "/selectBusinessType", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessType(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object, Object>> maps = this.wnkBusinessTypeService.adminSelectAllRecord();
                result.setData(maps);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
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
     * 功能描述:  新增维护员信息
     *
     * @param   wnkMaintain 维护员实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/14 16:55
     */
    @RequestMapping(value = "/insertMaintain", method = RequestMethod.POST)
    @ResponseBody
    public Result insertMaintain(HttpServletRequest request, WnkMaintain wnkMaintain){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WnkMaintain wnkMaintainInfo = this.wnkMaintainService.selectMaintainInfoByBusinessTypeIdAll(wnkMaintain.getBusiness_type_id());
                if (wnkMaintainInfo == null){
                    if (this.wnkMaintainService.insertMaintain(wnkMaintain) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前分类已有维护员");
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
     * 功能描述:  更新维护员信息
     *
     * @param   wnkMaintain 维护员实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/14 16:55
     */
    @RequestMapping(value = "/updateMaintain", method = RequestMethod.POST)
    @ResponseBody
    public Result updateMaintain(HttpServletRequest request, WnkMaintain wnkMaintain){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WnkMaintain wnkMaintainInfo = this.wnkMaintainService.selectMaintainInfoByBusinessTypeIdAll(wnkMaintain.getBusiness_type_id());
                if (wnkMaintainInfo == null || wnkMaintainInfo.getId().equals(wnkMaintain.getId())){
                    if (this.wnkMaintainService.updateMaintain(wnkMaintain) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("更新失败");
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前分类已有维护员");
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
