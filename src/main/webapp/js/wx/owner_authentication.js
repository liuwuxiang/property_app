var storage = window.localStorage;
/*
 *省份选择(type=0:选择省市,type=1:选择小区,type=2:选择具体房号)
 * */
function chooseAction(type){
	if(type == 0){
        storage["buy_house_mobile"] = document.getElementById("buy_house_mobile").value;
        storage["buy_house_name"] = document.getElementById("buy_house_name").value ;
        self.window.location.href = "/property_system//wx/v1.0.0/joinProvinceChoose";
	}
	else if(type == 1){
        if (storage["city_id"] == undefined || storage["city_id"] == ""){
            toast(1,"请先选择城市");
        }
        else{
            self.window.location.href = "/property_system/wx/v1.0.0/joinResidentialChoose?city_id="+storage["city_id"];
        }

	}
	else{
        if (storage["residential_id"] == undefined || storage["residential_id"] == ""){
            toast(1,"请先选择小区");
        }
        else{
            self.window.location.href = "/property_system/wx/v1.0.0/joinResidentialBuildChoose?residential_id="+storage["residential_id"];
        }

	}
}


//初始化信息
function initData() {
    getOwnerAuthenticationInformation();
}

//获取业主认证信息
function getOwnerAuthenticationInformation() {
    toast(2,"开启loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getOwnerAuthenticationInformation",
        type:"POST",
        dataType : 'json',
        headers: {
            'login_session' : storage["login_session"]
        },
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭Loading");
                settingData(data);

            }
            else{
                toast(1,data.msg);
                settingData(null);
            }
        },
    });
}

//设置信息
function settingData(data) {
    if (data != null){
        if (storage["buy_house_mobile"] == undefined || storage["buy_house_mobile"] == ""){
            storage["buy_house_mobile"] = data.data.buy_house_mobile;
        }
        if (storage["buy_house_name"] == undefined || storage["buy_house_name"] == ""){
            storage["buy_house_name"] = data.data.buy_house_name;
        }
        if (storage["province_name"] == undefined || storage["province_name"] == ""){
            storage["province_name"] = data.data.province_name;
        }
        if (storage["city_name"] == undefined || storage["city_name"] == ""){
            storage["city_name"] = data.data.city_name;
        }
        if (storage["residential_name"] == undefined || storage["residential_name"] == ""){
            storage["residential_name"] = data.data.residential_name;
        }
        if (storage["building_number"] == undefined || storage["building_number"] == ""){
            storage["building_number"] = data.data.building_number;
        }
        if (storage["unit_number"] == undefined || storage["unit_number"] == ""){
            storage["unit_number"] = data.data.unit_number;
        }
        if (storage["house_number"] == undefined || storage["house_number"] == ""){
            storage["house_number"] = data.data.house_number;
        }
        if (storage["city_id"] == undefined || storage["city_id"] == ""){
            storage["city_id"] = data.data.city_id;
        }
        if (storage["province_id"] == undefined || storage["province_id"] == ""){
            storage["province_id"] = data.data.province_id;
        }
        if (storage["house_number_id"] == undefined || storage["house_number_id"] == ""){
            storage["house_number_id"] = data.data.house_number_id;
        }
        if (storage["residential_building_id"] == undefined || storage["residential_building_id"] == ""){
            storage["residential_building_id"] = data.data.residential_building_id;
        }
        if (storage["residential_unit_id"] == undefined || storage["residential_unit_id"] == ""){
            storage["residential_unit_id"] = data.data.residential_unit_id;
        }
        if (storage["residential_id"] == undefined || storage["residential_id"] == ""){
            storage["residential_id"] = data.data.residential_id;
        }

        if(storage["buy_house_mobile"] != undefined && storage["buy_house_mobile"] != ""){
            document.getElementById("buy_house_mobile").value = storage["buy_house_mobile"];
        }
        if (storage["buy_house_name"] != undefined && storage["buy_house_name"] != ""){
            document.getElementById("buy_house_name").value = storage["buy_house_name"];
        }
        if (storage["province_name"] != undefined && storage["province_name"] != "" && storage["city_name"] != undefined && storage["city_name"] != ""){
            document.getElementById("provinceAndCity").innerText = storage["province_name"] + "/" + storage["city_name"];
        }
        if (storage["residential_name"] != undefined && storage["residential_name"] != ""){
            document.getElementById("residential_name").innerText = storage["residential_name"];
        }
        if (storage["building_number"] != undefined && storage["building_number"] != "" && storage["unit_number"] != undefined && storage["unit_number"] != "" && storage["house_number"] != undefined && storage["house_number"] != ""){
            document.getElementById("buildingAndUnitAndHouse").innerText = storage["building_number"] + "/" + storage["unit_number"] + "/" + storage["house_number"];
        }
    }
    else{
        if(storage["buy_house_mobile"] != undefined && storage["buy_house_mobile"] != ""){
            document.getElementById("buy_house_mobile").value = storage["buy_house_mobile"];
        }
        if (storage["buy_house_name"] != undefined && storage["buy_house_name"] != ""){
            document.getElementById("buy_house_name").value = storage["buy_house_name"];
        }
        if (storage["province_name"] != undefined && storage["province_name"] != "" && storage["city_name"] != undefined && storage["city_name"] != ""){
            document.getElementById("provinceAndCity").innerText = storage["province_name"] + "/" + storage["city_name"];
        }
        if (storage["residential_name"] != undefined && storage["residential_name"] != ""){
            document.getElementById("residential_name").innerText = storage["residential_name"];
        }
        if (storage["building_number"] != undefined && storage["building_number"] != "" && storage["unit_number"] != undefined && storage["unit_number"] != "" && storage["house_number"] != undefined && storage["house_number"] != ""){
            document.getElementById("buildingAndUnitAndHouse").innerText = storage["building_number"] + "/" + storage["unit_number"] + "/" + storage["house_number"];
        }
    }
}

//业主认证信息提交
function submitAuthentication() {
    var buy_house_mobile = document.getElementById("buy_house_mobile").value;
    var buy_house_name = document.getElementById("buy_house_name").value;
    if (buy_house_mobile == undefined || buy_house_mobile == ""){
        toast(1,"请输入购房所留电话");
    }
    else if (buy_house_name == undefined || buy_house_name == ""){
        toast(1,"请输入购房姓名");
    }
    else if (storage["province_id"] == undefined || storage["province_id"] == "" || storage["city_id"] == undefined || storage["city_id"] == ""){
        toast(1,"请选择城市");
    }
    else if (storage["residential_id"] == undefined || storage["residential_id"] == ""){
        toast(1,"请选择小区");
    }
    else if (storage["residential_building_id"] == undefined && storage["residential_building_id"] == "" && storage["residential_unit_id"] == undefined && storage["residential_unit_id"] == "" && storage["house_number_id"] == undefined && storage["house_number_id"] == ""){
        toast(1,"请选择房间号");
    }
    else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/app/v1.0.0/submitOwnerAuthentication",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"mobile":buy_house_mobile,"name":buy_house_name,"residential_id":storage["residential_id"],"house_id":storage["house_number_id"],"user_id":storage["user_id"]},
            success:function(data){
                if (data.status == 0){
                    toast(0,"提交成功");
                        storage["buy_house_mobile"] = "";
                        storage["buy_house_name"] = "";
                        storage["province_name"] = "";
                        storage["city_name"] = "";
                        storage["residential_name"] = "";
                        storage["building_number"] = "";
                        storage["unit_number"] = "";
                        storage["house_number"] = "";
                        storage["city_id"] = "";
                        storage["province_id"] = "";
                        storage["house_number_id"] = "";
                        storage["residential_building_id"] = "";
                        storage["residential_unit_id"] = "";
                        storage["residential_id"] = "";
                    window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}