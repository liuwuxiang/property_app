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
                , {field: 'business_id', title: '充值商家',sort:true}
                , {field: 'amount', title: '充值金额',sort:true}
                , {field: 'state', title: '充值状态',sort:true}
                , {field: 'order_no', title: '订单号',sort:true}
                , {field: 'create_time', title: '充值时间', sort: true}
            ]]
            , page: true
            , url: '/property_system/admin/getAllBusinessRechargeOrder'
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
            elem: '#create_time', //指定元素
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
    var business_id = $('#business_id').val();
    var amount      = $('#amount').val();
    var state       = $('#state').val();
    var order_no    = $('#order_no').val();
    var create_time = $('#create_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminBusinessSearchRechargeOrderByConditions'
        , cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID',sort:true}
            , {field: 'business_id', title: '充值商家',sort:true}
            , {field: 'amount', title: '充值金额',sort:true}
            , {field: 'state', title: '充值状态',sort:true}
            , {field: 'order_no', title: '订单号',sort:true}
            , {field: 'create_time', title: '充值时间', sort: true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'business_id' : business_id,
            'order_no' : order_no,
            'amount' : amount,
            'state': state,
            'create_time':create_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}