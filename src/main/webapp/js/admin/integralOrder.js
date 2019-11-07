var table = null ;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        table = layui.table;
        table.render({
            elem: '#member_level_manager_table',
            cellMinWidth: 80,
            cols: [[
                {type: 'checkbox'},
                {field: 'id', title: 'ID',sort:true},
                {field: 'order_id', title: '订单ID',sort:true},
                {field: 'name', title: '商品名称',sort:true},
                {field: 'count', title: '购买数量',sort:true},
                {field: 'user_id', title: '收件人ID',sort:true},
                {field: 'startTime', title: '下单时间', sort:true},
                {field: 'username', title: '收件人姓名',sort:true},
                {field: 'phone', title: '收件人联系方式',sort:true},
                {field: 'address', title: '收件人地址',sort:true},
                {
                    field: 'status', title: '订单状态', templet: function (e) {
                        switch (e.status) {
                            case 0:
                                return "已付款";
                            case 1:
                                return "已发货";
                            case 2:
                                return "已收获";
                        }
                    },sort:true
                },
                {field: 'express_name', title: '快递商家',sort:true},
                {field: 'express_id', title: '快递单号',sort:true},


            ]],
            page: true,
            url: '/property_system/admin/getIntegralOrderAll',
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
            elem: '#startTime', //指定元素id
            btns: ['confirm'],
            type: 'datetime'//数据类型(时间)
        });
    });
    //加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}


//
//修改商品分类
function setMemberLevel() {
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
            title: '发货',
            content: '/property_system/admin/integralOrderEdit?order_id=' + model.id,
            area: ['25%', '40%'],
            maxmin: true
        });
    }

}

// 根据条件模糊查询
function Search() {

    var order_id     = $('#order_id').val();
    var name         = $('#name').val();
    var count        = $('#count').val();
    var user_id      = $('#user_id').val();
    var startTime    = $('#startTime').val();
    var username     = $('#username').val();
    var phone        = $('#phone').val();
    var address      = $('#address').val();
    var status       = $('#status').val();
    var express_name = $('#express_name').val();
    var express_id   = $('#express_id').val()

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchIntegralOrder'
        ,cols: [[
            {type: 'checkbox'},
            {field: 'id', title: 'ID',sort:true},
            {field: 'order_id', title: '订单ID',sort:true},
            {field: 'name', title: '商品名称',sort:true},
            {field: 'count', title: '购买数量',sort:true},
            {field: 'user_id', title: '收件人ID',sort:true},
            {field: 'startTime', title: '下单时间', sort:true},
            {field: 'username', title: '收件人姓名',sort:true},
            {field: 'phone', title: '收件人联系方式',sort:true},
            {field: 'address', title: '收件人地址',sort:true},
            {
                field: 'status', title: '订单状态', templet: function (e) {
                    switch (e.status) {
                        case 0:
                            return "已付款";
                        case 1:
                            return "已发货";
                        case 2:
                            return "已收货";
                    }
                },sort:true
            },
            {field: 'express_name', title: '快递商家',sort:true},
            {field: 'express_id', title: '快递单号',sort:true},

        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'order_id'    : order_id,
            'name'        : name,
            'count'       : count,
            'user_id'     : user_id,
            'startTime'   : startTime,
            'username'    : username,
            'phone'       : phone,
            'address'     : address,
            'status'      : status,
            'express_name': express_name,
            'express_id'  : express_id,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

// //
// //新增商品分类
// function addIntegralTypeEdit() {
//     layer.open({
//         type: 2,
//         title: '新增',
//         content: '/property_system/admin/integralTypeEdit',
//         area: ['100%', '100%'],
//         maxmin: true
//     });
//
//
// }
