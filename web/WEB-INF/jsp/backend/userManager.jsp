<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/24
  Time: 15:56
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

    <!-- 引入bootstrap分页插件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>

    <script type="text/javascript">

        $(function()
        {
            //--------------------分页部分  ↓
            var pages;

            // ${userlist.pages } 对应PageInfo对象中的pages;
            if ("${userlist.pages }" == 0)
            {
                pages = 1;
            }else
            {
                pages = "${userlist.pages }";
            }

            var options =
                {
                    bootstrapMajorVersion : 3,                        // bootstrap版本 (此项目中所有的页面都是用bootstrap 3画的)
                    currentPage  : "${userlist.pageNum }", // pageNum对应PageInfo对象中的字段pageNum
                    totalPages : pages,
                    aligment : "center",
                    pageUrl  : function(type, page, current)
                    {
                        return "${pageContext.request.contextPath }/user/userManager.html?pageNo=" + page;
                    }
                };

            $("#productTypePage").bootstrapPaginator(options);
            //----------------------------分页部分  ↑


            //修改的状态栏获取值
            $("input[name='toModifyUser']").click(function () {
                $.ajax(
                 {
                     type:"post",
                     url:"${pageContext.request.contextPath }/user/findUserById",
                     data:{
                         "userId" :$(this).attr("data-value")
                     },
                     dataType : "json",
                     success  : function(result){

                         if (result.responseCode== 1)
                         {
                             $("#N").val(result.returnObject.userId);

                             $("#musername").val(result.returnObject.userName);

                             $("#mloginName").val(result.returnObject.loginName);

                             $("#mphone").val(result.returnObject.phone);

                             $("#madrees").val(result.returnObject.address);
                         }else
                         {
                             $("#errorMsg").tooptip(
                                 {
                                     title : "error",
                                     placement: "center",
                                     template : "<div class='tooltip errorMsg'>" +  result.message + "</div>",
                                     tigger  : "manual"
                                 }).tooltip("show");
                         }

                     },
                     error:function () {
                         alert("服务器内容错误!");
                     }
                })
            });

            //修改提交数据的ajax
            $("#modifyuser").click(function() {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/user/modifyUser",
                        data : {
                            "userId"   : $("#N").val(),
                            "userName" : $("#musername").val(),
                            "loginName" : $("#mloginName").val(),
                            "phone" : $("#mphone").val(),
                            "address" : $("#madrees").val()
                        },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href = "${pageContext.request.contextPath }/user/userManager.html?pageNo=${userlist.pageNum }";
                            }else
                            {
                                $("#errorMsg").tooptip(
                                    {
                                        title : "error",
                                        placement: "center",
                                        template : "<div class='tooltip errorMsg'>" +  result.message + "</div>",
                                        tigger  : "manual"
                                    }).tooltip("show");
                            }
                        }

                    });
            });

            //导出
            $("input[name='outputuser']").click(function () {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath }/user/outputuserexcel",
                    data:{
                    },
                    dataType : "json",
                    async:false,
                    success  : function(result)
                    {
                        if (result.responseCode == 1)
                        {
                            alert("导出成功!");
                        }else
                        {
                            alert("导出失败!");
                        }
                    },
                    error:function () {
                        alert("服务器内容错误3!");
                    }
                });
            });



        });

    </script>

</head>

<body>
<div class="panel panel-default" id="userInfo" id="homeSet">
    <div class="panel-heading">
        <h3 class="panel-title">用户管理  <span id="errorMsg" data-toggle="tooltip"></span></h3>
    </div>
    <div class="panel-body">
        <%--查询状态栏--%>
        <div class="showusersearch">
            <form class="form-inline" action="${pageContext.request.contextPath}/user/userManager.html" method="post">
                <div class="form-group">
                    <label for="iuserName">姓名:</label>
                    <input type="text" class="form-control" id="iuserName"  name="iuserName" placeholder="请输入姓名">
                </div>
                <div class="form-group">
                    <label for="iloginName">帐号:</label>
                    <input type="text" class="form-control" id="iloginName"  name="iloginName"  placeholder="请输入帐号">
                </div>
                <div class="form-group">
                    <label for="iphone">电话:</label>
                    <input type="text" class="form-control" id="iphone"  name="iphone"  placeholder="请输入电话">
                </div>
                <div class="form-group">
                    <label for="iaddress">地址:</label>
                    <input type="text" class="form-control" id="iaddress"  name="iaddress" placeholder="请输入地址">
                </div>
                <div class="form-group">
                    <label for="iisValid">状态</label>
                    <select class="form-control"  id="iisValid"  name="iisValid">
                        <option value="-1">全部</option>
                        <option value="1">---有效---</option>
                        <option value="0">---无效---</option>
                    </select>
                </div>
                <input type="submit"  class="btn btn-primary" id="doSearch" value="查询" >
            </form>
        </div>
            <input type="button" value="导出" class="btn btn-primary" name="outputuser" id="outputuser">
            &nbsp;&nbsp;<form method="POST"  enctype="multipart/form-data" id="form2" action="${pageContext.request.contextPath }/user/inputuserexcel">
            <table>
                <tr>
                    <td> <input id="userfile" type="file" name="userfile" ></td>
                    <td><input type="submit" value="提交" id="inputbtn" name="inputbtn" class="btn btn-primary"></td>
                </tr>


            <%--数据表单显示栏--%>
        <div class="show-list" style="position: relative;top: 30px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tbuser">
                <c:forEach var="u" items="${userlist.list}">
                    <tr>
                        <td>${u.userId}</td>
                        <td>${u.userName}</td>
                        <td>${u.loginName}</td>
                        <td>${u.phone}</td>
                        <td>${u.address}</td>
                        <td>
                            <c:if test="${u.isValid eq 1 }">有效</c:if>
                            <c:if test="${u.isValid eq 0 }">无效</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doModify" value="修改" data-value="${u.userId}" name="toModifyUser">
                            <c:if test="${u.isValid eq 1 }">
                                <a type="button" class="btn btn-danger btn-sm doDisable" href="${pageContext.request.contextPath}/user/modifyUserStatus?userId=${u.userId}&isValid=${u.isValid}&pageNo=${userlist.pageNum}">禁用</a>
                                <!-- 将以上的input标签变成a标签 因为input标签不能用问号传参 -->
                                <%--<a type="button" class="btn btn-danger btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/user/modifyUserStatus?
                                id=${type.id }&status=${type.status }&pageNo=${pageProductTypeList.pageNum }"> 禁用 </a>--%>
                            </c:if>

                            <c:if test="${u.isValid eq 0 }">
                                <!-- 把 class的值 btn-danger  改成   btn-success -->
                                <a type="button" class="btn btn-success btn-sm doDisable" href="${pageContext.request.contextPath}/user/modifyUserStatus?userId=${u.userId}&isValid=${u.isValid}&pageNo=${userlist.pageNum}">启用</a>
                                <%--<a type="button" class="btn btn-success btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/type/modifyProductTypeStatus?
                                id=${type.id }&status=${type.status }&pageNo=${pageProductTypeList.pageNum }"> 启用 </a>--%>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 添加: 分页 ul -->
            <ul id="productTypePage">
            </ul>
        </div>
       <%-- 修改状态栏--%>
        <div class="modal fade" tabindex="-1" id="myModal">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">用户修改</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="N" class="col-sm-4 control-label">编号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="N" readonly="readonly">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="musername" class="col-sm-4 control-label">姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="musername">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="mloginName" class="col-sm-4 control-label">帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="mloginName">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="mphone" class="col-sm-4 control-label">电话：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="mphone">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="madrees" class="col-sm-4 control-label">地址：</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="madrees">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning updateOne" id="modifyuser">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>



</html>
