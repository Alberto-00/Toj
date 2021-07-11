$(function() {
    $("form[name='formPopup']").validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8,
                maxlength: 30,
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri.",
                maxlength: "La password deve essere al più 30 caratteri."
            },
            email: {
                required: "Inserire l'e-mail.",
                email: "Inserire un'email valida.",
            },
        }, errorElement: 'span',
        errorPlacement: function(error, element) {
            error.insertAfter(element.closest("div"))
        },
        submitHandler: function(form) {
            form.submit();
        },
    });

    $("form[name='formPopup2']").validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 8,
                maxlength: 30,
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri.",
                maxlength: "La password deve essere al più 30 caratteri."
            },
            email: {
                email: "Inserire un'e-mail valida.",
                required: "Inserisci l'e-mail.",
            }
        }, errorElement: 'span',
        errorPlacement: function(error, element) {
            error.insertAfter(element.closest("div"))
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='formPopup3']").validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 8,
                maxlength: 30,
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri.",
                maxlength: "La password deve essere al più 30 caratteri.",
            },
            email: {
                required: "Inserire l'e-mail.",
                email: "Inserire un'e-mail valida.",
            }
        }, errorElement: 'span',
        errorPlacement: function(error, element) {
            error.insertAfter(element)
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='formPopup4']").validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 8,
                maxlength: 30,
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri.",
                maxlength: "La password deve essere al più 30 caratteri.",
            },
            email: {
                required: "Inserire l'e-mail.",
                email: "Inserire un'e-mail valida.",
            }
        }, errorElement: 'span',
        errorPlacement: function(error, element) {
            error.insertAfter(element)
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});