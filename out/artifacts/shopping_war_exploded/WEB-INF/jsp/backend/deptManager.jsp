<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入JSTL标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
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
            var pages;

            // ${pageDeptList.pages } 对应PageInfo对象中的pages;
            if ("${pageDeptList.pages }" == 0)
            {
                pages = 1;
            }else
            {
                pages = "${pageDeptList.pages }";
            }

            var options =
                {
                    bootstrapMajorVersion : 3,                        // bootstrap版本 (此项目中所有的页面都是用bootstrap 3画的)
                    currentPage  : "${pageDeptList.pageNum }", // pageNum对应PageInfo对象中的字段pageNum
                    totalPages : pages,
                    aligment : "center",
                    pageUrl  : function(type, page, current)
                    {
                        return "${pageContext.request.contextPath }/dept/findAll?pageNo=" + page;
                    }
                };

            $("#deptPage").bootstrapPaginator(options);


            // 添加父部门
            $("#addFatherDept").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/dept/addDept",
                        data :
                            {
                                "deptName" : $("#fatherDeptName").val(),
                                "remark"   : $("#fatherRemark").val(),
                                "deptNum"   : $("#fatherDeptNum").val(),
                                "fatherDeptId"   : $("#fatherDeptId").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href = "${pageContext.request.contextPath }/dept/findAll?pageNo=${pageDeptList.pageNum }";
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


            // 添加子部门
            $("#addSonDept").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/dept/addDept",
                        data :
                            {
                                "fatherDeptId" : $("#dept-id-fa").val(),
                                "deptName"   : $("#dept-name2").val(),
                                "deptNum"   : $("#dept-num").val(),
                                "remark"   : $("#dept-duty2").val()
                            },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href = "${pageContext.request.contextPath }/dept/findAll?pageNo=${pageDeptList.pageNum }";
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


            $("input[name='toModifyDept']").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/dept/findById",
                        data : {"id" : $(this).attr("data-value") },
                        dataType : "json",
                        success  : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                // 页面设置 部门ID 字段的值
                                $("#dept-id").val(result.returnObject.deptId);
                                // 页面设置 部门名称 字段的值
                                $("#dept-name").val(result.returnObject.deptName);
                                // 页面设置 职能名称 字段的值
                                $("#dept-duty").val(result.returnObject.remark);

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
                    }
                );
            });

            $("#modifyDept2").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/dept/modifyName",
                        data : {
                            "id"   : $("#dept-id").val(),
                            "name" : $("#dept-name").val(),
                            "duty" : $("#dept-duty").val()
                        },
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href = "${pageContext.request.contextPath }/dept/findAll?pageNo=" + ${pageDeptList.pageNum };
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
                        }/*,
	                               error : function()
				                   {
				                        alert("服务器内容错误!");
				                   }  */

                    });

            });










        });
    </script>
</head>

<body>

<!-- 部门管理 -->
<div class="panel panel-default" id="departmentSet">
    <div class="panel-heading">
        <h3 class="panel-title">部门管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加部门" class="btn btn-primary" id="doAddDept">
        <br>
        <br>
        <div class="modal fade" tabindex="-1" id="Dept">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加部门</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">父部门ID：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="fatherDeptId" value="1" readonly="readonly">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="fatherDeptName">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">部门编号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="fatherDeptNum">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="fatherRemark">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary addDept" id="addFatherDept">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 添加子部门模态框 -->
        <div class="modal fade" tabindex="-1" id="sonDept">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加子部门</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">父部门ID：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-id-fa" >
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-name2">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">部门编号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-num">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-duty2">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary addSonDept" id="addSonDept">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- 修改部门模态框 -->
        <div class="modal fade" tabindex="-1" id="modifyDept">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">修改部门</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">id：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-id" readonly="readonly" >
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-name">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="dept-duty">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary modifyDept" id="modifyDept2">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">部门编号</th>
                    <th class="text-center">部门名称</th>
                    <th class="text-center">部门职能</th>
                    <th class="text-center">所属部门</th>
                    <th class="text-center">部门状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">

                <c:forEach items="${pageDeptList.list }" var="dept">
                    <tr>
                        <td>${dept.deptId }</td>
                        <td>${dept.deptNo }</td>
                        <td>${dept.deptName }</td>
                        <td>${dept.remark }</td>
                        <td>${dept.fatherDept }</td>
                        <td>
                            <c:if test="${dept.isValid eq 1 }">有效</c:if>
                            <c:if test="${dept.isValid eq 0 }">无效</c:if>
                        </td>

                        <td class="text-center">

                            <input type="button" class="btn btn-info btn-sm doAddSonDept" value="添加子部门">

                            <input type="button" class="btn btn-warning btn-sm doModifyDept" value="修改" data-value="${dept.deptId}" name="toModifyDept">

                            <c:if test="${dept.isValid eq 1 }">
                                <!-- <input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用"> -->
                                <!-- 将以上的input标签变成a标签 因为input标签不能用问号传参 -->
                                <a type="button" class="btn btn-danger btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/dept/modifyDeptStatus?id=${dept.deptId}&status=${dept.isValid}&pageNo=${pageDeptList.pageNum }"> 禁用 </a>
                            </c:if>

                            <c:if test="${dept.isValid eq 0 }">
                                <!-- 把 class的值 btn-danger  改成   btn-success -->
                                <a type="button" class="btn btn-success btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/dept/modifyDeptStatus?id=${dept.deptId}&status=${dept.isValid}&pageNo=${pageDeptList.pageNum }"> 启用 </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <!-- 添加: 分页 ul -->
            <ul id="deptPage">
            </ul>
        </div>
    </div>
</div>
</body>

</html>
