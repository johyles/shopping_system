package com.zte.shopping.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zte.shopping.util.CreateCodeUtil;

/**
 * 验证码Handler
 * 
 * @author liyan
 *
 */
@Controller
@RequestMapping("/code")
public class CodeController 
{
	 /**
	  * 生成验证码
	  * 
	  * http://localhost:8080/shopping_ssm/showLogin
	  */
	 @RequestMapping("/show")
     public void show(HttpServletRequest request, HttpServletResponse response) throws ImageFormatException, IOException
     {
    	 Random random = new Random();
    	 
    	 // java.awt.image.BufferedImage
    	 BufferedImage image = new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
    	 
    	 Graphics graphics = image.getGraphics();
    	 graphics.fillRect(0, 0, 100, 20);
    	 graphics.setColor( new Color( random.nextInt(256), random.nextInt(256), random.nextInt(256) )  );
    	 graphics.setFont(new Font("宋体", Font.BOLD+ Font.ITALIC, 18));
    	 
    	 // 生成4位的随机数(数字和字母组合)
    	 StringBuffer code = CreateCodeUtil.createCode(random);
    	 // 生成4位的纯数字验证码
    	 //StringBuffer code = CreateCodeUtil.createCodeNum(random);
    	 // 生成 成语验证码
    	 //String code = CreateCodeUtil.createCodeIdiom(random);
    	 
    	 // 绘制验证码字符串
    	 graphics.drawString(code.toString(), 5, 15);
    	 
    	 // 将验证码放入到session中
    	 request.getSession().setAttribute("code", code.toString());
    	 
    	 // throws IOException
    	 OutputStream out = response.getOutputStream();
    	 
    	 // 将验证码放入到图片中
    	 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    	 // throws ImageFormatException, IOException
    	 encoder.encode(image);
     }

	@RequestMapping("/check")
	private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		req.setCharacterEncoding("utf-8");
		String inputcode = req.getParameter("inputcode");

		String code = (String) req.getSession().getAttribute("code");

		if(inputcode.equals(code))
		{
			out.print("正确");
		}else
		{
			out.print("错误");
		}

		out.flush();
		out.close();
	}
}
