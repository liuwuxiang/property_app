//初始化table组件以及数据
function initTableAndData(){
    var one_tag_id = document.getElementById("one_tag_id").value;
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#members_manager_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'}
                ,{field:'id', title: 'ID',sort:true}
                ,{field:'name', title: '二级标签',sort:true}
                ,{field: 'photo_url',         title: '标签图片',templet:function (data) {
                    return "<img src='"+data.photo_url+"'>";
                }}
                ,{field:'state_str', title: '状态',sort:true}

            ]]
            ,page: true
            ,url:'/property_system/admin/getTwoBusinessTagList'
            ,where: {one_tag_id: one_tag_id} //如果无需传递额外参数，可不加该参数
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
}

//新增商户一级标签
function addBusinessOneTag(){
    var one_tag_id = document.getElementById("one_tag_id").value;
    //弹出即全屏
    var index = layer.open({
        type: 2,
        title: '商户二级标签添加',
        content: '/property_system/admin/joinWnkBusinessTagAdd?makeType=1&last_id='+one_tag_id,
        area: ['100%', '100%'],
        maxmin: true
    });

}



//修改商户一级标签
function setBusinessTag(){
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
            title: '商户二级标签修改',
            content: '/property_system/admin/joinWnkBusinessTagSet?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}


//删除商户二级标签
function deleteBusinessTag() {
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
        layer.confirm('确定删除此标签？',
            {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: "/property_system/admin/deleteBusinessTag",
                    type: "POST",
                    dataType: 'json',
                    data: {business_type_id: model.id},
                    success: function (data) {
                        if (data.status == 0){
                            layer.msg(data.msg, {icon: 1});
                        } else {
                            layer.msg(data.msg, {icon: 5});
                        }
                        return true;
                    }
                });
            },function () {

            });
    }
}
