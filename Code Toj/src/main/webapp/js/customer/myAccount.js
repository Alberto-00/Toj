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
        if(t.style.display=='block')
            t.style.display='none';
        else
            t.style.display = 'block';
}