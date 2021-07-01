//Aspettiamo che il file sia pronto
$(function() {
    // Inizializziamo la validazione sul form con name = "admin-login"
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
                extensions: "png|jpeg|gif",
                accept: "image/*",
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
                required: "Inserisci almeno due foto.",
                extensions: "Formato non valido",
                accept: "Formato non valido.",
            }
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });


    $("form[name='modify-form']").validate({
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
                extensions: "png|jpeg|gif",
                accept: "image/*",
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
                extensions: "Formato non valido",
                accept: "Formato non valido.",
            }
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='delete-form']").validate({
        rules: {
            id: {
                digits: true
            },
        },
        messages: {
            id: {
                digits: "ID non valido",
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});