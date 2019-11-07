var storage = window.localStorage;
var year_month = "";
var order_state = -1;
//(make_type=0-按订单统计详情，make_type=1-按销售统计详情)
var current_make_type = -1;

//初始化数据
function initData(current_year_month,current_state,make_type) {
    year_month = current_year_month;
    order_state = current_state;
    current_make_type = make_type;
    document.getElementById("title_tag").innerText = year_month+"统计详情";
    getOrderData(order_state);
}

//获取订单数据
function getOrderData(type) {
    toast(2,"打开loading");
    $("#order_in_progress_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllOrderStaticDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type":type,"month_year":year_month,"make_type":current_make_type},
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
                        var html = "<li class=\"order_li\">"+
                            "<div class=\"li_top_div\">"+
                            "<a class=\"li_top_day_tag\">"+obj.day+"</a>"+
                            "<a class=\"li_order_no_tag\">NO."+obj.order_no+"</a>"+
                            "</div>"+
                            "<div class=\"li_commodites_div\">"+
                            "<a class=\"commodities_number_tag\">商品("+obj.commodity_count+")</a>"+
                            "<ul class=\"commodities_ul\">";
                        for (var index2 = 0;index2 < commodity_list.length;index2++){
                            var commodity = commodity_list[index2];
                            html = html + "<li>"+
                                "<a class=\"commodity_name_tag\">"+commodity.commodity_name+"</a>"+
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
                    document.getElementById("top_people_static_tag").innerText = "消费人数："+data.data.user_number+"人";
                    document.getElementById("top_number_static_tag").innerText = "消费次数："+list.length+"次";
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}