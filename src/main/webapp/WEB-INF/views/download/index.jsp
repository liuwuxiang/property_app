<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>辰蓝科技-下载页面 </title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/download/g.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/download/download.css">

    <style>
        #download_guide{
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            z-index: 999;
            opacity: 0.7;
            height:100%;
            background-color: #0C0C0C;
        }

        #download_guide img {
            padding-left: 18%;
            width: 80%;
            height: auto;
        }
    </style>

</head>

<body class="appdownload-body">
<div class="mb-stars"></div>
<div class="page-bg-btm mobile"></div>
<input type="hidden" value="${client_type}" id="client_type">
<div class="appdownload-wrap">
    <div class="appdownload-logo">
        <img src="${ctx}/images/dowload/logo.jpg" style="border-radius: 50% !important;" width="80" height="80">
        <p class="appname">辰蓝科技</p>
    </div>
    <!--提示：页面不存在-->
    <!--提示：暂不支持的机型-->
    <div class="appdownload-btns">
        <%--<a class="btn item-ios" href="${user}" target="_blank">--%>
        <%--<span class="ico ico-ios"></span>--%>
        <%--<span class="txt">猛戳用户版</span>--%>
        <%--</a>--%>
        <a class="btn item-ios" href="javascript:void(0);" data-href="${business}">
            <span class="ico ico-android"></span>
            <span class="txt">猛戳商家版</span>
        </a>
    </div>
    <p class="appdownload-copyright">Copyright © All Rights Reserved</p>
    <div id="download_guide" style="display: none;">
        <img src="${ctx}/images/dowload/download_guide.png"/>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/wx/jquery-1.9.1.min.js"></script>
<script type="text/javascript" >

    function optionClientyype() {
        var client_type = document.getElementById("client_type").value;
        switch (String(client_type)){
            case "ios":
                window.open('https://itunes.apple.com/cn/app/猛戳商家版/id1442209578?mt=8')
                break;
            case "wx":
                var a = document.getElementsByClassName('item-ios')[0];
                a.onclick = function () {
                    document.getElementById('download_guide').style.display = 'inline';
                };
                break;
            default:
                var a = document.getElementsByClassName('item-ios')[0];
                a.onclick = function () {
                    window.open(a.getAttribute('data-href'));
                }
        }

    }

    optionClientyype();


</script>
</body>
</html>