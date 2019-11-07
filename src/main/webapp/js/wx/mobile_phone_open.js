//跳转至新页面(type=0:密码开门,type=1:二维码开门,type=2:人脸识别,type=3:智能卡片,type=4:收入房号,type=5:授权管理,type=6:呼叫转移)
function jumpNewPage(type) {
    if (type == 0){
        self.window.location.href = "/property_system/wx/v1.0.0/joinPasswordOpenDoor";
    }
    else if (type == 1){
        self.window.location.href = "/property_system/wx/v1.0.0/joinPasswordOpenDoor";
    }
    else if (type == 2){

    }
    else if (type == 3){
        self.window.location.href = "/property_system/wx/v1.0.0/joinSmartCard";
    }
    else if (type == 4){
        self.window.location.href = "/property_system/wx/v1.0.0/joinIncomeHouseNumber";
    }
    else if (type == 5){
        self.window.location.href = "/property_system/wx/v1.0.0/joinAuthorizationManagement";
    }
    else if (type == 6){
        self.window.location.href = "/property_system/wx/v1.0.0/joinCallTransfer";
    }
}