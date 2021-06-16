$(document).ready(function (){
    $(".small-img").click(function (){
        $(".big-img").attr('src',$(this).attr('src'));
    })
})

function setQuantita(quantita){
    document.getElementById("input_quantita").setAttribute("max" , quantita)
}
