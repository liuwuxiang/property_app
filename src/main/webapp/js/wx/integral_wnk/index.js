var wnkIndex = {
    storage: window.localStorage,
    joinMyIntegral: function () {
        self.window.location.href = "/property_system/wx/v1.0.0/joinMyIntegralByWnk?business_id="+jQuery('#business_id').val();
    },
    getIntegralGoods: function(){
        toast(2, "打开loading");
        jQuery.ajax({
            url: '/property_system/wx/v1.0.0/getIntegralGoodsByTrue',
            type: "post",
            headers: {'login_session': wnkIndex.storage['login_session']},
            dataType: 'json',
            timeout: 5000,
            data: {
                "business_id": jQuery('#business_id').val(),
                "user_id"    : wnkIndex.storage['user_id']
            },
            success: function (data) {
                toast(3, "关闭loading");
                if (data.status === 0) {
                    var ul = jQuery('#goods-ul');
                    ul.empty();
                    for (var i = 0; i < data.data.length; i++) {
                        ul.append('' +
                            '                   <li class="div-li mui-col-sm-6 mui-col-xs-6">\n' +
                            '                               <a href="/property_system/wx/v1.0.0/joinIntegralDetailByWnk?goods_id='+data.data[i].id+"&business_id="+jQuery('#business_id').val()+'" class="goods">\n' +
                            '                                   <div>                                   '+
                            '                                   <div class="media weizhi">\n' +
                            '                                       <img src="'+data.msg+data.data[i].img+'">\n' +
                            '                                   </div>\n' +
                            '                                   <div class="media info"><span class="type"> '+data.data[i].name+' </span>\n' +
                            '                                       <p class="name">'+data.data[i].synopsis+'</p>\n' +
                            '                                       <span class="customer-level">  </span>\n' +
                            '                                       <p class="customer-price">\n' +
                            '                                           <span class="text-highlight">'+data.data[i].price+'</span>\n' +
                            '                                           <span>积分</span>\n' +
                            '                                       </p>\n' +
                            '                                   </div>\n' +
                            '                                   </div>'+
                            '                               </a>\n' +
                            '                        </li>'
                        );
                    }
                }

            },
            error: function () {
            }
        });
    },
    getIntegralType: function () {
        toast(2, "打开loading");
        jQuery.ajax({
            url: '/property_system/wx/v1.0.0/getIntegralTypeByTrue',
            type: "post",
            headers: {'login_session': wnkIndex.storage['login_session']},
            dataType: 'json',
            timeout: 5000,
            data: {
                "business_id": jQuery('#business_id').val(),
                "user_id"    : wnkIndex.storage['user_id']
            },
            success: function (data) {
                toast(3, "关闭loading");
                if (data.status === 0) {
                    var headerList = jQuery('#header-list');
                    headerList.empty();
                    for (var i = 0; i < data.data.length; i++) {
                        headerList.append('' +
                            '                <li class="mui-col-sm-2 mui-col-xs-2" style="margin-top: 0.5rem">' +
                            '                    <a href="/property_system/wx/v1.0.0/joinGoodsTypeByWnk?type_id=' + data.data[i].id +"&business_id="+jQuery('#business_id').val()+ '">' +
                            '                        <img class="media" src="' + data.msg + data.data[i].img + '">' +
                            '                        </img>' +
                            '                        <span class="txt">' + data.data[i].name + '</span>' +
                            '                    </a>' +
                            '                </li>');

                    }
                }

            },
            error: function () {
            }
        });
    },
    getUserIntegral: function () {
        toast(2, "打开loading");
        jQuery.ajax({
            url: '/property_system/wx/v1.0.0/getUserIntegral',
            type: "post",
            headers: {'login_session': wnkIndex.storage['login_session']},
            dataType: 'json',
            timeout: 5000,
            data: {
                "business_id": jQuery('#business_id').val(),
                'user_id': wnkIndex.storage['user_id']
            },
            success: function (data) {
                toast(3, "关闭loading");
                if (data.status === 0) {
                    jQuery('#user_integral').text(data.data.integral);
                } else {
                    toast(0, "获取积分出错")
                }

            },
            error: function () {
            }
        });
    },
    getUserInfo: function () {
        toast(2, "打开loading");
        mui.ajax("/property_system/app/v1.0.0/getUserBaseInformation", {
            type: "post",
            dataType: "json",
            headers: {'login_session': wnkIndex.storage["login_session"]},
            data: {"user_id": wnkIndex.storage["user_id"]},
            timeout: 10000,
            success: function (ret) {
                if (parseInt(ret.status) === 0) {
                    jQuery('#nick_name').text(ret.data.nick_name);
                    jQuery('#header').attr('src', ret.data.header);
                    jQuery('#level_icon').attr('src', ret.data.level_icon);
                    jQuery('#level_name').text(ret.data.level_name);
                    toast(3, "关闭loading");
                }
            },
            error: function (xhr, type, errorThrown) {

            }
        });
    },
    init: function () {
        // 初始化mui
        mui.init();
        // 避免含义冲突
        $.noConflict();
        // 获取用户信息
        wnkIndex.getUserInfo();
        // 获取用户在此商家的积分信息
        wnkIndex.getUserIntegral();
        // 获取分类列表
        wnkIndex.getIntegralType();
        // 获取商品
        wnkIndex.getIntegralGoods();
        // 绑定我的积分点击事件
        jQuery('#user_integral').click(wnkIndex.joinMyIntegral);
    }
};

wnkIndex.init();
