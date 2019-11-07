var storage = window.localStorage;

/*
 *	获取短信验证码
 * */
function getMobileCode(){
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getMobileCodeSetPwd",
        type:"POST",
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type":10},
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

//设置的支付密码
function setPayPwd() {
    var new_pwd = document.getElementById("new_pwd").value;
    var confirm_new_pwd = document.getElementById("confirm_new_pwd").value;
    var code = document.getElementById("code").value;
    if (new_pwd == undefined || new_pwd == ""){
        toast(1,"请输入新密码");
    }
    else if(confirm_new_pwd == undefined || confirm_new_pwd == ""){
        toast(1,"请再次输入新密码");
    }
    else if (new_pwd != confirm_new_pwd){
        toast(1,"两次输入的密码不一致");
    }
    else if (code == undefined || code == ""){
        toast(1,"请输入验证码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/setPwdPwd",
            type:"POST",
            dataType : 'json',
            data:{"business_id":storage["business_id"],"new_pay_pwd":confirm_new_pwd,"code":code},
            success:function(data){
                toast(1,data.msg);
                if (data.status == 0){
                    self.window.history.go(-1);
                }
            },
        });
    }

}