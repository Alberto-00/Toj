$(document).ready(function (){
    jQuery(".carousel").owlCarousel({
        autoplay: false,
        rewind: false, /* use rewind if you don't want loop */
        margin: 20,
        dots: false,
        responsiveClass: true,
        autoHeight: true,
        smartSpeed: 800,
        nav: true,
        responsive: {
            0: {
                items: 1
            },

            600: {
                items: 3
            },

            1024: {
                items: 4
            },

            1366: {
                items: 4
            }
        }
    });
})
