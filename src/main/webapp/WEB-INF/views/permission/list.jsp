<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>PSS-权限管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".btn_reload").click(function () {
                var url = $(this).data("url");
                $.dialog({
                    icon: "question",
                    title: "确认加载",
                    content: "加载需要较长时间,您确定要加载权限吗?",
                    ok: function () {
                        var dialog = $.dialog({
                            icon: "face-smile",
                            title: "温馨提示",
                            drag: false,
                            lock: true,
                            resize: false,
                            opacity: 0.4
                        });
                        $.get(url, function (data) {
                            if (data.success) {
                                dialog.content("加载成功").button({
                                    name: "朕知道了",
                                    callback: function () {
                                        window.location.reload();
                                    }
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
            })
        })
    </script>
</head>
<body>
<form id="searchForm" action="/permission/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="加载权限" class="ui_input_btn01 btn_reload"
                               data-url="/permission/reload.do"/>
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
                        <th>权限表达式</th>
                        <th>权限名</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.data}" var="permission">
                        <tr>
                            <td>
                                <input type="checkbox" name="IDCheck" class="acb"/>
                            </td>
                            <td>${permission.id}</td>
                            <td>${permission.expression}</td>
                            <td>${permission.name}</td>
                            <td>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="/permission/delete.do?id=${permission.id}">删除</a>
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
