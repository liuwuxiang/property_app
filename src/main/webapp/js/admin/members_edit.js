var storage = window.localStorage;

var user_id = -1;

var layFrom = null;

// 初始化数据
function initData() {
    user_id = $('#user_id').val();
    // 初始化表单
    layui.use('form', function () {
        layFrom = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        // 获取会员用户信息
        adminSelectUserInfoById();
    });

    // 绑定选择图片按钮被单击事件
    document.getElementById('img_upload_show').addEventListener('click',function (ev) {
        $('#img_upload_header').click();
    });
}

// 查询会员用户信息
function adminSelectUserInfoById() {
    $.ajax({
        url: "/property_system/admin/adminSelectUserInfoById",
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "user_id": user_id
        },
        success: function (ret) {
            if (ret.status === 0) {
                $('#nick_name').val(ret.data.nick_name);
                $('#img_preview_header').attr('src',ret.data.img_header);

                var inputSex = $('input[name=sex]');
                for (var i = 0 ; i < inputSex.length;i++){
                    if(parseInt(inputSex[i].value) === parseInt(ret.data.sex)){
                        inputSex[i].checked = true;
                    }
                }
                $('#email').val(ret.data.email);
            }
            layFrom.render();
        }
    });
}

// 上传头像
function imgUpload() {
    $.ajaxFileUpload({
        url: '/property_system/images/savaimageMobile.do',
        secureuri: false,
        fileElementId: 'img_upload_header',
        dataType: 'json',
        type: 'post',
        data: {'fileNameStr': 'ajaxFile', 'fileId': 'img_upload_header'},
        success: function (data, status) {
            console.log(data);
            if (status === 'success') {
                var img_preview = $('#img_preview_header');
                img_preview.attr('src',data.url_location);
                img_preview.attr('data-src',data.url);
            } else {
            }
        },
        error: function (data, status, msg) {
        }
    });
}

//  保存修改后的信息
function adminUpdateUserInfoById() {

    var nick_name = document.getElementById('nick_name').value;
    var header    = document.getElementById('img_preview_header').getAttribute('data-src');
    var sex       = $('input[name="sex"]:checked ').val();
    var email     = document.getElementById('email').value;

    if (nick_name === undefined || nick_name == null || String(nick_name) === '') {
        layer.open({
            icon: 1,
            title: '提示',
            content: '昵称不能为空'
        });
        return;
    }
    if (header === undefined || header == null || String(header) === '') {
        layer.open({
            icon: 1,
            title: '提示',
            content: '请上传头像'
        });
        return;
    }
    if (sex === undefined || sex == null || String(sex) === '') {
        layer.open({
            icon: 1,
            title: '提示',
            content: '请选择性别'
        });
        return;
    }
    if (email === undefined || email == null || String(email) === '') {
        layer.open({
            icon: 1,
            title: '提示',
            content: '邮箱不能为空'
        });
        return;
    }

    $.ajax({
        url: "/property_system/admin/adminUpdateUserInfoById",
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "id"       : user_id,
            "nick_name": nick_name,
            "header"   : header,
            "sex"      : sex,
            "email"    : email
        },
        success: function (ret) {
            if (ret.status === 0) {
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
                layer.open({
                    icon: 1,
                    title: '提示',
                    content: ret.msg
                });
            }
            layFrom.render();
        }
    });
}