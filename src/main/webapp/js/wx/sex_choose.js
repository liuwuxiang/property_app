var storage = window.localStorage;
//当前选中的性别(0-男,1-女,2-保密)
var current_choose_sex = -1;

/*
* 初始化数据
* */
function initData() {
    sexChooseAction(storage["sex"]);
}

/*
 *	性别选择事件(0-男,1-女,2-保密)
 * */
function sexChooseAction(sex){
	if(sex != current_choose_sex){
		var sexLiId = new Array();
		sexLiId[0] = "male";
		sexLiId[1] = "female";
		sexLiId[2] = "secrecy";
		if(current_choose_sex != -1){
			document.getElementById(sexLiId[current_choose_sex]).getElementsByTagName("img")[0].src = "/property_system/images/wx/icon_checkbox.png";
		}
		document.getElementById(sexLiId[sex]).getElementsByTagName("img")[0].src = "/property_system/images/wx/icon_checkbox_checked.png";
		current_choose_sex = sex;
	}
}

/*
* 修改用户性别事件
* */
function setUseSex() {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/setUserSex",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"],"sex":current_choose_sex},
        success:function(data){
            if (data.status == 0){
                toast(0,"修改成功");
                storage.setItem("sex",current_choose_sex);
                window.history.go(-1);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}