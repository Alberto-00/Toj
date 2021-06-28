$(function() {
    $("form[name='formPopup']").validate({
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
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri."
            },
            email: "Inserire un'email valida."
        }, errorElement: 'span',
        errorPlacement: function(error, element) {
            error.insertAfter(element.closest("div"))
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='formPopup2']").validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri."
            },
            email: {
                required: "Inserire un'email valida.",
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
                minlength: 8
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri."
            },
            email: {
                required: "Inserire un'email valida.",
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
                minlength: 8
            }
        },
        messages: {
            password: {
                required: "Inserire la password.",
                minlength: "La password deve avere almeno 8 caratteri."
            },
            email: {
                required: "Inserire un'email valida.",
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