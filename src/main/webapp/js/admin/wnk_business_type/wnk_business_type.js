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
                ,{field:'id',                  title: 'ID',        sort:true}
                ,{field:'name',                title: '分类名称',  sort:true}
                ,{field:'commdity_charge_way', title: '资费方式',  sort:true}
                ,{field:'commodifty_price',    title: '会员价格',  sort:true}
                ,{field:'make_wnk_state',      title: '万能卡权益',sort:true}
                ,{field:'discount_type',       title: '优惠方式',  sort:true}
                ,{field: 'photo_url',          title: '分类图标',templet:function (data) {
                    return "<img src='"+data.photo_url+"'>";
                }}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllWnkBusinessType'
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
    });

    //加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}

// 根据条件模糊查询
function Search() {

    var name                 = $('#name').val();
    var commdity_charge_way  = $('#commdity_charge_way').val();
    var commodifty_price     = $('#commodifty_price').val();
    var make_wnk_state       = $('#make_wnk_state').val();
    var discount_type        = $('#discount_type').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchWnkBusinessType'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id',                  title: 'ID',        sort:true}
            ,{field:'name',                title: '分类名称',  sort:true}
            ,{field:'commdity_charge_way', title: '资费方式',  sort:true}
            ,{field:'commodifty_price',    title: '会员价格',  sort:true}
            ,{field:'make_wnk_state',      title: '万能卡权益',sort:true}
            ,{field:'discount_type',       title: '优惠方式',  sort:true}
            ,{field: 'photo_url',          title: '分类图标',templet:function (data) {
                    return "<img src='"+data.photo_url+"'>";
                }}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'                 : name,
            'commdity_charge_way'  : commdity_charge_way,
            'commodifty_price'     : commodifty_price,
            'make_wnk_state'       : make_wnk_state,
            'discount_type'        : discount_type,
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
function addWnkBusinessType() {
//弹出即全屏
    var index = layer.open({
        type: 2,
        title: '新增商户分类',
        content: '/property_system/admin/addWnkBusinessType',
        area: ['100%', '100%'],
        maxmin: true
    });
}

//修改商户分类
function setWnkBusinessType(){
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
            title: '修改商家分类',
            content: '/property_system/admin/joinWnkTypeSetPage?type_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//删除商户分类
function deleteWnkBusinessType() {
    //找到要删除的table
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')//获取表格选中行
        , data = checkStatus.data;//获取选中行的数据
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据', {icon: 5});
    } else if (length > 1) {
        layer.msg('只能选择一条数据', {icon: 5});
    } else {
        var modle = data[0];
        //弹出询问框
        layer.confirm('确定要删除此商品分类吗?', {btn: ['确定', '取消']}, function () {
            //
            // console.log(e);
            // //关闭
            // layer.closeAll('dialog');
            // layer.msg('删除成功',{icon:6});
            $.ajax({
                url: '/property_system/admin/deleteWnkBusinessTypeById',
                data: {
                    id: modle.id,
                },
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (parseInt(data.status) === 0) {
                        layer.open({
                            offset: ['40%', '40%'],
                            title: '消息:',
                            content: data.msg,
                            btn: ['确定'],
                        });
                    } else {
                        layer.open({
                            offset: ['40%', '40%'],
                            title: '消息:',
                            content: data.msg
                        });
                    }
                }
            });
        })
    }
}