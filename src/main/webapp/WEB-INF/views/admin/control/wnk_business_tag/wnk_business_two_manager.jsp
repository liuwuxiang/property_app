<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户二级标签管理-万能卡</title>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/members.css">


    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/wnk_business_two_manager.js"></script>
</head>
<body>

<input type="hidden" id="one_tag_id" value="${one_tag_id}">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addBusinessOneTag()">
        <i class="layui-icon">&#xe642;</i> 添加
    </button>
    <button class="layui-btn layui-btn-sm" onclick="setBusinessTag()">
        <i class="layui-icon">&#xe65e;</i> 修改
    </button>
    <button class="layui-btn layui-btn-sm" onclick="deleteBusinessTag()">
        <i class="layui-icon">&#xe65e;</i> 删除
    </button>
</div>

<table class="layui-hide" id="members_manager_table">

</table>

<script>
    initTableAndData();
</script>
</body>
</html>
