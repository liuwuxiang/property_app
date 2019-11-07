<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>安全中心</title>
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link href="/property_system/img/logo.jpg" rel="shortcut icon" />
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <link rel="stylesheet" href="/property_system/css/wx/security_center.css">
  <script src="/property_system/js/wx/security_center.js"></script>
  <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
  <script src="/property_system/js/wx/mui.min.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw">
  <!-- 头部END -->
  
  <!-- 设置 -->
  <div class="pubmenulist">

    <a class="menuitem" onclick="setMobileCheckCen(0)">
      <span class="name">登录密码设置</span>
    </a>

    <a class="menuitem" onclick="setMobileCheckCen(1)">
      <span class="name">支付密码设置</span>
    </a>

    <a class="menuitem" onclick="setMobileCheckCen(2)">
      <span class="name">密保问题</span>
    </a>   

    <a class="menuitem nomore">
      <span class="name">小额免密码支付</span>
      <input type="checkbox" class="weui-switch" checked="checked">
    </a>
  </div>
  
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  
<!--号码修改验证-->
		<div id="mobile_set_check_div" class="box mui-popover mui-popover-action mui-popover-bottom mobile_set_check_div">
			<div class="number_set_duv">
				<a class="check_title_tag" id="check_title_tag"></a>
				<a class="check_content_tag" id="check_content_tag"></a>
				<div class="code_input_div">
					<input type="number" placeholder="请填写验证码" id="check_code"/>
					<input type="button" value="获取验证码" onclick="getMobileCode()"/>
				</div>
				<div class="check_button_div">
					<input type="button" value="取消" class="close_button" onclick="closeMobileCheckCen()"/>
					<input type="button" value="确定" class="submit_button" onclick="mobileCodeCheck()"/>
				</div>
			</div>
		</div>

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
