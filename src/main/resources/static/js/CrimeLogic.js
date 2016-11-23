var $results = document.querySelector('#addressIsHere');
var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');
TeleportAutocomplete.init('#citySearch').on('change', function(value) {
appendToResult('<pre id="selectedCity" style="display:none;">' + value.title + '</pre>');
});

function reformatAddress(address) {
    return address.replace(/, /g,"-").replace(/ /g,"_");
}

function showPreLoad() {
    $('#pre-load').show();
    $('#cityStateTitle').hide();
    $('#noGpsError').hide();
    $('#serverError').hide();
}

function loadModal() {
    $('#pre-load').hide();
    $('#resultModal').modal({show:true});
    $('#resultModal').scrollTop(0);
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
        fillGraph(result);
        $('#displayResult').show();
        $('#noResultError').hide();

    } else {
        $('#noResultError').show();
        $('#displayResult').hide();
    }
}

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

function fillGraph(result) {
    var layout = {
        title: result.city.concat(' Crime Statistics'),
        xaxis: {
            title: 'Types of Crimes'
        },
        yaxis: {
            title: 'Total Incidents'
        }, 
        barmode: 'group'
    };

    Plotly.BUILD; // This constructor must be called for the graph to be created.
    
    var crimeTypes = [];
    for (var i = 0; i < result.crimeStats.length; i++) {
        if (result.crimeStats[i].totalCrimes != null) {
            crimeTypes.push(result.crimeStats[i].type);
        }
    }

    var crimeYearsArr = [];
    var allCrimesPerYear = [];
    for (var i = result.crimeDataYears.length - 1; i >= ((result.crimeDataYears.length < 3) ? 0 : result.crimeDataYears.length - 3); i--) {
        crimeYearsArr.push(result.crimeDataYears[i]);
        allCrimesPerYear.push([]);
    }

    for (var i = 0; i < crimeYearsArr.length; i++) {
        for (var j = 0; j < result.crimeStats.length; j++) {
            if (result.crimeStats[j].totalCrimes != null) {
                var cellLocation = result.crimeStats[j].totalCrimes.length - 1 - i;
                allCrimesPerYear[i].push(result.crimeStats[j].totalCrimes[cellLocation]);
            }
        }
    }

    var traces = [];
    for (var i = 0; i < crimeYearsArr.length; i++) {
        var temp = {
            x: crimeTypes,
            y: allCrimesPerYear[i],
            name: crimeYearsArr[i],
            type: 'bar'
        };
        traces.push(temp);
    }
    Plotly.newPlot('detailGraph', traces, layout);
}
