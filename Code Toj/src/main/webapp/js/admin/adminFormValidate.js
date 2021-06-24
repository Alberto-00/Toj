//Aspettiamo che il file sia pronto
$(function() {
    // Inizializziamo la validazione sul form con name = "admin-login"
    $("form[name='admin-login']").validate({
        // Specifichiamo le regole di valdazione
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        // Specifichiamo i messaggi di errore
        messages: {
            password: {
                required: "Inserire la password",
                minlength: "La password deve essere almeno 8 caratteri"
            },
            email: "Inserire un'email valida"
        },
        //Quando valido, ci assicuriamo che il form venga inviato
        submitHandler: function(form) {
            form.submit();
        }
    });
});