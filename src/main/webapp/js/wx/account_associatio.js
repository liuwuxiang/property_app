var storage = window.localStorage;
//关联的 手机号
var relation_mobile = "";
//值为0时表示未关联手机 号，值为1时表示已关联 手机号(当值为1时不可再 次进行关联，为1时点击 手机号关联不可进入下 一个界面)
var mobile_state = -1;
//值为0时表示未关联微 信，值为1时表示已关联 微信(当值为1时不可再次 进行关联)
var wx_state = -1;

/*
 *	进入手机号关联界面
 * */
function joinMobileAssociatio(){
	if (mobile_state == 0){
        self.window.location.href = "/property_system/wx/v1.0.0/mobileAssociation";
	}
	else{
        toast(1,"此账号已关联手机号");
	}
}

/*
 *	关联微信号
 * */
function associationWX(){
    self.window.location.href = "/property_system/wx/v1.0.0/associationWX";
}

//数据初始化
function initData() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserMobileWXRelationState",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭Loading");
                relation_mobile = data.data.relation_mobile;
                mobile_state = data.data.mobile_state;
                wx_state = data.data.wx_state;
                if (wx_state == 0){
                    document.getElementById("wx_state").innerText = "未绑定";
				}
				else{
                    document.getElementById("wx_state").innerText = "已绑定";
				}
                if (mobile_state == 0){
                    document.getElementById("relation_mobile").innerText = "未关联";
				}
				else{
					var phnumAfter = relation_mobile.substr(0,3) + "****" + relation_mobile.substr(7);
					var result = relation_mobile.replace(relation_mobile,phnumAfter);
                    document.getElementById("relation_mobile").innerText = result;
				}
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}