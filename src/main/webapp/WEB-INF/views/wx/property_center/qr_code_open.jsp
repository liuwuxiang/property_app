<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>${title}</title>
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
    <style type="text/css">body,html{min-height:100%; }</style>
</head>
<body class="bgblur">
<!-- 头部STR -->
<div class="head">
    <h1>${title}</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
</div>
<div class="h88"></div>
<!-- 头部END -->

<div style="width: 95%;height: auto;margin-top: 30px;margin-left: 2.5%;background: white;">
    <div style="width: 100%;height: 60px;background: #F3F3F3;padding: 0;margin: 0;" onclick="jump()">
        <a style="display: block;height: 30px;width: 100%;text-align: center;line-height: 30px;">
            文化空间D栋-1901室
        </a>
        <a style="display: block;height: 30px;width: 100%;text-align: center;line-height: 30px;color: #A9A9A9;">
            张凡,187****5223
        </a>
    </div>
    <a style="display: block;height: 30px;width: 100%;text-align: center;line-height: 30px;margin-top: 30px;">
        2018-07-24点前有效
    </a>
    <img src="/property_system/images/wx/ewm.png" alt="" class="ewm" style="display: block;width: 50%;margin-left: 25%;">
    <a style="display: block;height: auto;width: 100%;text-align: center;margin-top: 5px;">
        开门密码
    </a>
    <a style="display: block;height: 20px;width: 100%;text-align: center;font-weight: bold;font-size: 20px;padding-bottom: 30px;">
        881127
    </a>
</div>
<script>
    function jump() {
        self.window.location.href = "/property_system/wx/v1.0.0/joinResidentialsChoose";
    }
</script>
</body>
</html>
