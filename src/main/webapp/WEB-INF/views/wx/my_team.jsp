<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>我的团队</title>
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
    <script src="/property_system/js/wx/my_team.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部END -->

  <!-- 团队 -->
  <div class="teamhead"  style="margin-top: 5px;margin-bottom: 5px;">
    <img src="" id="header_img" alt="" class="img">
    <span class="name boy" id="nick_name">业主名称/昵称</span><!-- 女生替换class boy为girl  -->
    <p class="desc" id="level_name">铂金会员</p>
    <a href="javascript:;" class="ewm" id="ewm"></a>
  </div>

  <!-- 二维码弹出 -->
  <div class="ewmshow" id="ewmshow">
    <div class="wrap">
      <img src="" id="qrcode_header_img" alt="" class="img">
      <span class="name boy" id="qrcode_nick_name"></span><!-- 女生替换class boy为girl  -->
      <p class="tel" id="qrcode_mobile"></p>
      <img src="/property_system/images/wx/ewm.png" id="qrcode_photo_url" alt="" class="ewm">
      <span class="desc">扫一扫二维码加入我们</span><br/>
      <span class="desc">长按二维码分享</span>
    </div>
    <%--<button style="width: 100px;height: 35px;margin-top: 10px;background: #2993F8;color:white;border:0;border-radius: 8px;">分享</button>--%>
    <a href="javascript:;" class="close"></a>
  </div>

  <!-- 团队列表 -->
  <p class="pubtitle">我的团队</p>
  <ul class="teamlist" id="teamlist">
  	<li>
	    <div class="item">
	      <div class="left">
	        <img src="/property_system/images/wx/img_head.jpg" alt="" class="img">
	        <span class="name">飞翔的阿里</span>
	        <p class="lev">三星级铂金会员</p>
	      </div>
	      <div class="right">
	        <p class="tel">18787015223</p>
	        <p class="time">2018-05-21 10:59:32</p>
	      </div>
	    </div>
    </li>
  </ul>

  <!-- 团队为空 -->
  <div class="publicnull" id="publicnull_tip">
    <span class="icon team"></span>
    <p class="text">您还没有团队</p>
  </div>

  <script type="text/javascript">
    // 显示二维码
    selShow("ewm","ewmshow");
  </script>
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
  

</body>
</html>
