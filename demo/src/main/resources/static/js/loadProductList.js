var loadProductList = function(){

    $.ajax({
        type: "GET",
        url: "/getProductList?a=s",
        dataType: "json",
        success: function(data){
            layui.use('form',function(){
                var form = layui.form;
                var result = data.result;
                var server = document.getElementById("product");
                for( var p in result){
                    var option = document.createElement("option");
                    option.setAttribute("value",result[p].product_type );
                    option.innerText = result[p].product_name + " : " + result[p].product_type;
                    server.appendChild(option);
                    form.render('select');
                }
            })
        }
    })
}