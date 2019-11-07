
function initDate() {
    selectSuggestStatisticsInfo();
    selectUserStatisticsInfo();
    selectBusinessStatisticsInfo();
    selectBusinessRoseDetailStatisticsInfo();
    selectUserRoseDetailStatisticsInfo();
    selectUserBalanceStatisticsInfo();
    selectBusinessBalanceStatisticsInfo();
    selectUserWithdrawStatisticsInfo();
    selectBusinessWithdrawStatisticsInfo();
    selectOrdersStatisticsInfo();
}

// 商品订单记录 - 商家
function selectOrdersStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectOrdersStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                $('#wnk_order_totle_number').html(data.data.wnk_order_totle_number);
                $('#wnk_order_complete_number').html(data.data.wnk_order_complete_number);
                $('#wnk_order_Tobeused_number').html(data.data.wnk_order_Tobeused_number);
                $('#wnk_order_Tobepaid_number').html(data.data.wnk_order_Tobepaid_number);
            }
        }
    });
}

// 建议反馈统计 - 商家AND用户
function selectSuggestStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectSuggestStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == null || data.data === undefined){
                    $('#total_number_user').html(0);
                    $('#wait_number_user').html(0);
                    $('#processing_number_user').html(0);
                    $('#complete_number_user').html(0);

                    $('#total_number_business').html(0);
                    $('#wait_number_business').html(0);
                    $('#processing_number_business').html(0);
                    $('#complete_number_business').html(0);
                }else {
                    $('#total_number_user').html(data.data.total_number_user == undefined ?"0" : data.data.total_number_user);
                    $('#wait_number_user').html(data.data.wait_number_user == undefined ?"0" : data.data.wait_number_user);
                    $('#processing_number_user').html(data.data.processing_number_user == undefined ?"0" : data.data.processing_number_user);
                    $('#complete_number_user').html(data.data.complete_number_user == undefined ?"0" : data.data.complete_number_user);

                    $('#total_number_business').html(data.data.total_number_business == undefined ?"0" : data.data.total_number_business);
                    $('#wait_number_business').html(data.data.wait_number_business == undefined ?"0" : data.data.wait_number_business);
                    $('#processing_number_business').html(data.data.processing_number_business == undefined ?"0" : data.data.processing_number_business);
                    $('#complete_number_business').html(data.data.complete_number_business == undefined ?"0" : data.data.complete_number_business);
                }
            }
        }
    });
}

// 用户统计
function selectUserStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectUserStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                $('#user_total_number').html(data.data.user_total_number);
                $('#today_user_number').html(data.data.today_user_number);
                $('#silver_user_number').html(data.data.silver_user_number);
                $('#gold_user_number').html(data.data.gold_user_number);
            }
        }
    });
}

// 商家统计
function selectBusinessStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectBusinessStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                $('#business_total_number').html(data.data.business_total_number);
                $('#business_today_number').html(data.data.business_today_number);
                if (parseInt(data.data.level_info.length) > 0){
                    var level_info = data.data.level_info;
                    for(var i = 0;i < level_info.length;i++){
                        var html =
                            '<li>' +
                            '   <a class="number_value" style="background: #009688;">'+level_info[i].level_number+'</a>' +
                            '   <a class="number_tag" id="business_today_number" style="color: #009688;">'+level_info[i].level_name+'</a>' +
                            '</li>';
                        $('#business_number_ul').append(html);
                    }
                }
            }
        }
    });
}

// 玫瑰兑换 - 用户
function selectUserRoseDetailStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectUserRoseDetailStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == null || data.data === undefined){
                    $('#user_rose_sum_number').html(0);
                    $('#user_rose_today_sum_number').html(0);
                    $('#user_rose_total_number').html(0);
                    $('#user_rose_today_total_number').html(0);
                } else {
                    $('#user_rose_sum_number').html(data.data.user_rose_sum_number == undefined ?"0" : data.data.user_rose_sum_number);
                    $('#user_rose_today_sum_number').html(data.data.user_rose_today_sum_number  == undefined  ?"0" : data.data.user_rose_today_sum_number);
                    $('#user_rose_total_number').html(data.data.user_rose_total_number  == undefined  ?"0" : data.data.user_rose_total_number);
                    $('#user_rose_today_total_number').html(data.data.user_rose_today_total_number  == undefined  ?"0" : data.data.user_rose_today_total_number);
                }
            }
        }
    });
}

// 玫瑰兑换 - 商家
function selectBusinessRoseDetailStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectBusinessRoseDetailStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == undefined || data.data == null) {
                    $('#business_rose_sum_number').html(0);
                    $('#business_rose_today_sum_number').html(0);
                    $('#business_rose_total_number').html(0);
                    $('#business_rose_today_total_number').html(0);
                } else {
                    $('#business_rose_sum_number').html(data.data.business_rose_sum_number == undefined ?"0" : data.data.business_rose_sum_number);
                    $('#business_rose_today_sum_number').html(data.data.business_rose_today_sum_number  == undefined  ?"0" : data.data.business_rose_today_sum_number);
                    $('#business_rose_total_number').html(data.data.business_rose_total_number  == undefined  ?"0" : data.data.business_rose_total_number);
                    $('#business_rose_today_total_number').html(data.data.business_rose_today_total_number  == undefined  ?"0" : data.data.business_rose_today_total_number);
                }
            }
        }
    });
}

// 充值统计 - 用户
function selectUserBalanceStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectUserBalanceStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == undefined || data.data == null) {
                    $('#user_balance_sum_number').html(0);
                    $('#user_balance_today_sum_number').html(0);
                    $('#user_balance_total_number').html(0);
                    $('#user_balance_today_total_number').html(0);
                } else {
                    $('#user_balance_sum_number').html(data.data.user_balance_sum_number == undefined ?"0" : data.data.user_balance_sum_number);
                    $('#user_balance_today_sum_number').html(data.data.user_balance_today_sum_number  == undefined  ?"0" : data.data.user_balance_today_sum_number);
                    $('#user_balance_total_number').html(data.data.user_balance_total_number  == undefined  ?"0" : data.data.user_balance_total_number);
                    $('#user_balance_today_total_number').html(data.data.user_balance_today_total_number  == undefined  ?"0" : data.data.user_balance_today_total_number);
                }
            }
        }
    });
}

// 充值统计 - 商家
function selectBusinessBalanceStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectBusinessBalanceStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == undefined || data.data == null) {
                    $('#business_balance_sum_number').html(0);
                    $('#business_balance_today_sum_number').html(0);
                    $('#business_balance_total_number').html(0);
                    $('#business_balance_today_total_number').html(0);
                } else {
                    $('#business_balance_sum_number').html(data.data.business_balance_sum_number == undefined ?"0" : data.data.business_balance_sum_number);
                    $('#business_balance_today_sum_number').html(data.data.business_balance_today_sum_number  == undefined  ?"0" : data.data.business_balance_today_sum_number);
                    $('#business_balance_total_number').html(data.data.business_balance_total_number  == undefined  ?"0" : data.data.business_balance_total_number);
                    $('#business_balance_today_total_number').html(data.data.business_balance_today_total_number  == undefined  ?"0" : data.data.business_balance_today_total_number);
                }
            }
        }
    });
}

// 提现统计 - 用户
function selectUserWithdrawStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectUserWithdrawStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                if (data.data == undefined || data.data == null) {
                    $('#user_withdraw_sum_number').html(0);
                    $('#user_withdraw_today_sum_number').html(0);
                    $('#user_withdraw_order_sum_number').html(0);
                    $('#user_withdraw_today_order_sum_number').html(0);
                    $('#user_withdraw_disposedOf_number').html(0);
                    $('#user_withdraw_untreated_number').html(0);
                    $('#user_withdraw_disposedOf_order_number').html(0);
                    $('#user_withdraw_untreated_order_number').html(0);
                } else {

                    $('#user_withdraw_sum_number').html(data.data.user_withdraw_sum_number == undefined ? "0" : data.data.user_withdraw_sum_number);
                    $('#user_withdraw_today_sum_number').html(data.data.user_withdraw_today_sum_number  == undefined  ?"0" : data.data.user_withdraw_today_sum_number);
                    $('#user_withdraw_order_sum_number').html(data.data.user_withdraw_order_sum_number  == undefined  ?"0" : data.data.user_withdraw_order_sum_number);
                    $('#user_withdraw_today_order_sum_number').html(data.data.user_withdraw_today_order_sum_number  == undefined  ?"0" : data.data.user_withdraw_today_order_sum_number);
                    $('#user_withdraw_disposedOf_number').html(data.data.user_withdraw_disposedOf_number == undefined ?"0" : data.data.user_withdraw_disposedOf_number);
                    $('#user_withdraw_untreated_number').html(data.data.user_withdraw_untreated_number == undefined ?"0" : data.data.user_withdraw_untreated_number);
                    $('#user_withdraw_disposedOf_order_number').html(data.data.user_withdraw_disposedOf_order_number == undefined ?"0" : data.data.user_withdraw_disposedOf_order_number);
                    $('#user_withdraw_untreated_order_number').html(data.data.user_withdraw_untreated_order_number == undefined ?"0" : data.data.user_withdraw_untreated_order_number);
                }
            }
        }
    });
}

// 提现统计 - 商家
function selectBusinessWithdrawStatisticsInfo() {
    $.ajax({
        url:"/property_system/admin/selectBusinessWithdrawStatisticsInfo",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (parseInt(data.status) === 0){
                console.log(data);
                if (data.data == undefined || data.data == null) {
                    $('#business_withdraw_sum_number').html(0);
                    $('#business_withdraw_today_sum_number').html(0);
                    $('#business_withdraw_order_sum_number').html(0);
                    $('#business_withdraw_today_order_sum_number').html(0);
                    $('#business_withdraw_disposedOf_number').html(0);
                    $('#business_withdraw_untreated_number').html(0);
                    $('#business_withdraw_disposedOf_order_number').html(0);
                    $('#business_withdraw_untreated_order_number').html(0);
                } else {

                    $('#business_withdraw_sum_number').html(data.data.business_withdraw_sum_number == undefined ? "0" : data.data.business_withdraw_sum_number);
                    $('#business_withdraw_today_sum_number').html(data.data.business_withdraw_today_sum_number  == undefined  ?"0" : data.data.business_withdraw_today_sum_number);
                    $('#business_withdraw_order_sum_number').html(data.data.business_withdraw_order_sum_number  == undefined  ?"0" : data.data.business_withdraw_order_sum_number);
                    $('#business_withdraw_today_order_sum_number').html(data.data.business_withdraw_today_order_sum_number  == undefined  ?"0" : data.data.business_withdraw_today_order_sum_number);
                    $('#business_withdraw_disposedOf_number').html(data.data.business_withdraw_disposedOf_number == undefined ?"0" : data.data.business_withdraw_disposedOf_number);
                    $('#business_withdraw_untreated_number').html(data.data.business_withdraw_untreated_number == undefined ?"0" : data.data.business_withdraw_untreated_number);
                    $('#business_withdraw_disposedOf_order_number').html(data.data.business_withdraw_disposedOf_order_number == undefined ?"0" : data.data.business_withdraw_disposedOf_order_number);
                    $('#business_withdraw_untreated_order_number').html(data.data.business_withdraw_untreated_order_number == undefined ?"0" : data.data.business_withdraw_untreated_order_number);
                }
            }
        }
    });
}



