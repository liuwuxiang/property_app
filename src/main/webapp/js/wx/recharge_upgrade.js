var storage = window.localStorage;
//使用类型(0-消费积分,1-通用积分)
var makeType = 0;

/*
* 数据初始化
* */
function initData() {

}

//支付方式选择
function pay_way_choose(type) {
    makeType = type;
}

//充值事件
function rechargeAction() {
    toast(1,"充值暂未开放");
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