<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/10
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="keywords"    content="">
    <meta name="description" content="">
    <meta name="copyright"   content=""/>
    <meta name="viewport"    content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">

    <link rel="stylesheet" type="text/css" href="${ctx}/css/wnk_business/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/hui.css"/>
    <!-- UI插件 -->
    <link rel="stylesheet" href="${ctx}/plus/lib/weui.min.css">
    <link rel="stylesheet" href="${ctx}/plus/css/jquery-weui.css">

    <title>积分系统-商品管理</title>
</head>
<body class="bgw" onload="init()">

<!-- 头部STR -->
<div class="head">
    <h1>积分系统-商品分类管理</h1>
    <a href="javascript:void(0);" onclick="history.go(-1);" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->
</div>
<div class="h88"></div>

<input type="hidden" id="goods_id" value="${goods_id}">

<div class="weui-cells weui-cells_form">

    <div class="weui-cell">
        <div class="weui-cell__bd">
            <div class="weui-uploader">
                <div class="weui-uploader__hd">
                    <p class="weui-uploader__title">商品大图</p>
                </div>
                <div class="weui-uploader__bd">
                    <ul class="weui-uploader__files" id="uploaderFiles">
                        <%-- 如果有图片则在添加这个节点--%>
                        <%--<li class="weui-uploader__file" style="background-image:url(./images/pic_160.png)"></li>--%>
                    </ul>
                    <div class="weui-uploader__input-box">
                        <input name="ajaxFile" id="header_file" onchange="imgUpload()" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <input type="hidden" id="img">

    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">商品名称</label></div>
        <div class="weui-cell__bd">
            <input id="name" class="weui-input" type="text" placeholder="请输入分类名称">
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">商品简介</label></div>
        <div class="weui-cell__bd">
            <input id="synopsis" class="weui-input" type="text" placeholder="请输入商品简介">
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">商品价格</label></div>
        <div class="weui-cell__bd">
            <input id="price" class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入商品价格">
        </div>
    </div>

    <div class="weui-cell weui-cell_switch">
        <div class="weui-cell__bd">是否启用</div>
        <div class="weui-cell__ft">
            <input id="is_checked" class="weui-switch"  type="checkbox">
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">商品分类</label></div>
        <div class="weui-cell__bd">
            <input name="type" class="weui-input" type="text" readonly  placeholder="请选择商品分类" onclick="getGoodsType()">
        </div>
    </div>

    <input type="hidden" id="type" >

    <div class="weui-cell weui-cell_switch">
        <div class="weui-cell__bd">商品详情</div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__bd">
            <textarea id="detail" class="weui-textarea" placeholder="请输入商品详情" rows="3"></textarea>
            <div class="weui-textarea-counter"><span>0</span>/200</div>
        </div>
    </div>

</div>


<div class="memmenu" style="margin-top: 0;border-width: 1px;">
    <a href="javascript:void(0);" class="weui-btn weui-btn_primary" onclick="addIntegralGoods()">保存修改</a>
</div>


<script src="${ctx}/js/wnk_business/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/wnk_business/common.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<%--UI插件--%>
<script src="${ctx}/plus/lib/fastclick.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/plus/js/jquery-weui.js"></script>
<script src="//res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
<%--上传组件--%>
<script src="${ctx}/js/wnk_business/ajaxfileupload.js"></script>
<%--自己的js--%>
<script src="${ctx}/js/wnk_business/integral/goods_edit.js"></script>
<script>
    $(function () {
        FastClick.attach(document.body);
    });
</script>
</body>
</html>
