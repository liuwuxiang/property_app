//初始化
function initDataAndImg() {
    /****1、轮播图*****/
        //获取父容器对象
    var groupSlider = document.getElementById('slider_group');
    var imgList = new Array();
    imgList.push("/property_system/images/wx/banner001.jpg");
    imgList.push("/property_system/images/wx/banner002.jpg");
    // imgList.push("/property_system/images/wx/business3.png");


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

        if (i == 0){
            //轮播图点击跳转
            a.href = "https://mp.weixin.qq.com/s/J-uQldolUidTAWCTi52QTw";
        }
        // else if (i == 1){
        //     //轮播图点击跳转
        //     a.href = "https://mp.weixin.qq.com/s/BIWDDtI-xpN8CUVlKK6h-g";
        // }
        else{
            //轮播图点击跳转
            a.href = "https://mp.weixin.qq.com/s/chOQvb5wNrfQj-w8beu9MA";
        }
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