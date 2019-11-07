var storage = window.localStorage;
/*
 *	列表项初始化
 * type=0:系统消息，type=1：我的消息
 * */
function listOptionInit(type){
	var system_message_ul = document.getElementById("system_message_ul");
	var my_message_ul = document.getElementById("my_message_ul");
	if(type == 0){
		system_message_ul.style.display = "block";
		my_message_ul.style.display = "none";
		document.getElementById("system_message_item").setAttribute("class","item sel"); 
		document.getElementById("my_message_item").setAttribute("class","item");
//      getUserSystemMessage();
	}
	else if(type == 1){
		system_message_ul.style.display = "none";
		my_message_ul.style.display = "block";
		document.getElementById("system_message_item").setAttribute("class","item"); 
		document.getElementById("my_message_item").setAttribute("class","item sel");
//      publicnull_tip("暂无消息",0);
	}
}




/*
* 提示修改
* */
function publicnull_tip(content,state) {
    var publicnull_tip = document.getElementById("publicnull_tip");
    if (state == 0){
        publicnull_tip.style.display = "none";
    }
    else{
        document.getElementById("request_tip").innerText = content;
        publicnull_tip.style.display = "block";
    }
}