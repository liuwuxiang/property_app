<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>优惠券详情</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/coupon_detail.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData(${coupon_id})">
  <!-- 头部END -->
  
  <!-- 优惠券STR -->
  <br>
  <div class="ticktlist">
    <a href="" class="item">
      <p class="icon"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>
      <p class="line"><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i></p>
      <div class="info">
        <p class="tit">肯德基午餐券</p>
        <p class="desc">满99可用</p>
        <p class="time">有效期至2018年8月30日</p>
      </div>
      <div class="numwrap">
        <p class="num"><span>¥</span><b>9</b></p>
      </div>
    </a>
  </div>
  <br>
  <!-- 优惠券END -->

  <!-- 优惠券说明 -->
  <div class="ticktinfo">
    <!-- 不同的分类/金额可以通过控制 item的类 t1 t2 -->
    <div class="item">
      <span class="name">过期时间</span>
      <p class="info">2018年10月25日</p>
    </div>

    <div class="item">
      <span class="name">优惠金额</span>
      <p class="info">88元</p>
    </div>

    <div class="item">
      <span class="name">使用条件</span>
      <p class="info">满300使用</p>
    </div>

    <div class="item">
      <span class="name">消费方式</span>
      <p class="info">线上消费/线下消费</p>
    </div>

    <div class="item">
      <span class="name">使用说明</span>
      <p class="info">后台设定的每个优惠券的使用说明内容，后台设定的每个优惠券的使用说明内容</p>
    </div>
  </div>

</body>
</html>
