//同意提现
function passWithdraw(){
    layer.confirm('确定同意此提现订单？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.closeAll('dialog');
        passwithdrawAction();
    }, function(){

    });
}

//拒绝提现
function noPasswithdraw(){
    layer.confirm('确定拒绝此提现订单？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.closeAll('dialog');
        noPasswithdrawAction();
    }, function(){

    });
}

//拒绝提现网络事件
function noPasswithdrawAction() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/refusingPresentActionWnkBusiness",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
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

//同意提现放款网络事件
function passwithdrawAction() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/wnkBusinessWithdrawPassFK",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
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

//设置为已提现
function alreadyPresented() {
    layer.confirm('确定将此订单设置为已提现？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.closeAll('dialog');
        alreadyPresentedAction();
    }, function(){

    });
}

//已提现设置事件
function alreadyPresentedAction() {
    var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/wankBusinessAlreadyPresentedAction",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
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