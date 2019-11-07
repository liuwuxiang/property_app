var storage = window.localStorage;
var clickType = -1;
/*
 *	手机号修改验证弹出层
 * type=0:登录密码修改,type=1:支付密码修改,type=2:密保问题修改
 * */
function setMobileCheckCen(type){
	clickType = type;
    var mobile = storage["mobile"];
    if (mobile == undefined || mobile == ""){
        toast(0,"请先绑定手机号");
    }
    else{
        document.getElementById("check_code").value = "";
        document.getElementById("check_title_tag").innerHTML = "验证手机号码";
        document.getElementById("check_content_tag").innerHTML = "输入手机"+mobile.slice(7)+"接收到的短信验证码";
        mui('#mobile_set_check_div').popover('show',document.getElementById("mobile_set_check_div")); // 将id为list的元素放在想要弹出的位置
    }

}

/*
 *关闭号码验证弹出层
 **/
function closeMobileCheckCen(){
	mui('#mobile_set_check_div').popover('toggle',document.getElementById("mobile_set_check_div")); // 将id为list的元素放在想要弹出的位置
}

/*
 *	获取短信验证码
 * */
function getMobileCode(){
	var type = -1;
    if (clickType == 0){
		type = 3;
	}
	else if (clickType == 1){
        type = 4;
    }
    else{
		type = 5;
	}
    var mobile = storage["mobile"];
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getMobileCode",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"mobile":mobile,"type":type},
        success:function(data){
            if (data.status == 0){
                toast(0,"验证码已发送");
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
 *	短信验证码校验
 * */
function mobileCodeCheck(){
    var type = -1;
    if (clickType == 0){
        type = 3;
    }
    else if (clickType == 1){
        type = 4;
    }
    else{
        type = 5;
    }
    var mobile = storage["mobile"];
    var code = document.getElementById("check_code").value;
    if(check_code == '' || check_code == undefined){
        toast(1,"请输入验证码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/checkMobileCode",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"code":code,"type":type,"mobile":mobile},
            success:function(data){
                if (data.status == 0){
                    document.getElementById("check_code").value = "";
                    closeMobileCheckCen();
                    if(clickType == 0){
                        self.window.location.href = "/property_system/wx/v1.0.0/setLoginPwd?current_code="+code;
                    }
                    else if(clickType == 1){
                        self.window.location.href = "/property_system/wx/v1.0.0/setPayPwd?current_code="+code;
                    }
                    else{
                        self.window.location.href = "/property_system/wx/v1.0.0/setSecurityQuestion?current_code="+code;
                    }
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
	}
}