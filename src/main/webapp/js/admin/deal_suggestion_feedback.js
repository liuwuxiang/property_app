//初始化视图数据
function initView() {
    var state = document.getElementById("state").value;
    $("#feed_state_select").val(state);
}

function saveDeal() {
    var record_id = document.getElementById("record_id").value;
    var recmark = document.getElementById("remark").value;
    var state = document.getElementById("feed_state_select").options[document.getElementById("feed_state_select").selectedIndex].value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/feedDealAction",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id,"state":state,"remark":recmark},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                layer.msg(data.msg, {icon: 6});
            }
            else{
                layer.msg(data.msg, {icon: 5});
            }
        },
    });
}