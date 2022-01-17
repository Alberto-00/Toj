$(document).ready(function(){

    $('#txt-search').keyup(function(){
        var url = $(".url").attr("data");
        var searchField = $(this).val();
        if(searchField === '')  {
            $('#filter-records').html('');
            return;
        }
        var regex = new RegExp(searchField, "i"); /*i -> not case sensitive*/
        var output = '<div class="container-search">';
        output += '<div class="row">';
        var count = 1;
        $.ajax({
            method: 'GET',
            accepts: {
                json: 'application/json',
            },
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            url: url + '/ajax/api-searchBar',
            success: function (response) {
                var arr = JSON.parse(response);
                $.each(arr.listName, function (key, value){
                    if (value.name.search(regex) != -1 && value.name != null) {
                        output += '<div class="columnAjax">';
                        output += '<p>' + value.name + '</p>'
                        output += '</div>';

                        if(count%1 == 0){
                            output += '</div><div class="row">'
                        }
                        count++;
                    }
                });
                output += '</div>';
                output += '</div>';
                $('#filter-records').html(output);
            }
        })
    })
    $('#filter-records').on('click', 'p', function() {
        var click_text = $(this).text().split('|');
        $('#txt-search').val($.trim(click_text[0]));
        $("#filter-records").html('');
    });
});