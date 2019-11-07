$(function () {
    // 加载完成时候进行页面初始化
    $.ajax({
        url: '/property_system/wnk_business/getIntegralTypeAll',
        type: 'post',
        dataType: 'json',
        timeout: 5000,
        beforeSend: function (request) {
            $.showLoading("载入数据中...");
            request.setRequestHeader("login_session", storage["login_session"]);
        },
        data: {'business_id': storage['business_id']},
        success: function (data) {
            var isYes = $('#isYes');
            var isNo = $('#isNo');
            $.hideLoading();
            if (parseInt(data.status) === 0) {
                if (parseInt(data.data.length) === 0) {
                    isYes.addClass(" dataNotNUll");
                } else {
                    isNo.addClass(" dataNotNUll");
                    var yes = $('#yes');
                    var no = $('#no');
                    data.data.forEach(function (ret) {
                        if (parseInt(ret.is_checked) === 0) {
                            yes.append('<a href="/property_system/wnk_business/joinIntegralTypeEdit?type_id=' + ret.id + '" class="item member A' + ret.name + '">' + ret.name + '</a>');
                            yes.append('<style>.A' + ret.name + ':before{background-image: url(' + data.msg + ret.img + ') !important;border-radius:50%;}</style>');
                        } else {
                            no.append('<a href="/property_system/wnk_business/joinIntegralTypeEdit?type_id=' + ret.id + '" class="item member A' + ret.name + '">' + ret.name + '</a>');
                            no.append('<style>.A' + ret.name + ':before{background-image: url(' + data.msg + ret.img + ') !important;border-radius:50%;}</style>');
                        }
                    });
                }
            } else {
                $.toast("加载出错，请重试", 'text', function () {
                    history.go(-1);
                });
            }


        },
        error: function (jqXHR, textStatus, errorThrown) {
            /*
            表示返回的状态，根据服务器不同的错误
            可能返回下面这些信息："timeout"（超时）, "error"（错误）, "abort"(中止),
             "parsererror"（解析错误），还有可能返回空值。
             */
            if (String(textStatus) === 'timeout') {
                $.hideLoading();
                $.toast("加载超时,请重试", 'text');
            } else {
                $.hideLoading();
                $.toast("加载失败,请重试", 'text');
            }
        }
    });

    // jquery 添加样式的方式
    //$('#isYes').addClass(" dataNotNUll");

});