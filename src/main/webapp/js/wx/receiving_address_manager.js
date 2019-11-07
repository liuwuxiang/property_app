var storage = window.localStorage;
/*
 *省份选择
 * */
function chooseAction(){
    storage["address_name"] = document.getElementById("name").value;
    storage["mobile"] = document.getElementById("mobile").value;
    storage["detail_address"] = document.getElementById("detail_address").value;
	self.window.location.href = "/property_system/wx/v1.0.0/joinProvinceChoose";
    // self.window.open("/property_system/wx/v1.0.0/joinProvinceChoose");
}

//初始化数据
function initData() {
    getUsersAddressInformation();
}

//获取用户收货地址信息
function getUsersAddressInformation() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserReceivingAddress",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                initXSContent(data);
            }
            else{
                toast(3,"关闭loading");
                initXSContent(null);
            }
        },
    });
}

//初始化显示内容
function initXSContent(data) {
    if (data != null){
        if (storage["address_name"] == undefined || storage["address_name"] == ""){
            storage["address_name"] = data.data.name;
        }
        if (storage["mobile"] == undefined || storage["mobile"] == ""){
            storage["mobile"] = data.data.mobile;
        }
        if (storage["detail_address"] == undefined || storage["detail_address"] == ""){
            storage["detail_address"] = data.data.detailed_address;
        }
        if (storage["province_name"] == undefined || storage["province_name"] == ""){
            storage["province_name"] = data.data.province_name;
        }
        if (storage["city_name"] == undefined || storage["city_name"] == ""){
            storage["city_name"] = data.data.city_name;
        }
        if (storage["city_id"] == undefined || storage["city_id"] == ""){
            storage["city_id"] = data.data.city_id;
        }
        if (storage["province_id"] == undefined || storage["province_id"] == ""){
            storage["province_id"] = data.data.province_id;
        }
        document.getElementById("name").value = storage["address_name"];
        document.getElementById("mobile").value = storage["mobile"];
        document.getElementById("provinAndCity").innerText = storage["province_name"]+"/"+storage["city_name"];
        document.getElementById("detail_address").value = storage["detail_address"];
    }
    else{
        document.getElementById("name").value = storage["address_name"];
        document.getElementById("mobile").value = storage["mobile"];
        if (storage["province_name"] != undefined && storage["province_name"] != "" && storage["city_name"] != undefined && storage["city_name"] != ""){
            document.getElementById("provinAndCity").innerText = storage["province_name"]+"/"+storage["city_name"];
        }
        document.getElementById("detail_address").value = storage["detail_address"];
    }
}

//提交信息
function submitInformation() {
    var name = document.getElementById("name").value;
    var mobile = document.getElementById("mobile").value;
    var detail_address = document.getElementById("detail_address").value;
    if (name == undefined || name == ""){
        toast(1,"请收入收货人姓名");
    }
    else if (mobile == undefined || mobile == ""){
        toast(1,"请收入收货人电话");
    }
    else if (storage["province_id"] == undefined || storage["province_id"] == "" || storage["city_id"] == undefined || storage["city_id"] == ""){
        toast(1,"请选择收货省份及城市");
    }
    else if (detail_address == undefined || detail_address == ""){
        toast(1,"请收入详细地址");
    }
    else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/setUserReceivingAddress",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"user_id":storage["user_id"],"name":name,"mobile":mobile,"province_id":storage["province_id"],"city_id":storage["city_id"],"detailed_address":detail_address},
            success:function(data){
                if (data.status == 0){
                    toast(0,"设置成功");
                    storage["province_name"] = "";
                    storage["city_name"] = "";
                    storage["city_id"] = "";
                    storage["province_id"] = "";
                    storage["address_name"] = "";
                    storage["mobile"] = "";
                    storage["detail_address"] = "";
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}