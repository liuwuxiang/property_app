var maintain_id = null;
// 0= 新增 1=修改
var type = 0;
var layForm = null;

function init() {
    // 初始化参数
    maintain_id = $('#maintain_id').val();
    if (maintain_id != null && String(maintain_id) !== '') {
        type = 1;
    }

    // 初始化form表单并且刷新表单
    layui.use("form", function () {
        layForm = layui.form;
    });

    // 获取分类
    getBusinessType();

    if (parseInt(type) === 1){
        getMaintainInfoById(maintain_id);
    }



}


/**
 * 根据ID获取维护员信息
 * @param maintain_id 维护员ID
 */
function getMaintainInfoById(maintain_id){
    $.ajax({
        url: '/property_system/admin/selectMaintainInfoById',
        headers: {
            'login_session': mMain.storage["login_session"]
        },
        data: {
            'maintain_id':maintain_id
        },
        type: "post",
        timeout: 10000,
        async: true,
        dataType: "json",
        success: function (ret) {
            console.log(ret);
            if (parseInt(ret.status) === 0){
                // 维护员姓名
                $('#maintain_name').val(ret.data.maintain_name);
                // 维护员联系方式
                $('#maintain_phone').val(ret.data.maintain_phone);
                // 启用状态
                var status = document.getElementsByName("maintain_status");
                if (parseInt(ret.data.status) === 0){
                    status[0].checked = true;
                } else {
                    status[1].checked = true;
                }
                // 维护员维护类别
                $('#maintain_type').val(ret.data.business_type_id);
                console.log(ret.data.business_type_id);
                layForm.render();
            }
        }
    });
}

/**
 * 获取商家分类
 */
function getBusinessType() {
    // 获得分类
    $.ajax({
        url: "/property_system/admin/selectBusinessType",
        type: "POST",
        headers: {
            'login_session': mMain.storage["login_session"]
        },
        dataType: 'json',
        data: {},
        success: function (ret) {
            if (parseInt(ret.status) === 0) {
                for (var i = 0; i < ret.data.length; i++) {
                    var html = '<option value="' + ret.data[i].id + '">' + ret.data[i].name + '</option>';
                    $("#maintain_type").append(html);
                }
                layForm.render();
            }
        }
    });
}

/**
 * 新增/编辑
 */
function submitMaintain() {
    var url = null;
    // 0 : 新增 1 : 修改
    if (parseInt(type) === 0){
        url = '/property_system/admin/insertMaintain';
    } else {
        url = '/property_system/admin/updateMaintain';
    }

    var maintain_name  = $('#maintain_name').val();
    var maintain_phone = $('#maintain_phone').val();
    var maintain_type  = $('#maintain_type').val();
    var status         = $('input[type=radio]:checked').val();
    /*
    console.log(maintain_name);
    console.log(maintain_phone);
    console.log(maintain_type);
    console.log(status);
    */
    if (maintain_name === undefined || String(maintain_name) === ''){
        layer.msg('请输入维护员姓名');
        return;
    }
    if (maintain_phone === undefined || String(maintain_phone) === ''){
        layer.msg('请输入维护员联系方式');
        return;
    }
    if (maintain_type === undefined || String(maintain_type) === ''){
        layer.msg('请选择维护员维护类别');
        return;
    }
    if (status === undefined || String(status) === ''){
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
            'id'               : maintain_id,
            'business_type_id' : maintain_type,
            'maintain_name'    : maintain_name,
            'maintain_phone'   : maintain_phone,
            'status'           : status
        },
        success: function (ret) {
            console.log(ret);
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