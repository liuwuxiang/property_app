<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>登录</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/login.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgblur">
  <!-- LOGO STR -->
  <div class="logo">
    <img src="/property_system/images/wnk_business/logo.png" alt="" class="img">
    <span class="adv">小卡片·大作为</span>
  </div>
  <!-- LOGO END -->

  <!-- 登录表单STR -->
  <div class="loginwrap">
    <p class="tit">商家登录</p>
    <div class="tabmenu">
      <a class="item sel" id="account_login" onclick="loginWayClick(0)">账号密码登录</a>
      <a class="item" id="code_login" onclick="loginWayClick(1)">手机快捷登录</a>
    </div>
    <form class="loginform" id="account_login_from">
      <label class="item">
        <p class="name user">账号</p>
        <input type="number" placeholder="请填写手机号码" id="mobile">
      </label>
      <label class="item">
        <p class="name pwd">登录密码</p>
        <input type="password" placeholder="请填写登录密码" id="login_pwd">
      </label>
      <a onclick="mobileAndPwdLogin()" class="submit">立即登录</a>
    </form>
    
    <form class="loginform" style="display: none;" id="code_login_from">
      <label class="item">
        <p class="name user">账号</p>
        <input type="number" placeholder="请填写手机号码" id="quick_mobile">
      </label>
      <label class="item">
        <p class="name pwd">验证码</p>
        <input type="number" placeholder="请填写短信验证码" id="quick_login_code">
        <a onclick="getMobileCode()" class="getcode">获取验证码</a>
      </label>
      <a onclick="quickLoginAction()" class="submit">立即登录</a>
    </form>
    <div class="hadle">
      <p>不是商家？立即<a onclick="businessRegister()">加入</a></p>
      <%--<a onclick="joinRetrievePassword()">忘记密码？</a>--%>
    </div>
  </div>
  <!-- 登录表单END -->

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
