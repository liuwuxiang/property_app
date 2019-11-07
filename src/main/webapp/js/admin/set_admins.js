//初始化界面
function initView() {
    getAllAdminType();
}


//获取所有管理员类别
function getAllAdminType() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllAdminTypes",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.id+"\">"+obj.type_name+"</option>";
                    $(".layui-select-group").append(html);
                }
                getAdminsInformation();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}


//获取管理员信息
function getAdminsInformation() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAdminsInformation",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var name = data.data.name;
                var mobile = data.data.mobile;
                var postion = data.data.position;
                var type_id = data.data.admin_type_id;
                document.getElementById("name").value = name;
                document.getElementById("mobile").value = mobile;
                document.getElementById("postion").value = postion;
                $(".layui-select-group").val(type_id);
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//保存管理员信息
function saveAdminInformation() {
    var record_id = document.getElementById("record_id").value;
    var name = document.getElementById("name").value;
    var mobile = document.getElementById("mobile").value;
    var postion = document.getElementById("postion").value;
    var type_id = document.getElementById("layui-select-group").options[document.getElementById("layui-select-group").selectedIndex].value;
    if (name == undefined || name == ""){
        layer.msg('请输入管理员姓名!', {icon: 5});
    }
    else if (mobile == undefined || mobile == ""){
        layer.msg('请输入管理员电话!', {icon: 5});
    }
    else if (postion == undefined || postion == ""){
        layer.msg('请输入管理员职位!', {icon: 5});
    }
    else if (type_id == undefined || postion == ""){
        layer.msg('请先创建管理员类别!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setAdminInformationAction",
            type:"POST",
            dataType : 'json',
            data:{"name":name,"mobile":mobile,"position":postion,"admin_type_id":type_id,"record_id":record_id},
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