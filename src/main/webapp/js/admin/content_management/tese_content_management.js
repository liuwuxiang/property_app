var table = null ;

var layFrom = null ;

//初始化table组件以及数据
function initTableAndData(){
    layui.use('table', function(){
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'},
                {field:'id',      title: 'id',sort:true         },
                {field:'name',    title: '特色内容名称',sort:true},
                {field:'state',   title: '是否启用',sort:true    },
            ]],
            page: true,
            url:'/property_system/admin/getContentAll',
            method: 'post',
            where:{label_type:1},
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
    var name   = $('#name').val();
    var state  = $('#state').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchContentInfoByConditions'
        ,cols: [[
            {type:'checkbox'},
            {field:'id',      title: 'id',sort:true         },
            {field:'name',    title: '特色内容名称',sort:true},
            {field:'state',   title: '是否启用',sort:true    },
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'      : name,
            'state'     : state,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}


// 新增维护员
function addTeseContent() {
    layer.open({
        type: 2,
        title: '添加管理员',
        content: '/property_system/admin/joinInsertOrUpdateContentInfo?type=1',
        area: ['100%', '100%'],
        maxmin: true
    });
}

// 编辑维护员
function updateTeseContent(){
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else{
        var model = data[0];
        layer.open({
            type: 2,
            title: '添加管理员',
            content: '/property_system/admin/joinInsertOrUpdateContentInfo?content_id='+model.id+'&type=1',
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}
