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
            data:{"mobile":mobile,"type":7},
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

/*
* 修改登录密码
* */
function setLoginPWD() {
    var mobile = document.getElementById("mobile").value;
    var code = document.getElementById("code").value;
    var login_pwd = document.getElementById("login_pwd").value;
    if (mobile == undefined || mobile == ''){
        toast(1,"请输入手机号");
    }
    else if (code == undefined || code == ''){
        toast(1,"请输入验证码");
    }
    else if (login_pwd == undefined || login_pwd == ''){
        toast(1,"请输入新的登录密码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/retrieveLoginPWD",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile,"code":code,"new_login_pwd":login_pwd},
            success:function(data){
                if (data.status == 0){
                    toast(0,"修改成功");
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}