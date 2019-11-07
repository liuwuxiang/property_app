var storage = window.localStorage;

//选项切换事件(0-新订单,1-已完成订单,2-权益订单)
function optionClick(index){
	if(index == 0){
		document.getElementById("new_order").setAttribute("class","item sel"); 
		document.getElementById("finish_order").setAttribute("class","item");
        document.getElementById("quanyi_order").setAttribute("class","item");
	}
	else if (index == 1){
        document.getElementById("finish_order").setAttribute("class","item sel");
        document.getElementById("new_order").setAttribute("class","item");
        document.getElementById("quanyi_order").setAttribute("class","item");
    }
	else{
        document.getElementById("finish_order").setAttribute("class","item");
        document.getElementById("new_order").setAttribute("class","item");
        document.getElementById("quanyi_order").setAttribute("class","item sel");
	}
	if (index == 0 || index == 1){
        getOrderData(index);
    }
    else{
        getQuanYiOrderData();
    }
}


//初始化数据
function initData() {
    optionClick(0);
    wxScanRedy();
}

//获取权益订单数据
function getQuanYiOrderData() {
    toast(2,"打开loading");
    $("#order_in_progress_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllWnkQyOrder",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data.list;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var guige_name = obj.guige_name;
                        if (guige_name == undefined){
                            guige_name = "";
                        }
                        var html = "<li class=\"order_li\">"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"li_top_day_tag\">"+obj.day+"</a>"+
                            "<a class=\"li_order_no_tag\">NO."+obj.order_no+"</a>"+
                        "</div>"+
                        "<div class=\"li_commodites_div\">"+
                            "<a class=\"commodities_number_tag\">基础信息</a>"+
                            "<div class=\"user_div\">"+
                            "<a>会员名称："+obj.user_name+"</a>"+
                        "<a>卡片等级："+obj.member_card_level+"</a>"+
                        "<a>商品名称："+obj.commodity_name+"</a>"+
                            "<a>使用次数："+obj.make_number+"次</a>"+
                            "<a>商品规格："+guige_name+"</a>"+
                        "</div>"+
                        "</div>"+
                        "<div class=\"li_bottom_div\">"+
                            "<a class=\"line_order_time\">"+obj.line_order_date_str+"</a>"+
                        "<a class=\"order_price_tag\">"+obj.state_str+"</a>"+
                            "</div>"+
                            "</li>";
                        $("#order_in_progress_ul").append(html);
                    }
                    document.getElementById("top_people_static_tag").innerText = "消费人数："+data.data.people_number+"人";
                    document.getElementById("top_number_static_tag").innerText = "消费次数："+data.data.make_number+"次";
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取订单数据
function getOrderData(type) {
    toast(2,"打开loading");
    $("#order_in_progress_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllOrder",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type":type},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data.list;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var commodity_list = obj.commodity_list;
                        var html = "<li class=\"order_li\">"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"li_top_day_tag\">"+obj.day+"</a>"+
                            "<a class=\"li_order_no_tag\">NO."+obj.order_no+"</a>"+
                        "</div>"+
                        "<div class=\"li_commodites_div\">"+
                            "<a class=\"commodities_number_tag\">商品("+obj.commodity_count+")</a>"+
                            "<ul class=\"commodities_ul\">";
                        for (var index2 = 0;index2 < commodity_list.length;index2++){
                        	var commodity = commodity_list[index2];
                        	var specifications_name = commodity.specifications_name;
                        	if (specifications_name == undefined){
                                specifications_name = "";
                            }
                        	html = html + "<li>"+
                                "<a class=\"commodity_name_tag\">"+commodity.commodity_name+"("+specifications_name+")</a>"+
                                "<a class=\"commodity_number_tag\">x"+commodity.buy_number+"</a>"+
                                "<a class=\"price_tag\">"+commodity.count_amount+"</a>"+
                                "</li>";
						}
						html = html + "</ul>"+
                            "</div>"+
                            "<div class=\"li_bottom_div\">"+
                            "<a class=\"line_order_time\">"+obj.submit_time_str+"</a>"+
                        "<a class=\"order_price_tag\">￥"+obj.amount+"</a>"+
                        "</div>";
                        $("#order_in_progress_ul").append(html);
                    }
                    document.getElementById("top_people_static_tag").innerText = "消费人数："+data.data.user_number+"人";
                    document.getElementById("top_number_static_tag").innerText = "消费次数："+list.length+"次";
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//微信扫一扫准备
function wxScanRedy() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/makeWxScanSetting",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                wxConfig(data.data.appId,data.data.timestamp,data.data.nonceStr,data.data.signature);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

function wxConfig(appId,timestamp, nonceStr, signature) {
    wx.config({
        debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId : appId, // 必填，公众号的唯一标识
        timestamp : timestamp, // 必填，生成签名的时间戳
        nonceStr : nonceStr, // 必填，生成签名的随机串
        signature : signature,// 必填，签名，见附录1
        jsApiList : ['scanQRCode']// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        // alert("config完成");
    });
    wx.error(function(res){
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        alert("config失败");
    });
}

//扫描二维码
function scanQrCode() {
    wx.scanQRCode({
        needResult : 1,
        scanType : [ "qrCode", "barCode" ],
        success : function(res) {
            console.log(res);
            // alert(JSON.stringify(res));
            var result = res.resultStr;
            var result2 = JSON.parse(result);
            if (result2.type == 1){
                memberQYQrCodeMake(result2.user_id,result2.order_no);
            }
            else if (result2.type == 2){
                orderQrcodeMake(result2.user_id,result2.order_no);
            } else if(result2.type == 3) {
                IntegralOrderConfirm(result2.user_id,result2.order_no);
            }
        },
        fail : function(res) {
            console.log(res)
            alert(JSON.stringify(res));

        }
    });
}
// 商家商城积分订单使用
function IntegralOrderConfirm(user_id,order_no) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business/getIntegralOrderByOrderId",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"order_no":order_no,"user_id":user_id},
        success:function(data){
            toast(3,"关闭loading");
            alert(data.msg);
            if (data.status == 0){
                self.window.location.href = "/property_system/wnk_business/joinIntegralOrderConfirm?" +
                    "order_id="+order_no+
                    "&userId="+user_id;
            } else {
                toast(1,data.msg);
            }
        },
    });
}

//会员权益免费使用网络事件
function memberQYQrCodeMake(user_id,order_no) {
    // self.window.location.href = "/property_system/wnk_business/joinMemberQYMake?user_id="+user_id;
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/wnkQYOrderMake",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"order_no":order_no,"user_id":user_id},
        success:function(data){
            toast(3,"关闭loading");
            alert(data.msg);
            if (data.status == 0){
                self.window.location.href = "/property_system/wnk_business/getWnkOrderDetail?order_no="+order_no+"&type=1";
            }
        },
    });
}

//订单使用
function orderQrcodeMake(user_id,order_no) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/orderQrcodeMake",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"order_no":order_no,"user_id":user_id},
        success:function(data){
            toast(3,"关闭loading");
            alert(data.msg);
            if (data.status == 0){
                self.window.location.href = "/property_system/wnk_business/getWnkOrderDetail?order_no="+order_no+"&type=0";
            }
        },
    });
}