var pageMain = {
    getGoodsById:function(){
        $.ajax({
            url:"/property_system/wx/v1.0.0/getGoodsById",
            type:"post",
            dataType:"json",
            beforeSend:function (request) {
                request.setRequestHeader("login_session",mMain.storage["login_session"]);
            },
            data:{
                "user_id": mMain.storage["user_id"],
                "id":$('#id').val()
            },
            success:function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    // 设置大图
                    $(".detail-header > img").attr("src",ret.data.IntegralGoods.img);
                    $(".product-name").text(ret.data.IntegralGoods.name);
                    $(".text-highlight").text(ret.data.IntegralGoods.price);
                    $(".meta-body").text(ret.data.IntegralGoods.trader);
                    $(".rich-txt").empty();
                    $(".rich-txt").html(ret.data.IntegralGoods.detail);
                    if (parseInt(ret.data.integral) <= parseInt(ret.data.IntegralGoods.price)){
                        $('.pay-bar-cell1 > div').empty().html("积分不足");
                        $('.pay-bar-cell1 > div').addClass(" disabled");
                    } else {
                        $('.pay-bar-cell1 > div').empty().html("立即兑换");
                        $('.pay-bar-cell1 > div').removeClass(" disabled");
                        $('#pay-bar').click(function () {
                            console.log(1);
                            self.window.location.href= "/property_system/wx/v1.0.0/joinGoodsChange?id="+ret.data.IntegralGoods.id;
                        })
                    }
                }
            }

        });

    },
    init: function () {
        // mui 初始化
        // mui.init();
        /**
         * 获取商品信息
         */
        pageMain.getGoodsById();
    }
};

pageMain.init();