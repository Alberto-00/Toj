function callAjax(){
    var val = $('#selectColor option:selected').val();
    if( val === "default"){
        return;
    }

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

            $('form > h1').text(arr.articolo.nome);
            $('.current_price').text("€ " + arr.articolo.prezzo);
            $('.product_desc > p').text(arr.articolo.descrizione);

            for (let i = 0; i < parseInt(arr.articolo.taglie.length); i++){
                $('#option_taglia' + i).val(arr.articolo.taglie[i].id_nome).
                attr('onclick', 'setQuantita(' + arr.articolo.taglie[i].quantita +')').
                text(arr.articolo.taglie[i].id_nome)
            }
        }, error: function (){
            console.log(response)
        }
    })
}

function modifyURL(){
    window.history.pushState("", "", "?id=" + $('#selectColor option:selected').val() +
    "&sex=" + $('img.big-img').attr('data'));
}

function supports_history_api() {
    return !!(window.history && history.pushState);
}