$(document).ready(function (){
    var div = document.getElementById("item");
    var listChildren = div.children;
    var t1 = listChildren[0];
    var t2 = listChildren[1];

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
            url: './customers/api',
            success: function (response) {
                var arr = JSON.parse(response);

                for (let i = 0; i < parseInt(arr.products.length) && i < 10; i++){
                    $('.carousel').owlCarousel().trigger('add.owl.carousel', [jQuery("<div class='item'>" + t1.outerHTML + t2.outerHTML + "</div>")]).trigger('refresh.owl.carousel');
                    createItem(i);
                }

                function createItem(i){
                    let firstHref = $('.single-product').find('a')[0],
                        secondHref = $('.overlay-product').find('a')[0];

                    /*primo blocca 'a' - 'img'*/
                    $(firstHref).attr({
                        'href': "./customers/products?id=" + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                        'id' : "firstImg" + i
                    });

                    $($(firstHref).find('img')[0]).attr({
                        'src': "./covers/" + arr.products[i].paths[0].pathName,
                        "alt": arr.products[i].nome
                    });

                    /*secondo blocco 'a' - 'img'*/
                    $(secondHref).attr({
                        'href': "./customers/products?id="  + arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso,
                        'id' : "secondImg" + i
                    });

                    $($(secondHref).find('img')[0]).attr({
                        'src': "./covers/" + arr.products[i].paths[1].pathName,
                        "alt": arr.products[i].nome
                    })

                    /*terzo blocco*/
                    if (parseInt(arr.products[i].sconto) > 0) {
                        $('div.product_sale').children('span').text("- " + arr.products[i].sconto + "%");
                    }

                    /*quarto blocco*/
                    $($('.product_content').find('a')[0]).attr({
                        'href': "./customers/products/?id=" + arr.products[i].IDarticolo,
                        'id' : "nameProduct" + i
                    }).text(arr.products[i].nome);

                    $($('.product_content').find('span')[0]).attr({
                        'id' : "price" + i
                    }).text("€ " + arr.products[i].prezzo);
                }
            }
        })
   })
})

function deleteItem(){
    var div = document.getElementsByClassName('owl-stage');
    var listItem = $(div).find('.owl-item');

    if(parseInt(listItem.length) > 0){
        for (let i = 0; i < parseInt(listItem.length); i++){
            listItem[i].remove();
        }
    }
}
