package com.zy;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


/**
 * Created by Administrator on 2018/7/20.
 */
public class ExportExcel {

    /**
     *  导出Excel
     * @param sheetName 表格 sheet 的名称
     * @param headers  标题名称
     * @param dataList 需要显示的数据集合
     * @param exportExcelName 导出excel文件的名字
     */
    public  static void exportExcel(String sheetName, List<Map<String, Object>> dataList,
                                    String[] headers,String exportExcelName) {

        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);

        // 生成表格中非标题栏的样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);//背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成表格中非标题栏的字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);


        // 设置表格标题栏的样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置标题栏字体
        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(HSSFColor.WHITE.index);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBold(true);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);

        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(titleStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<Map<String, Object>> it = dataList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Map<String, Object> data = it.next();
            int i = 0;
            for(String key : data.keySet()){
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                XSSFRichTextString text = new XSSFRichTextString(data.get(key)+"");
                cell.setCellValue(text);
                i++;
            }
        }
        try {

            OutputStream out = null;
            String tmpPath = "D:/" + exportExcelName + ".xlsx";
            out = new FileOutputStream(tmpPath);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<>();
        // 使用 LinkedHashMap 保证有序，即标题和数据对应上
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("id", 1);
        map1.put("name", "张三");
        map1.put("age", 23);
        map1.put("sex", "男");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("id", 2);
        map2.put("name", "李四");
        map2.put("age", 20);
        map2.put("sex", "女");

        Map<String, Object> map3 = new LinkedHashMap<>();
        map3.put("id", 3);
        map3.put("name", "王五");
        map3.put("age", 19);
        map3.put("sex", "男");

        Map<String, Object> map4 = new LinkedHashMap<>();
        map4.put("id", 4);
        map4.put("name", "赵六");
        map4.put("age", 18);
        map4.put("sex", "女");

        Map<String, Object> map5 = new LinkedHashMap<>();
        map5.put("id", 5);
        map5.put("name", "小七");
        map5.put("age", 22);
        map5.put("sex", "男");

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);

        return data;
    }

}
