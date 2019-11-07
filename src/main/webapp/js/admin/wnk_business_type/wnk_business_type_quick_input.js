var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
    layui.use('table', function(){
        table = layui.table;

        table.render({
            elem: '#members_manager_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'}
                ,{field:'id',                 title: 'ID',     sort:true}
                ,{field:'name',               title: '输入内容',sort:true}
                ,{field:'business_type_name', title: '所属分类',sort:true}
            ]]
            ,page: true
            ,url:'/property_system/admin/selectAdminBusinessTypeQuickInputInfoAll'
            ,method: 'post'
            ,response: {
                dataName: 'data'
                ,msgName: 'msg'
                ,statusName: 'status'
                ,statusCode: 0
            }
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

    var name                = $('#name').val();
    var business_type_name  = $('#business_type_name').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchBusinessTypeQuickInput'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id',                 title: 'ID',     sort:true}
            ,{field:'name',               title: '输入内容',sort:true}
            ,{field:'business_type_name', title: '所属分类',sort:true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'                : name,
            'business_type_name'  : business_type_name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}


//新增商户分类
function insertBusinessTypeQuickInput() {
    //弹出即全屏
    layer.open({
        type: 2,
        title: '新增快速输入内容',
        content: '/property_system/admin/joinBusinessTypeQuickInputEdit',
        area: ['450px', '300px'], //宽高
        maxmin: true
    });
}

//修改商户分类
function updateBusinessTypeQuickInput(){
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
        //弹出即全屏
        layer.open({
            type: 2,
            title: '编辑快速输入内容',
            content: '/property_system/admin/joinBusinessTypeQuickInputEdit?quickId='+model.id,
            area: ['450px', '300px'], //宽高
            maxmin: true
        });
    }
}