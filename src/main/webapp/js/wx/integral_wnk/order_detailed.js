var WnkOrderDetailed = {
    storage:window.localStorage,
    getOrderInfoById: function () {
        jQuery.ajax({
            url: "/property_system/wx/v1.0.0/getIntegralWnkOrderByGoodsId",
            type: "post",
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("login_session", WnkOrderDetailed.storage["login_session"]);
            },
            data: {
                "user_id"    : WnkOrderDetailed.storage["user_id"],
                "business_id": jQuery('#business_id').val(),
                'order_id'   : jQuery('#order_id').val()
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    jQuery('#goods_img').attr('src', ret.data.goodsImgPath+ret.data.img);
                    jQuery('#goods_price').text(ret.data.price);

                    jQuery('#order_id').html(ret.data.order_id);

                    jQuery('#username').html(ret.data.username);
                    jQuery('#phone').html(ret.data.phone);
                    switch (ret.data.status) {
                        case 0:
                            jQuery('#status').html("已支付");
                            break;
                        case 1:
                            jQuery('#status').html("已完成");
                            break;
                    }

                    jQuery('#order_qrcode').attr('src', ret.data.qrcodeShowURL+ret.data.qrcode)

                }
            }

        });
    },
    init: function () {
        // 避免含义冲突
        $.noConflict();
        // 初始化mui
        mui.init();
        // 获取订单详情
        WnkOrderDetailed.getOrderInfoById();
    }
};

WnkOrderDetailed.init();