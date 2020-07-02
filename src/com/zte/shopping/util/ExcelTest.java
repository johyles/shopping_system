package com.zte.shopping.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


public class ExcelTest 
{
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		 // 1.创建一个工作簿Workbook
      	 HSSFWorkbook workbook = new HSSFWorkbook();
      	 
      	 // 2.创建工作表 Sheet 
      	 HSSFSheet sheet = workbook.createSheet("productType");
      	 sheet.setDefaultRowHeightInPoints(20);  // 设置行高
      	 sheet.setDefaultColumnWidth(12);        // 设置列宽
      	 

       	 // 合并单元格:CellRangeAddress对象
       	 //     (1)将第一个Row中的3列合并成一列
       	 //     new CellRangeAddress(0, 0, 0, 2); 下标从0开始   从第1行   第1行   第1列   第3列  合并单元格
       	 CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 2);
       	 //     (2)做合并单元格操作
       	 sheet.addMergedRegion(cellRangeAddress);
       	 
       	 // 3.创建行
       	 //   a.创建标题行,并设置标题 --- 导出的商品类型管理数据  
       	 HSSFRow rowTitle = sheet.createRow(0);
       	 rowTitle.setHeightInPoints(40);   // 行高
       	 HSSFCell cellTitle = rowTitle.createCell(0);   // 设置标题列
       	 cellTitle.setCellStyle(createCellTitleStyle(workbook, HSSFColor.RED.index, (short)14));
       	 cellTitle.setCellValue("导出的商品类型管理数据");
       	 
    	 //   b.创建导出哪些数据的提示信息
       	 HSSFRow rowTip = sheet.createRow(1);  // 创建提示行对象
       	 // 
       	 String[] tips = {"编号", "类型名称", "状态"};
       	 
       	 for (int i = 0; i < tips.length; i++)
       	 {
       		 HSSFCell cellTip = rowTip.createCell(i);  // 在提示行对象中  创建cell对象
       		 
       		 // 设置提示信息行的中的单元格样式
       		 cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)12));
       		 
       		 // 往提示信息行中加入3列  设置信息行中单元格Cell的内容
       		 cellTip.setCellValue(tips[i]);
       	 }

		FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\16090\\Desktop\\11.xls");
       	 workbook.write(fileOutputStream);
         fileOutputStream.close();
       	 
       	 
	}
	
	
	/**
     * Cell设置样式
     */
	public static HSSFCellStyle createCellTitleStyle(HSSFWorkbook workbook, short fontColor, short fontSize) 
	{
		HSSFCellStyle cellStyle = workbook.createCellStyle();//单元格对象
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
		
		
		HSSFFont font = workbook.createFont();//字体对象
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 加粗
		font.setColor(fontColor);   // 字体颜色
		font.setFontHeightInPoints(fontSize);  // 字体大小
		
		// 将font添加到cellStyle
		cellStyle.setFont(font);
		
		return cellStyle;
	}
}
