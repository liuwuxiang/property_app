var recordMain = {

    joinOrderDetailed:function(t){
        self.window.location.href= "/property_system/wx/v1.0.0/joinOrderDetailed?goods_id="+t;
    },
    getIntegralOrderById:function(){
        $.ajax({
            url:"/property_system/wx/v1.0.0/getIntegralOrderById",
            type:"post",
            dataType:"json",
            beforeSend:function (request) {
                request.setRequestHeader("login_session",mMain.storage["login_session"]);
            },
            data:{
                "user_id": mMain.storage["user_id"]
            },
            success:function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    var content = $('#content');
                    content.empty();
                   for (var i=0;i<ret.data.length;i++){
                       var status;
                       switch (ret.data[i].status) {
                           case 0:
                               status = "已付款";
                               break;
                           case 1:
                               status = "已发货";
                               break;
                           case 2:
                               status = "交易成功";
                               break;
                           default:
                               status = "渲染异常";
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
                           '                                <img src="'+ret.data[i].img+'">\n' +
                           '                            </div>\n' +
                           '                            <div class="order-goods-trader">\n' +
                           '                                <span>'+ret.data[i].trader+'</span>\n' +
                           '                                <span class="order-goods-trader-name">'+ret.data[i].name+'</span>\n' +
                           '                            </div>\n' +
                           '                        </div>\n' +
                           '                        <div class="mui-card-footer">\n' +
                           '                            <div>\n' +
                           '                                <span>共<span>'+ret.data[i].count+'</span>件商品,实付: <span>'+ret.data[i].price+'</span> 积分</span>\n' +
                           '                            </div>\n' +
                           '                            <div>\n' +
                           '                                <input onclick="recordMain.joinOrderDetailed('+ret.data[i].order_id+')" type="text" value="订单追踪">\n' +
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

        // 获取我的订单ID
        recordMain.getIntegralOrderById();

    }
};
recordMain.init();