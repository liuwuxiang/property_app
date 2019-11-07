var table = null;

var layFrom = null;
//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'],function () {
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID',sort:true}
                , {field: 'user_mobile', title: '充值用户',sort:true}
                , {field: 'recharge_amount', title: '充值金额',sort:true}
                , {field: 'integral_number', title: '获得积分',sort:true}
                , {field: 'recharge_type', title: '充值方式',sort:true}
                , {field: 'pay_type', title: '支付方式',sort:true}
                , {field: 'state', title: '充值状态',sort:true}
                , {field: 'system_order_no', title: '订单号',sort:true}
                , {field: 'recharge_time', title: '充值时间', sort: true}

            ]]
            , page: true
            , url: '/property_system/admin/getAllRechargeOrder'
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

        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#recharge_time', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });
    });

    layui.use('form', function(){
        layFrom = layui.form;
    });

}

//会员充值
function memberRecharge() {
    //页面层
    layer.open({
        type: 2,
        title: '通用积分充值',
        skin: 'layui-layer-rim', //加上边框
        area: ['450px', '300px'], //宽高
        content: '/property_system/admin/memberRecharge'
    });
}

// 根据条件模糊查询
function Search() {
    var username = $('#username').val();
    var system_order_no = $('#system_order_no').val();
    var pay_type = $('#pay_type').val();
    var pay_recharge_type = $('#pay_recharge_type').val();
    var pay_state = $('#pay_state').val();
    var recharge_time = $('#recharge_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchRechargeOrderByConditions'
        , cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID'}
            , {field: 'user_mobile', title: '充值用户'}
            , {field: 'recharge_amount', title: '充值金额'}
            , {field: 'integral_number', title: '获得积分'}
            , {field: 'recharge_type', title: '充值方式'}
            , {field: 'pay_type', title: '支付方式'}
            , {field: 'state', title: '充值状态'}
            , {field: 'system_order_no', title: '订单号'}
            , {field: 'recharge_time', title: '充值时间'}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'username' : username,
            'system_order_no' : system_order_no,
            'pay_type' : pay_type,
            'pay_recharge_type':pay_recharge_type,
            'pay_state': pay_state,
            'recharge_time':recharge_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}