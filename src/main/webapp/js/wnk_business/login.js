var storage = window.localStorage;
//登录方式切换(0-账号密码登录,1-手机快捷登录)
function loginWayClick(index){
	var account_login = document.getElementById("account_login_from");
	var code_login = document.getElementById("code_login_from");
	if(index == 0){
		account_login.style.display = "block";
		code_login.style.display = "none";
		document.getElementById("account_login").setAttribute("class","item sel"); 
		document.getElementById("code_login").setAttribute("class","item");
	}
	else{
		code_login.style.display = "block";
		account_login.style.display = "none";
		document.getElementById("code_login").setAttribute("class","item sel"); 
		document.getElementById("account_login").setAttribute("class","item");
	}
}

//手机号+密码登录事件
function mobileAndPwdLogin() {
	var mobile = document.getElementById("mobile").value;
	var login_pwd = document.getElementById("login_pwd").value;
    if (mobile == undefined || mobile == ''){
        toast(1,"请输入手机号");
    }
    else if (login_pwd == undefined || login_pwd == ''){
        toast(1,"请输入密码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/mobileAndPwdLogin",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile,"login_pwd":login_pwd},
            success:function(data){
                if (data.status == 0){
                    saveUserInformation(data);
                    toast(3,"关闭loading");
                    self.window.location.href = "/property_system/wnk_business/joinOrders";
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
    var quick_mobile = document.getElementById("quick_mobile").value;
    if (quick_mobile == undefined || quick_mobile == ''){
        toast(1,"请输入手机号");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/getMobileCode",
            type:"POST",
            dataType : 'json',
            data:{"mobile":quick_mobile,"type":8},
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
 *	快捷登录事件
 * */
function quickLoginAction(){
    var quick_mobile = document.getElementById("quick_mobile").value;
    var quick_login_code = document.getElementById("quick_login_code").value;
    if (quick_mobile == undefined || quick_mobile == ''){
        toast(1,"请输入手机号");
    }
    else if (quick_login_code == undefined || quick_login_code == ''){
        toast(1,"请输入验证码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/businessMobileAndCodeLogin",
            type:"POST",
            dataType : 'json',
            data:{"mobile":quick_mobile,"code":quick_login_code},
            success:function(data){
                if (data.status == 0){
                    saveUserInformation(data);
                    toast(3,"关闭loading");
                    self.window.location.href = "/property_system/wnk_business/joinOrders";
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}

/*
* 保存用户信息
* */
function saveUserInformation(data) {
    var user_id = data.data.business_id;
    var login_session = data.data.login_session;
    storage.setItem("business_id",user_id);
    storage.setItem("login_session",login_session);
}


/*
* 商家注册
* */
function businessRegister() {
    self.window.location.href = "/property_system/wnk_business/register?mobile=\"\"";
}