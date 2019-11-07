<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>积分商城-订单管理</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/js/wangEditor.css">

    <style>
        .w-e-text-container{
            z-index: 999 !important;
        }
    </style>

</head>
<body onload="initDate()">

<input type="hidden" value="${protocol_id}" id="protocol_id">

<div style="margin-top: 10px">
    <form class="layui-form" id="integral-order-edit" onsubmit="return false;" style="width: 80%">

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">协议内容</label>
            <div class="layui-input-block">
                <div id="detail_text"></div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">选择商家</label>
            <div class="layui-input-block" >
                <select id="business_id">
                    <option value="">暂未选择</option>
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
                <button class="layui-btn" type="button" onclick="insertOrUpdateBusinessOpenCardProtocol()">立即提交</button>
            </div>
        </div>
    </form>
</div>

</table>
</body>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/wangEditor.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/admin/wnk/wnk_business_open_card_protocol_edit.js"></script>
</html>