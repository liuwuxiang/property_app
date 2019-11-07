<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>会员权益使用</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/member_qy_make.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
	<script src="/property_system/js/wnk_business/member_qy_make.js"></script>
	<script src="/property_system/js/wx/hui.js"></script>
	<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData(${user_id})">
	<!-- 头部STR -->
  <div class="head">
    <h1>会员权益使用</h1>
    <a onclick="back()" class="back"></a>
  </div>
  <div class="h88"></div>
  <div class="bottom_div">
  	<div class="li_commodites_div">
  		<a class="commodities_number_tag">基础信息</a>
  		<div class="user_div">
  			<a id="nick_name">会员名称：</a>
  			<a id="member_sex">会员性别：</a>
  			<a id="member_card_level">卡片等级：</a>
  			<a id="member_star">用户星级：</a>
  		</div>
  	</div>
  	<div class="li_commodites_div">
  		<a class="commodities_number_tag">使用次数</a>
  		<div class="pubform">
    			<form>
		      <div class="fgroup">
		  		<label class="fitem">
		          <span class="fname">次数</span>
		          <input class="ftext" type="number" value="1" placeholder="请填写使用次数(如：1)" id="make_number">
		        </label>
		     </div>
		     </form>
		</div>
  	</div>
  	<div class="li_commodites_div">
  		<a class="commodities_number_tag">使用验证</a>
  		<div class="pubform">
    			<form>
		      <div class="fgroup">
		  		<label class="fitem">
		          <span class="fname">验证码</span>
		          <input class="ftext" type="text" placeholder="请填写用户手机验证码" id="user_code">
		          <a onclick="getCode()" class="getcode">获取验证码</a>
		        </label>
		      </div>
		      
		      <div class="fhandle">
		        <a onclick="memberQYMakeCheck()" class="fsubmit">验证使用</a>
		      </div>
		     </form>
		</div>
  	</div>
  </div>

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
