
var storage = window.localStorage;
var commodity_id = "";
var busying = false;
//规格id
var guige_id = -1;

//初始化数据
function initData(current_commodity_id) {
    commodity_id = current_commodity_id;
    getCommodityDetail();
    getUserMemberLevelInformation();
}

function getUserMemberLevelInformation() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserMemberCardInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("wnk_qrcode_img").src = data.data.wnk_qrcode_img;

            }
            else{
                toast(3,"关闭Loading");
            }
        },
    });
}

//获取商品信息
function getCommodityDetail() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getCommodityDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                document.getElementById("commodity_name").innerText = data.data.name;
                document.getElementById("commodity_price").innerText = "￥"+data.data.price;
                document.getElementById("commodity_describe").innerText = "￥"+data.data.commodity_describe;
                initDataAndImg(data.data.banners);
                if (data.data.is_make_wnk == 1){
                    document.getElementById("make_wnk_button").style.display = "block";
                }
                getCommoditySpecification(commodity_id);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//获取商品规格信息
function getCommoditySpecification(commodity_id) {
    toast(2,"打开loading");
    $("#guige_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getWnkCommoditySpecification",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"commodity_id":commodity_id},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var html = "";
                    if (index == 0){
                        guige_id = obj.id;
                        html = "<li class=\"no_choose_li choose_li\" onclick=\"guigeChoose("+obj.id+","+index+")\">"+obj.specifications_name+"</li>";
                    }
                    else{
                        html = "<li class=\"no_choose_li\" onclick=\"guigeChoose("+obj.id+","+index+")\">"+obj.specifications_name+"</li>";
                    }
                    $("#guige_ul").append(html);
                }

            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//规格信息选择
function guigeChoose(record_id,index_tag) {
    guige_id = record_id;
    var lis = $("#guige_ul li")
    for (var index = 0;index < lis.length;index++){
        var li = lis[index];
        if (index == index_tag){
            li.setAttribute("class","no_choose_li choose_li");
        }
        else{
            li.setAttribute("class","no_choose_li");
        }
    }
}

//初始化
function initDataAndImg(banners) {
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

//商品购买
function buy_shop(pay_way) {
    if (guige_id == -1){
        toast(1,"暂无商品规格,不可购买");
    }
    else{
        self.window.location.href = "/property_system/wx/v1.0.0/joinWnkCommodityOrder?commodity_id="+commodity_id+"&guige_id="+guige_id+"&pay_way="+pay_way;
    }
}



function toggleMenu() {
    var menuWrapper = document.getElementById("menu-wrapper");
    var menu = document.getElementById("menu");
    var menuWrapperClassList = menuWrapper.classList;

    var backdrop = document.getElementById("menu-backdrop");
    // var qr_code_tuqi_div = document.getElementById("qr_code_tuqi_div");
    // var qr_code_button_div = document.getElementById("qr_code_button_div");
    var down_arrow_div = document.getElementById("down_arrow_div");
    backdrop.addEventListener('tap', closeQrCode);
    // qr_code_tuqi_div.addEventListener('tap', closeQrCode);
    // qr_code_button_div.addEventListener('tap', closeQrCode);
    down_arrow_div.addEventListener('tap', closeQrCode);

    if(busying) {
        return;
    }
    busying = true;
    if(menuWrapperClassList.contains('mui-active')) {
        document.body.classList.remove('menu-open');
        menuWrapper.className = 'menu-wrapper fade-out-up animated';
        menu.className = 'menu bounce-out-up animated';
        setTimeout(function() {
            // backdrop.style.opacity = 0;
            menuWrapper.classList.add('hidden');
        }, 500);
    } else {
        document.body.classList.add('menu-open');
        menuWrapper.className = 'menu-wrapper fade-in-down animated mui-active';
        menu.className = 'menu bounce-in-down animated';
        // backdrop.style.opacity = 1;
    }
    setTimeout(function() {
        busying = false;
    }, 500);
}

//关闭二维码视图
function closeQrCode(){
    var menuWrapper = document.getElementById("menu-wrapper");
    var menu = document.getElementById("menu");
    var menuWrapperClassList = menuWrapper.classList;

    document.body.classList.remove('menu-open');
    menuWrapper.className = 'menu-wrapper fade-out-up animated';
    menu.className = 'menu bounce-out-up animated';
    setTimeout(function() {
        // backdrop.style.opacity = 0;
        menuWrapper.classList.add('hidden');
    }, 500);
}