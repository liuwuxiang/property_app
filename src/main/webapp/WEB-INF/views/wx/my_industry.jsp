<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>我的产业</title>
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
  <script src="/property_system/js/wx/my_industry.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部END -->

  <!-- 积分STR -->
  <div class="jfhead" style="height: 135px">
    <div class="desc" style="width: 100%;height: 70px;margin-top: 10px;">
      <span class="tit" style="width: 100%;text-align: center;">我的产业</span>
      <p class="num" id="sovile_coin_balance" style="width: 100%;text-align: center;">0</p>
    </div>
    <div class="hadle" style="width: 100%;text-align: center;height: 50px;padding: 0; ">
      <%--<a href="javascript:;" class="help"></a>--%>
      <div class="buts">
        <a onclick="joinSilverCoinDetail()">明细</a>
        <a onclick="joinCanWithdrawSilverCoinDetail()" class="b2">提现</a>
        <a onclick="lookMyCertificate()" class="b1">证书</a>
      </div>
    </div>


  </div>
  <!-- 积分END -->
  
  <div class="pubtit_2">我的收益(通用积分)</div>

  <!-- 明细STR -->
  <div class="condetaile">
    <ul class="list" id="list">
      <li class="item">
        <div class="left">
          <span class="timename">2018-05-21 10:59:22</span>
        </div>
        <div class="right">
          <span class="num up">+1800</span>
        </div>
      </li>
    </ul>
  </div>
  <!-- 明细END -->

  <!-- 积分为空 -->
  <div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text">暂无收益</p>
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


</body>
</html>
