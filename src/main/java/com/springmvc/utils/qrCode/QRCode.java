package com.springmvc.utils.qrCode;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.springmvc.controller.upload.ImageToolsController;

import java.io.File;
import java.util.Hashtable;

public class QRCode {

    /**
     * 生成二维码
     * @param url         图片地址
     * @param qrcode_name 二维码名称
     * @return 返回生成的二维码地址
     * @throws Exception
     */
    public static String generateQRCode(String url,String qrcode_name) throws Exception{
        int width = 250;
        int height = 250;
        String format = "png";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
        String pathName = ImageToolsController.qrcodeURL+"/"+qrcode_name+".png";
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return ImageToolsController.qrcodeShowURL + "?imageid="+qrcode_name+".png";
    }


}
