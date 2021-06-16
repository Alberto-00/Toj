/** Popup form Login and Register: **/
const signUp = document.getElementById('sign-up'),
    signIn = document.getElementById('sign-in'),
    loginIn = document.getElementById('login-in'),
    loginUp = document.getElementById('login-up')


signUp.addEventListener('click', ()=>{
    loginIn.classList.remove('block')
    loginUp.classList.remove('none')

    loginIn.classList.toggle('none')
    loginUp.classList.toggle('block')
});

signIn.addEventListener('click', ()=>{
    loginIn.classList.remove('none')
    loginUp.classList.remove('block')

    loginIn.classList.toggle('block')
    loginUp.classList.toggle('none')
});

function openForm() {
    document.getElementById("myForm").style.display = "block";
    document.body.style.overflow="hidden";
    document.getElementById("popup-background-color").style.visibility ="visible";}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
    document.body.style.overflow="auto";
    document.getElementById("popup-background-color").style.visibility ="hidden";
}


/**Popup function:
* questa funzione dà l'effetto di fadeOut al popup
* settando un timeout
**/
function popupFunction() {
    const popup = document.getElementById("myPopup");
    popup.classList.add("show");
    setTimeout(nascondi, 1700);
}
function nascondi() {
    const popup = document.getElementById("myPopup");
    popup.classList.remove("show");
}


/**
 * Scroll Up
 */
/*Utilizziamo addEventListener per eseguire due onscroll senza che questi si sovrascrivessero*/
window.addEventListener("scroll", scrollFunction);

var scrollUp = document.getElementById("scrollUp");

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 250 || document.documentElement.scrollTop > 250) {
        scrollUp.style.display = "block";
    } else {
        scrollUp.style.display = "none";
    }
}


/**
 * Sticky effect:
 * questo script dà l'effetto sticky alla nav-bottom
 * del sito.
 */
window.addEventListener("scroll", stickyEffect);

window.onscroll = function() {stickyEffect()};

const navbar = document.getElementById("nav-bottom");
const sticky = navbar.offsetTop;

function stickyEffect() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky");
    } else {
        navbar.classList.remove("sticky");
    }
}


/**
 * Script sidebar
 */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("sidenav-background-color").style.visibility ="visible";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("sidenav-background-color").style.visibility ="hidden";
}

/*permette di chiudere la sidenav in un punto qualsiasi della pagina*/
$(document).ready(function (){
    $('#sidenav-background-color').on('click', function(){
        if( parseInt( $('#mySidenav').css('width') ) > 0 ){
            closeNav();
        }
    });
})


/**
 * Dropdown button sidebar
 */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function() {
        this.classList.toggle("active-sidenav");
        var dropdownContent = this.nextElementSibling;
        if (dropdownContent.style.display === "block") {
            dropdownContent.style.display = "none";
        } else {
            dropdownContent.style.display = "block";
        }
    });
}

/**
 * Validation Popup Newsletter
 */
$(document).ready(function(){
    $("#button-newsletter").click(function(){
        if(validateEmail($("#newsletter").val()) === false){
            $("#myPopup").text("Email errata. Riprova.");
        }
        else {
            $("#myPopup").text("Email inviata. Grazie!");
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