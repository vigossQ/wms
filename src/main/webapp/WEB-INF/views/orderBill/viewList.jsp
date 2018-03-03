<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
</head>
<body>
<form name="editForm" method="post" id="editForm">
    <input type="hidden" name="id" value="${orderBill.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单查看</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编码:</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02" value="${orderBill.sn}" disabled="disabled"/>
                        <%--&nbsp;<span name="sn">${orderBill.sn}</span>--%>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商:</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02" value="${orderBill.supplier.name}" disabled="disabled"/>
                        <%--&nbsp;<span name="sn">${orderBill.supplier.name}</span>--%>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间:</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" value="${vdate}" class="ui_input_txt02" disabled="disabled"/>
                        <%--&nbsp;<span name="sn">${vdate}</span>--%>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <%--<input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>--%>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:forEach items="${orderBill.items}" var="item">
                                <tr>
                                    <td>
                                        <img width="80px" tag="imagePath" src="${item.product.imagePath}"/>
                                    </td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"
                                               value="${item.product.name}" disabled="disabled"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" name="items.product.id" tag="pid" value="${item.product.id}"/>
                                    </td>
                                    <td><span tag="brand">${item.product.brandName}</span></td>
                                    <td><input tag="costPrice" name="items.costPrice" disabled="disabled"
                                               class="ui_input_txt00" value="${item.costPrice}"/></td>
                                    <td><input tag="number" name="items.number" disabled="disabled"
                                               class="ui_input_txt00" value="${item.number}"/></td>
                                    <td><span tag="amount">${item.amount}</span></td>
                                    <td><input tag="remark" name="items.remark" disabled="disabled"
                                               class="ui_input_txt02" value="${item.remark}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="返回列表" onclick="window.history.back()" class="ui_input_btn01 btn_submit"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>