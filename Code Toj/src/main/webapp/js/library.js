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
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
    document.body.style.overflow="auto";
}

/** Script per la search bar: **/
var x, i, j, l, ll, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("custom-select");
l = x.length;
for (i = 0; i < l; i++) {
    selElmnt = x[i].getElementsByTagName("select")[0];
    ll = selElmnt.length;
    /*for each element, create a new DIV that will act as the selected item:*/
    a = document.createElement("DIV");
    a.setAttribute("class", "select-selected");
    a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
    x[i].appendChild(a);
    /*for each element, create a new DIV that will contain the option list:*/
    b = document.createElement("DIV");
    b.setAttribute("class", "select-items select-hide");
    for (j = 1; j < ll; j++) {
        /*for each option in the original select element,
        create a new DIV that will act as an option item:*/
        c = document.createElement("DIV");
        c.innerHTML = selElmnt.options[j].innerHTML;
        c.addEventListener("click", function(e) {
            /*when an item is clicked, update the original select box,
            and the selected item:*/
            var y, i, k, s, h, sl, yl;
            s = this.parentNode.parentNode.getElementsByTagName("select")[0];
            sl = s.length;
            h = this.parentNode.previousSibling;
            for (i = 0; i < sl; i++) {
                if (s.options[i].innerHTML === this.innerHTML) {
                    s.selectedIndex = i;
                    h.innerHTML = this.innerHTML;
                    y = this.parentNode.getElementsByClassName("same-as-selected");
                    yl = y.length;
                    for (k = 0; k < yl; k++) {
                        y[k].removeAttribute("class");
                    }
                    this.setAttribute("class", "same-as-selected");
                    break;
                }
            }
            h.click();
        });
        b.appendChild(c);
    }
    x[i].appendChild(b);
    a.addEventListener("click", function(e) {
        /*when the select box is clicked, close any other select boxes,
        and open/close the current select box:*/
        e.stopPropagation();
        closeAllSelect(this);
        this.nextSibling.classList.toggle("select-hide");
        this.classList.toggle("select-arrow-active");
    });
}

function closeAllSelect(elmnt) {
    /*a function that will close all select boxes in the document,
    except the current select box:*/
    var x, y, i, xl, yl, arrNo = [];
    x = document.getElementsByClassName("select-items");
    y = document.getElementsByClassName("select-selected");
    xl = x.length;
    yl = y.length;
    for (i = 0; i < yl; i++) {
        if (elmnt === y[i]) {
            arrNo.push(i)
        } else {
            y[i].classList.remove("select-arrow-active");
        }
    }
    for (i = 0; i < xl; i++) {
        if (arrNo.indexOf(i)) {
            x[i].classList.add("select-hide");
        }
    }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);


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
 * Sticky effect:
 * questo script dà l'effetto sticky alla nav-bottom
 * del sito.
 */
window.onscroll = function() {stickyEffect()};
var navbar = document.getElementById("nav-bottom");
var sticky = navbar.offsetTop;

function stickyEffect() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}


/**
 * Questo script permette di cliccare sull'href delle immagini ("Uomo" e "Donna")
 * e di reindirizzarci ad un URL.
 */

$('#prev').on('click', function() {
    $('#menu .product-container').animate({
        scrollLeft: '-=380' /*300*/
    }, 500, 'swing');
});

$('#next').on('click', function() {
    $('#menu .product-container').animate({
        scrollLeft: '+=380' /*290*/
    }, 500, 'swing');
});


/** Overlay foto middle
 */
document.getElementById("overlay-image-woman").addEventListener("click", function (){
    window.location.href = "./Donna";
})
document.getElementById("overlay-image-man").addEventListener("click", function (){
    window.location.href = "./Uomo";
})


/**
 * Script sidebar
 */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.body.style.backgroundColor = "white";
}

const $menu = $('.sidenav');
$(document).mouseup(e => {
    if (!$menu.is(e.target)
        && $menu.has(e.target).length === 0)
    {
        $menu.removeClass('mySidenav');
    }
});

$('.toggle').on('click', () => {
    $menu.toggleClass('mySidenav');
});


/**
 * Dropdown button sidebar
 */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var dropdownContent = this.nextElementSibling;
        if (dropdownContent.style.display === "block") {
            dropdownContent.style.display = "none";
        } else {
            dropdownContent.style.display = "block";
        }
    });
}
