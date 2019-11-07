<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>商品规格</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/commodity_specifications.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/commodity_specifications.js"></script>
    <script src="/property_system/js/wnk_business/general_lib.js"></script>
    <link rel="stylesheet" href="/property_system/css/wnk_business/mui.min.css">
    <script src="/property_system/js/wnk_business/mui.min.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData(0,${commodity_id})">
	<!-- 头部STR -->
  <div class="head">
    <h1>商品规格</h1>
      <a onclick="back()" class="back"></a>
    <a onclick="addAction()" class="add"></a> <!-- sel表示有消息  -->
  </div>
  <div class="h88"></div>
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu">
    <div class="wrap">
      <a class="item" id="new_order" onclick="optionClick(0)">已启用</a>
      <a class="item" id="finish_order" onclick="optionClick(1)">未启用</a>
    </div>
  </div>
  <ul class="specification_ul" id="specification_ul">
  	<%--<li>--%>
  		<%--<a class="specification_name">规格名称:</a>--%>
  		<%--<div class="button_div">--%>
  			<%--<input type="button" value="修改" onclick="setAction(1)"/>--%>
  			<%--<input type="button" value="停用" onclick="startOrEndGuiGe(1)"/>--%>
  		<%--</div>--%>
  	<%--</li>--%>
  </ul>


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
