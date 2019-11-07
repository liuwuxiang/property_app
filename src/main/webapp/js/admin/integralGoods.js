var table = null;

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
                ,{field: 'id',          title: 'ID',sort:true}
                ,{field: 'name',        title: '商品名称',sort:true}
                ,{field: 'price',       title: '商品价格',sort:true}
                ,{field: 'trader',      title: '商家名称',sort:true}
                ,{field: 'detail',      title: '商品详情',sort:true}
                ,{field: 'img',         title: '商品图片',templet:function (data) {
                        return "<img src='"+data.img+"'>";
                    },sort:true}
                ,{field: 'isRecommend', title: '上架状态', templet: function (data) {
                        return parseInt(data.isRecommend) === 0 ? "是" : "否"
                    },sort:true
                }
                ,{field:'is_checked',  title:'是否推荐' ,sort:true}
            ]],
            page: true,
            url: '/property_system/admin/getAllIntegralGoods',
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

//修改商品分类
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
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '修改商品信息',
            content: '/property_system/admin/integralGoodsEdit?goods_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }

}

// 添加商品
function addIntegralGoods() {
    //self.window.location.href= "/property_system/admin/joinAddIntegralGoods";
    layer.open({
        type: 2,
        title: '新增商品',
        content: '/property_system/admin/joinAddIntegralGoods',
        area: ['100%', '100%'],
        maxmin: true
    });
}

// 根据条件模糊查询
function Search() {

    var name        = $('#name').val();
    var price       = $('#price').val();
    var trader      = $('#trader').val();
    var detail      = $('#detail').val();
    var isRecommend = $('#isRecommend').val();
    var is_checked  = $('#is_checked').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchIntegralGoods'
        ,cols: [[
            {type: 'checkbox'}
            ,{field: 'id',          title: 'ID',sort:true}
            ,{field: 'name',        title: '商品名称',sort:true}
            ,{field: 'price',       title: '商品价格',sort:true}
            ,{field: 'trader',      title: '商家名称',sort:true}
            ,{field: 'detail',      title: '商品详情',sort:true}
            ,{field: 'img',         title: '商品图片',templet:function (data) {
                    return "<img src='"+data.img+"'>";
                },sort:true}
            ,{field: 'isRecommend', title: '上架状态', templet: function (data) {
                    return parseInt(data.isRecommend) === 0 ? "是" : "否"
                },sort:true
            }
            ,{field:'is_checked',  title:'是否推荐' ,sort:true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'        : name,
            'price'       : price,
            'trader'      : trader,
            'detail'      : detail,
            'isRecommend' : isRecommend,
            'is_checked'  : is_checked,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}