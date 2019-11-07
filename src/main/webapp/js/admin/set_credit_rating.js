//视图初始化
function initView(state){
	initRichTextBox();
	if (state == 0){
        $("#is_qiyong").attr("checked",false);
        layui.use('form', function(){
            form = layui.form;
            form.render("checkbox");
        });
	}
	else{
        $("#is_qiyong").attr("checked",true);
        layui.use('form', function(){
            form = layui.form;
            form.render("checkbox");
        });
	}

}

//初始化富文本框
function initRichTextBox(){
	layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('course_detail', {
		  height: 600, //设置编辑器高度
		  uploadImage: {url: '/upload/', type: 'post'}, //设定图片上传接口
		  tool: [
			  'strong' //加粗
			  ,'italic' //斜体
			  ,'underline' //下划线
			  ,'del' //删除线
			  
			  ,'|' //分割线
			  
			  ,'left' //左对齐
			  ,'center' //居中对齐
			  ,'right' //右对齐
			  ,'link' //超链接
			  ,'unlink' //清除链接
			  ,'image' //插入图片
		  ]
	  });
	});
	
}

//保存评级信息
function saveCreditRating() {
	var record_id = document.getElementById("record_id").value;
	var clasp_value = document.getElementById("clasp_value").value;
	var checkbox = document.getElementById("is_qiyong");
	var state = 0;
	if (checkbox.checked){
		state = 1;
	}
	else{
		state = 0;
	}
	if (clasp_value == undefined || clasp_value == ""){
        layer.msg('请输入扣信额度!', {icon: 5});
	}
	else if (clasp_value <= 0){
        layer.msg('扣信额度不可小于等于0!', {icon: 5});
	}
	else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setCreditRatingInformation",
            type:"POST",
            dataType : 'json',
            data:{"record_id":record_id,"clasp_value":clasp_value,"state":state},
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
}
