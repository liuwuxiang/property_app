var wnkCountMain = {
    business_id:jQuery('#business_id').val(),
    storage:window.localStorage,
    /**
     * 获取我的积分详情.
     */
    getMyIntegralDetail: function () {
        jQuery.ajax({
            url       : "/property_system/wx/v1.0.0/getUserIntegral",
            type      : "post",
            dataType  : "json",
            data      : {
                "user_id": wnkCountMain.storage["user_id"],
                'business_id':wnkCountMain.business_id
            },
            beforeSend: function (request) {
                // 设置请求头
                request.setRequestHeader("login_session", wnkCountMain.storage["login_session"]);
            },
            success: function (ret) {
                console.log(ret);
                jQuery('.num').html(ret.data.integral);
            }
        });
    },
    /**
     * 获取积分列表
     */
    getIntegralCount:function(){
        jQuery.ajax({
            url       : "/property_system/wx/v1.0.0/getIntegralCountByWnk",
            type      : "post",
            dataType  : "json",
            data      : {
                "user_id": wnkCountMain.storage["user_id"],
                'business_id':wnkCountMain.business_id
            },
            beforeSend: function (request) {
                // 设置请求头
                request.setRequestHeader("login_session", wnkCountMain.storage["login_session"]);
            },
            success: function (ret) {
                console.log(ret);
                if(ret.status === 0){
                    for (var i=0;i<ret.data.length;i++){

                        var price = ret.data[i].income_type === 0 ? "+"+ret.data[i].income_amount:"-"+ret.data[i].income_amount;

                        jQuery('#detail-list').append('' +
                            '<li class="count-item mui-col-sm-12 mui-col-xs-12">\n' +
                            '                <div class="mui-col-sm-6 mui-col-xs-6">\n' +
                            '                    <p class="name ">'+ret.data[i].name+'</p>\n' +
                            '                    <p class="meta">'+ret.data[i].income_date+'</p>\n' +
                            '                </div>\n' +
                            '                <span class="nums mui-col-sm-6 mui-col-xs-6">'+price+'</span>\n' +
                            '            </li>');
                    }
                }
            }
        });
    },
    init: function () {
        // 避免含义冲突
        $.noConflict();
        // 初始化mui
        mui.init();
        // 获取我的积分详情
        wnkCountMain.getMyIntegralDetail();
        // 获取积分明细列表
        wnkCountMain.getIntegralCount();
    }
};

wnkCountMain.init();