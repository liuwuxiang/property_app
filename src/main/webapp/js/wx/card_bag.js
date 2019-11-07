var storage = window.localStorage;
/*
 *	列表项初始化
 * type=0:会员卡，type=1：优惠券
 * */
function listOptionInit(type){
	var member_card_ul = document.getElementById("member_card_ul");
	var coupon_ul = document.getElementById("coupon_ul");
	if(type == 0){
		member_card_ul.style.display = "block";
		coupon_ul.style.display = "none";
		document.getElementById("member_card_item").setAttribute("class","item sel"); 
		document.getElementById("coupon_item").setAttribute("class","item");
        getUserMemberLevelInformation();
	}
	else{
		member_card_ul.style.display = "none";
		coupon_ul.style.display = "block";
		document.getElementById("member_card_item").setAttribute("class","item"); 
		document.getElementById("coupon_item").setAttribute("class","item sel");
        getCouponList();
	}
    mui('.mui-scroll-wrapper').scroll();
}

/*
 *	进入会员卡详情页
 * */
function member_card_detail(){
    self.window.location.href = "/property_system/wx/v1.0.0/memberCardDetail?card_id="+1;
}

/*
 *	优惠券详情页
 * */
function coupon_detail(){
    self.window.location.href = "/property_system/wx/v1.0.0/couponDetail?coupon_id="+1;
}

var storage = window.localStorage;

function getUserMemberLevelInformation() {
    $(".my_member_card_li").hide();
    $(".open_my_member_card_li").hide();
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
                publicnull_tip("暂无数据",0);
                var member_card_level = data.data.card_level;
                if (member_card_level == -1){
                    $(".my_member_card_li").hide();
                    $(".open_my_member_card_li").show();
                }
                else{
                    $(".my_member_card_li").show();
                    $(".open_my_member_card_li").hide();
                    if (member_card_level == 0){
                        document.getElementById("my_member_card_li").style.backgroundImage="url('/property_system/images/wx/yinka_background.png')";
                    }
                    else{
                        document.getElementById("my_member_card_li").style.backgroundImage="url('/property_system/images/wx/jinka_background.png')";
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
                        // $("#contentInsert").html(data.data.brief_introduction);
                    }
                    document.getElementById("term_validity_tag").innerText = "有效期至:"+data.data.term_date;
                    document.getElementById("card_number_tag").innerText = "NO."+data.data.card_no;
                }

            }
            else{
                $(".my_member_card_li").hide();
                $(".open_my_member_card_li").show();
                toast(3,"关闭Loading");
            }
        },
    });
}

//获取会员卡
function getMemberCardList() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#member_card_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getMyMemberCards",
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
                    toast(1,"暂无数据");
                    publicnull_tip("暂无数据",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var fold_number = obj.fold_number.replace("折","");
                        var html = "<li onclick=\"member_card_detail()\">"+
                            "<div class=\"item\">"+
                            "<b class=\"word\">VIP</b>"+
                            "<p class=\"name\">"+obj.business_name+"</p>"+
                            "<span class=\"time\">有效期至"+obj.term_validity+"</span>"+
                            "<a class=\"usebut\">使用</a>"+
                            "<p class=\"num\"><b>"+fold_number+"</b>折</p>"+
                        "<p class=\"number\">No."+obj.member_no+"</p>"+
                        "</div>"+
                        "</li>";
                        $("#member_card_ul").append(html);
                    }
                }
            }
            else{
                toast(1,data.msg);
                publicnull_tip(data.msg,1);
            }
        },
    });
}

//获取优惠券信息
function getCouponList() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#coupon_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getMyCoupon",
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
                    toast(1,"暂无数据");
                    publicnull_tip("暂无数据",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li onclick=\"coupon_detail()\">"+
                            "<a class=\"item\">"+
                            "<p class=\"icon\"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>"+
                        "<p class=\"line\"><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>"+
                        "<div class=\"info\">"+
                            "<p class=\"tit\">"+obj.coupon_name+"</p>"+
                            "<p class=\"desc\">"+obj.consumption_amount+"</p>"+
                            "<p class=\"time\">有效期至"+obj.term_validity+"</p>"+
                            "</div>"+
                            "<div class=\"numwrap\">"+
                            "<p class=\"num\"><span>¥</span><b>"+obj.coupon_amount+"</b></p>"+
                        "<span class=\"but\">使用说明</span>"+
                            "</div>"+
                            "</a>"+
                            "</li>";
                        $("#coupon_ul").append(html);
                    }
                }
            }
            else{
                toast(1,data.msg);
                publicnull_tip(data.msg,1);
            }
        },
    });
}

/*
* 提示修改
* */
function publicnull_tip(content,state) {
    var publicnull_tip = document.getElementById("publicnull_tip");
    if (state == 0){
        publicnull_tip.style.display = "none";
    }
    else{
        document.getElementById("request_tip").innerText = content;
        publicnull_tip.style.display = "block";
    }
}

//开学生卡
function openStudentCard() {
    window.event.cancelBubble = true;//停止冒泡
    window.event.returnValue = false;//阻止事件的默认行为
    self.window.location.href = "/property_system/wx/v1.0.0/joinStudentAuthorization";
}

//开尊贵卡
function openZunGuiCard() {
    window.event.cancelBubble = true;//停止冒泡
    window.event.returnValue = false;//阻止事件的默认行为
    self.window.location.href = "/property_system/wx/v1.0.0/joinAdultAuthorization";
}

//打开协议框
function openXY() {
    window.event.cancelBubble = true;//停止冒泡
    window.event.returnValue = false;//阻止事件的默认行为
    mui('#my_qrcode_div').popover('show',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
}

//开卡
function openCard() {
    mui('#my_qrcode_div').popover('toggle',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserBaseInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                //是否实名认证或设置支付密码
                realAuthentication(data.data.id_card_state,data.data.is_pay_state);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//实名认证处理
function realAuthentication(id_card_state,is_pay_state) {
    if (id_card_state == -1){
        mui.alert('请先进行实名认证', '实名认证', function() {
            self.window.location.href = "/property_system/wx/v1.0.0/idCardAuthentication";
        });
    }
    else{
        self.window.location.href = "/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=-1";
    }
}

//进入万能卡权益页面
function joinWNK() {
    self.window.location.href = "/property_system/wx/v1.0.0/universalCardLegal";
}


//进入积分商城
function joinIntgralShopping() {
    self.window.location.href = "/property_system/wx/v1.0.0/myIntegral";
}