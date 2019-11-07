package com.springmvc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.springmvc.controller.upload.ImageToolsController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * app图片上传方法(图片需经过Base64编码)
 */
public class AppImageBase64UploadUtil {

    /**图片上传路径*/
    String imageSavePath = ImageToolsController.imageURL;

    /**
     * 上传文件(前台为Base64格式)
     *
     * @param file 上传文件的名字(前台的属性名称)
     * @return 成功返回图片名字,失败返回null
     * @throws Exception
     */
    public String getRequestBase64File(String file) throws Exception {
        String[] sourceStrArray = file.split(";");
        if (sourceStrArray.length > 0){
            String imageFormat = sourceStrArray[0];
            //data:image/png
            String[] imageFormatArrat = imageFormat.split("/");
            if (imageFormatArrat.length == 2){
                String suffix = imageFormatArrat[1];  //后缀名
                //产生的文件名称
                String name = getRandomFileName()+"."+suffix;
                file = sourceStrArray[1];
                file = file.substring(7, file.length());
                BASE64Decoder decoder = new BASE64Decoder();
                try {
                    // Base64解码
                    byte[] b = decoder.decodeBuffer(file);
                    for (int i = 0; i < b.length; ++i) {
                        if (b[i] < 0) {// 调整异常数据
                            b[i] += 256;
                        }
                    }
                    //生成jpeg图片
                    String imgFilePath = imageSavePath+"/"+name;//新生成的图片
                    // 生成文件
                    OutputStream out = new FileOutputStream(imgFilePath);
                    out.write(b);
                    out.flush();
                    out.close();
                    return name;
                } catch (Exception e) {
                    return null;
                }
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }


    /**
     * 生成随机名字
     */
    public String getRandomFileName() {
        Random r = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        StringBuffer sb = new StringBuffer();
        sb.append(r.nextInt(100));
        sb.append(r.nextInt(100));
        sb.append("_");
        sb.append(sdf.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));
        sb.append(r.nextInt(100));
        return sb.toString();
    }
}
