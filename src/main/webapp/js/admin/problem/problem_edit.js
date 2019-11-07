var layForm = null;
var problem_id = undefined;
var layEdit;
var layEditIndex;

var editor = new window.wangEditor('#detail_text');

layui.use('form', function () {
    layForm = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    problem_id = $('#problem_id').val();

    initLayUI();

    initView();

    /**
     * 表单提交事件
     */
    $('#problem-form-submit').click(function () {
        var url = (problem_id === undefined || problem_id + '' === '') ?
            "/property_system/admin/addProblem" :
            "/property_system/admin/editProblem";
        // 获得参数
        var title = $('#title').val();
        //var content = layui.layedit.getContent(layEditIndex);
        var content = editor.txt.html();

        var check = $('input[name=check]:checked').val();
        var type = $('input[name=problem_check]:checked').val();

        var open_way = $('input[name=open_type_check]:checked').val();
        var open_url = $('#open_url').val();

        var video_url = document.getElementById('video_logo').getAttribute("src");

        // 参数检查
        if (title === undefined || title + '' === '') {
            layer.open({
                offset: ['40%', '40%'],
                title: '消息:',
                content: '请输入常见问题标题'
            });
            return;
        }
        if (open_way == 0) {
            if (content === undefined || content + '' === '') {
                layer.open({
                    offset: ['40%', '40%'],
                    title: '消息:',
                    content: '请输入常见问题内容'
                });
                return;
            }
        }
        else if (open_way == 1) {
            if (open_url === undefined || open_url + '' === '') {
                layer.open({
                    offset: ['40%', '40%'],
                    title: '消息:',
                    content: '请输入常见打开链接'
                });
                return;
            }
            else {
                content = open_url;
            }
        } else {
            if (video_url === undefined || video_url === '') {
                layer.open({
                    offset: ['40%', '40%'],
                    title: '消息:',
                    content: '请上传视频'
                });
                return;
            } else {
                content = video_url;
            }
        }

        if (check === undefined || check + '' === '') {
            layer.open({
                offset: ['40%', '40%'],
                title: '消息:',
                content: '请选择是否启用'
            });
            return;
        }
        $.ajax({
            url: url,
            data: {
                'id': problem_id,
                'title': title,
                'content': content,
                'check': check,
                'type': type,
                'open_way': open_way
            },
            type: "post",
            dataType: "json",
            success: function (ret) {
                if (parseInt(ret.status) === 0) {
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
                } else {
                    layer.open({
                        offset: ['40%', '40%'],
                        title: '消息:',
                        content: ret.msg
                    });
                }
            }
        });

    });

    layForm.on('radio(open_type_check)', function (data) {
        //打开方式(0-富文本,1-链接)
        var open_type = data.value;
        if (open_type == 0) {
            document.getElementById("open_text_div").style.display = "block";
            document.getElementById("open_url_div").style.display = "none";
            document.getElementById('open_url_video').style.display = 'none'
        } else if (open_type == 1) {
            document.getElementById("open_text_div").style.display = "none";
            document.getElementById("open_url_div").style.display = "block";
            document.getElementById('open_url_video').style.display = 'none'
        } else {
            document.getElementById("open_text_div").style.display = "none";
            document.getElementById("open_url_div").style.display = "none";
            document.getElementById('open_url_video').style.display = 'block'
        }
    });

});

layui.use('upload', function () {
    layui.upload.render({
        elem: '#img_upload', //绑定元素
        url: '/property_system/video/saveVideo.do', //上传接口
        accept: 'video',
        method: "post",
        acceptMime: 'video/*',
        field: 'ajaxFile',
        data: {
            "fileNameStr": "ajaxFile",
            "fileId": "img_upload"
        },
        before: function (data) {

        },
        done: function (res, index, upload) {
            console.log(res);
            //上传完毕回调
            if (res.error === 0) {
                document.getElementById('video_logo').setAttribute('src', res.url_location);
                document.getElementById('video_logo').style.display = 'inline';
            }
        },
        error: function (index, upload) {
            //请求异常回调

        }
    });
});

function initView() {
    // 判断是修改还是新增
    // 如果是修改则执行ajax进行读取该条常见问题信息

    if (problem_id !== undefined && problem_id + '' !== '') {
        $.ajax({
            url: "/property_system/admin/selectProblemById",
            type: "POST",
            headers: {'login_session': mMain.storage["login_session"]},
            dataType: 'json',
            data: {"user_id": mMain.storage["user_id"], "problem_id": problem_id},
            success: function (ret) {
                console.log(ret);
                if (ret.status == 0) {
                    $('#title').val(ret.data.title);

                    if (ret.data.state + '' === '启用') {
                        document.getElementsByName("check")[0].checked = true;
                    } else {
                        document.getElementsByName("check")[1].checked = true;
                    }
                    if (ret.data.type == 0) {
                        document.getElementsByName("problem_check")[0].checked = true;
                    }
                    else if (ret.data.type == 1) {
                        document.getElementsByName("problem_check")[1].checked = true;
                    }
                    else {
                        document.getElementsByName("problem_check")[2].checked = true;
                    }
                    var open_way = ret.data.open_way;
                    if (open_way == 0) {
                        document.getElementsByName("open_type_check")[0].checked = true;
                        // $('#detail_text').val(ret.data.content);
                        editor.txt.html(ret.data.content);

                        document.getElementById("open_text_div").style.display = "block";
                        document.getElementById("open_url_div").style.display = "none";
                        document.getElementById("open_url_video").style.display = "none";
                    }
                    else if (open_way == 1) {
                        document.getElementsByName("open_type_check")[1].checked = true;
                        $('#open_url').val(ret.data.content);
                        document.getElementById("open_text_div").style.display = "none";
                        document.getElementById("open_url_div").style.display = "block";
                        document.getElementById("open_url_video").style.display = "none";
                    } else {
                        document.getElementsByName("open_type_check")[2].checked = true;
                        // $('#open_url').val(ret.data.content);
                        document.getElementById('video_logo').setAttribute('src', ret.data.content);
                        document.getElementById('video_logo').style.display = 'inline';

                        document.getElementById("open_text_div").style.display = "none";
                        document.getElementById("open_url_div").style.display = "none";
                        document.getElementById("open_url_video").style.display = "block";
                    }
                   layForm.render();
                }

            }
        });
    }


}

function initLayUI() {
    editor.customConfig.showLinkImg = false;
    // 配置上传图片接口
    editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    editor.customConfig.uploadFileName = 'file';
    editor.create();
    /*
   // 加载LayUI相关组件
   layui.use(['form', 'layedit'], function () {
       layEdit = layui.layedit;
       // 初始化富文本编辑器
       layEdit.set({                    // 上传接口
           uploadImage: {
               url: '/property_system/images/saveImageLayUi',
               type: 'post'
           }
       });

       layEditIndex = layEdit.build('detail_text', {  // 构建富文本编辑器
           height: 400,
           tool: [
               'strong' //加粗
               , 'italic' //斜体
               , 'underline' //下划线
               , 'del' //删除线
               , '|' //分割线
               , 'left' //左对齐
               , 'center' //居中对齐
               , 'right' //右对齐
               , 'link' //超链接
               , 'unlink' //清除链接
               , 'image' //插入图片
           ]
       });
    });
     */
}
