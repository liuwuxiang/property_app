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
                {type:'checkbox'}
                ,{field:'id', title: 'ID',sort: true}
                ,{field:'store_name', title: '店铺',sort: true}
                ,{field:'type_name' , title: '分类',sort: true}
                ,{field:'state_str' , title: '状态',sort: true}
                ,{field:'balance'   , title: '余额', sort: true}
                ,{field:'contact_mobile', title: '联系电话', sort: true}
                ,{field:'tese_label', title: '特色内容', sort: true}
                ,{field:'fuwu_label', title: '服务内容', sort: true}
                ,{field:'legalize_stop_time', title: '审核时间', sort: true}
                ,{field:'join_time', title: '加入时间', sort: true}
                ,{field:'recommend_name',title:'推荐人',sort:true}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllLowerWnkBusiness'
            // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
            ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
            //	,request: {} //如果无需自定义请求参数，可不加该参数
            ,response: {
                dataName: 'data'
                ,msgName: 'msg'
                ,statusName: 'status'
                ,statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#join_time', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });
    });

    layui.use('form', function(){
        layFrom = layui.form;

        selectBusinessType();

        layFrom.render();
    });
}

//修改商户
function setBusiness(){
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
        var index = layer.open({
            type: 2,
            title: '修改商户',
            content: '/property_system/admin/joinSetWnkBusinessManager?business_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//修改服务和特色内容
function editBusinessLabel(){
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
        var index = layer.open({
            type: 2,
            title: '修改服务和特色内容',
            content: '/property_system/admin/joinBusinessLabel?business_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

// 充值积分或者余额
function chargeBusinessMoneyOrIntegral() {
    //页面层
    layer.open({
        type: 2,
        title: '商户充值',
        skin: 'layui-layer-rim', //加上边框
        area: ['450px', '300px'], //宽高
        content: '/property_system/admin/joinWnkBusinessRecharge'
    });
}


//上架/下架商户
function lowerBusinessStore() {
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
        layer.confirm('确定上架此商户？',
            {
                btn: ['确定', '取消'] //按钮
            }, function () {
                var loading_index = layer.load(1);
                $.ajax({
                    url:"/property_system/admin/lowerBusinessStore",
                    type:"POST",
                    dataType : 'json',
                    data:{
                        "business_id":model.id,
                        "is_lower":0
                    },
                    success:function(data){
                        layer.close(loading_index);
                        if (data.status == 0){
                            parent.layer.alert(data.msg, {icon: 6});
                            location.reload();
                        }
                        else{
                            parent.layer.alert(data.msg, {icon: 5});
                        }
                    },
                });
            },function () {

            });
    }
}

// 查询所有店铺分类
function selectBusinessType() {
    $.ajax({
        url:"/property_system/admin/getAllWnkBusinessType",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            console.log(data);
            // layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                var select = $('#wnk_business_type');
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                    select.append(html);
                }
            }
            layFrom.render();
            // else{
            //     parent.layer.alert(data.msg, {icon: 5});
            // }
        },
    });
}

// 根据条件模糊查询
function Search() {
    var store_name               = $('#store_name').val();
    var balance                  = $('#balance').val();
    var contact_mobile           = $('#contact_mobile').val();
    var wnk_business_type        = $('#wnk_business_type').val();
    var wnk_business_store_state = $('#wnk_business_store_state').val();
    var join_time                = $('#join_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchBusinessInfoByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID'}
            ,{field:'store_name', title: '店铺'}
            ,{field:'type_name' , title: '分类'}
            ,{field:'state_str' , title: '状态'}
            ,{field:'balance'   , title: '余额', sort: true}
            ,{field:'contact_mobile', title: '联系电话'}
            ,{field:'tese_label', title: '特色内容'}
            ,{field:'fuwu_label', title: '服务内容'}
            ,{field:'legalize_stop_time', title: '审核时间', sort: true}
            ,{field:'join_time', title: '加入时间', sort: true}
            ,{field:'recommend_name',title:'推荐人',sort:true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'type'                    : 1,
            'store_name'              : store_name,
            'balance'                 : balance,
            'contact_mobile'          : contact_mobile,
            'wnk_business_type'       : wnk_business_type,
            'wnk_business_store_state': wnk_business_store_state,
            'join_time'               : join_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}