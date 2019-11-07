$(function () {


    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    $('#integral-order-edit').submit(function () {

        console.log($('#integral-order-edit').serializeObject());

        $.ajax({
            url: "/property_system/admin/updateIntegralOrderExpress",
            type: "POST",
            headers: {
                'login_session': mMain.storage["login_session"]
            },
            dataType: 'json',
            data: {
                "user_id": mMain.storage["user_id"],
                "id": $('#id').val(),
                "expressName":$('select[name=express_name]').val(),
                "expressId" : $('input[name=express_id]').val()
            },
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0){
                    layer.open({
                        content: '发货成功',
                        btn: ['确定'],
                        yes: function(){
                            parent.layer.close(parent.layer.getFrameIndex(window.name));
                        }
                    });
                }
            }
        });


        return false;
    });

});