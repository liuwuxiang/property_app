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
    <link rel="stylesheet" href="${ctx}/js/wangEditor.css">
    <style>
        #video_logo {
            width: 50%;
            margin-top: 10px;
            margin-left: 12%;
        }
    </style>


</head>
<body>

<div style="margin: 5% 15%">

    <input type="hidden" id="problem_id" value="${problem_id}">

    <form class="layui-form">

        <div class="layui-form-item">
            <label class="layui-form-label">标题:</label>
            <div class="layui-input-inline">
                <label>
                    <input type="text" id="title"  class="layui-input">
                </label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">是否启用:</label>
            <div class="layui-input-block">
                <input type="radio" name="check" value="0" title="是">
                <input type="radio" name="check" value="1" title="否">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">类别:</label>
            <div class="layui-input-block">
                <input type="radio" name="problem_check" value="0" title="所有">
                <input type="radio" name="problem_check" value="1" title="用户">
                <input type="radio" name="problem_check" value="2" title="商家">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">打开方式:</label>
            <div class="layui-input-block">
                <input type="radio" name="open_type_check" value="0"  lay-filter="open_type_check"  title="富文本" checked>
                <input type="radio" name="open_type_check" value="1"  lay-filter="open_type_check"  title="链接">
                <input type="radio" name="open_type_check" value="2"  lay-filter="open_type_check"  title="视频">
            </div>
        </div>

        <div class="layui-form-item" id="open_url_video" style="display: none;">
            <label class="layui-form-label">上传视频:</label>
            <button type="button" class="layui-btn" onclick="$('#img_upload').click();">
                <i class="layui-icon">&#xe67c;</i>上传视频
            </button>
            <input id="img_upload" name="ajaxFile" type="file"  class="layui-btn" style="display: none;">
            <br>
            <video id="video_logo" src="#" controls="controls" loop="loop" autoplay="autoplay" style="display: none;"></video>
        </div>

        <div class="layui-form-item" id="open_url_div" style="display: none;">
            <label class="layui-form-label">链接:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <label>
                    <input type="text" id="open_url"  class="layui-input">
                </label>
            </div>
        </div>

        <%--content--%>
        <div class="layui-form-item layui-form-text" id="open_text_div">
            <label class="layui-form-label">解决方式:</label>
            <div class="layui-input-block">
                <label for="detail_text"></label>
                <%--<textarea id="detail_text" style="display: none;"></textarea>--%>
                <div id="detail_text"></div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn"  type="button" id="problem-form-submit">立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>

    </form>

</div>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/wangEditor.min.js"></script>
<script src="${ctx}/js/admin/problem/problem_edit.js"></script>
</body>
</html>
