var storage = window.localStorage;
//初始化数据
function initData() {
    //获取用户当前等级信息
    getUserMemberLevelInformation();

}

function getUserMemberLevelInformation() {
    toast(2,"打开loading");
    $("#star i").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserMemberCardInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var member_card_level = data.data.card_level;
                if (member_card_level == 0){
                    document.getElementById("member_card_level").className = "vip member_card1";
                    document.getElementById("member_level_name").innerHTML = "银卡会员";
                    document.getElementById("uplev").style.display = "block";
                }
                else{
                    document.getElementById("member_card_level").className = "vip member_card2";
                    document.getElementById("member_level_name").innerHTML = "金卡会员";
                    document.getElementById("uplev").style.display = "none";
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