
function initView() {
    getBusinessTypeSet();
}

function getBusinessTypeSet() {
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
                var business_type_id = document.getElementById("type_id").value;
                $("#business_type_select").val(business_type_id);
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//保存商户信息
function saveBusiness() {
    if (type == 0){
        addBusinessInformation();
    }
    else{
        updateAddBusinessInformation();
    }
}

//更新申请信息
function updateAuditedInformation(type) {
    var record_id = document.getElementById("record_id").value;
    var store_name = document.getElementById("store_name").value;
    var area = document.getElementById("area").value;
    var address = document.getElementById("address").value;
    var business_type_id = document.getElementById("business_type_select").options[document.getElementById("business_type_select").selectedIndex].value;
    var login_account = document.getElementById("login_account").value;
    var contact_name = document.getElementById("contact_name").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    var store_describe = document.getElementById("store_describe").value;
    if (store_name == undefined || store_name == ""){
        layer.msg('请输入店铺名称', {icon: 5});
    }
    else if (area == undefined || area == ""){
        layer.msg('请输入店铺区域', {icon: 5});
    }
    else if (address == undefined || address == ""){
        layer.msg('请输入店铺地址', {icon: 5});
    }
    else if (login_account == undefined || login_account == ""){
        layer.msg('请输入登录账号', {icon: 5});
    }
    else if (contact_name == undefined || contact_name == ""){
        layer.msg('请输入联系人姓名', {icon: 5});
    }
    else if (contact_mobile == undefined || contact_mobile == ""){
        layer.msg('请输入联系电话', {icon: 5});
    }
    else if (store_describe == undefined || store_describe == ""){
        layer.msg('请输入店铺简介', {icon: 5});
    }
    else{
        if (type == 2){
            layer.prompt({title: '输入拒绝原因', formType: 2,
                // 点击确定按钮事件
                yes : function(){
                    // 取输入框数据
                    var pass =$(document.getElementsByClassName('layui-layer-input')[0]).val();
                    if (pass == undefined || pass == ""){
                        layer.msg('请输入拒绝原因!', {icon: 5});
                    }
                    else{
                        dealBusinessRegisterSHState(record_id,store_name,business_type_id,area,address,login_account,contact_name,contact_mobile,store_describe,type,pass);
                        layer.closeAll();
                    }
                }
            });
        }
        else{
            dealBusinessRegisterSHState(record_id,store_name,business_type_id,area,address,login_account,contact_name,contact_mobile,store_describe,type,"");
        }


    }
}

//处理商家审核状态
function dealBusinessRegisterSHState(record_id,store_name,business_type_id,area,address,login_account,contact_name,contact_mobile,store_describe,type,reason) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/updateWnkBusinessAuditedInformation",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id,"store_name":store_name,"type_id":business_type_id,"area":area,"address":address,"login_account":login_account,"contact_name":contact_name,"contact_mobile":contact_mobile,"miaoshu":store_describe,"state":type,"reason":reason},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                if (type == 2){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    addBusinessInformation(login_account,'123456',0,business_type_id,store_name,address,store_describe,contact_mobile,area);
                }
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//添加商户信息
function addBusinessInformation(account,login_pwd,state,business_type_id,store_name,address,store_describe,contact_mobile,area) {
    var lunbo = document.getElementById("lunbo").value;
    var recommend_id = document.getElementById("recommend_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/addWnkBusiness",
        type:"POST",
        dataType : 'json',
        data:{"account":account,"login_pwd":login_pwd,"state":state,"business_type_id":business_type_id,"store_name":store_name,"address":address,"store_describe":store_describe,"contact_mobile":contact_mobile,"area":area,"lunbo":lunbo,"recommend_id":recommend_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                layer.msg("审核成功,默认密码为:"+login_pwd, {icon: 6});
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}
