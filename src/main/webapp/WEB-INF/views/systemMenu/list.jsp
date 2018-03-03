<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/systemMenu/list.do" method="post">
    <input type="hidden" name="parentId" value="${qo.parentId}">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <%--<input type="button" value="批量删除" class="ui_input_btn01 batchDelete" data-delete="/departmrnt/batchDelete.do"/>--%>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/systemMenu/input.do"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    当前菜单:<a href="/systemMenu/list.do">根目录</a>
    <c:forEach items="${parents}" var="parent">
        > <a href="/systemMenu/list.do?parentId=${parent.id}">${parent.name}</a>
    </c:forEach>
    <div class="ui_content">
        <div class="ui_tb">
            <table class="table" cellspacing="0" cellpadding="0" width="100%"
                   align="center" border="0">
                <tr>
                    <th width="30">
                        <input type="checkbox" id="all"/>
                    </th>
                    <th>编号</th>
                    <th>菜单名称</th>
                    <th>菜单编码</th>
                    <th>父级菜单</th>
                    <th>URL</th>
                    <th></th>
                </tr>
                <tbody>
                <c:forEach items="${result.data}" var="systemMenu">
                    <tr>
                        <td>
                            <input type="checkbox" name="IDCheck" class="acb"/>
                        </td>
                        <td>${systemMenu.id}</td>
                        <td>${systemMenu.name}</td>
                        <td>${systemMenu.sn}</td>
                        <td>${systemMenu.parent.name}</td>
                        <td>${systemMenu.url}</td>
                        <td>
                            <a href="/systemMenu/input.do?id=${systemMenu.id}">编辑</a>
                            <a href="javascript:;" class="btn_delete"
                               data-url="/systemMenu/delete.do?id=${systemMenu.id}">删除</a>
                            <a href="/systemMenu/list.do?parentId=${systemMenu.id}">下级菜单</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="/WEB-INF/views/commons/common_page.jsp"></jsp:include>
    </div>
</form>
</body>
</html>
