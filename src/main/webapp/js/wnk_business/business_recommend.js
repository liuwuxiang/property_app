//注册(type=0商家注册,type=1用户注册)
function register(type) {
	var mobile = document.getElementById("mobile").value;
	if (type == 0) {
		self.window.location.href = "/property_system/wnk_business/recommendRegisterBusiness?mobile="+mobile;
	}
	else{type
		self.window.location.href = "/property_system/wx/v1.0.0/register?invitation_code="+mobile+"&type=1";
	}
}