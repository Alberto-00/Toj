const xhttp = new XMLHttpRequest();

$(document).ready(function (){
   $("a.ajax").click(function () {
       var sex = $(this).attr("id");
       $.ajax({
           method: "GET",
           url: './customers/api',
           success: function (response){
               var root = JSON.parse(response);
               document.getElementById("M").innerHTML = root;
           }
       })
   })
})
