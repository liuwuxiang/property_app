package com.springmvc.controller.admin.doings_spread;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.DoingsSpread;
import com.springmvc.service.impl.DoingsSpreadService;
import com.springmvc.utils.CookileUtil;
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
 * 推广活动 后台管理
 * @author 杨新杰
 * @Date 2018/11/3 10:13
 */
@Controller
@RequestMapping(value = "/admin")
public class DoingsSpreadController {

    @Resource
    private DoingsSpreadService doingsSpreadService;

    /**
     *
     * 功能描述: 获取所有的推广活动审核信息
     *
     * @param   request
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/17 0017 10:09
     */
    @RequestMapping(value = "/selectDoingsSpreadAll",method = RequestMethod.POST)
    @ResponseBody

    public Result selectDoingsSpreadAll(HttpServletRequest request){
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
                List<Map<String,Object>> list = doingsSpreadService.selectDoingsSpreadAll();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("无数据");
                } else {
                    for (Map<String,Object> map : list){
                        map.put("img_path",ImageToolsController.imageShowURL+"?imageid=");
                    }
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
     * 功能描述: 根据条件查询推广活动审核信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   title               活动标题
     * @param   store_name          商家名称
     * @param   ad_type             活动类型
     * @param   receive_type        接收对象
     * @param   verify_statue       审核状态
     * @param   activating_statue   上架状态
     * @param   create_time         发布时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/17 0017 11:02
     */
    @RequestMapping(value = "/adminSearchDoingsSpread", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchDoingsSpread(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Integer limit,
                                     Integer page,
                                     String title,
                                     String store_name,
                                     Integer ad_type,
                                     Integer receive_type,
                                     Integer verify_statue,
                                     Integer activating_statue,
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
                if (title != null && !"".equals(title)) {
                    map.put("title", "%" + title + "%");
                }
                if (store_name != null && !"".equals(store_name)){
                    map.put("store_name","%" + store_name + "%");
                }
                map.put("ad_type", ("".equals(ad_type) ? null : ad_type) );
                map.put("receive_type", ("".equals(receive_type) ? null : receive_type) );
                map.put("verify_statue", ("".equals(verify_statue) ? null : verify_statue) );
                map.put("activating_statue", ("".equals(activating_statue) ? null : activating_statue) );
                map.put("create_time",create_time);
                List<Map<Object,Object>> list = this.doingsSpreadService.adminSearchDoingsSpread(map);

                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Map<Object,Object> maps : list){
                        maps.put("img_path",ImageToolsController.imageShowURL+"?imageid=");
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
     * 功能描述:通过ID更新活动状态
     *
     * @param   request, doingsSpread
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/17 0017 23:07
     */
    @RequestMapping(value = "/updateDoingsSpreadStatue",method = RequestMethod.POST)
    @ResponseBody
    public Result updateDoingsSpreadStatue(HttpServletRequest request, DoingsSpread doingsSpread){

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
                int i  = doingsSpreadService.updateDoingsSpreadStatue(doingsSpread);
                if (i <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("操作失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作成功");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

}
