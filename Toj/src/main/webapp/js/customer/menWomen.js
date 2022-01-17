function hideElement (str) {
    var t = document.getElementById(str);
    if(t.style.display ==='block')
        t.style.display='none';
    else
        t.style.display = 'block';
}

var slider = document.getElementById("myRange");
var output = document.getElementById("quanto");
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
}