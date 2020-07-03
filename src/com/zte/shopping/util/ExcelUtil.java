package com.zte.shopping.util;

import com.zte.shopping.util.InfoVo;
import com.zte.shopping.util.UserVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExcelUtil {

    public static void main(String[] args) throws IOException {
        String title="员工信息表";
        String[] tip={"编号","员工姓名","登录账号","手机号","邮箱","角色","所在部门"};
        List<InfoVo> list=new ArrayList<>();
        InfoVo infoVo=new InfoVo("1","呜呜","嗯嗯","丰富","丰富","丰富","爱上");
        list.add(infoVo);
        export(title,tip,list);
    }
    public static void exportUser(String title,String[] tips,List<UserVo> userVos) throws IOException {
        // 1.创建一个工作簿Workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.创建工作表 Sheet
        HSSFSheet sheet = workbook.createSheet("userSheet");
        sheet.setDefaultRowHeightInPoints(20);  // 设置行高
        sheet.setDefaultColumnWidth(12);        // 设置列宽
        // 合并单元格:CellRangeAddress对象
        //     (1)将第一个Row中的3列合并成一列
        //     new CellRangeAddress(0, 0, 0, 2); 下标从0开始   从第1行   第1行   第1列   第3列  合并单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, tips.length-1);
        //     (2)做合并单元格操作
        sheet.addMergedRegion(cellRangeAddress);
        // 3.创建标题行,并设置标题 --- 导出的商品类型管理数据
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(40);   // 行高
        HSSFCell cellTitle = rowTitle.createCell(0);   // 设置标题列
        cellTitle.setCellStyle(createCellTitleStyle(workbook, HSSFColor.RED.index, (short)14));
        cellTitle.setCellValue(title);//设置标题文字
        //   b.创建导出哪些数据的提示信息
        HSSFRow rowTip = sheet.createRow(1);  // 创建提示行对象

        //输出提示行单元格
        for(int i=0;i<tips.length;i++){
            HSSFCell cellTip = rowTip.createCell(i);  // 在提示行对象中  创建cell对象
            // 设置提示信息行的中的单元格样式
            cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BROWN.index, (short)12));
            // 往提示信息行中加入3列  设置信息行中单元格Cell的内容
            cellTip.setCellValue(tips[i]);
            //System.out.println(tips[i]);
        }
        //输出数据单元格
        for(int i=0;i<userVos.size();i++){
            HSSFRow rowData=sheet.createRow(i+2);
            String[] data={userVos.get(i).getUserid(),userVos.get(i).getUserName(),userVos.get(i).getLoginName(),
                    userVos.get(i).getPhone(),userVos.get(i).getAddress()};
            for(int j=0;j<data.length;j++){
                HSSFCell cellData=rowData.createCell(j);// 在提示行对象中  创建cell对象
                // 设置提示信息行的中的单元格样式
                cellData.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)10));
                // 往提示信息行中加入3列  设置信息行中单元格Cell的内容
                cellData.setCellValue(data[j]);
                //System.out.print(data[j]+"==");
            }
        }

        Random random=new Random();
        String pathtitile=title+ random.nextInt(10);

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\"+pathtitile+".xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("导出成功");
    }

    public static void export(String title,String[] tips,List<InfoVo> infoVos) throws IOException {
        // 1.创建一个工作簿Workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.创建工作表 Sheet
        HSSFSheet sheet = workbook.createSheet("staffSheet");
        sheet.setDefaultRowHeightInPoints(20);  // 设置行高
        sheet.setDefaultColumnWidth(12);        // 设置列宽
        // 合并单元格:CellRangeAddress对象
        //     (1)将第一个Row中的3列合并成一列
        //     new CellRangeAddress(0, 0, 0, 2); 下标从0开始   从第1行   第1行   第1列   第3列  合并单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, tips.length-1);
        //     (2)做合并单元格操作
        sheet.addMergedRegion(cellRangeAddress);
        // 3.创建标题行,并设置标题 --- 导出的商品类型管理数据
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(40);   // 行高
        HSSFCell cellTitle = rowTitle.createCell(0);   // 设置标题列
        cellTitle.setCellStyle(createCellTitleStyle(workbook, HSSFColor.RED.index, (short)14));
        cellTitle.setCellValue(title);//设置标题文字
        //   b.创建导出哪些数据的提示信息
        HSSFRow rowTip = sheet.createRow(1);  // 创建提示行对象

        //输出提示行单元格
        for(int i=0;i<tips.length;i++){
            HSSFCell cellTip = rowTip.createCell(i);  // 在提示行对象中  创建cell对象
            // 设置提示信息行的中的单元格样式
            cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BROWN.index, (short)12));
            // 往提示信息行中加入3列  设置信息行中单元格Cell的内容
            cellTip.setCellValue(tips[i]);
            //System.out.println(tips[i]);
        }
       //输出数据单元格
        for(int i=0;i<infoVos.size();i++){
            HSSFRow rowData=sheet.createRow(i+2);
            String[] data={infoVos.get(i).getId(),infoVos.get(i).getStaffName(),infoVos.get(i).getLoginName(),
                    infoVos.get(i).getPhone(),infoVos.get(i).getEmail(),
                    infoVos.get(i).getRole(),infoVos.get(i).getDeptName()};
            for(int j=0;j<data.length;j++){
                HSSFCell cellData=rowData.createCell(j);// 在提示行对象中  创建cell对象
                // 设置提示信息行的中的单元格样式
                cellData.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)10));
                // 往提示信息行中加入3列  设置信息行中单元格Cell的内容
                cellData.setCellValue(data[j]);
                //System.out.print(data[j]+"==");
            }
        }

        Random random=new Random();
        String pathtitile=title+ random.nextInt(10);

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\"+pathtitile+".xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("导出成功");
    }

    public static HSSFCellStyle createCellTitleStyle(HSSFWorkbook workbook, short fontColor, short fontSize)
    {
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中


        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 加粗
        font.setColor(fontColor);   // 字体颜色
        font.setFontHeightInPoints(fontSize);  // 字体大小

        // 将font添加到cellStyle
        cellStyle.setFont(font);

        return cellStyle;
    }
}
