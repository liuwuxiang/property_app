//类型图标
var type_icon = "";
//类型背景图
var type_background_photo = "";

//获取物料信息
function getMaterielInformation() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getMaterielInformationById",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                document.getElementById("name").value = data.data.name;
                document.getElementById("buy_number").value = data.data.buy_number;
                $("#buy_limit_select").val(data.data.limit_times);
                document.getElementById("img_logo").src = "/property_system/images/getimage.do?imageid="+data.data.logo_photo_id;
                document.getElementById("img_logo_background").src = "/property_system/images/getimage.do?imageid="+data.data.background_photo;
                //类型图标
                type_icon = data.data.logo_photo_id;
                //类型背景图
                type_background_photo = data.data.background_photo;
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//修改物料信息
function updateMateriel() {
    var record_id = document.getElementById("record_id").value;
    var limit_times = document.getElementById("buy_limit_select").options[document.getElementById("buy_limit_select").selectedIndex].value;
    var buy_number = document.getElementById("buy_number").value;

    if (buy_number == undefined || buy_number == ""){
        layer.msg('请输入购买次数', {icon: 5});
    }
    else if (type_icon == undefined || type_icon == ""){
        layer.msg('请上传物料图标', {icon: 5});
    }
    else if (type_background_photo == undefined || type_background_photo == ""){
        layer.msg('请上传物料背景图', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/materielInformationSetById",
            type:"POST",
            dataType : 'json',
            data:{"record_id":record_id,"logo_photo_id":type_icon,"background_photo":type_background_photo,"buy_number":buy_number,"limit_times":limit_times},
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
}

//图标选择事件
function photoChoose() {
    $("#img_upload").click();
}

//背景图选择事件
function photoChooseBackground() {
    $("#img_upload_background").click();
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
                $('#img_logo').attr("src", res.url_location);
                //类型图标
                type_icon = res.url;
            }
        },
        error: function (index, upload) {
            //请求异常回调
        }
    });

    layui.upload.render({
        elem: '#img_upload_background', //绑定元素
        url: '/property_system/images/savaimageMobile.do', //上传接口
        accept: 'images/*',
        method: "post",
        data: {
            "fileNameStr": "ajaxFile_background",
            "fileId": "img_upload_background"
        },
        before: function (data) {

        },
        done: function (res, index, upload) {
            console.log(res);
            //上传完毕回调
            if (res.error === 0) {
                $('#img_logo_background').attr("src", res.url_location);//类型图标
                type_background_photo = res.url;
            }
        },
        error: function (index, upload) {
            //请求异常回调
        }
    });
});