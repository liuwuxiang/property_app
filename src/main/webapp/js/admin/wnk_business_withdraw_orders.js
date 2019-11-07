var table = null;
var layFrom = null;
//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id',                     title: 'ID'      ,sort:true}
                , {field: 'bank_card_name',         title: '收款姓名',sort:true}
                , {field: 'back_name',              title: '收款银行',sort:true}
                , {field: 'bank_card_number',       title: '收款账号',sort:true}
                , {field: 'rmb_count_amount',       title: '提现金额',sort:true}
                , {field: 'service_charge_amount',  title: '手续费'  ,sort:true}
                , {field: 'real_payment_rmb_amount',title: '到账金额',sort:true}
                , {field: 'state',                  title: '提现状态',sort:true}
                , {field: 'apply_date',             title: '申请时间',sort:true}
                , {field: 'loan_date',              title: '处理时间',sort:true}

            ]]
            , page: true
            , url: '/property_system/admin/getAllWnkBusinessWithdrawOrders'
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
            elem: '#start_time', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#shop_time', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });
    });
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}

//提现订单详情查看
function lookWithdrawOrderDetail() {
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
            title: '提现订单详情',
            content: '/property_system/admin/wnkBusinessWithdrawOrderDetail?record_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}


//同意提现
function passWithdraw() {
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
        layer.confirm('确定同意此提现订单？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll('dialog');
            passwithdrawAction(model.id);
        }, function () {

        });
    }
}

//拒绝提现
function noPasswithdraw() {
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
        layer.confirm('确定拒绝此提现订单？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll('dialog');
            noPasswithdrawAction(model.id);
        }, function () {

        });
    }
}

//设置为已提现
function alreadyPresented() {
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
        layer.confirm('确定将此订单设置为已提现？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll('dialog');
            alreadyPresentedAction(model.id);
        }, function () {

        });
    }
}

//已提现设置事件
function alreadyPresentedAction(record_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url: "/property_system/admin/wankBusinessAlreadyPresentedAction",
        type: "POST",
        dataType: 'json',
        data: {"record_id": record_id},
        success: function (data) {
            layer.close(loading_index);
            if (data.status == 0) {
                parent.layer.alert(data.msg, {icon: 6});
            }
            else {
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//拒绝提现网络事件
function noPasswithdrawAction(record_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url: "/property_system/admin/refusingPresentActionWnkBusiness",
        type: "POST",
        dataType: 'json',
        data: {"record_id": record_id},
        success: function (data) {
            layer.close(loading_index);
            if (data.status == 0) {
                parent.layer.alert(data.msg, {icon: 6});
            }
            else {
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//同意提现放款网络事件
function passwithdrawAction(record_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url: "/property_system/admin/wnkBusinessWithdrawPassFK",
        type: "POST",
        dataType: 'json',
        data: {"record_id": record_id},
        success: function (data) {
            layer.close(loading_index);
            if (data.status == 0) {
                parent.layer.alert(data.msg, {icon: 6});
            }
            else {
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

// 根据条件模糊查询商家
function SearchBusiness() {
    var bank_card_name         = $('#bank_card_name').val();
    var back_name              = $('#back_name').val();
    var bank_card_number       = $('#bank_card_number').val();
    var rmb_count_amount       = $('#rmb_count_amount').val();
    var service_charge_amount  = $('#service_charge_amount').val();
    var real_payment_rmb_amount= $('#real_payment_rmb_amount').val();
    var order_state            = $('#order_state').val();
    var start_time             = $('#start_time').val();
    var shop_time              = $('#shop_time').val();

    if (new Date(start_time).getTime() > new Date(shop_time).getTime()) {
        layer.open({
            icon: 2,
            title: '提示',
            content: '申请时间不能大于处理时间'
        });
        return;
    }
    table.render({
        elem: '#members_manager_table'
        , cellMinWidth: 80
        , cols: [[
            {type: 'checkbox'}
            , {field: 'id',                     title: 'ID'      ,sort:true}
            , {field: 'bank_card_name',         title: '收款姓名',sort:true}
            , {field: 'back_name',              title: '收款银行',sort:true}
            , {field: 'bank_card_number',       title: '收款账号',sort:true}
            , {field: 'rmb_count_amount',       title: '提现金额',sort:true}
            , {field: 'service_charge_amount',  title: '手续费'  ,sort:true}
            , {field: 'real_payment_rmb_amount',title: '到账金额',sort:true}
            , {field: 'state',                  title: '提现状态',sort:true}
            , {field: 'apply_date',             title: '申请时间',sort:true}
            , {field: 'loan_date',              title: '处理时间',sort:true}

        ]]
        , page: true
        , url: '/property_system/admin/searchBusinessWithdrawOrdersInfoByConditions'
        , where: {
            'bank_card_name'          : bank_card_name,
            'back_name'               : back_name,
            'bank_card_number'        : bank_card_number,
            'rmb_count_amount'        : rmb_count_amount,
            'service_charge_amount'   : service_charge_amount,
            'real_payment_rmb_amount' : real_payment_rmb_amount,
            'order_state'             : order_state,
            'start_time'              : start_time,
            'shop_time'               : shop_time,
        } //如果无需传递额外参数，可不加该参数
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        //	,request: {} //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}