$(document).ready(function () {
    const btns = document.getElementsByClassName("show");
    for (let i = 0; i < btns.length; i++) {
        $("#show" + i).click(function () {
            if ($("#hide" + i).css('display') === 'none') {
                $("#hide" + i).show(400)
                console.log("SI")
            } else {
                $("#hide" + i).hide(400)
                console.log("NO")
            }
        })
    }
});