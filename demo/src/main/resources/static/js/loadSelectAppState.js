var loadSelectAppState = function(){
    $.ajax({
        type: "GET",
        url: "/getAppStateItem",
        dataType: "json",
        success: function(data){
            layui.use('form', function(){
                var form = layui.form;
                var result = data.result;
                var server = document.getElementById("appState");
                for(var p in result){
                    var option = document.createElement("option");
                    option.setAttribute("value",result[p]); // 给option的value添加值
                    option.innerText=p;     // 打印option对应的纯文本
                    server.appendChild(option);           //给select添加option子标签
                    form.render('select');       // 刷性select，显示出数据
                }
            })

        }

    })
}