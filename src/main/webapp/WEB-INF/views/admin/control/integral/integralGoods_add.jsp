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

    <%--富文本编辑器--%>
    <script src="${ctx}/js/wangEditor.min.js"></script>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/wx/toast.js"></script>
    <script src="${ctx}/js/wx/hui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
    <script src="${ctx}/js/wx/integral_mall/main.js"></script>
    <script src="${ctx}/js/admin/integral_goods_add.js"></script>

</head>
<body>

<div style="margin: 5% 15%">

    <input type="hidden" id="id" value="${id}">
    <form id="integral-form" class="layui-form">

        <div class="layui-form-item">
            <label class="layui-form-label">商品名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="name" required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品价格:</label>
            <div class="layui-input-inline">
                <input type="text" name="price" required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品简介:</label>
            <div class="layui-input-inline">
                <input type="text" name="synopsis" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商家名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="trader" required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品图片:</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="img_upload_show">
                    <i class="layui-icon">&#xe67c;</i>商品图片
                </button>
                <input id="img_upload" name="ajaxFile" type="file" class="layui-btn" style="display: none;">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <img id="img_preview" src="${ctx}/images/wx/integral_mall/huodong.jpg" style="display: none"/>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">是否上架</label>
            <div class="layui-input-block">
                <input type="radio" name="isRecommend" value="0" title="是">
                <input type="radio" name="isRecommend" value="1" title="否">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">是否推荐</label>
            <div class="layui-input-block">
                <input type="radio" name="is_checked" value="0" title="是">
                <input type="radio" name="is_checked" value="1" title="否">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品分类</label>
            <div class="layui-input-block">
                <select id="type_id_select" name="type" lay-verify="required">
                    <%--<option value=""></option>--%>
                </select>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">商品详情</label>
            <div class="layui-input-block">
                <%--<textarea id="detail_text" style="display: none;"></textarea>--%>
                    <div id="detail_text"></div>
            </div>
        </div>

        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="detail">
        <input type="hidden" name="img">

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
