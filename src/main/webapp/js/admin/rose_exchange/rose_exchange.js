function initView() {
    var type = document.getElementById("type").value;
    if (type == 0){
        document.getElementById("exchange_name").innerHTML = "元账户余额";
    }
    else{
        document.getElementById("exchange_name").innerHTML = "个通用积分";
    }
    getSettingData(type);
}

//获取设置数据数据
function getSettingData(type) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getRoseExchangeSetData",
        type:"POST",
        dataType : 'json',
        data:{
            "type":type
        },
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                document.getElementById("exchange_bili").value = data.data.bili;
                document.getElementById("start_day").value = data.data.start_day;
                document.getElementById("end_day").value = data.data.end_day;
                document.getElementById("min_exchange_number").value = data.data.min_number;
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//保存兑换设置信息
function saveSetInformation() {
    var type = document.getElementById("type").value;
    var bili = document.getElementById("exchange_bili").value;
    var start_day = document.getElementById("start_day").value;
    var end_day = document.getElementById("end_day").value;
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
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setRoseExchangeSetData",
            type:"POST",
            dataType : 'json',
            data:{
                "type":type,
                "bili":bili,
                "start_date":start_day,
                "end_date":end_day,
                "min_number":min_number
            },
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    parent.layer.alert(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }

}