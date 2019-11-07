var storage = window.localStorage;
//选项切换事件(0-待支付,1-待使用,2-已完成,,3-权益订单)
function optionClick(index){
	if(index == 0){
		document.getElementById("no_pay_order").setAttribute("class","item sel"); 
		document.getElementById("no_make_order").setAttribute("class","item");
		document.getElementById("finish_order").setAttribute("class","item");
		document.getElementById("quanyi_order").setAttribute("class","item");
	}
	else if(index == 1){
		document.getElementById("no_pay_order").setAttribute("class","item"); 
		document.getElementById("no_make_order").setAttribute("class","item sel");
		document.getElementById("finish_order").setAttribute("class","item");
		document.getElementById("quanyi_order").setAttribute("class","item");
	}
	else if(index == 2){
		document.getElementById("no_pay_order").setAttribute("class","item"); 
		document.getElementById("no_make_order").setAttribute("class","item");
		document.getElementById("finish_order").setAttribute("class","item sel");
		document.getElementById("quanyi_order").setAttribute("class","item");
	}
	else{
		document.getElementById("no_pay_order").setAttribute("class","item"); 
		document.getElementById("no_make_order").setAttribute("class","item");
		document.getElementById("finish_order").setAttribute("class","item");
		document.getElementById("quanyi_order").setAttribute("class","item sel");
	}
	if(index != 3){
        getXFOrderData(index);
	}
	else{
        getQuanYiOrderData();
	}
}


//消费消费订单数据
function getXFOrderData(state) {
    toast(2,"打开loading");
    $("#order_in_progress_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/wnkWnkMyOrder",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"state":state},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data.list;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var commodity_list = obj.commodity_list;
                        var html = "<li class=\"order_li\" onclick=\"joinOrderDetail("+obj.id+",0)\">"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"li_top_day_tag\">"+obj.day+"</a>"+
                            "<a class=\"li_order_no_tag\">"+obj.business_name+"</a>"+
                            "</div>"+
                            "<div class=\"li_commodites_div\">"+
                            "<a class=\"commodities_number_tag\">商品("+commodity_list.length+")</a>"+
                            "<ul class=\"commodities_ul\">";
                        for (var index2 = 0;index2 < commodity_list.length;index2++){
                            var commodity = commodity_list[index2];
                            var specifications_name = commodity.specifications_name;
                            if (specifications_name == undefined){
                                specifications_name = "";
                            }
                            html = html + "<li>"+
                                "<a class=\"commodity_name_tag\">"+commodity.commodity_name+"("+specifications_name+")</a>"+
                                "<a class=\"commodity_number_tag\">x"+commodity.buy_number+"</a>"+
                                "<a class=\"price_tag\">"+commodity.count_amount+"</a>"+
                                "</li>";
                        }
                        html = html + "</ul>"+
                            "</div>"+
                            "<div class=\"li_bottom_div\">"+
                            "<a class=\"line_order_time\">"+obj.submit_time_str+"</a>"+
                            "<a class=\"order_price_tag\">￥"+obj.amount+"</a>"+
                            "</div>";
                        $("#order_in_progress_ul").append(html);
                    }
                    document.getElementById("top_people_static_tag").innerText = "消费次数："+data.data.xf_cs+"次";
                    document.getElementById("top_number_static_tag").innerText = "金额："+data.data.count_amount+"元";
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//权益订单数据
function getQuanYiOrderData() {
    toast(2,"打开loading");
    $("#order_in_progress_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/wnkQuanyiOrder",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    var count = 0;
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var specifications_name = obj.specifications_name;
                        if (specifications_name == undefined){
                            specifications_name = "";
                        }
                        var html = "<li class=\"order_li\" onclick=\"joinOrderDetail("+obj.id+",1)\">"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"li_top_day_tag\">"+obj.day+"</a>"+
                            "<a class=\"li_order_no_tag\">NO."+obj.order_no+"</a>"+
                        "</div>"+
                        "<div class=\"li_commodites_div\">"+
                            "<a class=\"commodities_number_tag\">基础信息</a>"+
                            "<div class=\"user_div\">"+
                            "<a>商家分类："+obj.business_type_name+"</a>"+
                        "<a>商家名称："+obj.business_name+"</a>"+
                            "<a>商品名称："+obj.commodity_name+"</a>"+
                            "<a>使用次数："+obj.make_number+"次</a>"+
                            "<a>商品规格："+specifications_name+"</a>"+
                        "</div>"+
                        "</div>"+
                        "<div class=\"li_bottom_div\">"+
                            "<a class=\"line_order_time\">"+obj.line_order_date_str+"</a>"+
                        "<a class=\"order_price_tag\">"+obj.state_str+"</a>"+
                            "</div>"+
                            "</li>";
                        count = count + obj.make_number;
                        $("#order_in_progress_ul").append(html);
                    }
                    document.getElementById("top_people_static_tag").innerText = "消费次数："+count+"次";
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//进入订单详情页(type=0-正常万能卡订单(付费购买)，type=1-万能卡会员权益使用订单)
function joinOrderDetail(order_id,type) {
	self.window.location.href = "/property_system/wx/v1.0.0/joinWnkMyOrderDetail?order_id="+order_id+"&type="+type;
}

//进入商家主页
function joinWnkBusinessHome(business_id) {
    self.window.location.href = "/property_system/wx/v1.0.0/joinBusinessHome?business_id="+business_id;
}