$(document).ready(function (){
    $(".small-img").click(function (){
        $(".big-img").attr('src',$(this).attr('src'));
    })
})