var showTable = function(buttonName, formName, url){
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
            elem: '#customertable'
            ,cellMinWidth: 80
            ,data:result
            ,page:true
            ,cols: [[
              {field:'id',width:80, title: 'ID', sort: true}
              ,{field:'sales_no', width:80, title: '销售工号'}
              ,{field:'customer_name', width:120, title: '用户名'}
              ,{field:'mobile', width:'20%', title: '手机号'}
              ,{field:'id_no',width:'20%', title: '身份证'}
              ,{field:'product_name', width:120, title: '产品名'}
              ,{field:'state_type', title: '状态'}
            ]]
            });
            });

        },
        error : function() {
        alert("异常！");
        }
         });
    })
}