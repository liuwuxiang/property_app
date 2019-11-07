var storage = window.localStorage;
/*手机号、邮箱修改类型(type=0:修改手机号，type=1:修改邮箱)*/
var setType = 0;

//业主认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var ownerState = -1;
//车主认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var cardState = -1;
//身份证认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var idCardState = -1;

/*
 *	头像选择事件
 * */
function headerChoose(){
	document.getElementById("header_file").click();
}

/*
 *	修改昵称
 * */
function setNickName(){
	self.window.location.href = "/property_system/wx/v1.0.0/setNickName";
}

/*
 *	修改性别
 * */
function setSex(){
	self.window.location.href = "/property_system/wx/v1.0.0/sexChoose";
}

/*
 *	手机号修改验证弹出层
 * (type=0:修改手机号，type=1:修改邮箱)
 * */
function setMobileCheckCen(type){
	setType = type;
	document.getElementById("check_code").value = "";
	if(type == 0){
	    var mobile = storage["mobile"];
	    if (mobile == undefined || mobile == ""){
            self.window.location.href = "/property_system/wx/v1.0.0/setMobileOrEmail?type="+setType;
        }
        else{
            document.getElementById("check_title_tag").innerHTML = "验证手机号码";
            document.getElementById("check_content_tag").innerHTML = "输入手机"+mobile.slice(7)+"接收到的短信验证码";
        }

	}
	else{
        var email = storage["email"];
        if (email == undefined || email == ""){
            self.window.location.href = "/property_system/wx/v1.0.0/setMobileOrEmail?type="+setType;
        }
        else{
            document.getElementById("check_title_tag").innerHTML = "验证邮箱号码";
            document.getElementById("check_content_tag").innerHTML = "输入邮箱"+email.slice(5)+"接收到的验证码";
        }

	}
	mui('#mobile_set_check_div').popover('show',document.getElementById("mobile_set_check_div")); // 将id为list的元素放在想要弹出的位置 
}

/*
 *关闭号码验证弹出层
 **/
function closeMobileCheckCen(){
	mui('#mobile_set_check_div').popover('toggle',document.getElementById("mobile_set_check_div")); // 将id为list的元素放在想要弹出的位置
}

/*
 *	号码验证确定事件
 * */
function numberCheckConfirmAction(){
	var check_code = document.getElementById("check_code").value;
	if(check_code == '' || check_code == undefined){
        toast(1,"请输入验证码");
	}
	else{
        if (setType == 0){
            mobileCodeCheck();
        }
        else{
            emailCodeCheck();
        }
	}
}


/*
 *	查看我的二维码
 * */
function lookMyQrcode(){
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserQrcode",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
				var nick_name = data.data.nick_name;
				var sex = data.data.sex;
				var mobile = data.data.mobile;
				var user_header = data.data.user_header;
				var qrcode_url = data.data.qrcode_url;
				document.getElementById("qrcode_nick_name").innerText = nick_name;
                document.getElementById("qrcode_header").src = user_header;
                document.getElementById("qrcode_photo").src = qrcode_url;
                document.getElementById("qrcode_mobile").innerText = mobile;
                if (sex == 0){
                    document.getElementById("qrcode_sex").src = "/property_system/images/wx/icon_man.png";
				}
				else if (sex == 1){
                    document.getElementById("qrcode_sex").src = "/property_system/images/wx/icon_woman.png";
				}
                toast(3,"关闭loading");
                mui('#my_qrcode_div').popover('show',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
 *关闭二维码查看视图
 * */
function closeQrcodeDiv(){
	mui('#my_qrcode_div').popover('toggle',document.getElementById("my_qrcode_div")); // 将id为list的元素放在想要弹出的位置
}

/*
 *进入实名认证中心
 * */
function joinRealNameAuthentication(){
	self.window.location.href = "/property_system/wx/v1.0.0/realNameAuthenticationHome";
}

/*
 *	进入安全中心
 * */
function joinSecurityCenter(){
	self.window.location.href = "/property_system/wx/v1.0.0/securityCenterHome";
}

/*
 *	进入收货地址界面
 * */
function joinReceivingAddress(){
    storage["address_name"] = "";
    storage["mobile"] = "";
    storage["detail_address"] = "";
    storage["province_name"] = "";
    storage["city_name"] = "";
    storage["city_id"] = "";
    storage["province_id"] = "";
	self.window.location.href = "/property_system/wx/v1.0.0/receivingAddressManager";
}

/*
* 初始化数据
* */
function initData() {
    document.getElementById("header_img").src = storage["header"];
    if (storage["nick_name"] == "" || storage["nick_name"] == undefined){
        document.getElementById("nick_name").innerText = "未设置";
	}
	else{
        document.getElementById("nick_name").innerText = storage["nick_name"];
	}
    if (storage["mobile"] == "" || storage["mobile"] == undefined){
        document.getElementById("mobile").innerText = "未设置";
    }
    else{
        document.getElementById("mobile").innerText = storage["mobile"];
    }
    if (storage["email"] == "" || storage["email"] == undefined){
        document.getElementById("email").innerText = "未设置";
    }
    else{
        document.getElementById("email").innerText = storage["email"];
    }
    if (storage["sex"] == 0){
        document.getElementById("sex").innerText = "男";
    }
    else if (storage["sex"] == 1){
        document.getElementById("sex").innerText = "女";
    }
    else{
        document.getElementById("sex").innerText = "保密";
	}
    getUserAuthenticationState();
    lookMyQrcode();

}

//获取认证状态信息
function getUserAuthenticationState() {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserAuthenticationState",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭Loading");
                document.getElementById("owner_authrntication").innerText = data.data.userOwner.authentication_result;
                document.getElementById("card_authrntication").innerText = data.data.userCard.authentication_result;
                ownerState = data.data.userOwner.state;
                cardState = data.data.userCard.state;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
* 获取验证码
* */
function getCode() {
    if (setType == 0){
        getMobileCode();
    }
    else{
        getEmailCode();
    }
}

/*
 *	获取短信验证码
 * */
function getMobileCode(){
    var mobile = storage["mobile"];
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
    var email = storage["email"];
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
 *	短信验证码校验
 * */
function mobileCodeCheck(){
    var mobile = storage["mobile"];
    var code = document.getElementById("check_code").value;
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/checkMobileCode",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"code":code,"type":2,"mobile":mobile},
        success:function(data){
            if (data.status == 0){
                closeMobileCheckCen();
                self.window.location.href = "/property_system/wx/v1.0.0/setMobileOrEmail?type="+setType;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
 *	邮箱验证码校验
 * */
function emailCodeCheck(){
    var email = storage["email"];
    var code = document.getElementById("check_code").value;
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/checkEmailCode",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"email":email,"code":code},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                closeMobileCheckCen();
                self.window.location.href = "/property_system/wx/v1.0.0/setMobileOrEmail?type="+setType;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


function chooseHeaderChangeFile() {
    toast(2,"开启loading");
    $.ajaxFileUpload({
        url : '/property_system/images/savaimageMobile.do', // 用于文件上传的服务器端请求地址
        secureuri : false, // 是否需要安全协议，一般设置为false
        fileElementId : 'header_file', // 文件上传域的ID
        dataType : 'json', // 返回值类型 一般设置为json
        type : "post",
        data:{"fileNameStr":"ajaxFile","fileId":"header_file"},
        success : function(data, status) // 服务器成功响应处理函数
        {
            if (data.error == 0){
                toast(3,"关闭loading");
                setHeader(data.url,data.url_location);
            }
            else{
                toast(1,data.message);
            }
        },
        error : function(data, status, e)// 服务器响应失败处理函数
        {
            toast(3,"关闭loading");
            alert(e);
        }
    });
}

//头像修改
function setHeader(imageId,header_url) {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/setUserHeader",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"],"imageid":imageId},
        success:function(data){
            if (data.status == 0){
                storage["header"] = header_url;
                document.getElementById("header_img").src = storage["header"];
                toast(0,"修改完成");
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
 *	获取二维码信息
 * */
function lookMyQrcode(){
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserQrcode",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var nick_name = data.data.nick_name;
                var sex = data.data.sex;
                var mobile = data.data.mobile;
                var user_header = data.data.user_header;
                var qrcode_url = data.data.qrcode_url;
                document.getElementById("qrcode_nick_name").innerText = nick_name;
                document.getElementById("qrcode_header_img").src = user_header;
                document.getElementById("qrcode_mobile").innerText = mobile;
                document.getElementById("qrcode_photo_url").src = qrcode_url;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

/*
 *	进入下一级页面(type=0:业主认证,type=1:车主认证,type=2:身份证认证)
 * */
function joinNextView(type){
    if(type == 0){
        self.window.location.href = "/property_system/wx/v1.0.0/ownerAuthentication";
    }
    else if(type == 1){
        self.window.location.href = "/property_system/wx/v1.0.0/cardAuthentication";
    }
    else{
        self.window.location.href = "/property_system/wx/v1.0.0/idCardAuthentication";
    }
}