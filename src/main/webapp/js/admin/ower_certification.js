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
	      ,{field:'buy_house_mobile', title: '购房电话',sort:true}
	      ,{field:'buy_house_name', title: '购房姓名',sort:true}
	      ,{field:'city', title: '城市',sort:true}
	      ,{field:'residential_name', title: '小区',sort:true}
	      ,{field:'house_number', title: '房号',sort:true}
	      ,{field:'state', title: '状态',sort:true}
	      ,{field:'submit_date_str', title: '提交时间',sort:true}
	      
	    ]]
	    ,page: true
	    ,url:'/property_system/admin/getAllOwnerCertification'
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
            elem: '#submit_date_str', //指定元素id
            btns: ['confirm'],
            type: 'datetime'
        });

	});

    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//审核通过事件
function reviewPass(){
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
    else{
        var model = data[0];
        layer.confirm('确定审核通过？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            setStateNetwork(model.id,1);
        }, function(){

        });
    }
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var buy_house_mobile = document.getElementById("buy_house_mobile").value;
    var buy_house_name = document.getElementById("buy_house_name").value;
    var city = document.getElementById("city").value;
    var residential_name = document.getElementById("residential_name").value;
    var house_number = document.getElementById("house_number").value;
    var state = document.getElementById("state").value;
    var submit_date_str = document.getElementById("submit_date_str").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchOwnerCertificationByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'buy_house_mobile', title: '购房电话',sort:true}
            ,{field:'buy_house_name', title: '购房姓名',sort:true}
            ,{field:'city', title: '城市',sort:true}
            ,{field:'residential_name', title: '小区',sort:true}
            ,{field:'house_number', title: '房号',sort:true}
            ,{field:'state', title: '状态',sort:true}
            ,{field:'submit_date_str', title: '提交时间',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'buy_house_mobile' : buy_house_mobile,
            'buy_house_name'   : buy_house_name,
            'city'             : city,
            'residential_name' : residential_name,
            'house_number'     : house_number,
            'state'            : state,
            'submit_date_str'  : submit_date_str,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}


//审核不通过事件
function reviewNoPass(){
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
    else{
        var model = data[0];
        layer.confirm('确定审核不通过？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            setStateNetwork(model.id,2);
        }, function(){

        });
    }
}


//修改认证状态事件
function setStateNetwork(record_id,state) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/setOwnerCertificationPass",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id,"state":state},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                parent.layer.alert(data.msg, {icon: 6});
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}