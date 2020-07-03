package com.zte.shopping.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.entity.Dept;
import com.zte.shopping.entity.Staff;
import com.zte.shopping.exception.*;
import com.zte.shopping.service.IDeptManagerService;
import com.zte.shopping.util.ParameterUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.service.IStaffService;
import com.zte.shopping.util.ResponseResult;

/**
 * 后台 --- 管理员管理Hanlder
 * 
 * @author liyan
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffController 
{
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptManagerService deptService;

	@RequestMapping(value = "/main.html")
	public String gostaff_main(){
		return "backend/main";
	}

	/**
	 * 动态  模糊  查询   管理员信息
	 */
	@RequestMapping("/staffManager.html")
	public ModelAndView findFuzzyByParamList(Staff staffParameter, String deptId, String pageNo, String pageSize)
	{
		ModelAndView  modelAndView = new ModelAndView();

		if (ParameterUtil.isnull(pageNo))
		{
			pageNo = DictConstant.PAGE_NO;
		}

		if (ParameterUtil.isnull(pageSize))
		{
			pageSize = DictConstant.PAGE_SIZE;
		}

		PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		List<Staff> staffList = staffService.findFuzzyByParamList(staffParameter, deptId);

		PageInfo<Staff> pageStaffList = new PageInfo<Staff>(staffList);

		//查询所有部门列表
		List<Dept> deptList = deptService.selectDeptAll();

		modelAndView.addObject("deptList", deptList);
		modelAndView.addObject("staffList", pageStaffList);

		modelAndView.setViewName("backend/staffManager");

		return modelAndView;
	}



	/**
	 * 添加管理员
	 *
	 * 员工账号不能重复
	 * 密码需要使用MD5加密
	 * 创建人      为    当前登录员工
	 * 创建时间   为   当前时间
	 *
	 * 默认田间的员工为有效状态
	 *
	 */
	@ResponseBody
	@RequestMapping("/addStaff")
	public ResponseResult addStaff(Staff staff, String deptId, HttpSession session)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			staffService.addStaff(staff, deptId, session);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

		} catch (StaffExistException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);

		} catch (LoginDisabledException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_LOGIN_TIMEOUT);

		} catch (NoPromissionException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_NO_PROMISSON);
		} catch(Exception e)
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}

	/**
	 * 做修改管理员操作时  查询出修改页面的管理员信息
	 *
	 * 根据id查询员工信息
	 */
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(String staffId)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			Staff staff = staffService.findById(staffId);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

			result.setReturnObject(staff);

		} catch (RequestParameterException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

		} catch(Exception e)
		{

			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}
	/**
	 * 修改员工管理员
	 */
	@ResponseBody
	@RequestMapping("/modifyStaff")
	public ResponseResult modifyStaff(Staff staff, String deptId)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			staffService.modifyStaff(staff, deptId);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

		} catch(RequestParameterException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

		} catch (Exception e)
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}

	//管理员禁用启用转换部分--已完成
	@RequestMapping(value = "/modifyStaffStatus")
	public ModelAndView modifyStaffStatus(String staffId,String isValid){
		ModelAndView mv = new ModelAndView();
		Staff staff=new Staff();
		int validcode= Integer.parseInt(isValid);
		int id=Integer.parseInt(staffId);
		staff.setStaffId(id);
		if(validcode==1){
			staff.setIsValid(0);
			if(staffService.modifyStaff(staff)){
				System.out.println("禁用成功");
			}else {
				System.out.println("禁用失败");
			}
		}else if(validcode==0){
			staff.setIsValid(1);
			if(staffService.modifyStaff(staff)){
				System.out.println("启用成功");
			}else {
				System.out.println("启用失败");
			}
		}else{
			System.out.println("validcode异常");
		}
		mv.setViewName("redirect:/staff/staffManager.html");
		return mv;
	}

	//导出管理员信息表
	@RequestMapping("/OutputExcel")
	public ModelAndView exportStaff(Staff staffParameter, String staffName, String loginName,
									String phone, String email, String role , String isValid, String deptId , String pageNo,
									HttpServletResponse response)throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		ModelAndView modelAndView = new ModelAndView();
		try
		{
			// 1.创建一个工作簿Workbook
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 2.创建工作表 Sheet
			HSSFSheet sheet = workbook.createSheet("staff");
			sheet.setDefaultRowHeightInPoints(20);  // 设置行高
			sheet.setDefaultColumnWidth(12);        // 设置列宽

			// 合并单元格:CellRangeAddress
			//     (1)将第一个Row中的7列合并成一列
			//     new CellRangeAddress(0, 0, 0, 6); 下表从0开始   从第1行   第1行   第1列   第8列  合并单元格
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 7);
			//     (2)做合并单元格操作
			sheet.addMergedRegion(cellRangeAddress);

			// 3.创建行
			//   a.创建标题行,并设置标题
			HSSFRow rowTitle = sheet.createRow(0);
			rowTitle.setHeightInPoints(40);   // 行高
			HSSFCell cellTitle = rowTitle.createCell(0);   // 设置标题列
			cellTitle.setCellStyle(createCellTitleStyle(workbook, HSSFColor.RED.index, (short)13));
			cellTitle.setCellValue("zte管理员信息表");

			//   b.创建导出哪些数据的提示信息
			HSSFRow rowTip = sheet.createRow(1);
			//
			String[] tips = {"序号", "姓名", "账号", "电话", "邮箱", "部门", "状态","角色"};

			for (int i = 0; i < tips.length; i++)
			{
				HSSFCell cellTip = rowTip.createCell(i);
				cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)12));
				// 往提示信息行中加入7列  设置信息行中单元格Cell的内容
				cellTip.setCellValue(tips[i]);
			}

			// 4.遍历学生信息列表 塞入到对应的单元格中
			List<Staff> staffList = staffService.findFuzzyByParamList(staffParameter, deptId);
			if (staffList != null && staffList.size() != 0)
			{
				for (int i = 0; i < staffList.size(); i++)
				{
					Staff staff = staffList.get(i);

					HSSFRow rowContent = sheet.createRow(i + 2);

					// 第1列: 序号
					HSSFCell cellContent0 = rowContent.createCell(0);
					cellContent0.setCellValue(i + 1 + "");


					// 第3列: 账号
					HSSFCell cellContent2 = rowContent.createCell(2);
					cellContent2.setCellValue(staff.getLoginName());

					// 第2列: 姓名
					HSSFCell cellContent1 = rowContent.createCell(1);
					cellContent1.setCellValue(staff.getStaffName());

					// 第4列: 电话
					HSSFCell cellContent3 = rowContent.createCell(3);
					cellContent3.setCellValue(staff.getPhone());

					// 第5列: 年龄
					HSSFCell cellContent4 = rowContent.createCell(4);
					cellContent4.setCellValue(staff.getEmail());

					// 第6列: 部门
					HSSFCell cellContent5 = rowContent.createCell(5);
					if(staff.getDept()==null){
						cellContent5.setCellValue("null");
					}else {

						cellContent5.setCellValue(staff.getDept().getDeptName());
					}

					// 第7列: 状态
					HSSFCell cellContent6 = rowContent.createCell(6);
					cellContent6.setCellValue(staff.getIsValid()==1?"启动":"禁用");

					// 第8列: 角色
					HSSFCell cellContent7 = rowContent.createCell(7);
					cellContent7.setCellValue(staff.getRole().equals("2001") ? "系统管理员" : "普通管理员");
				}
			}

			FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\16090\\Desktop\\11.xls");

			workbook.write(fileOutputStream);
			workbook.close();
			out.flush();
			out.println("<script>alert('导出成功！');</script>");
			modelAndView.setViewName("backend/staffManager");

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return modelAndView;

	}

	/**
	 * Cell设置样式
	 * @return cellStyle
	 */
	public static HSSFCellStyle createCellTitleStyle(HSSFWorkbook workbook, short fontColor, short fontSize)
	{
		HSSFCellStyle cellStyle = workbook.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中


		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(fontColor);
		font.setFontHeightInPoints(fontSize);

		// 将font添加到cellStyle
		cellStyle.setFont(font);

		return cellStyle;
	}

	//导入管理员信息
	@RequestMapping("/ImportExcel")
	public ModelAndView inputStaff(String pageNo, HttpSession session,HttpServletResponse response)throws IOException {
		ModelAndView modelAndView=new ModelAndView();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\16090\\Desktop\\1234.xlsx");

			// 2.读取工作表 Sheet
			XSSFSheet sheet = workbook.getSheetAt(0);

			// 3.读取数据行
			int lastRowNum=sheet.getLastRowNum();

			Staff staff=new Staff();

			// for 循环 start
			for (int i = 1; i <=lastRowNum; i++)
			{
				// 获取数据行
				XSSFRow row= sheet.getRow(i);

				if(row!=null) {

					// 4. 读取单元格中的内容  并且将响应的值放入student对象中
					String name = row.getCell(1).getStringCellValue();
					// 姓名
					staff.setStaffName(name);

					if(row.getCell(2)!=null) {
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						String username = row.getCell(2).getStringCellValue();
						// 用户名
						staff.setLoginName(username);
					}

					// 密码
					if(row.getCell(3)!=null) {
						row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						String pwd = row.getCell(3).getStringCellValue();
						staff.setPassword(pwd);
					}
					// 电话
					if(row.getCell(4)!=null) {
						row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						String phone = row.getCell(4).getStringCellValue();
						staff.setPhone(phone);
					}

					// 邮箱
					if(row.getCell(5)!=null){
						 row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						 String email = row.getCell(5).getStringCellValue();
						 staff.setEmail(email);
					}

					// 部门
					if(row.getCell(6)!=null) {
						row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						if (row.getCell(6).getStringCellValue()!=null&&!row.getCell(6).getStringCellValue().equals("")){
							Integer depId = Integer.valueOf(row.getCell(6).getStringCellValue());
							staff.setDeptId(depId);
						}
					}
					String depId1 = row.getCell(6).getStringCellValue();


					// 状态
					staff.setIsValid("启动".equals(row.getCell(7).getStringCellValue()) ? 1 : 0);

					//角色
					staff.setRole("普通管理员".equals(row.getCell(8).getStringCellValue()) ? "2002" : "2001");

					// 导入管理员信息操作
					staffService.addStaff(staff, depId1,session);
				}
			}

			// for 循环 end
			// 结束事务
			workbook.close();

			//modelAndView.setViewName("backend/staffManager");
			out.flush();
			out.println("<script>alert('导入成功！');</script>");
			modelAndView.setViewName("backend/staffManager");

			// if 判断end

		} catch (IOException | StaffExistException | NoPromissionException | LoginDisabledException e)
		{
			e.printStackTrace();
			out.flush();
			out.println("<script>alert('导入失败！');</script>");
			modelAndView.setViewName("backend/staffManager");
		}
		return modelAndView;

	}

}
