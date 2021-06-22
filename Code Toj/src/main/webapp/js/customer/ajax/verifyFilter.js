function ajaxCall(){
    $(document).ready(function (){
        $.ajax({
            method: 'GET',
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: '../ajax/api-verifyFilter',
            success: function (response) {
                var arr = JSON.parse(response);
                var $span = $("span.errMsg").text();
                console.log($span);
            }
        })
    })
}


