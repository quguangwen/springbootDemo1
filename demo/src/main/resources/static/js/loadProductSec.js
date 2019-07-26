var loadProductSec = function(){
    $.ajax({
        type: "GET",
        url: "/getProductSec",
        dataType: "json",
        success: function(data){
            layui.use('form',function(){
                var form = layui.form;
                var result = data.result;
                var server = document.getElementById("product_code");
                for( var p in result){
                    console.log(result[p].product_type );
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