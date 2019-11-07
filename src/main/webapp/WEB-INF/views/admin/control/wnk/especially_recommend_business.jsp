<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户管理-特别推荐商户</title>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/members.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/especially_recommend_business.js"></script>
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="cancelEspciallyRecommend()">
        <i class="layui-icon">&#x1006;</i> 取消特别推荐
    </button>
</div>

<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="店铺" id="store_name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="wnk_business_type">
                <option value="">请选择分类</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="state_str">
                <option value="">请选择状态</option>
                <option value="0">启用</option>
                <option value="1">停用</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="余额" id="balance" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="联系电话" id="contact_mobile" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" placeholder="请选择加入时间" id="join_time" autocomplete="off">
        </div>
        <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>
</html>
