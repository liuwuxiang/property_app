//修改密码
function saveLoginPwd() {
    var old_login_pwd = document.getElementById("old_login_pwd").value;
    var new_login_pwd = document.getElementById("new_login_pwd").value;
    var confirm_new_login_pwd = document.getElementById("confirm_new_login_pwd").value;
    if (old_login_pwd == undefined || old_login_pwd == ""){
        parent.layer.alert("请输入原始密码", {icon: 5});
    }
    else if (new_login_pwd == undefined || new_login_pwd == ""){
        parent.layer.alert("请输入新密码", {icon: 5});
    }
    else if (confirm_new_login_pwd == undefined || confirm_new_login_pwd == ""){
        parent.layer.alert("请确认新密码", {icon: 5});
    }
    else if (confirm_new_login_pwd != new_login_pwd){
        parent.layer.alert("两次输入的新密码不一致", {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setAdminLoginPwd",
            type:"POST",
            dataType : 'json',
            data:{"old_login_pwd":old_login_pwd,"new_login_pwd":new_login_pwd},
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