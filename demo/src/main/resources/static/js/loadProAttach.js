var loadProAttach = function(){
    layui.use('form',function(){
        var form = layui.form;
        form.on('select(product_name)',function(data){
            $("#type").empty();
            form.render('select');
            var dataTest = {"product_type":data.value}
            $.ajax({
                type: "POST",
                url: "/getAttachByPro",
                data: dataTest,
                dataType: "json",
                success: function(data){
                    var form = layui.form;
                    var result = data.result;
                    var server = document.getElementById("type");
                    for(var p in result){
                        var option = document.createElement("option");
                        option.setAttribute("value",result[p].material_type);
                        option.innerText = result[p].material_name;
                        server.appendChild(option);
                        form.render('select');
                    }
                }
            })
        })
    })
}