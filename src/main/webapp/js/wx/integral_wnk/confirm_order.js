var wnkConfirmOrder = {
    storage     : window.localStorage,
    business_id : jQuery('#business_id').val(),
    goods_id    : jQuery('#goods_id').val(),
    /**
     * 参数判断
     * @returns {boolean}
     */
    inspectionParameter: function () {
        var userName = jQuery('input[name=username]').val();
        var phone    = jQuery('input[name=phone]').val();


        if (userName === undefined || userName === '') {
            mui.alert('联系人不能为空', '提示');
            return false;
        }

        if (!(/^1[34578]\d{9}$/.test(phone))){
            mui.alert('请输入正确的手机号码', '提示');
            return false;
        }

        if (phone === undefined || phone === '') {
            mui.alert('联系人手机号不能为空', '提示');
            return false;
        }

        return true;

    },
    /**
     * 提交
     */
    addGoodsOrder: function () {

        if (!wnkConfirmOrder.inspectionParameter()) {
            return;
        }

        jQuery.ajax({
            url: "/property_system/wx/v1.0.0/addGoodsOrderWnk",
            type: "post",
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            data: {
                "user_id"     : mMain.storage["user_id"],
                "goods_id"     : jQuery('#goods_id').val(),
                "price"       : jQuery('input[name=price]').val(),
                "username"    : jQuery('input[name=username]').val(),
                "phone"       : jQuery('input[name=phone]').val(),
                "business_id" : wnkConfirmOrder.business_id
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    mui.alert('下单成功', '提示',function () {
                        self.window.location.href = "/property_system/wx/v1.0.0/joinWnkIntegralIndex?business_id="+wnkConfirmOrder.business_id;
                    });
                } else {
                    mui.alert('新增订单出错', '提示',function () {
                        self.window.location.href = "/property_system/wx/v1.0.0/joinWnkIntegralIndex?business_id="+wnkConfirmOrder.business_id;
                    });
                }
            }
        });


    },
    /**
     * 获取必要信息
     */
    getGoodsAndUserById: function () {
        jQuery.ajax({
            url: "/property_system/wx/v1.0.0/getIntegralByIdAndWnk",
            type: "post",
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            data: {
                "user_id"    : mMain.storage["user_id"],
                "business_id": wnkConfirmOrder.business_id,
                "goods_id"   : wnkConfirmOrder.goods_id
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    jQuery('.mui-card-content > img').attr("src", ret.data.imgPath + ret.data.img);
                    jQuery('.mui-card-footer > span').html(ret.data.price);

                    jQuery('input[name=userId]').val(wnkConfirmOrder.storage["user_id"]);
                    jQuery('input[name=goodsId]').val(ret.data.id);
                    jQuery('input[name=price]').val(ret.data.price);
                } else {
                    mui.alert(ret.msg,'提示');
                }
            }

        });
    },
    init: function () {
        // 避免含义冲突
        $.noConflict();
        // MUI 初始化
        mui.init();
        // 获取商品信息和必要的用户信息
        wnkConfirmOrder.getGoodsAndUserById();
        // 绑定点击确认订单事件
        jQuery('.js-pay').click(wnkConfirmOrder.addGoodsOrder);
    }
};

wnkConfirmOrder.init();