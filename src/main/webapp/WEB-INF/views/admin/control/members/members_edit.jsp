<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会员管理-编辑会员信息</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/wx/ajaxfileupload.js"></script>
    <script src="${ctx}/js/admin/members_edit.js"></script>


    <style>
        #img_preview_header {
            width: 5rem;
            height: 5rem;
        }
    </style>
</head>
<body onload="initData()">
<div style="margin: 5% 15%">
<form id="members_edit_from" class="layui-form">
    <input type="hidden" value="${user_id}" id="user_id">

    <div class="layui-form-item">
        <label class="layui-form-label">昵称:</label>
        <div class="layui-input-inline">
            <input type="text" id="nick_name"  class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">头像:</label>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn" id="img_upload_show">
                <i class="layui-icon">&#xe67c;</i>选择头像
            </button>
            <input id="img_upload_header" name="ajaxFile" type="file" class="layui-btn" style="display: none;" onchange="imgUpload()">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <img id="img_preview_header" src="#" data-src="default_header.jpg"/>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别:</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="0" title="男" checked>
            <input type="radio" name="sex" value="1" title="女">
            <input type="radio" name="sex" value="2" title="保密">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱:</label>
        <div class="layui-input-inline">
            <input type="text" id="email"   class="layui-input">
        </div>
    </div>



    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button" onclick="adminUpdateUserInfoById();">立即提交</button>
            <button class="layui-btn layui-btn-primary" type="reset">重置</button>
        </div>
    </div>
</form>
</div>
</body>
</html>
