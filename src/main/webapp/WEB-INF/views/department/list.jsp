<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>PSS-部门管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/department/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <%--<input type="button" value="批量删除" class="ui_input_btn01 batchDelete" data-delete="/departmrnt/batchDelete.do"/>--%>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/department/input.do"/>
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
                    <th>编号</th>
                    <th>部门名称</th>
                    <th>部门编码</th>
                    <th></th>
                </tr>
                <tbody>
                <c:forEach items="${result.data}" var="department">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.sn}</td>
                        <td>
                            <a href="/department/input.do?id=${department.id}">编辑</a>
                            <a href="javascript:;" class="btn_delete"
                               data-url="/department/delete.do?id=${department.id}">删除</a>
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
