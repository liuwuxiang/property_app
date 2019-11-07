var layui_form = null;

var editor = new window.wangEditor('#detail_text');

var protocol_id = null;

var storage = window.localStorage;
/**
 * 初始化数据
 */
function initDate() {
    // 初始化layui相关
    layui.use(['form'], function () {
        layui_form = layui.form;
        // 查询所有商家信息
        selectBusinessInfo();
        layui_form.render();
    });

    // 初始化富文本编辑器
    initEditor();

    // 获取ID
    protocol_id = $('#protocol_id').val();

    if (protocol_id !=null && protocol_id !== undefined && String(protocol_id) !== '') {
        selectAdminBusinessOpenCardProtocolById();
    }

}

function initEditor() {
    editor.customConfig.showLinkImg = false;
    editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    editor.customConfig.uploadFileName = 'file';
    editor.create();
}

function selectBusinessInfo() {
    $.ajax({
        url: "/property_system/admin/selectBusinessInfoAll",
        type: "POST",
        headers: {'login_session': storage["login_session"]},
        dataType: 'json',
        data: {
        },
        success: function (ret) {
            if (ret.status === 0 && ret.data != null && ret.data.length > 0) {
                for (var i = 0; i < ret.data.length ;i++ ){
                    var obj = ret.data[i];
                    var html = '<option value="'+obj.business_id+'">'+obj.store_name+'</option>';
                    $('#business_id').append(html);
                }
            } else {
                layer.msg(ret.msg, {icon: 5});
            }
            layui_form.render();
        }
    });
}

function insertOrUpdateBusinessOpenCardProtocol() {
    // 获取参数
    var url = null;
    var id = protocol_id;
    var content = editor.txt.html();
    var is_checked = $('input[name=is_checked]:checked').val();
    var business_id = $('#business_id').val();

    if (content == null || String(content) === ''){
        layer.msg('请输入协议内容!', {icon: 5});
        return;
    }
    if (is_checked == null || String(is_checked) === ''){
        layer.msg('请选择是否启用!', {icon: 5});
        return;
    }
    if (business_id == null || String(business_id) === ''){
        layer.msg('请选择为那个商家设置协议内容!', {icon: 5});
        return;
    }
    url = (id == null || id === undefined || String(id) === '') ? '/property_system/admin/insertBusinessOpenCardProtocol':'/property_system/admin/updateBusinessOpenCardProtocol';
    $.ajax({
        url: url,
        type: "POST",
        headers: {'login_session': storage["login_session"]},
        dataType: 'json',
        data: {
            id          : id,
            content     : content,
            business_id : business_id,
            is_checked  : is_checked
        },
        success: function (ret) {
            if (ret.status == 0){
                layer.open({
                    title: '提示',
                    content:ret.msg,
                    yes:function(index, layero){
                        parent.layer.close(parent.layer.getFrameIndex(window.name));
                        parent.location.reload();
                    }
                });
            }  else {
                layer.msg(ret.msg, {icon: 2});
            }

        }
    });
}

function selectAdminBusinessOpenCardProtocolById() {
    $.ajax({
        url: "/property_system/admin/selectBusinessOpenCardProtocolById",
        type: "POST",
        headers: {'login_session': storage["login_session"]},
        dataType: 'json',
        data: {
            protocol_id : protocol_id
        },
        success: function (ret) {
            console.log(ret);
            if (ret.status === 0 && ret.data != null) {
                editor.txt.html(ret.data.content);
                document.getElementsByName('is_checked')[ret.data.is_checked].checked = true;
                var business_id = $('#business_id');
                business_id.val(ret.data.business_id);
                business_id.attr('disabled','disabled');
                business_id.css('background-color','#EEEEEE');
            } else {
                layer.msg(ret.msg, {icon: 5});
            }
            layui_form.render();
        }
    });
}