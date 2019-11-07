<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content='easyui,jui,jquery easyui,easyui demo,easyui中文'/>
    <meta name="description" content='辰蓝科技'/>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>物业系统后台</title>
    <link rel="stylesheet" href="/property_system/css/admin/bootstrap.min.css">
    <link rel="stylesheet" href="/property_system/css/admin/font-awesome.min.css">
    <script src="/property_system/js/admin/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
    <script src="/property_system/js/admin/login.js"></script>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <style type="text/css">
        html, body {
            height: 100%;
        }

        .box {
            background: url("/property_system/images/admin/loginBg.jpg") no-repeat center center;
            background-size: cover;

            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }

        .login-box {
            width: 100%;
            max-width: 500px;
            height: 400px;
            position: absolute;
            top: 50%;

            margin-top: -200px;
            /*设置负值，为要定位子盒子的一半高度*/

        }

        @media screen and (min-width: 500px) {
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -250px;
            }
        }

        .form {
            width: 100%;
            max-width: 500px;
            height: 275px;
            margin: 2px auto 0px auto;
        }

        .login-content {
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            height: 250px;
            width: 100%;
            max-width: 500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
        }

        .input-group {
            margin: 30px 0px 0px 0px !important;
        }

        .form-control,
        .input-group {
            height: 40px;
        }

        .form-actions {
            margin-top: 30px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }

        .login-title {
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }

        .login-title h1 {
            margin-top: 10px !important;
        }

        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }

        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }

        .flag {
            position: absolute;
            top: 10px;
            right: 10px;
            color: #fff;
            font-weight: bold;
            font: 14px/normal "microsoft yahei", "Times New Roman", "宋体", Times, serif;
        }
    </style>
    <title>${config_siteConfig.cfgCompanyName}</title>
</head>
<body>
<div class="box">
    <div class="login-box">
        <div class="login-title text-center">
            <span class="flag"><i class="fa fa-user"></i> 管理员登录</span>
            <h1>
                <small>物业系统后台</small>
            </h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form id="modifyPassword" class="form-horizontal" action="#" method="post">
                    <input type="hidden" id="referer" name="referer" value="${param.referer}">
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="username" class="form-control" placeholder="用户名"
                                       value="">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="password" class="form-control"
                                       placeholder="密码" value="">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-12 text-center">
                            <button type="button" id="login" class="btn btn-sm btn-success" onclick="loginAction()">
                                <span class="fa fa-check-circle"></span> 登录
                            </button>
                            <button type="button" id="reset" class="btn btn-sm btn-danger" onclick="resetInput()">
                                <span class="fa fa-close"></span> 重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span class="text-danger"><i class="fa fa-warning"></i> 用户名或密码错误，请重试！</span>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	function resetInput() {
		document.getElementById("username").value="";
		document.getElementById("password").value="";
	}
</script>

</body>
</html>