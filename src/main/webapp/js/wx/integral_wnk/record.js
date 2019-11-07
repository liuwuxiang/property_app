var wnkRecord = {
    business_id:jQuery('#business_id').val(),
    storage:window.localStorage,
    joinOrderDetailed:function(t){
        self.window.location.href= "/property_system/wx/v1.0.0/joinOrderDetailedByWnk?order_id="+t+"&business_id="+wnkRecord.business_id;
    },
    getIntegralOrderById:function(){
        jQuery.ajax({
            url:"/property_system/wx/v1.0.0/getIntegralWnkOrderById",
            type:"post",
            dataType:"json",
            beforeSend:function (request) {
                request.setRequestHeader("login_session",wnkRecord.storage["login_session"]);
            },
            data:{
                "user_id"    : wnkRecord.storage["user_id"],
                'business_id': wnkRecord.business_id
            },
            success:function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    var content = jQuery('#content');
                    content.empty();
                    for (var i=0;i<ret.data.length;i++){
                        var status;
                        switch (ret.data[i].status) {
                            case 0:
                                status = "已支付";
                                break;
                            case 1:
                                status = "已完成";
                                break;
                        }
                        content.append('' +
                            '                <li class="mui-col-sm-12 mui-col-xs-12">\n' +
                            '                    <div class="mui-card ">\n' +
                            '                        <div class="mui-card-header">\n' +
                            '                            <span class="order-id">订单号:'+ret.data[i].order_id+'</span>\n' +
                            '                            <span class="order-id-status">'+status+'</span>\n' +
                            '                        </div>\n' +
                            '                        <div class="mui-card-content">\n' +
                            '                            <div class="order-goods-img">\n' +
                            '                                <img src="'+ret.msg+ret.data[i].img+'">\n' +
                            '                            </div>\n' +
                            '                            <div class="order-goods-trader">\n' +
                            '                                <span class="order-goods-trader-name">'+ret.data[i].goods_name+'</span>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="mui-card-footer">\n' +
                            '                            <div>\n' +
                            '                                <span>共<span>1</span>件商品,实付: <span>'+ret.data[i].price+'</span> 积分</span>\n' +
                            '                            </div>\n' +
                            '                            <div>\n' +
                            '                                <input onclick="wnkRecord.joinOrderDetailed('+ret.data[i].order_id+')" type="text" value="订单详细">\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </li>');
                    }
                }
            }

        });
    },
    init:function () {
        // 避免含义冲突
        $.noConflict();
        // 初始化mui
        mui.init();
        // 获取我的订单ID
        wnkRecord.getIntegralOrderById();

    }
};
wnkRecord.init();