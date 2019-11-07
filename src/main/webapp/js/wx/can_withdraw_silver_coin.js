var storage = window.localStorage;

/*
 *	一键提现
 * */
function oneKeyLifting(){
    $.prompt({
        text: "请填写支付密码进行验证",
        title: "支付密码",
        onOK: function(text) {
            if (text == undefined || text == ""){
                toast(1,"请输入支付密码");
            }
            else{
                silverCoinWithdrawNetwork(text);
            }
        },
        onCancel: function() {

        },
        input: ''
    });
}

//银币提现网络事件
function silverCoinWithdrawNetwork(pay_pwd) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/userSilverCoinWithdraw",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"pay_pwd":pay_pwd},
        success:function(data){
            if (data.status == 0){
                toast(1,data.msg);
                initData();
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//初始化数据
function initData() {
    getBalance();
    getSliverCoinDetail();
}

//获取余额
function getBalance() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserSliverCanWithdrawBalance",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,data.msg);
                document.getElementById("sovile_coin_balance").innerText = data.data.can_withdraw_balance;
                document.getElementById("account_number").innerText = data.data.account_number;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取银币明细
function getSliverCoinDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#list li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getCanWithdrawSilverCoinDetail",
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
                    publicnull_tip("暂无数据",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li class=\"item\">"+
                            "<div class=\"left\">"+
                            "<span class=\"name\">"+obj.name+"</span>"+
                            "<span class=\"time\">余额 "+obj.balance+"银币</span>"+
                            "</div>"+
                            "<div class=\"right\">";
                        if (obj.income_expenditure_type == 0){
                            html = html + "<span class=\"num down\">-"+obj.transaction_amount+"</span>";
                        }
                        else{
                            html = html + "<span class=\"num up\">+"+obj.transaction_amount+"</span>";
                        }
                        html = html + "<p class=\"time\">"+obj.record_date+"</p>"+
                            "</div>"+
                            "</li>";

                        $("#list").append(html);
                    }
                }
            }
            else{
                toast(3,"关闭Loading");
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
        publicnull_tip.style.display = "block";
    }
}