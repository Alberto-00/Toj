$(document).ready(function (){

    $("a.ajax").click(function () {
        var sex = $(this).attr("id");

        $.ajax({
            method: 'GET',
            data: {sex: sex},
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: './customers/api',
            success: function (response){
                const arr = JSON.parse(response);
                const firstHref = $('#firstImg'), secondHref = $('#secondImg');

                for (let i in arr.products){

                    /*primo blocca 'a' - 'img'*/
                    firstHref.attr('href', firstHref.attr("href") + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso);
                    $('img.image-first').attr({'src': $('img.image-first').attr('src') + arr.products[i].paths[0].pathName, "alt" : arr.products[0].nome});

                    /*secondo blocco 'a' - 'img'*/
                    secondHref.attr('href', firstHref.attr("href") + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso);
                    secondHref.children('img').attr({'src': $('img.image-second').attr('src') + arr.products[i].paths[1].pathName, "alt" : arr.products[0].nome})

                    /*terzo blocco*/
                    if(parseInt(arr.products[i].sconto) > 0){
                        $('div.product_sale').children('span').text("- " + arr.products[i].sconto + "%");
                    }

                    /*quarto blocco*/
                    $('#nameProduct').attr('href', $('#nameProduct').attr('href') + arr.products[i].IDarticolo).text(arr.products[i].nome);
                    $('span.current_price').text("€ " + arr.products[0].prezzo);
                }
            }
        })
   })
})
