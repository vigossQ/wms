<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
</head>
<body>
<form name="editForm" data-url="/product/list.do" action="/product/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${product.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">商品编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">商品编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${product.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品名称</td>
                    <td class="ui_text_lt">
                        <input name="name" class="ui_input_txt02" value="${product.name}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">成本价格</td>
                    <td class="ui_text_lt">
                        <input name="costPrice" class="ui_input_txt02" value="${product.costPrice}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">销售价格</td>
                    <td class="ui_text_lt">
                        <input name="salePrice" class="ui_input_txt02" value="${product.salePrice}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品品牌</td>
                    <td class="ui_text_lt">
                        <select name="brandId" class="ui_select01">
                            <c:forEach items="${brands}" var="brand">
                            <option value="${brand.id}"
                                ${product.brandId==brand.id?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品图片</td>
                    <td class="ui_text_lt">
                        <input type="file" name="pic" class="ui_input_txt02"/>
                        <img src="${product.smallImagePath}" width="80px"/>

                        <input type="hidden" name="imagePath" value="${product.imagePath}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品简介</td>
                    <td class="ui_text_lt">
                        <textarea name="intro" rows="10" cols="80">${product.intro}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定编辑" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>