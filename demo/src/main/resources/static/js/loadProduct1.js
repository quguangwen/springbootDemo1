var loadProduct1 = function(){

    $.ajax({
        type: "GET",
        url: "/getProductList",
        dataType: "json",
        success: function(data){
            layui.use('form',function(){
                var form = layui.form;
                var result = data.result;
                var server = document.getElementById("pro");
                for( var p in result){
                    var option = document.createElement("option");
                    option.setAttribute("value",result[p].product_name );
                    option.innerText = result[p].product_name + " : " + result[p].product_type;
                    server.appendChild(option);
                    form.render('select');
                }
            })
        }
    })
}