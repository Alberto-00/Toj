$(document).ready(function() {

    $('#coupon').click(function () {
        $("button + small").removeClass();
        const $coupon = $("#couponInput").val();

        $("#couponInput").keyup(function () {
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
                if (sconto.sconto === undefined) {
                    $("button + small").addClass("errMsg").text("Coupon non valido.");
                } else if (sconto.sconto === "Carrello vuoto.") {
                    $("button + small").addClass("errMsg").text(sconto.sconto);
                } else
                    $("button + small").addClass("successMsg").text("Coupon applicato!");
            }
        })
    });

    $("#checkout-btn").click(function () {
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
                if (pass.msg !== "") {
                    $("a + small").addClass("errMsg").text(pass.msg);
                } else {
                    window.location.href = $url + '/customers/checkout';
                }
            }
        })
    });

    $(".update").change(function (){
        const $url = $("#coupon").attr("data");
        var $quantity = $(this).val();
        var $size = $(this).attr("data");
        var $id = $(this).attr("data1");

        if ($quantity === ""){
            $(".update + small").removeClass("successMsg");
            $(".update + small").addClass("errMsg").text("Aggiornamento fallito.").fadeIn(500).delay(1000).fadeOut(500);
            return;
        } else if ($quantity >= $(this).attr("max")){
            $(".update + small").removeClass("successMsg");
            $(".update + small").addClass("errMsg").text("Valore troppo alto.").fadeIn(500).delay(1000).fadeOut(500);
            return;
        }

        $.ajax({
            method: 'GET',
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: $url + '/ajax/api-updateCart?id=' + $id + "&size=" + $size + "&quantity=" + $quantity,
            success: function (response) {
                var pass = JSON.parse(response);
                if (pass.msg === "true")
                    $(".update + small").addClass("successMsg").text("Aggiornato!").fadeIn(500).delay(1000).fadeOut(500);
                else
                    $(".update + small").addClass("errMsg").text("Aggiornameto fallito.").fadeIn(500).delay(1000).fadeOut(500);
            }
        });
    });
});


