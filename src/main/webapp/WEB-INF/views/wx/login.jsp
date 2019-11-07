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
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/login.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgblur" onload="loginOption(0)">
  <!-- LOGO STR -->
  <div class="logo">
    <img src="/property_system/images/wx/logo.png" alt="" class="img">
    <span class="adv">小卡片·大作为</span>
  </div>
  <!-- LOGO END -->

  <!-- 登录表单STR -->
  <div class="loginwrap">
    <p class="tit">用户登录</p>
    <div class="tabmenu">
      <a class="item sel" id="account_login_item" onclick="loginOption(0)">账号密码登录</a>
      <a class="item" id="quick_login_item" onclick="loginOption(1)">手机快捷登录</a>
    </div>
    <!--账号密码登录-->
    <form class="loginform" id="account_login">
      <label class="item">
        <p class="name user">账号</p>
        <input type="number" id="account_mobile" placeholder="请填写手机号码">
      </label>
      <label class="item">
        <p class="name pwd">登录密码</p>
        <input type="password" id="login_pwd" placeholder="请填写登录密码">
      </label>
      <a class="submit" onclick="accountLoginAction()">立即登录</a>
    </form>

    <!--快捷登录-->
    <form class="loginform" id="quick_login">
      <label class="item">
        <p class="name user">账号</p>
        <input type="number" id="quick_mobile" placeholder="请填写手机号码">
      </label>
      <label class="item">
        <p class="name pwd">验证码</p>
        <input type="number" id="quick_login_code" placeholder="请填写短信验证码">
        <a class="getcode" onclick="getMobileCode()">获取验证码</a>
      </label>
      <a class="submit" onclick="quickLoginAction()">立即登录</a>
    </form>

    <div class="hadle">
      <p>没有账号？立即<a onclick="accountRegister()">注册</a></p>
      <a onclick="joinRetrievePassword()">忘记密码？</a>
    </div>
  </div>
  <!-- 登录表单END -->
  <a onclick="wxLogin()" class="wxlogin"> 微信快捷登录 </a>

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

