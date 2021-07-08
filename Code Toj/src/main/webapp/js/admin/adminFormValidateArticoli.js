$(function() {
    $("form[name='input-form']").validate({
        // Specifichiamo le regole di valdazione
        rules: {
            idArticolo: {
                required: true,
                digits: true,
            },
            descrizione:{
                required: true,
            },
            prezzo: {
                required: true,
                number: true
            },
            sconto: {
                required: true,
                number: true,
                max: 1,
            },
            idCategoria: {
                required: true
            },
            nome: {
                required: true,
            },
            colore: {
                required: true,
            },
            quantita: {
                required: true,
                digits: true,
            },
            path:{
                required: true,
                extension: "jpg|jpeg|gif|png",
            }
        },
        // Specifichiamo i messaggi di errore
        messages: {
            idArticolo: {
                required: "Campo necessario.",
                digits: "Contiene solo numeri."
            },
            descrizione:{
                required: "Campo necessario.",
            },
            prezzo : {
                required: "Campo necessario.",
                number: "Contiene solo numeri."
            },
            sconto : {
                required: "Campo necessario.",
                number: "Contiene solo numeri.",
                max: "Valore troppo alto",
            },
            idCategoria: {
                required: "Campo necessario."
            },
            nome: {
                required: "Campo necessario."
            },
            colore: {
                required: "Campo necessario."
            },
            quantita : {
                required: "Campo necessario.",
                digits: "Contiene solo numeri."
            },
            path:{
                extension: "Formato non valido.",
                required: "Inserisci almeno due foto.",
            }
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });

   $("form[name='modify-form']").validate({
        rules: {
            descrizione:{
                required: true,
            },
            prezzo: {
                required: true,
                number: true
            },
            sconto: {
                required: true,
                number: true,
                max: 1,
            },
            idCategoria: {
                required: true,
            },
            nome: {
                required: true,
            },
            colore: {
                required: true,
            },
            quantita: {
                required: true,
                digits: true,
            },
            path:{
                extension: "jpg|jpeg|gif|png",
            }
        },
        messages: {
            descrizione:{
                required: "Campo necessario.",
            },
            prezzo : {
                required: "Campo necessario.",
                number: "Contiene solo numeri."
            },
            sconto : {
                required: "Campo necessario.",
                digits: "Contiene solo numeri.",
                max: "Valore troppo alto",
            },
            idCategoria: {
                required: "Campo necessario."
            },
            nome: {
                required: "Campo necessario."
            },
            colore: {
                required: "Campo necessario."
            },
            quantita : {
                required: "Campo necessario.",
                digits: "Contiene solo numeri."
            },
            path:{
                extension: "Formato non valido.",
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='delete-form']").validate({
        rules: {
            idDelete: {
                required: true,
                digits: true,
            },
        },
        messages: {
            idDelete: {
                required: "Inserisci ID.",
                digits: "ID non valido.",
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    setTimeout(function () {
        $("#errorID").fadeOut("slow");
    }, 2000);

});