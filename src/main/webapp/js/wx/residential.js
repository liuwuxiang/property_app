var storage = window.localStorage;

//初始化数据
function initData(city_id) {
    getResidential(city_id);
}

//获取某个城市下的小区
function getResidential(city_id) {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getResidentialsByCityId",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"city_id":city_id},
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
                        var html = "<li onclick=\"residentialClick("+obj.residential_id+",'"+obj.residential_name+"')\">"+
                            "<a>"+obj.residential_name+"</a>"+
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

//小区选择事件
function residentialClick(residential_id,residential_name) {
    storage["residential_id"] = residential_id;
    storage["residential_name"] = residential_name;
    window.history.go(-1);
}