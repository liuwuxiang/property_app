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
                {type: 'checkbox'},
                {field: 'id'          , title: 'ID',sort:true},
                {field: 'img_url_path', title: '轮播图图片',templet:function (data) {
                        return "<img src='"+data.img_url_path+"'>"},sort:true},
                {field: 'url'         , title: '跳转链接',sort:true},
                {field: 'position'    , title: '轮播图位置',sort:true},
                {field: 'is_checked'  , title: '是否启用',sort:true}
            ]],
            page: true,
            url: '/property_system/admin/selectCarouselInfoAll',
            method: 'post',
            response: {
                dataName  : 'data',
                msgName   : 'msg',
                statusName: 'status',
                statusCode: 0
            }
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

    var url         = $('#url').val();
    var position    = $('#position').val();
    var is_checked  = $('#is_checked').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchCarouselImg'
        ,cols: [[
            {type: 'checkbox'},
            {field: 'id'          , title: 'ID',sort:true},
            {field: 'img_url_path', title: '轮播图图片',templet:function (data) {
                    return "<img src='"+data.img_url_path+"'>"},sort:true},
            {field: 'url'         , title: '跳转链接',sort:true},
            {field: 'position'    , title: '轮播图位置',sort:true},
            {field: 'is_checked'  , title: '是否启用',sort:true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'url'        : url,
            'position'   : position,
            'is_checked' : is_checked,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//修改商品分类
function setIntegralGoodsEdit() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')
        , data = checkStatus.data;
    var length = data.length;
    if (parseInt(length) === 0) {
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
            title: '修改轮播图信息',
            content: '/property_system/admin/joinCarouselImgEdit?carousel_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }

}

// 添加商品
function addIntegralGoods() {
    layer.open({
        type: 2,
        title: '新增轮播图信息',
        content: '/property_system/admin/joinCarouselImgEdit',
        area: ['100%', '100%'],
        maxmin: true
    });
}
