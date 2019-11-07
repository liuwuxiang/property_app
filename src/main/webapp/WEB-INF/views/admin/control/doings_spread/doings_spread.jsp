<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推广活动-推广活动审核</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
    <script src="${ctx}/js/wx/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/doings_spread/doings_spread.js"></script>
    <style>
        .show_img {
            width: 100%;
            height: auto;
        }

        .show_img > img {
            width: 100%;
            height: auto;
        }
    </style>

</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="verifyOkAndActivating()">
        <i class="layui-icon">&#xe605;</i> 审核通过并上架
    </button>

    <button class="layui-btn layui-btn-sm" onclick="verifyError()">
        <i class="layui-icon">&#x1006;</i> 审核拒绝
    </button>

    <button class="layui-btn layui-btn-sm" onclick="ActivatingOk()">
        <i class="layui-icon">&#xe6c6;</i> 上架
    </button>

    <button class="layui-btn layui-btn-sm" onclick="ActivatingError()">
        <i class="layui-icon">&#xe6c5;</i> 下架
    </button>
</div>

<div class="member_level_manager_top_div">
    <%--搜索--%>
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="活动标题" id="title" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="商家名称" id="store_name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="ad_type">
                <option value="">请选择活动类型</option>
                <option value="0">轮播图推广</option>
                <option value="1">系统消息推广</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="receive_type">
                <option value="">请选择接收对象</option>
                <option value="0">所有商家和用户</option>
                <option value="1">所有商家</option>
                <option value="2">所有用户</option>
                <option value="3">我的会员</option>
                <option value="4">我的商家</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="verify_statue">
                <option value="">请选择审核状态</option>
                <option value="0">待审核</option>
                <option value="1">审核通过</option>
                <option value="2">审核失败</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="activating_statue">
                <option value="">请选择上架状态</option>
                <option value="0">上架</option>
                <option value="1">下架</option>
            </select>
        </div>
        <div style="padding: .3rem 0">
            <div class="layui-inline">
                <input type="text" class="layui-input" id="create_time" placeholder="请选择发布时间">
            </div>
            <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
        </div>
    </form>
</div>

<table class="layui-hide" id="member_level_manager_table" lay-filter="test">

</table>
</body>

</html>