<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>标签排序</title>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/wnk_business_tag_sort.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/wnk_business_tag_sort.js"></script>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
</head>
<body onload="getTag()">
<div class="top_div">
    <button class="layui-btn" onclick="saveTagSort()">保存</button>
</div>
<a class="tip_tag">注：输入的标签序号越大用户端标签显示越靠前</a>
<input type="hidden" value="${record_id}" id="record_id">
<div class="header_title_div">
    <a>标签名称</a>
    <a>标签序号</a>
</div>
<ul class="set_member_level_ul">
    <%--<li>--%>
        <%--<a>标签名称</a>--%>
        <%--<input type="number" name="title" placeholder="请输入序号" id="tag_id" autocomplete="off" class="layui-input">--%>
    <%--</li>--%>
</ul>

</body>
</html>
