var $results = document.querySelector('#addressIsHere');
var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');
TeleportAutocomplete.init('#citySearch').on('change', function(value) {
appendToResult('<pre id="selectedCity" style="display:none;">' + value.title + '</pre>');
});

function reformatAddress(address) {
    return address.replace(/, /g,"-").replace(/ /g,"_");
}

function loadModal() {
    $('#resultModal').modal({show:true});
}

function parseServerConnectError() {
    $('#serverError').show();
    $('#noGpsError').hide();
    $('#cityStateTitle').hide();

}

function parseNoGpsError() {
    $('#noGpsError').show();
    $('#cityStateTitle').hide();
    $('#serverError').hide();
}

function parseOutput(result) {
    $('#cityStateTitle').show();
    $('#noGpsError').hide();
    $('#serverError').hide();
    if (result.success) {
        constructStars(result.amISafeIndex);
        googleMapAddress(result.city + ", " + result.state + ", United States");
        $('#displayResult').show();
        $('#noResultError').hide();

    } else {
        $('#noResultError').show();
        $('#displayResult').hide();
    }
}

function constructStars(value) {
    var imgTag = "<img src=\"../img/star-small-$Val.png\" alt=\"\" />";

    var index = 0;
    var htmlText = "";
    while (index < 5) {
        htmlText += imgTag.replace("$Val", index < value ? "1" : "0");
        index++;
    }
    $('#resultStars').html(htmlText);
}

function googleMapAddress(address) {
    var geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
            zoom: 12,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
    var map = new google.maps.Map(document.getElementById("googleMapMedium"), myOptions);
    geocoder.geocode( { 'address': address}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });
        }
    });
}
