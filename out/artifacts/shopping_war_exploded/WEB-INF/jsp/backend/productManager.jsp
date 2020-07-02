
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <style>
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width: 100%;
            text-align: center;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

    </style>

    <script type="text/javascript">
        $(function()
        {
            var pages;

            // ${pageProductList.pages } 对应PageInfo对象中的pages;
            if ("${pageProductList.pages }" == 0)
            {
                pages = 1;
            }else
            {
                pages = "${pageProductList.pages }";
            }

            var options =
                {
                    bootstrapMajorVersion : 3,                        // bootstrap版本 (此项目中所有的页面都是用bootstrap 3画的)
                    currentPage  : "${pageProductList.pageNum }", // pageNum对应PageInfo对象中的字段pageNum
                    totalPages : pages,
                    aligment : "center",
                    pageUrl  : function(type, page, current)
                    {
                        return "${pageContext.request.contextPath }/product/findAll?pageNo=" + page;
                    }
                };

            $("#productPage").bootstrapPaginator(options);

            $("# ").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/product/addProduct",
                        data : {"product_name" : $("#product-name").val(),
                                "price" : $("#product-price").val(),
                                "product_type_id" : $("#product-type").val()},

                        dataType : "json",
                        success  : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                // alert(1111111);
                                location.href = "${pageContext.request.contextPath }/product/findAll?pageNo=" + ${pageProductList.pageNum};
                                alert("添加成功!");
                            }else
                            {
                                $("#errorMsg").tooptip(
                                    {
                                        // alert(2222222);
                                        title : "error",
                                        placement: "center",
                                        template : "<div class='tooltip errorMsg'>" +  result.message + "</div>",
                                        tigger  : "manual"
                                    }).tooltip("show");
                            }

                        },
                        /*error : function()
                        {
                            alert("服务器内容错误!");
                        }*/
                    }
                );
            });

            $("input[name='toModifyProduct']").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/product/findById",
                        data : {"id" : $(this).attr("data-value") },
                        dataType : "json",
                        success  : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                // 页面设置 商品类型ID 字段的值
                                $("#modifyId").val(result.returnObject.productId);
                                $("#modifyName").val(result.returnObject.name);
                                $("#modifyPrice").val(result.returnObject.price);
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
                        error : function()
                        {
                            alert("服务器内容错误!");
                        }
                    }
                );
            });

            //修改的状态栏获取值
            $("#modifyProduct").click(function()
            {
                $.ajax(
                    {
                        type : "post",
                        url  : "${pageContext.request.contextPath }/product/modifyName",
                        data : {
                            "id"   : $("#modifyId").val(),
                            "product_name" : $("#modifyName").val(),
                            "price" : $("#modifyPrice").val(),
                            "product_type":$("#modifyType").val()},
                        dataType : "json",
                        success : function(result)
                        {
                            if (result.responseCode == 1)
                            {
                                location.href = "${pageContext.request.contextPath }/product/findAll?pageNo=" + ${pageProductList.pageNum };
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

        });
    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">商品管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
        <div class="modal fade" tabindex="-1" id="Product">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加商品</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="product-name">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="product-price">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-4">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="" id="">
                                </a>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-4">
                                <select id="product-type" name="product-type"class="form-control">
                                    <c:if test="${TypeList != null }">
                                        <option value="0">--请选择--</option>
                                        <c:forEach var="type" items="${TypeList}">
                                            <option value="${type.id}">${type.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <!-- 添加:  id="addProduct" -->
                        <button class="btn btn-primary addProduct" id="addProduct">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">编号</th>
                    <th class="text-center">商品</th>
                    <th class="text-center">价格</th>
                    <th class="text-center">产品类型</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageProductList.list }" var="product" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${product.productNo }</td>
                        <td>${product.name }</td>
                        <td>${product.price }</td>
                        <td>${product.productType }</td>
                        <td>
                            <c:if test="${product.status eq 1 }">启用</c:if>
                            <c:if test="${product.status eq 0 }">禁用</c:if>
                        </td>

                        <td class="text-center">
                            <!-- 添加  data-value="${product.productId}" name="toModifyProduct" 注意这里有多条数据有修改操作, 所以这里不能用id选择器 选中要"修改"的数据 -->
                            <input type="button" class="btn btn-warning btn-sm doProModify" value="修改" data-value="${product.productId}" name="toModifyProduct">

                            <c:if test="${product.status eq 1 }">
                                <!-- <input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用"> -->
                                <!-- 将以上的input标签变成a标签 因为input标签不能用问号传参 -->
                                <a type="button" class="btn btn-danger btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/product/modifyProductStatus?id=${product.productId }&status=${product.status }&pageNo=${pageProductList.pageNum }"> 禁用 </a>
                            </c:if>

                            <c:if test="${product.status eq 0 }">
                                <!-- 把 class的值 btn-danger  改成   btn-success -->
                                <a type="button" class="btn btn-success btn-sm doProTypeDisable" href="${pageContext.request.contextPath }/product/modifyProductStatus?id=${product.productId }&status=${product.status }&pageNo=${pageProductList.pageNum }"> 启用 </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 添加: 分页 ul -->
            <ul id="productPage">
            </ul>
        </div>
        <%-- 修改状态栏--%>
        <div class="modal fade" tabindex="-1" id="myProduct">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">商品修改</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="pro-num" class="col-sm-4 control-label">编号：</label>
                            <div class="col-sm-4">
                                <!-- 添加隐藏域 -->
                                <input type="hidden" name="pageNo"  value="${pageProductList.pageNum }" />
                                <input type="text" class="form-control" id="modifyId" name="modifyId" readOnly="readonly">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="modifyName"  name="modifyName">
                            </div>
                        </div>
                        <br>

                        <div class="row text-right">
                            <label for="product-image" class="col-sm-4 control-label">商品图片:</label>
                            <div class="col-sm-4">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file">
                                </a>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-4">
                                <select id="modifyType" name="modifyType"class="form-control">
                                    <c:if test="${TypeList != null }">
                                        <option value="0">--请选择--</option>
                                        <c:forEach var="type" items="${TypeList}">
                                            <option value="${type.id}">${type.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="modifyPrice" name="modifyPrice">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary updatePro" id="modifyProduct">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
