<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <title>PSS-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".btn_bar").click(function () {
                var url = "/chart/saleChartsByBar.do?"+$("#searchForm").serialize();
                $.dialog.open(url, {
                    id: 'ajxxList',
                    title: '销售总额',
                    width: 800,
                    height: 500,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.2,
                    lock: true,
                    resize: false
                });
            });
            $(".btn_pie").click(function () {
                var url = "/chart/saleChartsByPie.do?"+$("#searchForm").serialize();
                $.dialog.open(url, {
                    id: 'ajxxList',
                    title: '销售总额',
                    width: 800,
                    height: 500,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.2,
                    lock: true,
                    resize: false
                });
            });
        })
    </script>
</head>
<body>
<form id="searchForm" action="/chart/sale.do" method="post">
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
                        客户
                        <select class="ui_select01" name="clientId">
                            <option value="-1">所有客户</option>
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.id}" ${client.id==qo.clientId?"selected='selected'":""}>${client.name}</option>
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
                        <select class="ui_select01" name="groupBySaleType">
                            <c:forEach items="${groupBySaleTypes}" var="groupBySaleType">
                                <option value="${groupBySaleType.key}" ${groupBySaleType.key==qo.groupBySaleType?"selected='selected'":""}>${groupBySaleType.value}</option>
                            </c:forEach>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                    </div>
                    <div class="box_bottom" style="padding-right: 10px;text-align: right;">
                        <input type="button" value="柱状报表" class="left2right btn_bar"/>
                        <input type="button" value="饼图报表" class="left2right btn_pie"/>
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
                    <th>销售总数量</th>
                    <th>销售总金额</th>
                    <th>毛利润</th>
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
                        <td>${chart.profit}</td>
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
