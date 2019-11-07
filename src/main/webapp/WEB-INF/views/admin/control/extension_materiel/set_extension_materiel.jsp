<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推广物料-修改</title>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/extension_materiel/set_extension_materiel.js"></script>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
</head>
<body onload="getMaterielInformation()">
<input type="hidden" value="${record_id}" id="record_id">
<ul class="set_member_level_ul">
    <li>
        <a>物料类型</a>
        <input type="text" name="title" placeholder="请输入物料类型" id="name" autocomplete="off" class="layui-input" readonly="readonly">
    </li>
    <li>
        <a>限制购买次数</a>
        <select class="layui-select-group" id="buy_limit_select" lay-filter="buy_limit_select">
            <option value="0">限制</option>
            <option value="1">不限制</option>
        </select>
    </li>
    <li>
        <a>限制次数</a>
        <input type="text" name="title" placeholder="请输入物料限制购买次数" id="buy_number" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>物料图标</a>
        <button type="button" class="layui-btn" onclick="photoChoose()">
            <i class="layui-icon">&#xe67c;</i>物料图标
        </button>
        <input id="img_upload" name="ajaxFile" type="file"  class="layui-btn" style="display: none;">
        <img src="" id="img_logo" style="display: block;margin-top: 10px;">
    </li>
    <li>
        <a>物料背景图</a>
        <button type="button" class="layui-btn" onclick="photoChooseBackground()">
            <i class="layui-icon">&#xe67c;</i>物料背景图
        </button>
        <input id="img_upload_background" name="ajaxFile_background" type="file"  class="layui-btn" style="display: none;">
        <img src="" id="img_logo_background" style="display: block;margin-top: 10px;">
    </li>
</ul>
<div class="top_div">
    <button class="layui-btn" onclick="updateMateriel()">修改</button>
</div>

</body>
</html>
