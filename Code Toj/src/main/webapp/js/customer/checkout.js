$(document).ready(function (){
    const pass = $(".hide");
    $("#accountPass").click(function (){
        if(pass.css('display') === 'none'){
            pass.show(400)
        }
        else {
            pass.hide(400)
        }
    })

    const address = $(".hide2");
    $("#address").click(function (){
        if(address.css('display') === 'none'){
            address.show(400)
        }
        else {
            address.hide(400)
        }
    })

    const paycard = $(".hide3");
    $("#paycard").click(function (){
        if(paycard.css('display') === 'none'){
            paycard.show(400)
        }
        else {
            paycard.hide(400)
        }
    })
});