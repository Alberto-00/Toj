$(document).ready(function (){
    $(".small-img").click(function (){
        $(".big-img").attr('src',$(this).attr('src'));
    })
})

function setQuantita(){
    var value = $("#size option:selected").attr("data");
    $("#input_quantita").attr("max", value);
}