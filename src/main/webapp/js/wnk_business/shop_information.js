var storage = window.localStorage;

//当前图片index值
var currentPhotoIndex = -1;

//删除轮播图li标签
function deletePhotoLi(index){
	$("#photo"+index).remove();
}

//初始化数据
function initData() {
    getInformationData();
}

//获取店铺数据
function getInformationData() {
    toast(2,"打开loading");
    $("#lunbo_photo_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getStoreInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("store_name").value = data.data.name;
                document.getElementById("address").value = data.data.address;
                document.getElementById("mobile").value = data.data.mobile;
                document.getElementById("store_describe").value = data.data.store_describe;
                var list = data.data.photos;
                for (var index = 0;index < list.length; index++){
                    currentPhotoIndex = index;
                    var obj = list[index];
                    var html = "<li id=\"photo"+index+"\">"+
                        "<img src=\""+obj+"\" class=\"photo_img\"/>"+
                        "<img src=\"/property_system/images/wnk_business/delete_icon.png\" class=\"delete_button\" onclick=\"deletePhotoLi("+index+")\"/>"+
                        "</li>";
                    $("#lunbo_photo_ul").append(html);
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}


//修改店铺数据
function setInformationData() {
    var name = document.getElementById("store_name").value;
    var address = document.getElementById("address").value;
    var mobile = document.getElementById("mobile").value;
    var store_describe = document.getElementById("store_describe").value;
    var photos = document.getElementsByClassName("photo_img");
    if (name == "" || name == undefined){
        toast(1,"请输入店铺名称");
    }
    else if (address == "" || address == undefined){
        toast(1,"请输入店铺地址");
	}
	else if (mobile == "" || mobile == undefined){
        toast(1,"请输入店铺联系电话");
	}
	else if (store_describe == "" || store_describe == undefined){
        toast(1,"请输入店铺描述");
	}
	else if (photos.length <= 0){
        toast(1,"请上传店铺滚动图");
	}
	else{
		var photoIds = "";
        for (var index = 0;index < photos.length;index++){
            var url = photos[index].src;
            var photoId = url.split("imageid=")[1];
            if (index != photos.length - 1){
            	photoIds = photoIds + photoId + ",";
			}
			else{
                photoIds = photoIds + photoId;
			}
		}

        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/setStoreInformation",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"business_id":storage["business_id"],"store_name":name,"address":address,"mobile":mobile,"store_describe":store_describe,"photo_ids":photoIds},
            success:function(data){
                toast(1,data.msg);
                if (data.status == 0){
                	self.window.history.go(-1);
				}
            },
        });

	}
}

//图片选择
function choosePhoto() {
    document.getElementById("header_file").click();
}

function chooseHeaderChangeFile() {
    toast(2,"开启loading");
    $.ajaxFileUpload({
        url : '/property_system/images/savaimageMobile.do', // 用于文件上传的服务器端请求地址
        secureuri : false, // 是否需要安全协议，一般设置为false
        fileElementId : 'header_file', // 文件上传域的ID
        dataType : 'json', // 返回值类型 一般设置为json
        type : "post",
        data:{"fileNameStr":"ajaxFile","fileId":"header_file"},
        success : function(data, status) // 服务器成功响应处理函数
        {
            if (data.error == 0){
                toast(3,"关闭loading");
                currentPhotoIndex++;
                var html = "<li id=\"photo"+currentPhotoIndex+"\">"+
                    "<img src=\""+data.url_location+"\" class=\"photo_img\"/>"+
                    "<img src=\"/property_system/images/wnk_business/delete_icon.png\" class=\"delete_button\" onclick=\"deletePhotoLi("+currentPhotoIndex+")\"/>"+
                    "</li>";
                $("#lunbo_photo_ul").append(html);
            }
            else{
                toast(1,data.message);
            }
        },
        error : function(data, status, e)// 服务器响应失败处理函数
        {
            toast(3,"关闭loading");
            alert(e);
        }
    });
}