<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新增一级标签</title>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/add_wnk_business_tag.js"></script>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
</head>
<body onload="initData()">
<input type="hidden" value="${makeType}" id="makeType">
<input type="hidden" value="${last_id}" id="last_id">
<ul class="set_member_level_ul">
    <li>
        <a>标签名称</a>
        <input type="text" name="title" placeholder="请输入标签名称" id="name" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>标签图标</a>
        <button type="button" class="layui-btn" id="img_upload_show" onclick="photoChoose()">
            <i class="layui-icon">&#xe67c;</i>标签图标
        </button>
        <input id="img_upload" name="ajaxFile" type="file"  class="layui-btn" style="display: none;">
        <img src="" id="img_preview" style="display: block;margin-top: 10px;">
    </li>
    <li>
        <a>标签状态</a>
        <select class="layui-select-group" id="tag_state_select" lay-filter="tag_state_select">
            <option value="1">启用</option>
            <option value="0">停用</option>
        </select>
    </li>
    <li id="relation_business_type" style="display: none;">
        <a>关联分类</a>
        <select class="layui-select-group" id="relation_business_type_select" lay-filter="relation_business_type_select">
            <option value="-1">不关联</option>
        </select>
    </li>
</ul>
<div class="top_div">
    <button class="layui-btn" onclick="addAction()">添加</button>
</div>

</body>
</html>
