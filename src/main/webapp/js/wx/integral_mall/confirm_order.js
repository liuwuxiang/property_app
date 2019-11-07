var confirmOrderMain = {

    /**
     * 参数判断
     * @returns {boolean}
     */
    inspectionParameter: function () {
        var userName = $('input[name=username]').val();
        var phone    = $('input[name=phone]').val();
        var address  = $('input[name=address]').val();

        console.log(/^(86)*0*13\d{9} /.test(phone));

        if (userName === undefined || userName === '') {
            mui.alert('收件人不能为空', '提示');
            return false;
        }

        if (!(/^1[34578]\d{9}$/.test(phone))){
            mui.alert('请输入正确的手机号码', '提示');
            return false;
        }

        if (phone === undefined || phone === '') {
            mui.alert('收件人手机号', '提示');
            return false;
        }

        if (address === undefined || address === '') {
            mui.alert('收件地址不能为空', '提示');
            return false;
        }

        return true;

    },
    /**
     * 提交
     */
    addGoodsOrder: function () {

        if (!confirmOrderMain.inspectionParameter()) {
            return;
        }

        $.ajax({
            url: "/property_system/wx/v1.0.0/addGoodsOrder",
            type: "post",
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            data: {
                "user_id" : mMain.storage["user_id"],
                "goodsId": $('input[name=goodsId]').val(),
                "count"   : $('input[name=count]').val(),
                "price"   : $('input[name=price]').val(),
                "username": $('input[name=username]').val(),
                "phone"   : $('input[name=phone]').val(),
                "address" : $('input[name=address]').val(),
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    mui.alert('下单成功', '提示',function () {
                        self.window.location.href = "/property_system/wx/v1.0.0/myIntegral";
                    });
                } else {
                    mui.alert('新增订单出错', '提示',function () {
                        self.window.location.href = "/property_system/wx/v1.0.0/myIntegral";
                    });
                }
            }
        });


    },
    /**
     * 获取必要信息
     */
    getGoodsAndUserById: function () {
        $.ajax({
            url: "/property_system/wx/v1.0.0/getGoodsAndUserById",
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
                if (ret.data.user_integral < ret.data.goods.price) {
                    mui.alert('积分不足', '信息', function () {
                        self.window.location.href = "/property_system/wx/v1.0.0/login";
                    });
                }
                if (ret.status === 0) {
                    $('.mui-card-content > img').attr("src", ret.data.goods.img);
                    $('.mui-card-footer > span').html(ret.data.goods.price);

                    $('input[name=userId]').val(ret.data.user_id);
                    $('input[name=goodsId]').val(ret.data.goods.id);
                    $('input[name=count]').val("1");
                    $('input[name=price]').val(ret.data.goods.price);
                }
            }

        });
    },
    init: function () {
        // MUI 初始化
        mui.init();
        // 获取商品信息和必要的用户信息
        confirmOrderMain.getGoodsAndUserById();
        // 绑定点击确认订单事件
        $('.js-pay').click(confirmOrderMain.addGoodsOrder);
    }
};

confirmOrderMain.init();