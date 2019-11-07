var storage = window.localStorage;
//选项卡事件(0-订单统计被点击,1-销售统计被点击)
function optionClick(index){
	var order_div = document.getElementById("order_statistics_div");
	var seal_div = document.getElementById("seal_statistics_div");
	if(index == 0){
		order_div.style.display = "block";
		seal_div.style.display = "none";
		document.getElementById("order_statistics").setAttribute("class","item sel"); 
		document.getElementById("seal_statistics").setAttribute("class","item");
        getOrderStatisticsData();
	}
	else{
		seal_div.style.display = "block";
		order_div.style.display = "none";
		document.getElementById("seal_statistics").setAttribute("class","item sel"); 
		document.getElementById("order_statistics").setAttribute("class","item");
        getSaleStatisticsData();
	}
}

//获取订单统计数据
function getOrderStatisticsData() {
    toast(2,"打开loading");
    $("#new_order_statistics_ul li").remove();
    $("#finish_order_statistics_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/countAllOrderByMonth",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var finish_order_list = data.data.finish_order;
                var new_order_list = data.data.new_order;
                for (var index = 0;index < new_order_list.length; index++){
                	var obj = new_order_list[index];
                    var html = "<li onclick=\"orderStatisticsDetail('"+obj.year_month_str+"',0,0)\">"+
                    "<a class=\"month_name_tag\">"+obj.year_month_str+"</a>"+
                    "<div class=\"progress_div\">"+
                        "<div class=\"progress\">"+
                        "<div class=\"progress-bar progress-bar-info progress-bar-striped active\" style=\"width: "+obj.bili+"%;\">"+
                        "<div class=\"progress-value\">"+obj.count+"单</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</li>";
                    $("#new_order_statistics_ul").append(html);
                }
                for (var index = 0;index < finish_order_list.length; index++){
                    var obj = finish_order_list[index];
                    var html = "<li onclick=\"orderStatisticsDetail('"+obj.year_month_str+"',1,0)\">"+
                        "<a class=\"month_name_tag\">"+obj.year_month_str+"</a>"+
                        "<div class=\"progress_div\">"+
                        "<div class=\"progress\">"+
                        "<div class=\"progress-bar progress-bar-info progress-bar-striped active\" style=\"width: "+obj.bili+"%;\">"+
                        "<div class=\"progress-value\">"+obj.count+"单</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</li>";
                    $("#finish_order_statistics_ul").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


//获取销售统计数据
function getSaleStatisticsData() {
    toast(2,"打开loading");
    $("#order_count_statistics_ul li").remove();
    $("#amount_count_statistics_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/countAllIncomeByMonth",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var order_list = data.data.order;
                var count_amount_list = data.data.count_amount;
                for (var index = 0;index < order_list.length; index++){
                    var obj = order_list[index];
                    var html = "<li onclick=\"orderStatisticsDetail('"+obj.year_month_str+"',0,1)\">"+
                        "<a class=\"month_name_tag\">"+obj.year_month_str+"</a>"+
                        "<div class=\"progress_div\">"+
                        "<div class=\"progress\">"+
                        "<div class=\"progress-bar progress-bar-info progress-bar-striped active\" style=\"width: "+obj.bili+"%;\">"+
                        "<div class=\"progress-value\">"+obj.count+"单</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</li>";
                    $("#order_count_statistics_ul").append(html);
                }
                for (var index = 0;index < count_amount_list.length; index++){
                    var obj = count_amount_list[index];
                    var html = "<li onclick=\"orderStatisticsDetail('"+obj.year_month_str+"',1,1)\">"+
                        "<a class=\"month_name_tag\">"+obj.year_month_str+"</a>"+
                        "<div class=\"progress_div\">"+
                        "<div class=\"progress\">"+
                        "<div class=\"progress-bar progress-bar-info progress-bar-striped active\" style=\"width: "+obj.bili+"%;\">"+
                        "<div class=\"progress-value\">"+obj.count_amount+"元</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</li>";
                    $("#amount_count_statistics_ul").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//订单统计详情
function orderStatisticsDetail(year_month,state,make_type) {
    self.window.location.href = "/property_system/wnk_business/joinOrdersStatisticsDetail?year_month="+year_month+"&state="+state+"&make_type="+make_type;
}