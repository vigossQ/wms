<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <link rel="stylesheet" type="text/css" href="../../../js/jquery/plugins/fancyBox/jquery.fancybox.css"/>
    <script type="text/javascript" src="/js/jquery/plugins/fancyBox/jquery.fancybox.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.fancybox').fancybox();
        })
    </script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/product/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        关键字
                        <input type="text" class="ui_input_txt02" name="keywords" value="${qo.keywords}"/>
                        商品品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1">全部</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${brand.id==qo.brandId?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <%--<input type="button" value="批量删除" class="ui_input_btn01 batchDelete" data-delete="/departmrnt/batchDelete.do"/>--%>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/product/input.do"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui_content">
        <div class="ui_tb">
            <table class="table" cellspacing="0" cellpadding="0" width="100%"
                   align="center" border="0">
                <tr>
                    <th width="30">
                        <input type="checkbox" id="all"/>
                    </th>
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>商品品牌</th>
                    <th>商品成本价</th>
                    <th>商品销售价</th>
                    <th></th>
                </tr>
                <tbody>
                <c:forEach items="${result.data}" var="product">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>
                            <a class="fancybox" href="${product.imagePath}" data-fancybox-group="gallery" title=${product.name}>
                                <img src="${product.smallImagePath}" width="80px"/>
                            </a>
                        </td>
                        <td>${product.name}</td>
                        <td>${product.sn}</td>
                        <td>${product.brandName}</td>
                        <td>${product.costPrice}</td>
                        <td>${product.salePrice}</td>
                        <td>
                            <a href="/product/input.do?id=${product.id}">编辑</a>
                            <a href="javascript:;" class="btn_delete"
                               data-url="/product/delete.do?id=${product.id}&imagePath=${product.imagePath}">删除</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
        <jsp:include page="/WEB-INF/views/commons/common_page.jsp"></jsp:include>
    </div>
    </div>
</form>
</body>
</html>
