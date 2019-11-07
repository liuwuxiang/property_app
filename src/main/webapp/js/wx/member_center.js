var storage = window.localStorage;

//初始化数据
function initData() {
    //获取用户当前等级信息
    getUserMemberLevelInformation();
    getUserCanChooseLevel();

}

function getUserMemberLevelInformation() {
    toast(2,"打开loading");
    $("#star i").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserMemberLevelInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("member_level_name").innerHTML = data.data.level_name;
                var member_star = data.data.member_star;
                var is_can_upgrade = data.data.is_can_upgrade;
                if (is_can_upgrade == 0){
                    document.getElementById("uplev").style.display = "none";
                }
                else{
                    document.getElementById("uplev").style.display = "block";
                }
                var xx_icon_urls = data.data.xx_icon_urls;
                for (var index = 0;index < xx_icon_urls.length;index++){
                    var icon_url = xx_icon_urls[index];
                    var i_tag = "";
                    // if (index < member_star){
                    //     i_tag = "<i class=\"sel\" style='background-image: url(/property_system/images/wx/icon/icon_star.svg);'></i>";
                    // }
                    // else{
                    //     i_tag = "<i style='background-image: url(/property_system/images/wx/icon/icon_star.svg);'></i>";
                    // }
                    i_tag = "<i style='background-image: url("+icon_url+");'></i>";
                    $("#star").append(i_tag);
                    $("#contentInsert").html(data.data.brief_introduction);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取可选会员等级信息
function getUserCanChooseLevel() {
    toast(2,"打开loading");
    $("#member_levels_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserCanChooseLevel",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                var list = data.data.list;
                if (list.length <= 0){
                    toast(3,"关闭Loading");
                }
                else{
                    toast(3,"关闭Loading");
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li onclick=\"upgradeAction("+obj.id+")\">"+
                        "<img src=\""+obj.icon_url+"\" class=\"member_levels_li_icon\"/>"+
                            "<div class=\"member_levels_li_content\">"+
                            "<a class=\"member_levels_li_name\">"+obj.name+"</a>"+
                            "<a class=\"member_levels_li_condition\">需充值"+obj.recharge_consumption_integral+"消费积分</a>"+
                            "</div>"+
                            "<img src=\"/property_system/images/wx/icon_more.svg\" class=\"member_levels_li_arrow\"/>"+
                            "</li>";
                        $("#member_levels_ul").append(html);
                    }
                }
            }
            else{
                toast(3,"关闭Loading");
            }
        },
    });
}

//升级事件
function upgradeAction(member_level_id) {
    $("#ewmshow").removeClass('sel');
    self.window.location.href = "/property_system/wx/v1.0.0/memberLevelRechargeUpgradePage?member_level_id="+member_level_id;
}