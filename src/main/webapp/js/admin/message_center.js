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
	      ,{field:'receiving_object', title: '消息对象',sort:true}
	      ,{field:'title', title: '标题',sort:true}
	      ,{field:'content', title: '内容',sort:true}
	      ,{field:'send_time', title: '时间',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllNotice'
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
            elem: '#send_time', //指定元素id
            btns: ['confirm'],
            type: 'datetime'
        });
    });

    //加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//新增消息
function addMessage(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '发送新消息',
  		content: '/property_system/admin/sendMessage',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var receiving_object = document.getElementById("receiving_object").value;
    var title = document.getElementById("title").value;
    var content = document.getElementById("content").value;
    var send_time = document.getElementById("send_time").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchMessageByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'receiving_object', title: '消息对象',sort:true}
            ,{field:'title', title: '标题',sort:true}
            ,{field:'content', title: '内容',sort:true}
            ,{field:'send_time', title: '时间',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'receiving_object' : receiving_object,
            'title'            : title,
            'content'          : content,
            'send_time'        : send_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}


