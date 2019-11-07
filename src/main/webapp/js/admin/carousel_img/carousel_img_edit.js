var layui_form = null;

var storage = window.localStorage;

var carousel_id = -1;

// 初始化数据
function initCarouselData() {
    layui.use('form', function () {

        // 获取ID
        carousel_id = $('#carousel_id').val();
        console.log(1);

        layui_form = layui.form;
        // 默认是否启用为启用


        // 判断是新增还是编辑
        if (parseInt(carousel_id) !== -1 || carousel_id !== undefined || carousel_id !== '' || carousel_id != null){
            selectCarouselInfoById(carousel_id);
        }

        // 初始化全部结束,重新渲染一下表单
        layui_form.render();
    });
}

// 通过轮播图id查询轮播图信息
function selectCarouselInfoById(carousel_id) {
    $.ajax({
        url: "/property_system/admin/selectCarouselInfoById",
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {"user_id": storage["user_id"], "carousel_id": carousel_id},
        success: function (ret) {
            console.log(ret);
            if (parseInt(ret.status) === 0) {
                var img_url = $('#img_url');
                img_url[0].src = ret.data.img_url_path;
                img_url[0].setAttribute('data-url',ret.data.img_url);

                $('#join_url').val(ret.data.url);
                $('#position').val(ret.data.position);

                document.getElementsByName('is_checked')[ret.data.is_checked].checked = true;
            }
            // 填充数据结束,重新渲染表单
            layui_form.render();
        }
    });
}

// 表单提交
function from_submit() {
    var url = null;

    // 获取参数
    var id         = carousel_id;
    var img_url    = $('#img_url')[0].getAttribute("data-url");
    var position   = $('#position').val();
    var is_checked = $("input[name='is_checked']:checked").val();
    var join_url   = $('#join_url').val();

    // 判断是新增还是编辑
    if (carousel_id !== undefined && carousel_id !== '' && carousel_id != null){
        url = '/property_system/admin/updateCarouselInfoById';
    } else {
        url = '/property_system/admin/insertCarouselInfo';
        id = -1;
    }

    // 参数检查
    if (img_url == null || String(img_url) === '' || img_url === undefined){
        layer.msg('请选择图片!', {icon: 5});
        return;
    }
    if (position == null || String(position) === '' || position === undefined){
        layer.msg('请选择轮播图显示位置!', {icon: 5});
        return;
    }
    if (join_url == null || String(join_url) === '' || join_url === undefined){
        layer.msg('请输入链接!', {icon: 5});
        return;
    }
    if (is_checked == null || String(is_checked) === '' || is_checked === undefined){
        layer.msg('请选择是否启用该轮播图!', {icon: 5});
        return;
    }

    // var data = {
    //     "id"         : carousel_id,
    //     'img_url'    : img_url,
    //     'position'   : position,
    //     'is_checked' : is_checked,
    //     'url'        : join_url
    // };
    // console.log(data);
    // return;

    // 发送请求
    $.ajax({
        url: url,
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "id"         : id,
            'img_url'    : img_url,
            'position'   : position,
            'is_checked' : is_checked,
            'url'        : join_url
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
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
            } else {
                layer.msg(ret.msg, {icon: 5});
            }
        }
    });
}

// 图片上传
function chooseHeaderChangeFile() {
    $.ajaxFileUpload({
        url : '/property_system/images/savaimageMobile.do', // 用于文件上传的服务器端请求地址
        secureuri : false,
        fileElementId : 'img_upload',
        dataType : 'json',
        type : "post",
        data:{"fileNameStr":"ajaxFile","fileId":"img_upload"},
        success : function(data, status){
            console.log(data);
            console.log(status);
            if (parseInt(data.error) === 0){
                $('#img_url')[0].src = data.url_location;
                $('#img_url')[0].setAttribute("data-url",data.url);
            }
        }
    });
}