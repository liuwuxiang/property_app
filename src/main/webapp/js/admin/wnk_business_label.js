
var business_id = null;

var layForm = null;

function init() {

    business_id = $('#business_id').val();

    // 初始化form表单并且刷新表单
    layui.use("form", function () {
        layForm = layui.form;
    });


    // 获取所有特色内容
    getBusinessLable(1);
    // 获取所有服务内容
    getBusinessLable(0);

    //selectBusinessTrueLabel
    selectBusinessTrueLabel();

}

/**
 *  获取服务/特色内容数据
 * @param {Object} type  0 :服务内容 1:特色内容
 */
function getBusinessLable(type) {
    $.ajax({
        url: '/property_system/admin/getBusinessLabel',
        type: "POST",
        dataType: 'json',
        data: {
            'type': type
        },
        success: function (ret) {
            var div,name ;
            if (type == 0){
                div = $('#fuwu_div');
                name = 'fuwu';
            } else {
                div = $('#tese_div');
                name = 'test';
            }
            if (parseInt(ret.status) === 0){
                div.empty();
                for (var i=0;i<ret.data.length;i++){
                    div.append('<input type="checkbox" name="'+name+'" title="'+ret.data[i].title+'" value="'+ret.data[i].value+'">');
                }
            }
            layForm.render();

        }
    });
}

/**
 * 获取商家已经选中的特色/服务内容
 */
function selectBusinessTrueLabel() {
    $.ajax({
        url: '/property_system/admin/selectBusinessTrueLabel',
        type: "POST",
        dataType: 'json',
        data: {
            'business_id': business_id
        },
        success: function (ret) {
            if (parseInt(ret.status) === 0){
                var tese = ret.data.tese_label;
                var fuwu = ret.data.fuwu_label;
                if (tese !== undefined && String(tese) !== '' && tese !== null){
                    var teseArr = tese.split(',');
                    for (var i =0;i < teseArr.length ;i ++ ){
                        var input =  $('input[type=checkbox]');
                        for (var j = 0;j < input.length;j++){
                            if (input[j].value == teseArr[i]){
                                input[j].checked = true;
                            }
                        }
                    }
                }

                if (fuwu !== undefined && String(fuwu) !== '' && fuwu !== null) {
                    var fuwuArr = fuwu.split(',');

                    for (var i = 0; i < fuwuArr.length; i++) {
                        var input = $('input[type=checkbox]');
                        for (var j = 0; j < input.length; j++) {
                            if (input[j].value == fuwuArr[i]) {
                                input[j].checked = true;
                            }
                        }
                    }
                }

            }
            layForm.render();

        }
    });
}

/**
 * 保存商家特色/服务内容
 */
function submitBusinessLabel() {
    var check = $('input[type=checkbox]:checked');
    var fuwuParam = '';
    var teseParam = '';

    for (var i = 0 ;i<check.length;i++){
        if (check[i].name == 'fuwu'){
            fuwuParam += check[i].value + ',';
        } else {
            teseParam += check[i].value + ',';
        }
    }


    console.log(fuwuParam);
    console.log(teseParam);

    $.ajax({
        url: '/property_system/admin/updateBusinessLabel',
        type: "POST",
        dataType: 'json',
        data: {
            'business_id': business_id,
            'fuwu_label' : fuwuParam.substring(0,fuwuParam.length - 1),
            'tese_label' : teseParam.substring(0,teseParam.length - 1)
        },
        success: function (ret) {
            console.log(ret);
            if (parseInt(ret.status) === 0) {
                layer.open({
                    offset: ['40%', '40%'],
                    title: '消息:',
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function () {
                        parent.layer.close(parent.layer.getFrameIndex(window.name));
                        parent.location.reload();
                    }
                });
            } else {
                layer.msg(ret.msg);
            }
            layForm.render();
        }
    });
}