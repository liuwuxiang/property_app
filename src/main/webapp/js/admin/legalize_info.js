var layFrom = null;

// 初始化数据
function iniBusinessLegalizeData() {
    layui.use('form', function () {
        layFrom = layui.form;
        selectBusinessLegalizeInfoById();
        layFrom.render();
    });
}

function selectBusinessLegalizeInfoById() {
    $.ajax({
        url: "/property_system/admin/selectBusinessLegalizeInfoById",
        type: "POST",
        dataType: 'json',
        data: {'id': $('#legalize_id').html()},
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
                // var list = data.data;
                // var select = $('#wnk_business_type');
                // for (var index = 0;index < list.length;index++){
                //     var obj = list[index];
                //     var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                //     select.append(html);
                // }
                $('#business_id').html(data.data.business_id);
                $('#store_name').html(data.data.store_name);

                $('#phone').html(data.data.phone);
                $('#business_type_str').html(data.data.business_type_str);
                $('#start_time').html(data.data.start_time);
                $('#legalize_status').html(data.data.legalize_status);
                $('#id_card_facade_img').attr('src', data.data.img_path + "?imageid=" + data.data.id_card_facade_img);
                $('#id_card_rear_img').attr('src', data.data.img_path + "?imageid=" + data.data.id_card_rear_img);
                $('#business_license_img').attr('src', data.data.img_path + "?imageid=" + data.data.business_license_img);
                $('#license_img').attr('src', data.data.img_path + "?imageid=" + data.data.license_img);
            }
            layFrom.render();
        }
    });
}

// 认证通过或者认证拒绝
function updateBusinessLegalize(type, reson) {
    var legalize_id = $('#legalize_id').html();
    var business_id = $('#business_id').html();
    $.ajax({
        url: "/property_system/admin/updateBusinessLegalize",
        type: "POST",
        dataType: 'json',
        data: {
            "legalize_id": legalize_id,
            'type': type,
            'business_id': business_id,
            'reson': reson
        },
        success: function (ret) {
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
        }
    });
}
