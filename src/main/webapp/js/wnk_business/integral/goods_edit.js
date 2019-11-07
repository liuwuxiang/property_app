/**存储*/
var storage = window.localStorage;

/**
 * 也初始化
 */
function init() {

    var id = $('#goods_id').val();
    if (String(id) !== undefined && String(id) !== null && String(id) !==''){
        // 发送请求
        $.ajax({
            url: '/property_system/wnk_business/getIntegralGoodsById',
            type: 'post',
            dataType: 'json',
            timeout: 5000,
            beforeSend: function (request) {
                $.showLoading("正在处理中...");
                request.setRequestHeader("login_session", storage["login_session"]);
            },
            data: {
                'business_id': storage['business_id'],
                'id'         : id
            },
            success: function (data) {
                $.hideLoading();
                if (parseInt(data.status) === 0) {
                    $('#detail').val(data.data.detail);
                    $('#img').val(data.data.img);
                    $('#uploaderFiles').append('' +
                        '<li class="weui-uploader__file" style="background-image:url(' + data.data.urlPath+data.data.img + ')">' +
                        '</li>' +
                        '');
                    $('#name').val(data.data.name);
                    $('#price').val(data.data.price);
                    $('#synopsis').val(data.data.synopsis);
                    if (parseInt(data.data.is_checked) === 0){
                        $('#is_checked').attr('checked','checked');
                    } else {
                        $('#is_checked').removeAttr('checked','checked');
                    }
                    $('input[name=type]').val(data.data.typeName);
                    $('#type').val(data.data.type);
                } else {
                    $.toast(data.msg, 'text',function () {
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
                    $.toast("请求超时,请重试", 'text');
                } else {
                    $.hideLoading();
                    $.toast("未知异常,请重试", 'text');
                }
            }
        });
    }

}


/**
 * 添加商品
 */
function addIntegralGoods() {

    // 获取参数
    var id         = $('#goods_id').val();
    var name       = $('#name').val();
    var img        = $('#img').val();
    var synopsis   = $('#synopsis').val();
    var price      = $('#price').val();
    var is_checked = $('#is_checked').is(':checked') ? 0 : 1;
    var type       = $('#type').val();
    var detail     = $('#detail').val();

    // 请求地址
    var url = null;


    if (String(name) === undefined || String(name) === null || String(name) === ''){
        $.toast("请输入商品名称",'cancel');
        return;
    }
    if (String(img) === undefined || String(img) === null || String(img) === ''){
        $.toast("请上传商品大图",'cancel');
        return;
    }
    if (String(synopsis) === undefined || String(synopsis) === null || String(synopsis) === ''){
        $.toast("请输入商品简介",'cancel');
        return;
    }
    if (String(price) === undefined || String(price) === null || String(price) === ''){
        $.toast("请输入商品价格",'cancel');
        return;
    }
    if (String(is_checked) === undefined || String(is_checked) === null || String(is_checked) === ''){
        $.toast("请请选择是否启用",'cancel');
        return;
    }
    if (String(type) === undefined || String(type) === null || String(type) === ''){
        $.toast("请选择商品分类",'cancel');
        return;
    }
    if (String(detail) === undefined || String(detail) === null || String(detail) === ''){
        $.toast("请输入商品详情",'cancel');
        return;
    }

    if (String(id) === undefined || String(id)===null || String(id) === ''){
        url = '/property_system/wnk_business/addIntegralGoods';
    } else {
        url = '/property_system/wnk_business/editIntegralGoods';
    }
    // 发送请求
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        timeout: 5000,
        beforeSend: function (request) {
            $.showLoading("正在处理中...");
            request.setRequestHeader("login_session", storage["login_session"]);
        },
        data: {
            'business_id': storage['business_id'],
            'id'         : id,
            "name"       : name,
            'img'        : img,
            'synopsis'   : synopsis,
            'price'      : price,
            "is_checked" : is_checked,
            'type'       : type,
            'detail'     : detail
        },
        success: function (data) {
            if (parseInt(data.status) === 0) {
                $.hideLoading();
                $.toast(data.msg, 'text', function () {
                    history.go(-1);
                });
            } else {
                $.toast(data.msg, 'text');
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
                $.toast("请求超时,请重试", 'text');
            } else {
                $.hideLoading();
                $.toast("未知异常,请重试", 'text');
            }
        }
    });
}

/**
 * 获取已经启用的分类
 */
function getGoodsType() {
    $.ajax({
        url: '/property_system/wnk_business/getIntegralTypeByTrueToGoods',
        type: 'post',
        dataType: 'json',
        timeout: 5000,
        beforeSend: function (request) {
            $.showLoading("载入数据中...");
            request.setRequestHeader("login_session", storage["login_session"]);
        },
        data: {"business_id":storage["business_id"]},
        success: function (data) {
            $.hideLoading();
            weui.picker(data.data,{
                onChange:function(result){
                    data.data.forEach(function (value) {
                        if (parseInt(value.value) === parseInt(result)){
                            $('input[name=type]').val(value.label);
                            $('#type').val(value.value);
                        }
                    })
                },
                defaultValue:[0]
            });

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
                history.go(-1);
            } else {
                $.hideLoading();
                $.toast("加载失败,请重试", 'text');
                history.go(-1);
            }
        }
    });
}

/**
 * 文件上传
 */
function imgUpload() {
    var uploaderFiles = $('#uploaderFiles');
    $.showLoading("图片上传中");
    $.ajaxFileUpload({
        url: '/property_system/images/savaimageMobile.do',
        secureuri: false,
        fileElementId: 'header_file',
        dataType: 'json',
        type: 'post',
        data: {'fileNameStr': 'ajaxFile', 'fileId': 'header_file'},
        success: function (data, status) {
            $.hideLoading();
            if (status === 'success') {
                $('#img').val(data.url);
                uploaderFiles.empty();
                uploaderFiles.append('' +
                    '<li class="weui-uploader__file" style="background-image:url(' + data.url_location + ')">' +
                    '</li>' +
                    '');
            } else {
                $.toast("上传出错", 'text');
            }
        },
        error: function (data, status, msg) {
            $.hideLoading();
            $.toast(msg, 'text');
        }
    });
}

