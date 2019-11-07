package com.springmvc.controller.app;

import com.springmvc.entity.AboutUs;
import com.springmvc.entity.IntegralHelp;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.AboutUsService;
import com.springmvc.service.impl.IntegralHelpService;
import com.springmvc.utils.LoginSessionCheckUtil;
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
 * @author: zhangfan
 * @Date: 2018/11/14 02:36
 * @Description:积分帮助说明前端数据获取控制器
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class IntegralHelpAppController {
    @Resource
    private IntegralHelpService integralHelpService;
    @Resource
    private AboutUsService aboutUsService;

    /**
     *
     * 功能描述: 获取积分帮助说明
     *
     * @param type 积分说明类型(0-用户端平台积分、1-用户端商家积分、2-用户端赠送积分、3-商家端等级积分、4-商家端推广积分说明数据,5-商家端玫瑰说明数据,6-用户端玫瑰说明帮助)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 2:37 AM
     */
    @RequestMapping(value = "/getIntegralHelpContent", method = RequestMethod.POST,params = {"type"})
    @ResponseBody
    public Result getIntegralHelpContent(HttpServletRequest request, HttpServletResponse response, Integer type){
        Result result = new Result();
        try {
            IntegralHelp integralHelp = this.integralHelpService.selectContentByType(type);
            if (integralHelp == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("未设置内容");
            }
            else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",integralHelp.getContent());
                map.put("open_type",integralHelp.getOpen_type());

                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("获取成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取关于我们帮助说明
     *
     * @param type (0-法律声明,1-价格声明,2-隐私政策,3-餐饮安全管理,4-用户协议)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/14 2:37 AM
     */
    @RequestMapping(value = "/getAboutHelpContent", method = RequestMethod.POST)
    @ResponseBody
    public Result getAboutHelpContent(Integer type,Integer about){
        Result result = new Result();
        try {
            Integer contentType = 19;
            if (about == 0){
                if (type == 0){
                    contentType = 19;
                }
                else if(type == 1){
                    contentType = 20;
                }
                else if (type == 2){
                    contentType = 21;
                }
                else if (type == 3){
                    contentType = 22;
                }
                else if (type == 4){
                    contentType = 27;
                }
            } else {
                if (type == 0){
                    contentType = 28;
                }
                else if(type == 1){
                    contentType = 29;
                }
                else if (type == 2){
                    contentType = 30;
                }
                else if (type == 3){
                    contentType = 31;
                }
                else if (type == 4){
                    contentType = 32;
                }
            }
            AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(contentType);
            if (aboutUs == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("未设置内容");
            }
            else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",aboutUs.getContent());

                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("获取成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
}
