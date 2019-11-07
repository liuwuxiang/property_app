var storage = window.localStorage;
var build_id = -1;
var building_number = "";

/*
 *单元选择
 */
function chooseAction(unit_id,unit_number){
 	self.window.location.href = "/property_system/wx/v1.0.0/joinResidentialHouseNumberChoose?build_id="+build_id+"&building_number="+building_number+"&unit_id="+unit_id+"&unit_number="+unit_number;
}

//数据初始化
function initData(choose_build_id,choose_building_number) {
    build_id = choose_build_id;
    building_number = choose_building_number;
    getAllUnitByBuildingId();
}

//获取单元数
function getAllUnitByBuildingId() {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getAllUnitByBuildingId",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"building_id":build_id},
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
                        var html = "<li onclick=\"chooseAction("+obj.unit_id+",'"+obj.unit_number+"')\">"+
                            "<a>"+obj.unit_number+"</a>"+
                            "<img src=\"/property_system/images/wx/icon_more.svg\"/>"+
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