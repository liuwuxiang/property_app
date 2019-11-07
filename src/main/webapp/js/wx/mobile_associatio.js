var storage = window.localStorage;
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
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"mobile":mobile,"type":6},
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
 *	关联手机号
 * */
function relationMobile(){
    var mobile = document.getElementById("mobile").value;
    var code = document.getElementById("code").value;
    if (mobile == undefined || mobile == ''){
        toast(1,"请输入手机号");
    }
    else if (code == undefined || code == ''){
        toast(1,"请输入验证码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/relationMobileOrWX",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"user_id":storage["user_id"],"type":1,"code":code,"relation_number":mobile},
            success:function(data){
                if (data.status == 0){
                    toast(0,"关联成功");
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}