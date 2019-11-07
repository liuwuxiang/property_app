
var storage = window.localStorage;
var opertionType = -1;
var commodity_id = -1;

//初始化视图数据
function initData(option_index,current_commodity_id){
    commodity_id = current_commodity_id;
    optionClick(option_index);
}

//选项切换事件(0-已启用,1-未启用)
function optionClick(index){
    opertionType = index;
	if(index == 0){
		document.getElementById("new_order").setAttribute("class","item sel"); 
		document.getElementById("finish_order").setAttribute("class","item");
        getCommodityGuiGe(index);
	}
	else{
		document.getElementById("finish_order").setAttribute("class","item sel"); 
		document.getElementById("new_order").setAttribute("class","item");
        getCommodityGuiGe(index);
	}

}


//获取商品规格
function getCommodityGuiGe(type) {
    toast(2,"打开loading");
    $("#specification_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getCommodityGuiGe",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"commodity_id":commodity_id,"state":type},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                var button_name = "";
                if (type == 0){
                    button_name = "停用";
				}
				else{
                    button_name = "启用";
				}
                for (var index = 0;index < list.length; index++){
                    var obj = list[index];
                    var html = "<li>"+
                    "<a class=\"specification_name\">规格名称:"+obj.specifications_name+"</a>"+
                    "<div class=\"button_div\">"+
                        "<input type=\"button\" value=\"修改\" onclick=\"setAction("+obj.id+",'"+obj.specifications_name+"')\"/>"+
                        "<input type=\"button\" value=\""+button_name+"\" onclick=\"startOrEndGuiGe("+obj.id+")\"/>"+
                        "</div>"+
                        "</li>";
                    $("#specification_ul").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//添加按钮事件
function addAction() {
    var btnArray = ['取消', '确定'];
    mui.prompt('请输入商品规格名称：', '请输入商品规格名称', '添加商品规格', btnArray, function(e) {
        if (e.index == 1) {
            var name = e.value;
            if (name == undefined || name == ""){
                toast(1,"未输入规格名称");
			}
			else{
                addGuiGeNetwork(name);
			}
            //确定e.value
        } else {
            //取消
        }
    })
}

//添加规格网络事件
function addGuiGeNetwork(guige_name) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/addCommodityGuiGe",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"commodity_id":commodity_id,"guige_name":guige_name},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                optionClick(0);
            }
        },
    });
}

//修改商品规格事件
function setAction(guige_id,specifications_name) {
    var btnArray = ['取消', '确定'];
    mui.prompt('请输入商品规格名称：', specifications_name, '修改商品规格', btnArray, function(e) {
        if (e.index == 1) {
            var name = e.value;
            if (name == undefined || name == ""){
                toast(1,"未输入规格名称");
            }
            else{
                setGuiGeName(name,guige_id);
            }
        } else {
            //取消
        }
    })
}

//修改规格信息名称网络事件
function setGuiGeName(guige_name,guige_id) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/updateGuiGeName",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"guige_id":guige_id,"name":guige_name},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                optionClick(opertionType);
            }
        },
    });
}

//停用/启用商品规格
function startOrEndGuiGe(guige_id) {
	if (opertionType == 0){
        var btnArray = ['取消', '确认'];
        mui.confirm('确认停用此规格？', '停用商品规格', btnArray, function(e) {
            if (e.index == 1) {
                startOrEndGuiGeNetwork(guige_id,1);
            } else {
                //取消
            }
        })
	}
	else{
        var btnArray = ['否', '是'];
        mui.confirm('确认启用此规格？', '启用商品规格', btnArray, function(e) {
            if (e.index == 1) {
                startOrEndGuiGeNetwork(guige_id,0);
            } else {
                //取消
            }
        })
	}
}

//启用/停用规格网络事件
function startOrEndGuiGeNetwork(guige_id,type) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/updateGuiGeState",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"guige_id":guige_id,"state":type},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                optionClick(type);
            }
        },
    });
}