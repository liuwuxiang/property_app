//省份id
var province_id;
//初始化table组件以及数据
function initTableAndData(current_province_id){
    province_id = current_province_id;
	layui.use('table', function(){
	  var table = layui.table;
	  
	  table.render({
	    elem: '#members_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'city_id', title: 'ID',sort: true}
	      ,{field:'name', title: '城市名称',sort:true}
	      
	    ]]
	    ,page: true
	    ,url:'/property_system/admin/getAllCityByProvinceId'
	   ,where: {province_id: current_province_id} //如果无需传递额外参数，可不加该参数
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
}

//新增城市
function addCity(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '添加城市',
  		content: '/property_system/admin/addCity?province_id='+province_id,
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改城市
function setCity(){
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
            title: '修改城市',
            content: '/property_system/admin/setCity?province_id='+province_id+"&city_id="+model.city_id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}
