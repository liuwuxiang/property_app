var storage = window.localStorage;

var order_id = -1;
var type = -1;
function initData(current_order_id,current_type) {
    order_id = current_order_id;
    type = current_type;
    if (type == 0){
        getOrderDetail();
    }
    else{
        getWnkOrderDetail();
    }
}

//获取订单详情
function getOrderDetail() {
    toast(2,"打开loading");
    $("#commodities_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/wnkBuyOrderDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"order_id":order_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data.commodity_list;
                document.getElementById("business_name").innerText = data.data.business_name;
                document.getElementById("commodity_number_tag").innerText = "商品("+list.length+")";
                document.getElementById("line_time_tag").innerText = "下单时间："+data.data.submit_time_str;
                document.getElementById("order_no_tag").innerText = "订单号：NO."+data.data.order_no;
                document.getElementById("count_amount_tag").innerText = "总价：￥"+data.data.amount;
                for (var index = 0;index < list.length; index++){
                    var obj = list[index];
                    var specifications_name = obj.specifications_name;
                    if (specifications_name == undefined){
                        specifications_name = "";
                    }
                    var html = "<li>"+
                        "<a class=\"commodity_name_tag\">"+obj.commodity_name+"("+specifications_name+")</a>"+
                        "<a class=\"commodity_number_tag\">x1</a>"+
                        "<a class=\"price_tag\">"+obj.count_amount+"</a>"+
                        "</li>";
                    $("#commodities_ul").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取万能卡权益订单详情
function getWnkOrderDetail() {
    toast(2,"打开loading");
    $("#commodities_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getWnkOrderDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"order_id":order_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("business_name").innerText = data.data.store_name;
                document.getElementById("commodity_number_tag").innerText = "商品("+data.data.make_number+")";
                document.getElementById("line_time_tag").innerText = "下单时间："+data.data.line_date;
                document.getElementById("order_no_tag").innerText = "订单号：NO."+data.data.order_no;
                document.getElementById("count_amount_tag").innerText = "总价：￥0";
                business_id = data.data.business_id;
                var guige_name = data.data.guige_name;
                if (guige_name == undefined){
                    guige_name = "";
                }
                var html = "<li>"+
                    "<a class=\"commodity_name_tag\">"+data.data.commodity_name+"("+guige_name+")</a>"+
                    "<a class=\"commodity_number_tag\">x"+data.data.make_number+"</a>"+
                    "<a class=\"price_tag\">0</a>"+
                    "</li>";
                $("#commodities_ul").append(html);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}
