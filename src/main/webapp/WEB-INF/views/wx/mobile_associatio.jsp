<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>手机号关联</title>
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
  <script src="/property_system/js/wx/mobile_associatio.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <!-- 头部END -->

  <!-- 表单STR -->
  <div class="pubform">
    <form>
      <div class="ftitle">关联手机号可以最大程度的保障您的账户安全</div>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">手机号</span>
          <input class="ftext" id="mobile" type="number" placeholder="请填写手机号码">
        </label>
        <label class="fitem">
          <span class="fname">验证码</span>
          <input class="ftext" type="text" id="code" placeholder="请填写验证码">
          <a onclick="getMobileCode()" class="getcode">获取验证码</a>
        </label>      
      </div>
      <div class="fhandle">
        <a onclick="relationMobile()" class="fsubmit">关联</a>
      </div>
    </form>
  </div>
  <!-- 表单END

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
