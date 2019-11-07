var storage = window.localStorage;
//使用类型(0-消费积分,1-通用积分)
var makeType = 0;
//此处值表示多少个消费积分可以兑换一个银币
var xfandyb = 1000;

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
                xfandyb = data.data.xfandyb;
                var tip = "积分";
                if (makeType == 0){
                    tip = "消费积分";
                }
                else{
                    tip = "通用积分";
                }
                document.getElementById("top_tip").innerHTML = xfandyb+"个"+tip+"=1个银币";
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
        document.getElementById("integral_number").innerHTML = number * xfandyb;
    }
    else{
        document.getElementById("integral_number").innerHTML = 0;
    }
}

//兑换
function exchangeAction() {
    var exchange_number = document.getElementById("exchange_number").value;
    if (exchange_number == undefined || exchange_number == ""){
        toast(1,"请选择兑换个数");
    }
    else if (exchange_number <= 0){
        toast(1,"兑换个数必须大于0");
    }
    else{
        $.prompt({
            text: "请填写支付密码进行验证",
            title: "支付密码",
            onOK: function(text) {
                if (text == undefined || text == ""){
                    toast(1,"请输入支付密码");
                }
                else{
                    exchangeNetworkAction(exchange_number,text);
                }
            },
            onCancel: function() {

            },
            input: ''
        });
    }
}

//兑换网络事件
function exchangeNetworkAction(exchange_number,pay_pwd) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/silverCoinExchange",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"user_pay_pwd":pay_pwd,"silver_coin_number":exchange_number,"type":makeType},
        success:function(data){
            if (data.status == 0){
                toast(0,"兑换成功");
                self.window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}