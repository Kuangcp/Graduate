var token = localStorage.getItem('token')

if(token == null){
    $.get();
}

function mythGet(type, url ) {
    var settings = {
        type: "GET",
        url:URL+"?"+REQUEST,
        dataType:"xml",
        error: function(XHR,textStatus,errorThrown) {
            alert ("XHR="+XHR+"\ntextStatus="+textStatus+"\nerrorThrown=" + errorThrown);
        },
        success: function(data,textStatus) {
            $("body").append(data);
        },
        headers: {
            "Access-Control-Allow-Origin":"http://example.edu",
            "Access-Control-Allow-Headers":"X-Requested-With"
        }
    };

    $.ajax(settings);
    
}

