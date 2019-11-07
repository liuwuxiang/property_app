<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>资金密码设置</title>
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
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
  <script src="/property_system/js/wnk_business/set_pay_pwd.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw">
  <!-- 头部STR -->
  <div class="head">
    <h1>资金密码设置</h1>
    <a onclick="back()" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->  
  </div>
  <div class="h88"></div>
  
  <!-- 表单STR -->
  <div class="pubform">
    <form>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">新密码</span>
          <input class="ftext" type="password" placeholder="请填写新密码" id="new_pwd">
        </label>
        <label class="fitem">
          <span class="fname">确认密码</span>
          <input class="ftext" type="password" placeholder="请再次填写新密码" id="confirm_new_pwd">
        </label>
        <label class="fitem">
          <span class="fname">验证码</span>
          <input class="ftext" type="text" placeholder="请填写验证码" id="code">
          <a onclick="getMobileCode()" class="getcode">获取验证码</a>
        </label>      
      </div>
      <div class="fhandle">
        <a onclick="setPayPwd()" class="fsubmit">设置</a>
      </div>
    </form>
  </div>
  <!-- 表单END -->

  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
  <%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
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
