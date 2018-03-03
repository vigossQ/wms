//左移右移
$(function () {
    $("#selectAll").click(function () {
        $(".allRoles option").appendTo($(".selectedRoles"));
    });
    $("#deselectAll").click(function () {
        $(".selectedRoles option").appendTo($(".allRoles"));
    });
    $("#select").click(function () {
        $(".allRoles option:checked").appendTo($(".selectedRoles"));
    });
    $("#deselect").click(function () {
        $(".selectedRoles option:checked").appendTo($(".allRoles"));
    });
});
//去除重复
$(function () {
    var options = [];
    $(".selectedRoles option").each(function (index, item) {
        options.push(this.value);
    });
    $(".allRoles option").each(function (index, item) {
        if ($.inArray(this.value, options) >= 0) {
            $(this).remove();
        }
    });
});
//提交前全选
$(function () {
    $(":submit").click(function () {
        $(".selectedRoles option").each(function (index, item) {
            $(this).prop("selected",true);
        });
    })
});