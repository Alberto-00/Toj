function callAjax(){
    var val = $('#selectColor option:selected').val();
    if( val === "")
        return;

    $.ajax({
        method: 'GET',
        accepts: {
            json: 'application/json',
        },
        dataType: 'text',
        contentType: "application/json; charset=utf-8",
        url: '../ajax/api-product?id=' + val,
        success: function (response){
            var arr = JSON.parse(response);
            if (supports_history_api()){
                modifyURL();
            }

            $(".big-img").attr({
                'src' : "../covers/" + arr.articolo.paths[0].pathName,
                'alt' : arr.articolo.nome
            })

            for (let j in arr.articolo.paths){
                $("#small-img" + j).attr({
                    'src' : "../covers/" + arr.articolo.paths[j].pathName,
                    'alt' : arr.articolo.nome
                })
            }

            $('#productForm > h1').text(arr.articolo.nome);

            const $sale = $('.sale');
            if (parseFloat(arr.articolo.sconto) > 0.0){
                if ($sale.length === 0){
                    const text = '<span class="current_price sale">€ ' +
                        arr.articolo.prezzo.toFixed(2).replace('.', ',') + '</span>';
                     $(text).insertBefore('#priceReal');
                } else
                    $sale.text("€ " + arr.articolo.prezzo.toFixed(2).replace('.', ','));
            }
            else if ($sale.length > 0)
                $sale.remove();

            $('#priceReal').text("€ " + sale(arr.articolo.prezzo, arr.articolo.sconto).toFixed(2).replace('.', ','));
            $('.product_desc > p').text(arr.articolo.descrizione);
        }
    })
}

function sale(price, sale){
    return parseFloat(price)  - (parseFloat(price) * parseFloat(sale));
}

function modifyURL(){
    window.history.pushState("", "", "?id=" + $('#selectColor option:selected').val() +
    "&sex=" + $('img.big-img').attr('data'));
}

function supports_history_api() {
    return !!(window.history && history.pushState);
}

$(document).ready(function (){
    $("form[name='formProduct']").validate({
        rules: {
            quantity: {
                required: true,
            },
            size: {
                required: true,
            },
            id: {
                required: true,
            }
        },
        messages: {
            quantity: {
                required: "Quantità non disponibile",
                max: "Quantità non disponibile",
                min: "Quantità non disponibile",
            },
            size: {
                required: "Inserisci la taglia",
            },
            id:{
                required: "Inserisci il colore",
            }
        }, errorElement: 'small',
        submitHandler: function(form) {
            form.submit();
        }
    });
})