var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
    layui.use('table', function(){
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            //,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'}
                ,{field:'id',                   title: 'ID',sort:true}
                ,{field:'name',          title: '物料类型',sort:true}
                ,{field:'limit_times',          title: '是否限制购买次数',sort:true}
                ,{field:'buy_number',          title: '限制次数',sort:true}
                ,{field:'background_photo', event: 'setSign', style:'cursor: pointer;',   title: '背景图',templet:function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid='+e.background_photo+'">';
                }}
                ,{field:'logo_photo_id',     title: '图标',templet:function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid='+e.logo_photo_id+'">';
                }}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllExtensionMaterielInformation'
            ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
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

    var name         = $('#name').val();
    var limit_times  = $('#limit_times').val();
    var buy_number   = $('#buy_number').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchExtensionMateriel'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id',                   title: 'ID',sort:true}
            ,{field:'name',          title: '物料类型',sort:true}
            ,{field:'limit_times',          title: '是否限制购买次数',sort:true}
            ,{field:'buy_number',          title: '限制次数',sort:true}
            ,{field:'background_photo', event: 'setSign', style:'cursor: pointer;',   title: '背景图',templet:function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid='+e.background_photo+'">';
                }}
            ,{field:'logo_photo_id',     title: '图标',templet:function (e) {
                    return '<img src="/property_system/images/getimage.do?imageid='+e.logo_photo_id+'">';
                }}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'         : name,
            'limit_times'  : limit_times,
            'buy_number'   : buy_number,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//修改物料
function setExtensionMateriel(){
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
    else {
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '修改推广物料',
            content: '/property_system/admin/setExtensionMaterielPage?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }

}


// 赠送物料给商家
function giftExtensionMateriel() {
    //页面层
    layer.open({
        type: 2,
        title: '赠送物料',
        skin: 'layui-layer-rim', //加上边框
        area: ['450px', '300px'], //宽高
        content: '/property_system/admin/giftExtensionMateriel'
    });
}