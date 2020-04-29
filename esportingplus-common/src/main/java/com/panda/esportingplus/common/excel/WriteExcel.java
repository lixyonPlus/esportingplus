package com.panda.esportingplus.common.excel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


import com.panda.esportingplus.common.tools.ObjectTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    private static final Logger logger = LoggerFactory.getLogger(WriteExcel.class);
    public static void main(String[] args) {

        Map<String, Object> dataMap=new HashMap<String, Object>();
        dataMap.put("unionName", "1111");
        dataMap.put("userName", "2222");
        dataMap.put("chickenId", "44444");
        dataMap.put("totalFee", 777);
        dataMap.put("currentDate","kksk");
        dataMap.put("operator","sssss");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, "D:/m/reward.xlsx");

    }
    public static void writeExcel(List<Map> dataList, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            logger.info("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            Stream.iterate(0, i -> i + 1).limit(dataList.size()).forEach(index -> {
                // 创建一行：从第二行开始，跳过属性列
                Map dataMap = dataList.get(index);
                Row row = sheet.createRow(index + 1);
                // 得到要插入的每一条记录
                //Map dataMap = dataList.get(index);
                String unionName = dataMap.get("unionName").toString();
                String userName = dataMap.get("userName").toString();
                String chickenId = dataMap.get("chickenId").toString();
                Integer totalFee = ObjectTools.convertToInteger(dataMap.get("totalFee"));
                String currentDate = dataMap.get("currentDate").toString();
                String operator = dataMap.get("operator").toString();
                // 在一行内循环
                row.createCell(0).setCellValue(index + 1);
                row.createCell(1).setCellValue(unionName);
                row.createCell(2).setCellValue(userName);
                row.createCell(3).setCellValue(chickenId);
                row.createCell(4).setCellValue(totalFee);
                row.createCell(6).setCellValue(currentDate);
                row.createCell(7).setCellValue(operator);
            });
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            logger.info("表格生成失败。。。。");
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}

