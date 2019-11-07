var lastClickTypeIdName = "";
var storage = window.localStorage;

//修改商品
function setCommodity(commodity_id){
	self.window.location.href = "/property_system/wnk_business/joinSetCommodity?commodity_id="+commodity_id;
}

function initData(){
	var screenHeight = window.screen.height;
	var height = screenHeight * 0.9;
	var commodityHeight = screenHeight * 0.75;
	document.getElementById("content_div").style.height = height+"px";
	document.getElementById("commodity_div").style.height = commodityHeight+"px";
	
	document.getElementById("commodities_type_ul").style.height = height+"px";
    getAllCommodityType();
	// typeClick(id_name);
}

//分类点击事件
function typeClick(id_name,type_id){
	if(lastClickTypeIdName != id_name){
		if(lastClickTypeIdName != ""){
			document.getElementById(lastClickTypeIdName).setAttribute("class","type_li");
		}
		document.getElementById(id_name).setAttribute("class","type_li type_sel");
		lastClickTypeIdName = id_name;
        getAllCommodityByTypeId(type_id);
	}
}


//获取所有商品分类
function getAllCommodityType() {
    toast(2,"打开loading");
    $("#commodities_type_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllCommodityType",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                	var firstControlId = "";
                	var firstTypeId = -1;
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        if (index == 0){
                            firstControlId = "type_li_"+obj.id;
                            firstTypeId = obj.id;
						}
                        var html = "<li class=\"type_li\" id=\"type_li_"+obj.id+"\" onclick=\"typeClick('type_li_"+obj.id+"',"+obj.id+")\">"+
                            "<a class=\"type_name\">"+obj.type_name+"</a>"+
                            "</li>";

                        $("#commodities_type_ul").append(html);
                    }
                    typeClick(firstControlId,firstTypeId);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取某个分类下的商品
function getAllCommodityByTypeId(type_id) {
    toast(2,"打开loading");
    $("#content1 li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllCommodityByTypeId",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type_id":type_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li onclick=\"setCommodity("+obj.id+")\">"+
                            "<img src=\""+obj.photo+"\"/>"+
                            "<div class=\"commodity_information_div\">"+
                            "<a class=\"commodity_name_tag\">"+obj.name+"</a>"+
                            "<a class=\"commodity_describe\">"+obj.commodity_describe+"</a>"+
                            "<a class=\"month_sale_tag\">"+obj.state_str+"</a>"+
                            "<a class=\"commodity_price_tag\">￥"+obj.price+"</a>"+
                            "</div>"+
                            "<input type=\"button\" value=\"规格\" class=\"guige_button\" onclick=\"commodity_guige("+obj.id+")\"/>"+
                            "</li>";

                        $("#content1").append(html);
                    }
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//商品规格点击事件
function commodity_guige(commodity_id) {
    window.event.cancelBubble = true;//停止冒泡
    window.event.returnValue = false;//阻止事件的默认行为
    self.window.location.href = "/property_system/wnk_business/joinCommoditySpecification?commodity_id="+commodity_id;
}
