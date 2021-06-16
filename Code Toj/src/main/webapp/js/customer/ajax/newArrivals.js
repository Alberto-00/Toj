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
                var arr = JSON.parse(response);
                for (let i in arr.products){
                    $('#firstImg').path();
                    attr('href', ).append(arr.products[i].IDarticolo + "&sex=" + arr.products[i].sesso);
                }
            }
        })
   })
})

function appendText(attr, appendPath){
    return $(this).attr(attr) + appendPath;
}
