var form = null;

var editor = new window.wangEditor('#detail_text');

layui.use('form', function () {
    form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

    var id = $('#id').val();
    // 0= 新增 1=修改
    var type = 0;
    var layeditIndex;
    var layedit;
    // 图片预览;
    var preview = $('#img_preview');

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    // 初始化上传组件
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
                toast(2, "开启loading");
                data.preview(function (index, file, result) {
                    $('#img_preview').attr("src", result);
                    $('#img_preview').css("display", "inline");
                });
            },
            done: function (res, index, upload) {
                //上传完毕回调
                if (res.error === 0) {
                    console.log(res);
                    toast(3, "关闭loading");
                    $("input[name=img]").val(res.url);
                    $('#img_preview').attr("src", res.url_location);
                }
            },
            error: function (index, upload) {
                //请求异常回调
            }
        });
    });

    $('#img_upload_show').click(function () {
        $('#img_upload').click();
    });

    // 获得分类
    $.ajax({
        url: "/property_system/admin/getAllIntegralTypeTrue",
        type: "POST",
        headers: {
            'login_session': mMain.storage["login_session"]
        },
        dataType: 'json',
        data: {"user_id": mMain.storage["user_id"], "id": id},
        success: function (ret) {
            console.log(ret);
            if (ret.status == 0) {
                for (var i = 0; i < ret.data.list.length; i++) {
                    var html = '<option value="' + ret.data.list[i].id + '">' + ret.data.list[i].name + '</option>';
                    $("#type_id_select").append(html);
                }
                form.render();
            }
        }
    });

    // 如果有ID 则为修改, 则需要获取商品信息
    if (id !== undefined && id !== "") {
        // 获得分类
        $.ajax({
            url: "/property_system/admin/getIntegralGoodsById",
            type: "POST",
            headers: {'login_session': mMain.storage["login_session"]},
            dataType: 'json',
            data: {"user_id": mMain.storage["user_id"], "goods_id": id},
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    console.log(ret);
                    $('input[name=name]').val(ret.data.name);
                    $('input[name=price]').val(ret.data.price);
                    $('input[name=trader]').val(ret.data.trader);
                    $('input[name=synopsis]').val(ret.data.synopsis);
                    console.log(ret.data.type);
                    $('#type_id_select').val(ret.data.type);
                    $('#img_preview').attr("src", ret.data.img);
                    $('#img_preview').css("display", "inline");

                    var is = document.getElementsByName("isRecommend");
                    is[parseInt(ret.data.isRecommend)].checked = true;

                    var is_c = document.getElementsByName("is_checked");
                    is_c[parseInt(ret.data.is_checked)].checked = true;

                    initEdit();
                    editor.txt.html(ret.data.detail);
                    // $('#detail_text').val(ret.data.detail);
                    type = 1;
                    form.render();
                }
            }
        });
    }
    else {
        var is = document.getElementsByName("isRecommend");
        is[0].checked = true;

        var is_c = document.getElementsByName("is_checked");
        is_c[0].checked = true;

        form.render();
        initEdit();
    }

    /**
     * 初始化富文本编辑器
     */
    function initEdit() {

        editor.customConfig.showLinkImg = false;
        // 配置上传图片接口
        editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
        editor.customConfig.uploadFileName = 'file';
        editor.create();

        // layui.use('layedit', function () {
        //     layedit = layui.layedit;
        //     // 上传接口
        //     layedit.set({
        //         uploadImage: {
        //             url: '/property_system/images/savaimageLayui',
        //             type: 'post'
        //         }
        //     });
        //     layeditIndex = layedit.build('detail_text', {
        //         height: 400, //设置编辑器高度
        //         tool: [
        //             'strong',     //加粗
        //             'italic',     //斜体
        //             'underline',  //下划线
        //             'del',        //删除线
        //             '|',          //分割线
        //             'left',       //左对齐
        //             'center',     //居中对齐
        //             'right',      //右对齐
        //             'link',       //超链接
        //             'unlink',     //清除链接
        //             'image'
        //         ]
        //     });
        // });

    }

    /**
     * AJAX 提交
     */
    $('#integral-form').submit(function () {
        var url = type === 0 ? "/property_system/admin/addIntegralGoods" : "/property_system/admin/updateIntegralGoods";
        var index;
        //$('input[name=detail]').val(layui.layedit.getContent(layeditIndex));
        $('input[name=detail]').val(editor.txt.html());
        $('input[name=img]').val($('#img_preview')[0].src);
        $.ajax({
            url: url,
            data: $('#integral-form').serializeObject(),
            type: "post",
            timeout: 10000,
            async: true,
            dataType: "json",
            success: function (ret) {
                layer.open({
                    offset: ['40%', '40%'],
                    title: '消息:',
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function () {
                        parent.layer.close(parent.layer.getFrameIndex(window.name));
                        parent.location.reload();
                    }
                });
            },
            error: function () {
                layer.open({
                    title: '消息:',
                    content: '操作失败',
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

});

