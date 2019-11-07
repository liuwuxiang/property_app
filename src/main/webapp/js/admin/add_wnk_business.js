//type=0添加,type=1修改
var type;

function initView(current_type) {
    type = current_type;
    if (type == 0){
        getBusinessType();
        getBusinessRegion();
    }
    else{
        var state = document.getElementById("state").value;
        $("#account_state_select").val(state);
        $('#account').attr("readonly",true);
        getBusinessTypeSet();
        getBusinessRegionSet();
    }
}

// 获取商家分类
function getBusinessType() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getWnkBusinessType",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                    $("#business_type_select").append(html);
                }
                // 获取对应分类下的所有一级标签
                selectBusinessTagOneByBusinessTypeId(list[0].id);
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

// 保存时获取商家分类
function getBusinessTypeSet() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getWnkBusinessType",
        type:"POST",
        dataType : 'json',
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                    $("#business_type_select").append(html);
                }
                var business_type_id = document.getElementById("business_type_id").value;
                $("#business_type_select").val(business_type_id);
                selectBusinessTagOneByBusinessTypeId(business_type_id);
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//保存商户信息
function saveBusiness() {
    if (parseInt(type) === 0){
        addBusinessInformation();
    }
    else{
        updateAddBusinessInformation();
    }
}

//添加商户信息
function addBusinessInformation() {
    var account = document.getElementById("account").value;
    var login_pwd = document.getElementById("login_pwd").value;
    var state = document.getElementById("account_state_select").options[document.getElementById("account_state_select").selectedIndex].value;
    var business_type_id = document.getElementById("business_type_select").options[document.getElementById("business_type_select").selectedIndex].value;
    var store_name = document.getElementById("store_name").value;
    var address = document.getElementById("address").value;
    var store_describe = document.getElementById("store_describe").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    var area = document.getElementById("area").value;
    if (account == undefined || account == ""){
        layer.msg('请输入商户主账号', {icon: 5});
    }
    else if (login_pwd == undefined || login_pwd == ""){
        layer.msg('请输入登录密码', {icon: 5});
    }
    else if (business_type_id == undefined || business_type_id == ""){
        layer.msg('请先创建商户分类', {icon: 5});
    }
    else if (store_name == undefined || store_name == ""){
        layer.msg('请输入商户名称', {icon: 5});
    }
    else if (address == undefined || address == ""){
        layer.msg('请输入商户地址', {icon: 5});
    }
    else if (store_describe == undefined || store_describe == ""){
        layer.msg('请输入商户简介', {icon: 5});
    }
    else if (contact_mobile == undefined || contact_mobile == ""){
        layer.msg('请输入商户联系电话', {icon: 5});
    }
    else if (area == undefined || area == ""){
        layer.msg('请输入商户店铺区域', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/addWnkBusiness",
            type:"POST",
            dataType : 'json',
            data:{"account":account,"login_pwd":login_pwd,"state":state,"business_type_id":business_type_id,"store_name":store_name,"address":address,"store_describe":store_describe,"contact_mobile":contact_mobile,"area":area,"lunbo":""},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}

//更新商户信息
function updateAddBusinessInformation() {
    var business_id = document.getElementById("business_id").value;
    var account = document.getElementById("account").value;
    var login_pwd = document.getElementById("login_pwd").value;
    var state = document.getElementById("account_state_select").options[document.getElementById("account_state_select").selectedIndex].value;
    var business_type_id = document.getElementById("business_type_select").options[document.getElementById("business_type_select").selectedIndex].value;
    var store_name = document.getElementById("store_name").value;
    var address = document.getElementById("address").value;
    var store_describe = document.getElementById("store_describe").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    var area = document.getElementById("area").value;

    var business_tag_one = document.getElementById('business_tag_one_value').value;
    var business_tag_two = $('#business_tag_two').val();

    if(String(business_tag_two) === '无对应的二级标签'){
        business_tag_two = -1;
    }

    if (account == undefined || account == ""){
        layer.msg('请输入商户主账号', {icon: 5});
    }
    else if (login_pwd == undefined || login_pwd == ""){
        layer.msg('请输入登录密码', {icon: 5});
    }
    else if (business_type_id == undefined || business_type_id == ""){
        layer.msg('请先创建商户分类', {icon: 5});
    }
    else if (store_name == undefined || store_name == ""){
        layer.msg('请输入商户名称', {icon: 5});
    }
    else if (address == undefined || address == ""){
        layer.msg('请输入商户地址', {icon: 5});
    }
    else if (store_describe == undefined || store_describe == ""){
        layer.msg('请输入商户简介', {icon: 5});
    }
    else if (contact_mobile == undefined || contact_mobile == ""){
        layer.msg('请输入商户联系电话', {icon: 5});
    }
    else if (area == undefined || area == ""){
        layer.msg('请输入商户店铺区域', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setWnkBusiness",
            type:"POST",
            dataType : 'json',
            data:{"business_id":business_id,
                "account":account,
                "login_pwd":login_pwd,
                "state":state,
                "business_type_id":business_type_id,
                "store_name":store_name,
                "address":address,
                "store_describe":store_describe,
                "contact_mobile":contact_mobile,
                "area":area,
                'business_tag_one' : business_tag_one,
                'business_tag_two' : business_tag_two
            },
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}


function getBusinessRegionSet() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/selectWnkBusinessRegionAll",
        type:"POST",
        dataType : 'json',
        success:function(data){
            layer.close(loading_index);
            if (parseInt(data.status) === 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.name+"\">"+obj.name+"</option>";
                    $("#area_select").append(html);
                }
                var business_type_id = document.getElementById("area").value;
                $("#area_select").val(business_type_id);
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

function getBusinessRegion() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/selectWnkBusinessRegionAll",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                    $("#area_select").append(html);
                }
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

/**
 * 商户分类选择事件
 * @param t 标签自身
 */
function cityChange(t) {
    selectBusinessTagOneByBusinessTypeId($('#business_type_select').val());
}

/**
 * 获取所有对应分类的所有一级标签
 * @param business_type_id 商家分类ID
 */
function selectBusinessTagOneByBusinessTypeId(business_type_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/selectBusinessTagOneByBusinessTypeId",
        type:"POST",
        dataType : 'json',
        data:{
            businessTypeID : business_type_id
        },
        success:function(data){
            layer.close(loading_index);
            if (parseInt(data.status) === 0){
                $('#business_tag_one').html(data.data.name);
                $('#business_tag_one_value').val(data.data.id);
                $('#business_tag_two').show();
                selectBusinessTagTwoByOneTagId(data.data.id);
            }  else {
                $('#business_tag_one').html('未关联商家标签');
                $('#business_tag_one_value').val('');
                $('#business_tag_two').hide();
            }
        },
    });
}

/**
 * 获取所有对应分类的所有一级标签
 * @param business_one_tag_id 商户一级标签ID
 */
function selectBusinessTagTwoByOneTagId(business_one_tag_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getTwoBusinessTagList",
        type:"POST",
        dataType : 'json',
        data:{
            one_tag_id : business_one_tag_id
        },
        success:function(data){
            layer.close(loading_index);
            $('#business_tag_two').empty();
            if (parseInt(data.status) === 0){
                if (data.data != null && data.data.length > 0){
                    var html = '<option value="">暂不选择</option>';
                    $('#business_tag_two').append(html);
                    for(let i = 0;i< data.data.length ; i++){
                        let obj = data.data[i];
                        html = '<option value="'+obj.id+'">'+obj.name+'</option>';
                        $('#business_tag_two').append(html);
                    }
                }
            } else {
                var html = '<option>无对应的二级标签</option>';
                $('#business_tag_two').append(html);
                document.getElementById('business_tag_two').setAttribute('disabled','disabled');
            }
        },
    });
}
