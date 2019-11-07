var storage = window.localStorage;
var code = "";

function initData(current_code) {
    code = current_code;
}

//修改密码
function setPayPwd() {
    var new_pay_pwd = document.getElementById("new_pay_pwd").value;
    var new_agin_pay_pwd = document.getElementById("new_agin_pay_pwd").value;
    if (new_pay_pwd == undefined || new_pay_pwd == ""){
        toast(1,"请输入新支付密码");
    }
    else if (new_agin_pay_pwd == undefined || new_agin_pay_pwd == ""){
        toast(1,"请确认新支付密码");
    }
    else if(new_pay_pwd != new_agin_pay_pwd){
        toast(1,"两次输入的密码不一致");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/setPayPwd",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"],"new_pay_pwd":new_pay_pwd,"code":code},
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