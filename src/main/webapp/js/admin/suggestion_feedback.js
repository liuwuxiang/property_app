var layTable = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        layTable = layui.table;
        layTable.render({
            elem: '#members_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID',sort:true}
                , {field: 'user_name', title: '联系人',sort:true}
                , {field: 'user_mobile', title: '联系电话',sort:true}
                , {field: 'content', title: '反馈内容'}
                , {field: 'feedback_date', title: '反馈时间',sort:true}
                , {field: 'state', title: '处理状态',sort:true}
                , {field: 'remark', title: '备注'}

            ]]
            , page: true
            , url: '/property_system/admin/getAllSuggestionFeed'
            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });

        //监听单元格事件
        layTable.on('row(test)', function(obj){
            // console.log(1111);
            var data = obj.data;
            // layer.alert(JSON.stringify(data), {
            //     title: '当前行数据：'
            // });

            var index = layer.open({
                type: 2,
                title: '反馈详情信息',
                content: '/property_system/admin/suggestionFeedbackDetail?record_id=' + data.id,
                area: ['100%', '100%'],
                maxmin: true
            });

        });
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#feedback_date', //指定元素id
            btns: ['confirm'],
            type: 'datetime'
        });
    });

//加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });

}

//查看反馈详情
function lookDetail() {
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
            title: '反馈详情信息',
            content: '/property_system/admin/suggestionFeedbackDetail?record_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//反馈处理
function dealFeedback() {
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
            title: '反馈处理',
            content: '/property_system/admin/dealSuggestionFeedback?record_id=' + model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var user_name = document.getElementById("user_name").value;
    var user_mobile = document.getElementById("user_mobile").value;
    var content = document.getElementById("content").value;
    var feedback_date = document.getElementById("feedback_date").value;
    var state = document.getElementById("state").value;
    var remark = document.getElementById("remark").value;

    //方法更新渲染
    layTable.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchSuggestionFeedByConditions'
        ,cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID',sort:true}
            , {field: 'user_name', title: '联系人',sort:true}
            , {field: 'user_mobile', title: '联系电话',sort:true}
            , {field: 'content', title: '反馈内容'}
            , {field: 'feedback_date', title: '反馈时间',sort:true}
            , {field: 'state', title: '处理状态',sort:true}
            , {field: 'remark', title: '备注'}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'user_name'     : user_name,
            'user_mobile'   : user_mobile,
            'content'       : content,
            'feedback_date' : feedback_date,
            'state'         : state,
            'remark'        : remark,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}