var $results = document.querySelector('#addressIsHere');
var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');
TeleportAutocomplete.init('#citySearch').on('change', function(value) {
appendToResult('<pre id="selectedCity">' + value.title + '</pre>');
});

// Logic to be done by Ben
function coordToCity(lat, long) {
    console.info(lat + " " + long);
    return "Walnut, California, United States";
}

function reformatAddress(address) {
    return address.replace(/, /g,"-").replace(/ /g,"_");
}


function loadModal() {
    //$('#resultModalBody').empty();
    //$('#resultModalBody').append(content);
    $('#resultModal').modal({show:true});
}

function parseServerConnectError() {
    //return "<div class=\"has-error\"><p>Error connecting to server. <br />Please try again later. </p></div>";
    $('#serverError').show();
    $('#noGpsError').hide();
    $('#resultModalBody').hide();

}

function parseNoGpsError() {
    //return "<div class=\"has-error\"><p>Unable to access your current locaitons.<br/>Please use the search box.</p></div>";
    $('#noGpsError').show();
    $('#resultModalBody').hide();
    $('#serverError').hide();
}


function parseOutput(result) {
    $('#resultModalBody').show();
    $('#noGpsError').hide();
    $('#serverError').hide();
    //var content = "<h3>" + result.city + ", " + result.state + "</h3>";
    if (result.result) {
        $('#displayResult').show();
        $('#noResultError').hide();
        /*
        content += "<p><em>Safe Index: </em><strong>" + result.index + "<strong></p>"
                 + "<p>" + result.average + "</p>"
                 + "<p>" + result.years + "</p>";

                 + "<p>" + result.index + "</p>"
                 + "<p>" + result.index + "</p>"
                 + "<p>" + result.index + "</p>"
                 + "<p>" + result.index + "</p>"
                 ;
                 */
    } else {
        $('#noResultError').show();
        $('#displayResult').hide();
        /*
        content += "<p>We are <em>unable to find</em> the results for " + result.city + ", " + result.state + " in our database.</p>"
        + "<p>We <strong>apologize</strong> for the <em>inconvenience</em></p>"
        + "<p>Please report this issue to us by contacting us if you have time.</p>"
        + "<p>Thank you.</p>";
        */
    }


    //return content;
}
