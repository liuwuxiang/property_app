var storage = window.localStorage;
/*
* 注册账号
* */
function registerAccount() {
    var mobile = document.getElementById("mobile").value;
    var code = document.getElementById("code").value;
    var login_pwd = document.getElementById("login_pwd").value;
    var invitation_code = document.getElementById("invitation_code").value;
    var type = document.getElementById("type").value;
    if (invitation_code == undefined || invitation_code == ''){
        toast(1,"请输入邀请码");
    }
    else if (mobile == undefined || mobile == ''){
        toast(1,"请输入手机号");
    }
    else if (code == undefined || code == ''){
        toast(1,"请输入验证码");
    }
    else if (login_pwd == undefined || login_pwd == ''){
        toast(1,"请输入登录密码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/userMobileAndCodeRegister",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile,"code":code,"login_pwd":login_pwd,"invitation_mobile":invitation_code,"type":type},
            success:function(data){
                if (data.status == 0){
                    toast(1,"注册成功");
                    storage.setItem("user_id",data.data.user_id);
                    self.window.location.href = "/property_system/wx/v1.0.0/pageRegisterSuccessLogin?user_id="+data.data.user_id;
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}

/*
 *	获取短信验证码
 * */
function getMobileCode(){
    var mobile = document.getElementById("mobile").value;
    if (mobile == undefined || mobile == ''){
        toast(1,"请输入手机号");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/getMobileCode",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile,"type":0},
            success:function(data){
                if (data.status == 0){
                    toast(0,"获取成功");
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}