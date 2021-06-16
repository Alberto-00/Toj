$(document).ready(function (){

    $("a.ajax").click(function () {
        var sex = $(this).attr("id");

            $.ajax({
                method: "GET",
                url: './customers/api',
                success: function (response){
                    var p = JSON.parse(response)
                    alert(p.nuoviArriviMen[0].nome)
                }
            })

   })
})
