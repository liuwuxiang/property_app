function addAction() {
    var makeType = document.getElementById("makeType").value;
    var last_id = document.getElementById("last_id").value;

    var photo_url = document.getElementById("img_preview").src;
    var name = document.getElementById("name").value;
    var state = document.getElementById("tag_state_select").options[document.getElementById("tag_state_select").selectedIndex].value;
    if (name == undefined || name == ""){
        layer.msg('请输入商户标签名称', {icon: 5});
    }
    else if (photo_url == undefined || photo_url == ""){
        layer.msg('请上传标签图标', {icon: 5});
    }
    else{
        if (makeType == 0){
            var business_type_id = document.getElementById("relation_business_type_select").options[document.getElementById("relation_business_type_select").selectedIndex].value;
            var loading_index = layer.load(1);
            $.ajax({
                url:"/property_system/admin/AddOneBusinessTag",
                type:"POST",
                dataType : 'json',
                data:{"name":name,"state":state,"photo_url":photo_url,"business_type_id":business_type_id},
                success:function(data){
                    layer.close(loading_index);
                    if (data.status == 0){
                        layer.open({
                            offset: ['40%', '40%'],
                            title: '消息:',
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
        else{
            var loading_index = layer.load(1);
            $.ajax({
                url:"/property_system/admin/addTwoBusinessTag",
                type:"POST",
                dataType : 'json',
                data:{"name":name,"state":state,"last_id":last_id,"photo_url":photo_url},
                success:function(data){
                    layer.close(loading_index);
                    if (data.status == 0){
                        parent.layer.alert(data.msg, {icon: 6});
                    }
                    else{
                        parent.layer.alert(data.msg, {icon: 5});
                    }
                },
            });
        }

    }
}

//初始化内容
function initData() {
    var makeType = document.getElementById("makeType").value;
    if (makeType == 0){
        $("#relation_business_type").show();
        //获取所有商家分类
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/getAllWnkBusinessType",
            type:"POST",
            dataType : 'json',
            data:{},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    var list = data.data;
                    for(var index = 0;index < list.length;index++){
                        var obj = list[index];
                        var html = "<option value=\""+obj.id+"\">"+obj.name+"</option>";
                        $("#relation_business_type_select").append(html);
                    }
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}

//图片选择事件
function photoChoose() {
    $("#img_upload").click();
}


layui.use('upload', function () {
    layui.upload.render({
        elem: '#img_upload', //绑定元素
        url: '/property_system/images/savaimageMobile.do', //上传接口
        accept: 'images/*',
        method: "post",
        data: {
            "fileNameStr": "ajaxFile",
            "fileId": "img_upload"
        },
        before: function (data) {

        },
        done: function (res, index, upload) {
            console.log(res);
            //上传完毕回调
            if (res.error === 0) {
                $('#img_preview').attr("src", res.url_location);
            }
        },
        error: function (index, upload) {
            //请求异常回调
        }
    });
});