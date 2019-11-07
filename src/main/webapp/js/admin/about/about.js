/**
 * <p>0-法律声明</p>
 * <p>1-价格声明</p>
 * <p>2-隐私政策</p>
 * <p>3-餐饮安全管理</p>
 * @type {number}
 */
var edit_type = -1;

var storage = window.localStorage;

/**
 * 富文本编辑器对象
 * @type {null}
 */
var editor = new window.wangEditor('#editor');

// 初始化
function init() {

    // 初始化富文本编辑器
    initEditor();

    // 获取内容
    edit_type = $('#about_type').val();
    //
    selectAboutContent(edit_type);

}

/**
 * 查询内容
 * @param type
 * <p>0-法律声明</p>
 * <p>1-价格声明</p>
 * <p>2-隐私政策</p>
 * <p>3-餐饮安全管理</p>
 */
function selectAboutContent(type) {
    var title = $('#about_title');
    if (type == 19){
        title.html("法律声明");
    }
    if (type == 20){
        title.html("价格声明");
    }
    if (type == 21){
        title.html("隐私政策");
    }
    if (type == 22){
        title.html("餐饮安全管理");
    }
    if (type == 27){
        title.html("用户协议");
    }
    $.ajax({
        url: '/property_system/admin/selectAboutByType',
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "type": type
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
                console.log(ret.data.content)
                editor.txt.html(ret.data.content);
            }
        }
    });
}

/**
 * 初始化富文本编辑器
 */
function initEditor() {
    // var E = window.wangEditor;
    // var editor = new E('#editor');
    // 隐藏“网络图片”tab
    editor.customConfig.showLinkImg = false;
    // 配置上传图片接口
    editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    editor.customConfig.uploadFileName = 'file';
    editor.create();
}

function about_submit() {
    $.ajax({
        url: '/property_system/admin/updateAboutInfo',
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "type"   : edit_type,
            'content': editor.txt.html()
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
                layer.msg('更新成功!', {icon: 1});
            }
        }
    });

}