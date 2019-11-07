var storage = window.localStorage;

//初始化数据
function initData() {
    getAccountBalance();
}

//获取账户余额
function getAccountBalance() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getBalance",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,data.msg);
                document.getElementById("balance_tag").innerHTML = data.data.balance+"元";
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//开发中
function kaifazhong() {
    toast(1,"开发中……");
}

/*
 *	退出登录
 * */
function exitLogin(){
    if(window.confirm('确认退出登录？')){
        exitLoginNetwork();
        return true;
    }else{
        //alert("取消");
        return false;
    }
}

//退出登录网络事件
function exitLoginNetwork() {
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/exitLogin",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                storage.setItem("business_id","");
                storage.setItem("login_session","");
                self.location.href = "/property_system/wnk_business";
            }
            else{
                alert(data.msg);
            }
        },
    });
}