$(document).ready(function(){
    $("button").click(function(){
        if(validateEmail($("#idEmail").val()) === false){
            alert("Formato email non valido");
        }
    });
});

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}


/**
 * Validation Form
 */
function dictionary(){
    return{
        'patternMismatch' : 'Formato non valido',
        'rangeOverflow' : "Il valore è più grande di %s",
        'rangeUnderflow' : 'Il valore è più piccolo di %s',
        'tooLong' : 'Non deve superare lunghezza %s caratteri',
        'tooShort' : 'Non deve essere più piccola di %s caratteri',
        'stepMismatch' : 'Deve avere uno step di %s',
        'valueMissing' : 'Campo obbligatorio'
    };
}

function validateForm(form){

    const dict = dictionary();

    function _reportError(event){
        const _el = event.target;
        const errors = [];

        if (_el.validity.tooShort){
            errors.push(dict['tooShort'].replace("%s", _el.getAttribute('minlenght')));
        }

        if (_el.validity.tooLong){
            errors.push(dict['tooLong'].replace("%s", _el.getAttribute('maxlenght')));
        }

        if (_el.validity.patternMismatch){
            errors.push(dict['patternMismatch']);
        }

        if (_el.validity.valueMissing){
            errors.push(dict['valueMissing']);
        }

        if (_el.validity.rangeUnderflow){
            errors.push(dict['rangeUnderflow'].replace("%s", _el.getAttribute('min')));
        }

        if (_el.validity.rangeOverflow){
            errors.push(dict['rangeOverflow'].replace("%s", _el.getAttribute('max')));
        }

        if (_el.validity.stepMismatch){
            errors.push(dict['stepMismatch'].replace("%s", _el.getAttribute('step')));
        }
        _el.parentElement.nextElementSibling.textContent = errors.join('|');
    }

    function _reset(event){
        const _el = event.target;
        _el.parentElement.nextElementSibling.textContent = '';
    }

    form.setAttribute("novalidate", "true");
    const inputs = form.elements;
    for(let i = 0; i < inputs.length; i++){
        let isValidInput = inputs[i].nodeName.match("INPUT|TEXTAREA|SELECT");
        if(inputs[i].willValidate !== 'undefined' && isValidInput){
            inputs[i].addEventListener('invalid', _reportError());
            inputs[i].addEventListener('focus', _reset);
        }
    }

    form.addEventListener('submit', function (event){
        if(form.checkValidity()){
            form.submit();
        } else {
            event.preventDefault();
        }
    })
}

/*Validazione*/
/*const form = document.getElementById("customerForm");
if (form){
    validateForm(form);
}*/ 