package com.springmvc.controller.app;

import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.utils.AppImageBase64UploadUtil;
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
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class ImageAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 上传图片
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST,params = {"file"})
    @ResponseBody
    public Result uploadImage(HttpServletRequest request, HttpServletResponse response, String file){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (file.equals("")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请提交图片");
            }
            else {
                String[] sourceStrArray = file.split(";");
                if (sourceStrArray.length > 0){
                    String imageFormat = sourceStrArray[0];
                    //data:image/png
                    String[] imageFormatArrat = imageFormat.split("/");
                    if (imageFormatArrat.length == 2){
                        AppImageBase64UploadUtil appImageBase64UploadUtil = new AppImageBase64UploadUtil();
                        String fileLocalName = appImageBase64UploadUtil.getRequestBase64File(file);

                        if (fileLocalName == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("上传失败");
                        }
                        else{
                            Map<Object,Object> imageMap = new HashMap<Object,Object>();
                            imageMap.put("imageid",fileLocalName);

                            result.setData(imageMap);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("上传成功");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("图片Base64编码错误");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("图片Base64编码错误");
                }


            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("上传失败");
        }
        return result;
    }
}
