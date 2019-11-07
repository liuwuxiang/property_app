var storage = window.localStorage;
/*
 *	列表项初始化
 * type=0:消费明细，type=1：充值明细，type=2：提现明细
 * */
function listOptionInit(type){
    document.getElementById("general_balance").innerText = storage["general_integral"];
	var consumption_detail_ul = document.getElementById("consumption_detail_ul");
	var recharge_detail_ul = document.getElementById("recharge_detail_ul");
	var withdraw_detail_ul = document.getElementById("withdraw_detail_ul");
	if(type == 0){
		consumption_detail_ul.style.display = "block";
		recharge_detail_ul.style.display = "none";
		withdraw_detail_ul.style.display = "none";
		document.getElementById("xf_item").setAttribute("class","item sel"); 
		document.getElementById("cz_item").setAttribute("class","item"); 
		document.getElementById("tx_item").setAttribute("class","item");
        getGeneralConsumptionDetail();
	}
	else if(type == 1){
		consumption_detail_ul.style.display = "none";
		recharge_detail_ul.style.display = "block";
		withdraw_detail_ul.style.display = "none";
		document.getElementById("xf_item").setAttribute("class","item"); 
		document.getElementById("cz_item").setAttribute("class","item sel"); 
		document.getElementById("tx_item").setAttribute("class","item");
        getGeneralRechargeDetail();
	}
	else{
		consumption_detail_ul.style.display = "none";
		recharge_detail_ul.style.display = "none";
		withdraw_detail_ul.style.display = "block";
		document.getElementById("xf_item").setAttribute("class","item"); 
		document.getElementById("cz_item").setAttribute("class","item"); 
		document.getElementById("tx_item").setAttribute("class","item sel");
        getGeneralWitharthDetail();
	}
}

/*
 *	进入积分充值界面
 * */
function joinIntegralRecharge(){
    self.window.location.href = "/property_system/wx/v1.0.0/integralRecharge?type=1";
}

/*
 *	进入积分兑换页面
 * */
function joinIntegralExchange(){
    self.window.location.href = "/property_system/wx/v1.0.0/integralExchange?type=1";
}

/*
 *	进入通用积分提现界面
 * */
function joinGeneralIntegralWithdraw(){
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wx/v1.0.0/getUserBankCard",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭Loading");
                var state = data.data.bank_state;
                if (state == 0){
                    alert(data.msg);
                    self.window.location.href = "/property_system/wx/v1.0.0/joinBankCardSetting";
                }
                else{
                    self.window.location.href = "/property_system/wx/v1.0.0/generalIntegralWithdraw";
                }
            }
            else{
                toast(3,"关闭Loading");
            }
        },
    });


}

/*
 *	进入积分帮助说明页面
 * */
function joinIntegralHelp(){
    self.window.location.href = "/property_system/wx/v1.0.0/integralMakeHelp?type=1";
}

//获取通用积分消费明细
function getGeneralConsumptionDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#consumption_detail_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserGenerallntegralExpenditureByUser",
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
                            "<span class=\"time\">"+obj.expenditure_date+"</span>"+
                        "</div>"+
                        "<div class=\"right\">"+
                            "<span class=\"num down\">-"+obj.expenditure_amount+"</span>"+
                            "</div>"+
                            "</li>";
                        $("#consumption_detail_ul").append(html);
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

//获取通用积分充值明细
function getGeneralRechargeDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#recharge_detail_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserGenerallntegralRechargeIncome",
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
                            "<span class=\"time\">余额："+obj.balance+"</span>"+
                        "</div>"+
                        "<div class=\"right\">"+
                            "<span class=\"time\">"+obj.income_date+"</span>"+
                        "<span class=\"num down\">+"+obj.income_amount+"</span>"+
                            "</div>"+
                            "</li>";
                        $("#recharge_detail_ul").append(html);
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

//获取通用积分提现明细
function getGeneralWitharthDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#withdraw_detail_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserGenerallntegralWithdrawByUser",
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
                            "<span class=\"time\">余额："+obj.balance+"</span>"+
                        "</div>"+
                        "<div class=\"right\">"+
                            "<span class=\"time\">"+obj.expenditure_date+"</span>"+
                        "<span class=\"num down\">-"+obj.expenditure_amount+"</span>"+
                            "</div>"+
                            "</li>";
                        $("#withdraw_detail_ul").append(html);
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
        document.getElementById("request_tip").innerText = content;
        publicnull_tip.style.display = "block";
    }
}

//获取用户基础信息
function getUserBaseInformation() {
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
                setUserInformation(data);
                toast(3,"关闭loading");
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
* 设置用户信息
* */
function setUserInformation(data) {
    var user_id = data.data.user_id;
    // var login_session = data.data.login_session;
    var level_name = data.data.level_name;
    var level_icon = data.data.level_icon;
    var consumption_integral = data.data.consumption_integral;
    var nick_name = data.data.nick_name;
    var sex = data.data.sex;
    var header = data.data.header;
    var recommend_mobile = data.data.recommend_mobile;
    var team_members_number = data.data.team_members_number;
    var general_integral = data.data.general_integral;
    var mobile = data.data.mobile;
    var email = data.data.email;
    var is_microfinance = data.data.is_microfinance;

    storage.setItem("user_id",user_id);
    storage.setItem("level_name",level_name);
    storage.setItem("level_icon",level_icon);
    storage.setItem("consumption_integral",consumption_integral);
    storage.setItem("nick_name",nick_name);
    storage.setItem("sex",sex);
    storage.setItem("header",header);
    storage.setItem("recommend_mobile",recommend_mobile);
    storage.setItem("team_members_number",team_members_number);
    storage.setItem("general_integral",general_integral);
    storage.setItem("mobile",mobile);
    storage.setItem("email",email);
    storage.setItem("is_microfinance",is_microfinance);
    document.getElementById("general_balance").innerText = storage["general_integral"];
}