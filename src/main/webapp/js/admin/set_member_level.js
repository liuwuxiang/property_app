var layedit;
var layeditIndex;

/**
 * 富文本编辑器对象
 * @type {null}
 */
var editor = new window.wangEditor('#course_detail');

//视图初始化
function initView(){
	initRichTextBox();
}

//初始化富文本框
function initRichTextBox(){

    editor.customConfig.showLinkImg = false;
    // 配置上传图片接口
    editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    editor.customConfig.uploadFileName = 'file';
    editor.create();

	/*
	layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('course_detail', {
		  height: 600, //设置编辑器高度
		  uploadImage: {url: '/property_system/images/savaimage13.do', type: 'post'}, //设定图片上传接口
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
		  ]
	  });
	});
	*/
}

//修改会员等级信息
function setMemberLevelInformation() {
	var level_id = document.getElementById("level_id").value;
	var recharge_consumption_integral = document.getElementById("recharge_consumption_integral").value;
    // var brief_introduction = layedit.getContent(layeditIndex);
    var brief_introduction = editor.txt.html();

    if (recharge_consumption_integral == undefined || recharge_consumption_integral == ""){
        layer.msg('请输入升级条件!', {icon: 5});
	}
	else if (recharge_consumption_integral < 0){
        layer.msg('升级条件不可小于0!', {icon: 5});
	}
	else if (brief_introduction == undefined || brief_introduction == ""){
        layer.msg('请输入等级介绍!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setMemberLevel",
            type:"POST",
            dataType : 'json',
            data:{"level_id":level_id,"recharge_consumption_integral":recharge_consumption_integral,"brief_introduction":brief_introduction},
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

/*
layui.use('layedit', function(){
    layedit = layui.layedit
        ,$ = layui.jquery;
    layeditIndex = layedit.build('course_detail');
});
*/
