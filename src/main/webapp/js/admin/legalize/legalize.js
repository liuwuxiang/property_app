var table = null;

var layFrom = null;
//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        table = layui.table;

        table.render({
            //指定原始表格元素选择器（推荐id选择器）
            elem: '#members_manager_table'
            // ,cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id',                 title: 'ID',          sort:true}
                , {field: 'business_id',        title: '商家ID',      sort:true}
                , {field: 'store_name',         title: '店铺',        sort:true}
                , {field: 'business_type_str',  title: '店铺分类',    sort:true}
                , {field: 'phone',              title: '法人联系电话',sort:true}
                , {
                    field: 'id_card_facade_img',
                    style: 'cursor: pointer;',
                    title: '法人身份证正面',
                    event: 'id_card_facade_img',
                    templet: function (e) {
                        return '<img src="/property_system/images/getimage.do?imageid=' + e.id_card_facade_img + '">';
                    },sort:true
                }
                , {
                    field: 'id_card_rear_img',
                    title: '法人身份证背面',
                    event: 'id_card_rear_img',
                    templet: function (e) {
                        return '<img src="/property_system/images/getimage.do?imageid=' + e.id_card_rear_img + '">';
                    },sort:true
                }
                , {
                    field: 'business_license_img',
                    title: '营业执照图片',
                    event: 'business_license_img',
                    templet: function (e) {
                        return '<img src="/property_system/images/getimage.do?imageid=' + e.business_license_img + '">';
                    },sort:true
                }
                , {
                    field: 'license_img',
                    title: '许可证',
                    event: 'license_img',
                    templet: function (e) {
                        return '<img src="/property_system/images/getimage.do?imageid=' + e.license_img + '">';
                    },sort:true
                }
                , {
                    field: 'legalize_status', title: '审核状态', templet: function (e) {
                        return e.legalize_status == '-1' ? '未上传认证信息' : e.legalize_status == '0' ? '未认证' : e.legalize_status == '1' ? '认证通过' : '审核拒绝';
                    },sort:true
                }
                , {field: 'start_time', title: '审核时间', sort: true}
            ]]
            , page: true
            , url: '/property_system/admin/getWnkBusinessLegalizeAll'
            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var src_url = '/property_system/images/getimage.do?imageid=';
            if(String(obj.event) === 'id_card_facade_img'){
                src_url += data.id_card_facade_img;
            }
            if(String(obj.event) === 'id_card_rear_img'){
                src_url += data.id_card_rear_img;
            }
            if(String(obj.event) === 'business_license_img'){
                src_url += data.business_license_img;
            }
            if(String(obj.event) === 'license_img'){
                src_url += data.license_img;
            }
            var html = '' +
                '<div class="show_img">' +
                '   <img src="'+src_url+'">' +
                '</div>' +
                '';
            layer.open({
                type: 1,
                title:'查看图片',
                skin: 'layui-layer-rim', //加上边框
                // area: auto, //宽高
                content: html
            });
        });
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#start_time' //指定元素id
            ,btns: ['confirm']
            ,type: 'datetime'//数据类型(时间)
        });
    });


    layui.use('form', function () {
        layFrom = layui.form;
        selectBusinessType();
        layFrom.render();
    });
}

//提示
function updateBusinessNoLegalize() {
    layer.prompt({
        title: '输入拒绝原因', formType: 2,
        // 点击确定按钮事件
        yes: function () {
            // 取输入框数据
            var pass = $(document.getElementsByClassName('layui-layer-input')[0]).val();
            if (pass == undefined || pass == "") {
                layer.msg('请输入拒绝原因!', {icon: 5});
            }
            else {
                updateBusinessLegalize('2', pass);
                // layer.closeAll();
            }
        }
    });
}

// 认证通过或者认证拒绝
function updateBusinessLegalize(type, reson) {
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        , data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        $.ajax({
            url: "/property_system/admin/updateBusinessLegalize",
            type: "POST",
            dataType: 'json',
            data: {
                "legalize_id": model.id,
                'type': type,
                'business_id': model.business_id,
                'reson': reson
            },
            success: function (data) {
                layer.open({
                    content: data.msg,
                    btn: ['确定'],
                    yes: function (index, layero) {
                        location.reload();
                        return true;
                    }
                });
            }
        });
    }
}

// 查询所有店铺分类
function selectBusinessType() {
    $.ajax({
        url: "/property_system/admin/getAllWnkBusinessType",
        type: "POST",
        dataType: 'json',
        data: {},
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
                var list = data.data;
                var select = $('#wnk_business_type');
                for (var index = 0; index < list.length; index++) {
                    var obj = list[index];
                    var html = "<option value=\"" + obj.id + "\">" + obj.name + "</option>";
                    select.append(html);
                }
            }
            layFrom.render();
        },
    });
}

// 根据条件模糊查询
function SearchBusinessLegalize() {
    var business_id     = $('#business_id').val();//商家ID
    var store_name      = $('#store_name').val();//店铺
    var phone           = $('#phone').val();//法人联系电话
    var stop_time       = $('#stop_time').val();//审核时间
    var wnk_business_type = $('#wnk_business_type').val();
    var legalize_status = $('#legalize_status').val();//审核情况

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        , url: '/property_system/admin/adminSearchWnkBusinessLegalizeInfoByConditions'
        , cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID',sort:true}
            , {field: 'business_id', title: '商家ID',sort:true}
            , {field: 'store_name', title: '店铺',sort:true}
            , {field: 'business_type_str', title: '店铺分类',sort:true}
            , {field: 'phone', title: '法人联系电话',sort:true}
            , {
                field: 'id_card_facade_img',
                style: 'cursor: pointer;',
                title: '法人身份证正面',
                event: 'id_card_facade_img',
                templet: function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid=' + e.id_card_facade_img + '">';
                },sort:true
            }
            , {
                field: 'id_card_rear_img',
                title: '法人身份证背面',
                event: 'id_card_rear_img',
                templet: function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid=' + e.id_card_rear_img + '">';
                },sort:true
            }
            , {
                field: 'business_license_img',
                title: '营业执照图片',
                event: 'business_license_img',
                templet: function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid=' + e.business_license_img + '">';
                },sort:true
            }
            , {
                field: 'license_img',
                title: '许可证',
                event: 'license_img',
                templet: function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid=' + e.license_img + '">';
                },sort:true
            }
            , {field: 'legalize_status', title: '审核状态', sort: true}
            , {field: 'start_time', title: '申请时间', sort: true}

        ]]
        , id: 'members_manager_table'
        , page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        , where: {
            'business_id'      : business_id,
            'store_name'       : store_name,
            'phone'            : phone,
            'stop_time'        : stop_time,
            'wnk_business_type':wnk_business_type,
            'legalize_status'  : legalize_status,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

// 查看商家信息
function showBusinessLegalizeInfo() {
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        , data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        //弹出即全屏
        layer.open({
            type: 2,
            title: '商家认证信息',
            content: '/property_system/admin/adminJoinBusinessLegalizeInfo?business_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}