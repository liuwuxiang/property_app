//type=0添加省份,type=1修改省份
var type;
function initView(current_type) {
    type = current_type;
}

//保存省份
function saveProvince() {
    if (type == 0){
        addProvince();
    }
    else{
        setProvince();
    }
}

//添加省份
function addProvince() {
    var province_name = document.getElementById("province_name").value;
    if (province_name == undefined || province_name == ""){
        layer.msg('请输入省份名称', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/addProvinceAction",
            type:"POST",
            dataType : 'json',
            data:{"province_name":province_name},
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

//修改省份
function setProvince() {
    var record_id = document.getElementById("record_id").value;
    var province_name = document.getElementById("province_name").value;
    if (province_name == undefined || province_name == ""){
        layer.msg('请输入省份名称', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setProvinceAction",
            type:"POST",
            dataType : 'json',
            data:{"record_id":record_id,"province_name":province_name},
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