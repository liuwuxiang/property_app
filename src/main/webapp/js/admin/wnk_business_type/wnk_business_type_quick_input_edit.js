
var layFrom = null;

/**
 * 快速输入ID
 * @type {number}
 */
var quickId = -1;

function initData() {
    layui.use('form', function(){
        layFrom = layui.form;
        selectBusinessTypeInfoAll();

        quickId = document.getElementById('quickId').value;

        if (quickId != null && quickId !== undefined && String(quickId) !== ''){
            selectBusinessTypeQuickInputInfoById(quickId);
        }
        layFrom.render();
    });
}

/**
 * 查询快速输入内容信息
 * @param quickId 快速输入ID
 */
function selectBusinessTypeQuickInputInfoById(quickId) {
    $.ajax({
        url:"/property_system/admin/selectBusinessTypeQuickInputInfoById",
        type:"POST",
        dataType : 'json',
        data:{
            quickId : quickId
        },
        success:function(data){
            if (parseInt(data.status)  === 0){
                $('#name').val(data.data.name);
                $('#business_type').val(data.data.business_type_id);
            }
            layFrom.render();
        },
    });
}

/**
 * 查询所有商家分类
 */
function selectBusinessTypeInfoAll() {
    $.ajax({
        url:"/property_system/admin/getAllWnkBusinessType",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status)  === 0 && data.data != null && data.data.length > 0){
                for (let i = 0;i< data.data.length;i++){
                    var obj = data.data[i];
                    var html = '<option value="'+obj.id+'">'+obj.name+'</option>';
                    $('#business_type').append(html)
                }
            }
            layFrom.render();
        },
    });
}

/**
 * 新增或跟新快捷输入
 */
function recharge() {
    let name = $('#name').val();
    let business_type = $('#business_type').val();

    if (name === undefined || String(name) === ""){
        layer.msg('请输入名称', {icon: 5});
    }
    else if(business_type === undefined || String(business_type) === ""){
        layer.msg('请选择商家分类', {icon: 5});
    } else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/insertOrUpdateBusinessTypeQuickInputInfo",
            type:"POST",
            dataType : 'json',
            data:{
                id              :quickId,
                name            : name,
                business_type_id:business_type
            },
            success:function(data){
                layer.close(loading_index);
                if (parseInt(data.status) === 0){
                    layer.open({
                        icon: 1,
                        title: '提示',
                        content: data.msg,
                        btn: ['确定'],
                        yes: function () {
                            parent.layer.close(parent.layer.getFrameIndex(window.name));
                            parent.location.reload();
                        }
                    });
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}
