function hideElement (str) {
    var t = document.getElementById(str);
    if(t.style.display=='block')
        t.style.display='none';
    else
        t.style.display = 'block';
}