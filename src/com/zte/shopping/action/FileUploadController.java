package com.zte.shopping.action;

import com.zte.shopping.service.FileUploadService;
import com.zte.shopping.service.IUserService;
import com.zte.shopping.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.zte.shopping.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileUploadController 
{
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private IUserService iuserService;
	 /**
	  * CommonsMultipartResolver
	  * 输入http://localhost:8080/file/showUpload
	  * 跳转到upload.jsp页面
	  */
	 @RequestMapping("/showUpload")
     public String showUpload()
     {
		return "/upload";
     }
	 
	 
	 @RequestMapping("/doUpload")
	 public String upload(@RequestParam("fileUpload") CommonsMultipartFile file, HttpServletRequest request, Model model, HttpSession session,
						  @RequestParam(value = "userName",required = false) String userName) throws IllegalStateException, IOException
	 {
		// 原始的文件名
		String oriFilename = file.getOriginalFilename();
		
		// 上传到服务器的文件名(唯一性的值)
		//String serverFileName = UploadFileUtil.renameUploadFileName(oriFilename);
		 String serverFileName = UploadFileUtil.renameUploadFileName(oriFilename);
		
		// 要上传到的服务器路径  ---> request.getServetContext()
		// request.getServletContext().getRealPath("")  ---> E:\apache-tomcat-8.5.38\webapps\fileupload\
		String serverPath = request.getServletContext().getRealPath("upload/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		//访问路径
	    String fangWenPath = "upload/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/"+serverFileName;
		File file2 = new File(serverPath);
		file2.mkdirs();
		
		// SpringMVC中提供的做文件上传方法
		file.transferTo(new File(serverPath, serverFileName));  //  throws IllegalStateException, IOException
		
		// 对DB的操作
		// upload\2020-06-30\8a92c4dc-b0b9-4704-b86d-3dd034894857xxx.png  这个相对路径入库
		// t_user表中  imgUrl 字段值 upload\2020-06-30\8a92c4dc-b0b9-4704-b86d-3dd034894857xxx.png
		// 在页面中的处理  取出upload\2020-06-30\8a92c4dc-b0b9-4704-b86d-3dd034894857xxx.png  这个相对路径 
		// 前面再拼上  ${pageContext.request.contextPath}/${user.imgUrl} --- 页面就能显示这张图片了
		
		 System.out.println(serverFileName + "-----------------------" + serverPath);
		 System.out.println("*****************"+fangWenPath);
		 session.setAttribute("fangWenPath",fangWenPath);
		 System.out.println("获取到的登录用户名********************"+userName);
		 if(userName!=null){
			 Integer id=iuserService.selectByName(userName).getUserId();
			 System.out.println("用户的id*******************"+id);
			 fileUploadService.deleteById(id);
			 fileUploadService.addPath(id,fangWenPath);
		 }

		/*return "redirect:/file/showUpload";*/
		 return "center";
		 
	 }
	 
}
