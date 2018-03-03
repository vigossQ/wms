<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".searchproduct").click(function () {
                var currentTr = $(this).closest("tr");
                var url = "/product/selectProductList.do";
                $.dialog.open(url, {
                            id: 'ajxxList',
                            title: '商品信息',
                            width: 900,
                            height: 550,
                            left: '50%',
                            top: '50%',
                            background: '#000000',
                            opacity: 0.2,
                            lock: true,
                            resize: false,
                            close: function () {
                                var jsonObject = $.dialog.data("jsonObject");
                                if (jsonObject) {
                                    $(currentTr).find("input[tag=pid]").val(jsonObject.id);
                                    $(currentTr).find("input[tag=name]").val(jsonObject.name);
                                    $(currentTr).find("span[tag=brand]").text(jsonObject.brandName);
                                    $(currentTr).find("input[tag=costPrice]").val(jsonObject.costPrice);
                                    $(currentTr).find("img[tag=imagePath]").prop("src", jsonObject.imagePath);
                                    //清除缓存
                                    $(jsonObject).val(null);
                                }
                            }
                        },
                        false);
            });
            //小计
            $("input[tag=costPrice],input[tag=number]").blur(function () {
                var costPrice = parseFloat($(this).closest("tr").find("input[tag=costPrice]").val()) || 0;
                var number = parseFloat($(this).closest("tr").find("input[tag=number]").val()) || 0;
                $(this).closest("tr").find("span[tag=amount]").text((costPrice * number).toFixed(2));
            });
            //添加明细
            $(".appendRow").click(function () {
                var newTr = $("#edit_table_body tr:first").clone(true);
                $(newTr).find("input[tag=pid]").val("");
                $(newTr).find("input[tag=name]").val("");
                $(newTr).find("input[tag=number]").val("");
                $(newTr).find("input[tag=remark]").val("");
                $(newTr).find("span[tag=brand]").text("");
                $(newTr).find("span[tag=amount]").text("");
                $(newTr).find("input[tag=costPrice]").val("");
                $(newTr).find("img[tag=imagePath]").prop("src", "");
                $("#edit_table_body").append(newTr);
            });
            //提交的时候保存明细
            $("#editForm").submit(function () {
                $("#edit_table_body tr").each(function (index, item) {
                    $(item).find("input[tag=pid]").prop("name", "items[" + index + "].product.id");
                    $(item).find("input[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
                    $(item).find("input[tag=number]").prop("name", "items[" + index + "].number");
                    $(item).find("input[tag=remark]").prop("name", "items[" + index + "].remark");
                });
            });

            //编辑删除明细
            $(".removeItem").click(function () {
                var currentTr = $(this).closest("tr");
                if ($("#edit_table_body tr").size() == 1) {
                    $(currentTr).find("input[tag=pid]").val("");
                    $(currentTr).find("input[tag=name]").val("");
                    $(currentTr).find("input[tag=number]").val("");
                    $(currentTr).find("input[tag=remark]").val("");
                    $(currentTr).find("span[tag=brand]").text("");
                    $(currentTr).find("span[tag=amount]").text("");
                    $(currentTr).find("input[tag=costPrice]").val("");
                    $(currentTr).find("img[tag=imagePath]").prop("src", "");
                } else {
                    $(currentTr).remove();
                }
            });
        })
    </script>
</head>
<body>
<form name="editForm" data-url="/orderBill/list.do" action="/orderBill/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${orderBill.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02" value="${orderBill.sn}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <select name="supplier.id" class="ui_select01">
                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}" ${orderBill.supplier.id==supplier.id?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" onClick="WdatePicker()" readonly="readonly"
                               value="${vdate}" class="ui_input_txt02 Wdate"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
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
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:if test="${orderBill.id!=null}">
                                <c:forEach items="${orderBill.items}" var="item">
                                    <tr>
                                        <td>
                                            <img width="80px" tag="imagePath" src="${item.product.imagePath}"/>
                                        </td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${item.product.name}"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <input type="hidden" name="items.product.id" tag="pid" value="${item.product.id}"/>
                                        </td>
                                        <td><span tag="brand">${item.product.brandName}</span></td>
                                        <td><input tag="costPrice" name="items.costPrice"
                                                   class="ui_input_txt00" value="${item.costPrice}"/></td>
                                        <td><input tag="number" name="items.number"
                                                   class="ui_input_txt00" value="${item.number}"/></td>
                                        <td><span tag="amount">${item.amount}</span></td>
                                        <td><input tag="remark" name="items.remark"
                                                   class="ui_input_txt02" value="${item.remark}"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${orderBill.id==null}">
                                <tr>
                                    <td>
                                        <img width="80px" tag="imagePath"/>
                                    </td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" name="orderBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input tag="costPrice" name="orderBill.items.costPrice"
                                               class="ui_input_txt00"/></td>
                                    <td><input tag="number" name="orderBill.items.number"
                                               class="ui_input_txt00"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark" name="orderBill.items.remark"
                                               class="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
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