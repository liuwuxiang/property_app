<%--
  Created by IntelliJ IDEA.
  User: 刘武祥
  Date: 2018/12/26 0026
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>添加商家分类</title>
        <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
        <link rel="stylesheet" href="/property_system/css/admin/layui.css">
        <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
        <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
        <script src="/property_system/js/admin/layui.js"></script>
        <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
        <script src="/property_system/js/admin/lay/modules/layer.js"></script>
        <script src="/property_system/js/admin/wnk_business_type/add_wnk_business_type.js"></script>
    </head>
</head>
<body onload="initTableAndData()">
<%--<input type="hidden" value="${type_id}" id="type_id">--%>
<div class="top_div">
    <button class="layui-btn" onclick="saveWnkBusinessTypeInformation()">保存</button>
</div>
<ul class="set_member_level_ul">
    <li>
        <a>分类名称</a>
        <input type="text" name="title" value="" placeholder="请输入商家分类" id="type_name" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>资费方式</a>
        <select class="layui-select-group" id="zifei_way_select" lay-filter="zifei_way_select">
            <option value="0">按次</option>
            <option value="1">按年</option>
        </select>
    </li>
    <li>
        <a>万能卡权益</a>
        <select class="layui-select-group" id="wnk_make_state_select" lay-filter="wnk_make_state_select">
            <option value="0">不执行万能卡权益</option>
            <option value="1">执行万能卡权益</option>
        </select>
    </li>
    <li>
        <a>优惠方式</a>
        <select class="layui-select-group" id="wnk_yh_select" lay-filter="wnk_yh_select">
            <option value="0">定价</option>
            <option value="1">折扣</option>
        </select>
    </li>
    <li id="member_price_li" >
        <a>会员价(若设置按年计费此会员价代表开卡价,若按次计费此价格代表开卡后消费价)</a>
        <input type="number" name="title" placeholder="请输入会员价格" id="member_price" value="" autocomplete="off" class="layui-input">
    </li>
    <li id="zhekou_li" >
        <a>折扣比例(%)</a>
        <input type="number" name="title" placeholder="请输入折扣比例(0-100)" id="zhekou_bili" value="" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>分类图标</a>
        <button type="button" class="layui-btn" id="img_upload_show" onclick="photoChoose()">
            <i class="layui-icon">&#xe67c;</i>分类图标
        </button>
        <input id="img_upload" name="ajaxFile" type="file"  class="layui-btn" style="display: none;">
        <img src="" id="img_preview" style="display: block;margin-top: 10px;">
    </li>
</ul>
</body>
</html>
