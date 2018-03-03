//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
    }
}

$(function () {
    loadDate();

    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});

//主页面使用zTree下拉条
$(function () {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                $("#rightMain").prop("src", treeNode.controller);
                var name = treeNode.name;
                var title = $("#TabPage2 li[class='selected']").prop("title");
                $("#here_area").text(function () {
                    this.innerHTML = "当前位置：系统&nbsp;>&nbsp;" + title + "&nbsp;>&nbsp;" + name;
                })
            }
        },
        async: {
            enable: true,
            url:"/systemMenu/loadMenuByParentSn.do",
            autoParam:["sn=parentSn"]
        }
    };
    var nodes = {
        business: [
            {id: 1, pId: 0, name: "业务模块",sn:'business', open: true, isParent: true}
        ],
        system: [
            {id: 2, pId: 0, name: "系统模块",sn:'system', open: true, isParent: true}
        ],
        charts: [
            {id: 3, pId: 0, name: "报表模块",sn:'charts', open: true, isParent: true}
        ]
    };
    $.fn.zTree.init($(".ztree"), setting, nodes["business"]);

    //绑定点击事件
    $("#TabPage2 li").click(function () {
        $("#TabPage2 li").each(function (index, item) {
            $(item).find("img").prop("src", "/images/common/" + (index + 1) + ".jpg");
        });
        //改变图片样式
        $("#TabPage2 li").removeClass("selected");
        $(this).addClass("selected");
        var index = $(this).index();
        $(this).find("img").prop("src", "/images/common/" + (index + 1) + "_hover.jpg");
        $("#nav_module").find("img").prop("src", "/images/common/module_" + (index + 1) + ".png");
        //提示当前位置
        var title = $(this).prop("title");
        $("#here_area").text(function () {
            this.innerHTML = "当前位置：系统&nbsp;>&nbsp;" + title;
        });

        $.fn.zTree.init($(".ztree"), setting, nodes[$(this).data("rootmenu")]);
    })
});
