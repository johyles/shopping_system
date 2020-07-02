//package com.zte.shopping.util;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import entity.Student;
//
//public class ExcelUtil
//{
//    public static void exportStudent(List<Student> stuList, OutputStream outputStream)
//    {
//    	try
//    	{
//         // 1.创建一个工作簿Workbook
//       	 HSSFWorkbook workbook = new HSSFWorkbook();
//
//       	 // 2.创建工作表 Sheet
//       	 HSSFSheet sheet = workbook.createSheet("学生信息");
//       	 sheet.setDefaultRowHeightInPoints(20);  // 设置行高
//       	 sheet.setDefaultColumnWidth(12);        // 设置列宽
//
//       	 // 合并单元格:CellRangeAddress
//       	 //     (1)将第一个Row中的7列合并成一列
//       	 //     new CellRangeAddress(0, 0, 0, 6); 下表从0开始   从第1行   第1行   第1列   第7列  合并单元格
//       	 CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 6);
//       	 //     (2)做合并单元格操作
//       	 sheet.addMergedRegion(cellRangeAddress);
//
//       	 // 3.创建行
//       	 //   a.创建标题行,并设置标题
//       	 HSSFRow rowTitle = sheet.createRow(0);
//       	 rowTitle.setHeightInPoints(40);   // 行高
//       	 HSSFCell cellTitle = rowTitle.createCell(0);   // 设置标题列
//       	 cellTitle.setCellStyle(createCellTitleStyle(workbook, HSSFColor.RED.index, (short)13));
//       	 cellTitle.setCellValue("zte学生信息表");
//
//       	 //   b.创建导出哪些数据的提示信息
//       	 HSSFRow rowTip = sheet.createRow(1);
//       	 //
//       	 String[] tips = {"序号", "用户名", "姓名", "性别", "年龄", "班级", "课程"};
//
//       	 for (int i = 0; i < tips.length; i++)
//       	 {
//       		 HSSFCell cellTip = rowTip.createCell(i);
//       		 cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)12));
//       		 // 往提示信息行中加入7列  设置信息行中单元格Cell的内容
//       		 cellTip.setCellValue(tips[i]);
//       	 }
//
//
//       	 // 4.遍历学生信息列表 塞入到对应的单元格中
//       	 if (stuList != null && stuList.size() != 0)
//       	 {
//       		 for (int i = 0; i < stuList.size(); i++)
//       		 {
//       			 Student stu = stuList.get(i);
//
//       			 HSSFRow rowContent = sheet.createRow(i + 2);
//
//       			 // 第1列: 序号
//       			 HSSFCell cellContent0 = rowContent.createCell(0);
//       			 cellContent0.setCellValue(i + 1 + "");
//
//
//       			 // 第2列: 用户名
//       			 HSSFCell cellContent1 = rowContent.createCell(1);
//       			 cellContent1.setCellValue(stu.getUsername());
//
//       			 // 第3列: 姓名
//       			 HSSFCell cellContent2 = rowContent.createCell(2);
//       			 cellContent2.setCellValue(stu.getName());
//
//       			 // 第4列: 性别
//       			 HSSFCell cellContent3 = rowContent.createCell(3);
//       			 /*
//       			 if (stu.getSex().equals("male"))
//       			 {
//       				 "男"
//       			 }else
//       			 {
//       				 "女"
//       			 }
//       			 */
//       			 cellContent3.setCellValue(stu.getSex().equals("male") ? "男" : "女");
//
//       		 	 // 第5列: 年龄
//       			 HSSFCell cellContent4 = rowContent.createCell(4);
//       			 cellContent4.setCellValue(stu.getAge());
//
//       			 // 第6列: 班级
//       			 HSSFCell cellContent5 = rowContent.createCell(5);
//       			 cellContent5.setCellValue(stu.getGrade().getGname());
//
//       			 // 第7列: 课程
//       			 HSSFCell cellContent6 = rowContent.createCell(6);
//       			 cellContent6.setCellValue(stu.getCourse().getCname());
//       		 }
//       	 }
//
//
//			workbook.write(outputStream);
//			workbook.close();
//		} catch (FileNotFoundException e)
//    	{
//			e.printStackTrace();
//		} catch (IOException e)
//    	{
//			e.printStackTrace();
//		}
//
// 		System.out.println("success");
//
//    }
//
//    /**
//     * Cell设置样式
//     * @return
//     */
//	public static HSSFCellStyle createCellTitleStyle(HSSFWorkbook workbook, short fontColor, short fontSize)
//	{
//		HSSFCellStyle cellStyle = workbook.createCellStyle();
//
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 水平居中
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
//
//
//		HSSFFont font = workbook.createFont();
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setColor(fontColor);
//		font.setFontHeightInPoints(fontSize);
//
//		// 将font添加到cellStyle
//		cellStyle.setFont(font);
//
//		return cellStyle;
//	}
//}
