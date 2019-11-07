// 初始化
function init() {

    selectIntegralExchangeInfo();

}

// 获取已经保存的兑换信息
function selectIntegralExchangeInfo() {
    $.ajax({
        url:"/property_system/admin/selectIntegralExchangeInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            console.log(data);
            if (parseInt(data.status) === 0){
                // 获取参数
                $('#exchange_bili').val(data.data.exchange_bili.content);

                var start_time = data.data.exchange_start_time.content.split('-');

                $('#exchange_start_time_month').val(start_time[0]);
                $('#exchange_start_time_day').val(start_time[1]);

                var shop_time = data.data.exchange_shop_time.content.split('-');

                $('#exchange_stop_time_month').val(shop_time[0]);
                $('#exchange_stop_time_day').val(shop_time[1]);

                $('#min_exchange_number').val(data.data.min_exchange_number.content);
            }
        }
    });
}

// 保存兑换信息
function saveSetInformation() {

    // 获取参数
    var exchange_bili = $('#exchange_bili').val();

    var exchange_start_time = $('#exchange_start_time_month').val() + '-' + $('#exchange_start_time_day').val();

    var exchange_shop_time = $('#exchange_stop_time_month').val() + '-' + $('#exchange_stop_time_day').val();

    var min_exchange_number = $('#min_exchange_number').val();

    // 参数检查

    if (exchange_bili == '' || exchange_bili == undefined || exchange_bili == null) {
        layer.msg('请输入兑换比例!', {icon: 5});
        return;
    }

    if (exchange_start_time == '-' || exchange_start_time == undefined || exchange_start_time == null) {
        layer.msg('请输入开放时间!', {icon: 5});
        return;
    }

    if (exchange_shop_time == '-' || exchange_shop_time == undefined || exchange_shop_time == null) {
        layer.msg('请输入结束时间!', {icon: 5});
        return;
    }

    if (min_exchange_number == '' || min_exchange_number == undefined || min_exchange_number == null) {
        layer.msg('请输入最低兑换数量!', {icon: 5});
        return;
    }

    // 发送请求进行保存
    // 请求参数整合
    var data = {
        exchange_bili:exchange_bili,
        exchange_start_time:exchange_start_time,
        exchange_shop_time:exchange_shop_time,
        min_exchange_number:min_exchange_number
    };

    $.ajax({
        url:"/property_system/admin/updateIntegralExchangeInfo",
        type:"POST",
        dataType : 'json',
        data:data,
        success:function(data){
            console.log(data);
            layer.msg(data.msg, {icon: 1});
        }
    });



}