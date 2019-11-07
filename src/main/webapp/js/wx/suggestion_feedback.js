var storage = window.localStorage;
var uploadImageId = "";
/*
 *	图像选择事件
 * */
function imgChoose(){
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
                uploadImageId = uploadImageId + ","+data.url;
                setPhoto(data.url_location);
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

//设置图片
function setPhoto(photo_url) {
	var html = "<p class=\"loadimgitem\"><img src=\""+photo_url+"\" alt=\"\"><span class=\"delte\"></span></p>";
    var buttonHtml = "<label class=\"floadimgbut\" id=\"floadimgbut\" onclick=\"imgChoose()\"></label>";
    $("#floadimgbut").remove();
    $("#fuploadimg").append(html);
    $("#fuploadimg").append(buttonHtml);
}

//建议反馈
function feedback() {
	var ftextarea = document.getElementById("ftextarea").value;
	if (ftextarea == undefined || ftextarea == ""){
        toast(0,"请填写反馈内容");
	}
	else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/userFeedBack",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"],"content":ftextarea,"photos":uploadImageId},
            success:function(data){
                if (data.status == 0){
                    toast(0,"反馈完成");
                    storage["message"] = "反馈完成";
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
	}

}