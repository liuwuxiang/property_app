var storage = window.localStorage;
//业主认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var ownerState = -1;
//车主认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var cardState = -1;
//身份证认证状态(-1- 未认证,0-待审核,1-已认证,2- 认证失败)
var idCardState = -1;

/*
 *	进入下一级页面(type=0:业主认证,type=1:车主认证,type=2:身份证认证,type=3:银行卡信息)
 * */
function joinNextView(type){
	if(type == 0){
		self.window.location.href = "/property_system/wx/v1.0.0/ownerAuthentication";
	}
	else if(type == 1){
		self.window.location.href = "/property_system/wx/v1.0.0/cardAuthentication";
	}
	else if(type == 2){
        self.window.location.href = "/property_system/wx/v1.0.0/idCardAuthentication";
    }
	else{
        self.window.location.href = "/property_system/wx/v1.0.0/joinBankCardSetting";
	}
}

//初始化信息
function initData() {
    getUserAuthenticationState();
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
                // document.getElementById("owner_authrntication").innerText = data.data.userOwner.authentication_result;
				// document.getElementById("card_authrntication").innerText = data.data.userCard.authentication_result;
				document.getElementById("id_card_authrntication").innerText = data.data.userIdCard.authentication_result;
                ownerState = data.data.userOwner.state;
                cardState = data.data.userCard.state;
                idCardState = data.data.userIdCard.state;
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}