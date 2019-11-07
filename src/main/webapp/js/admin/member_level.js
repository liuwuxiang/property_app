var table = null;

var layFrom = null;
//初始化table组件以及数据
function initTableAndData(){
	layui.use('table', function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#member_level_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', title: 'ID',sort: true}
	      ,{field:'name', title: '等级名称',sort: true}
	      ,{field:'is_default_level', title: '默认等级',sort: true}
	      ,{field:'recharge_consumption_integral', title: '消耗积分兑换币(多少积分)',sort: true}
	    ]]
	    ,page: true
	    ,url:'/property_system/admin/getAllMemberLevel'
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
	});

    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//模糊查询
function Search() {
    //找到table页面对应id的value值
    var name = document.getElementById("name").value;
    var is_default_level = document.getElementById("is_default_level").value;
    var recharge_consumption_integral = document.getElementById("recharge_consumption_integral").value;
    //方法更新渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchMemberLevelByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort: true}
            ,{field:'name', title: '等级名称',sort: true}
            ,{field:'is_default_level', title: '默认等级',sort: true}
            ,{field:'recharge_consumption_integral', title: '消耗积分兑换币(多少积分)',sort: true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'name' : name,
            'is_default_level' : is_default_level,
            'recharge_consumption_integral' : recharge_consumption_integral,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}


//修改会员等级
function setMemberLevel(){
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else{
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '修改会员等级',
            content: '/property_system/admin/setMemberLevel?level_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}


