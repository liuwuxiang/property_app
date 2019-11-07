var categoryMain = {
	getIntegralTypeGoods:function(){
        $.ajax({
            url:"/property_system/wx/v1.0.0/getGoodsByTypeId",
            type:"post",
            dataType:"json",
            beforeSend:function (request) {
                request.setRequestHeader("login_session",mMain.storage["login_session"]);
            },
            data:{
                "user_id": mMain.storage["user_id"],
                "type_id": $('#typeId').val()
            },
            success:function (ret) {
                console.log(ret);
                var yesUl = $('#yes-ul');
                if (ret.data.length===undefined||ret.data.length===0) {
                    $('#yes').css("display","none");
                    $('#no').css("display","inline");
				}
				yesUl.empty();
                for (var i = 0;i<ret.data.length;i++){
                    yesUl.append('' +
                        '                <li>                                                                     ' +
                        '                  <a href="/property_system/wx/v1.0.0/joinIntegralDetail?id='+ret.data[i].id+'">'+
                        '                    <div name="goods" class="goods">                                     ' +
                        '                        <div class="media mui-col-sm-6 mui-col-xs-6">                    ' +
                        '                            <img src="'+ret.data[i].img+'"/>      ' +
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
		categoryMain.getIntegralTypeGoods();
    }
};

categoryMain.init();




// var time = document.getElementById("time");
//
// var price = document.getElementById("price");
//
// var priceSort = document.getElementById("price-sort");
//
// var yes = document.getElementById("yes");
//
// var no = document.getElementById("no");
//
// var goods = document.getElementsByName('goods');
//
// /**
//  * 判断是否有指定样式
//  * @param {Object} element  在哪个element中判断
//  * @param {Object} cls      需要判断的样式
//  */
// function hasClass(element, cls) {
// 	return(' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
// }
//
// /**
//  * 获取地址参数
//  * @param {Object} name 参数名称
//  */
// function GetQueryString(name) {
// 	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
// 	var r = window.location.search.substr(1).match(reg);
// 	if(r != null) return unescape(r[2]);
// 	return null;
// }
//
// /**
//  * 文档加载完成后判断是否有商品
//  * 如果没有则把无商品的界面显示
//  */
// window.onload = function() {
// 	var ret = GetQueryString("isNull");
// 	if(ret == null || ret == undefined) {
// 		yes.style.display = 'none';
// 		no.style.display = 'inline'
// 	}
//
// 	for(var i = 0; i < goods.length; i++) {
// 		goods[i].onclick = function() {
// 			window.location.href = "detail.html";
// 		}
// 	}
// 	console.log(goods)
// };
//
// time.onclick = function() {
// 	priceSort.classList.remove('up');
// 	priceSort.classList.remove('down');
// 	price.style.color = '#000';
// 	time.style.color = '#fc9153';
//
// }
//
// price.onclick = function() {
// 	if(hasClass(priceSort, 'up')) {
// 		priceSort.classList.remove('up');
// 		priceSort.classList.add('down');
// 	} else {
// 		priceSort.classList.add('up');
// 		priceSort.classList.remove('down');
// 	}
// 	price.style.color = '#fc9153';
// 	time.style.color = '#000';
// }