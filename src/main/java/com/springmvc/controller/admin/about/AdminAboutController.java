package com.springmvc.controller.admin.about;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.service.impl.AboutUsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/11 11:13
 */
@Controller
@RequestMapping("/admin")
public class AdminAboutController {

    @Resource
    private AboutUsService aboutUsService;


    /**
     *
     * 功能描述: 进入关于我们设置
     *
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinAbout",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinAbout(HttpServletRequest request,String type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/about/about");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入关于我们设置(商家版本设置和用户版本设置)
     *
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinAboutTermsSetting",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinAboutTermsSetting(HttpServletRequest request,String type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/about/about_user");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }



    /**
     *
     * 功能描述:  根据类型查询所需要的信息
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/selectAboutByType", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAboutByType(HttpServletRequest request,Integer type,Integer about){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                /**
                 * //用户
                 * 19-法律声明,
                 * 20-价格声明,
                 * 21-隐私政策,
                 * 22-餐饮安全管理,
                 * 27-用户协议
                 * //商家
                 * 28-法律声明,
                 * 29-价格声明,
                 * 30-隐私政策,
                 * 31-餐饮安全管理,
                 * 32-用户协议
                 */
                // 用户
                if (about == 0){
                    switch (type){
                        case 1:
                            type=19;
                            break;
                        case 2:
                            type=20;
                            break;
                        case 3:
                            type=21;
                            break;
                        case 4:
                            type=22;
                            break;
                        case 5:
                            type=27;
                            break;
                    }
                } else {
                    switch (type){
                        case 1:
                            type=28;
                            break;
                        case 2:
                            type=29;
                            break;
                        case 3:
                            type=30;
                            break;
                        case 4:
                            type=31;
                            break;
                        case 5:
                            type=32;
                            break;
                    }
                }
                Map<String,Object> map =  aboutUsService.selectAboutByType(String.valueOf(type));
                result.setData(map);
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
     * 功能描述:  更新信息
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/10
     */
    @RequestMapping(value = "/updateAboutInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result updateAboutInfo(HttpServletRequest request,Integer type,String content,Integer about){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (about == 0){
                    switch (type){
                        case 1:
                            type=19;
                            break;
                        case 2:
                            type=20;
                            break;
                        case 3:
                            type=21;
                            break;
                        case 4:
                            type=22;
                            break;
                        case 5:
                            type=27;
                            break;
                    }
                } else {
                    switch (type){
                        case 1:
                            type=28;
                            break;
                        case 2:
                            type=29;
                            break;
                        case 3:
                            type=30;
                            break;
                        case 4:
                            type=31;
                            break;
                        case 5:
                            type=32;
                            break;
                    }
                }
                if(aboutUsService.updateAboutInfo(String.valueOf(type),content) > 0){
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
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }



}
