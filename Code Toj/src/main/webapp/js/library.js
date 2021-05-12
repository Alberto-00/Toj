/**Popup function:
* questa funzione dà l'effetto di fadeOut al popup
* settando un timeout
**/
function popupFunction() {
    const popup = document.getElementById("myPopup");
    popup.classList.add("show");
    setTimeout(nascondi, 1800);
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
window.onscroll = function() {
    stickyEffect()
};
const navbar = document.getElementById("nav-bottom");
const sticky = navbar.offsetTop;

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
document.getElementById("overlay-image-woman").addEventListener("click", function (){
    window.location.href = "./Donna";
})
document.getElementById("overlay-image-man").addEventListener("click", function (){
    window.location.href = "./Uomo";
})