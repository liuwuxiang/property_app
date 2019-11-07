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
	      ,{field:'id', title: 'ID',sort:true}
	      ,{field:'user_mobile', title: '兑换用户',sort:true}
	      ,{field:'consume_integral_number', title: '消耗积分(个)',sort:true}
	      ,{field:'silver_coin_number', title: '获得银币(个)',sort:true}
	      ,{field:'type', title: '兑换方式',sort:true}
	      ,{field:'exchange_date', title: '兑换时间',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllSilverCoinExchange'
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
            elem: '#exchange_date', //指定元素
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
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '修改会员等级',
  		content: 'set_member_level.jsp',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

// 根据条件模糊查询
function Search() {
    var user_mobile =document.getElementById("user_mobile").value;
    var consume_integral_number = $('#consume_integral_number').val();
    var silver_coin_number = $('#silver_coin_number').val();
    var type = $('#type').val();
    var exchange_date = $('#exchange_date').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchSilverCoinExchangeByConditions'
        , cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'user_mobile', title: '兑换用户',sort:true}
            ,{field:'consume_integral_number', title: '消耗积分(个)',sort:true}
            ,{field:'silver_coin_number', title: '获得银币(个)',sort:true}
            ,{field:'type', title: '兑换方式',sort:true}
            ,{field:'exchange_date', title: '兑换时间',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'user_mobile'             : user_mobile,
            'consume_integral_number' : consume_integral_number,
            'silver_coin_number'      : silver_coin_number,
            'type'                    : type,
            'exchange_date'           : exchange_date,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}
