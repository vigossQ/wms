<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".btn_audit").click(function () {
                var url = $(this).data("url");
                $.dialog({
                    icon: "question",
                    title: "确认审核",
                    content: "您确认审核该条数据吗?",
                    okVal: "审核",
                    ok: function () {
                        $.get(url, function (data) {
                            if (data.success) {
                                $.dialog({
                                    icon: "face-smile",
                                    title: "温馨提示",
                                    content: "审核成功",
                                    ok: function () {
                                        window.location.reload();
                                    },
                                    drag: false,
                                    lock: true,
                                    resize: false,
                                    opacity: 0.4
                                });
                            } else {
                                $.dialog({
                                    icon: "face-smile",
                                    title: "温馨提示",
                                    content: data.errorMsg,
                                    ok: true,
                                    drag: false,
                                    lock: true,
                                    resize: false,
                                    opacity: 0.4
                                });
                            }
                        });
                    },
                    cancel: true,
                    drag: false,
                    lock: true,
                    resize: false,
                    opacity: 0.4
                });
            });
        });
    </script>
    <title>PSS-订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/orderBill/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        开始时间
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <input type="text" class="ui_input_txt02 Wdate" onClick="WdatePicker()"
                               name="beginDate" value="${beginDate}"/>
                        ~结束时间
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
                        状态
                        <select id="status" name="status" class="ui_select01">
                            <option value="-1" >所有状态</option>
                            <option value="0" >待审核</option>
                            <option value="1" >已审核</option>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                    </div>
                    <div id="box_bottom">
                        <%--<input type="button" value="批量删除" class="ui_input_btn01 batchDelete" data-delete="/departmrnt/batchDelete.do"/>--%>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/orderBill/input.do"/>
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
                    <th>订单编码</th>
                    <th>业务时间</th>
                    <th>供应商</th>
                    <th>总金额</th>
                    <th>总数量</th>
                    <th>录入人</th>
                    <th>审核人</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                <tbody>
                <c:forEach items="${result.data}" var="orderBill">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>${orderBill.sn}</td>
                        <fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <td>${vdate}</td>
                        <td>${orderBill.supplier.name}</td>
                        <td>${orderBill.totalAmount}</td>
                        <td>${orderBill.totalNumber}</td>
                        <td>${orderBill.inputUser.name}</td>
                        <td>${orderBill.auditor.name}</td>
                        <td>
                            <c:if test="${orderBill.status==0}">
                                <span style="color: red">待审核</span>
                            </c:if>
                            <c:if test="${orderBill.status==1}">
                                <span style="color: green">已审核</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${orderBill.status==0}">
                                <a href="/orderBill/input.do?id=${orderBill.id}">编辑</a>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="/orderBill/delete.do?id=${orderBill.id}">删除</a>
                                <a href="javascript:;" class="btn_audit"
                                   data-url="/orderBill/audit.do?id=${orderBill.id}">审核</a>
                            </c:if>
                            <c:if test="${orderBill.status==1}">
                                <a href="/orderBill/viewBill.do?id=${orderBill.id}">查看</a>
                            </c:if>
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
