<%--
  Created by IntelliJ IDEA.
  User: 16090
  Date: 2020/6/30
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function()
        {

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
                                //location.href = "${pageContext.request.contextPath }/user/center.html";
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

            $("#modifyMessage").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/user/modifyMessage",
                        data :
                            {
                                "loginName" : $("#loginName").val(),
                                "phone" : $("#phone").val(),
                                "address"   : $("#address").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                //location.href = "${pageContext.request.contextPath }/user/center.html";
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
                <li>
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
                        <img class="img-circle" src=" ${pageContext.request.contextPath}/${fangWenPath}" height="33" />
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
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>基本资料</h3>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="name" class="col-md-2  col-sm-2 control-label">用户姓名:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="用户姓名" readonly="readonly"  value=${user.userName}>
            </div>
        </div>
        <div class="form-group">
            <label for="loginName" class="col-md-2 col-sm-2 control-label">登陆账号:</label>
            <div class="col-md-8  col-sm-10">
                <input type="text" class="form-control" id="loginName" placeholder="登陆账号" readonly="readonly" value=${user.loginName}>
            </div>
        </div>

        <div class="form-group">
            <label for="phone" class="col-md-2  col-sm-2 control-label">联系电话:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" id="phone" placeholder="联系电话" id="phone" value=${user.phone}>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-md-2   col-sm-2  control-label">联系地址:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" id="address" placeholder="联系地址" id="address" value=${user.address}>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-warning" id="modifyMessage">确认修改</button>
            </div>
        </div>
    </form>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>修改头像</h3>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/file/doUpload?userName=${user.loginName}" method="post" enctype="multipart/form-data">
        <input type="file" name="fileUpload" />

        <input type="submit" value="上传" />

    </form>
    <div>
        <input type="hidden" name="userName" id="userName" value=${user.loginName}>
    </div>
    <div class="col-md-10 col-sm-10">
        <img src=" ${pageContext.request.contextPath}/${fangWenPath}" id="showImg" class="togeImg" onclick="openFile()" alt="" width="100" height="100">
        <input id="file" type="file" style="display: none;" name="imgName" />
    </div>
</div>
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
                            <input class="form-control" type="text" id="dengluname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" id="password">
                        </div>
                    </div>
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

