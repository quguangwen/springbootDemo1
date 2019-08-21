var showAppFinupTable = function(buttonName, formName, url){
        $(buttonName).click(function(){
            $.ajax({
               type: "POST",
                url: url,
                data: $(formName).serialize(),
                dataType: "json",
                success: function(data){
                     var result = data.result;
                     console.log(result)
                      layui.use('table', function(){
                        var table = layui.table;
                        table.render({
                        elem: '#appFinupTable'
                                ,cellMinWidth: 80
                                ,data:result
                                ,page:true
                                ,cols: [[
                                  {field:'id',width:'10%', title: '系统进件ID', sort: true}
                                  ,{field:'appLendRequestId', width:120, title: 'APP进件ID'}
                                  ,{field:'logInId', width:120, title: 'APP_UUID'}
                                  ,{field:'appCustomerId', width:120, title: 'APP客户ID'}
                                  ,{field:'lendCustomerId', width:140, title: '系统客户ID'}
                                  ,{field:'stateType', width:'30%', title: 'APP状态'}
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