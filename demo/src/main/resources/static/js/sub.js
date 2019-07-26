var sub = function(buttonName, formName, url){
     $(buttonName).click(function(){
       $.ajax({
        type: "POST",
        url: url,
        data: $(formName).serialize(),
        dataType: "json",
        success: function(data){
           alert("code: " + data.code + "," + "message: " + data.message + "," + "result: " + data.result );
        },
        error : function() {
        alert("异常！");
        }
         });
    })
}
