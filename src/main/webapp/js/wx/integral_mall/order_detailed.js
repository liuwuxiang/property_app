var orderDetailedMain = {

    getOrderInfoById: function () {
        $.ajax({
            url: "/property_system/wx/v1.0.0/getOrderInfoById",
            type: "post",
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            data: {
                "user_id": mMain.storage["user_id"],
                "id": $('#id').val()
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    $('.mui-card-content > img').attr('src', ret.data.img);
                    $('.mui-card-footer > span').html(ret.data.price);

                    $('#order_id').html(ret.data.order_id);
                    switch (ret.data.status) {
                        case 0:
                            $('#status').html("已付款");
                            break;
                        case 1:
                            $('#status').html("已发货");
                            break;
                        case 2:
                            $('#status').html("交易完成");
                            break;
                    }

                    $('#express_name').html(ret.data.express_name);
                    $('#express_id').html(ret.data.express_id);
                    $('#username').html(ret.data.username);
                    $('#phone').html(ret.data.phone);
                    $('#address').html(ret.data.address);
                }

            }

        });
    },
    init: function () {

        orderDetailedMain.getOrderInfoById();
    }
};

orderDetailedMain.init();