//左移右移
$(function () {
    //权限
    $("#selectAll").click(function () {
        $(".allPermissions option").appendTo($(".selectedPermissions"));
    });
    $("#deselectAll").click(function () {
        $(".selectedPermissions option").appendTo($(".allPermissions"));
    });
    $("#select").click(function () {
        $(".allPermissions option:checked").appendTo($(".selectedPermissions"));
    });
    $("#deselect").click(function () {
        $(".selectedPermissions option:checked").appendTo($(".allPermissions"));
    });
    //菜单
    $("#mselectAll").click(function () {
        $(".allSystemMenus option").appendTo($(".selectedSystemMenus"));
    });
    $("#mdeselectAll").click(function () {
        $(".selectedSystemMenus option").appendTo($(".allSystemMenus"));
    });
    $("#mselect").click(function () {
        $(".allSystemMenus option:checked").appendTo($(".selectedSystemMenus"));
    });
    $("#mdeselect").click(function () {
        $(".selectedSystemMenus option:checked").appendTo($(".allSystemMenus"));
    });
});
//去除重复
$(function () {
    var options = [];
    $(".selectedPermissions option").each(function (index, item) {
        options.push(this.value);
    });
    $(".allPermissions option").each(function (index, item) {
        if ($.inArray(this.value, options) >= 0) {
            $(this).remove();
        }
    });
    var moptions = [];
    $(".selectedSystemMenus option").each(function (index, item) {
        moptions.push(this.value);
    });
    $(".allSystemMenus option").each(function (index, item) {
        if ($.inArray(this.value, moptions) >= 0) {
            $(this).remove();
        }
    });
});
//提交前全选
$(function () {
    $(":submit").click(function () {
        $(".selectedPermissions option").each(function (index, item) {
            $(this).prop("selected",true);
        });
        $(".selectedSystemMenus option").each(function (index, item) {
            $(this).prop("selected",true);
        });
    })
});