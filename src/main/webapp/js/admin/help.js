// var E = window.wangEditor;
//
// var ty_make = new E('#ty_make');
// var ty_get  = new E('#ty_get');
// var ty_kj   = new E('#ty_kj');
// var xf_make = new E('#xf_make');
// var xf_get  = new E('#xf_get');
// var xf_kj   = new E('#xf_kj');
// var about_us = new E('#about_us');


//视图初始化
function initView(){
	initRichTextBox();
}


layui.use('element', function(){
		  var $ = layui.jquery,
		  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
		  
		  //触发事件
		  var active = {
		    tabAdd: function(){
		      //新增一个Tab项
		      element.tabAdd('demo', {
		        title: '新选项'+ (Math.random()*1000|0) ,//用于演示
		        content: '内容'+ (Math.random()*1000|0) ,
		        id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
		      })
		    },
		    tabDelete: function(othis){
		      //删除指定Tab项
		      element.tabDelete('demo', '44'); //删除：“商品管理”
		      othis.addClass('layui-btn-disabled');
		    },
		    tabChange: function(){
		      //切换到指定Tab项
		      element.tabChange('demo', '22'); //切换到：用户管理
		    }
		  };
});

//初始化富文本框
function initRichTextBox(){
    //
    // ty_make.customConfig.showLinkImg = false;
    // ty_make.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // ty_make.customConfig.uploadFileName = 'file';
    // ty_make.create();
    //
    // ty_get.customConfig.showLinkImg = false;
    // ty_get.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // ty_get.customConfig.uploadFileName = 'file';
    // ty_get.create();
    //
    // ty_kj.customConfig.showLinkImg = false;
    // ty_kj.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // ty_kj.customConfig.uploadFileName = 'file';
    // ty_kj.create();
    //
    // xf_make.customConfig.showLinkImg = false;
    // xf_make.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // xf_make.customConfig.uploadFileName = 'file';
    // xf_make.create();
    //
    // xf_get.customConfig.showLinkImg = false;
    // xf_get.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // xf_get.customConfig.uploadFileName = 'file';
    // xf_get.create();
    //
    // xf_kj.customConfig.showLinkImg = false;
    // xf_kj.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // xf_kj.customConfig.uploadFileName = 'file';
    // xf_kj.create();
    //
    // about_us.customConfig.showLinkImg = false;
    // // 配置上传图片接口
    // about_us.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
    // about_us.customConfig.uploadFileName = 'file';
    // about_us.create();



	layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('ty_make', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('ty_get', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('ty_kj', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('xf_make', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('xf_get', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('xf_kj', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	  layedit.build('about_us', {
		  height: 400, //设置编辑器高度
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
		  ]
	  });
	});

}