<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>积分商城-商品新增</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">
    <link rel="stylesheet" href="${ctx}/css/admin/integral/integral_add.css">


    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"   ></script>
    <script src="${ctx}/js/admin/layui.js"              ></script>
    <script src="${ctx}/js/wx/toast.js"                 ></script>
    <script src="${ctx}/js/wx/hui.js"                   ></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js" ></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"  ></script>
    <script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
    <script src="${ctx}/js/wx/ajaxfileupload.js"        ></script>
    <script src="${ctx}/js/admin/carousel_img/carousel_img_edit.js" ></script>

    <style>
        #img_url {
            width: 40%;
            height: auto;
            border: 1px dashed #000;
        }
    </style>

</head>
<body onload="initCarouselData();">

<div style="margin: 5% 15%" >


    <form id="integral-form" class="layui-form">
        <input type="hidden" id="carousel_id" value="${carousel_id}">
        <div class="layui-form-item">
            <label class="layui-form-label">轮播图:</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="img_upload_show" onclick="$('#img_upload').click();">
                    <i class="layui-icon">&#xe67c;</i>轮播图
                </button>
                <input id="img_upload" name="ajaxFile" type="file" class="layui-btn" accept="image/*" style="display: none;" onchange="chooseHeaderChangeFile()">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <img id="img_url" src=""/>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">跳转链接:</label>
            <div class="layui-input-inline">
                <input type="text" id="join_url"  class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">显示位置</label>
            <div class="layui-input-block">
                <select id="position" name="position">
                    <option value="">请选择位置</option>
                    <option value="0">全部</option>
                    <option value="1">首页</option>
                    <option value="2">附近</option>
                    <option value="3">平台积分商城</option>
                    <option value="4">商家积分商城</option>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
                <input type="radio" name="is_checked" value="0" title="是" checked>
                <input type="radio" name="is_checked" value="1" title="否">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn"                   type="button" onclick="from_submit();">立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset" >重置    </button>
            </div>
        </div>

    </form>
</div>

</body>
</html>
