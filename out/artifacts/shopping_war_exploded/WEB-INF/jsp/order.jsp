<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/25
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>确认订单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">

        $(function()
        {
            // 生成订单 操作
            $("#createOrder").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/order/createOrder",
                        dataType : "text",
                        success  : function(no)
                        {
                            $("#orderNo").html(no);
                        }
                    });
            });
        });

    </script>
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
                <li class="userName">
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
                            <a href="#">
                                <i class="glyphicon glyphicon-off"></i> 退出
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
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>

            <th>商品数量</th>
            <th>商品总价</th>
        </tr>
        <c:forEach items="${cart.items }" var="item" varStatus="i">
            <tr>
                <td>${i.count }</td>
                <td>${item.product.name }</td>
                <td><img src="${pageContext.request.contextPath }/${item.product.image }" alt="" width="60" height="60"></td>
                <td>${item.num }</td>
                <td>${item.price }</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" class="foot-msg">
                总计：<b> <span>${cart.price }</span></b>元
                <a href="${pageContext.request.contextPath}/main/cart"><button class="btn btn-warning pull-right ">返回</button></a>

                <button  id="createOrder"  name="createOrder" class="btn btn-warning pull-right margin-right-15" data-toggle="modal" data-target="#buildOrder" >生成订单</button>


            </td>
        </tr>
    </table>
</div>
<!-- content end-->
<div class="modal fade" id="buildOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">提示消息</h4>
            </div>
            <div class="orderMsg">
                <p>
                    订单生成成功！！

                </p>
                <p>
                    订单号：<span id="orderNo"></span>

                </p>
            </div>

        </div>
    </div>
</div>


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
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">重复密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">修&nbsp;&nbsp;改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 登录模态框 -->
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
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                        <div class="col-sm-2 input-height">
                            1234
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button>
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
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登陆账号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系电话:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>
