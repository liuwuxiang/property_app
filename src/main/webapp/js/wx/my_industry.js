var storage = window.localStorage;
/*
 *	进入银币明细
 * */
function joinSilverCoinDetail(){
	self.window.location.href = "/property_system/wx/v1.0.0/mySilverCoinDetail";
}

/*
 *	进入可提现银币记录页
 * */
function joinCanWithdrawSilverCoinDetail(){
    self.window.location.href = "/property_system/wx/v1.0.0/canWithdrawSilverCoinDetail";
}

//查看我的证书
function lookMyCertificate() {
    self.window.location.href = "/property_system/wx/v1.0.0/searchMyCertificateList";
}

//初始化数据
function initData() {
    getBalance();
    getMyProfitList();
}

//获取余额
function getBalance() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserBalance",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,data.msg);
				document.getElementById("sovile_coin_balance").innerText = data.data.silver_coin_balance;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取我的收益记录
function getMyProfitList() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#list li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserIndustryIncome",
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
                            "<span class=\"timename\">"+obj.income_date+"</span>"+
                        "</div>"+
                        "<div class=\"right\">"+
                            "<span class=\"num up\">+"+obj.income_amount+"</span>"+
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