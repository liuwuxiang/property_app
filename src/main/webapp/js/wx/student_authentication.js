var storage = window.localStorage;

//是否同意协议(-1-不同意,0-同意)
var authorization = -1;

//是否同意协议点击事件
function isAuthorization(){
	if(authorization == -1){
		authorization = 0;
		document.getElementById("check_img").src = "/property_system/images/wx/checked.png";
	}
	else{
		authorization = -1;
		document.getElementById("check_img").src = "/property_system/images/wx/check.png";
	}
}

//提交认证
function submitAuthentication() {
	var real_name = document.getElementById("real_name").value;
    var id_card_number = document.getElementById("id_card_number").value;
    var school = document.getElementById("school").value;
    var mobile = document.getElementById("mobile").value;
    var code = document.getElementById("code").value;
    if (real_name == undefined || real_name == ""){
        toast(1,"请输入真实姓名");
    }
    else if (id_card_number == undefined || id_card_number == ""){
        toast(1,"请输入身份证号码");
    }
    else if (id_card_number.length != 18){
        toast(1,"身份证号码不正确");
    }
    else if (mobile == undefined || mobile == ""){
        toast(1,"请输入手机号码");
    }
    else if (mobile.length != 11){
        toast(1,"手机号码不正确");
    }
    else if (code == undefined || code == ""){
        toast(1,"请输入验证码");
    }
    else if (authorization == -1){
        toast(1,"请先同意协议");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/submitWnkAuthentication",
            type:"POST",
            dataType : 'json',
            data:{"user_id":storage["user_id"],"type":0,"real_name":real_name,"id_card_number":id_card_number,"school":school,"mobile":mobile,"code":code},
            success:function(data){
                toast(1,data.msg);
                if (data.status == 0){
                    self.window.location.href = "/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=1";
                }
            },
        });
	}
}


//获取短信验证码
function getCode() {
    var mobile = document.getElementById("mobile").value;
    if (mobile == undefined || mobile == ""){
        toast(1,"请输入手机号");
	}
    else if (mobile.length != 11){
        toast(1,"手机号码不正确");
    }
	else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/getWnkAuthenticationMobileCode",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile},
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