
var table = null;

var form = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table','laydate'], function () {
        table = layui.table;

        // 查询所有推广物料
        selectMaterielInfoAll();
        form.render();
        table.render({
            elem: '#members_manager_table'
            //,cellMinWidth: 80
            , cols: [[
                  {type: 'checkbox'}
                , {field: 'id'             , title: 'ID'         , sort: true}
                , {field: 'admin_name'     , title: '操作人'      , sort: true}
                , {field: 'business_name'  , title: '受赠商家'    , sort: true}
                , {field: 'business_mobile', title: '受赠商家账号' , sort: true}
                , {field: 'materiel_name'  , title: '物料名称'    , sort: true}
                , {field: 'gift_number'    , title: '赠送数量'    , sort: true}
                , {field: 'back_number'    , title: '撤回数量'    , sort: true}
                , {field: 'back_status'    , title: '撤回状态'    , sort: true}
                , {field: 'gift_time_str'  , title: '赠送时间'    , sort: true}
            ]]
            , page: true
            , url: '/property_system/admin/selectMaterielGiftRecordAll'
            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
            , response: {
                dataName: 'data'
                , msgName: 'msg'
                , statusName: 'status'
                , statusCode: 0
            } //如果无需自定义数据响应名称，可不加该参数
        });
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#gift_time_str', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });
    });
    layui.use('form', function(){
        form = layui.form;
    });
}

//撤销赠送
function backMaterielGift() {
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table');
    var data = checkStatus.data;
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
        layer.confirm('是否要撤销赠送!',
            {
                btn: ['确定', '取消']
            }, function (index, layero) {
                //无法关闭这个消息框
                // layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                $.ajax({
                    url: "/property_system/admin/adminBackGiftMaterielByGiftId",
                    type: "POST",
                    dataType: 'json',
                    data: {
                        gift_id : model.id
                    },
                    success: function (data) {
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            yes: function () {
                                layer.closeAll('dialog');
                                location.reload();
                            }
                        });
                    }
                });
            }
        );
    }

}

//查询
function SearchBusinessLegalize() {

    var adminName      = document.getElementById("admin_name").value;
    var businessName   = document.getElementById("business_name").value;
    var businessMobile = document.getElementById("business_mobile").value;
    var materielId    = $('#materiel_id').val();
    var gift_number   = $('#gift_number').val();
    var back_number   = $('#back_number').val();
    var backStatus    = $('#back_status').val();
    var gift_time_str = $('#gift_time_str').val();

    table.render({
        elem: '#members_manager_table'
        //,cellMinWidth: 80
        , cols: [[
            {type: 'checkbox'}
            , {field: 'id'             , title: 'ID'         , sort: true}
            , {field: 'admin_name'     , title: '操作人'      , sort: true}
            , {field: 'business_name'  , title: '受赠商家'    , sort: true}
            , {field: 'business_mobile', title: '受赠商家账号' , sort: true}
            , {field: 'materiel_name'  , title: '物料名称'    , sort: true}
            , {field: 'gift_number'    , title: '赠送数量'    , sort: true}
            , {field: 'back_number'    , title: '撤回数量'    , sort: true}
            , {field: 'back_status'    , title: '撤回状态'    , sort: true}
            , {field: 'gift_time_str'  , title: '赠送时间'    , sort: true}
        ]]
        , page: true
        , url: '/property_system/admin/selectMaterielGiftRecordAll'
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        }
        , where: {
            adminName     : adminName,
            businessName  : businessName,
            businessMobile: businessMobile,
            materielId    : materielId,
            gift_number   : gift_number,
            back_number   : back_number,
            backStatus    : backStatus,
            gift_time_str : gift_time_str
        }
    });
}

function selectMaterielInfoAll() {
    $.ajax({
        url: "/property_system/admin/selectMaterielInfoAll",
        type: "POST",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (parseInt(data.status) === 0 && data.data != null && data.data.length > 0){
                for (let i = 0; i < data.data.length ; i++){
                    var obj = data.data[i];
                    var html = '<option value="'+obj.id+'">'+obj.name+'</option>';
                    $('#materiel_id').append(html)
                }
                form.render();
            }
        }
    });
}