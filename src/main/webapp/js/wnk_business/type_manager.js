var storage = window.localStorage;

function initData() {
    getAllType();
}

//获取所有分类
function getAllType() {
    toast(2,"打开loading");
    $("#type_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllCommodityType",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li>"+
                        "<a class=\"type_name\">"+obj.type_name+"</a>"+
                            "<div class=\"button_div\">"+
                            "<input type=\"button\" value=\"删除\" class=\"delete_button\" onclick=\"deleteType("+obj.id+")\"/>"+
                            "<input type=\"button\" value=\"修改\" onclick=\"setType("+obj.id+")\"/>"+
                            "</div>"+
                            "</li>";
                        $("#type_ul").append(html);
                    }
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//添加分类
function addType(type_name) {
    if (type_name == "" || type_name == undefined){
        toast(1,"请输入分类名称");
    }
    else{
        toast(2,"打开loading");
        $.ajax({
            url:"/property_system/wnk_business_app/v1.0.0/addCommodityType",
            type:"POST",
            headers: {
                'login_session' : storage["login_session"]
            },
            dataType : 'json',
            data:{"business_id":storage["business_id"],"type_name":type_name},
            success:function(data){
                toast(1,data.msg);
                if (data.status == 0){
                    getAllType();
                }
            },
        });
    }
}

//删除分类事件
function deleteType(type_id) {
    if(window.confirm('删除分类将同步删除分类下商品,确认删除？')){
        deleteTypeNetwork(type_id);
        return true;
    }else{
        //alert("取消");
        return false;
    }
}

//修改分类事件
function setType(type_id) {
    var btnArray = ['取消', '确定'];
    mui.prompt('', '请输入分类新名称', '分类修改', btnArray, function(e) {
        if (e.index == 1) {
            setTypeNameNetwork(e.value,type_id);
//						info.innerText = '谢谢你的评语：' + e.value;
        } else {
//						info.innerText = '你点了取消按钮';
        }
    })
}

//修改分类网络事件
function setTypeNameNetwork(new_type_name,type_id) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/setCommodityTypeName",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type_id":type_id,"new_type_name":new_type_name},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                getAllType();
            }
        },
    });
}

//删除分类网络事件
function deleteTypeNetwork(type_id) {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/deleteCommodityType",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"],"type_id":type_id},
        success:function(data){
            toast(1,data.msg);
            if (data.status == 0){
                getAllType();
            }
        },
    });
}