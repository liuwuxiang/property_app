var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
	layui.use(['table', 'laydate'], function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#members_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id',                  title: 'ID',          sort: true}
	      ,{field:'mobile',              title: '手机号',      sort: true}
	      ,{field:'nick_name',           title: '昵称',        sort: true}
	      ,{field:'sex',                 title: '性别',        sort: true}
	      ,{field:'member_card_level',   title: '开卡类型',    sort: true}
	      ,{field:'general_integral',    title: '通用积分余额',sort: true}
	      ,{field:'silver_coin_balance', title: '玫瑰余额',    sort: true}
	      ,{field:'register_time',       title: '注册时间',    sort: true}
	      ,{field:'recommend_user_name', title: '推荐人',      sort:true}
	    ]]
	    ,page: true
	    ,url:'/property_system/admin/getAllMembers'
	   // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
	  	,method: 'post' //如果无需自定义HTTP类型，可不加该参数
	  //	,request: {} //如果无需自定义请求参数，可不加该参数
          ,response: {
              dataName: 'data'
              ,msgName: 'msg'
              ,statusName: 'status'
              ,statusCode: 0
          } //如果无需自定义数据响应名称，可不加该参数
	  });

        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#register_time', //指定元素
            btns: ['confirm'],
            type: 'datetime'
        });

	});
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//修改会员等级
function setMemberLevel(){
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        , data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    } else {
        var model = data[0];
        //弹出即全屏
        layer.open({
            type: 2,
            title: '编辑会员信息',
            content: 'members_edit?user_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
	}
}

//模糊查询
function Search() {
    //找到table页面对应id的value值
    var mobile               = document.getElementById("mobile").value;
    var nick_name            = document.getElementById("nick_name").value;
    var sex                  = document.getElementById("sex").value;
    var member_card_level    = document.getElementById("member_card_level").value;
    var general_integral     = $('#general_integral').val();
    var silver_coin_balance  = $('#silver_coin_balance').val();
    var register_time        = $('#register_time').val();
    var recommend_user_name  = $('#recommend_user_name').val();

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchMemberByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id',                  title: 'ID',          sort: true}
            ,{field:'mobile',              title: '手机号',      sort: true}
            ,{field:'nick_name',           title: '昵称',        sort: true}
            ,{field:'sex',                 title: '性别',        sort: true}
            ,{field:'member_card_level',   title: '开卡类型',    sort: true}
            ,{field:'general_integral',    title: '通用积分余额',sort: true}
            ,{field:'silver_coin_balance', title: '玫瑰余额',    sort: true}
            ,{field:'register_time',       title: '注册时间',    sort: true}
            ,{field:'recommend_user_name', title: '推荐人',      sort:true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'mobile'              : mobile,
            'nick_name'           : nick_name,
            'sex'                 : sex,
            'member_card_level'   : member_card_level,
            'general_integral'    : general_integral,
            'silver_coin_balance' : silver_coin_balance,
            'register_time'       : register_time,
            'recommend_user_name' : recommend_user_name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}



//会员充值
function memberRecharge(){
	//页面层
	layer.open({
	  type: 2,
	  title: '会员充值',
	  skin: 'layui-layer-rim', //加上边框
	  area: ['420px', '350px'], //宽高
	  content: 'member_recharge.jsp'
	});
}
