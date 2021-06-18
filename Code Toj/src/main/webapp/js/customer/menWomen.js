function hideElement (str) {
    var t = document.getElementById(str);
    if(t.style.display ==='block')
        t.style.display='none';
    else
        t.style.display = 'block';
}

function closeButton(){
    const elem = document.getElementById("filters");

    if(elem.style.display ==='block') {
        elem.style.display = 'none';
        document.getElementById("close").innerHTML = "APRI";
    }
    else {
        elem.style.display = 'block';
        document.getElementById("close").innerHTML = "CHIUDI";
    }

}

var slider = document.getElementById("myRange");
var output = document.getElementById("quanto");
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
}