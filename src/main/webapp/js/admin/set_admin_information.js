//保存信息
function saveInformation() {
    var name = document.getElementById("name").value;
    var mobile = document.getElementById("mobile").value;
    if (name == undefined || name == ""){
        parent.layer.alert("请输入姓名", {icon: 5});
    }
    else if (mobile == undefined || mobile == ""){
        parent.layer.alert("请输入手机号", {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setAdminInformationAction",
            type:"POST",
            dataType : 'json',
            data:{"name":name,"mobile":mobile},
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