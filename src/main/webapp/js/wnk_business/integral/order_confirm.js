/**
 * 初始化
 */
function webLoad() {
    toast(2, "打开loading");
    $.ajax({
        url: "/property_system/wnk_business/getIntegralOrderByOrderId",
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "business_id": storage["business_id"],
            "order_no": $('#order_id').val(),
            "user_id": $('#user_id').val()
        },
        success: function (data) {
            toast(3, "关闭loading");
            console.log(data);
            if (parseInt(data.status) === 0) {
                $('#goods_name')[0].innerText = data.data.goods_name;
                $('#creation_time')[0].innerText = data.data.creation_time;
                $('#orderId')[0].innerText = data.data.order_id;
                $('#price')[0].innerText = data.data.price + '积分';
                $('#phone')[0].innerText = data.data.phone;
                $('#status')[0].innerText = data.data.status;
                $('#username')[0].innerText = data.data.username;
                $('#end_time')[0].innerText = data.data.end_time === undefined ? '' : data.data.end_time;

                var submitBtn = $('.weui-form-preview__btn_primary').css('display', 'none');
                if (data.data.status === '已完成') {
                    submitBtn.css('display', 'none');
                } else {
                    submitBtn.css('display', 'inline');
                }
            } else {
                toast(1, data.msg);
            }
        }
    });
}

/**
 * 使用订单操作
 */
function integralOrderUse() {

    $.confirm("确认发货?", '信息:', function () {
        toast(2, "打开loading");
        $.ajax({
            url: "/property_system/wnk_business/integralOrderUse",
            type: "POST",
            headers: {
                'login_session': storage["login_session"]
            },
            dataType: 'json',
            data: {
                "business_id": storage["business_id"],
                "order_id": $('#order_id').val(),
                "user_id": $('#user_id').val()
            },
            success: function (data) {
                toast(3, "关闭loading");
                console.log(data);
                $.alert(data.msg, "消息:", function () {
                    window.location.reload();
                });
            }

        });
    });
}