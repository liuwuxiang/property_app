<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>证书验证</title>
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link href="/property_system/css/wx/user_certificate_check.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <a>公司名称：辰蓝科技</a>
    <a>证书名称：合伙人股权证书</a>
  	<a>证书编号：NO.${number}</a>
  	<a>期权股东：${user_name}</a>
  	<a>出资金额：${cz_amount}积分</a>
  	<a>出资方式：${cz_type}</a>
  	<a>期权股权：${qiquangudong}股币</a>
  	<a>时间：${cz_time}</a>
</body>
</html>
