var storage = window.localStorage;
var busying = false;

function initData() {
    getUserMemberLevelInformation();
    getBusinessType();
}

function toggleMenu() {
    // var menuWrapper = document.getElementById("menu-wrapper");
    // var menu = document.getElementById("menu");
    // var menuWrapperClassList = menuWrapper.classList;
    //
    // var backdrop = document.getElementById("menu-backdrop");
    // var memberqy = document.getElementById("memberqy");
    // var business_type_div = document.getElementById("business_type_div");
    // var down_arrow_div = document.getElementById("down_arrow_div");
    // backdrop.addEventListener('tap', closeQrCode);
    // memberqy.addEventListener('tap', closeQrCode);
    // business_type_div.addEventListener('tap', closeQrCode);
    // down_arrow_div.addEventListener('tap', closeQrCode);
    //
    // if(busying) {
    //     return;
    // }
    // busying = true;
    // if(menuWrapperClassList.contains('mui-active')) {
    //     document.body.classList.remove('menu-open');
    //     menuWrapper.className = 'menu-wrapper fade-out-up animated';
    //     menu.className = 'menu bounce-out-up animated';
    //     setTimeout(function() {
    //         // backdrop.style.opacity = 0;
    //         menuWrapper.classList.add('hidden');
    //     }, 500);
    // } else {
    //     document.body.classList.add('menu-open');
    //     menuWrapper.className = 'menu-wrapper fade-in-down animated mui-active';
    //     menu.className = 'menu bounce-in-down animated';
    //     // backdrop.style.opacity = 1;
    // }
    // setTimeout(function() {
    //     busying = false;
    // }, 500);
}

//关闭二维码视图
function closeQrCode(){
    var menuWrapper = document.getElementById("menu-wrapper");
    var menu = document.getElementById("menu");
    var menuWrapperClassList = menuWrapper.classList;

    document.body.classList.remove('menu-open');
    menuWrapper.className = 'menu-wrapper fade-out-up animated';
    menu.className = 'menu bounce-out-up animated';
    setTimeout(function() {
        // backdrop.style.opacity = 0;
        menuWrapper.classList.add('hidden');
    }, 500);
}

function liClick(){
    // alert("li标签点击");
}


//商家类别点击事件
function businessTypeClick(index_id){
    var zk_swith = document.getElementById("zk_swith_"+index_id);
    var business_ul = document.getElementById("business_ul_"+index_id);
    var control_id = "business_ul_"+index_id;
    if(zk_swith.value == 0){
        toast(2,"打开loading");
        $("#"+control_id+" li").remove();
        $.ajax({
            url:"/property_system/app/v1.0.0/getWnkBusinessByTypeId",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"user_id":storage["user_id"],"type_id":index_id},
            success:function(data){
                if (data.status == 0){
                    toast(3,"关闭loading");
                    var list = data.data;
                    for (var index = 0;index < list.length;index++){
                        var obj = list[index];
                        var html = "<li onclick=\"joinBusinessHome("+obj.business_id+")\">"+
                            "<a class=\"business_area\">"+obj.area+"</a>"+
                            "<a class=\"business_name\">"+obj.store_name+"</a>"+
                            "</li>";
                        $("#"+control_id+"").append(html);
                    }
                    document.getElementById("zk_swith_"+index_id).value = 1;
                    document.getElementById("business_ul_"+index_id).style.display = "block";

                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
    else{
        document.getElementById("zk_swith_"+index_id).value = 0;
        document.getElementById("business_ul_"+index_id).style.display = "none";
    }

}

//进入商家主页
function joinBusinessHome(business_id){
    self.window.location.href = "/property_system/wx/v1.0.0/joinBusinessHome?business_id="+business_id;
}


function getUserMemberLevelInformation() {
    toast(2,"打开loading");
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
                    document.getElementById("top_background_img").src="/property_system/images/wx/yinka_background.png";
                }
                else{
                    document.getElementById("top_background_img").src="/property_system/images/wx/jinka_background.png";
                }
                var tuirenshu = data.data.tuirenshu;
                for (var index = 0;index < tuirenshu;index++){
                    var html = "<img src=\"/property_system/images/wx/yinka_xx.png\"/>";
                    if (member_card_level == 0){
                        html = "<img src=\"/property_system/images/wx/yinka_xx.png\"/>";
                    }
                    else{
                        html = "<img src=\"/property_system/images/wx/jinka_xx.png\"/>";
                    }
                    $("#xx_div").append(html);
                }
                document.getElementById("term_validity_tag").innerText = "有效期至:"+data.data.term_date;
                document.getElementById("card_number_tag").innerText = "NO."+data.data.card_no;
                document.getElementById("wnk_qrcode_img").src = data.data.wnk_qrcode_img;

            }
            else{
                toast(3,"关闭Loading");
            }
        },
    });
}

//获取商家信息
function getBusiness() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/selectBusinesss",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list1 = data.data.list1;
                var list2 = data.data.list2;
                var list3 = data.data.list3;
                var list4 = data.data.list4;
                var list5 = data.data.list5;
                for (var index = 0;index < list1.length;index++){
                    var obj = list1[index];
                    var html = "<li onclick=\"joinBusinessHome("+obj.id+")\">"+
                        "<a class='business_area'>"+obj.area+"</a>"+
                        "<a class='business_name'>"+obj.name+"</a>"+
                        "</li>";
                    $("#business_ul_2").append(html);
                }

                for (var index = 0;index < list2.length;index++){
                    var obj = list2[index];
                    var html = "<li onclick=\"joinBusinessHome("+obj.id+")\">"+
                        "<a class='business_area'>"+obj.area+"</a>"+
                        "<a class='business_name'>"+obj.name+"</a>"+
                        "</li>";
                    $("#business_ul_1").append(html);
                }

                for (var index = 0;index < list3.length;index++){
                    var obj = list3[index];
                    var html = "<li onclick=\"joinBusinessHome("+obj.id+")\">"+
                        "<a class='business_area'>"+obj.area+"</a>"+
                        "<a class='business_name'>"+obj.name+"</a>"+
                        "</li>";
                    $("#business_ul_0").append(html);
                }

                for (var index = 0;index < list4.length;index++){
                    var obj = list4[index];
                    var html = "<li onclick=\"joinBusinessHome("+obj.id+")\">"+
                        "<a class='business_area'>"+obj.area+"</a>"+
                        "<a class='business_name'>"+obj.name+"</a>"+
                        "</li>";
                    $("#business_ul_3").append(html);
                }

                for (var index = 0;index < list5.length;index++){
                    var obj = list5[index];
                    var html = "<li onclick=\"joinBusinessHome("+obj.id+")\">"+
                        "<a class='business_area'>"+obj.area+"</a>"+
                        "<a class='business_name'>"+obj.name+"</a>"+
                        "</li>";
                    $("#business_ul_5").append(html);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


//获取商家分类
function getBusinessType() {
    toast(2,"打开loading");
    $("#business_type_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getWnkBusinessType",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<li class=\"business_type_ul_li\">"+
                        "<input type=\"hidden\" value=\"0\" id=\"zk_swith_"+obj.id+"\"/>"+
                        "<div class=\"business_type_li_div\" onclick=\"businessTypeClick("+obj.id+")\">"+
                        "<img src=\""+obj.background_photo_id+"\" class=\"li_background_img\"/>"+
                        "<div class=\"li_top_div\">"+
                        "<img src=\""+obj.logo_photo_id+"\"/>"+
                        "<a>"+obj.name+"</a>"+
                        "</div>"+
                        "<a class=\"surplus_tip\" id=\"surplus_tip\">剩余:"+obj.month_free_number+"次</a>"+
                    "</div>"+
                    "<ul class=\"business_ul\" id=\"business_ul_"+obj.id+"\">"+

                        "</ul>"+
                        "</li>";
                    $("#business_type_ul").append(html);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}