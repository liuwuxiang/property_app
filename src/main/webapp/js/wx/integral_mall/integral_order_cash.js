$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

// 初始化页面数据
function load() {
    $.ajax({
        url: "/property_system/wx/v1.0.0/getMyIntegralDetail",
        type: "POST",
        headers: {'login_session': mMain.storage["login_session"]},
        dataType: 'json',
        data: {"user_id": mMain.storage["user_id"]},
        beforeSend: function (request) {
            // 设置请求头
            request.setRequestHeader("login_session", mMain.storage["login_session"]);
        },
        success: function (data) {
            console.log(data);
            if (data.status === 0){
                // data.userIntegral
                $('#current_general_integral').html("当前可用"+data.data.userIntegral+"个平台积分");
            }
        }

    });
}

//提现申请
function submitWithdraw() {
    var withdraw_number = document.getElementById("withdraw_number").value;

    if (withdraw_number === undefined || withdraw_number === "") {
        toast(1, "请输入提现积分个数");
    } else if (parseInt(withdraw_number) % 100 !== 0) {
        toast(1, "提现积分必须为100的倍数");
    } else {
        $.prompt({
            text: "请填写支付密码进行验证",
            title: "支付密码",
            onOK: function (text) {
                if (text == undefined || text == "") {
                    toast(1, "请输入支付密码");
                }
                else {
                    Action(mMain.storage["user_id"], text , withdraw_number);
                }
            },
            onCancel: function () {

            },
            input: ''
        });
    }
}


//提现网络事件
function Action(user_id, user_pay_pwd,withdraw_number) {
    toast(2, "打开loading");
    $.ajax({
        url: "/property_system/wx/v1.0.0/IntegralOrderCash",
        type: "POST",
        headers: {'login_session': mMain.storage["login_session"]},
        dataType: 'json',
        data: {
            "withdraw_number": withdraw_number,
            "user_id"        : user_id,
            "user_pay_pwd"   : user_pay_pwd
        },
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
                toast(1, data.msg);
                self.window.history.go(-1);
            }
            else {
                toast(1, data.msg);
            }
        },
    });
}
