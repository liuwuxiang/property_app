//登录事件
function loginAction(){
	//账号
	var username = document.getElementById("username").value;
	//密码
	var password = document.getElementById("password").value;
	if(username == "" || username == undefined){
		alert("请输入账号!");
	}
	else if(password == "" || password == undefined){
		alert("请输入密码!");
	}
	else{
        $.ajax({
            url:"/property_system/admin/loginAction",
            type:"POST",
            dataType : 'json',
            data:{"account":username,"login_pwd":password},
            success:function(data){
                if (data.status == 0){
                    parent.layer.alert('登录成功!', {icon: 6});
                    self.window.location.href = "/property_system/admin/home";
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
	}
}
