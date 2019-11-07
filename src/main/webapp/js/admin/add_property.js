//type=0添加,type=2修改
var type;
function initView(current_type) {
    type = current_type;
    if (type == 0){
        getAllProvince();
    }
    else{
        document.getElementById("account_li").style.display = "none";
        getAllProvinceSet();
    }
}

//获取所有省份
function getAllProvince() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllProvinceAction",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.province_id+"\">"+obj.name+"</option>";
                    $("#province_select").append(html);
                }
                provinceChange();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//省份选中改变事件
function provinceChange() {
    var provinceId = document.getElementById("province_select").options[document.getElementById("province_select").selectedIndex].value;
    $("#city_choose_select").find("option").remove();
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllCityByProvinceId",
        type:"POST",
        dataType : 'json',
        data:{"province_id":provinceId},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.city_id+"\">"+obj.name+"</option>";
                    $("#city_choose_select").append(html);
                }
            }
            else{

            }
        },
    });
}


//获取所有省份-修改使用
function getAllProvinceSet() {
    var proviince_id = document.getElementById("proviince_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllProvinceAction",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "";
                    if (obj.province_id == proviince_id){
                        html = "<option value=\""+obj.province_id+"\" selected='selected'>"+obj.name+"</option>";
                    }
                    else{
                        html = "<option value=\""+obj.province_id+"\">"+obj.name+"</option>";
                    }
                    $("#province_select").append(html);
                }
                provinceChangeSet();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//省份选中改变事件-修改使用
function provinceChangeSet() {
    var city_id = document.getElementById("city_id").value;
    var provinceId = document.getElementById("province_select").options[document.getElementById("province_select").selectedIndex].value;
    $("#city_choose_select").find("option").remove();
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllCityByProvinceId",
        type:"POST",
        dataType : 'json',
        data:{"province_id":provinceId},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                var html = "";
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    if (obj.city_id == city_id){
                        html = "<option value=\""+obj.city_id+"\" selected='selected'>"+obj.name+"</option>";
                    }
                    else{
                        html = "<option value=\""+obj.city_id+"\">"+obj.name+"</option>";
                    }
                    $("#city_choose_select").append(html);
                }
            }
            else{

            }
        },
    });
}

//保存物业信息
function saveProperty() {
    if (type == 0){
        addProperty();
    }
    else{
        setProperty();
    }
}

//添加物业信息
function addProperty() {
    var account = document.getElementById("account").value;
    var province_id = document.getElementById("province_select").options[document.getElementById("province_select").selectedIndex].value;
    var city_id = document.getElementById("city_choose_select").options[document.getElementById("city_choose_select").selectedIndex].value;
    var residentials_name = document.getElementById("residentials_name").value;
    var address = document.getElementById("address").value;
    var contact_name = document.getElementById("contact_name").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    if (account == undefined || account == ""){
        layer.msg('请输入物业主账号', {icon: 5});
    }
    else if (province_id == undefined || province_id == ""){
        layer.msg('请选择物业所在省份', {icon: 5});
    }
    else if (city_id == undefined || city_id == ""){
        layer.msg('请选择物业所在城市', {icon: 5});
    }
    else if (residentials_name == undefined || residentials_name == ""){
        layer.msg('请输入物业名称', {icon: 5});
    }
    else if (address == undefined || address == ""){
        layer.msg('请输入物业地址', {icon: 5});
    }
    else if (contact_name == undefined || contact_name == ""){
        layer.msg('请输入物业联系人姓名', {icon: 5});
    }
    else if (contact_mobile == undefined || contact_mobile == ""){
        layer.msg('请输入物业联系电话', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/addPropertyAction",
            type:"POST",
            dataType : 'json',
            data:{"account":account,"province_id":province_id,"city_id":city_id,"residentials_name":residentials_name,"address":address,"contact_name":contact_name,"contact_mobile":contact_mobile},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    layer.msg(data.msg, {icon: 5});
                }
            },
        });
    }
}

//修改物业信息
function setProperty() {
    var record_id = document.getElementById("record_id").value;
    var province_id = document.getElementById("province_select").options[document.getElementById("province_select").selectedIndex].value;
    var city_id = document.getElementById("city_choose_select").options[document.getElementById("city_choose_select").selectedIndex].value;
    var residentials_name = document.getElementById("residentials_name").value;
    var address = document.getElementById("address").value;
    var contact_name = document.getElementById("contact_name").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    if (province_id == undefined || province_id == ""){
        layer.msg('请选择物业所在省份', {icon: 5});
    }
    else if (city_id == undefined || city_id == ""){
        layer.msg('请选择物业所在城市', {icon: 5});
    }
    else if (residentials_name == undefined || residentials_name == ""){
        layer.msg('请输入物业名称', {icon: 5});
    }
    else if (address == undefined || address == ""){
        layer.msg('请输入物业地址', {icon: 5});
    }
    else if (contact_name == undefined || contact_name == ""){
        layer.msg('请输入物业联系人姓名', {icon: 5});
    }
    else if (contact_mobile == undefined || contact_mobile == ""){
        layer.msg('请输入物业联系电话', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setPropertyAction",
            type:"POST",
            dataType : 'json',
            data:{"record_id":record_id,"province_id":province_id,"city_id":city_id,"residentials_name":residentials_name,"address":address,"contact_name":contact_name,"contact_mobile":contact_mobile},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    layer.msg(data.msg, {icon: 5});
                }
            },
        });
    }
}