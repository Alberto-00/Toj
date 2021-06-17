$(document).ready(function (){

    $("option.ajax").click(function () {

        $.ajax({
            method: 'GET',
            data: {coloreID : $('#slectColor option:selected').val()},
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: '../ajax/api-product',
            success: function (response){
                var colore = JSON.parse(response);

                for (let i in colore.articolo){
                    $(".big-img").attr({
                        'src' : "../covers/" + colore.articolo.paths[0].pathName,
                        'alt' : colore.articolo.nome
                    })

                    for (let j in colore.articolo.paths){
                        $("#small-img" + j).attr({
                            'src' : "../covers/" + colore.articolo.paths[j].pathName,
                            'alt' : colore.articolo.nome
                        })
                    }
                }
            }
        })
    })
})
