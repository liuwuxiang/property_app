var storage = window.localStorage;
var build_id = -1;
var building_number = "";
var unit_id = -1;
var unit_number = "";

function initData(choose_build_id,choose_building_number,choose_unit_id,choose_unit_number) {
    build_id = choose_build_id;
    building_number = choose_building_number;
    unit_id = choose_unit_id;
    unit_number = choose_unit_number;
    getAllHouseByUnitId();
}

//获取房间号
function getAllHouseByUnitId() {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getAllHouseByUnitId",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"unit_id":unit_id},
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
                        var html = "<li onclick=\"houseClick("+obj.number_id+",'"+obj.house_number+"')\">"+
                            "<a>"+obj.house_number+"</a>"+
                            "</li>";
                        $("#content_ul").append(html);
                    }
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//房间号选择事件
function houseClick(number_id,house_number) {
    storage["residential_building_id"] = build_id;
    storage["building_number"] = building_number;
    storage["residential_unit_id"] = unit_id;
    storage["unit_number"] = unit_number;
    storage["house_number_id"] = number_id;
    storage["house_number"] = house_number;
    window.history.go(-3);
}