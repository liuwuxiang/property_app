<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>系统设置</title>
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
  <script src="/property_system/js/wx/system_setting.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw">
  <!-- 头部END -->
  
  <!-- 设置 -->
  <div class="pubmenulist">

    <a class="menuitem" onclick="joinAccountAssociatio()">
      <span class="name">账号关联</span>
    </a>

    <a class="menuitem" onclick="joinSuggestionFeedback()">
      <span class="name">投诉建议</span>
    </a>

    <a class="menuitem" onclick="joinAboutUs()">
      <span class="name">关于我们</span>
    </a>   

    <a class="menuitem nomore">
      <span class="name">版本信息</span>
      <span class="info">V1.0.0</span>
    </a>
  </div>
  <div class="pubhandle" onclick="exitLogin()">
    <a class="defualt">退出登录</a>
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
      window.addEventListener('pageshow', function(e) {
          showToast();
      });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
</body>
</html>
