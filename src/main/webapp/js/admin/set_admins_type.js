var form = null;
layui.use('form',function(){
    form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    initView();
});

//视图初始化
function initView(){
    initRichTextBox();
    getOneMenus();
}

//保存事件
function saveAction() {
    var menus = document.getElementsByName('like[write]');
    var value = new Array();
    for(var i = 0; i < menus.length; i++){
        if(menus[i].checked){
            value.push(menus[i].id);
        }
    }
    var menusIds = value.join(',');
    var type_name = document.getElementById("type_name").value;
    var type_id = document.getElementById("record_id").value;
    if (type_name == undefined || type_name == ""){
        layer.msg('请输入类别名称!', {icon: 5});
    }
    else if (value.length <= 0){
        layer.msg('请选择类别权限!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setAdminsTypeById",
            type:"POST",
            dataType : 'json',
            data:{"type_name":type_name,"menusIds":menusIds,"type_id":type_id},
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

//初始化富文本框
function initRichTextBox(){
    layui.use('layedit', function(){
        var layedit = layui.layedit;
        layedit.build('course_detail', {
            height: 600, //设置编辑器高度
            uploadImage: {url: '/upload/', type: 'post'}, //设定图片上传接口
            tool: [
                'strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线

                ,'|' //分割线

                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'link' //超链接
                ,'unlink' //清除链接
                ,'image' //插入图片
            ]
        });
    });

}


//获取所有一级菜单
function getOneMenus() {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getAllAdminOneMenus",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<div class=\"checkbox_div\">"+
                        "<input type=\"checkbox\" name=\"like[write]\" title=\""+obj.name+"\" id=\""+obj.id+"\">"+
                        "</div>";
                    $(".layui-form").append(html);
                }
                getTypeInformation();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//获取分类信息
function getTypeInformation() {
    var type_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/selectAdminsTypeById",
        type:"POST",
        dataType : 'json',
        data:{"type_id":type_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                document.getElementById("type_name").value = data.data.type_name;
                var menusIds = data.data.access_rights;
                var menusIdsArray = menusIds.split(',');
                for (var index = 0;index < menusIdsArray.length;index++){
                    var ids = menusIdsArray[index];
                    // document.getElementById(ids).checked = checked;
                    $('#'+ids).prop("checked", true);
                }
                form.render();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}