<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/24
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    '<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>

    <!-- 引入bootstrap分页插件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>

    <script type="text/javascript">
        $(function () {

            //获取修改信息
            $("input[name='toModifyStaff']").click(function () {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath }/staff/findById",
                    data:{
                        "staffId":$(this).attr("data-value"),
                    },
                    dataType:"json",
                    async:false,
                    success:function (result) {
                        if (result.responseCode==1){
                            $("#MargerStaffId").val(result.returnObject.staffId);
                            $("#MargerStaffname").val(result.returnObject.staffName);
                            $("#MargerLoginName").val(result.returnObject.loginName);
                            $("#MargerPhone").val(result.returnObject.phone);
                            $("#MargerEmail").val(result.returnObject.email);
                            $("#MargerRole").val(result.returnObject.role);
                            $("#MargerDeptId").val(result.returnObject.deptId)
                        }else{
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
                });
            });

            //提交修改
            $("#modifyStaff").click(function () {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/staff/modifyStaff" ,
                    data:{
                        "staffId"   : $("#MargerStaffId").val(),
                        "staffName" : $("#MargerStaffname").val(),
                        "loginName" : $("#MargerLoginName").val(),
                        "phone" : $("#MargerPhone").val(),
                        "email" : $("#MargerEmail").val(),
                        "role" : $("#MargerRole").val(),
                        "deptId" : $("#MargerDeptId").val()
                    },
                    dataType:"json",
                    async:false,
                    success:function (result) {
                        if (result.responseCode==1){
                            location.href="${pageContext.request.contextPath}/staff/staffManager.html";
                        }else{
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
                });
            });

            // 添加管理员
            $("#addStaff").click(function()
            {
                $.ajax(
                    {
                        type:"post",
                        url:"${pageContext.request.contextPath}/staff/addStaff",
                        data:{
                            "staffName":$("#addStaffName").val(),
                            "loginName":$("#addLoginName").val(),
                            "password":$("#addPassword").val(),
                            "phone":$("#addPhone").val(),
                            "email":$("#addEmail").val(),
                            "deptId":$("#addDept")[0].options[$("#addDept")[0].selectedIndex].value,
                            "role":$("#addRole")[0].value
                        },
                        dataType:"json",
                        success:function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href="${pageContext.request.contextPath}/staff/staffManager.html?pageNo=${pageStaffList.pageNum }";
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
                        },
                        error:function () {
                            alert("服务器内容错误!");
                        }
                    });
            });
        });
    </script>
</head>

<body>
<!-- 管理员管理 -->
<div class="panel panel-default" id="adminSet">
    <div class="panel-heading">
        <h3 class="panel-title">管理员管理<span id="errorMsg" data-toggle="tooltip"></span></h3>
    </div>
    <div class="panel-body">
        <%--模糊查询--%>
        <div class="showmargersearch">
            <form class="form-inline" action="${pageContext.request.contextPath}/staff/staffManager.html" method="post">
                <div class="form-group">
                    <label for="userName">姓名:</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入姓名">
                </div>
                <div class="form-group">
                    <label for="loginName">帐号:</label>
                    <input type="text" class="form-control" id="loginName"  name="loginName" placeholder="请输入帐号">
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="text" class="form-control" id="phone" name="phone"  placeholder="请输入电话">
                </div>
                <div class="form-group">
                    <label for="email">邮箱:</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱">
                </div>
                <div class="form-group">
                    <label for="deptId">部门</label>
                    <select class="form-control" name="deptId" id="deptId">
                        <option value="-1">请选择</option>
                        <c:forEach var="dept3" items="${deptList3}">
                            <option <c:if test="${dept3.deptId == sdeptId }">selected="selected"</c:if>
                                    value="${dept3.deptId}">${dept3.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="role">角色</label>
                    <select class="form-control" name="role" id="role">
                        <option value="-1">全部</option>
                        <option value="2001">系统管理员</option>
                        <option value="2002">普通管理员</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="sisValid">状态</label>
                    <select class="form-control" name="sisValid" id="sisValid">
                        <option value="-1">全部</option>
                        <option value="1">---有效---</option>
                        <option value="0">---无效---</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <br>
        <input type="button" value="添加管理员" class="btn btn-primary" id="doAddManger">
        <!-- 添加管理员 -->
        <div class="modal fade" tabindex="-1" id="myMangerUser">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加管理员</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="addStaffName" class="col-sm-4 control-label">用户姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addStaffName">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addLoginName" class="col-sm-4 control-label">登录帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addLoginName">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addPassword" class="col-sm-4 control-label">登录密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="addPassword">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addPhone" class="col-sm-4 control-label">联系电话：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addPhone">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addEmail" class="col-sm-4 control-label">联系地址：</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="addEmail">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" id="addRole">
                                    <option>请选择</option>
                                    <option value="2001">系统管理员</option>
                                    <option value="2002">普通管理员</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="addDept" class="col-sm-4 control-label">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" id="addDept" name="addDept">
                                    <option value="-1">--请选择--</option>
                                    <c:forEach var="dept2" items="${deptList2}">
                                        <option <c:if test="${dept2.deptId == marge-dept }">selected="selected"</c:if>
                                                value="${dept2.deptId}">${dept2.deptName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary add-Manger" id="addStaff">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 管理员修改 -->
        <div class="modal fade" tabindex="-1" id="myModal-Manger">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">管理员修改</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="MargerStaffId" class="col-sm-4 control-label">ID：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerStaffId" >
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerStaffname" class="col-sm-4 control-label">管理员姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerStaffname">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerLoginName" class="col-sm-4 control-label">登录帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerLoginName" >
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerPhone" class="col-sm-4 control-label">联系电话：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerPhone">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerEmail" class="col-sm-4 control-label">联系邮箱：</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="MargerEmail">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" id="MargerRole" name="MargerRole">
                                    <option value="-1">请选择</option>
                                    <option value="2001">系统管理员</option>
                                    <option value="2002">普通管理员</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="MargerDeptId" class="col-sm-4 control-label">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" id="MargerDeptId" name="MargerDeptId">
                                    <c:forEach var="dept" items="${deptList}">
                                        <option value="${dept.deptId}">${dept.deptName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning doMargerModal" id="modifyStaff">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <%--信息显示--%>
        <div class="show-stafflist" style="position: relative; top: 10px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">部门</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${staffList.list}" var="staff">
                    <tr>
                        <td>${staff.staffId}</td>
                        <td>${staff.staffName}</td>
                        <td>${staff.loginName}</td>
                        <td>${staff.phone}</td>
                        <td>${staff.email}</td>
                        <td> ${staff.dept.deptName}</td>
                        <td>
                            <c:if test="${staff.isValid eq 1 }">有效</c:if>
                            <c:if test="${staff.isValid eq 0 }">无效</c:if>
                        </td>
                        <td>
                            <c:if test="${staff.role eq '2001'}">系统管理员</c:if>
                            <c:if test="${staff.role eq '2002'}">普通管理员</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doMangerModify" value="修改" name="toModifyStaff" data-value="${staff.staffId}">
                            <c:if test="${staff.isValid eq 1 }">
                                <%-- <input type="button" class="btn btn-danger btn-sm doMangerDisable" value="禁用">--%>
                                <a type="button" class="btn btn-danger btn-sm doMangerDisable" href="${pageContext.request.contextPath}/staff/modifyStaffStatus?staffId=${staff.staffId}&isValid=${staff.isValid}">禁用</a>
                            </c:if>
                            <c:if test="${staff.isValid eq 0 }">
                                <a type="button"  class="btn btn-success btn-sm doMangerDisable" href="${pageContext.request.contextPath}/staff/modifyStaffStatus?staffId=${staff.staffId}&isValid=${staff.isValid}">启用</a>
                                <%-- <input type="button" class="btn btn-success btn-sm doMangerDisable" value="启用">--%>
                            </c:if>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--<!-- 添加: 分页 ul -->
            <ul id="productTypePage">
            </ul>--%>
        </div>
    </div>
</div>
</body>

</html>
