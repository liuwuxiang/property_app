package com.springmvc.controller.upload;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/video")
public class VideoToolsController {

    /**
     * 开发环境
     */
//    //显示视频的路径
//    public static String videoShowURL = "http://localhost:8080/property_system/video/showVideo.do";
//    //存放视频的路径
//    public static String videoURL = "E:/property_system/video";

    /**
     * 生产环境
     */
    //显示视频的路径
    public static String videoShowURL = "http://m.chenlankeji.cn/video/showVideo.do";
    //存放视频的路径
    public static String videoURL = "/usr/local/property_system/video";

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    /**
     * 存入视频
     *
     * @param request 获取上传的图片信息
     * @return {"success":"true"|"false","url":""}
     */
    @RequestMapping(value = "/saveVideo.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8", params = {"fileNameStr", "fileId"})
    @ResponseBody
    public void saveVideo(HttpServletRequest request, HttpServletResponse response, String fileNameStr, String fileId) {
        response.setContentType("text/plain;charset=UTF-8");
        Map<String, Object> mapsuccess = new HashMap<String, Object>();
        System.out.println(request.getRequestURL());
		/*System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getQueryString());	*/
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /** 页面控件的文件流 **/
        MultipartFile multipartFile = multipartRequest.getFile("file");
//        MultipartFile multipartFile = multipartRequest.getFile(fileNameStr);
        if (multipartFile == null) {
            multipartFile = multipartRequest.getFile(fileNameStr);
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
            FileOutputStream fstream = new FileOutputStream(imageFile);
            BufferedOutputStream stream = null;
            stream = new BufferedOutputStream(fstream);
            stream.write(multipartFile.getBytes());
            stream.close();
            mapsuccess.put("success", "success");
            mapsuccess.put("error", 0);
            //mapsuccess.put("url", request.getRequestURL()+ "?imageid=" + imageFile.getName());
//            mapsuccess.put("url", imageShowURL+ "?imageid=" + imageFile.getName());
            mapsuccess.put("url", imageFile.getName());
            mapsuccess.put("url_location", videoShowURL + "?videoid=" + imageFile.getName());
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
     * 创建存储视频的文件夹及视频名称。
     *
     * @return
     */
    private File createImageFile(String fileName) {
        // 文件保存目录URL
        String saveUrl = videoURL;
        log.debug("saveUrl:" + saveUrl);
        File uploadFile = new File(saveUrl);
        if (!uploadFile.isDirectory()) {
            uploadFile.mkdirs();
        }
        File file = new File(saveUrl + "/" + UUID.randomUUID().toString() + fileName);
        if (!file.isDirectory()) {
            try {
                file.createNewFile();
                log.debug(file.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 展示视频
     *
     */
    @RequestMapping(value = "/showVideo.do")
    public void showVideo(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("videoid");
        try {
            fileName = new String(fileName.getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        if (fileName != null) {
            File file = new File(videoURL + "//" + fileName);
            if (file.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    try {
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
