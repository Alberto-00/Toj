$(document).ready(function(){

    $('#coupon').click(function(){
        var $url = $("#coupon").attr("data");
        var $coupon = $("#couponInput").val();
        $.ajax({
            method: 'GET',
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: $url + '/ajax/api-coupon?coupon=' + $coupon,
            success: function (response) {
                var sconto = JSON.parse(response);
                if(sconto.sconto === undefined){
                   $("small.errMsg").text("Coupon non valido");
                }
            }
        })
    })
    if($('#couponInput').val() === undefined)
        $("small.errMsg").text('');
});