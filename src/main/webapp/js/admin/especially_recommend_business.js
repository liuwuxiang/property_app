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
                ,{field:'id', title: 'ID',sort:true}
                ,{field:'store_name', title: '店铺',sort:true}
                ,{field:'type_name' , title: '分类',sort:true}
                ,{field:'state_str' , title: '状态',sort:true}
                ,{field:'balance'   , title: '余额',sort:true}
                ,{field:'contact_mobile', title: '联系电话',sort:true}
                ,{field:'tese_label', title: '特色内容',sort:true}
                ,{field:'fuwu_label', title: '服务内容',sort:true}
                ,{field:'join_time', title: '加入时间',sort:true}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllEspeciallyRecommendWnkBusiness'
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
            elem: '#join_time', //指定元素id
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


//取消特别推荐
function cancelEspciallyRecommend(){
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
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/cancelBusinessEspciallyRecommend",
            type:"POST",
            dataType : 'json',
            data:{
                "business_id":model.id,
                "type":0
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
    var contact_mobile           = $('#contact_mobile').val();
    var wnk_business_type        = $('#wnk_business_type').val();
    var state_str                = $('#state_str').val();
    var balance                  = $('#balance').val();
    var join_time                = $('#join_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchEspeciallyRecommendWnkBusiness'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'store_name', title: '店铺',sort:true}
            ,{field:'type_name' , title: '分类',sort:true}
            ,{field:'state_str' , title: '状态',sort:true}
            ,{field:'balance'   , title: '余额',sort:true}
            ,{field:'contact_mobile', title: '联系电话',sort:true}
            ,{field:'tese_label', title: '特色内容',sort:true}
            ,{field:'fuwu_label', title: '服务内容',sort:true}
            ,{field:'join_time', title: '加入时间',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'type'                    : 0,
            'store_name'              : store_name,
            'contact_mobile'          : contact_mobile,
            'wnk_business_type'       : wnk_business_type,
            'state_str'               : state_str,
            'balance'                 : balance,
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

