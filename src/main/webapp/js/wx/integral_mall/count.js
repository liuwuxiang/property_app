var countMain = {

    /**
     * 获取我的积分详情.
     */
    getMyIntegralDetail: function () {
        $.ajax({
            url       : "/property_system/wx/v1.0.0/getMyIntegralDetail",
            type      : "post",
            dataType  : "json",
            data      : {"user_id": mMain.storage["user_id"],},
            beforeSend: function (request) {
                // 设置请求头
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            success: function (ret) {
                console.log(ret);
                $('.num').html(ret.data.userIntegral);
            }
        });
    },
    /**
     * 获取积分列表
     */
    getIntegralCount:function(){
        $.ajax({
            url       : "/property_system/wx/v1.0.0/getIntegralCount",
            type      : "post",
            dataType  : "json",
            data      : {"user_id": mMain.storage["user_id"],},
            beforeSend: function (request) {
                // 设置请求头
                request.setRequestHeader("login_session", mMain.storage["login_session"]);
            },
            success: function (ret) {
                if(ret.status === 0){
                    for (var i=0;i<ret.data.length;i++){

                        var price = ret.data[i].type === 0 ? "+"+ret.data[i].transaction_integral_number:"-"+ret.data[i].transaction_integral_number;

                        $('#detail-list').append('' +
                            '<li class="count-item mui-col-sm-12 mui-col-xs-12">\n' +
                            '                <div class="mui-col-sm-6 mui-col-xs-6">\n' +
                            '                    <p class="name ">'+ret.data[i].name+'</p>\n' +
                            '                    <p class="meta">'+ret.data[i].transaction_date+'</p>\n' +
                            '                </div>\n' +
                            '                <span class="nums mui-col-sm-6 mui-col-xs-6">'+price+'</span>\n' +
                            '            </li>');
                    }
                }
            }
        });
    },
    init: function () {
        countMain.getMyIntegralDetail();

        countMain.getIntegralCount();
    }
};
countMain.init();