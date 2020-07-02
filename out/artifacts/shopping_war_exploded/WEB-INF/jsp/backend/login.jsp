<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>在线商城管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/houtailogin.js"></script>
	  <script type="text/javascript">
		  function showCode(str){
			  var xmlhttp;
			  if(str.length==0){
				  document.getElementById("checkCode").innerHTML="&nbsp;";
				  return;
			  }
			  if(window.XMLHttpRequest){
				  xmlhttp = new XMLHttpRequest();
			  }else{
				  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			  }
			  xmlhttp.onreadystatechange=function(){
				  if(xmlhttp.readyState==4&&xmlhttp.status==200){
					  //alert(xmlhttp.responseText);
					  document.getElementById("checkCode").innerHTML=xmlhttp.responseText;
				  }
			  }
			  xmlhttp.open("GET","${pageContext.request.contextPath}/code/check?inputcode="+str,true);
			  xmlhttp.send();
		  }
		  $(function()
		  {
			  // 点击 "验证码图片" 切换  验证码
			  $("#image").click(function()
			  {
				  $(this).attr("src", "${pageContext.request.contextPath}/code/show?id=" + new Date().getTime());
			  });

			  // 点击 "看不清" 切换  验证码
			  $("#invisit").click(function()
			  {
				  $("#image").attr("src", "${pageContext.request.contextPath}/code/show?id=" + new Date().getTime());
			  });
		  });
	  </script>

  </head>
  <body>
  	<!-- 使用自定义css样式 div-signin 完成元素居中-->
    <div class="containercc div-signin">
      <div class="panel panel-primary div-shadow">
      	<!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
	    <div class="panel-heading">
	    	<h3>在线商城 3.0</h3>
	    	<span>Network Mall Manager System</span>
	    </div>
	    <div class="panel-body">
	      <!-- login form start -->
	      <form  action="${pageContext.request.contextPath}/login/login.html" class="form-horizontal ccc" method="get" id="loginFrm">
		     <div class="form-group">
		       <label class="col-sm-3 control-label">用户名：</label>
		       <div class="col-sm-9">
		         <input class="form-control" type="text" placeholder="请输入用户名"  id="username" name="username" data-toggle="tooltip">
		       </div>
		     </div>

		     <div class="form-group">
		       <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
		       <div class="col-sm-9">
		         <input class="form-control" type="password" placeholder="请输入密码" id="pwd" name="pwd" data-toggle="tooltip">
		       </div>
		     </div>

		     <div class="form-group">
		       <label class="col-sm-3 control-label">身&nbsp;&nbsp;&nbsp;&nbsp;份：</label>
		       <div class="col-sm-9">
		       	 <select class="form-control" name="state" id="state" data-toggle="tooltip">
		       	 	<option value="-1">--请选择身份--</option>
		       	 	<option value="2002">普通管理员</option>
		       	 	<option value="2001">系统管理员</option>
		       	 </select>
		       </div>
		    </div>
		     <div class="form-group">
		       <label class="col-sm-3 control-label">验证码：</label>
		       <div class="col-sm-4">
		         <input class="form-control" type="text" id="inputcode" name ="inputcode" placeholder="请输入验证码"
						data-toggle="tooltip" onkeyup="showCode(this.value)" autocomplete="off"><label id="checkCode">&nbsp;</label>
		       </div>
		       <div class="col-sm-2">
		       	  <!-- 验证码图片加载(需引入验证码文件)图像高度经过测试,建议不要修改 -->
			      <img id="image" class="img-rounded create-code" src="${pageContext.request.contextPath}/code/show" alt="验证码" style="height: 32px; width: 70px;"/>
		       </div>
		       <div class="col-sm-2 create-code">
		         <button id="invisit" type="button" class="btn btn-link">看不清</button>
		       </div>
		    </div>
		    <div class="form-group">
		       <div class="col-sm-9  col-sm-offset-3 padding-left-0">
		       	 <div class="col-sm-4">
			         <%--<button type="button" class="btn btn-link btn-block">忘记密码？</button>--%>
						 <span style="color:red;">${logMsg }</span>
		       	 </div>
		       	 <div class="col-sm-4">
			         <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
		       	 </div>
		       	 <div class="col-sm-4">
			         <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
		       	 </div>
		       </div>
		    </div>
	      </form>
	      <!-- login form end -->
	    </div>
	  </div>
    </div>
<%--    <!-- 页尾 版权声明 -->
    <div class="containercc">
		<div class="col-sm-12 foot-css" id="ccc">
		        <p class="text-muted credit">
		            Copyright © 2017 南京中兴 版权所有
		        </p>
	    </div>
    </div>--%>
  </body>
</html>