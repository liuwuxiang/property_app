var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
    layui.use(['table', 'laydate'], function(){
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'},
                {field:'id',               title: 'id',sort:true           },
                {field:'maintain_name',    title: '维护员姓名',sort:true    },
                {field:'maintain_phone',   title: '联系方式',sort:true },
                {field:'maintain_type',    title: '所属分类',sort:true      },
                {field:'status',           title: '是否启用',sort:true      },
                {field:'create_time',      title: '创建时间',sort:true      }
            ]],
            page: true,
            url:'/property_system/admin/getMaintainAll',
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            response: {
                dataName: 'data',
                msgName: 'msg',
                statusName: 'status',
                statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#create_time' //指定元素id
            ,btns: ['confirm']
            ,type: 'datetime'//数据类型(时间)
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

    var maintain_name  = $('#maintain_name').val();
    var maintain_phone = $('#maintain_phone').val();
    var maintain_type  = $('#maintain_type').val();
    var status         = $('#status').val();
    var create_time    = $('#create_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchMaintain'
        ,cols: [[
            {type:'checkbox'},
            {field:'id',               title: 'id',sort:true           },
            {field:'maintain_name',    title: '维护员姓名',sort:true    },
            {field:'maintain_phone',   title: '联系方式',sort:true },
            {field:'maintain_type',    title: '所属分类',sort:true      },
            {field:'status',           title: '是否启用',sort:true      },
            {field:'create_time',      title: '创建时间',sort:true      }
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'maintain_name'   : maintain_name,
            'maintain_phone'  : maintain_phone,
            'maintain_type'   : maintain_type,
            'status'          : status,
            'create_time'     : create_time,
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
function addWnkMaintain() {
    layer.open({
        type: 2,
        title: '添加管理员',
        content: '/property_system/admin/joinWnkMaintainEdit',
        area: ['100%', '100%'],
        maxmin: true
    });
}

// 编辑维护员
function editWnkMaintain(){
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
            content: '/property_system/admin/joinWnkMaintainEdit?maintain_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}
