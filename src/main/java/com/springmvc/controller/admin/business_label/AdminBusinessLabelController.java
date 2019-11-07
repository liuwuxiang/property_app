package com.springmvc.controller.admin.business_label;

import com.springmvc.entity.WnkBusinessLabel;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.DoingsSpreadService;
import com.springmvc.service.impl.WnkBusinessLabelService;
import com.springmvc.service.impl.WnkStoreInformationService;
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
 * 功能描述:         特色内容及服务内容
 * @Author:         刘武祥
 *
 * @CreateDate:     2019/2/15 0015 10:59
 * @UpdateUser:     刘武祥
 * @UpdateDate:     2019/2/15 0015 10:59
 * 
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminBusinessLabelController {

    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;

    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 进入商家特色内容管理
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinTeseContentManagement",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinTeseContentManagement(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/content_management/tese_content_management");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家服务内容管理
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinFuwuContentManagement",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinFuwuContentManagement(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/content_management/fuwu_content_management");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入服务/特色内容新增/修改
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinInsertOrUpdateContentInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinInsertOrUpdateContentInfo(HttpServletRequest request,String type,String content_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/content_management/content_edit");
            modelAndView.addObject("type",type);
            modelAndView.addObject("content_id",content_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入管理员后台修改商家服务/特色内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinBusinessLabel",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinBusinessLabel(HttpServletRequest request,String business_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_business_label");
            modelAndView.addObject("business_id",business_id);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述:  获取所有特色/服务内容
     *
     * @param  request      请求
     * @param  label_type   id
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/getContentAll",method = RequestMethod.POST)
    @ResponseBody
    public Result getContentAll(HttpServletRequest request,Integer label_type){
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
                List<Map<String,Object>> list =  wnkBusinessLabelService.selectLabelContentAll(label_type);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("暂无数据");
                } else {
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询特色标签管理
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   name        特色内容名称
     * @param   state       标签状态(0-启用,1-禁用)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/21 0021 14:40
     */
    @RequestMapping(value = "/adminSearchContentInfoByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchContentInfoByConditions(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String name,
                                                      String state){

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
                map.put("state",("".equals(state) ? null : state));
                List<Map<Object,Object>> list = this.wnkBusinessLabelService.adminSearchContentInfoByConditions(map);
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
     * 功能描述: 根据条件查询服务标签管理
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   name        特色内容名称
     * @param   state       标签状态(0-启用,1-禁用)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/21 0021 14:40
     */
    @RequestMapping(value = "/adminSearchFContentInfoByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchFContentInfoByConditions(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     Integer limit,
                                                     Integer page,
                                                     String name,
                                                     String state){

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
                map.put("state",("".equals(state) ? null : state));
                List<Map<Object,Object>> list = this.wnkBusinessLabelService.adminSearchFContentInfoByConditions(map);
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
     * 功能描述:  根据ID获取特色/服务内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/selectContentInfoById",method = RequestMethod.POST)
    @ResponseBody
    public Result selectContentInfoById(HttpServletRequest request,Integer content_id,Integer content_type){
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
                Map<String,Object> map =  wnkBusinessLabelService.selectContentInfoById(content_id,content_type);
                if (map == null){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("暂无数据");
                } else {
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述:  根据ID获取特色/服务内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/insertContentLabel",method = RequestMethod.POST)
    @ResponseBody
    public Result insertContentLabel(HttpServletRequest request, WnkBusinessLabel wnkBusinessLabel){
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
                if (wnkBusinessLabelService.insertContentLabel(wnkBusinessLabel) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("新增成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("新增失败");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述:  根据ID获取特色/服务内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/updateContentLabel",method = RequestMethod.POST)
    @ResponseBody
    public Result updateContentLabel(HttpServletRequest request,WnkBusinessLabel wnkBusinessLabel){
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
                if (wnkBusinessLabelService.updateContentLabel(wnkBusinessLabel) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                } else {
                    result.setData(wnkBusinessLabel.toString());
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新失败");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述:  获取所有服务内容或者特色内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/getBusinessLabel",method = RequestMethod.POST)
    @ResponseBody
    public Result getBusinessLabel(HttpServletRequest request,String type){
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
                List<Map<String,Object>> list = wnkBusinessLabelService.getBusinessLabel(type);
                list.size();
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述:  获取所有商家选中的特色/服务内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/selectBusinessTrueLabel",method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessTrueLabel(HttpServletRequest request,Integer business_id){
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
                WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                result.setData(wnkStoreInformation);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 更新商家的特色/服务内容
     *
     * @param  request
     * @return
     * @author 杨新杰
     * @date   2018/11/15 14:47
     */
    @RequestMapping(value = "/updateBusinessLabel",method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessLabel(HttpServletRequest request,String business_id,String fuwu_label,String tese_label){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 参数检察
                if (fuwu_label == null || fuwu_label.equals("")){
                    fuwu_label = null;
                }
                if (tese_label == null || tese_label.equals("")){
                    tese_label = null;
                }
                // 业务逻辑开始
                if (wnkStoreInformationService.updateBusinessLabel(business_id,fuwu_label,tese_label) > 0){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("失败");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



}
