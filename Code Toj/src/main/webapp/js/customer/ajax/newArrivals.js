$(document).ready(function (){

    $("a.ajax").click(function () {
        var sex = $(this).attr("id");

        if(sex === 'M'){
            $('#M').css("color", "var(--hover)");
            $('#F').css("color", "var(--grey)");
        }
        else {
            $('#F').css("color", "var(--hover)");
            $('#M').css("color", "var(--grey)");
        }

        $.ajax({
            method: 'GET',
            data: {sex: sex},
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: './ajax/api',
            success: function (response) {
                var arr = JSON.parse(response);

                for (let i = 0; i < parseInt(arr.products.length) && i < 10; i++){
                    createItem(i);
                }

                function createItem(i){
                    /*primo blocca 'a' - 'img'*/
                    $('a#firstImg' + i).attr({
                        'href': "./customers/products?id=" + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                        'id' : "firstImg" + i
                    });

                    $('img.image-first' + i).attr({
                        'src': "./covers/" + arr.products[i].paths[0].pathName,
                        'class' : "image-first image-first" + i,
                        "alt": arr.products[i].nome
                    });


                    /*secondo blocco 'a' - 'img'*/
                    $('a#secondImg' + i).attr({
                        'href': "./customers/products?id="  + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                        'id' : "secondImg" + i
                    });

                    $('img.image-second' + i).attr({
                        'src': "./covers/" + arr.products[i].paths[1].pathName,
                        "alt": arr.products[i].nome,
                        "class" : "image-second" + i
                    })

                    /*terzo blocco*/
                    $('#cartHref' + i).attr({
                        'href': "./customers/cart?id="  + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                    })

                    if (parseInt(arr.products[i].sconto) > 0) {
                        $('div.product_sale').children('span').text("- " + arr.products[i].sconto + "%");
                    }

                    /*quarto blocco*/
                    $('#nameProduct' + i).attr({
                        'href': "./customers/products?id=" + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                        'id' : "nameProduct" + i
                    }).text(arr.products[i].nome);

                    $('price' + i).attr({
                        'id' : "price" + i
                    }).text("€ " + arr.products[i].prezzo);
                }
            }
        })
   })
})
