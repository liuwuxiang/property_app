
var layFrom = null;


function initData() {

    layui.use('form', function(){
        layFrom = layui.form;
    });

    layFrom.render();
}



function recharge() {
    var mobile = document.getElementById("mobile").value;
    var amount = document.getElementById("amount").value;
    var type   = $('#type option:selected').val();

    if (mobile == undefined || mobile == ""){
        layer.msg('请输入充值手机号', {icon: 5});
    }
    else if(amount == undefined || amount == ""){
        layer.msg('请输入充值金额', {icon: 5});
    }else if(type == undefined || type == ""){
        layer.msg('请选择充值积分类型', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/BusinessRechargeAction",
            type:"POST",
            dataType : 'json',
            data:{"mobile":mobile,"amount":amount,'type':type},
            success:function(data){
                layer.close(loading_index);
                if (data.status == 0){
                    layer.open({
                        icon: 1,
                        title: '提示',
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