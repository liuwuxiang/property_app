var pageMain = {

    joinMyIntegral: function () {
        self.window.location.href = "/property_system/wx/v1.0.0/joinMyIntegral"
    },
    /**
     * 获取商品分类列表
     */
    getAllIntegralType: function () {
        mui.ajax("/property_system/wx/v1.0.0/getAllIntegralTypeTrue", {
            type: "post",
            dataType: "json",
            headers: {'login_session': mMain.storage["login_session"]},
            data: {"user_id": mMain.storage["user_id"]},
            timeout: 10000,
            success: function (ret) {
                if (ret.status === 0) {
                    var headerList = $('#header-list');
                    headerList.empty();
                    for (var i = 0; i < ret.data.list.length; i++) {
                        headerList.append('' +
                            '                <li class="mui-col-sm-2 mui-col-xs-2" style="margin-top: 0.5rem">' +
                            '                    <a href="/property_system/wx/v1.0.0/joinGoodsType?type_id=' + ret.data.list[i].id + '">' +
                            '                        <img class="media" src="' + ret.data.list[i].img + '">' +
                            '                        </img>' +
                            '                        <span class="txt">' + ret.data.list[i].name + '</span>' +
                            '                    </a>' +
                            '                </li>');

                    }
                }
            }
        });
    },
    /**
     * 获取推荐商品
     */
    getRecommendGoodsInfo: function () {
        mui.ajax("/property_system/wx/v1.0.0/getRecommendGoodsInfo", {
            type: "post",
            dataType: "json",
            headers: {
                'login_session': mMain.storage["login_session"]
            },
            data: {"user_id": mMain.storage["user_id"]},
            timeout: 10000,
            success: function (ret) {
                console.log(ret);
                if (ret.status === 0) {
                    var ul = $('#goods-ul');
                    ul.empty();
                    for (var i = 0; i < ret.data.length; i++) {
                        ul.append('' +
                            '                   <li class="div-li mui-col-sm-6 mui-col-xs-6">\n' +
                            '                               <a href="/property_system/wx/v1.0.0/joinIntegralDetail?id='+ret.data[i].id+'" class="goods">\n' +
                            '                                   <div>                                   '+
                            '                                   <div class="media weizhi">\n' +
                            '                                       <img src="'+ret.data[i].img+'">\n' +
                            '                                   </div>\n' +
                            '                                   <div class="media info"><span class="type"> '+ret.data[i].name+' </span>\n' +
                            '                                       <p class="name">'+ret.data[i].synopsis+'</p>\n' +
                            '                                       <span class="customer-level">  </span>\n' +
                            '                                       <p class="customer-price">\n' +
                            '                                           <span class="text-highlight">'+ret.data[i].price+'</span>\n' +
                            '                                           <span>积分</span>\n' +
                            '                                       </p>\n' +
                            '                                   </div>\n' +
                            '                                   </div>'+
                            '                               </a>\n' +
                            '                        </li>'
                        );
                    }
                }
            }
        });
    },
    /**
     * 设置用户信息
     */
    setUserInformation: function (data) {
        document.getElementById("nick_name").innerText = data.data.nick_name;
        document.getElementById("header").src = data.data.header;
        document.getElementById("level_icon").src = data.data.level_icon;
        document.getElementById("level_name").innerText = data.data.level_name;
        document.getElementById("user_integral").innerText = data.data.user_integral;
    },
    /**
     * 获取用户信息
     */
    getUserInfo: function () {
        toast(2, "打开loading");
        mui.ajax("/property_system/app/v1.0.0/getUserBaseInformation", {
            type: "post",
            dataType: "json",
            headers: {
                'login_session': mMain.storage["login_session"]
            },
            data: {"user_id": mMain.storage["user_id"]},
            timeout: 10000,
            success: function (ret) {
                if (ret.status === 0) {
                    pageMain.setUserInformation(ret);
                    toast(3, "关闭loading");
                }
            },
            error: function (xhr, type, errorThrown) {

            }
        });
    },
    init: function () {
        // mui初始化
        mui.init();
        // 设置用户信息
        pageMain.getUserInfo();
        // 获取推荐商品
         pageMain.getRecommendGoodsInfo();
        // 获取所有商品分类
        pageMain.getAllIntegralType();
        // 绑定头像和积分点击事件
        $('.head-right').click(pageMain.joinMyIntegral);
    }
};

pageMain.init();