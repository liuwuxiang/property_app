var layui_table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use('table', function () {
        layui_table = layui.table;
        layui_table.render({
            elem: '#member_level_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type : 'checkbox'}
                , {field: 'id',             title: 'ID'     , sort: true}
                , {field: 'business_name',  title: '商家名称', sort: true}
                , {field: 'content',        title: '协议内容', sort: true}
                , {field: 'is_checked_str', title: '是否启用', sort: true}
            ]]
            , page: true
            , url: '/property_system/admin/adminSelectWnkBusinessOpenCardProtocolAll'
            , method: 'post'
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            }
        });
    });
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

// 根据条件模糊查询
function Search() {
    var business_name   = $('#business_name').val();
    var content         = $('#content').val();
    var is_checked_str  = $('#is_checked_str').val();

    //方法级渲染
    layui_table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchWnkBusinessOpenCardProtocol'
        , cols: [[
            {type : 'checkbox'}
            , {field: 'id',             title: 'ID'     , sort: true}
            , {field: 'business_name',  title: '商家名称', sort: true}
            , {field: 'content',        title: '协议内容', sort: true}
            , {field: 'is_checked_str', title: '是否启用', sort: true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'business_name'   : business_name,
            'content'         : content,
            'is_checked_str'  : is_checked_str,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//新增
function insertBusinessOpenCardProtocol() {
    //页面层
    var index = layer.open({
        type: 2,
        title: '新增商家会员卡开卡协议',
        content: '/property_system/admin/joinWnkBusinessOpenCardProtocolEdit',
        area: ['100%', '100%'],
        maxmin: true
    });
}

//修改
function updateBusinessOpenCardProtocol() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table');
    var data = checkStatus.data;
    var length = data.length;
    if (parseInt(length) === 0) {
        layer.msg('请选择一条数据!', {icon: 5});
        return;
    }
    if (parseInt(length) > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
        return
    }
    let model = data[0];
    var index = layer.open({
        type: 2,
        title: '更新商家会员卡开卡协议',
        area: ['100%', '100%'], //宽高
        content: '/property_system/admin/joinWnkBusinessOpenCardProtocolEdit?protocol_id='+model.id,
        maxmin: true
    });
}