var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use('table', function () {
        table = layui.table;

        table.render({
            elem: '#members_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID',sort:true}
                , {field: 'name', title: '一级标签',sort:true}
                , {
                    field: 'photo_url', title: '标签图片', templet: function (data) {
                        return "<img src='" + data.photo_url + "'>";
                    }
                }
                , {field: 'state_str', title: '状态',sort:true}
                , {field: 'relation_type_name' ,title : '关联分类',sort:true}

            ]]
            , page: true
            , url: '/property_system/admin/getOneBusinessTagList'
            // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
            //	,request: {} //如果无需自定义请求参数，可不加该参数
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
    });

    //加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });

}

// 根据条件模糊查询
function Search() {

    var name                 = $('#name').val();
    var state_str            = $('#state_str').val();
    var relation_type_name   = $('#relation_type_name').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchBusinessTag'
        ,cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID',sort:true}
            , {field: 'name', title: '一级标签',sort:true}
            , {
                field: 'photo_url', title: '标签图片', templet: function (data) {
                    return "<img src='" + data.photo_url + "'>";
                }
            }
            , {field: 'state_str', title: '状态',sort:true}
            , {field: 'relation_type_name' ,title : '关联分类',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'               : name,
            'state_str'          : state_str,
            'relation_type_name' : relation_type_name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//新增商户一级标签
function addBusinessOneTag() {
    //弹出即全屏
    var index = layer.open({
        type: 2,
        title: '商户一级标签添加',
        content: '/property_system/admin/joinWnkBusinessTagAdd?makeType=0&last_id=-1',
        area: ['100%', '100%'],
        maxmin: true
    });

}

//修改商户一级标签
function setBusinessTag() {
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
        var index = layer.open({
            type: 2,
            title: '商户一级标签修改',
            content: '/property_system/admin/joinWnkBusinessTagSet?record_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//删除商户一级标签
function deleteBusinessTag() {
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
        layer.confirm('确定删除此标签？',
            {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: "/property_system/admin/deleteBusinessTag",
                    type: "POST",
                    dataType: 'json',
                    data: {business_type_id: model.id},
                    success: function (data) {
                        console.log(1);
                        if (data.status == 0){
                            layer.msg(data.msg, {icon: 1});
                        } else {
                            layer.msg(data.msg, {icon: 5});
                        }
                        return true;
                    }
                });
            },function () {

            });
    }
}

//一级标签排序
function oneTagSort() {
    //弹出即全屏
    var index = layer.open({
        type: 2,
        title: '一级标签排序',
        content: '/property_system/admin/joinWnkBusinessTagSortPage?record_id=-1',
        area: ['100%', '100%'],
        maxmin: true
    });
}

//二级标签排序
function twoTagSort() {
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
        var index = layer.open({
            type: 2,
            title: '二级标签排序',
            content: '/property_system/admin/joinWnkBusinessTagSortPage?record_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//管理二级标签
function twoTagManager() {
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
        var index = layer.open({
            type: 2,
            title: '商户二级标签管理-万能卡',
            content: '/property_system/admin/joinWnkBusinessTwoTagManager?one_tag_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}