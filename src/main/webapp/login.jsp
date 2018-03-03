<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>叩丁狼教育PSS（演示版）</title>
    <link href="/style/login_css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/system/login.js"></script>
</head>
<body>
<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <form id="submitForm" action="/login.do" method="post">
                    <div id="login_tip">
                        <span id="login_err" class="sty_txt2">${errorMsg}</span>
                    </div>
                    <div>
                        用户名：<input type="text" name="name" class="username" id="name" value="admin">
                    </div>
                    <div>
                        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" class="pwd" id="pwd"
                                                          value="1111">
                    </div>
                    <div id="btn_area">
                        <input type="button" class="login_btn" id="login_sub" value="登  录">
                        <input type="reset" class="login_btn" id="login_ret" value="重 置">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>