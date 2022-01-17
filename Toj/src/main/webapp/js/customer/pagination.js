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
            319:{
              items: 4
            },
            427:{
                item: 3
            },
            700:{
                items: 4
            },
            800: {
                items: 9
            },
            1024: {
                items: 16
            },
        }
    });
})