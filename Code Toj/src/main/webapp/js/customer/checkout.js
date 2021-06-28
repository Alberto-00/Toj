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
    });

    $("form input").css("color", "var(--black");

    $("form[name='checkout']").validate({
        rules: {
            nome: {
                alphabetsnspace: true,
                required: true,
            },
            cognome:{
                alphabetsnspace: true,
                required: true,
            },
            telefono: {
                phoneUS: true,
                required: true,
            },
            email: {
                required: true,
                email: true,
            },
            password: {
                minlength: 8,
            },
            indirizzo:{
                required: true,
            },
            citta:{
                required: true,
                alphabetsnspace: true,
            },
            stato:{
                required: true,
                alphabetsnspace: true,
            },
            cap:{
                digits: true,
                minlength: 5,
                maxlength: 5,
                required: true,
            },
            nomeCard:{
                alphabetsnspace: true,
                required: true,
            },
            cognomeCard:{
                alphabetsnspace: true,
                required: true,
            },
            numeroCarta:{
                required: true,
                paycard: true,
            },
            cvv:{
                required: true,
                minlength: 3,
                maxlength: 4,
            },
            dataCarta:{
                required: true,
                dataCard: /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/
            },
            card:{
                required: true,
            },
        },
        messages: {
            nome: {
                alphabetsnspace: "Inseririsci il tuo nome reale.",
                required: "Inserisci il tuo nome.",
            },
            cognome:{
                alphabetsnspace: "Inserisci il tuo cognome reale.",
                required: "Inserisci il tuo cognome.",
            },
            telefono: {
                phoneUS: "Inserire un numero telefonico valido",
                required: "Inserisci il tuo telefono.",
            },
            cap:{
                digits: "Inserisci un CAP valido.",
                minlength: "CAP non completo.",
                maxlength: "CAP non completo.",
                required: "Inserisci il CAP.",
            },
            password: {
                minlength: "La password deve avere almeno 8 caratteri.",
            },
            email: {
                required: "Inserire un'email valida.",
                email: "Inserisci un email valida."
            },
            indirizzo:{
                required: "Inserisci l'indirizzo.",
            },
            citta:{
                required: "Inserisci la città.",
                alphabetsnspace: "Inserisci una città valida."
            },
            stato:{
                required: "Inserisci lo Stato in cui vivi.",
                alphabetsnspace: "Inserisci uno Stato valido.",
            },
            nomeCard:{
                alphabetsnspace: "Inseririsci il tuo nome reale.",
                required: "Inserisci il tuo nome.",
            },
            cognomeCard:{
                alphabetsnspace: "Inseririsci il tuo cognome reale.",
                required: "Inserisci il tuo cognome.",
            },
            numeroCarta:{
                required: "Inserisci il numero della carta.",
                paycard: "Numero carta errato.",
            },
            cvv: {
                required: "Inserisci il CVV.",
                minlength: "CVV errato.",
                maxlength: "CVV errato.",
            },
            dataCarta:{
                required: "Inserisci la scadenza.",
                dataCard: "Data non corretta."
            },
            card: {
                required: "Inserisci i dati."
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});