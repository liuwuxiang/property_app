var table = null ;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use('table', function () {
        table = layui.table;
        table.render({
            elem: '#member_level_manager_table',
            cellMinWidth: 80,
            cols: [[
                {type: 'checkbox'}
                ,{field: 'id',           title: 'ID',sort:true}
                ,{field: 'business_name',title: '商家名称',sort:true}
                ,{field: 'name',         title: '商品名称',sort:true}
                ,{field: 'price',        title: '商品价格',sort:true}
                ,{field: 'detail',       title: '商品详情',sort:true}
                ,{field: 'img',          title: '商品图片',templet:function (data) {
                    return "<img src='"+data.img+"'>";
                    },sort:true}
                ,{field:'is_recommend', title:'是否推荐' ,sort:true}
                ,{field:'is_checked',   title:'是否上架' ,sort:true}
            ]],
            page: true,
            url: '/property_system/admin/getAllIntegralBusinessGoods',
            method: 'post',
            response: {
                dataName  : 'data',
                msgName   : 'msg',
                statusName: 'status',
                statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
    });
    //加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}

// 推荐/不推荐
function setIntegralGoodsEdit() {
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
        var tisStr = "";
        var status = -1;
        if (String(model.is_recommend) === "不推荐"){
            tisStr = "是否推荐此商品";
            status = 1;
        } else {
            tisStr = "是否不推荐此商品";
            status = 0;
        }
        layer.confirm(tisStr,
            {
                btn: ['确定', '取消']
            },
            function () {
                $.ajax({
                    url:"/property_system/admin/updateBusinessIntegralGoodsRecommendStatus",
                    type:"POST",
                    dataType : 'json',
                    data:{
                        status : status,
                        goods_id : model.id
                    },
                    success:function(data){
                        parent.layer.alert(data.msg, {icon: 6});
                        layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    },
                });

            }
        );
    }

}


//商家或下架
function addIntegralGoods() {
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
        var tisStr = "";
        var status = -1;
        if (String(model.is_recommend) === "禁用"){
            tisStr = "是否启用此商品";
            status = 0;
        } else {
            tisStr = "是否下架此商品";
            status = 1;
        }
        layer.confirm(tisStr,
            {
                btn: ['确定', '取消']
            },
            function () {
                $.ajax({
                    url:"/property_system/admin/updateBusinessIntegralGoodsCheckedStatus",
                    type:"POST",
                    dataType : 'json',
                    data:{
                        status : status,
                        goods_id : model.id
                    },
                    success:function(data){
                        parent.layer.alert(data.msg, {icon: 6});
                        layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    },
                });

            }
        );
    }
}

// 根据条件模糊查询
function Search() {

    var business_name = $('#business_name').val();
    var name          = $('#name').val();
    var price         = $('#price').val();
    var is_recommend   = $('#is_recommend').val();
    var is_checked    = $('#is_checked').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchIntegralBusinessGoods'
        ,cols: [[
            {type: 'checkbox'}
            ,{field: 'id',           title: 'ID',sort:true}
            ,{field: 'business_name',title: '商家名称',sort:true}
            ,{field: 'name',         title: '商品名称',sort:true}
            ,{field: 'price',        title: '价格',sort:true}
            ,{field: 'detail',       title: '商品详情',sort:true}
            ,{field: 'img',          title: '商品图片',templet:function (data) {
                    return "<img src='"+data.img+"'>";
                },sort:true}
            ,{field:'is_recommend', title:'是否推荐' ,sort:true}
            ,{field:'is_checked',   title:'是否上架' ,sort:true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'business_name' : business_name,
            'name'          : name,
            'price'         : price,
            'is_recommend'  : is_recommend,
            'is_checked'    : is_checked,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}
