var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use('table', function () {
        table = layui.table;
        table.render({
            elem: '#members_manager_table'
            , cellMinWidth: 80
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', sort: true}
                , {field: 'name', title: '名称', sort: true}
            ]]
            , page: true
            , url: '/property_system/admin/selectWnkBusinessRegionAll'
            // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
            //	,request: {} //如果无需自定义请求参数，可不加该参数
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
    });

}

// 根据条件模糊查询
function Search() {
    var name = $('#name').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchWnkBusinessRegionInfoByConditions'
        ,cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: 'ID', sort: true}
            , {field: 'name', title: '名称', sort: true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'              : name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//新增管理员
function addBusiness() {
    layer.prompt({
        formType: 0,
        value: '',
        title: '请输入名称'
    }, function (value, index) {
        $.ajax({
            url: "/property_system/admin/insertWnkBusinessRegion",
            type: "POST",
            dataType: 'json',
            data: {
                name: value
            },
            success: function (data) {
                layer.open({
                    content: data.msg,
                    btn: ['确定'],
                    yes: function () {
                        layer.close(index);
                        location.reload();
                        return true;
                    }
                });
            }
        });
    });
}

//修改商户
function setBusiness() {
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
        layer.prompt({
            formType: 0,
            value: model.name,
            title: '请输入名称'
        }, function (value, index) {
            $.ajax({
                url: "/property_system/admin/updateWnkBusinessRegion",
                type: "POST",
                dataType: 'json',
                data: {
                    id: model.id,
                    name: value
                },
                success: function (data) {
                    layer.open({
                        content: data.msg,
                        btn: ['确定'],
                        yes: function () {
                            layer.close(index);
                            location.reload();
                            return true;
                        }
                    });
                }
            });
        });
    }
}

//删除
function deleteBusiness() {
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
        layer.confirm('确定要删除吗?', {btn: ['确定', '取消']},function () {
            $.ajax({
                url: "/property_system/admin/deleteWnkBusinessRegion",
                type: "POST",
                dataType: 'json',
                data: {
                    region_id : modle.id
                },
                success: function (data) {
                    layer.open({
                        content: data.msg,
                        btn: ['确定'],
                        yes: function () {
                            location.reload();
                            return true;
                        }
                    });
                }
            });
        })
    }
}
