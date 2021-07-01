var header = document.getElementById("dashboard-Account");
var btns = header.getElementsByClassName("btn-Account");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function() {
        var current = document.getElementsByClassName("active-btn");
        current[0].className = current[0].className.replace(" active-btn", "");
        this.className += " active-btn";
    });
}

var elements = document.getElementsByClassName("account-content");
function hide (elements, t) {
    for (var index = 0; index < elements.length; index++) {
        if(elements[index]===t){
            elements[index].style.display='block';
        }
        else {
            elements[index].style.display = 'none';
        }
    }
}

function showhide(str) {
    var t = document.getElementById(str);
    hide(elements, t);
}

function hideElement (str) {
    var t = document.getElementById(str);
        if(t.style.display === 'block')
            t.style.display ='none';
        else
            t.style.display = 'block';
}

$(document).ready(function () {
    const btns = document.getElementsByClassName("show");
    for(let i = 0; i < btns.length; i++){
        $("#show" + i).click(function () {
            if ($("#hide" + i).css('display') === 'none') {
                $("#hide" + i).show(400)
            } else {
                $("#hide" + i).hide(400)
            }
        })
    }

    $("#details-account input").css("color", "var(--black)");
    $("#address-account input").css("color", "var(--black)");

    $("form[name='formData']").validate({
        rules: {
            nome: {
                alphabetsnspace: true,
            },
            cognome:{
                alphabetsnspace: true,
            },
            telefono: {
                phoneUS: true,
            },
            birthday:{
                minAge: 18,
            },
            email: {
                required: true,
                email: true,
            },
            password: {
                minlength: 8
            }
        },
        messages: {
            nome: {
                alphabetsnspace: "Inseririsci il tuo nome reale.",
            },
            cognome:{
                alphabetsnspace: "Inserisci il tuo cognome reale.",
            },
            telefono: {
                phoneUS: "Inserire un numero telefonico valido",
            },
            birthday:{
                minAge: "Devi essere maggiorenne",
            },
            password: {
                minlength: "La password deve avere almeno 8 caratteri."
            },
            email: {
                required: "Inserire un'email valida.",
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='formAddress']").validate({
        rules: {
            cap:{
                digits: true,
                minlength: 5,
                maxlength: 5,
            },
        },
        messages: {
            cap:{
                digits: "Inserisci un CAP valido",
                minlength: "CAP non completo",
                maxlength: "CAP non completo",
            },
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});