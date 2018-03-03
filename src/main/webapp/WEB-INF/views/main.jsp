<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>叩丁狼教育PSS（演示版）</title>
    <link href="/style/main_css.css" rel="stylesheet" type="text/css"/>
    <link href="/style/zTreeStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/jquery-form/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/jquery-ztree/jquery.ztree.all-3.4.js"></script>
    <script type="text/javascript" src="/js/system/index.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div id="top">
    <div id="top_logo">
        <img alt="logo" src="/images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;">
    </div>
    <div id="top_links">
        <div id="top_op">
            <ul>
                <li>
                    <img alt="当前用户" src="/images/common/user.jpg">：
                    <span>${employee_in_session.name}</span>
                </li>
                <li>
                    <img alt="今天是" src="/images/common/date.jpg">：
                    <span id="day_day"></span>
                </li>
            </ul>
        </div>
        <div id="top_close">
            <a href="/logout.do" target="_parent">
                <img alt="退出系统" title="退出系统" src="/images/common/close.jpg" style="position: relative; top: 10px; left: 25px;">
            </a>
        </div>
    </div>
</div>
<!-- side menu start -->
<div id="side">
    <div id="left_menu">
        <ul id="TabPage2" style="height:200px; margin-top:50px;">
            <li id="left_tab1" class="selected" title="业务模块" data-rootmenu="business">
                <img alt="业务模块" title="业务模块" src="/images/common/1_hover.jpg" width="33" height="31">
            </li>
            <li id="left_tab2" title="系统管理" data-rootmenu="system">
                <img alt="系统管理" title="系统管理" src="/images/common/2.jpg" width="33" height="31">
            </li>
            <li id="left_tab3" title="报表" data-rootmenu="charts">
                <img alt="报表" title="报表" src="/images/common/3.jpg" width="33" height="31">
            </li>
        </ul>

        <div id="nav_show" style="position:absolute; bottom:0px; padding:10px;">
            <a href="javascript:;" id="show_hide_btn">
                <img alt="显示/隐藏" title="显示/隐藏" src="/images/common/nav_hide.png" width="35" height="35">
            </a>
        </div>
    </div>
    <div id="left_menu_cnt">
        <div id="nav_module">
            <img src="/images/common/module_1.png" width="210" height="58"/>
        </div>
        <div id="nav_resource">
            <ul id="dleft_tab1" class="ztree">

            </ul>
        </div>
    </div>
</div>
<!-- side menu start -->
<div id="top_nav">
    <span id="here_area">当前位置：系统&nbsp;>&nbsp;业务模块</span>
</div>
<div id="main">
    <iframe name="right" id="rightMain" src="" frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true"/>
</div>
</body>
</html>


   
 