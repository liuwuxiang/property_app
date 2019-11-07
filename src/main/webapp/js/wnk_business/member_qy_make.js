var storage = window.localStorage;

var user_id = -1;
//初始化数据
function initData(current_user_id) {
    user_id = current_user_id;
    getUserBaseInformation();
}

//获取用户基础信息
function getUserBaseInformation() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/wnkBusinessBaseInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"user_id":user_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("nick_name").innerText = "会员名称："+data.data.nick_name;
                document.getElementById("member_sex").innerText = "会员性别："+data.data.sex;
                document.getElementById("member_card_level").innerText = "卡片等级："+data.data.member_card;
                document.getElementById("member_star").innerText = "用户星级："+data.data.member_star+"星用户";
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取验证码
function getCode() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/memberQYMakeGetMobileCode",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"user_id":user_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                alert(data.msg);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//会员权益使用校验
function memberQYMakeCheck() {
    var user_code = document.getElementById("user_code").value;
    var make_number = document.getElementById("make_number").value;
    if (user_code == undefined || user_code == ""){
        toast(1,"请输入会员验证码");
    }
    else if (make_number == undefined || make_number == ""){
        toast(1,"请输入使用次数");
    }
    else if (make_number <= 0){
        toast(1,"使用次数不可小于等于0");
    }
    else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/memberQYQrCodeMake",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"business_id":storage["business_id"],"user_id":user_id,"code":user_code,"make_number":make_number},
            success:function(data){
                toast(3,"关闭loading");
                alert(data.msg);
            },
        });
    }

}