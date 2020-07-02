<%--
  Created by IntelliJ IDEA.
  User: 16090
  Date: 2020/6/23
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function()
        {
            // 商品类型管理
            $("#product-type-set").click(function()
            {
                // 点击  "商品类型管理"
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/type/findAll");
            });

            // 商品管理
            $("#product-set").click(function()
            {
                // 点击  "商品管理"
                $("#frame-id").attr("src", "${pageContext.request.contextPath }/product/findAll");
            });

            // 部门管理
            $("#dept-set").click(function()
            {
                $("#frame-id").attr("src", "${pageContext.request.contextPath }/dept/findAll");
            });

            // 管理员管理
            $("#manager-set").click(function()
            {
                $("#frame-id").attr("src", "${pageContext.request.contextPath }/staff/staffManager.html");
            });

            //用户管理
            $("#user-set").click(function()
            {
                // 点击用户管理
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/user/userManager.html");
            });

            // 添加 :  点击"退出"
            $("#logout").click(function()
            {
                location.href="${pageContext.request.contextPath }/staff/logout";
            });
        });
    </script>
</head>

<body>
<div class="wrapper-cc clearfix">
    <div class="content-cc">
        <!-- header start -->
        <div class="clear-bottom head">
            <div class="container head-cc">
                <p>在线商城后台管理系统</p>
                <div class="welcome">
                    <div class="left">欢迎您:</div>
                    <div class="right">xxx</div>
                    <div class="exit">退出</div>
                </div>
            </div>
        </div>
        <!-- header end -->
        <!-- content start -->
        <div class="container-flude flude-cc" id="a">
            <div class="row user-setting">
                <div class="col-xs-3 user-wrap">
                    <ul class="list-group">
                        <li class="list-group-item active" name="userSet" id="product-type-set">
                            <i class="glyphicon glyphicon-lock"></i> &nbsp;商品类型管理
                        </li>
                        <li class="list-group-item" name="userPic" id="product-set">
                            <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;商品管理
                        </li>

                        <li class="list-group-item" name="departmentSet" id="dept-set">
                            <i class="glyphicon glyphicon-modal-window"></i> &nbsp;部门管理
                        </li>
                        <li class="list-group-item" name="adminSet" id="manager-set">
                            <i class="glyphicon glyphicon-globe"></i> &nbsp;管理员管理
                        </li>
                        <li class="list-group-item" name="userInfo" id="user-set">
                            <i class="glyphicon glyphicon-user"></i> &nbsp;用户管理
                        </li>
                    </ul>
                </div>
                <div class="col-xs-9" id="userPanel">
                    <iframe id="frame-id" src="${pageContext.request.contextPath}/type/findAll" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
                </div>

            </div>
        </div>
        <!-- content end-->
    </div>
</div>
</body>

</html>
