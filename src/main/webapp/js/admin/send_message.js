function sendMessage() {
    var message_title = document.getElementById("message_title").value;
    var message_object = document.getElementById("message_object_select").options[document.getElementById("message_object_select").selectedIndex].value;
    var message_content = document.getElementById("message_content").value;
    if (message_title == undefined || message_title == undefined){
        layer.msg('请输入消息标题', {icon: 5});
    }
    else if (message_content == undefined || message_content == undefined){
        layer.msg('请输入消息内容', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/sendNewNotice",
            type:"POST",
            dataType : 'json',
            data:{"title":message_title,"receiving_object":message_object,"content":message_content},
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
}