var storage = window.localStorage;

//获取我的证书
function getMyCertificate() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#consumption_detail_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserCertificates",
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
                    publicnull_tip("暂无数据",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li onclick=\"lookCertificatePhoto('"+obj.number+"')\">"+
                        "<div class=\"li_left\">"+
                            "<img src=\""+obj.certificate_photo+"\"/>"+
                            "</div>"+
                            "<div class=\"li_right\">"+
                            "<a class=\"certicate_number\">NO."+obj.number+"</a>"+
                        "<a class=\"certificate_time\">"+obj.create_time+"</a>"+
                        "</div>"+
                        "</li>";
                        $("#consumption_detail_ul").append(html);
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
        publicnull_tip.style.display = "block";
    }
}

//查看证书图片
function lookCertificatePhoto(certificate_number) {
    self.window.location.href = "/property_system/wx/v1.0.0/searchMyCertificatePhoto?number="+certificate_number;
}