var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
	layui.use('table', function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#members_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'province_id', title: 'ID',sort:true}
	      ,{field:'name', title: '省份名称',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllProvinceAndCity'
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
    //加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//添加省份
function addProvince(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '添加省份',
  		content: '/property_system/admin/addProvince',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改省份
function setProvince(){
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '修改省份',
            content: '/property_system/admin/setProvince?record_id='+model.province_id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}


//城市管理
function cityManager() {
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '城市管理',
            content: '/property_system/admin/cityManager?province_id='+model.province_id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var name = document.getElementById("name").value;
    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchProvinceAndCityByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'province_id', title: 'ID',sort:true}
            ,{field:'name', title: '省份名称',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'name'  : name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}