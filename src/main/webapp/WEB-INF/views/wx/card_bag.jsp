<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>卡包</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/mui.min.js"></script>
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/card_bag.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/card_bag.js"></script>
</head>
<body onload="listOptionInit(0)">
 <!-- Tab菜单 STR -->
  <div class="pubtabmenu" id="pubtabmenu">
    <div class="wrap">
      <a class="item sel" onclick="listOptionInit(0)" id="member_card_item">会员卡</a>
      <a class="item" onclick="listOptionInit(1)" id="coupon_item">优惠券</a>
    </div>
  </div>
  <!-- Tab菜单 END -->
  <!-- 会员卡STR -->
  <ul class="cardlist" id="member_card_ul">
      <li class="my_member_card_li" id="my_member_card_li" onclick="joinWNK()">
          <a class="card_number_tag" id="card_number_tag">NO.</a>
          <div class="xx_div" id="xx_div">
              <%--<img src="/property_system/images/wx/yinka_xx.png"/>--%>
          </div>
          <a class="term_validity_tag" id="term_validity_tag">有效期至：</a>
      </li>
      <li class="open_my_member_card_li" id="open_my_member_card_li" onclick="joinWNK()">
          <input type="button" value="万能银卡" class="student_card" onclick="openStudentCard()"/>
          <%--<a>万能卡<br>畅享生活</a>--%>
          <%--<input type="button" value="点击开卡" onclick="openXY()"/>--%>
          <input type="button" value="万能金卡" class="zungui_card" onclick="openZunGuiCard()"/>
      </li>
      <li class="integral_card_xg_li">

      </li>
      <li class="integral_card_li" onclick="joinIntgralShopping()">

      </li>
  </ul>
  
  <!-- 优惠券STR -->
  <ul class="ticktlist" id="coupon_ul">
  	<li onclick="coupon_detail()">
	    <a class="item">
	      <p class="icon"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>
	      <p class="line"><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>
	      <div class="info">
	        <p class="tit">肯德基午餐券</p>
	        <p class="desc">满99可用</p>
	        <p class="time">有效期至2018年8月30日</p>
	      </div>
	      <div class="numwrap">
	        <p class="num"><span>¥</span><b>9</b></p>
	        <span class="but">使用说明</span>
	      </div>
	    </a>
    </li>
  </ul>

 <!-- 积分为空 -->
 <div class="publicnull" id="publicnull_tip">
     <span class="icon jf"></span>
     <p class="text" id="request_tip">暂无数据</p>
 </div>

 <!--我的二维码-->
 <div id="my_qrcode_div" class="box mui-popover mui-popover-action mui-popover-bottom mobile_set_check_div">
     <div class="wc_qrcode_div">
         <div class="qrcode_div">
             <a style="display: block;height: 30px;line-height: 30px;color: #000000;font-size: 18px;margin-top: 10px;width: 100%;">卡片权益说明</a>
             <div class="comtent_div">
                 <div class="mui-scroll-wrapper">
                 <div class="mui-scroll">
                 <a class="title_tag">1、99元月卡（仅限本人使用）：</a>
                 <a class="value_tag">有效期一个月，主要针对学生群体；免费观影、健身、理发总次数10次/月；享受平台合作商家优惠折扣</a>

                 <a class="title_tag">2、999元年卡（仅限本人使用）：</a>
                 <a class="value_tag">有效期一年，主要针对社会有车群体；免费观影、健身、理发、洗车总次数120次/年；享受平台合作商家优惠折扣</a>

                 <a class="title_tag">3、1880元年卡（仅限本人使用）：</a>
                 <a class="value_tag">有效期一年，针对小康阶层；一年内免费观影、健身、理发、洗车不限次数；享受平台合作商家优惠折扣。</a>
                 </div>
                 </div>
             </div>

             <input type="button" value="阅读并同意" class="agree_button" onclick="openCard()">
         </div>
     </div>
 </div>
 <!-- UI插件 -->
 <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
 <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
 <script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
 <script src="/property_system/plus/lib/fastclick.js"></script>
 <script src="/property_system/js/wx/toast.js"></script>
 <script>
     $(function() {
         FastClick.attach(document.body);
     });
 </script>
 <script src="/property_system/plus/js/jquery-weui.js"></script>
<script>
    //先初始化一下
    mui.init();

    //再加入这段代码
    (function($){
        $(".comtent_div").scroll({
            //bounce: false,//滚动条是否有弹力默认是true
            indicators: true, //是否显示滚动条,默认是true
        });
    })(mui);
</script>
</body>
</html>
