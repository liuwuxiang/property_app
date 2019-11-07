<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>物业中心</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common3.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw">
    <!-- 头部STR -->
  <div class="head">
    <h1>选择小区</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- 头部END -->
  <div class="pubsearchbar">
    <input type="text" name="" placeholder="请输入小区名称进行搜索">
  </div>
  <div class="pubpos"><span>当前小区</span>文化空间</div>

  <div class="appwrap" id="appwrap">
    <div class="leftnav">
      <a href="javascript:;" class="item sel">昆明</a>
      <a href="javascript:;" class="item">曲靖</a>
      <a href="javascript:;" class="item">玉溪</a>
    </div>
    <div class="rightmenu hitems">
      <div class="tablist">
        <div class="selectitem sel">
          <a href="">文化空间</a>
          <a href="">世纪俊园</a>
          <a href="">SOHO俊园</a>
          <a href="">摩玛大厦</a>
        </div>
        <div class="selectitem">
          <a href="">1</a>
          <a href="">2</a>
          <a href="">3</a>
          <a href="">4</a>
        </div>
        <div class="selectitem">
          <a href="">5</a>
          <a href="">6</a>
          <a href="">7</a>
          <a href="">8</a>
        </div>
      </div>
    </div>
  </div>
 
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>  
  <script type="text/javascript">
    // 功能模块菜单切换
    selTab('appwrap','item','selectitem',1.6,'hitems');
  </script>
</body>
</html>
