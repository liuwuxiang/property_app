//type=0添加，type=1修改
var type;
function initView(current_type) {
    type = current_type;
}

//保存城市信息
function saveCity() {
    if (type == 0){
        addCity();
    }
    else{
        setCity();
    }
}

//添加城市
function addCity() {
    var province_id = document.getElementById("province_id").value;
    var city_name = document.getElementById("city_name").value;
    if (city_name == undefined || city_name == ""){
        layer.msg('请输入城市名称', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/addCityAction",
            type:"POST",
            dataType : 'json',
            data:{"province_id":province_id,"city_name":city_name},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    parent.layer.alert(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }

}

//修改城市
function setCity() {
    var province_id = document.getElementById("province_id").value;
    var city_name = document.getElementById("city_name").value;
    var city_id = document.getElementById("city_id").value;
    if (city_name == undefined || city_name == ""){
        layer.msg('请输入城市名称', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setCityAction",
            type:"POST",
            dataType : 'json',
            data:{"city_id":city_id,"city_name":city_name,"province_id":province_id},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    parent.layer.alert(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}