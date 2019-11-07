/**
 * <p>0-法律声明</p>
 * <p>1-价格声明</p>
 * <p>2-隐私政策</p>
 * <p>3-餐饮安全管理</p>
 * <p>4-用户协议</p>
 * @type {number}
 */
var edit_type = -1;

/**
 * <p>0-用户</p>
 * <p>1-商家</p>
 * @type {number}
 */
var about_type = -1;

var storage = window.localStorage;

/**
 * 富文本编辑器对象
 * @type {null}
 */
var editor = new window.wangEditor('#editor');

// 初始化
function init() {

    // 获取是用户还是商家
    about_type = document.getElementById("about_type").value;

    // 初始化富文本编辑器
    initEditor();

    // 获取内容
    edit_type = document.getElementById("about_type_select").value;
    //
    selectAboutContent(edit_type,about_type);

}

/**
 * 查询内容
 * @param type       获取那个内容
 * <p>0-法律声明</p>
 * <p>1-价格声明</p>
 * <p>2-隐私政策</p>
 * <p>3-餐饮安全管理</p>
 * <p>4-用户协议</p>
 * @param about 0-用户 1-商家
 */
function selectAboutContent(type,about) {
    var title = $('#about_title');
    switch (parseInt(type)){
        case 19:
            title.html("法律声明");
            break;
        case 20:
            title.html("价格声明");
            break;
        case 21:
            title.html("隐私政策");
            break;
        case 22:
            title.html("餐饮安全管理");
            break;
        case 27:
            title.html("用户协议");
            break;
    }
    $.ajax({
        url: '/property_system/admin/selectAboutByType',
        type: "POST",
        headers: {
            'login_session': storage["login_session"]
        },
        dataType: 'json',
        data: {
            "type": type,
            "about":about
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
                if (ret.data !== null){
                    editor.txt.html(ret.data.content);
                } else {
                    editor.txt.html("");
                }

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
            "type"   : document.getElementById("about_type_select").value,
            'content': editor.txt.html(),
            'about'  : document.getElementById("about_type").value
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
                layer.msg('更新成功!', {icon: 1});
            }
        }
    });

}

function aboutTypeChange() {
    selectAboutContent(document.getElementById("about_type_select").value,about_type);
}