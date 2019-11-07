function initView() {
    getSettingData();
}

//获取设置数据数据
function getSettingData() {
    // 发送请求
    $.ajax({
        url: "/property_system/admin/adminSelectWithdrawSetting",
        type: "POST",
        dataType: 'json',
        data: {
            withdraw_type : '2'
        },
        success: function (data) {
            console.log(data);
            if (parseInt(data.status) === 0 && data.data != null || data.data !== undefined){
                document.getElementById("exchange_bili").value = data.data.withdraw_proportion;

                document.getElementById("start_day").value = data.data.withdraw_start_time;

                document.getElementById("end_day").value = data.data.withdraw_end_time;

                document.getElementById("min_exchange_number").value = data.data.min_number;
            }
        }
    });
}

//保存兑换设置信息
function saveSetInformation() {

    // 兑换比例
    var bili       = document.getElementById("exchange_bili").value;
    // 开始时间
    var start_day  = document.getElementById("start_day").value;
    // 结束时间
    var end_day    = document.getElementById("end_day").value;
    // 最低兑换数量
    var min_number = document.getElementById("min_exchange_number").value;

    if (bili == undefined || bili == ""){
        layer.msg('请输入兑换比例', {icon: 5});
    }
    else if (bili <= 0){
        layer.msg('兑换比例不可小于等于0', {icon: 5});
    }
    else if (start_day == undefined || start_day == ""){
        layer.msg('请输入兑换开始时间!', {icon: 5});
    }
    else if (parseInt(start_day) < 1){
        layer.msg('兑换开始时间不可小于1', {icon: 5});
    }
    else if (end_day == undefined || end_day == ""){
        layer.msg('请输入兑换结束时间', {icon: 5});
    }
    else if (parseInt(end_day) > 30){
        layer.msg('兑换结束时间不可大于30', {icon: 5});
    }
    else if (min_number == undefined || min_number == ""){
        layer.msg('请输入允许最低兑换量', {icon: 5});
    }
    else if (parseInt(min_number) <= 0){
        layer.msg('最低兑换量不可小于等于0', {icon: 5});
    }
    else{
        // 发送请求
        $.ajax({
            url: "/property_system/admin/adminUpdateWithdrawSetting",
            type: "POST",
            dataType: 'json',
            data: {
                withdraw_type            : '2',
                service_charge_proportion: '0',
                is_any_time              : '0',
                withdraw_start_time      : start_day,
                withdraw_end_time        : end_day,
                withdraw_proportion      : bili,
                min_number               : min_number
            },
            success: function (data) {
                layer.open({
                    content: data.msg,
                    // btn: ['确定'],
                    // yes: function (index, layero) {
                    //     location.reload();
                    //     return true;
                    // }
                });
            }
        });
    }

}