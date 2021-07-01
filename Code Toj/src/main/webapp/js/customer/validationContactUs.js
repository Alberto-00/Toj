$(document).ready(function () {
    $("form[name='form-contactUs']").validate({
        rules: {
            name: {
                required: true,
                alphabetsnspace: true,
            },
            email:{
                required: true,
                email: true,
            },
            subject: {
                required: true,
            },
            message:{
                required: true,
            },
        },
        messages: {
            name: {
                required: "Inserisci il nome.",
                alphabetsnspace: "Inseririsci il tuo nome reale.",
            },
            email: {
                required: "Inserire un'email valida.",
                email: "Email errata.",
            },
            subject: {
                required: "Inserisci l'oggetto.",
            },
            message:{
                required: "Scrivi qui il tuo messaggio.",
            },
        },errorElement: 'span',
        submitHandler: function(form) {
            form.submit();
        }
    });
});