jQuery.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});
$(function () {
    //新增
    $(".btn_input").click(function () {
        window.location.href = $(this).data("url");
    });
    //翻页按钮
    $(".btn_page").click(function () {
        var currentPage = $(this).data("page") || 1;
        $("#currentPage").val(currentPage);
        $("#searchForm").submit();
    });
    //下拉列表
    $(":input[name='pageSize']").change(function () {
        $("#currentPage").val(1);
        $("#searchForm").submit();
    });
    $(":input[name='pageSize'] option").each(function (index, item) {
        var pageSize = $(":input[name='pageSize']").data("pagesize");
        if (pageSize == item.innerHTML) {
            $(item).prop("selected", true);
        }
    });
});

//删除的判断
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        $.dialog({
            icon: "question",
            title: "确认删除",
            content: "您确认删除该条数据吗?",
            okVal: "删除",
            ok: function () {
                $.get(url, function (data) {
                    if (data.success) {
                        $.dialog({
                            icon: "face-smile",
                            title: "温馨提示",
                            content: "删除成功",
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
//批量删除
$(function () {
    $(".batchDelete").click(function () {
        var url = $(this).data("delete");
        var ids = [];
        $(".acb:checked").each(function (index, item) {
            ids.push(item.value);
        });
        if (ids.length != 0) {
            $.dialog({
                icon: "question",
                title: "确认删除",
                content: "您确认删除选中数据吗?",
                okVal: "删除",
                ok: function () {
                    console.log(ids);
                    $.get(url, {ids: ids}, function (data) {
                        if (data.success) {
                            $.dialog({
                                icon: "face-smile",
                                title: "温馨提示",
                                content: "删除成功",
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
        } else {
            $.dialog({
                icon: "question",
                title: "温馨提示",
                content: "请选择您要删除的内容",
                ok: true,
                drag: false,
                lock: true,
                resize: false,
                opacity: 0.4
            });
        }
    });
    //全选功能
    $("#all").change(function () {
        $(".acb").prop("checked", this.checked);
    });

    //编辑提示
    $(function () {
        $("#editForm").ajaxForm(function (data) {
            var dialog = $.dialog({
                title: "温馨提示",
                icon: "face-smile",
                content: "保存中...",
                drag: false,
                lock: true,
                resize: false,
                opacity: 0.4,
                ok: function () {
                    window.location.href = $("#editForm").data("url");
                }
            });
            if (data.success) {
                dialog.content("保存成功");
            } else {
                dialog.content(data.errorMsg);
            }
        });
    })
});