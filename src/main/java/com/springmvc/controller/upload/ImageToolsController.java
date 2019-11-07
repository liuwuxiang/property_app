package com.springmvc.controller.upload;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springmvc.utils.PhotoSynthesisUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.awt.image.PNGImageDecoder;
import sun.misc.BASE64Decoder;

/**
 * Created by Administrator on 2017/4/19.
 */
@Controller
@RequestMapping("/images")
public class ImageToolsController {
    //图片缩放比例
    public static Double imageRate = 0.5;

    /**
     * 开发环境
     */
//    //工程内部图片显示地址
//    public static String projectImageShowURL = "http://192.168.0.111:8080/property_system/";
//    //显示图片的路径
//    public static String imageShowURL = "http://192.168.0.111:8080/property_system/images/getimage.do";
//    //显示二维码路径
//    public static String qrcodeShowURL = "http://192.168.0.111:8080/property_system/images/getCode.do";
//    //证书图片路径
//    public static String certificateShowURL = "http://192.168.0.111:8080/property_system/images/getCertificate.do";
//    //存放图片的路径
//    public static String imageURL = "E:\\property_system\\images";
//    public static String qrcodeURL = "E:\\property_system\\qrcode";

    /**
     * 生产环境
     */
    //工程内部图片显示地址
    public static String projectImageShowURL = "http://m.chenlankeji.cn/";
    //显示图片的路径
    public static String imageShowURL = "http://m.chenlankeji.cn/images/getimage.do";
    //显示二维码路径
    public static String qrcodeShowURL = "http://m.chenlankeji.cn/images/getCode.do";
    //查看证书
    public static String certificateShowURL = "http://m.chenlankeji.cn/images/getCertificate.do";
    //存放图片的路径
    public static String imageURL = "/usr/local/property_system/images";
    //二维码图片存储路径
    public static String qrcodeURL = "/usr/local/property_system/qrcode";

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));


    /**
     *
     * 功能描述:展示图片
     *
     * @param   response    响应
     * @param   imageid     图片id
     * @return: void        无返回值
     * @author: 刘武祥
     * @date: 2019/1/7 0007 12:22
     */
    @RequestMapping(value = "/getimage.do")
    public void getimage(HttpServletResponse response, String imageid) throws Exception {
        String fileName = imageid;
        fileName = new String(fileName.getBytes("iso-8859-1"), "UTF-8");
        if (!"".equals(fileName)) {
            File file = new File(imageURL + File.separator + fileName);
            if (file.exists()) {
                fileOutput(file, response);
            }
        }
    }


    /**
     *
     * 功能描述: 展示二维码
     *
     * @param   response    响应
     * @param   imageid     图片id
     * @return: void        无返回值
     * @author: 刘武祥
     * @date: 2019/1/7 0007 14:14
     */
    @RequestMapping(value = "/getCode.do")
    public void getCode(HttpServletResponse response, String imageid) throws Exception {
        String fileName = imageid;
        fileName = new String(fileName.getBytes("iso-8859-1"), "UTF-8");
        if (!"".equals(fileName)) {
            File file = new File(qrcodeURL + File.separator + fileName);
            if (file.exists()) {
                fileOutput(file, response);
            }
        }
    }

    /**
     *
     * 功能描述:展示证书
     *
     * @param   response    响应
     * @param   imageid     图片ID
     * @return: void        无返回值
     * @author: 刘武祥
     * @date: 2019/1/7 0007 14:22
     */
    @RequestMapping(value = "/getCertificate.do")
    public void getCertificate(HttpServletResponse response, String imageid) throws Exception {
        String fileName = imageid;
        fileName = new String(fileName.getBytes("iso-8859-1"), "UTF-8");
        if (fileName != null) {
            File file = new File(PhotoSynthesisUtil.showUrl + File.separator + fileName);
            if (file.exists()) {
                fileOutput(file, response);
            }
        }
    }


    /**
     *
     * 功能描述:存入图片
     *
     * @param   request     获取上传的图片信息
     * @param   response
     * @param   fileNameStr
     * @param   fileId
     * @return {"success":"true"|"false","url":""}
     * @author: 刘武祥
     * @date: 2019/1/7 0007 14:41
     */
    @RequestMapping(value = "/savaimage.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8", params = {"fileNameStr", "fileId"})
    @ResponseBody
    public void saveimage(HttpServletRequest request, HttpServletResponse response, String fileNameStr, String fileId) {
        response.setContentType("text/plain;charset=UTF-8");
        Map<String, Object> mapsuccess = new HashMap<String, Object>();
        System.out.println(request.getRequestURL());
		/*System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getQueryString());	*/
        // 是否压缩
        boolean isCompression = true;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /** 页面控件的文件流 **/
        MultipartFile multipartFile = multipartRequest.getFile(fileId);
//        MultipartFile multipartFile = multipartRequest.getFile(fileNameStr);
        if (multipartFile == null) {
            multipartFile = multipartRequest.getFile(fileId);
        }
        /** 获取文件的后缀 **/
        String fileName = null;
        try {
            fileName = new String((multipartFile.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File imageFile = createImageFile(fileName);
        //获取文件信息，包括文件大小
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imageFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            // 小于10kb 就不要压缩了
            if (fis.available() / 1000 < 13) {
                isCompression = false;
            }
            // 压缩
            if (isCompression) {
                CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();

                File newFile = fi.getStoreLocation();

                /** 对服务器上的临时文件进行处理 */
                Image srcFile = ImageIO.read(newFile);
                int w = srcFile.getWidth(null);
                int h = srcFile.getHeight(null);
                //按比例缩放或扩大图片大小，将浮点型转为整型
                int width = (int) (w * ImageToolsController.imageRate);
                int height = (int) (h * ImageToolsController.imageRate);
                /** 宽,高设定 */
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

                FileOutputStream fstream = new FileOutputStream(imageFile);

                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                /** 压缩质量 */
                jep.setQuality((float) 0.8, true);
                encoder.encode(tag, jep);
                fstream.close();
            } else {
                // 小于10kb就不压缩了
                FileOutputStream fstream = new FileOutputStream(imageFile);
                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(fstream);
                stream.write(multipartFile.getBytes());
                stream.close();
            }

            mapsuccess.put("success", "success");
            mapsuccess.put("error", 0);
            //mapsuccess.put("url", request.getRequestURL()+ "?imageid=" + imageFile.getName());
//            mapsuccess.put("url", imageShowURL+ "?imageid=" + imageFile.getName());
            mapsuccess.put("url", imageFile.getName());
            mapsuccess.put("url_location", imageShowURL + "?imageid=" + imageFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mapsuccess.put("error", "lose");
            mapsuccess.put("message", "上传失败。");
            System.out.println("上传失败。");
        } catch (IOException e) {
            e.printStackTrace();
            mapsuccess.put("error", "lose");
            mapsuccess.put("message", "上传失败。");
            System.out.println("上传失败。");
        }
        JSONObject jsonObject = JSONObject.fromObject(mapsuccess);
        String willreturn = jsonObject.toString();
        System.out.println("willreturn===" + willreturn);
        try {
            response.getWriter().write(willreturn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 功能描述: 存入图片
     *
     * @param   request
     * @param   response
     * @return: {"success":"true"|"false","url":""}
     * @author: 刘武祥
     * @date: 2019/1/7 0007 14:43
     */
    @RequestMapping(value = "/savaimage2.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void saveimage2(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");
        Map<String, Object> mapsuccess = new HashMap<String, Object>();
        System.out.println(request.getRequestURL());
        // 是否压缩
        boolean isCompression = true;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /** 页面控件的文件流 **/
        MultipartFile multipartFile = multipartRequest.getFile("file");
        if (multipartFile == null) {
            multipartFile = multipartRequest.getFile("imgFile");
        }
        /** 获取文件的后缀 **/
        String fileName = null;
        try {
            fileName = new String((multipartFile.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File imageFile = createImageFile(fileName);
        //获取文件信息，包括文件大小
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imageFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            // 小于10kb 就不要压缩了
            if (fis.available() / 1000 < 13) {
                isCompression = false;
            }
            // 压缩
            if (isCompression) {
                CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();

                File newFile = fi.getStoreLocation();

                /** 对服务器上的临时文件进行处理 */
                Image srcFile = ImageIO.read(newFile);
                int w = srcFile.getWidth(null);
                int h = srcFile.getHeight(null);
                //按比例缩放或扩大图片大小，将浮点型转为整型
                int width = (int) (w * ImageToolsController.imageRate);
                int height = (int) (h * ImageToolsController.imageRate);
                /** 宽,高设定 */
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

                FileOutputStream fstream = new FileOutputStream(imageFile);

                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);


                /** 压缩质量 */
                jep.setQuality((float) 0.8, true);
                encoder.encode(tag, jep);
                fstream.close();
            } else {
                // 小于10kb就不压缩了
                FileOutputStream fstream = new FileOutputStream(imageFile);
                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(fstream);
                stream.write(multipartFile.getBytes());
                stream.close();
            }
            mapsuccess.put("success", "success");
            mapsuccess.put("error", 0);
            //mapsuccess.put("url", request.getRequestURL()+ "?imageid=" + imageFile.getName());
            mapsuccess.put("url", imageShowURL + "?imageid=" + imageFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mapsuccess.put("error", "lose");
            mapsuccess.put("message", "上传失败。");
            System.out.println("上传失败。");
        } catch (IOException e) {
            e.printStackTrace();
            mapsuccess.put("error", "lose");
            mapsuccess.put("message", "上传失败。");
            System.out.println("上传失败。");
        }
        JSONObject jsonObject = JSONObject.fromObject(mapsuccess);
        JSONObject jsonObject1 = new JSONObject();
        if ((Integer) jsonObject.get("error") == 0) {
            jsonObject1.put("code", 0);
            jsonObject1.put("msg", "上传成功");
            JSONObject jsonObjectData = new JSONObject();
            jsonObjectData.put("src", jsonObject.get("url"));
            jsonObject1.put("data", jsonObjectData);
        } else {
            jsonObject1.put("code", 1);
            jsonObject1.put("code", "上传失败");
        }
        String willreturn = jsonObject.toString();
        System.out.println("willreturn===" + willreturn);
        try {
            response.getWriter().write(jsonObject1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 功能描述:存入图片
     *
     * @param   request
     * @param   response
     * @param   fileNameStr
     * @param   fileIdStr
     * @return: void  {"success":"true"|"false","url":""}
     * @author: 刘武祥
     * @date: 2019/1/7 0007 14:45
     */
    @RequestMapping(value = "/savaimageMobile.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void saveimageMobile(HttpServletRequest request, HttpServletResponse response, String fileNameStr, String fileIdStr) {
        response.setContentType("text/plain;charset=UTF-8");
        Map<String, Object> mapsuccess = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 是否压缩
        boolean isCompression = true;

        /** 页面控件的文件流 **/
        MultipartFile multipartFile = multipartRequest.getFile(fileNameStr);
        if (multipartFile == null) {
            multipartFile = multipartRequest.getFile(fileIdStr);
        }
        /** 获取文件的后缀 **/
        String fileName = null;
        try {
            fileName = new String((multipartFile.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File imageFile = createImageFile(fileName);


        try {
            //获取文件信息，包括文件大小
            FileInputStream fis = new FileInputStream(imageFile);
            // 小于10kb 就不要压缩了
            if (fis.available() / 1000 < 13) {
                isCompression = false;
            }

            // 压缩
            if (isCompression) {
                CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                File newFile = fi.getStoreLocation();
                // 对服务器上的临时文件进行处理
                Image srcFile = ImageIO.read(newFile);
                int w = srcFile.getWidth(null);
                int h = srcFile.getHeight(null);
                //按比例缩放或扩大图片大小，将浮点型转为整型
                int width = (int) (w * ImageToolsController.imageRate);
                int height = (int) (h * ImageToolsController.imageRate);
                // 宽,高设定
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
                FileOutputStream fstream = new FileOutputStream(imageFile);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                // 压缩质量
                jep.setQuality((float) 0.8, true);
                encoder.encode(tag, jep);
                fstream.close();
            } else {
                // 小于10kb就不压缩了
                FileOutputStream fstream = new FileOutputStream(imageFile);
                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(fstream);
                stream.write(multipartFile.getBytes());
                stream.close();
            }

            mapsuccess.put("success", "success");
            mapsuccess.put("error", 0);
            //mapsuccess.put("url", request.getRequestURL()+ "?imageid=" + imageFile.getName());
            mapsuccess.put("url_location", imageShowURL + "?imageid=" + imageFile.getName());
            mapsuccess.put("url", imageFile.getName());

        } catch (IOException e) {

            e.printStackTrace();
            mapsuccess.put("error", "lose");
            mapsuccess.put("message", "上传失败。");
            System.out.println("上传失败。");
        }
        JSONObject jsonObject = JSONObject.fromObject(mapsuccess);
        String willreturn = jsonObject.toString();
        System.out.println("willreturn===" + willreturn);
        try {
            response.getWriter().write(willreturn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: LayUi富文本编辑器上传图片接口
     *
     * @param file     CommonsMultipartFile
     * @param response HttpServletResponse 服务类
     * @return
     * @author 杨新杰
     * @date 2018/10/25 19:12
     */
    @RequestMapping(value = "/saveImageLayUi", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void upload(@RequestParam("file") CommonsMultipartFile file, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");
        // 用作返回
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        // 获取文件的后缀
        String fileName;
        try {
            fileName = new String((file.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
            File imageFile = createImageFile(fileName);

            CommonsMultipartFile cf = file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File newFile = fi.getStoreLocation();
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(newFile);
            int w = srcFile.getWidth(null);
            int h = srcFile.getHeight(null);
            //按比例缩放或扩大图片大小，将浮点型转为整型
            int width = (int) (w * ImageToolsController.imageRate);
            int height = (int) (h * ImageToolsController.imageRate);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

            FileOutputStream fstream = new FileOutputStream(imageFile);

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality((float) 0.8, true);
            encoder.encode(tag, jep);
            fstream.close();

            map2.put("src", imageShowURL + "?imageid=" + imageFile.getName());//图片url
            map2.put("title", imageFile.getName());//图片名称，这个会显示在输入框里
            map.put("code", 0);
            map.put("msg", "上传成");
            map.put("data", map2);
            map.put("url", imageFile.getName());//图片url
            JSONObject jsonObject = JSONObject.fromObject(map);
            String willreturn = jsonObject.toString();
            response.getWriter().write(willreturn);
        } catch (Exception e1) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传失败");//提示消息
            map.put("data", "");
        }
    }


    /**
     * 功能描述: Html5+上传文件
     *
     * @param response HttpServletResponse 服务类
     * @return
     * @author 杨新杰
     * @date 2018/10/25 19:12
     */
    @RequestMapping(value = "/saveImageByPlus", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void saveImageByPlus(HttpServletResponse response, @RequestParam("file") MultipartFile file) {
        response.setContentType("text/plain;charset=UTF-8");
        // 用作返回
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        // 获取文件的后缀
        String fileName;
        try {
            fileName = new String((file.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
            File imageFile = createImageFile(fileName);

            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();

            File newFile = fi.getStoreLocation();
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(newFile);
            int w = srcFile.getWidth(null);
            int h = srcFile.getHeight(null);
            //按比例缩放或扩大图片大小，将浮点型转为整型
            int width = (int) (w * ImageToolsController.imageRate);
            int height = (int) (h * ImageToolsController.imageRate);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

            FileOutputStream fstream = new FileOutputStream(imageFile);

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality((float) 0.8, true);
            encoder.encode(tag, jep);
            fstream.close();

            map2.put("src", imageShowURL + "?imageid=" + imageFile.getName());//图片url
            map2.put("title", imageFile.getName());//图片名称，这个会显示在输入框里
            map.put("code", 0);
            map.put("msg", "上传成");
            map.put("data", map2);
            JSONObject jsonObject = JSONObject.fromObject(map);
            String willreturn = jsonObject.toString();
            response.getWriter().write(willreturn);
        } catch (Exception e1) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传失败");//提示消息
            map.put("data", "");
        }
    }

    /**
     * 功能描述: wangEditor文件上传
     *
     * @param response HttpServletResponse 服务类
     * @return
     * @author 杨新杰
     * @date 2018/10/25 19:12
     */
    @RequestMapping(value = "/saveImageByWangEditor", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void saveImageByWangEditor(HttpServletResponse response, @RequestParam("file") MultipartFile file) {
        response.setContentType("text/plain;charset=UTF-8");
        // 用作返回
        Map<String, Object> map = new HashMap<>();
        List<String> list = new LinkedList<>();

        // 获取文件的后缀
        String fileName;
        try {
            fileName = new String((file.getOriginalFilename()).getBytes("iso-8859-1"), "utf-8");
            File imageFile = createImageFile(fileName);

            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();

            File newFile = fi.getStoreLocation();
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(newFile);
            int w = srcFile.getWidth(null);
            int h = srcFile.getHeight(null);
            //按比例缩放或扩大图片大小，将浮点型转为整型
            int width = (int) (w * ImageToolsController.imageRate);
            int height = (int) (h * ImageToolsController.imageRate);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

            FileOutputStream fstream = new FileOutputStream(imageFile);

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fstream);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality((float) 0.8, true);
            encoder.encode(tag, jep);
            fstream.close();

            map.put("errno", 0);
            list.add(imageShowURL + "?imageid=" + imageFile.getName());
            map.put("data", list);
            JSONObject jsonObject = JSONObject.fromObject(map);
            String willreturn = jsonObject.toString();
            response.getWriter().write(willreturn);
        } catch (Exception e1) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传失败");//提示消息
            map.put("data", "");
        }
    }

    /**
     * 功能描述: Base64图片上传 jpeg
     *
     * @param response HttpServletResponse 服务类
     * @return
     * @author 杨新杰
     * @date 2018/11/24 19:12
     */
    @RequestMapping(value = "/saveImageByBase64", method = RequestMethod.POST)
    @ResponseBody
    public void saveImageByBase64(HttpServletResponse response, String file) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        // 通过base64来转化图片
        file = file.replaceAll("data:image/jpeg;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(file);
            for (int i = 0; i < imageByte.length; ++i) {
                if (imageByte[i] < 0) {
                    // 调整异常数据
                    imageByte[i] += 256;
                }
            }
        } catch (Exception e) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传失败");//提示消息
            map.put("data", "");
        }
        // 生成文件名
        String files = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpeg";
        // 生成文件路径
        String filename = imageURL + "/" + files;
        try {
            // 生成文件
            File imageFile = new File(filename);
            imageFile.createNewFile();
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            OutputStream imageStream = new FileOutputStream(imageFile);
            imageStream.write(imageByte);
            imageStream.flush();
            imageStream.close();

            map2.put("src", imageShowURL + "?imageid=" + imageFile.getName());//图片url
            map2.put("title", imageFile.getName());//图片名称，这个会显示在输入框里
            map.put("code", 0);
            map.put("msg", "上传成功");
            map.put("data", map2);
            JSONObject jsonObject = JSONObject.fromObject(map);
            String willreturn = jsonObject.toString();

            response.getWriter().write(willreturn);
        } catch (Exception e) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传失败");//提示消息
            map.put("data", "");
        }
    }


    /**
     * 创建存储图片的文件夹及图片名称。
     *
     * @return
     */
    private File createImageFile(String fileName) {
        // 文件保存目录URL
        String saveUrl = imageURL;
        log.debug("saveUrl:" + saveUrl);
        File uploadFile = new File(saveUrl);
        if (!uploadFile.isDirectory()) {
            uploadFile.mkdirs();
        }
        File file = new File(saveUrl + File.separator + UUID.randomUUID().toString() + fileName);
        if (!file.isDirectory()) {
            try {
                file.createNewFile();
                log.debug(file.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;

    }

    /**
     * 获取图片宽度和高度
     *
     * @param file 图片文件
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = {0, 0};
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图片宽
            result[1] = src.getHeight(null);// 得到源图片高
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    /**
     * 功能描述: 文件写出
     *
     * @param file 文件对象
     * @return void
     * @author 杨新杰
     * @date 17:47 2018/12/25
     */
    private void fileOutput(File file, HttpServletResponse response) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        OutputStream out = response.getOutputStream();
        out.write(b);
        out.close();
        // out.flush();
        fis.close();
    }


}
