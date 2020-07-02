<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>在线购物商城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>

    <!-- 引入 bootstrap分页插件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-paginator.js"></script>

    <script type="text/javascript">
        $(function()
        {
            $("#minPrice").attr("value", "${parameter.minPrice }");
            $("#maxPrice").attr("value", "${parameter.maxPrice }");
            $("#productTypeId").find("option[value='${parameter.productTypeId }']").attr("selected", true);

            var pages;

            if ("${pageProductList.pages }" == 0)
	               {
	                    pages = 1;
	               }else
	               {
	                    pages = "${pageProductList.pages }";
	               }

	               var options = {
	                                  bootstrapMajorVersion : 3,
	                                  currentPage           : "${pageProductList.pageNum }",
	                                  totalPages            : pages,
	                                  size                  : "normal",
	                                  aligment              : "center",


	                                   //无条件查询时使用
	                                   pageUrl : function(type, page, current)
	                                   {
	                                         return "${pageContext.request.contextPath }/main/showMain?pageNo=" + page;
	                                   },

	                                  // 带参数查询使用
	                                  onPageClicked : function(event, originalEvent, type, page)
	                                  {
	                                       $("#pageNo").attr("value", page);
	                                       $("#searchForm").submit;
	                                  }
	                             };

	                $("#productPage").bootstrapPaginator(options);



            // 添加购物车
            $("div[name='addCart']").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  :  "${pageContext.request.contextPath }/cart/addCart",
                        data : {
                            "productId" : $(this).attr("data-value")
                        },
                        dataType : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                $("#cartMsg").tooltip(
                                    {
                                        title     : "error",
                                        placement : "center",
                                        template  : "<div class='tooltip errorMsg'>添加成功</div>",
                                        tigger    : "manual"
                                    }).tooltip("show");
                            }else
                            {
                                $("#cartMsg").tooltip(
                                    {
                                        title     : "error",
                                        placement : "center",
                                        template  : "<div class='tooltip errorMsg'>" + result.message + "</div>",
                                        tigger    : "manual"
                                    }).tooltip("show");
                            }
                        }
                    });
            });

            //登录
            $("#denglu").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/user/denglu",
                        data :
                            {
                                "name" : $("#dengluname").val(),
                                "password"   : $("#password").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                //location.href = "${pageContext.request.contextPath }/user/showUserMainPage";
                            }else
                            {
                                $("#errorMsg").tooltip(
                                    {
                                        title:"error",
                                        placement:"center",
                                        template:"<div class='tooltip errorMsg'>" + result.message + "</div>",
                                        tigger:"manual"
                                    }).tooltip("show");
                            }
                        }
                    });
            });

            //修改密码
            $("#modifyPassword").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/user/modifypassword",
                        data :
                            {
                                "loginName" : $("#loginName").val(),
                                "oldPassword" : $("#oldPassword").val(),
                                "newPassword"   : $("#newPassword").val(),
                                "repeatPassword"   : $("#repeatPassword").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                //location.href = "${pageContext.request.contextPath }/user/showUserMainPage";
                            }else
                            {
                                $("#errorMsg").tooltip(
                                    {
                                        title:"error",
                                        placement:"center",
                                        template:"<div class='tooltip errorMsg'>" + result.message + "</div>",
                                        tigger:"manual"
                                    }).tooltip("show");
                            }
                        }
                    });
            });

            //注册
            $("#regist").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/user/regist",
                        data :
                            {
                                "rUserName" : $("#rUserName").val(),
                                "rLoginName" : $("#rLoginName").val(),
                                "rPassword"   : $("#rPassword").val(),
                                "rPhone"   : $("#rPhone").val(),
                                "rAddress"   : $("#rAddress").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                //location.href = "${pageContext.request.contextPath }/user/showUserMainPage";
                            }else
                            {
                                $("#errorMsg").tooltip(
                                    {
                                        title:"error",
                                        placement:"center",
                                        template:"<div class='tooltip errorMsg'>" + result.message + "</div>",
                                        tigger:"manual"
                                    }).tooltip("show");
                            }
                        }
                    });
            });
        });
    </script>
</head>

    <body>
        <div class="navbar navbar-default clear-bottom">
            <div class="container">
                <!-- logo start -->
                <div class="navbar-header">
                    <a class="navbar-brand logo-style" href="/">
                        <img class="brand-img" src="${pageContext.request.contextPath}/images/com-logo1.png" alt="logo" height="70">
                    </a>
                </div>
                <!-- logo end -->
                <!-- navbar start -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/main/showMain">商城主页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/order/myOrders">我的订单</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/main/cart">购物车</a>
                        </li>
                        <li class="dropdown">
                        	<a href="${pageContext.request.contextPath}/main/center">会员中心</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                        </li>
                        <li class="userName" id="userName" value=${user.userName}>
                            您好：${user.userName}！
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg" height="30" />
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                        <i class="glyphicon glyphicon-cog"></i>修改密码
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath }/user/tuichu">
                                        <i class="glyphicon glyphicon-off" id="tuichu"></i> 退出
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- navbar end -->
            </div>
        </div>
        <!-- content start -->
        <div class="container" id="a">
            <div class="row">
                <div class="col-xs-12">
                    <div class="page-header" style="margin-bottom: 0px;">
                        <h3>商品列表</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-inline hot-search" id="searchForm" action="${pageContext.request.contextPath }/main/showMain" method="post">

                        <!-- 添加隐藏域 -->
                        <input type="hidden" name="pageNo" id="pageNo" />

                        <div class="form-group">
                            <label class="control-label">价格：</label>
                            <input type="text" class="form-control" placeholder="最低价格" id="minPrice" name="minPrice">--
                            <input type="text" class="form-control" placeholder="最高价格" id="maxPrice" name="maxPrice">
                        </div>
                        &nbsp;
                        <div class="form-group">
                            <!-- <input type="text" id="datepicker" class="form-control" placeholder="出发时间"> -->
                            <label class="control-label">分类：</label>
                            <div class="col-sm-4">
                                <select class="form-control input-sm" id="productTypeId" name="productTypeId">
                                    <option value="-1" selected="selected">查询全部</option>
                                        <c:forEach items="${typeList }" var="type">
                                            <option value="${type.id }">${type.name }</option>
                                        </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--<button type="button" class="btn btn-warning pull-right">
                            <i class="glyphicon glyphicon-search"></i>&nbsp;查询
                        </button>--%>
                        <input type="submit" class="btn btn-warning pull-right" value="查询">
                    </form>
                </div>
            </div>
        </div>
        <div class="content-back">
            <div class="container" id="a">
                <div class="row">
                        <c:forEach items="${pageProductList.list }" var="p">
                            <div class="col-xs-3  hot-item">
                                <div class="panel clear-panel">
                                    <div class="panel-body">
                                        <div class="art-back clear-back">
                                            <div class="add-padding-bottom">
                                                <!-- 修改 src的路径 -->
                                                <img src="${pageContext.request.contextPath }${p.image } " class="shopImg">
                                            </div>
                                            <h4>${p.name }</h4>
                                            <div class="user clearfix pull-right">
                                                    ￥${p.price }
                                            </div>

                                            <!-- <div class="desc">好喝的奶茶好喝的奶茶好喝的奶茶...</div> -->

                                            <!-- 添加   name="addCart" data-value="${p.productId }" -->
                                            <div class="attention pull-right" name="addCart" data-value="${p.productId } ">
                                                加入购物车
                                                <i class="icon iconfont icon-gouwuche"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    <!-- 添加: 分页 ul -->
                </div>
            </div>
        </div>
        <ul id="productPage">
        </ul>
        <!-- content end-->


        <!-- 修改密码模态框 -->
        <div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                    </div>
                    <form action="" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录名：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="loginName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">原密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" id="oldPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" id="newPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">重复密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" id="repeatPassword">
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                            <button type="submit" class="btn btn-warning" id="modifyPassword"  >修&nbsp;&nbsp;改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 登录模态框   -->
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">登&nbsp;陆</h4>
                    </div>
                    <form action="" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录帐号：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="dengluname">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" id="password">
                                </div>
                            </div>
                            <!--  <label class="col-sm-3 control-label" style="text-align:left; border: 1px solid #ccc;">1234</label> -->

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                            <button type="submit" class="btn btn-warning" id="denglu">登&nbsp;&nbsp;陆</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 注册模态框 -->
        <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">会员注册</h4>
                    </div>
                    <form action="" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户姓名:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="rUserName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登陆账号:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="rLoginName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录密码:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" id="rPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系电话:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="rPhone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系地址:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" id="rAddress">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                            <button type="submit" class="btn btn-warning" id="regist">注&nbsp;&nbsp;册</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
