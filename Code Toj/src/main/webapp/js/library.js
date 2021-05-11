//Popup
function myFunction() {
    const popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}

function nascondi() {
    document.getElementById("myPopup").style.display="none";
}
setTimeout("nascondi()", 4000);