var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        table = layui.table;
        table.render({
            elem: '#member_level_manager_table',
            cellMinWidth: 80,
            cols: [[
                {type: 'checkbox'},
                {field: 'id',            title: 'ID',       sort: true},
                {field: 'title',         title: '问题标题', sort: true},
                {field: 'open_way_str',  title: '打开方式', sort: true},
                {field: 'content',       title: '问题内容', sort: true},
                {field: 'type_str',      title: '类别',     sort: true},
                {field: 'creation_time', title: '创建时间', sort: true}
            ]],
            page: true,
            url: '/property_system/admin/selectProblemAll',
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            response: {
                dataName: 'data',
                msgName: 'msg',
                statusName: 'status',
                statusCode: 0
            }
        });
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#creation_time' //指定元素id
            ,btns: ['confirm']
            ,type: 'datetime'//数据类型(时间)
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

    var title         = $('#title').val();
    var open_way_str  = $('#open_way_str').val();
    var content       = $('#content').val();
    var type_str      = $('#type_str').val();
    var creation_time = $('#creation_time').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchProblem'
        ,cols: [[
            {type: 'checkbox'},
            {field: 'id',            title: 'ID',       sort: true},
            {field: 'title',         title: '问题标题', sort: true},
            {field: 'open_way_str',  title: '打开方式', sort: true},
            {field: 'content',       title: '问题内容', sort: true},
            {field: 'type_str',      title: '类别',     sort: true},
            {field: 'creation_time', title: '创建时间', sort: true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'title'         : title,
            'open_way_str'  : open_way_str,
            'content'       : content,
            'type_str'      : type_str,
            'creation_time' : creation_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}


/**
 * 修改常见问题
 */
function editProblem() {
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
        layer.open({
            type: 2,
            title: '编辑常见问题',
            content: '/property_system/admin/joinEditProblem?problem_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

/**
 * 新增常见问题
 */
function addProblem() {
    layer.open({
        type: 2,
        title: '新增常见问题',
        content: '/property_system/admin/joinAddProblem',
        area: ['100%', '100%'],
        maxmin: true
    });
}

/**
 * 删除常见问题
 */
function deleteProblem() {
    //找到要删除的table
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')//获取表格选中行
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
                url: '/property_system/admin/deleteProblemById',
                data: {
                    id: modle.id,
                },
                type: "post",
                dataType: "json",
                success: function (ret) {
                    if (parseInt(ret.status) === 0) {
                        layer.open({
                            offset: ['40%', '40%'],
                            title: '消息:',
                            content: ret.msg,
                            btn: ['确定'],
                            // yes: function () {
                            //     // parent.layer.close(parent.layer.getFrameIndex(window.name));
                            //     // parent.location.reload();
                            // }
                        });
                    } else {
                        layer.open({
                            offset: ['40%', '40%'],
                            title: '消息:',
                            content: ret.msg
                        });
                    }
                }
            });
        })
    }
}