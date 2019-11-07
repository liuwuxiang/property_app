package com.springmvc.controller.admin.carousel_img;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.BackstageMenus;
import com.springmvc.entity.CarouselImg;
import com.springmvc.service.impl.CarouselImgService;
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
 * 功能描述:轮播图
 *
 * @author 杨新杰
 * @date 2018/12/10 11:44
 */
@Controller
@RequestMapping("/admin")
public class AdminCarouselImgController {

    @Resource
    private CarouselImgService carouselImgService;

    /**
     *
     * 功能描述: 进入轮播图管理
     *
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinCarouselImgManagement",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinCarouselImgManagement(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/carousel_img/carousel_img");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入轮播图新增/编辑
     *
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinCarouselImgEdit",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinCarouselImgEdit(HttpServletRequest request,String carousel_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/carousel_img/carousel_img_edit");
            modelAndView.addObject("carousel_id",carousel_id);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述:  获取所有轮播图信息
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/selectCarouselInfoAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCarouselInfoAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
               List<Map<String,Object>> list =  carouselImgService.selectCarouselInfoAll();
               if (list.size() > 0){
                   for (Map<String,Object> map : list){
                       map.put("img_url_path", ImageToolsController.imageShowURL + "?imageid=" + map.get("img_url"));
                   }
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
     * 功能描述: 根据条件查询轮播图管理
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   url         跳转链接
     * @param   position    轮播图位置(0-全 1-首页 2-附近 3-平台积分商城 4-商家积分商城)
     * @param   is_checked  是否启用( 0-启用 1-禁用)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 17:45
     */
    @RequestMapping(value = "/adminSearchCarouselImg", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchCarouselImg(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Integer limit,
                                      Integer page,
                                      String url,
                                      Integer position,
                                      Integer is_checked){

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
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (url != null && !"".equals(url)) {
                    map.put("url", "%"+url+"%");
                }
                map.put("position", ("".equals(position) ? null : position) );
                map.put("is_checked",("".equals(is_checked) ? null : is_checked));
                List<Map<Object,Object>> list = this.carouselImgService.adminSearchCarouselImg(map);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Map<Object,Object> maps : list){
                        maps.put("img_url_path", ImageToolsController.imageShowURL + "?imageid=" + maps.get("img_url"));
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
     * 功能描述:  根据ID获取轮播图信息
     *
     * @param   carousel_id  轮播图ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/selectCarouselInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCarouselInfoById(HttpServletRequest request, String carousel_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = carouselImgService.selectCarouselInfoById(carousel_id);
                if (map != null){
                    map.put("img_url_path", ImageToolsController.imageShowURL + "?imageid=" + map.get("img_url"));
                    result.setData(map);
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
     * 功能描述:  更新轮播图信息
     *
     * @param   carouselImg  轮播图对象
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/updateCarouselInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCarouselInfoById(HttpServletRequest request, CarouselImg carouselImg){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                 if(carouselImgService.updateCarouselInfoById(carouselImg) > 0){
                     result.setData("");
                     result.setStatus(Result.SUCCESS);
                     result.setMsg("修改成功");
                 } else {
                     result.setData("");
                     result.setStatus(Result.FAIL);
                     result.setMsg("修改失败");
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
     * 功能描述:  插入新的轮播图
     * @param   carouselImg  轮播图对象
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/insertCarouselInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result insertCarouselInfo(HttpServletRequest request, CarouselImg carouselImg){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if(carouselImgService.insertCarouselInfo(carouselImg) > 0){
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
}
