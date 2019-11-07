var storage = window.localStorage;
/*
 *	进入账号关联页面
 * */
function joinAccountAssociatio(){
	self.window.location.href = "/property_system/wx/v1.0.0/accountAssociatio";
}

/*
 *	进入建议反馈
 * */
function joinSuggestionFeedback(){
	self.window.location.href = "/property_system/wx/v1.0.0/suggestionFeedback";
}

/*
 * 进入关于我们界面
 * */
function joinAboutUs(){
	self.window.location.href = "/property_system/wx/v1.0.0/aboutUs";
}

/*
 *	退出登录
 * */
function exitLogin(){
    if(window.confirm('确认退出登录？')){
        exitLoginNetwork();
        return true;
    }else{
        //alert("取消");
        return false;
    }
}

//退出登录网络事件
function exitLoginNetwork() {
    $.ajax({
        url:"/property_system/wx/v1.0.0/exitLogin",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                storage.setItem("user_id","");
                storage.setItem("login_session","");
                storage.setItem("level_name","");
                storage.setItem("level_icon","");
                storage.setItem("consumption_integral","");
                storage.setItem("nick_name","");
                storage.setItem("sex","");
                storage.setItem("header","");
                storage.setItem("recommend_mobile","");
                storage.setItem("team_members_number","");
                storage.setItem("general_integral","");
                storage.setItem("mobile","");
                storage.setItem("email","");
                storage.setItem("is_microfinance","");
                self.window.location.href = data.data.url;
            }
            else{
                alert(data.msg);
            }
        },
    });
}

function showToast() {
    if (storage["message"] != undefined && storage["message"] != ""){
        toast(storage["message"]);
        storage["message"] = "";
    }
}