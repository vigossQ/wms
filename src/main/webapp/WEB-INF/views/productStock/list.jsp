<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>PSS-即时库存报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/productStock/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_bstockIncome">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        商品名称或编码
                        <input type="text" class="ui_input_txt02" name="keywords" value="${qo.keywords}"/>
                        所在仓库
                        <select class="ui_select01" name="depotId">
                            <option value="-1">所有仓库</option>
                            <c:forEach items="${depots}" var="depot">
                                <option value="${depot.id}" ${depot.id==qo.depotId?"selected='selected'":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                        商品品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1">所有品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${brand.id==qo.brandId?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>
                        </select>
                        库存阈值
                        <input type="text" class="ui_input_txt02" name="limitNumber" value="${qo.limitNumber}"/>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
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
                    <th>仓库</th>
                    <th>商品编码</th>
                    <th>商品名称</th>
                    <th>品牌</th>
                    <th>库存数量</th>
                    <th>成本</th>
                    <th>库存汇总</th>
                </tr>
                <tbody>
                <c:forEach items="${result.data}" var="productStock">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>${productStock.depot.name}</td>
                        <td>${productStock.product.sn}</td>
                        <td>${productStock.product.name}</td>
                        <td>${productStock.product.brandName}</td>
                        <td>${productStock.storeNumber}</td>
                        <td>${productStock.price}</td>
                        <td>${productStock.amount}</td>
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
