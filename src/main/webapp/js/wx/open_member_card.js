var storage = window.localStorage;
//type=-1:开卡,type=0:升级
var type = -1;
//万能卡购买套餐
var wnkBuyMealList = null;
//-1-获取套餐类型不限,0-普通套餐,1-学生套餐,2-尊贵套餐
var open_card_type = -1;

//type=-1:开卡,type=0:升级,card_level=0:银卡会员,card_level=1:金卡会员
function initData(current_type,card_level,current_open_card_type) {
    type = current_type;
    open_card_type = current_open_card_type;
    if (open_card_type == 1){
        document.getElementById("business_button").style.display = "none";
    }
    // if (type == 0){
    //     if (card_level == 0){
    //         document.getElementById("yinka_member_card").style.display = "none";
    //     }
    //     else if (card_level == 1){
    //         document.getElementById("yinka_member_card").style.display = "none";
    //         document.getElementById("jinka_member_card").style.display = "none";
    //     }
    // }
    getWnkBuyMeal();
}

//同意办理会员卡(type=0-商家办理,type=1-个人办理)
function arrgenHandelMemberCard(user_type) {
    var member_card_level = 0;
    var card_type_id = -1;
    var pay_way = -1;
    var general_integral_radio = document.getElementById("general_integral_radio");
    var consumption_integral_radio = document.getElementById("consumption_integral_radio");
    var wx_pay_radio = document.getElementById("wx_pay_radio");
    for (var index = 0;index < wnkBuyMealList.length;index++) {
        var obj = wnkBuyMealList[index];
        var radio = document.getElementById("qingchuncard_radio_"+obj.id);
        if (radio.checked){
            card_type_id = obj.id;
            continue;
        }
    }
    if (general_integral_radio.checked){
        pay_way = 1;
    }
    else if (consumption_integral_radio.checked){
        pay_way = 0;
    }
    else if (wx_pay_radio.checked){
        pay_way = 2;
    }
    if (card_type_id == -1){
        toast(1,"请选择一种卡片类型");
    }
    else if (pay_way == -1){
        toast(1,"请选择支付方式");
    }
    else{
        mui('#my_qrcode_div').popover('toggle',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
        if (pay_way == 0 || pay_way == 1){
            $.prompt({
                text: "请填写支付密码进行验证",
                title: "支付密码",
                onOK: function(text) {
                    if (text == undefined || text == ""){
                        toast(1,"请输入支付密码");
                    }
                    else{
                        handelMemberCardNetwork(member_card_level,pay_way,text,card_type_id,user_type);
                    }
                },
                onCancel: function() {

                },
                input: ''
            });
        }
        else{
            openCardWXPay(member_card_level,card_type_id,user_type);
        }
    }
}

//办理会员卡
function handelMemberCard() {
    var member_card_level = 0;
    var card_type_id = -1;
    var pay_way = -1;
    var general_integral_radio = document.getElementById("general_integral_radio");
    var consumption_integral_radio = document.getElementById("consumption_integral_radio");
    var wx_pay_radio = document.getElementById("wx_pay_radio");
    for (var index = 0;index < wnkBuyMealList.length;index++) {
        var obj = wnkBuyMealList[index];
        var radio = document.getElementById("qingchuncard_radio_"+obj.id);
        if (radio.checked){
            card_type_id = obj.id;
            continue;
        }
    }
    if (general_integral_radio.checked){
        pay_way = 1;
    }
    else if (consumption_integral_radio.checked){
        pay_way = 0;
    }
    else if (wx_pay_radio.checked){
        pay_way = 2;
    }
    if (card_type_id == -1){
        toast(1,"请选择一种卡片类型");
    }
    else if (pay_way == -1){
        toast(1,"请选择支付方式");
    }
    else{
        mui('#my_qrcode_div').popover('show',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
    }
}

//办理会员卡网络事件
function handelMemberCardNetwork(member_card_level,pay_way,pay_pwd,card_type,user_type) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/myMemberCardUpgradeOrHandle",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"type":type,"member_card_level":member_card_level,"pay_way":pay_way,"pay_pwd":pay_pwd,"card_type":card_type,"user_type":user_type},
        success:function(data){
            if (data.status == 0){
                toast(0,data.msg);
                self.window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//开卡-微信支付
function openCardWXPay(member_card_level,card_type,user_type) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/myMemberCardUpgradeOrHandleWXPay",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"member_card_level":member_card_level,"card_type":card_type,"user_type":user_type},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var wx_state = data.data.wx_state;
                if (wx_state == 0){
                    alert(data.msg);
                    self.window.location.href = "/property_system/wx/v1.0.0/accountAssociatio";
                }
                else{
                    var wx_config = data.data.wx_config;
                    pay(wx_config.appid,wx_config.timeStamp,wx_config.nonceStr,wx_config.packageValue,wx_config.paySign,wx_config.order_no);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


function pay(appid,timeStamp,nonceStr,packageValue,paySign,order_no){
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady(appid,timeStamp,nonceStr,packageValue,paySign,order_no);
    }
}
function onBridgeReady(appid,timeStamp,nonceStr,packageValue,paySign,order_no){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId" : appid,     //公众号名称，由商户传入
            "timeStamp": timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr" : nonceStr, //随机串
            "package" : packageValue,
            "signType" : "MD5",         //微信签名方式:
            "paySign" : paySign,    //微信签名
        },function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok"){
                searchWXPayState(order_no);
                //微信支付成功!
                toast(1,"支付成功");
                // self.window.history.go(-1);
                self.window.location.href = "/property_system/wx/v1.0.0/cardBagHome";
            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                alert("用户取消支付!");
            }else{
                alert("支付失败!");
            }
        });
}

//查询微信支付是否成功
function searchWXPayState(order_no) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/searchMemberOpenCardWxPayState",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"order_no":order_no},
        success:function(data){
            if (data.status == 0){
                toast(1,"支付成功");
                self.window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


//获取万能购买套餐
function getWnkBuyMeal() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getWnkBuyMeal",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"open_card_type":open_card_type},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                wnkBuyMealList = data.data;
                for (var index = 0;index < wnkBuyMealList.length;index++){
                    var obj = wnkBuyMealList[index];
                    var html = "";
                    if (index == 0){
                        html = "<label class=\"fradio\" style=\"height: 50px;\" id=\"yinka_member_card\">"+
                            "<span class=\"icon\" style=\"background-image: url(/property_system/images/wx/chaojicard.png);\"></span>"+
                            "<span class=\"fname\">"+obj.name+"</span>"+
                        "<input type=\"radio\" name=\"card_type\" id=\"qingchuncard_radio_"+obj.id+"\" checked>"+
                            "<span class=\"status\"></span>"+
                            "</label>";
                    }
                    else{
                        html = "<label class=\"fradio\" style=\"height: 50px;\" id=\"yinka_member_card\">"+
                            "<span class=\"icon\" style=\"background-image: url(/property_system/images/wx/chaojicard.png);\"></span>"+
                            "<span class=\"fname\">"+obj.name+"</span>"+
                            "<input type=\"radio\" name=\"card_type\" id=\"qingchuncard_radio_"+obj.id+"\">"+
                            "<span class=\"status\"></span>"+
                            "</label>";
                    }
                    $("#wnk_buy_meal_div").append(html);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}