/**数据存储*/
var storage = window.localStorage;

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
                $('input[name=img]').val(data.url);
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

/**
 * 保存或者新增分类
 */
function addIntegralType() {
    // 获取参数
    var id = $('#type_id').val();
    // 分类图片
    var img = $('input[name=img]').val();
    // 分类名称
    var name = $('#name').val();
    // 是否启用
    var isChecked = $('#isChecked').is(':checked') ? 0 : 1;
    // 请求地址
    var url = null;
    // 提示消息
    var msgTest = null;

    // 参数检查
    if (img === undefined || img === null || img === '') {
        $.toast("请上传分类图片", 'text');
        return;
    }
    if (img === undefined || name === null || name === '') {
        $.toast("请输入分类名称", 'text');
        return;
    }
    if (id === undefined || id === null || id === '') {
        msgTest = "新增分类中";
        url = "/property_system/wnk_business/addWnkIntegralType";
    } else {
        msgTest = "修改分类中";
        url = "/property_system/wnk_business/editWnkIntegralType";
    }
    // 发送请求
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        timeout: 5000,
        beforeSend: function (request) {
            $.showLoading(msgTest);
            request.setRequestHeader("login_session", storage["login_session"]);
        },
        data: {
            'business_id': storage['business_id'],
            'id'         : id,
            "img"        : img,
            'name'       : name,
            'is_checked' : isChecked
        },
        success: function (data) {
            if (parseInt(data.status) === 0){
                $.hideLoading();
                $.toast(data.msg, 'text',function () {
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
 * 页面初始化
 */
function init() {

    var id = $('#type_id').val();
    if (String(id) !== undefined && String(id) !== null && String(id) !==''){
        // 发送请求
        $.ajax({
            url: '/property_system/wnk_business/getIntegralTypeById',
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
                    $('#uploaderFiles').append('' +
                        '<li class="weui-uploader__file" style="background-image:url('+data.data.urlPath+data.data.img+')"></li>');

                    $('#name').val(data.data.name);

                    $('input[name=img]').val(data.data.img);

                    if(parseInt(data.data.is_checked) === 0){
                        $('#isChecked').attr('checked','checked');
                    } else {
                        $('#isChecked').removeAttr('checked','checked');
                    }

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




