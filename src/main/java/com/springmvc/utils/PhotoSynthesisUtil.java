package com.springmvc.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.utils.qrCode.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 图片合成工具类，在图片上写字生成新图片
 */
public class PhotoSynthesisUtil {
//    public static String showUrl = "/svn/certificate";
    public static String showUrl = "/usr/local/property_system/certificate";
    public static String qrcodeURL = "/usr/local/property_system/qrcode";
//    public static String qrcodeURL = "/svn/qrcode";

    //证书查询
    public static String certificateSearchUrl = "http://m.chenlankeji.cn/wx/v1.0.0/joinCertificateSearch";
//    public static String certificateSearchUrl = "http://192.168.0.109:8080/property_system/wx/v1.0.0/joinCertificateSearch";

    public static Map<Object, Object> synthesisPhoto(String user_name,String amount,String silver_coin_number,String create_time) throws IOException {
        Map<Object,Object> returnMap = new HashMap<Object,Object>();

        BufferedImage img = new BufferedImage(1924, 1080, BufferedImage.TYPE_INT_RGB);//创建图片
        BufferedImage bg = ImageIO.read(new URL(ImageToolsController.projectImageShowURL+"images/equity_certificate.jpg"));//读取互联网图片
//        BufferedImage er = ImageIO.read(new File("C:\\Users\\Administrator.N1I11YAE7VJWQJS\\Desktop\\1.jpg"));//读取本地图片
        Graphics g = img.getGraphics();//开启画图
        String certificate_number = UUDUtil.getOrderIdByUUId();
        g.drawImage(bg.getScaledInstance(1924,1080, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图
        g.setColor(Color.red);
        g.setFont(new Font(null, Font.PLAIN, 35));
        g.drawString("NO", 380, 495);//绘制文字
        g.setColor(Color.decode("#696969"));
        g.drawString("."+certificate_number, 430, 495);//绘制文字
        g.drawString(user_name, 380, 570);
        g.drawString(amount+"积分", 380, 645);
        g.drawString("充值积分", 380, 720);
        g.drawString(silver_coin_number+"银币", 380, 795);
//        g.drawString("5.000.000", 450, 870);
//        g.drawString("2016年05月04日", 450, 945);
        g.drawString(create_time, 1250, 985);
        ImageObserver imageObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        };
        Image image = null;
        try {
            image = ImageIO.read(new File(PhotoSynthesisUtil.generateQRCode(PhotoSynthesisUtil.certificateSearchUrl+"?number="+certificate_number)));
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("status",false);
            return returnMap;
        }
        g.drawImage(image,900,90, imageObserver);


//        g.setFont(new Font("微软雅黑", Font.PLAIN, 36));
//        g.setColor(Color.decode("0x17994f"));
//        g.drawString("掌控天下", 533/2-36*4/2, 200);

        g.dispose();
        ImageIO.write(img, "jpg", new File(PhotoSynthesisUtil.showUrl+"/"+certificate_number+".jpg"));

        returnMap.put("status",true);
        returnMap.put("number",certificate_number);
        returnMap.put("photo_id",certificate_number+".jpg");
        return returnMap;
    }

    /**
     * 生成二维码<br>
     * 图片大小为200px*200px<br>
     * @param url 图片路径
     * @return    生成的二维码路径
     * @throws Exception 抛出异常
     */
    public static String generateQRCode(String url) throws Exception{
        System.out.println("url===="+url);
        int width = 200;
        int height = 200;
        String format = "png";
        String name = UUDUtil.getOrderIdByUUId()+"ZS";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
        String pathName = PhotoSynthesisUtil.qrcodeURL+"/"+name+".png";
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return PhotoSynthesisUtil.qrcodeURL+"/"+name+".png";
    }

}
