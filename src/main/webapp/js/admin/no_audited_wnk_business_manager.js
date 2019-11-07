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
                ,{field:'id',                title: 'ID'      ,sort: true}
                ,{field:'store_name',        title: '店铺'    ,sort: true}
                ,{field:'business_type_name',title: '分类'    ,sort: true}
                ,{field:'contact_name',      title: '联系人'  ,sort: true}
                ,{field:'contact_mobile',    title: '联系电话',sort: true}
                ,{field:'state_str',         title: '状态'    ,sort: true}
                ,{field:'submit_date_str',   title: '申请时间',sort: true}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllNoAuditedWnkBusiness'
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
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#submit_date_str', //指定元素id
            btns: ['confirm'],
            type: 'datetime'//数据类型(时间)
        });
    });
    layui.use('form', function(){
        layFrom = layui.form;
        selectBusinessType();
        layFrom.render();
    });
}

//查看申请详情
function lookAuditedDetail(){
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
            title: '申请详情',
            content: '/property_system/admin/joinNoAuditedDetail?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
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

    var store_name          = $('#store_name').val();
    var wnk_business_type   = $('#wnk_business_type').val();
    var contact_name        = $('#contact_name').val();
    var contact_mobile      = $('#contact_mobile').val();
    var state_str           = $('#state_str').val();
    var submit_date_str     = $('#submit_date_str').val();


    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchNoAuditedWnkBusiness'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id',                title: 'ID'      ,sort: true}
            ,{field:'store_name',        title: '店铺'    ,sort: true}
            ,{field:'business_type_name',title: '分类'    ,sort: true}
            ,{field:'contact_name',      title: '联系人'  ,sort: true}
            ,{field:'contact_mobile',    title: '联系电话',sort: true}
            ,{field:'state_str',         title: '状态'    ,sort: true}
            ,{field:'submit_date_str',   title: '申请时间',sort: true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'type'              : 0,
            'store_name'        : store_name,
            'wnk_business_type' : wnk_business_type,
            'contact_name'      : contact_name,
            'contact_mobile'    : contact_mobile,
            'state_str'         : state_str,
            'submit_date_str'   : submit_date_str
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}