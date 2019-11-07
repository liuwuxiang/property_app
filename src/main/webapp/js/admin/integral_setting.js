function saveInformation() {
    var integral_coin = document.getElementById("integral_coin").value;
    var integral_rmb = document.getElementById("integral_rmb").value;
    if (integral_coin == undefined || integral_coin == ""){
        layer.msg('请输入积分与银币兑换比例', {icon: 5});
    }
    else if (integral_rmb == undefined || integral_rmb == ""){
        layer.msg('请输入积分与银币兑换比例', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setIntegralInformation",
            type:"POST",
            dataType : 'json',
            data:{"integral_rmb":integral_rmb,"integral_coin":integral_coin},
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