var storage = null;
var content_id = null;
var content_type = null;
var layForm = null;
/**
 * 初始化页面数据
 */
function initDate() {
    storage = window.localStorage;
    content_id = $('#content_id').val();
    content_type = $('#content_type').val();

    // 初始化表单
    layui.use("form", function () {
        layForm = layui.form;
    });

    // 判断是新增还是编辑
    if (content_id !== undefined && String(content_id) !== ''){
        getContentInfoById(content_id,content_type)
    }
}

/**
 * 根据ID获取特色/服务内容信息
 * @param content_id   特色/服务内容ID
 * @param content_type 0-服务内容 1-特色内容
 */
function getContentInfoById(content_id,content_type){
    $.ajax({
        url: '/property_system/admin/selectContentInfoById',
        headers: {
            'login_session': mMain.storage["login_session"]
        },
        data: {
            'content_id'  :content_id,
            'content_type':content_type
        },
        type: "post",
        timeout: 10000,
        async: true,
        dataType: "json",
        success: function (ret) {
            if (parseInt(ret.status) === 0){
                $('#content_name').val(ret.data.name);
                $('input[name=content_status]')[parseInt(ret.data.state)].checked = true;
                $('input[name=content_type]')[parseInt(content_type)].checked = true;
            }
            layForm.render();
        }
    });
}


/**
 * 新增/编辑
 */
function submitContentLabel() {
    var url = null;
    if (content_id === undefined || String(content_id) === ''){
        url = '/property_system/admin/insertContentLabel';
    } else {
        url = '/property_system/admin/updateContentLabel';
    }

    var content_name   = $('#content_name').val();
    var content_status = $('input[name=content_status]:checked').val();
    var content_type = $('input[name=content_type]:checked').val();

    if (content_name === undefined || String(content_name) === ''){
        layer.msg('请输入内容名称');
        return;
    }

    if (content_type === undefined || String(content_status) === ''){
        layer.msg('请选内容类别');
        return;
    }

    if (content_status === undefined || String(content_status) === ''){
        layer.msg('请选择是否启用');
        return;
    }

    // 新增/编辑维护员分类
    $.ajax({
        url: url,
        type: "POST",
        headers: {
            'login_session': mMain.storage["login_session"]
        },
        dataType: 'json',
        data: {
            'id'    : content_id,
            'name'  : content_name,
            'state' : content_status,
            'type'  : content_type
        },
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
                layer.msg(ret.msg);
            }
        }
    });
}