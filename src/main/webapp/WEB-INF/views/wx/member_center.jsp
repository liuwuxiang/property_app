<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>会员中心</title>
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
  <script src="/property_system/js/wx/member_center.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wx/member_center.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">

  <!-- 会员权益STR -->
  <div class="memberqy">
    <div class="qyhead">
      <span class="vip vip3"></span><!-- 普通会员 vip1  铂金会员 vip2 -->
      <span class="name" id="member_level_name">钻石会员</span>
      <p class="star" id="star">
        <%--<i class="sel"></i>--%>
        <%--<i class="sel"></i>--%>
        <%--<i class="sel"></i>--%>
        <%--<i class="sel"></i>--%>
        <%--<i></i>--%>
      </p>
      <a href="javascript:;" class="uplev" id="uplev">升级</a>
    </div>

    <!-- 权益内容 -->
    <div class="qycontent">
      <div class="info">
        <p style="text-align:justify;" id="contentInsert" contenteditable="true">
        </p>
      </div>
    </div>

    <!-- 文章为空 -->
    <%--<div class="publicnull">--%>
      <%--<span class="icon article"></span>--%>
      <%--<p class="text">暂无使用特权</p>--%>
    <%--</div>--%>
  <%--</div>--%>
    <!--会员等级列表-->
    <div class="ewmshow" id="ewmshow">
      <div class="wrap" style="overflow: scroll;">
        <a class="ewmshow_title">可选会员等级</a>
        <ul class="member_levels_ul" id="member_levels_ul">
          <li>
            <img src="/property_system/images/wx/icon/icon_vip_03.png" class="member_levels_li_icon"/>
            <div class="member_levels_li_content">
              <a class="member_levels_li_name">普通会员</a>
              <a class="member_levels_li_condition">需充值100000消费积分</a>
            </div>
            <img src="/property_system/images/wx/icon_more.svg" class="member_levels_li_arrow"/>
          </li>
          <li>
            <img src="/property_system/images/wx/icon/icon_vip_03.png" class="member_levels_li_icon"/>
            <div class="member_levels_li_content">
              <a class="member_levels_li_name">铂金会员</a>
              <a class="member_levels_li_condition">需充值100000消费积分</a>
            </div>
            <img src="/property_system/images/wx/icon_more.svg" class="member_levels_li_arrow"/>
          </li>
          <li>
            <img src="/property_system/images/wx/icon/icon_vip_03.png" class="member_levels_li_icon"/>
            <div class="member_levels_li_content">
              <a class="member_levels_li_name">钻石会员</a>
              <a class="member_levels_li_condition">需充值100000消费积分</a>
            </div>
            <img src="/property_system/images/wx/icon_more.svg" class="member_levels_li_arrow"/>
          </li>
        </ul>
      </div>
      <a href="javascript:;" class="close"></a>
    </div>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
  <script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
  <script src="/property_system/plus/lib/fastclick.js"></script>
  <script src="/property_system/js/wx/toast.js"></script>
  <script>
    //显示会员等级选择列表
      selShow("uplev","ewmshow");
      $(function() {
          FastClick.attach(document.body);
      });
    window.addEventListener('pageshow', function(e) {
        // 通过persisted属性判断是否存在 BF Cache
        if (e.persisted) {
            initData();
        }
    });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
</body>
</html>
