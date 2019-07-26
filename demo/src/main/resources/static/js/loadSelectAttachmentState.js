var loadSelectAttachmentState = function(){
     $.ajax({
        type: "GET",
        url: "/getAttachmentStatus",
        dataType: "json",
        success: function(data){
            layui.use('form', function(){
                var form = layui.form;
                var result = data.result;
                var server = document.getElementById("status");
                for(var p in result){
                    var option = document.createElement("option");
                    option.setAttribute("value",result[p]);
                    option.innerText = p;
                    server.appendChild(option);
                    form.render('select');
                }
            })
        }
    })
}