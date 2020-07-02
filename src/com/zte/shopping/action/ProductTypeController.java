package com.zte.shopping.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.zte.shopping.util.ExcelTest;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.service.IProductTypeService;
import com.zte.shopping.util.ParameterUtil;
import com.zte.shopping.util.ResponseResult;

import javax.servlet.http.HttpServletResponse;

import static com.zte.shopping.util.ExcelTest.createCellTitleStyle;

/**
 * 商品类型管理Handler
 * 
 * 查看mysql字符集:
 *    show variables like '%char%';
 * 修改字符编码:
 *    set character_set_server=utf8;
 *    set character_set_database=utf8;
 *    show variables like '%char%';
 * 
 * @author liyan
 *
 */
@Controller
@RequestMapping("/type")
public class ProductTypeController
{
	@Autowired
	private IProductTypeService productTypeService;

	/**
	 * 查询商品类型列表
	 */
	@RequestMapping("/findAll")
	public ModelAndView findAll(String pageNo, String pageSize)
	{
		ModelAndView modelAndView = new ModelAndView();
		if (ParameterUtil.isnull(pageNo))
		{
			pageNo = DictConstant.PAGE_NO;
		}
		if (ParameterUtil.isnull(pageSize))
		{
			pageSize = DictConstant.PAGE_SIZE;
		}
		// 设置分页
		PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		// 查询所有数据
		List<ProductType> typeList = productTypeService.findAll();
		// 将查询出的所有数据进行分页设置,封装为PageInfo对象
		PageInfo<ProductType> pageProductTypeList = new PageInfo<ProductType>(typeList);

		if (pageProductTypeList.getList().isEmpty() && pageProductTypeList.getPageNum() != -1)
		{
			for(int i = 0; i < pageProductTypeList.getList().size(); i++)
			{
				System.out.println(pageProductTypeList.getList().get(i).getName() + "---------");
			}
		}
		
		modelAndView.addObject("pageProductTypeList", pageProductTypeList);

		modelAndView.setViewName("backend/productTypeManager");

		return modelAndView;
	}

	/**
	 * 导出商品类型列表
	 */
	@RequestMapping("/ExportProTpye")
	public ModelAndView ExportProTpye(HttpServletResponse response)throws IOException  {

		ModelAndView  modelAndView = new ModelAndView();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

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

		// b.创建导出哪些数据的提示信息
		HSSFRow rowTip = sheet.createRow(1);  // 创建提示行对象
		String[] tips = {"编号", "类型名称", "状态"};
		ExcelTest excelTest;
		for (int i = 0; i < tips.length; i++)
		{
			HSSFCell cellTip = rowTip.createCell(i);  // 在提示行对象中  创建cell对象
			// 设置提示信息行的中的单元格样式
			cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)12));
			// 往提示信息行中加入3列  设置信息行中单元格Cell的内容
			cellTip.setCellValue(tips[i]);
		}

		//c.获取导入信息
		List<ProductType> typeList = productTypeService.findAll();
		List<String> list=new ArrayList();
		//d.导入
		for (int j=2;j<=typeList.size();j++){
			HSSFRow rowTip2 = sheet.createRow(2);  // 创建提示行对象
			for (int i=0;i<1;i++){
				list.add(i,String.valueOf(typeList.get(i).getId()));
				list.add(i+1,typeList.get(i).getName());
				list.add(i+2,String.valueOf(typeList.get(i).getStatus()));
			}
			for (int i=0;i<3;i++){
				HSSFCell cellTip = rowTip2.createCell(i);  // 在提示行对象中  创建cell对象
				// 设置提示信息行的中的单元格样式
				cellTip.setCellStyle(createCellTitleStyle(workbook, HSSFColor.BLACK.index, (short)12));
				// 往提示信息行中加入3列  设置信息行中单元格Cell的内容
				cellTip.setCellValue(list.get(i));
			}
		}

		FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\16090\\Desktop\\11.xls");
		workbook.write(fileOutputStream);
		fileOutputStream.close();

		out.flush();
		out.println("<script>alert('导出成功！');</script>");

		modelAndView.setViewName("backend/main");
		return modelAndView;
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
	/**
	 * 添加商品类型(采用ajax来添加)
	 * 
	 * 我们需要让SpringMVC知道,我们需要返回的是数据模型 ---> 采用@ResponseBoby注解标注在当前方法addType上
	 * 
	 * @RequestMapping注解中的produces属性表示:设置响应体的格式   
	 * 即在此方法上加上  @RequestMapping(value = "/addType",produces="text/html;charset=utf-8") 为了解决此方法的响应数据乱码问题
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addType")
	public ResponseResult addType(String name) 
	{
		ResponseResult result = new ResponseResult();

		try 
		{
			productTypeService.addType(name);
			
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			
		} catch (ProductTypeExistException e)
		{
			e.printStackTrace();
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			
		} catch (RequestParameterException e) 
		{
			e.printStackTrace();
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			
		}  catch (Exception e) 
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			
		}

		return result;
	}
	
	/**
	 * 改变商品类型的状态:启用/禁用
	 *    当选择的商品类型   "启用"  ---> "禁用"
	 *    当选择的商品类型   "禁用"  ---> "启用"
	 */
	@RequestMapping("/modifyProductTypeStatus")
	public ModelAndView modifyStatus(String id, String status, String pageNo)
	{
		ModelAndView  modelAndView = new ModelAndView();
		
		productTypeService.modifyStatus(id, status);
		
		modelAndView.setViewName("redirect:findAll?pageNo" + pageNo);
		
		return modelAndView;
	}
	
	/**
	 * 根据 商品类型ID 查询   商品类型信息
	 */
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(String productid)
	{
		ResponseResult  result = new ResponseResult();
		
		
		try 
		{
			ProductType type = productTypeService.findById(productid);
			
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setReturnObject(type);
		} catch (RequestParameterException e) 
		{
			result.setMessage(e.getMessage());
			
			// 响应状态码为请求参数有误,值为:3
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			
		}
		
		return result;
	}
	
	/**
	 * 根据 商品类型ID 修改   商品类型信息
	 */
	@ResponseBody
	@RequestMapping("/modifyName")
	public ResponseResult modifyName(String id, String name)
	{
		ResponseResult  result = new ResponseResult();
		
		try
		{
			productTypeService.modifyName(id, name);
			
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (Exception e) 
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}
		
		return result;
	}
}
