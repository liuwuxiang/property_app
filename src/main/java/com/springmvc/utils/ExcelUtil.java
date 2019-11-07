package com.springmvc.utils;

import com.springmvc.controller.upload.ExcelToolsController;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Exce操作工具类
 */
public class ExcelUtil {

    /**
     * 生成excel表格
     * @param cardsMap
     * @return
     */
    public static String generateExcel(List<Map<String,Object>> cardsMap)
    {
        //文件名
        String fileName = UUDUtil.getOrderIdByUUId();
        //表格存储路径以及文件名
        String path = ExcelToolsController.excelShowURL + "/"+fileName+".xls";
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("卡号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < cardsMap.size(); i++)
        {
            Map<String,Object> map = cardsMap.get(i);
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((String)map.get("card_number"));
            row.createCell((short) 1).setCellValue((String)map.get("card_pwd"));
        }
        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ExcelToolsController.excelShowURL +"?excelid="+fileName+".xls";
    }
}
