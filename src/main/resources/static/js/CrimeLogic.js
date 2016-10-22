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


function loadModal(content) {
    $('#resultModalBody').empty();
    $('#resultModalBody').append(content);
    $('#resultModal').modal({show:true});
}

function parseServerConnectError() {
    return "<div class=\"has-error\"><p>Error connecting to server. <br />Please try again later. </p></div>";
}

function parseNoGpsError() {
    return "<div class=\"has-error\"><p>Unable to access your current locaitons.<br/>Please use the search box.</p></div>";
}


function parseOutput(data) {
    var content;
    content = "<h3>" + data.city + ", " + data.state + "</h3>";
    return content;
}
