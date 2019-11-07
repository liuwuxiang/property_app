var storage = window.localStorage;
/*
 *楼栋选择
 */
function chooseAction(build_id,building_number){
 	self.window.location.href = "/property_system/wx/v1.0.0/joinResidentialUnitChoose?build_id="+build_id+"&building_number="+building_number;
}

//初始化数据
function initData(residential_id) {
    getResidentialBuild(residential_id);
}

//获取某个城市下的小区
function getResidentialBuild(residential_id) {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getResidentialsAllBuilding",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"residential_id":residential_id},
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
                        var html = "<li onclick=\"chooseAction("+obj.build_id+",'"+obj.building_number+"')\">"+
                            "<a>"+obj.building_number+"</a>"+
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