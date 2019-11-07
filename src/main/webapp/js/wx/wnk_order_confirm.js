var storage = window.localStorage;
var commodity_id = "";
var guige_id = -1;
var pay_way_choose = -1;
var commodity_price = 0;
//初始化数据
function initData(current_commodity_id,current_guige_id,current_pay_way) {
    commodity_id = current_commodity_id;
    guige_id = current_guige_id;
    pay_way_choose = current_pay_way;
    if (current_pay_way == 0){
        var html1 = "<label class=\"fradio\" style=\"height: 50px;\">"+
            "<span class=\"icon\" style=\"background-image: url(/property_system/images/wx/general_integral.png);\"></span>"+
            "<span class=\"fname\">通用积分</span>"+
            "<input type=\"radio\" name=\"pay\" id=\"general_integral_radio\">"+
        "<span class=\"status\"></span>"+
            "</label>";
        var html2 = "<label class=\"fradio\" style=\"height: 50px;\">"+
            "<span class=\"icon\" style=\"background-image: url(/property_system/images/wx/consumption_integral.png);\"></span>"+
            "<span class=\"fname\">消费积分</span>"+
            "<input type=\"radio\" name=\"pay\" id=\"consumption_integral_radio\">"+
            "<span class=\"status\"></span>"+
            "</label>";
        var html3 = "<label class=\"fradio\" style=\"height: 50px;\">"+
            "<span class=\"icon wxpay\"></span>"+
            "<span class=\"fname\">微信支付</span>"+
            "<input type=\"radio\" name=\"pay\" id=\"wx_pay_radio\" checked>"+
        "<span class=\"status\"></span>"+
            "</label>";

        $("#pay_war_div").append(html1);
        $("#pay_war_div").append(html2);
        $("#pay_war_div").append(html3);
    }
    getOrderInformation();
}

//获取订单信息
function getOrderInformation() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getCommodityDetailAndGuige",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id,"guige_id":guige_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("commodity_name").innerText = data.data.name+"("+data.data.guige_name+")";
                document.getElementById("commodity_price").innerText = "￥"+data.data.price;
                commodity_price = data.data.price;
                document.getElementById("order_price").innerText = data.data.price;
                if (data.data.is_make_wnk == 1 && pay_way_choose == 1){
                    var html = "<label class=\"fradio\" style=\"height: 50px;\">"+
                        "<span class=\"icon wnkpay\"></span>"+
                        "<span class=\"fname\">万能卡支付</span>"+
                        "<input type=\"radio\" name=\"pay\" id=\"wx_wnk_pay_radio\" checked>"+
                        "<span class=\"status\"></span>"+
                        "</label>";
                    $("#pay_war_div").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//支付
function orderPay() {
    // var pay_way = -1;
    // var general_integral_radio = document.getElementById("general_integral_radio");
    // var consumption_integral_radio = document.getElementById("consumption_integral_radio");
    // var wx_pay_radio = document.getElementById("wx_pay_radio");
    // if (general_integral_radio.checked){
    //     pay_way = 1;
    // }
    // else if (consumption_integral_radio.checked){
    //     pay_way = 0;
    // }
    // else if (wx_pay_radio.checked){
    //     pay_way = 2;
    // }if (pay_way == -1){
    //     toast(1,"请选择支付方式");
    // }
    // else{
    //     if (pay_way == 0 || pay_way == 1){
    //         $.prompt({
    //             text: "请填写支付密码进行验证",
    //             title: "支付密码",
    //             onOK: function(text) {
    //                 if (text == undefined || text == ""){
    //                     toast(1,"请输入支付密码");
    //                 }
    //                 else{
    //                     handelMemberCardNetwork(member_card_level,pay_way,text,card_type_id);
    //                 }
    //             },
    //             onCancel: function() {
    //
    //             },
    //             input: ''
    //         });
    //     }
    //     else{
    //
    //     }
    // }
    orderWxPay();
}


//订单支付
function orderWxPay() {
    //0-微信支付,1-万能卡支付,2-通用积分支付,3-消费积分支付
    var pay_way = -1;
    if (pay_way_choose == 0){
        var wx_pay_radio = document.getElementById("wx_pay_radio");
        var general_integral_radio = document.getElementById("general_integral_radio");
        var consumption_integral_radio = document.getElementById("consumption_integral_radio");
        if (wx_pay_radio.checked){
            pay_way = 0;
        }
        else if (general_integral_radio.checked){
            pay_way = 2;
        }
        else if (consumption_integral_radio.checked){
            pay_way = 3;
        }
    }
    else{
        var wx_wnk_pay_radio = document.getElementById("wx_wnk_pay_radio");
        if (wx_wnk_pay_radio.checked){
            pay_way = 1;
        }
    }

    if (pay_way == -1){
        toast(1,"请选择支付方式");
    }
    else if (guige_id == -1){
        toast(1,"商品规格不存在");
    }
    else{
        if (pay_way == 1){
            wnkOrderPay();
        }
        else{
            if (pay_way == 2 || pay_way == 3){
                $.prompt({
                    text: "请填写支付密码进行验证",
                    title: "支付密码",
                    onOK: function(text) {
                        if (text == undefined || text == ""){
                            toast(1,"请输入支付密码");
                        }
                        else{
                            orderIntegralPay(pay_way,text);
                        }
                    },
                    onCancel: function() {

                    },
                    input: ''
                });
            }
            else{
                orderWXPayAction();
            }

        }
    }
}

//订单通用积分/消费积分支付
function orderIntegralPay(pay_way,pay_pwd) {
    var commodity_number = parseInt(document.getElementById("commodity_number_tag").innerText);
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/wnkCommodityIntegralPay",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id,"guige_id":guige_id,"pay_way":pay_way,"pay_pwd":pay_pwd,"commodity_number":commodity_number},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                var order_id = data.data.order_id;
                self.window.location.href = "/property_system/wx/v1.0.0/joinWnkMyOrderDetail?order_id="+order_id+"&type=0";
            }
        },
    });
}

//订单微信支付
function orderWXPayAction() {
    var commodity_number = parseInt(document.getElementById("commodity_number_tag").innerText);
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/wnkCommodityWxPay",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id,"guige_id":guige_id,"commodity_number":commodity_number},
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
                    pay(wx_config.appid,wx_config.timeStamp,wx_config.nonceStr,wx_config.packageValue,wx_config.paySign,wx_config.order_no,wx_config.order_id);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//万能卡订单支付
function wnkOrderPay() {
    var commodity_number = parseInt(document.getElementById("commodity_number_tag").innerText);
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/wnkCommodityQYLineOrder",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id,"guige_id":guige_id,"commodity_number":commodity_number},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                var order_id = data.data.order_id;
                self.window.location.href = "/property_system/wx/v1.0.0/joinWnkMyOrderDetail?order_id="+order_id+"&type=1";
            }
        },
    });
}

function pay(appid,timeStamp,nonceStr,packageValue,paySign,order_no,order_id){
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady(appid,timeStamp,nonceStr,packageValue,paySign,order_no,order_id);
    }
}
function onBridgeReady(appid,timeStamp,nonceStr,packageValue,paySign,order_no,order_id){
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
                //微信支付成功!
                alert("支付成功");
                self.window.history.go(-1);
                self.window.location.href = "/property_system/wx/v1.0.0/joinWnkMyOrderDetail?order_id="+order_id+"&type=0";
            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                alert("用户取消支付!");
            }else{
                alert("支付失败!");
            }
        });
}

//商品数量管理
function commodity_number(type) {
    var commodity_number_tag = parseInt(document.getElementById("commodity_number_tag").innerText);
    //减商品数量
    if (type == 0){
        if (commodity_number_tag > 1){
            commodity_number_tag = commodity_number_tag - 1;
        }
    }
    //加商品数量
    else{
        commodity_number_tag = commodity_number_tag + 1;
    }
    document.getElementById("commodity_number_tag").innerText = commodity_number_tag;
    var order_price = commodity_price * commodity_number_tag;
    document.getElementById("order_price").innerText = order_price;
}
