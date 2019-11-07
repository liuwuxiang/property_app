package com.springmvc.controller.download;

import com.springmvc.entity.WnkAppUpdateVersion;
import com.springmvc.service.impl.WnkAppUpdateVersionService;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/18 17:56
 */
@Controller
@RequestMapping
public class DownloadController {
    @Resource
    private WnkAppUpdateVersionService wnkAppUpdateVersionService;

    /**
     * 资源更新包开发环境
     */
//    //用户版更新包存放路径
//    public static String userAppVersionUrl = "/svn/update_apk/";
//    //商家版更新包存放路径
//    public static String businessAppVersionUrl = "/svn/update_apk/";


    /**
     * 资源包包开发环境
     */
    //用户版更新包存放路径
    public static String userAppVersionUrl = "/usr/local/property_system/update_apk/";
    //商家版更新包存放路径
    public static String businessAppVersionUrl = "/usr/local/property_system/update_apk/";

    /**
     * APK包生产环境
     */
//    //用户版APK包存放路径
//    public static String userAppAPKUrl = "/svn/apk/";
//    //商家版APK包存放路径
//    public static String businessAppAPKnUrl = "/svn/apk/";

    /**
     * APK包生产环境
     */
    //用户版更新包存放路径
    public static String userAppAPKUrl = "/usr/local/property_system/apk/";
    //商家版更新包存放路径
    public static String businessAppAPKnUrl = "/usr/local/property_system/apk/";

    /**
     * 功能描述: 获取客户端类型
     *
     * @param request 用于获取客户端相关信息
     * @return java.lang.String
     * @author 刘武祥
     * @date 17:12 2019/1/3
     */
    private String getClientType(HttpServletRequest request) {
        /*
          User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器
          能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
         */
        String userAgent = request.getHeader("user-agent").toLowerCase();
//        UserAgentInfo userAgentInfo = new  UserAgent.uasParser.parse(agent);
        String type;
        if (userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("ipod")) {
            type = "ios";
        }
        if (userAgent.contains("android") || userAgent.contains("liunx")) {
            type = "android";
        }
        if (userAgent.contains("micromessenger")) {
            type = "wx";
        } else {
            type = "pc";
        }
        return type;
    }

    /**
     *
     * 功能描述: 进入辰蓝科技-猛戳商家版下载页面
     *
     * @param   request      请求
     * @return: ModelAndView 猛戳商家版下载页面
     * @author: 刘武祥
     * @date: 2019/1/3 0003 11:55
     */
    @RequestMapping(value = "/toDownload", method = RequestMethod.GET)
    public ModelAndView jumpDownload(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("download/index");
        //user business
        modelAndView.addObject("user", "/property_system/download?type=user");
        modelAndView.addObject("business", "/property_system/download?type=business");
        modelAndView.addObject("client_type",getClientType(request));
        return modelAndView;
    }

    /**
     *
     * 功能描述:进入猛戳用户版-下载页面
     * @param   request       请求
     * @return: ModelAndView  返回猛戳用户版下载页面
     * @author: 刘武祥
     * @date: 2019/1/3 0003 11:56
     */
    @RequestMapping(value = "/toUserDownload", method = RequestMethod.GET)
    public ModelAndView toUserDownload(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("download/index2");
        //user business
        modelAndView.addObject("user", "/property_system/download?type=user");
        modelAndView.addObject("business", "/property_system/download?type=business");
        modelAndView.addObject("client_type",getClientType(request));
        return modelAndView;
    }


    /**
     *
     * 功能描述:下载
     *
     * @param   response  请求
     * @param   type      类型(0-用户端,1-商家端)
     * @return: void
     * @author: 刘武祥
     * @date: 2019/1/3 0003 12:01
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, String type) throws IOException {
        String filePath;
        if (type.equals("user")) {
            filePath = DownloadController.userAppAPKUrl + "mc_user.apk";
        } else {
            filePath = DownloadController.businessAppAPKnUrl + "mc_business.apk";
        }
//        if (type.equals("user")){
//            filePath = "/svn/apk/mc_user.apk";
//        } else {
//            filePath = "/svn/apk/mc_business.apk";
//        }
        File file = new File(filePath);
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + type + ".apk");

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] by = new byte[fileInputStream.available()];
            response.setContentLength(by.length);
            fileInputStream.read(by);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(by, 0, by.length);
            fileInputStream.close();
            outputStream.close();
        }

    }


    /**
     *
     * 功能描述:更新
     *
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/3 0003 14:13
     */
    @RequestMapping(value = "/download/check", method = RequestMethod.POST)
    @ResponseBody
    public Result check() {
        Result result = new Result();
        WnkAppUpdateVersion wnkAppUpdateVersion = this.wnkAppUpdateVersionService.selectNewVersionByType(1);
        if (wnkAppUpdateVersion == null) {
            result.setData("");
            result.setMsg("当前版本已是最新版本！");
            result.setStatus(Result.FAIL);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("version", wnkAppUpdateVersion.getVersion());
            //更新方式(0-资源包更新,1-apk包更新)
            map.put("update_type", wnkAppUpdateVersion.getUpdate_type());
            result.setData(map);
            result.setMsg("查询成功");
            result.setStatus(Result.SUCCESS);
        }
        return result;
    }


    @RequestMapping(value = "/download/getUpdate")
    public void getUpdate(String ver, HttpServletResponse response) throws IOException {
        WnkAppUpdateVersion wnkAppUpdateVersion = this.wnkAppUpdateVersionService.selectNewVersionByType(1);
        if (wnkAppUpdateVersion != null) {
            String fileName = wnkAppUpdateVersion.getFile_name();
            File file = new File(DownloadController.businessAppVersionUrl + fileName);
            if (file.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    try {
                        //更改下载的文件名
                        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                        OutputStream out = response.getOutputStream();
                        out.write(b);
                        out.close();
                    } catch (Exception e) {
                    }
                    // out.flush();
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/download/userCheck", method = RequestMethod.POST)
    @ResponseBody
    public Result userCheck() {
        Result result = new Result();
        WnkAppUpdateVersion wnkAppUpdateVersion = this.wnkAppUpdateVersionService.selectNewVersionByType(0);
        if (wnkAppUpdateVersion == null) {
            result.setData("");
            result.setMsg("当前版本已是最新版本！");
            result.setStatus(Result.FAIL);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("version", wnkAppUpdateVersion.getVersion());
            //更新方式(0-资源包更新,1-apk包更新)
            map.put("update_type", wnkAppUpdateVersion.getUpdate_type());
            result.setData(map);
            result.setMsg("查询成功");
            result.setStatus(Result.SUCCESS);
        }

        return result;
    }

    @RequestMapping(value = "/download/getUserUpdate")
    public void getUserUpdate(String ver, HttpServletResponse response) throws IOException {
        WnkAppUpdateVersion wnkAppUpdateVersion = this.wnkAppUpdateVersionService.selectNewVersionByType(0);
        if (wnkAppUpdateVersion != null) {
            String fileName = wnkAppUpdateVersion.getFile_name();
            File file = new File(DownloadController.userAppVersionUrl + fileName);
            if (file.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    try {
                        //更改下载的文件名
                        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                        OutputStream out = response.getOutputStream();
                        out.write(b);
                        out.close();
                    } catch (Exception e) {
                    }
                    // out.flush();
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
