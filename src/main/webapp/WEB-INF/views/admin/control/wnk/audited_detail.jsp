<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>审核详情</title>

    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">

    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/audited_detail.js"></script>
</head>
<body onload="initView()">
<div class="top_div">
    <button class="layui-btn" onclick="updateAuditedInformation(1)">同意</button>
    <button class="layui-btn" onclick="updateAuditedInformation(2)">拒绝</button>
</div>
<input type="hidden" value="${record_id}" id="record_id">
<input type="hidden" value="${type_id}" id="type_id">
<input type="hidden" value="${lunbo}" id="lunbo">
<input type="hidden" value="${recommend_id}" id="recommend_id">
<ul class="set_member_level_ul">
    <li>
        <a>店铺名称</a>
        <input type="text" name="title" value="${store_name}" placeholder="请输入店铺名称" id="store_name" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>店铺区域</a>
        <input type="text" name="title" placeholder="请输入店铺区域" id="area" value="${area}" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>店铺地址</a>
        <input type="text" name="title" placeholder="请输入店铺详细地址" id="address" value="${address}" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>登录账号</a>
        <input type="number" name="title" value="${login_account}" placeholder="请输入登录账号" id="login_account" autocomplete="off" class="layui-input">
    </li>

    <li>
        <a>联系人姓名</a>
        <input type="text" name="title" value="${contact_name}" placeholder="请输入联系人姓名" id="contact_name" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>联系电话</a>
        <input type="number" name="title" value="${contact_mobile}" placeholder="请输入联系电话" id="contact_mobile" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>店铺简介</a>
        <textarea placeholder="请输入店铺简介信息" class="layui-textarea" id="store_describe">${store_describe}</textarea>
    </li>
    <li>
        <a>商户分类</a>
        <select class="layui-select-group" id="business_type_select" lay-filter="city_choose_select" onchange="cityChange()">
            <%--<option value="0">1</option>--%>
        </select>
    </li>
    <%--<li>--%>
        <%--<a>营业执照</a>--%>
        <%--<img src="${yingyezhizhao}" id="yingyezhizhao">--%>
    <%--</li>--%>
    <%--<li>--%>
        <%--<a>法人身份证</a>--%>
        <%--<img src="${legal_person_id_card}" id="legal_person_id_card">--%>
    <%--</li>--%>
</ul>
</body>
</html>
