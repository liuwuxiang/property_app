var storage = window.localStorage;
var business_id = "";
//初始化
function initDataAndImg(current_business_id) {
    business_id = current_business_id;
    document.getElementById("shopping_information_div").style.height = window.screen.height - 200 - 50 + "px";
    listOptionInit(0);
}

//获取商家详情
function getBusinessDetail() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/selectBusinesssDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"business_id":business_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var banners = data.data.banners;
                var brief = data.data.brief;
                document.getElementById("brief").innerText = brief;
                document.getElementById("address").innerText = "地址："+data.data.address;
                initBanner(banners);

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//初始化banner
function initBanner(banners) {
    $("#slider_group div").remove();
    $("#slider_dot div").remove();
    /****1、轮播图*****/
        //获取父容器对象
    var groupSlider = document.getElementById('slider_group');
    var imgList = new Array();
    for (var index = 0;index < banners.length;index++){
        imgList.push(banners[index]);
    }


    /**添加额外的第一个容器（显示最后一张图片）**/
    var div = document.createElement('div');
    //设置div属性
    div.className = "mui-slider-item mui-slider-item-duplicate";
    var a = document.createElement('a');
    a.addEventListener('click', function() {
        //轮播图点击跳转
        self.location.href = "";
    });
    var img = document.createElement('img');
    img.src = imgList[imgList.length - 1];
    a.appendChild(img);
    div.appendChild(a);
    //插入到父容器
    groupSlider.appendChild(div);

    for(var i = 0; i < imgList.length; i++) {
        /***图片***/
            //创建div
        var div = document.createElement('div');
        //设置div属性
        div.className = "mui-slider-item";
        var a = document.createElement('a');
        a.addEventListener('click', function() {
            //轮播图点击跳转
            self.location.href = "";
        });
        var img = document.createElement('img');
        img.src = imgList[i];
        a.appendChild(img);
        div.appendChild(a);
        //插入到父容器
        groupSlider.appendChild(div);

        /***底端圆点***/
            //圆点父容器
        var groupDot = document.getElementById('slider_dot');
        var divDot = document.createElement('div');
        divDot.className = "mui-indicator";
        groupDot.appendChild(divDot);
    }

    /**添加额外的最后一个容器(显示第一张图片)**/
    var div = document.createElement('div');
    //设置div属性
    div.className = "mui-slider-item mui-slider-item-duplicate";
    var a = document.createElement('a');
    a.addEventListener('click', function() {
        //轮播图点击跳转
        self.location.href = "";
    });
    var img = document.createElement('img');
    img.src = imgList[0];
    a.appendChild(img);
    div.appendChild(a);
    //插入到父容器
    groupSlider.appendChild(div);
    //滑动初始化的代码，需要放到ajax之后，否则无法滚动
    mui("#slider").slider({
        interval: 3000
    });
}


/*
 *	列表项初始化
 * type=0:商品信息，type=1：商家简介 type=2: 商家积分商城
 * */
function listOptionInit(type){
    var shopping_information_div = document.getElementById("shopping_information_div");
    var business_information_div = document.getElementById("business_information_div");
    if(type == 0){
        shopping_information_div.style.display = "block";
        business_information_div.style.display = "none";
        document.getElementById("sp_item").setAttribute("class","item sel");
        document.getElementById("sj_jj_item").setAttribute("class","item");
        getBusinessDetail();
        //获取商品信息
        getCommodityInformation();
    }
    else if(type == 1){
        shopping_information_div.style.display = "none";
        business_information_div.style.display = "block";
        document.getElementById("sp_item").setAttribute("class","item");
        document.getElementById("sj_jj_item").setAttribute("class","item sel");
        getBusinessDetail();
    } else if (type == 2){
        self.window.location.href='/property_system/wx/v1.0.0/joinWnkIntegralIndex?business_id='+business_id;
    }
}

//进入商品详情页
function joinShoppingDetail(commodity_id){
    self.window.location.href = "/property_system/wx/v1.0.0/joinShopDetail?commodity_id="+commodity_id;
}

//订购商品
function buy(){
    window.event.cancelBubble = true;//停止冒泡
    window.event.returnValue = false;//阻止事件的默认行为
//	alert("商品订购");

}


//初始化商品列表
function initCommodityList(commodity_list) {
    mui.init({
        swipeBack: true //启用右滑关闭功能
    });
    var controls = document.getElementById("segmentedControls");
    var contents = document.getElementById("segmentedControlContents");
    var html = [];
    var i = 1,
        j = 1,
        m = commodity_list.length //左侧选项卡数量+1
        // n = 21; //每个选项卡列表数量+1
    for (; i <= m; i++) {
        var commodity_type = commodity_list[i-1];
        html.push('<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '">' + commodity_type.type_name + '</a>');
    }
    controls.innerHTML = html.join('');
    html = [];
    for (i = 1; i <= m; i++) {
        var commodity_type = commodity_list[i-1];
        var commodities = commodity_type.commodities;
        html.push('<div id="content' + i + '" class="mui-control-content"><ul class="mui-table-view">');
        for (j = 1; j <= commodities.length; j++) {
            var commodity = commodities[j-1];
            var commodity_li = "<li class=\"commodity_li mui-table-view-cell\" onclick='joinShoppingDetail("+commodity.id+")'>"+
                "<img src=\""+commodity.photo+"\"/>"+
                "<div class=\"commodity_information_div\">"+
                "<a class=\"commodity_name_tag\">"+commodity.name+"</a>"+
                "<a class=\"commodity_describe\">"+commodity.commodity_describe+"</a>"+
                "<a class=\"month_sale_tag\">销量:"+commodity.sael_number+"</a>"+
                "<a class=\"commodity_price_tag\">￥"+commodity.price+"</a>"+
                "</div>"+
                "</li>";
//					html.push('<li class="mui-table-view-cell">第' + i + '个选项卡子项-' + j + '</li>');
            html.push(commodity_li);
        }
        html.push('</ul></div>');
    }
    contents.innerHTML = html.join('');
    //默认选中第一个
    controls.querySelector('.mui-control-item').classList.add('mui-active');
//			contents.querySelector('.mui-control-content').classList.add('mui-active');
    (function() {
        var controlsElem = document.getElementById("segmentedControls");
        var contentsElem = document.getElementById("segmentedControlContents");
        var controlListElem = controlsElem.querySelectorAll('.mui-control-item');
        var contentListElem = contentsElem.querySelectorAll('.mui-control-content');
        var controlWrapperElem = controlsElem.parentNode;
        var controlWrapperHeight = controlWrapperElem.offsetHeight;
        var controlMaxScroll = controlWrapperElem.scrollHeight - controlWrapperHeight;//左侧类别最大可滚动高度
        var maxScroll = contentsElem.scrollHeight - contentsElem.offsetHeight;//右侧内容最大可滚动高度
        var controlHeight = controlListElem[0].offsetHeight;//左侧类别每一项的高度
        var controlTops = []; //存储control的scrollTop值
        var contentTops = [0]; //存储content的scrollTop值
        var length = contentListElem.length;
        for (var i = 0; i < length; i++) {
            controlTops.push(controlListElem[i].offsetTop + controlHeight);
        }
        for (var i = 1; i < length; i++) {
            var offsetTop = contentListElem[i].offsetTop;
            if (offsetTop + 100 >= maxScroll) {
                var height = Math.max(offsetTop + 100 - maxScroll, 100);
                var totalHeight = 0;
                var heights = [];
                for (var j = i; j < length; j++) {
                    var offsetHeight = contentListElem[j].offsetHeight;
                    totalHeight += offsetHeight;
                    heights.push(totalHeight);
                }
                for (var m = 0, len = heights.length; m < len; m++) {
                    contentTops.push(parseInt(maxScroll - (height - heights[m] / totalHeight * height)));
                }
                break;
            } else {
                contentTops.push(parseInt(offsetTop));
            }
        }
        contentsElem.addEventListener('scroll', function() {
            var scrollTop = contentsElem.scrollTop;
            for (var i = 0; i < length; i++) {
                var offsetTop = contentTops[i];
                var offset = Math.abs(offsetTop - scrollTop);
//						console.log("i:"+i+",scrollTop:"+scrollTop+",offsetTop:"+offsetTop+",offset:"+offset);
                if (scrollTop < offsetTop) {
                    if (scrollTop >= maxScroll) {
                        onScroll(length - 1);
                    } else {
                        onScroll(i - 1);
                    }
                    break;
                } else if (offset < 20) {
                    onScroll(i);
                    break;
                }else if(scrollTop >= maxScroll){
                    onScroll(length - 1);
                    break;
                }
            }
        });
        var lastIndex = 0;
        //监听content滚动
        var onScroll = function(index) {
            if (lastIndex !== index) {
                lastIndex = index;
                var lastActiveElem = controlsElem.querySelector('.mui-active');
                lastActiveElem && (lastActiveElem.classList.remove('mui-active'));
                var currentElem = controlsElem.querySelector('.mui-control-item:nth-child(' + (index + 1) + ')');
                currentElem.classList.add('mui-active');
                //简单处理左侧分类滚动，要么滚动到底，要么滚动到顶
                var controlScrollTop = controlWrapperElem.scrollTop;
                if (controlScrollTop + controlWrapperHeight < controlTops[index]) {
                    controlWrapperElem.scrollTop = controlMaxScroll;
                } else if (controlScrollTop > controlTops[index] - controlHeight) {
                    controlWrapperElem.scrollTop = 0;
                }
            }
        };
        //滚动到指定content
        var scrollTo = function(index) {
            contentsElem.scrollTop = contentTops[index];
        };
        mui(controlsElem).on('tap', '.mui-control-item', function(e) {
            scrollTo(this.getAttribute('data-index'));
            return false;
        });
    })();
}

//获取商品信息
function getCommodityInformation() {
    $("#segmentedControlContents div").remove();
    $("#segmentedControls a").remove();

    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getWnkCommodity",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"business_id":business_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                initCommodityList(data.data);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}