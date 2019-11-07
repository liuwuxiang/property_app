
//初始化table组件以及数据
function initTableAndData(){
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#members_manager_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'checkbox'}
                ,{field:'id', title: 'ID',sort:true}
                ,{field:'name', title: '分类名称',sort:true}
                ,{field:'commdity_charge_way', title: '资费方式',sort:true}
                ,{field:'commodifty_price', title: '会员价格',sort:true}
                ,{field:'make_wnk_state', title: '万能卡权益',sort:true}
                ,{field:'discount_type', title: '优惠方式',sort:true}
                ,{field: 'photo_url',         title: '分类图标',templet:function (data) {
                        return "<img src='"+data.photo_url+"'>";
                    }}

            ]]
            ,page: true
            ,url:'/property_system/admin/getAllWnkBusinessType'
            // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
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

//添加万能卡商家分类信息
function saveWnkBusinessTypeInformation() {
    var type_name = document.getElementById("type_name").value;
    var commdity_charge_way = document.getElementById("zifei_way_select").options[document.getElementById("zifei_way_select").selectedIndex].value;
    var make_wnk_state = document.getElementById("wnk_make_state_select").options[document.getElementById("wnk_make_state_select").selectedIndex].value;
    var discount_type = document.getElementById("wnk_yh_select").options[document.getElementById("wnk_yh_select").selectedIndex].value;
    var member_price = document.getElementById("member_price").value;
    var zhekou_bili = document.getElementById("zhekou_bili").value;
    var logo_photo = document.getElementById("img_preview").src;
    var type_id = document.getElementById("type_id");
    if (type_name == undefined || type_name == ""){
        layer.msg('请输入分类名称', {icon: 5});
    }
    else if (commdity_charge_way == undefined){
        layer.msg('请选择资费方式', {icon: 5});
    }
    else if (make_wnk_state == undefined){
        layer.msg('请选择是否执行万能卡权益', {icon: 5});
    }
    else if (discount_type == 1 && commdity_charge_way == 1){
        layer.msg('折扣优惠方式与按年资费不可共同使用', {icon: 5});
    }
    else if (discount_type == 0 && (member_price == undefined || member_price == "")){
        layer.msg('请输入会员价格', {icon: 5});
    }
    else if (discount_type == 1 && (zhekou_bili == undefined || zhekou_bili == "")){
        layer.msg('请输入折扣比例', {icon: 5});
    }
    else if (discount_type == 1 && parseFloat(zhekou_bili) <= 0){
        layer.msg('折扣比例必须大于0', {icon: 5});
    }
    else if (discount_type == 1 && parseFloat(zhekou_bili) > 100){
        layer.msg('折扣比例不可大于100', {icon: 5});
    }
    else if (logo_photo == undefined || logo_photo == ""){
        layer.msg('请上传分类图标', {icon: 5});
    }
    else{
        if (discount_type == 1){
            member_price = zhekou_bili;
        }
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/insertWnkBusinessInformationAction",
            type:"POST",
            dataType : 'json',
            data:{"type_id":type_id,"name":type_name,"logo_photo":logo_photo,"commdity_charge_way":commdity_charge_way,"commodifty_price":member_price,"make_wnk_state":make_wnk_state,"discount_type":discount_type},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.msg(data.msg, {icon: 6});
                }
                else{
                    parent.layer.alert(data.msg, {icon: 5});
                }
            },
        });
    }
}

//获取分类详情
function getTypeDetail() {
    var type_id = document.getElementById("type_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getWnkBusinessTypeDetailAction",
        type:"POST",
        dataType : 'json',
        data:{"type_id":type_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                document.getElementById("type_name").value = data.data.name;
                document.getElementById("img_preview").src = data.data.logo_photo_id;
                var commdity_charge_way = data.data.commdity_charge_way;
                var make_wnk_state = data.data.make_wnk_state;
                var discount_type = data.data.discount_type;
                $("#zifei_way_select").val(commdity_charge_way);
                $("#wnk_make_state_select").val(make_wnk_state);
                $("#wnk_yh_select").val(discount_type);
                if(discount_type == 0){
                    $("#member_price_li").show();
                    $("#zhekou_li").hide();
                    document.getElementById("member_price").value = data.data.commodifty_price;
                }
                else{
                    $("#member_price_li").hide();
                    $("#zhekou_li").show();
                    document.getElementById("zhekou_bili").value = data.data.commodifty_price;
                }
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });

    $("select#wnk_yh_select").change(function(){
        var yh_type = $(this).val();
        if(yh_type == 0){
            $("#member_price_li").show();
            $("#zhekou_li").hide();
        }
        else{
            $("#member_price_li").hide();
            $("#zhekou_li").show();
        }
    });
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