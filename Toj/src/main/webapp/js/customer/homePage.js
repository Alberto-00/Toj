/**
 * Overlay foto middle
 */
const man = document.getElementById("overlay-image-woman")
if(man){
    man.addEventListener("click", function (){
        window.location.href = "./customers/shop?page=1&Sesso=F";
    })
}

const women = document.getElementById("overlay-image-man")
if(women){
    women.addEventListener("click", function (){
        window.location.href = "./customers/shop?page=1&Sesso=M";
    })
}