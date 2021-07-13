const header = document.getElementById("dashboard-Account");
const btns = header.getElementsByClassName("btn-Account");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function() {
        var current = document.getElementsByClassName("active-btn");
        current[0].className = current[0].className.replace(" active-btn", "");
        this.className += " active-btn";
    });
}

const elements = document.getElementsByClassName("account-content");
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
    const t = document.getElementById(str);
    hide(elements, t);
}

function hideElement (str) {
    const t = document.getElementById(str);
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
                $("#hide" + i).show(400);
                $("#show" + i).parents("tr").addClass("selected-orange");
            } else {
                $("#hide" + i).hide(400);
                $("#show" + i).parents("tr").removeClass("selected-orange");
            }
        })
    }

    $("#details-account input").css("color", "var(--black)");
    $("#address-account input").css("color", "var(--black)");

    $('form[name="formData"]').validate({
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
                minlength: 8,
                maxlength: 30,
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
                minlength: "La password deve avere almeno 8 caratteri.",
                maxlength: "La password deve essere al più 30 caratteri."
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
            citta:{
                alphabetsnspace: true,
            },
            stato:{
                alphabetsnspace: true,
            }
        },
        messages: {
            cap:{
                digits: "Inserisci un CAP valido",
                minlength: "CAP non completo",
                maxlength: "CAP non completo",
            },
            citta:{
                alphabetsnspace: "Inserisci una città valida."
            },
            stato:{
                alphabetsnspace: "Inserisci uno Stato valido."
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});