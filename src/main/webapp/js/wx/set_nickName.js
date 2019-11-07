var storage = window.localStorage;

//初始化数据
function initData() {
    document.getElementById("nickName_input").value = storage["nick_name"];
}

/*
 *	修改昵称事件
 * */
function setNickNameAction(){
	var nickName = document.getElementById("nickName_input").value;
	if(nickName == '' || nickName_input == undefined){
        toast(1,"请输入昵称");
	}
	else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/setUserNickName",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"user_id":storage["user_id"],"nick_name":nickName},
            success:function(data){
                if (data.status == 0){
                    toast(0,"修改成功");
                    storage.setItem("nick_name",nickName);
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
	}
}
