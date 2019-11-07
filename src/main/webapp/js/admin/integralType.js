var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {

    layui.use('table', function () {
        table = layui.table;
        table.render({
            elem: '#member_level_manager_table',
            cellMinWidth: 80,
            cols: [[
                {type: 'checkbox'},
                {field: 'id', title: 'ID',sort:true},
                {field: 'name', title: '分类名称',sort:true},
                {
                    field: 'img', title: '分类图片', templet: function (e) {

                        return '<img src="' + e.img + '" style="width: auto;height: auto">'
                    },sort:true
                },
                {
                    field: 'isDelete', title: '启用', templet: function (e) {
                        return e.isDelete == 0 ? "启用" : "禁用";
                    },sort:true
                }
            ]],
            page: true,
            url: '/property_system/admin/getAllIntegralType',
            // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            //	,request: {} //如果无需自定义请求参数，可不加该参数
            response: {
                dataName: 'data',
                msgName: 'msg',
                statusName: 'status',
                statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
    });
    //加载form搜索表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}

// 根据条件模糊查询
function Search() {
    var name      = $('#name').val();
    var isDelete  = $('#isDelete').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchIntegralTypeInfoByConditions'
        ,cols: [[
            {type: 'checkbox'},
            {field: 'id', title: 'ID',sort:true},
            {field: 'name', title: '分类名称',sort:true},
            {
                field: 'img', title: '分类图片', templet: function (e) {

                    return '<img src="' + e.img + '" style="width: auto;height: auto">'
                },sort:true
            },
            {
                field: 'isDelete', title: '启用', templet: function (e) {
                    return e.isDelete == 0 ? "启用" : "禁用";
                },sort:true
            }
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'     : name,
            'isDelete' : isDelete,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//
//修改商品分类
function setIntegralTypeEdit() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')
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
            title: '修改商品分类',
            content: '/property_system/admin/integralTypeEdit?type_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }

}

//
//新增商品分类
function addIntegralTypeEdit() {
    layer.open({
        type: 2,
        title: '新增',
        content: '/property_system/admin/integralTypeEdit',
        area: ['100%', '100%'],
        maxmin: true
    });


}
