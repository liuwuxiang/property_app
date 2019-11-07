var form = null;
layui.use('form',function(){
    form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

    // 0 是新增 1 = 修改
    var type = 0;

    var id = $('#id').val();

    if (id !== undefined && id !== "") {
        $.ajax({
            url: "/property_system/admin/getIntegralGoodsType",
            type: "POST",
            headers: {
                'login_session': mMain.storage["login_session"]
            },
            dataType: 'json',
            data: {"user_id": mMain.storage["user_id"], "id": id},
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    $('input[name=id]').val(ret.data.id);
                    $('input[name=name]').val(ret.data.name);
                    $('#img_preview').attr("src", ret.data.img);
                    $('#img_preview').css("display", "inline");
                    // $('input[name=isDelete][value="'+ret.data.isDelete+'"]').attr("checked", "checked");
                    var is = document.getElementsByName("isDelete");
                    is[parseInt(ret.data.isDelete)].checked  = true;
                    type = 1;
                    form.render();
                }
            }
        });
    } else {
        var is = document.getElementsByName("isDelete");
        is[0].checked  = true;
        form.render();
    }


    $('#integral-type-form').submit(function () {
        var url = type === 0 ? "/property_system/admin/addIntegralGoodsType" : "/property_system/admin/updateIntegralGoodsType";
        $.ajax({
            url: url,
            type: "POST",
            headers: {
                'login_session': mMain.storage["login_session"]
            },
            dataType: 'json',
            data: {
                "user_id" : mMain.storage["user_id"],
                "id"      : id,
                "name"    : $('input[name=name]').val(),
                "img"     : $('#img_preview')[0].src,
                "isDelete": $('input[name=isDelete]:checked').val()
            },
            success: function (ret) {
                layer.open({
                    icon: 1,
                    title: '提示',
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function () {
                        parent.layer.close(parent.layer.getFrameIndex(window.name));
                        parent.location.reload();
                    }
                });
            }
        });


        return false;
    });

    $('#img_upload_show').click(function () {
        $('#img_upload').click();
    });

    layui.use('upload', function () {
        layui.upload.render({
            elem: '#img_upload', //绑定元素
            url: '/property_system//images/savaimageMobile.do', //上传接口
            accept: 'images/*',
            method: "post",
            data: {
                "fileNameStr": "ajaxFile",
                "fileId": "img_upload"
            },
            before: function (data) {
                data.preview(function (index, file, result) {
                    $('#img_preview').attr("src", result);
                    $('#img_preview').css("display", "inline");
                });
            },
            done: function (res, index, upload) {
                console.log(res);
                //上传完毕回调
                if (res.error === 0) {
                    $('#img_preview').attr("src", res.url_location);
                }
            },
            error: function (index, upload) {
                //请求异常回调
            }
        });
    });
});