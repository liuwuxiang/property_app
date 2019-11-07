//反馈处理
function dealFeedback(){
	var record_id = document.getElementById("record_id").value;
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '反馈处理',
  		content: '/property_system/admin/dealSuggestionFeedback?record_id='+record_id,
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//初始化数据
function initData() {
	var record_id = document.getElementById("record_id").value;
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/getFeedPhotos",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                var photos = data.data;
                for (var index = 0;index < photos.length;index++){
                    var html = "<li>"+
                        "<img src=\""+photos[index]+"\"/>"+
                        "</li>";

                    $("#photo_ul").append(html);
                }
            }
            else{

            }
        },
    });
}