var storage = window.localStorage;
//银行名称
var bankNames = new Array();
//银行id
var bankIds = new Array();
//当前选中的银行id
var chooseBankId = -1;

//初始化数据
function initData(bank_id,bank_name,bank_card_number,real_name) {
    chooseBankId = bank_id;
    if (bank_id != -1){
        document.getElementById("selectBank").value = bank_name;
        document.getElementById("bank_card_number").value = bank_card_number;
        document.getElementById("real_name").value = real_name;
    }
    //获取支持的提现银行
    getSuppertBank();
}

//获取支持的提现银行
function getSuppertBank() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getAllWithdrawSupportBank",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var banks = data.data.list;
                for(var index = 0;index < banks.length;index++){
                    var obj = banks[index];
                    bankNames.push(obj.name);
                    bankIds.push(obj.bank_id);
                }
                initBankList();
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//初始化银行选择列表
function initBankList() {
    $("#selectBank").picker({
        title: "请选择开户行",
        cols: [
            {
                textAlign: 'center',
                values: bankNames
            }
        ],
        onChange: function(p, v, dv) {
            for (var index = 0;index < bankNames.length;index++){
                var text = bankNames[index];
                if (text == dv){
                    chooseBankId = bankIds[index];
                    return;
                }
            }
        },
        onClose: function(p, v, d) {

        }
    })
}

//设置银行卡信息
function settingBankCard() {
    var bank_card_number = document.getElementById("bank_card_number").value;
    var real_name = document.getElementById("real_name").value;
    if(chooseBankId == -1){
        toast(1,"请选择银行");
    }
    else if (bank_card_number == undefined || bank_card_number == ""){
        toast(1,"请输入银行卡号");
    }
    else if (real_name == undefined || real_name == ""){
        toast(1,"请输入卡户姓名");
    }
    else{
        toast(2,"开启loading");
        $.ajax({
            url:"/property_system/wx/v1.0.0/setUserBankCardMessage",
            type:"POST",
            dataType : 'json',
            headers: {
                'login_session' : storage["login_session"]
            },
            data:{"user_id":storage["user_id"],"bank_id":chooseBankId,"bank_card_number":bank_card_number,"real_name":real_name},
            success:function(data){
                if (data.status == 0){
                    toast(1,data.msg);
                    self.window.history.go(-1);
                }
                else{
                    toast(1,data.msg);
                }
            },
        });
    }
}

//银行卡号输入监听
function bankCardNumberinputChange(bank_card_number) {
    if (bank_card_number.length > 19){
        document.getElementById("bank_card_number").value = bank_card_number.slice(0,19);
    }
}