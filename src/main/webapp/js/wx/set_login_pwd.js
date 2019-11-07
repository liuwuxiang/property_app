var storage = window.localStorage;
var code = "";

//初始化数据
function initData(current_code) {
    code = current_code;
}

//修改密码
function setLoginPwd() {
    var new_login_pwd = document.getElementById("new_login_pwd").value;
    var new_agin_login_pwd = document.getElementById("new_agin_login_pwd").value;
    if (new_login_pwd == undefined || new_login_pwd == ""){
        toast(1,"请输入新登录密码");
    }
    else if (new_agin_login_pwd == undefined || new_agin_login_pwd == ""){
        toast(1,"请确认新登录密码");
    }
    else if(new_login_pwd != new_agin_login_pwd){
        toast(1,"两次输入的密码不一致");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/setLoginPwd",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"],"new_login_pwd":new_login_pwd,"code":code},
            success:function(data){
                if (data.status == 0){
                    toast(0,"修改成功");
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}