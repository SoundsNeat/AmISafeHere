var $results = document.querySelector('#addressIsHere');
var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');
TeleportAutocomplete.init('#citySearch').on('change', function(value) {
appendToResult('<pre id="selectedCity" style="display:none;">' + value.title + '</pre>');
});

/*
 * This function tries to find the correct address type from all different types returned by google api.
 * The type of address for this program is "postal_code"
 * More detailed address type will be needed if this project moves toward "neighborhood"
 */
function extractAddressFromGoogleAPI(result) {
    var correctType = "postal_code";
    for (var i = 0; i < result.length; i++) {
        if (result[i].types.indexOf(correctType) > -1) {
            return getAddressFromGoogleAPI(result[i].address_components);
        }
    }
    return "";
}

/*
 * This function extracts city, state, and country from the postal_code address type
 * by matching the respective keywords
 */
function getAddressFromGoogleAPI(postalAddress) {
    var correctCityType = "locality";
    var correctStateType = "administrative_area_level_1";
    var correctCountryType = "country";
    var city = "";
    var state = "";
    var country = "";
    for (var i = 0; i < postalAddress.length; i++) {
        if (postalAddress[i].types.indexOf(correctCityType) > -1) {
            city = postalAddress[i].long_name;
        } else if (postalAddress[i].types.indexOf(correctStateType) > -1) {
            state = postalAddress[i].long_name;
        } else if (postalAddress[i].types.indexOf(correctCountryType) > -1) {
            country = postalAddress[i].long_name;
        }
    }
    return city + "-" + state + "-" + country;
}


/*
 * This function replace all ", " with "-" and all spaces with "_"
 * by using REGEX
 */
function reformatAddress(address) {
    return address.replace(/, /g,"-").replace(/ /g,"_");
}


/**
 * This function draw knifes or blank knifes based on how safe the city is.
 * 1 is safest, 5 is most dangerous.
 * when it is safe, there will be only 1 knife.
 * Hence this function approaches the result negatively.
 */
function constructStars(value) {
    var imgTag = "<img src=\"../img/knife-small-$Val.png\" class=\"danger-image\" alt=\"\" />&nbsp;";

    var index = 0;
    var htmlText = "";
    while (index < 5) {
        htmlText += imgTag.replace("$Val", index < value ? "1" : "0");
        index++;
    }
    $('#resultStars').html(htmlText);
}

/*
 * Constructing google map on pop up.
 * 1st half of the code is creating new map.
 * 2nd half of the code is converting address to geocode and make that the center of the map.
 * This code can be improved as 1st part should be executed once.
 * But it is not working for pop ups.
 * 2nd part is the double work as we already have the coordinates. (Can be improved)
 */
function googleMapAddress(address) {
    console.info(address);
    var geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
            zoom: 12,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
    var map = new google.maps.Map(document.getElementById("googleMapMedium"), myOptions);
    geocoder.geocode( { 'address': address}, function(results, status) {
        console.info(status);
        if (status == google.maps.GeocoderStatus.OK) {
            console.dir(results[0].geometry.location);
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });
        } else {
            console.error("geocoder failed");
        }
    });
}


