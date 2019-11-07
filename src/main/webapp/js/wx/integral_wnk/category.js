var wnkCategory = {
    storage:window.localStorage,
    getIntegralTypeGoods:function(){
        $.ajax({
            url:"/property_system/wx/v1.0.0/getGoodsByTypeIdAndWnk",
            type:"post",
            dataType:"json",
            beforeSend:function (request) {
                request.setRequestHeader("login_session",wnkCategory.storage["login_session"]);
            },
            data:{
                "user_id"    : wnkCategory.storage["user_id"],
                "type_id"    : $('#type_id').val(),
                "business_id": $('#business_id').val()
            },
            success:function (ret) {
                console.log(ret);
                var yesUl = $('#yes-ul');
                if (ret.data.length===undefined||ret.data.length===0) {
                    $('#yes').css("display","none");
                    $('#no').css("display","inline");
                }
                yesUl.empty();
                // "/property_system/wx/v1.0.0/joinIntegralDetailByWnk?goods_id='+data.data[i].id+"&business_id="+jQuery('#business_id').val()
                for (var i = 0;i<ret.data.length;i++){
                    yesUl.append('' +
                        '                <li>                                                                     ' +
                        '                  <a href="/property_system/wx/v1.0.0/joinIntegralDetailByWnk?goods_id='+ret.data[i].id+"&business_id="+$('#business_id').val()+'">'+
                        '                    <div name="goods" class="goods">                                     ' +
                        '                        <div class="media mui-col-sm-6 mui-col-xs-6">                    ' +
                        '                            <img src="'+ret.msg+ret.data[i].img+'"/>      ' +
                        '                        </div>                                                           ' +
                        '                        <div class="info mui-col-sm-6 mui-col-xs-6">                     ' +
                        '                            <span class="type">'+ret.data[i].name+'</span>                           ' +
                        '                            <span class="name">'+ret.data[i].synopsis+' </span>' +
                        '                            <span class="customer-level"></span>                         ' +
                        '                            <p class="customer-price">                                   ' +
                        '                                <span class="text-highlight b">'+ret.data[i].price+'</span>                 ' +
                        '                                <span>积分</span>                                         ' +
                        '                            </p>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                   </a>'+
                        '                </li>');
                }
            }

        });
    },
    init:function () {
        // 获取此分类下的商品信息
        wnkCategory.getIntegralTypeGoods();
    }
};

wnkCategory.init();