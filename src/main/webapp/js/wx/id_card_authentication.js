var storage = window.localStorage;
var id_card_photo = "";
/*
 *	图像选择事件
 * */
function imgChoose(){
	document.getElementById("header_file").click();
}

//初始化数据
function initData() {
    getIdCardAuthenticationInformation();
}

//获取认证信息
function getIdCardAuthenticationInformation() {
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/getIdCardAuthenticationInformation",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"]},
            success:function(data){
                if (data.status == 0){
                    toast(3,"关闭Loading");
                    id_card_photo = data.data.handheld_identity_card_photo_id;
                    document.getElementById("mobile").value = data.data.mobile;
                    document.getElementById("real_name").value = data.data.real_name;
                    document.getElementById("id_card_number").value = data.data.id_card_number;
                    // var card_effective_deadline = data.data.card_effective_deadline;
                    // document.getElementById("selectDatetime").value = card_effective_deadline;
                    // setPhoto(data.data.handheld_identity_card_photo_url);
                }
                else{
                    toast(3,"关闭Loading");
                }
            },
        });
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
                id_card_photo = data.url;
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
    var html = "<p class=\"loadimgitem\" id=\"loadimgitem\"><img src=\""+photo_url+"\" alt=\"\"><span class=\"delte\"></span></p>";
    var buttonHtml = "<label class=\"floadimgbut\" id=\"floadimgbut\" onclick=\"imgChoose()\"></label>";
    $("#floadimgbut").remove();
    $("#loadimgitem").remove();
    $("#fuploadimg").append(html);
    $("#fuploadimg").append(buttonHtml);
}

//提交认证
function submitAuthentication() {
    var mobile = document.getElementById("mobile").value;
    var real_name = document.getElementById("real_name").value;
    var id_card_number = document.getElementById("id_card_number").value;
    // var selectDatetime = document.getElementById("selectDatetime").value;
    if (mobile == undefined || mobile == ""){
        toast(0,"请填写手机号");
	}
	else if (real_name == undefined || real_name == ""){
        toast(0,"请填写真实姓名");
    }
    else if (id_card_number == undefined || id_card_number == ""){
        toast(0,"请填写身份证号码");
    }
    // else if (selectDatetime == undefined || selectDatetime == ""){
    //     toast(0,"请选择身份证有效截止日期");
    // }
    // else if (id_card_photo == undefined || id_card_photo == ""){
    //     toast(0,"请上传手持身份证正面照片");
    // }
    else{
        toast(2,"开启loading");
        // selectDatetime = selectDatetime.replace(/-/g, '/');
        $.ajax({
            url:"/property_system/app/v1.0.0/submitIdCardAuthentication",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"],"mobile":mobile,"real_name":real_name,"id_card_number":id_card_number,"handheld_identity_card_photo_id":"","card_effective_deadline":""},
            success:function(data){
                if (data.status == 0){
                    toast(0,"提交成功");
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }

}