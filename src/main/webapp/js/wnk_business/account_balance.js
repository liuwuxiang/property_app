var storage = window.localStorage;

//选项卡事件(0-收入,1-支出)
function optionClick(index){
    if(index == 0){
        document.getElementById("income_a").setAttribute("class","item sel");
        document.getElementById("expenditure_a").setAttribute("class","item");
        getDetailData(index);
    }
    else{
        document.getElementById("expenditure_a").setAttribute("class","item sel");
        document.getElementById("income_a").setAttribute("class","item");
        getPayData(index);
    }
}


//进入提现页面
function joinWithdraw(){
	self.window.location.href = "/property_system/wnk_business/joinWithdraw";
}

//初始化数据
function initData() {
    optionClick(0);
}

//获取详情数据
function getDetailData(type) {
    toast(2,"打开loading");
    var className = "";
    var fuhao = "-";
    if (type == 0){
        className = "income_money";
        fuhao = "+";
    }
    else{
        className = "income_money expenditure_amount";
        fuhao = "-";
    }
    $("#money_detail_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getBalanceDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type":type},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("account_balance_value").innerText =  data.data.balance;
                var list = data.data.detail;
                if (list.length <= 0){
                    toast(1,"暂无记录");
				}
				else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li>"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"detail_name\">"+obj.name+"</a>"+
                            "<a class=\""+className+"\">"+fuhao+obj.transaction_amount+"</a>"+
                            "</div>"+
                            "<div class=\"li_bottom_div\">"+
                            "<a class=\"detail_time\">"+obj.state_str+" "+obj.join_time_str+"</a>"+
                            "<a class=\"detail_balance\">余额："+obj.after_balance+"</a>"+
                            "</div>"+
                            "</li>";
                        $("#money_detail_ul").append(html);
                    }
				}

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取支出数据
function getPayData(type) {
    toast(2,"打开loading");
    var className = "";
    var fuhao = "-";
    if (type == 0){
        className = "income_money";
        fuhao = "+";
    }
    else{
        className = "income_money expenditure_amount";
        fuhao = "-";
    }
    $("#money_detail_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getBusinessWithdrawRecord",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("account_balance_value").innerText =  data.data.balance;
                var list = data.data.detail;
                if (list.length <= 0){
                    toast(1,"暂无记录");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li>"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"detail_name\">提现</a>"+
                            "<a class=\""+className+"\">"+fuhao+obj.rmb_count_amount+"</a>"+
                            "</div>"+
                            "<div class=\"li_bottom_div\">"+
                            "<a class=\"detail_time\">"+obj.apply_date_str+"</a>"+
                            "<a class=\"detail_balance\">状态："+obj.state_str+"</a>"+
                            "</div>"+
                            "</li>";
                        $("#money_detail_ul").append(html);
                    }
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}