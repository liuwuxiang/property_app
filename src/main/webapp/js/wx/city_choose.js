var storage = window.localStorage;
var province_id = -1;
var city_id = -1;
var province_name = "";
var city_name = "";

//初始化数据
function initData(province_id_choose,province_name_choose) {
    province_id = province_id_choose;
    province_name = province_name_choose;
    getCity(province_id);
}

//获取某个省份下的城市
function getCity(province_id) {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getProvinceAllCity",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"province_id":province_id},
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
                        var html = "<li onclick=\"cityClick("+obj.city_id+",'"+obj.name+"')\">"+
                            "<a>"+obj.name+"</a>"+
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

//城市选择事件
function cityClick(city_id_choose,city_name_choose) {
    city_id = city_id_choose;
    city_name = city_name_choose;
    storage["city_name"] = city_name;
    storage["province_name"] = province_name;
    storage["city_id"] = city_id;
    storage["province_id"] = province_id;
    window.history.go(-2);
}