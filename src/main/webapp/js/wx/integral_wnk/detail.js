var wnkPage = {
    storage    : window.localStorage,
    business_id: jQuery('#business_id').val(),
    goods_id   : jQuery('#goods_id').val(),
    getIntegralUser : function(){
        jQuery.ajax({
            url       : '/property_system/wx/v1.0.0/getUserIntegral',
            type      : 'post',
            dataType  : 'json',
            beforeSend: function (request) {
                request.setRequestHeader('login_session', wnkPage.storage['login_session']);
            },
            data: {
                'user_id'    : wnkPage.storage['user_id'],
                'business_id': wnkPage.business_id
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    if (parseInt(ret.data.integral) <= parseInt(jQuery(".text-highlight").text())) {
                        jQuery('.pay-bar-cell1 > div').empty().html("积分不足");
                        jQuery('.pay-bar-cell1 > div').addClass(" disabled");
                    } else {
                        jQuery('.pay-bar-cell1 > div').empty().html("立即兑换");
                        jQuery('.pay-bar-cell1 > div').removeClass(" disabled");
                        jQuery('#pay-bar').click(function () {
                            self.window.location.href = "/property_system/wx/v1.0.0/joinGoodsChangeWnk?goods_id=" + wnkPage.goods_id+'&business_id='+wnkPage.business_id;
                        })
                    }
                }
            }
        });
    },
    getGoodsById: function () {

        jQuery.ajax({
            url       : '/property_system/wx/v1.0.0/getIntegralByIdAndWnk',
            type      : 'post',
            dataType  : 'json',
            beforeSend: function (request) {
                request.setRequestHeader('login_session', wnkPage.storage['login_session']);
            },
            data: {
                'user_id'    : wnkPage.storage['user_id'],
                'business_id': jQuery('#business_id').val(),
                'goods_id'   : jQuery('#goods_id').val()
            },
            success: function (ret) {
                if (ret.status === 0) {
                    // 设置大图
                    jQuery(".detail-header > img").attr("src",ret.data.imgPath + ret.data.img);
                    jQuery(".product-name").text(ret.data.name);
                    jQuery(".text-highlight").text(ret.data.price);
                    jQuery(".rich-txt").html(ret.data.detail);
                }
            }
        });


    },
    init: function () {
        // 初始化mui
        mui.init();
        // 避免含义冲突
        $.noConflict();
        // 获取商品信息
        wnkPage.getGoodsById();
        // 获取用户信息
        wnkPage.getIntegralUser();
    }
};

wnkPage.init();