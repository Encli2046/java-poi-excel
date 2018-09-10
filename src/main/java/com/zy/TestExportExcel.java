package com.zy;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/20.
 */
public class TestExportExcel {

    public static void main(String[] args) {
        List<Map<String, Object>> data = ExportExcel.getData();
        String sheetName = "学生表";
        String[] headers = {"ID","名称","年龄","性别"};
        String exportExcelName = "student";
        ExportExcel.exportExcel(sheetName, data, headers, exportExcelName);
    }
}
