package com.springmvc.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Created by wangpeng on 16/12/15.
 */
public class Qrcode {

    private static final String CHARSET = "utf-8";
    private static final int graLength = 750;

    public static void main(String[] args) throws Exception {
        String text = "http://m.chenlankeji.cn/property_system/wnk_business/joinBusinessRecommendRegister?business_id=1";
        String logoPath = "/usr/local/property_system/images";
        StringBuilder sb = new StringBuilder(logoPath);
        BufferedImage sourceImg1 = ImageIO.read(new File("/usr/local/property_system/images"));

        sb.append(120).append("w_").append(120).append("h_1e_1c.src");
        BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);

        File file = new File("D:\\property_system\\qrcode\\1.png");
        FileOutputStream out = new FileOutputStream(file);
        createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);

    }

    /**
     * @param width     底层画布的宽
     * @param height    底层画布的高
     * @param firstImg  第一层的图片
     * @param firstW    第一层的图片宽
     * @param firstH    第一层的图片高
     * @param firstX    第一层的图片相对画布的位置(宽)
     * @param firstY    第一层的图片相对画布的位置(高)
     * @param secondImg 第二层图片
     * @param secondW   第二层图片宽
     * @param secondH   第二层图片高
     * @param secondX   第二层图片相对画布的位置(宽)
     * @param secondY   第二层图片相对画布的位置(高)
     * @param out       完整的二维码
     * @throws IOException
     */
    public static void createQrCodePic(int width, int height, BufferedImage firstImg, int firstW, int firstH, int firstX, int firstY,
                                       BufferedImage secondImg, int secondW, int secondH, int secondX, int secondY,
                                       OutputStream out) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 750, 750);
        g.setBackground(Color.white);
        g.dispose();
        g = bi.createGraphics();
        g.drawImage(firstImg, firstX, firstY, firstW, firstH, null);
        g.drawImage(secondImg, secondX, secondY, secondW, secondH, null);
        ImageIO.write(bi, "JPG", out);
    }

    /**
     * @param url      需要生成二维码的链接
     * @param logoPath logo的地址
     * @param codeX    二维码的宽度
     * @param codeY    二维码的高度
     * @param logoX    logo的宽度
     * @param logoY    logo的高度
     * @return 带logo的二维码图片
     * @throws Exception
     */
    public static BufferedImage generateCode(String url, String logoPath, int codeX, int codeY, int logoX, int logoY) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, codeX, codeY, hints);
        int margin = 6;
        bitMatrix = updateBit(bitMatrix, margin);

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000
                        : 0xFFFFFF);
            }
        }
        image = zoomInImage(image, codeX, codeY);
        try {
            insertImage(image, logoPath, logoX, logoY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * @param matrix 二维码
     * @param margin 边框的大小
     * @return 二维码
     */
    private static BitMatrix updateBit(BitMatrix matrix, int margin) {
        int tempM = margin * 2;
        int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        resMatrix.clear();
        for (int i = margin; i < resWidth - margin; i++) {   //循环，将二维码图案绘制到新的bitMatrix中
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;

    }

    /**
     * @param originalImage 原来二维码大小
     * @param width         改变后二维码的宽
     * @param height        改变后二维码的高
     * @return
     */
    public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;

    }

    /**
     * @param source  二维码
     * @param imgPath logo的路径
     * @param x       相对宽
     * @param y       相对高
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, int x, int y) throws Exception {
        Image src = ImageIO.read(new URL(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        Graphics2D graph = source.createGraphics();
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, height, 10, 10);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}