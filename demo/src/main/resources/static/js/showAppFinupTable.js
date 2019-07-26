var showAppFinupTable = function(buttonName, formName, url){
        $(buttonName).click(function(){
            $.ajax({
               type: "POST",
                url: url,
                data: $(formName).serialize(),
                dataType: "json",
                success: function(data){
                     var result = data.result;
                      layui.use('table', function(){
                        var table = layui.table;
                        table.render({
                        elem: '#appFinupTable'
                                ,cellMinWidth: 80
                                ,data:result
                                ,page:true
                                ,cols: [[
                                  {field:'id',width:'10%', title: '个贷系统ID', sort: true}
                                  ,{field:'app_lend_request_id', width:120, title: 'APP进件ID'}
                                  ,{field:'lend_customer_id', width:180, title: '个贷系统客户ID'}
                                  ,{field:'state_type', width:'30%', title: 'APP状态'}
                                  ,{field:'status', title: '个贷状态'}

                                ]]
                                });
                                });
                },
                error : function() {
                        alert("异常！");ID
                        }
                         });

    })
}