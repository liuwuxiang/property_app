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
        getUserSystemMessage();
	}
	else if(type == 1){
		system_message_ul.style.display = "none";
		my_message_ul.style.display = "block";
		document.getElementById("system_message_item").setAttribute("class","item"); 
		document.getElementById("my_message_item").setAttribute("class","item sel");
        publicnull_tip("暂无消息",0);
	}
}


//获取系统消息
function getUserSystemMessage() {
    toast(2,"打开loading");
    publicnull_tip("暂无消息",1);
    $("#system_message_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserSystemMessage",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                var list = data.data.list;
                if (list.length <= 0){
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无消息",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无消息",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li>"+
                        "<a class=\"system_message_title\">"+obj.title+"</a>"+
                            "<a class=\"system_message_content\">"+obj.content+"</a>"+
                        "<a class=\"system_message_time\">"+obj.send_date+"</a>"+
                        "</li>";
                        $("#system_message_ul").append(html);
                    }
                }
            }
            else{
                toast(3,"关闭Loading");
                publicnull_tip(data.msg,1);
            }
        },
    });
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