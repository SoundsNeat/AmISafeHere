
var coordinates = document.getElementById("demo");
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        coordinates.innerHTML = "Geolocation is not supported by this browser.";
    }
}
function showPosition(position) {
    coordinates.innerHTML = "Latitude: " + position.coords.latitude +
    "<br>Longitude: " + position.coords.longitude;
}

var $results = document.querySelector('#demo');
      var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');

      TeleportAutocomplete.init('#citySearch').on('change', function(value) {
        appendToResult('<pre>' + JSON.stringify(value, null, 2) + '</pre>');
      });
