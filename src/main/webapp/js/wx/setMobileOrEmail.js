var storage = window.localStorage;
/*手机号、邮箱修改类型(type=0:修改手机号，type=1:修改邮箱)*/
var setType = 0;
/*
 *	初始化页面数据
 * */
function initData(type){
    setType = type;
	if(type == 0){
		document.title = "修改绑定手机号";
		document.getElementById("tip_tag").innerHTML = "绑定手机号可以最大程度的保障您的账户安全";
		document.getElementById("number_tag").innerHTML = "手机号";
		document.getElementById("number_input").setAttribute("placeholder","请填写新的手机号");
		document.getElementById("bind_button").value = "绑定手机";
	}
	else{
		document.title = "修改绑定邮箱号";
		document.getElementById("tip_tag").innerHTML = "绑定邮箱号可以最大程度的保障您的账户安全";
		document.getElementById("number_tag").innerHTML = "邮箱号";
		document.getElementById("number_input").setAttribute("placeholder","请填写新的邮箱号");
		document.getElementById("bind_button").value = "绑定邮箱";
	}
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

/*
* 获取验证码
* */
function getCode() {
    var mobile = document.getElementById("number_input").value;
    if (mobile == undefined || mobile == ""){
        toast(1,setType==0?"请输入手机号":"请输入邮箱号");
	}
	else{
        if (setType == 0){
            getMobileCode();
        }
        else{
            getEmailCode();
        }
	}
}

/*
 *	获取短信验证码
 * */
function getMobileCode(){
    var mobile = document.getElementById("number_input").value;
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getMobileCode",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"mobile":mobile,"type":2},
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
 *	获取邮箱验证码
 * */
function getEmailCode(){
    var email = document.getElementById("number_input").value;
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getEmailCode",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"email":email},
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
* 绑定事件
* */
function bindAction() {
    var number = document.getElementById("number_input").value;
    var code = document.getElementById("code_input").value;
    if (number == undefined || number == ""){
        toast(1,setType==0?"请输入手机号":"请输入邮箱号");
	}
	else if (code == undefined || code == ""){
        toast(1,"请输入验证码");
	}
	else{
        if (setType == 0){
            mobileBindAction(number,code);
        }
        else{
            emailBindAction(number,code);
        }
	}

}

/*
* 手机号绑定事件
* */
function mobileBindAction(number,code) {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/setUserMobile",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"],"mobile":number,"code":code},
        success:function(data){
            if (data.status == 0){
                toast(0,"修改成功");
                storage.setItem("mobile",number);
                window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
* 邮箱绑定事件
* */
function emailBindAction(number,code) {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/setUserEmail",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"],"email":number,"code":code},
        success:function(data){
            if (data.status == 0){
                toast(0,"修改成功");
                storage.setItem("email",number);
                window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}