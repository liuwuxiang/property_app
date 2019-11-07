var layForm;
var layEdit;
var layEditIndex;

var editor = new window.wangEditor('#detail_text');

layui.use('form',function(){
    layForm = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

    initLayUI();

    //获取说明内容
    function getContent() {
        var type = document.getElementById("type").value;
        var loading_index = layer.load(1);
        $.ajax({
            url: "/property_system/admin/getIntegralHelpContentByType",
            type: "POST",
            headers: {'login_session': mMain.storage["login_session"]},
            dataType: 'json',
            data: {"user_id": mMain.storage["user_id"], "type": type},
            success: function (ret) {
                layer.close(loading_index);
                if (ret.status == 0) {
                    var open_type = ret.data.open_type;
                    console.log(open_type);
                    if(open_type == 0){
                        document.getElementsByName("open_type_check")[0].checked = true;
                        $('input[name=open_type_check]')[0].checked = true;
                        $("#open_url_div").hide();
                        $("#open_text_div").show();

                        editor.txt.html(ret.data.content);

                       // layEdit.setContent(layEditIndex, ret.data.content, false);
                    } else{
                        document.getElementsByName("open_type_check")[1].checked = true;
                        $('input[name=open_type_check]')[1].checked = true;
                        $("#open_url_div").show();
                        $("#open_text_div").hide();
                        document.getElementById("open_url").value = ret.data.content;
                    }

                    // $('#detail_text').val(ret.data.content);
                }
                else{
                    parent.layer.alert(ret.msg, {icon: 5});
                    document.getElementsByName("open_type_check")[0].checked = true;
                    $("#open_url_div").hide();
                    $("#open_text_div").show();
                }

                layForm.render();
            }
        });
    }

    function initLayUI(){
        // // 加载LayUI相关组件
        // layui.use(['form','layedit'], function () {
        //     // layForm = layui.form;
        //     layEdit = layui.layedit;
        //
        //     // 初始化富文本编辑器
        //     layEdit.set({                    // 上传接口
        //         uploadImage:{
        //             url : '/property_system/images/saveImageLayUi',
        //             type:'post'
        //         }
        //     });
        //     layEditIndex = layEdit.build('detail_text', {  // 构建富文本编辑器
        //         height: 400,
        //         tool: [
        //             'strong' //加粗
        //             ,'italic' //斜体
        //             ,'underline' //下划线
        //             ,'del' //删除线
        //             ,'|' //分割线
        //             ,'left' //左对齐
        //             ,'center' //居中对齐
        //             ,'right' //右对齐
        //             ,'link' //超链接
        //             ,'unlink' //清除链接
        //             ,'image' //插入图片
        //         ]
        //     });
        //
        // });

        editor.customConfig.showLinkImg = false;
        // 配置上传图片接口
        editor.customConfig.uploadImgServer = '/property_system/images/saveImageByWangEditor';
        editor.customConfig.uploadFileName = 'file';
        editor.create();


        getContent();
    }


    /**
     * 表单提交事件
     */
    $('#problem-form-submit').click(function () {
        var type = document.getElementById("type").value;
        var content = layui.layedit.getContent(layEditIndex);
        var open_way = $('input[name=open_type_check]:checked').val();
        if(open_way == 1){
            content = document.getElementById("open_url").value;
        }
        if (content == undefined || content == ""){
            parent.layer.alert("请输入说明内容", {icon: 5});
        }
        else{
            var loading_index = layer.load(1);
            $.ajax({
                url: "/property_system/admin/addOrUpdateIntegralHelpContent",
                data: {
                    'type'      : type,
                    'content'   : content,
                    'open_way'  : open_way
                },
                type: "post",
                dataType: "json",
                success: function (ret) {
                    layer.close(loading_index);
                    if (parseInt(ret.status) === 0){
                        parent.layer.alert(ret.msg, {icon: 6});
                    } else {
                        parent.layer.alert(ret.msg, {icon: 5});
                    }
                }
            });
        }

    });

    layForm.on('radio(open_type_check)', function (data) {
        //打开方式(0-富文本,1-链接)
        var open_type = data.value;
        if (open_type == 0){
            document.getElementById("open_text_div").style.display = "block";
            document.getElementById("open_url_div").style.display = "none";
        }
        else{
            document.getElementById("open_text_div").style.display = "none";
            document.getElementById("open_url_div").style.display = "block";
        }
    });
});
