var layui_from = null;

function initData() {

    layui.use('form', function () {
        layui_from = layui.form;
    });

    selectExtensionAll()
}

//
function selectExtensionAll() {
    $.ajax({
        url: "/property_system/admin/getAllExtensionMaterielInformation",
        type: "POST",
        dataType: 'json',
        data: {},
        success: function (data) {
            console.log(data);
            if (parseInt(data.status) === 0) {
                for (var i = 0; i < data.data.length; i++) {
                    var html = '<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>';
                    $('#type').append(html);
                }
                layui_from.render();
            }
        }
    });
}

// 赠送物料
function recharge() {

    var mobile = document.getElementById('mobile').value;

    var amount = document.getElementById('amount').value;

    var type = $('#type').val();

    if (mobile == undefined || mobile == '') {
        layer.msg('请输入手机号码!', {icon: 5});
        return;
    }

    if (amount == undefined || amount == '') {
        layer.msg('请输入赠送数量!', {icon: 5});
        return;
    }

    if (type == undefined || type == '') {
        layer.msg('请选择赠送物料类型!', {icon: 5});
        return;
    }


    $.ajax({
        url: "/property_system/admin/giftExtensionByAdmin",
        type: "POST",
        dataType: 'json',
        data: {
            mobile: mobile,
            amount: amount,
            extension_id: type
        },
        success: function (data) {
            layer.open({
                title: '提示',
                content: data.msg,
                yes: function (index, layero) {
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                    parent.location.reload();
                }
            });
        }
    });
}