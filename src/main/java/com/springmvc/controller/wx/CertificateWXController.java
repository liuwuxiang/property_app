package com.springmvc.controller.wx;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.UsersCertificates;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersCertificatesService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class CertificateWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersCertificatesService usersCertificatesService;

    //进入证书二维码扫描查询页面
    @RequestMapping(value = "/joinCertificateSearch",method = RequestMethod.GET,params = {"number"})
    @ResponseBody
    public ModelAndView joinCertificateSearch(HttpServletRequest request,String number){
        ModelAndView modelAndView = new ModelAndView("wx/user_certificate_check");
        UsersCertificates usersCertificates = this.usersCertificatesService.selectByNumber(number);
        if (usersCertificates == null){
            modelAndView.addObject("number","");
            modelAndView.addObject("user_name","");
            modelAndView.addObject("cz_amount","");
            modelAndView.addObject("cz_type","");
            modelAndView.addObject("qiquangudong","");
            modelAndView.addObject("cz_time","");
        }
        else{
            modelAndView.addObject("number",usersCertificates.getNumber());
            modelAndView.addObject("user_name",usersCertificates.getUser_name());
            modelAndView.addObject("cz_amount",usersCertificates.getAmount());
            modelAndView.addObject("cz_type","充值积分");
            modelAndView.addObject("qiquangudong",usersCertificates.getOption_equity());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDateStr = formatter.format(usersCertificates.getCreate_time());
            modelAndView.addObject("cz_time",nowDateStr);
        }

        return modelAndView;
    }

    //进入查看证书列表
    @RequestMapping(value = "/searchMyCertificateList")
    @ResponseBody
    public ModelAndView searchMyCertificateList(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/my_certificates");
        }
        return modelAndView;
    }

    //进入查看我的证书图片
    @RequestMapping(value = "/searchMyCertificatePhoto",method = RequestMethod.GET,params = {"number"})
    @ResponseBody
    public ModelAndView searchMyCertificatePhoto(HttpServletRequest request,String number){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/look_certificate_photo");
            UsersCertificates usersCertificates = this.usersCertificatesService.selectByNumber(number);
            if (usersCertificates == null){
                modelAndView.addObject("number",number);
                modelAndView.addObject("img_url","");
            }
            else{
                modelAndView.addObject("number",number);
                modelAndView.addObject("img_url", ImageToolsController.certificateShowURL+"?imageid="+usersCertificates.getCertificate_photo_id());
            }

        }
        return modelAndView;
    }
}
