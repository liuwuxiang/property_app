var storage = window.localStorage;
//使用类型(0-消费积分,1-通用积分)
var makeType = 0;
//此处的值表示多少个消费积分可以兑换一元人民币
var xfandrmb = 1000;

/*
* 数据初始化
* */
function initData(type) {
    makeType = type;
    getInitData();
}

//获取初始化数据
function getInitData() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getSubscriptionRatioInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                xfandrmb = data.data.xfandrmb;
                var tip = "积分";
                if (makeType == 0){
                    tip = "消费积分";
                }
                else{
                    tip = "通用积分";
                }
                // document.getElementById("top_tip").innerHTML = xfandrmb+"元=1个"+tip;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//金额输入监听
function inputChange(number) {
    if (number > 0){
        document.getElementById("integral_number").innerHTML = number / xfandrmb;
    }
    else{
        document.getElementById("integral_number").innerHTML = 0;
    }
}

//充值事件
function rechargeAction() {
    var recharge_amount = document.getElementById("recharge_amount").value;
    if (recharge_amount == undefined || recharge_amount == ""){
        toast(1,"请输入充值金额");
    }
    else if (recharge_amount <= 0){
        toast(1,"充值金额需大于0");
    }
    else if (recharge_amount % 1 != 0){
        toast(1,"充值金额需为整数");
    }
    else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/wx/v1.0.0/wxUnlineOrderPay",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"user_id":storage["user_id"],"amount":recharge_amount},
            success:function(data){
                if (data.status == 0){
                    toast(3,"关闭loading");
                    var wx_config = data.data.wx_config;
                    pay(wx_config.appid,wx_config.timeStamp,wx_config.nonceStr,wx_config.packageValue,wx_config.paySign,wx_config.order_no);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
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
                //微信支付成功!
                toast(1,"支付成功");
            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                alert("用户取消支付!");
            }else{
                alert("支付失败!");
            }
        });
}