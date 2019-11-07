package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.WnkBusinessTag;
import com.springmvc.service.impl.WnkBusinessTagService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 00:12
 * @Description:万能卡商户标签后台管理Controller
 */

@Controller
@RequestMapping(value = "/admin")
public class WnkBusinessTagAdminController {
    @Resource
    private WnkBusinessTagService wnkBusinessTagService;

    /**
     *
     * 功能描述: 进入万能卡商户标签管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:45
     */
    @RequestMapping(value = "/joinWnkBusinessTagManager")
    @ResponseBody
    public ModelAndView joinWnkBusinessTagManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_tag/wnk_business_tag_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入万能卡商户二级标签管理界面
     *
     * @param   request, one_tag_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:46
     */
    @RequestMapping(value = "/joinWnkBusinessTwoTagManager",method = RequestMethod.GET,params = {"one_tag_id"})
    @ResponseBody
    public ModelAndView joinWnkBusinessTwoTagManager(HttpServletRequest request,Integer one_tag_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_tag/wnk_business_two_manager");
            modelAndView.addObject("one_tag_id",one_tag_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入万能卡商户标签添加界面(makeType=0一级标签,makeType=1二级标签)
     *
     * @param   request, makeType, last_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:46
     */
    @RequestMapping(value = "/joinWnkBusinessTagAdd",method = RequestMethod.GET,params = {"makeType","last_id"})
    @ResponseBody
    public ModelAndView joinWnkBusinessTagAdd(HttpServletRequest request,Integer makeType,Integer last_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_tag/add_wnk_business_tag");
            modelAndView.addObject("makeType",makeType);
            modelAndView.addObject("last_id",last_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入万能卡商户标签修改界面
     *
     * @param   request, record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:46
     */
    @RequestMapping(value = "/joinWnkBusinessTagSet",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView joinWnkBusinessTagSet(HttpServletRequest request, Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_tag/set_wnk_business_tag");
            modelAndView.addObject("record_id",record_id);
            WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectById(record_id);
            if (wnkBusinessTag == null){
                modelAndView.addObject("name","");
                modelAndView.addObject("state",0);
                modelAndView.addObject("photo_url", "");
                //标签类型(0-一级标签,1-二级标签)
                modelAndView.addObject("tag_type", 1);
                //标签关联分类id
                modelAndView.addObject("business_type_id", -1);
            }
            else{
                modelAndView.addObject("name",wnkBusinessTag.getName());
                modelAndView.addObject("state",wnkBusinessTag.getState());
                modelAndView.addObject("photo_url", ImageToolsController.imageShowURL+"?imageid="+wnkBusinessTag.getPhoto_id());
                //标签类型(0-一级标签,1-二级标签)
                modelAndView.addObject("tag_type", wnkBusinessTag.getType());
                //标签关联分类id
                modelAndView.addObject("business_type_id", wnkBusinessTag.getRelation_business_type_id());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入标签排序页面
     *
     * @param   request, record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:19
     */
    @RequestMapping(value = "/joinWnkBusinessTagSortPage",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView joinWnkBusinessTagSortPage(HttpServletRequest request, Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_tag/wnk_business_tag_sort");
            modelAndView.addObject("record_id",record_id);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 获取一级商户标签信息
     *
     * @param   request
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:19
     */
    @RequestMapping(value = "/getOneBusinessTagList", method = RequestMethod.POST)
    @ResponseBody
    public Result getOneBusinessTagList(HttpServletRequest request){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.wnkBusinessTagService.selectAdminAllOneTag();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("photo_url",ImageToolsController.imageShowURL+"?imageid="+map.get("photo_id"));
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
     * 功能描述: 根据条件查询标签管理信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               计数
     * @param   page                分页
     * @param   name                一级标签
     * @param   state_str           状态
     * @param   relation_type_name  关联分类
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:25
     */
    @RequestMapping(value = "/adminSearchBusinessTag", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBusinessTag(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Integer limit,
                                               Integer page,
                                               String name,
                                               Integer state_str,
                                               String relation_type_name){

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
                map.put("state_str", ("".equals(state_str) ? null : state_str) );
                if (relation_type_name != null && !"".equals(relation_type_name)){
                    map.put("relation_type_name","%" + relation_type_name + "%");
                }
                List<Map<Object,Object>> list = this.wnkBusinessTagService.adminSearchBusinessTag(map);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> maps = list.get(index);
                        maps.put("photo_url",ImageToolsController.imageShowURL+"?imageid="+maps.get("photo_id"));
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
     * 功能描述: 获取商户二级标签信息
     *
     * @param   request, response, one_tag_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:47
     */
    @RequestMapping(value = "/getTwoBusinessTagList", method = RequestMethod.POST,params = {"one_tag_id"})
    @ResponseBody
    public Result getTwoBusinessTagList(HttpServletRequest request, HttpServletResponse response,Integer one_tag_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.wnkBusinessTagService.selectTwoTagByOneTagId(one_tag_id);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("photo_url",ImageToolsController.imageShowURL+"?imageid="+map.get("photo_id"));

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
     * 功能描述: 标签排序获取标签
     *
     * @param   request, response, one_tag_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:47
     */
    @RequestMapping(value = "/wnkBusinessTagSortGetTagAction", method = RequestMethod.POST,params = {"one_tag_id"})
    @ResponseBody
    public Result wnkBusinessTagSortGetTagAction(HttpServletRequest request, HttpServletResponse response,Integer one_tag_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = null;
                if (one_tag_id == -1){
                    list = this.wnkBusinessTagService.selectAllOneTag();
                }
                else{
                    list = this.wnkBusinessTagService.selectTwoTagByOneTagId(one_tag_id);
                }
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
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 更新标签序号
     *
     * @param tag_contents 标签json数组字符串内容(每个jsonObject中包含一个record_id与一个tag_index,record_id为标签id,tag_index为标签序号)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/18 12:04 AM
     */
    @RequestMapping(value = "/updateWnkBusinessTagSortIndex", method = RequestMethod.POST,params = {"tag_contents"})
    @ResponseBody
    public Result updateWnkBusinessTagSortIndex(HttpServletRequest request, HttpServletResponse response,String tag_contents){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                JSONArray jsonArray = JSONArray.fromObject(tag_contents);
                if (jsonArray.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前无可排序标签");
                }
                else{
                    for (Integer index = 0;index < jsonArray.size();index++){
                        JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                        if (jsonObject != null){
                            Integer record_id = jsonObject.getInt("record_id");
                            Integer sort_index = jsonObject.getInt("tag_index");
                            this.wnkBusinessTagService.updateTagSortIndex(record_id,sort_index);
                        }
                    }
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("排序完成");
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
     * 功能描述: 新增一级商户标签信息
     *
     * @param   request, response, name, state, photo_url, business_type_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:48
     */
    @RequestMapping(value = "/AddOneBusinessTag", method = RequestMethod.POST, params = {"name", "state", "photo_url", "business_type_id"})
    @ResponseBody
    public Result AddOneBusinessTag(HttpServletRequest request, HttpServletResponse response, String name, Integer state, String photo_url, Integer business_type_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessTag wnkBusinessTagCheck = null;
                if (business_type_id != -1) {
                    wnkBusinessTagCheck = this.wnkBusinessTagService.selectByBusinessTypeId(business_type_id);
                }
                if (wnkBusinessTagCheck != null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("一个商户分类只可对应一个一级标签");
                } else {
                    String imgQZ = ImageToolsController.imageShowURL + "?imageid=";
                    photo_url = photo_url.replace(imgQZ, "");
                    WnkBusinessTag wnkBusinessTag = new WnkBusinessTag();
                    wnkBusinessTag.setName(name);
                    wnkBusinessTag.setState(state);
                    wnkBusinessTag.setPhoto_id(photo_url);
                    wnkBusinessTag.setRelation_business_type_id(business_type_id);
                    int addState = this.wnkBusinessTagService.insertOneTag(wnkBusinessTag);

                    if (addState <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("添加失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("添加成功");
                    }
                }

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
     * 功能描述: 新增二级商户标签信息
     *
     * @param   request, response, name, state, last_id, photo_url
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:48
     */
    @RequestMapping(value = "/addTwoBusinessTag", method = RequestMethod.POST, params = {"name", "state", "last_id", "photo_url"})
    @ResponseBody
    public Result addTwoBusinessTag(HttpServletRequest request, HttpServletResponse response, String name, Integer state, Integer last_id, String photo_url) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                String imgQZ = ImageToolsController.imageShowURL + "?imageid=";
                photo_url = photo_url.replace(imgQZ, "");

                WnkBusinessTag wnkBusinessTag = new WnkBusinessTag();
                wnkBusinessTag.setName(name);
                wnkBusinessTag.setState(state);
                wnkBusinessTag.setLast_id(last_id);
                wnkBusinessTag.setPhoto_id(photo_url);
                int addState = this.wnkBusinessTagService.insertTwoTag(wnkBusinessTag);

                if (addState <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("添加失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("添加成功");
                }
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
     * 功能描述: 修改一级商户标签信息
     *
     * @param   request, response, name, state, record_id, photo_url, business_type_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:48
     */
    @RequestMapping(value = "/setOneBusinessTag", method = RequestMethod.POST, params = {"name", "state", "record_id", "photo_url", "business_type_id"})
    @ResponseBody
    public Result setOneBusinessTag(HttpServletRequest request, HttpServletResponse response, String name, Integer state, Integer record_id, String photo_url, Integer business_type_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessTag wnkBusinessTagCheck = null;
                if (business_type_id != -1) {
                    wnkBusinessTagCheck = this.wnkBusinessTagService.selectByBusinessTypeId(business_type_id);
                }
                if (wnkBusinessTagCheck != null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("一个商户分类只可对应一个一级标签");
                } else {
                    WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectById(record_id);
                    if (wnkBusinessTag == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("标签不存在");
                    } else {
                        String imgQZ = ImageToolsController.imageShowURL + "?imageid=";
                        photo_url = photo_url.replace(imgQZ, "");

                        wnkBusinessTag.setName(name);
                        wnkBusinessTag.setState(state);
                        wnkBusinessTag.setPhoto_id(photo_url);
                        wnkBusinessTag.setRelation_business_type_id(business_type_id);
                        int updateState = this.wnkBusinessTagService.updateOneTag(wnkBusinessTag);
                        if (updateState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新失败");
                        } else {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("更新成功");
                        }
                    }
                }

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
     * 功能描述: 删除用户标签(逻辑删除)
     *
     * @param   request, business_type_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 15:49
     */
    @RequestMapping(value = "/deleteBusinessTag", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteBusinessTag(HttpServletRequest request, Integer business_type_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectById(business_type_id);
                if (wnkBusinessTag.getState() == 1) {
                    if (wnkBusinessTagService.deleteBusinessTag(business_type_id) > 0) {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("删除成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("删除失败");
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先停用才可删除");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    /**
     *
     * 功能描述: 获取商家分类对应的商家标签
     *
     * @param   businessTypeID  商户分类ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    10:16 2019/1/7
     */
    @RequestMapping(value = "/selectBusinessTagOneByBusinessTypeId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessTagOneByBusinessTypeId(HttpServletRequest request, Integer businessTypeID){
        Result result = new Result();
        try {

            //<editor-fold desc="登陆检查">
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
                return result;
            }
            //</editor-fold>

            Map<String,Object>  retMap =  this.wnkBusinessTagService.selectBusinessTagOneByBusinessTypeId(businessTypeID);
            if (retMap != null){
                result.setData(retMap);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            } else {
                result.setData(retMap);
                result.setStatus(Result.FAIL);
                result.setMsg("未关联商家标签");
            }


        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



}
