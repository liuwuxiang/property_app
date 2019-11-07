var tagIdsArray = [];
//获取标签
function getTag() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/wnkBusinessTagSortGetTagAction",
        type:"POST",
        dataType : 'json',
        data:{
            "one_tag_id":record_id
        },
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "<li>"+
                                "<a>"+obj.name+"</a>"+
                                "<input type=\"number\" name=\"title\" placeholder=\"请输入序号\" value=\""+obj.sort_index+"\" id=\"tag_"+obj.id+"\" autocomplete=\"off\" class=\"layui-input\">"+
                               "</li>";
                    tagIdsArray.push(obj.id);
                    $(".set_member_level_ul").append(html);
                }
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}


//保存排序内容
function saveTagSort() {
    if (tagIdsArray.length <= 0){
        layer.msg('当前无可排序标签!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        var dataArray = [];
        for(var index = 0; index < tagIdsArray.length; index++){
            var inputObject = document.getElementById("tag_"+tagIdsArray[index]);
            if (inputObject != undefined){
                var sortIndex = inputObject.value;
                if(sortIndex == undefined || sortIndex == ""){
                    layer.close(loading_index);
                    layer.msg('您有标签未输入序号!', {icon: 5});
                    return;
                }
                else{
                    var row = {};
                    row.record_id = tagIdsArray[index];
                    row.tag_index = sortIndex;
                    dataArray.push(row);
                }
            }
        }
        var jsonStr = JSON.stringify(dataArray);
        $.ajax({
            url:"/property_system/admin/updateWnkBusinessTagSortIndex",
            type:"POST",
            dataType : 'json',
            data:{
                "tag_contents":jsonStr
            },
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