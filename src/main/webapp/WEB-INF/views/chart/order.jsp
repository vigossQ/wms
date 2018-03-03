<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <title>PSS-订货报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/chart/order.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_bstockIncome">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <input type="text" class="ui_input_txt02 Wdate" onClick="WdatePicker()"
                               name="beginDate" value="${beginDate}"/>
                        ~
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <input type="text" class="ui_input_txt02 Wdate" onClick="WdatePicker()"
                               name="endDate" value="${endDate}"/>
                        供应商
                        <select class="ui_select01" name="supplierId">
                            <option value="-1">所有供应商</option>
                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}" ${supplier.id==qo.supplierId?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>
                        </select>
                        商品品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1">所有品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${brand.id==qo.brandId?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>
                        </select>
                        分组
                        <select class="ui_select01" name="groupByType">
                            <c:forEach items="${groupByTypes}" var="groupByType">
                                <option value="${groupByType.key}" ${groupByType.key==qo.groupByType?"selected='selected'":""}>${groupByType.value}</option>
                            </c:forEach>
                        </select>
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
                    <th>分组类型</th>
                    <th>采购总数量</th>
                    <th>采购总金额</th>
                </tr>
                <tbody>
                <c:forEach items="${charts}" var="chart">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>${chart.groupByTypes}</td>
                        <td>${chart.totalNumber}</td>
                        <td>${chart.totalAmount}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    </div>
</form>
</body>
</html>
