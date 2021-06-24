$(document).ready(function(){

    $('#coupon').click(function(){
        $("button + small").removeClass();
        const $coupon = $("#couponInput").val();

        $("#couponInput").keyup(function (){
            if ($("#couponInput").val() === '') {
                $("button + small").text('');
            }
        });

        const $url = $("#coupon").attr("data");
        $.ajax({
            method: 'GET',
            data: {coupon: $coupon},
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: $url + '/ajax/api-coupon',
            success: function (response) {
                var sconto = JSON.parse(response);
                if(sconto.sconto === undefined){
                   $("button + small").addClass("errMsg").text("Coupon non valido.");
                } else if (sconto.sconto === "Carrello vuoto."){
                    $("button + small").addClass("errMsg").text(sconto.sconto);
                } else
                    $("button + small").addClass("successMsg").text("Coupon applicato!");
            }
        })
    });

    $("#checkout-btn").click(function (){
        const $url = $("#coupon").attr("data");
        $.ajax({
            method: 'GET',
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: $url + '/ajax/api-checkout',
            success: function (response) {
                var pass = JSON.parse(response);
                if (pass !== undefined){
                    $("a + small").addClass("errMsg").text(pass.msg);
                }
            }
        })
    });
});