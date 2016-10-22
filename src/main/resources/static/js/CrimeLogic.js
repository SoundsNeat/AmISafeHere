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
