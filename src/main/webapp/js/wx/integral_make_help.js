var storage = window.localStorage;
//使用类型(0-消费积分,1-通用积分)
var makeType = 0;
/*
 *	列表项初始化
 * type=0:如何使用，type=1：如何获得，type=2：扣减规则
 * */
function listOptionInit(type){
	if(type == 0){
		document.getElementById("rhsy_item").setAttribute("class","item sel"); 
		document.getElementById("rhhd_item").setAttribute("class","item"); 
		document.getElementById("rhkj_item").setAttribute("class","item");
	}
	else if(type == 1){
		document.getElementById("rhsy_item").setAttribute("class","item"); 
		document.getElementById("rhhd_item").setAttribute("class","item sel"); 
		document.getElementById("rhkj_item").setAttribute("class","item"); 
	}
	else{
		document.getElementById("rhsy_item").setAttribute("class","item"); 
		document.getElementById("rhhd_item").setAttribute("class","item"); 
		document.getElementById("rhkj_item").setAttribute("class","item sel"); 
	}
    getMakeHelp(type);
}

/*
* 数据初始化
* */
function initData(type) {
	makeType = type;
    listOptionInit(0);
}

//获取积分使用说明
function getMakeHelp(getType) {
	if (makeType == 0){
		if (getType == 0){
            getType = 1;
		}
		else if (getType == 0){
            getType = 2;
        }
        else{
            getType = 3;
		}
	}
	else{
        if (getType == 0){
            getType = 4;
        }
        else if (getType == 0){
            getType = 5;
        }
        else{
            getType = 6;
        }
	}
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getIntegralAbout",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"type":getType},
        success:function(data){
            if (data.status == 0){
                toast(0,"获取成功");
                var content = data.data.content;
                $("#contentInsert").html(content);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}