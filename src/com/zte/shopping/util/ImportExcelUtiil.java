package com.zte.shopping.util;

import com.zte.shopping.entity.Staff;
import com.zte.shopping.entity.User;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtiil {

    /*public static void main(String[] args){
        String fileName
    }*/


    public static List<InfoVo> getworkbook(String fileName, InputStream inputStream) throws IOException {
        Workbook wb=null;
        List<InfoVo> list=new ArrayList<>();
        //还有一种判断的但是比正则表达式复杂一些StringUtils.endsWithIgnoreCase(fileName,".xls")||StringUtils.endsWithIgnoreCase(fileName,".xlsx");
        // if 判断start
        // "^$"               ^ 匹配开头   $ 匹配结尾
        // "^(xls)|(xlsx)$"   整体匹配 (xls)  or (xlsx)中的一个
        // "^(?i)(xls)|(xlsx)$"   忽略大小写 (?i)   i表示忽略 ignore
        // "^\\.(?i)(xls)|(xlsx)$"  .号不是正则表达式中的. 表示任意一个字符  想要真正的表示一个.号 就要转义  \\.   匹配.xls .xlsx .XLS .XLSX 中任意一个
        // -----------------------------------------------------------------------------------------------------------------
        //  "^.+\\.(?i)(xls)|(xlsx)$"    这里的.号才是正则表达式中的. 表示任意一个字符
        // ?  匹配0次 or 1次          问零一
        // +  匹配1次 or 多次         加一多
        // *  匹配0次 or 多次         星有某
        //-------------1.判断工作簿
        if (fileName.matches( "^.+\\.(?i)(xls)|(xlsx)$"))
        {
            // 做导入功能
            // 匹配 xxx.xls(03版)    HSSFWorkbook  表示一个工作簿
            if (fileName.matches( "^.+\\.(?i)(xls)$"))
            {
                // 1.读取工作簿 Workbook
               //wb = new HSSFWorkbook(new FileInputStream(file));  // HSSFWorkbook .xls(03版)
                wb = new HSSFWorkbook(inputStream);  // HSSFWorkbook .xls(03版)
            }else if ("1.xls".matches( "^.+\\.(?i)(xlsx)$"))  // 匹配  xxx.xlsx(07版)   XSSFWorkbook  表示一个工作簿
            {
                // 1.读取工作簿 Workbook
               // wb = new XSSFWorkbook(new FileInputStream(file));
                wb = new XSSFWorkbook(inputStream);
            }
        }
        //------------2.读取工作表 Sheet
        Sheet sheet=wb.getSheetAt(0);
        //----------3.读取数据行
        if(sheet.getPhysicalNumberOfRows()>=2){
            InfoVo infoVo = null;
            for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
                infoVo=new InfoVo();
                Row row=sheet.getRow(i);
                //staffName
                infoVo.setStaffName(row.getCell(1).getStringCellValue());
                //loginName
                infoVo.setLoginName(row.getCell(2).getStringCellValue());
                //phone
                infoVo.setPhone(row.getCell(3).getStringCellValue());
                //email
                infoVo.setEmail(row.getCell(4).getStringCellValue());
                //role
                infoVo.setRole(row.getCell(5).getStringCellValue());
                //deptName
                infoVo.setDeptName(row.getCell(6).getStringCellValue());
                System.out.println(infoVo.getDeptName()+"==");
                list.add(infoVo);
            }
        }
        return list;
    }

    public static List<UserVo> getworkbook2(String fileName, InputStream inputStream) throws IOException {
        Workbook wb=null;
        List<UserVo> list=new ArrayList<>();
        if (fileName.matches( "^.+\\.(?i)(xls)|(xlsx)$"))
        {
            // 做导入功能
            // 匹配 xxx.xls(03版)    HSSFWorkbook  表示一个工作簿
            if (fileName.matches( "^.+\\.(?i)(xls)$"))
            {
                // 1.读取工作簿 Workbook
                //wb = new HSSFWorkbook(new FileInputStream(file));  // HSSFWorkbook .xls(03版)
                wb = new HSSFWorkbook(inputStream);  // HSSFWorkbook .xls(03版)
            }else if ("1.xls".matches( "^.+\\.(?i)(xlsx)$"))  // 匹配  xxx.xlsx(07版)   XSSFWorkbook  表示一个工作簿
            {
                // 1.读取工作簿 Workbook
                // wb = new XSSFWorkbook(new FileInputStream(file));
                wb = new XSSFWorkbook(inputStream);
            }
        }
        //------------2.读取工作表 Sheet
        Sheet sheet=wb.getSheetAt(0);
        //----------3.读取数据行
        if(sheet.getPhysicalNumberOfRows()>=2){
            UserVo userVo = null;
            for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
                userVo=new UserVo();
                Row row=sheet.getRow(i);
                //staffName
                userVo.setUserName(row.getCell(1).getStringCellValue());
                //loginName
                userVo.setLoginName(row.getCell(2).getStringCellValue());
                //phone
                userVo.setPhone(row.getCell(3).getStringCellValue());
                //email
                userVo.setAddress(row.getCell(4).getStringCellValue());
                System.out.println(userVo.getLoginName()+"==");
                list.add(userVo);
            }
        }
        return list;
    }

}
