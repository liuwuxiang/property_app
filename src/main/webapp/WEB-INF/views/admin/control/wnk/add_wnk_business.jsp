<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新增/修改商户</title>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/add_wnk_business.js"></script>
</head>
<body onload="initView(${type})">
<div class="top_div">
    <button class="layui-btn" onclick="saveBusiness()">保存</button>
</div>
<input type="hidden" value="${business_id}" id="business_id">
<input type="hidden" value="${business_type_id}" id="business_type_id">
<input type="hidden" value="${state}" id="state">
<input type="hidden" value="${area}" id="area">

<ul class="set_member_level_ul">
    <li>
        <a>商户账号(手机号)</a>
        <input type="text" name="title" value="${account}" placeholder="请输入商户账号(手机号)" id="account" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>登录密码</a>
        <input type="password" name="title" value="${login_pwd}" placeholder="请输入登录密码" id="login_pwd" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>账号状态</a>
        <select class="layui-select-group" id="account_state_select" lay-filter="province_select" onchange="provinceChange()">
            <option value="0">启用</option>
            <option value="1">停用</option>
        </select>
    </li>
    <li>
        <a>商户分类</a>
        <select class="layui-select-group" id="business_type_select" lay-filter="city_choose_select" onchange="cityChange(this)">
            <%--<option value="0">1</option>--%>
        </select>
    </li>

    <li>
        <a>商户标签</a>
        <p id="business_tag_one" readonly="readonly"></p>
        <input type="hidden" value=""  id="business_tag_one_value">

        <br>
        <select class="layui-select-group" id="business_tag_two">
            <%--<option value="0">1</option>--%>
        </select>
    </li>

    <li>
        <a>店铺区域</a>
        <%--<input type="text" name="title" placeholder="请输入店铺区域" id="area" value="${area}" autocomplete="off" class="layui-input">--%>
        <select class="layui-select-group" id="area_select" lay-filter="city_choose_select">
            <%--<option value="0">1</option>--%>
        </select>
    </li>
    <li>
        <a>店铺名称</a>
        <input type="text" name="title" placeholder="请输入店铺名称" id="store_name" value="${store_name}" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>店铺地址</a>
        <input type="text" name="title" placeholder="请输入店铺详细地址" id="address" value="${address}" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>店铺简介</a>
        <textarea placeholder="请输入店铺简介信息" class="layui-textarea" id="store_describe">${store_describe}</textarea>
    </li>
    <li>
        <a>联系电话</a>
        <input type="text" name="title" placeholder="请输入店铺联系电话" id="contact_mobile" value="${contact_mobile}" autocomplete="off" class="layui-input">
    </li>

</ul>
</body>
</html>
