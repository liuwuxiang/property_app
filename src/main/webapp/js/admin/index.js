var element;
layui.use('element', function () {
    element = layui.element;


    // 获取未读消息
    getAdminUnreadMsg()
    //…
});

//基本资料修改
function setAdminInformation() {
    //页面层
    layer.open({
        type: 2,
        title: '资料修改',
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '300px'], //宽高
        content: '/property_system/admin/setAdminInformation'
    });
}

//管理员密码修改
function setAdminPWD() {
    //页面层
    layer.open({
        type: 2,
        title: '密码修改',
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '350px'], //宽高
        content: '/property_system/admin/setAdminPwd'
    });
}

//退出登录
function exitLogin() {
    layer.confirm('确定退出登录？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        exitLoginNetwork();
    }, function () {

    });
}

//退出登录网络事件
function exitLoginNetwork() {
    var loading_index = layer.load(1);
    $.ajax({
        url: "/property_system/admin/adminExitLogin",
        type: "POST",
        dataType: 'json',
        data: {},
        success: function (data) {
            layer.close(loading_index);
            if (data.status == 0) {
                self.window.location.href = "/property_system/admin";
            }
            else {
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//获取菜单
function getMenus() {
    var loading_index = layer.load(1);
    $.ajax({
        url: "/property_system/admin/getAdminMenus",
        type: "POST",
        dataType: 'json',
        data: {},
        success: function (data) {
            layer.close(loading_index);
            if (data.status == 0) {
                var list = data.data.menus;
                for (var index = 0; index < list.length; index++) {
                    var obj = list[index];
                    var twoMenus = obj.two_menus;
                    var html = "<li class=\"layui-nav-item\">" +
                        "<a class=\"\" href=\"javascript:;\">" + obj.one_menu_name + "</a>" +
                        "<dl class=\"layui-nav-child\">";
                    for (var index2 = 0; index2 < twoMenus.length; index2++) {
                        var menuObject = twoMenus[index2];
                        html = html + "<dd><a href=\"" + menuObject.menus_url + "\" target=\"option\">" + menuObject.name + "</a></dd>"
                    }
                    html = html + "</dl></li>";
                    $("#admins_menus").append(html);
                }
                element.init();
            }
            else {
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

function getAdminUnreadMsg() {
    $.ajax({
        url: "/property_system/admin/getAdminUnreadMsg",
        type: "POST",
        dataType: 'json',
        data: {},
        success: function (data) {
            console.log( data.data == null);
            if (parseInt(data.status) === 0 && data.data === undefined && data.data == '' && data.data == null) {

                var html = '<div class="right_tip"><ul>';
                if (data.data.legalize !== undefined && data.data.legalize != '') {
                    html += '<li>您有新的商户未认证,请尽快处理</li>';
                }
                if (data.data.doings_spread !== undefined && data.data.doings_spread != '') {
                    for (var i = 0; i < data.data.doings_spread.length; i++) {
                        html += '<li>商家ID为:' + data.data.doings_spread[i].business_id + ',店铺名称为:'+data.data.doings_spread[i].store_name+'的商家做活动推广,请审核!</li>';
                        // html += '<li>店铺名称为:'+data.data.doings_spread[i].store_name+'的商家做活动推广,请审核!</li>';
                    }
                }

                if (data.data.suggestion_feedback !== undefined && data.data.suggestion_feedback != '') {
                    html += '<li>您有' + data.data.suggestion_feedback.length + '条新消息未读,请注意查看</li>';
                }

                html += '</ul></div>';

                layer.open({
                    area:'450px',
                    offset: 'rb',
                    title: '通知',
                    type: 1,
                    shade: 0,
                    anim: 2,
                    tipsMore: true,
                    content: html,
                    skin:'layui-layer-molv'
                });
            }
        }
    });
}
