$(document).ready(function (){
    $(".small-img").click(function (){
        $(".big-img").attr('src',$(this).attr('src'));
    })

    setTimeout(function () {
        $("#errorID").fadeOut("slow");
    }, 2500);
})

function setQuantita(){
    const value = $("#size option:selected").attr("data");
    $("#input_quantita").attr("max", value);
}