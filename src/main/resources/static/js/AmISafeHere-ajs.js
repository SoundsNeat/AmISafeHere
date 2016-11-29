var amISafeHere = angular.module('AmISafeHere', []);

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    var googleMapsPrefix = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
    var googleSensorTrue = "&sensor=true";
    var googleKey = "&key=AIzaSyD90S0xFhWOVrI1BsM72yAognJ7tQy-_xM";

    var tracesTotal = [];
    var tracesPerOneHundredThousand = [];
    var crimeTypes = [];
    var crimeYearsArr = [];
    var allCrimesPerYear = [];
    var allCrimesPerOneHundredThousandPerYear = [];

    /*
     * Function for auto detecting coordinates
     * 1. If browser support geolocation, get coordinates.
     * 2. use google to convert to address
     * 3. get correct address
     * 4. connect to server to get the result JSON
     */
    $scope.SearchCityWithCoordinates = function () {
            showPreLoad();
            if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var googleUrl = googleMapsPrefix + position.coords.latitude + "," + position.coords.longitude
                                + googleSensorTrue + googleKey;
                console.info(googleUrl);
                $http.post(googleUrl)
                .success(function(result){
                    $http.post("getCityStatistics" + "?location=" + reformatAddress(extractAddressFromGoogleAPI(result.results)))
                    .success(function(result){
                        console.info("auto");
                        console.info(result.city);
                        console.info(result.state);
                        $scope.result = result;
                        constructDataForGraph();
                        parseOutput(result);
                        loadModal();
                    })
                    .error(function(data){
                        console.error('Failed to connect to backend for auto-detect.');
                        parseServerConnectError();
                        loadModal();
                    });
                })
                .error(function(data){
                    console.error('Failed to get location from Google Maps.');
                    parseNoGpsError();
                    loadModal();
                });
            });
        } else {
            console.error("Unable to access your current location.\nPlease use the search box.");
            parseNoGpsError();
            loadModal();
        }
    }

    /*
     * Function for manual keying in address.
     * connect to server to get the result JSON
     */
    $scope.SearchCityWithAddress = function () {
        showPreLoad();
        $http.post("getCityStatistics" + "?location=" + reformatAddress($('#selectedCity').text()))
            .success(function(data){
                console.info("manual");
                console.info(data.city);
                console.info(data.state);
                $scope.result = data;
                constructDataForGraph();
                parseOutput(data);
                loadModal();
            })
            .error(function(data){
                console.error('Failed to connect to backend for manual input.');
                parseServerConnectError();
                loadModal();
            });
    }


    $('#buttonGraphOneHundredThousandView').on('click', function (evt) {
        showOneHundredThousandGraph();
    });

    /*
     * empty the graph.
     * reconstruct the graph
     * show and hide the "div"s
     * can be improved as this is reconstructing the same data again and again.
     * NOTE: the "div" need to be emptied first.
     * If reconstruct at the next line while "div" display is none, the graph output is wrong.
     * So, show all necessary "div" and construct it at the same time.
     */
    function showOneHundredThousandGraph() {
        $('#oneHundredThousandGraph').empty();
    	$("#detailGraph").hide();
    	$("#oneHundredThousandGraph").show();
        $('#buttonGraphOneHundredThousandView').css('background-color', '#eee');
        $('#buttonGraphDetailView').css('background-color', '');
        fillGraph($scope.result.city, "Incidents per 100,000", tracesPerOneHundredThousand, "oneHundredThousandGraph");
    }
    /*
     * empty the graph.
     * reconstruct the graph
     * show and hide the "div"s
     * can be improved as this is reconstructing the same data again and again.
     * Same as above.
     */
    $('#buttonGraphDetailView').on('click', function (evt) {
        $('#detailGraph').empty();
    	$("#oneHundredThousandGraph").hide();
    	$("#detailGraph").show();
        $('#buttonGraphDetailView').css('background-color', '#eee');
        $('#buttonGraphOneHundredThousandView').css('background-color', '');
        fillGraph($scope.result.city, "Total Incidents", tracesTotal, "detailGraph");
    });

    /*
     * The following functions show and hide respective "divs" based on results.
     */
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
            $('#displayResult').show();
            $('#noResultError').hide();
            showOneHundredThousandGraph();

        } else {
            $('#noResultError').show();
            $('#displayResult').hide();
        }
    }

    /*
     * rewrite the result data to the correct format for the graph.
     * The latest 3 years of the data will be needed for graph.
     */
    function constructDataForGraph() {
        var result = $scope.result;

        tracesTotal = [];
        tracesPerOneHundredThousand = [];
        crimeYearsArr = [];
        crimeTypes = [];
        allCrimesPerYear = [];
        allCrimesPerOneHundredThousandPerYear = [];

        /*
         * Fill the crime types murder, rape, ... in an array
         */
        for (var i = 0; i < result.crimeStats.length; i++) {
            if (result.crimeStats[i].perHundredThousand != null && result.crimeStats[i].totalCrimes != null) {
                crimeTypes.push(result.crimeStats[i].type);
            }
        }
        /*
         * Fill the latest 3 year in an array
         * Create empty arrays for all & per 100,000 crime types
         * at the end each of them will have 3 empty sub arrays
         */
        for (var i = result.crimeDataYears.length - 1; i >= ((result.crimeDataYears.length < 3) ? 0 : result.crimeDataYears.length - 3); i--) {
            crimeYearsArr.push(result.crimeDataYears[i]);
            allCrimesPerYear.push([]);
            allCrimesPerOneHundredThousandPerYear.push([]);
        }
        /*
         * For each crime type, get the respective value and fill in the array created.
         */
        for (var i = 0; i < crimeYearsArr.length; i++) {
            for (var j = 0; j < result.crimeStats.length; j++) {
                if (result.crimeStats[j].perHundredThousand != null && result.crimeStats[j].totalCrimes != null) {
                    var cellLocation = result.crimeStats[j].perHundredThousand.length - 1 - i;
                    allCrimesPerYear[i].push(result.crimeStats[j].totalCrimes[cellLocation]);
                    allCrimesPerOneHundredThousandPerYear[i].push(result.crimeStats[j].perHundredThousand[cellLocation]);
                }
            }
        }
        /*
         * Rearrange them into trace arrays
         */
        for (var i = 0; i < crimeYearsArr.length; i++) {
            var temp = {
                x: crimeTypes,
                y: allCrimesPerYear[i],
                name: crimeYearsArr[i],
                type: 'bar'
            };
            tracesTotal.push(temp);
            temp = {
                x: crimeTypes,
                y: allCrimesPerOneHundredThousandPerYear[i],
                name: crimeYearsArr[i],
                type: 'bar'
            };
            tracesPerOneHundredThousand.push(temp);
        }
    }

    /*
     * based on Plotly input JSON format.
     */
    function fillGraph(city, yaxisTitle, traceArray, divId) {
        var layoutDetailed = {
            title: city.concat(' Crime Statistics'),
            xaxis: {
                title: 'Types of Crimes'
            },
            yaxis: {
                title: yaxisTitle
            },
            barmode: 'group'
        };
        Plotly.newPlot(divId, traceArray, layoutDetailed);
    }
});