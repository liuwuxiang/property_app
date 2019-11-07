<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>注册</title>
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
  <script src="/property_system/js/wx/account_register.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <!-- 头部END -->
  <input type="hidden" value="${type}" id="type">
  <!-- 表单STR -->
  <div class="pubform">
    <form>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">手机号</span>
          <input class="ftext" id="mobile" type="number" placeholder="请填写手机号码">
        </label>
        <label class="fitem">
          <span class="fname">验证码</span>
          <input class="ftext" id="code" type="text" placeholder="请填写验证码">
          <a class="getcode" onclick="getMobileCode()">获取验证码</a>
        </label>
        <label class="fitem">
          <span class="fname">登录密码</span>
          <input class="ftext" id="login_pwd" type="password" value="" placeholder="请设置登录密码">
        </label>
        <label class="fitem">
          <span class="fname">邀请码</span>
          <input class="ftext" id="invitation_code" value="${invitation_code}" type="number" placeholder="请填写邀请人手机号">
        </label>
      </div>
      <div class="fhandle">
        <a class="fsubmit" onclick="registerAccount()">注册</a>
      </div>
    </form>
  </div>
  <!-- 表单END -->

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
