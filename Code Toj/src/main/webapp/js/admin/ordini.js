$(document).ready(function () {
    const btns = document.getElementsByClassName("show");
    for (let i = 0; i < btns.length; i++) {
        $("#show" + i).click(function () {
            if ($("#hide" + i).css('display') === 'none') {
                $("#hide" + i).show(400);
                $("#show" + i).parents("tr").addClass("selected-row");
            } else {
                $("#hide" + i).hide(400)
                $("#show" + i).parents("tr").removeClass("selected-row");
            }
        })
    }
});