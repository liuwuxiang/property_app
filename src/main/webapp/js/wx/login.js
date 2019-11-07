var storage = window.localStorage;

/*
 *	列表项初始化
 * type=0:账号密码登录，type=1：手机快捷登录
 * */
function loginOption(type){
	var account_login = document.getElementById("account_login");
	var quick_login = document.getElementById("quick_login");
	if(type == 0){
		account_login.style.display = "block";
		quick_login.style.display = "none";
		document.getElementById("account_login_item").setAttribute("class","item sel"); 
		document.getElementById("quick_login_item").setAttribute("class","item"); 
	}
	else{
		account_login.style.display = "none";
		quick_login.style.display = "block";
		document.getElementById("account_login_item").setAttribute("class","item"); 
		document.getElementById("quick_login_item").setAttribute("class","item sel"); 
	}
}

/*
 *	账号注册
 * */
function accountRegister(){
	self.window.location.href = "/property_system/wx/v1.0.0/register";
}

/*
 *	进入找回密码界面
 * */
function joinRetrievePassword(){
	self.window.location.href = "/property_system/wx/v1.0.0/retrievePassword";
}

/*
 *	账号密码登录事件
 * */
function accountLoginAction(){
	var account   = document.getElementById("account_mobile").value;
    var login_pwd = document.getElementById("login_pwd").value;
    if (account == undefined || account == ''){
        toast(1,"请输入手机号");
	}
	else if (login_pwd == undefined || login_pwd == ''){
        toast(1,"请输入密码");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/userAndPwdLogin",
            type:"POST",
            dataType : 'json',
            data:{"mobile":account,"login_pwd":login_pwd},
            success:function(data){
                console.log(data);
                if (data.status == 0){
                    saveUserInformation(data);
                    toast(3,"关闭loading");
                    self.window.location.href = "/property_system/wx/v1.0.0/my";
                }
                else{
                    toast(1,data.msg);
				}
            }
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
            url:"/property_system/app/v1.0.0/userMobileAndCodeLogin",
            type:"POST",
            dataType : 'json',
            data:{"mobile":quick_mobile,"code":quick_login_code},
            success:function(data){
                if (data.status == 0){
                    saveUserInformation(data);
                    toast(3,"关闭loading");
                    self.window.location.href = "/property_system/wx/v1.0.0/my";
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
            url:"/property_system/app/v1.0.0/getMobileCode",
            type:"POST",
            dataType : 'json',
            data:{"mobile":quick_mobile,"type":1},
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
* 保存用户信息
* */
function saveUserInformation(data) {
    var user_id = data.data.user_id;
    var login_session = data.data.login_session;
    var level_name = data.data.level_name;
    var level_icon = data.data.level_icon;
    var consumption_integral = data.data.consumption_integral;
    var nick_name = data.data.nick_name;
    var sex = data.data.sex;
    var header = data.data.header;
    var recommend_mobile = data.data.recommend_mobile;
    var team_members_number = data.data.team_members_number;
    var general_integral = data.data.general_integral;
    var mobile = data.data.mobile;
    var email = data.data.mobile;
    var is_microfinance = data.data.mobile;
    storage.setItem("user_id",user_id);
    storage.setItem("login_session",login_session);
    storage.setItem("level_name",level_name);
    storage.setItem("level_icon",level_icon);
    storage.setItem("consumption_integral",consumption_integral);
    storage.setItem("nick_name",nick_name);
    storage.setItem("sex",sex);
    storage.setItem("header",header);
    storage.setItem("recommend_mobile",recommend_mobile);
    storage.setItem("team_members_number",team_members_number);
    storage.setItem("general_integral",general_integral);
    storage.setItem("mobile",mobile);
    storage.setItem("email",email);
    storage.setItem("is_microfinance",is_microfinance);
}

//微信登录
function wxLogin() {
    toast(0,"暂未开放");
    //self.window.location.href = "/property_system/wx/v1.0.0/wxLogin";
}