var layui_table = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use('table', function () {
        layui_table = layui.table;
        // 查询表头
        selectMaterielInfoAll();
    });
}

function initTable(cols) {
    var mycars = [];
    mycars[0] = {field: 'business_name'     , title: '商家名称' ,sort : true};
    mycars[1] = {field: 'business_lower_str', title: '是否启用' ,sort : true};
    for (var i = 0; i < cols.length; i++) {
        mycars[i+2] = {field: cols[i].id, title: cols[i].name ,sort : true};
    }
    layui_table.render({
        elem: '#members_manager_table'
        , cols: [mycars]
        , page: true
        , url: '/property_system/admin/selectBusinessMaterielLastInfoAll'
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        , response: {
            dataName    : 'data'
            , msgName   : 'msg'
            , statusName: 'status'
            , statusCode: 0
            , countName : 'count'
        } //如果无需自定义数据响应名称，可不加该参数
    });
}

//查询表头
function selectMaterielInfoAll() {
    $.ajax({
        url: "/property_system/admin/selectMaterielInfoAll",
        type: "POST",
        dataType: 'json',
        success: function (data) {
            if (parseInt(data.status) === 0) {
                layuiColsArr = data.data;
                initTable(data.data);
            }
        }
    });
}

// 根据条件模糊查询
function SearchBusinessLegalize() {

    // 获取参数
    var store_name         = document.getElementById('store_name').value;
    // var business_lower_str = document.getElementById('business_lower_str').value;

    if (store_name === undefined || store_name == null || String(store_name) === '') {
        layer.msg('请输入搜索的商家!', {icon: 5});
        return;
    }

    $.ajax({
        url: "/property_system/admin/selectMaterielInfoAll",
        type: "POST",
        dataType: 'json',
        success: function (data) {
            if (parseInt(data.status) === 0) {
                var cols = data.data;
                var mycars = [];
                mycars[0] = {field: 'business_name'     , title: '商家名称' ,sort : true};
                mycars[1] = {field: 'business_lower_str', title: '是否启用' ,sort : true};
                for (var i = 0; i < cols.length; i++) {
                    mycars[i+2] = {field: cols[i].id, title: cols[i].name ,sort : true};
                }

                //方法级渲染
                layui_table.render({
                    elem: '#members_manager_table'
                    , cols: [mycars]
                    , page: true
                    , url: '/property_system/admin/selectBusinessMaterielLastInfoAll'
                    , method: 'post' //如果无需自定义HTTP类型，可不加该参数
                    , response: {
                        dataName    : 'data'
                        , msgName   : 'msg'
                        , statusName: 'status'
                        , statusCode: 0
                        , countName : 'count'
                    }
                    , where: {
                        'store_name'        : store_name
                        // 'business_lower_str':business_lower_str
                    }
                });


            }
        }
    });


}