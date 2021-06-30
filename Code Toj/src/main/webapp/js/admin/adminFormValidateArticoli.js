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
            prezzo: {
                required: true,
                number: true
            },
            sconto: {
                required: true,
                number: true
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
                extensions: "jpg|png|gif",
            }
        },
        // Specifichiamo i messaggi di errore
        messages: {
            idArticolo: {
                required: "Campo necessario.",
                digits: "Contiene solo numeri."
            },
            prezzo : {
                required: "Campo necessario.",
                number: "Contiene solo numeri."
            },
            sconto : {
                required: "Campo necessario.",
                digits: "Contiene solo numeri."
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
            }
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });
});

//Aspettiamo che il file sia pronto
$(function() {
    // Inizializziamo la validazione sul form con name = "admin-login"
    $("form[name='modify-form']").validate({
        // Specifichiamo le regole di valdazione
        rules: {
            idArticolo: {
                required: true,
                digits: true
            },
            prezzo: {
                required: true,
                digits: true
            },
            sconto: {
                required: true,
                digits: true
            },
            idCategoria: {
                required: true
            },
            nome: {
                required: true
            },
            colore: {
                required: true
            },
            quantita: {
                required: true,
                digits: true
            }
        },
        // Specifichiamo i messaggi di errore
        messages: {
            input: "campo necessario",
            idArticolo: {
                required: "Campo necessario",
                digits: "Contiene solo numeri"
            },
            prezzo : {
                required: "Campo necessario",
                digits: "Contiene solo numeri"
            },
            sconto : {
                required: "Campo necessario",
                digits: "Contiene solo numeri"
            },
            idCategoria: "Campo necessario",
            nome: "Campo necessario",
            colore: "Campo necessario",
            quantita : {
                required: "Campo necessario",
                digits: "Contiene solo numeri"
            }
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });
});