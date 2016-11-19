var amISafeHere = angular.module('AmISafeHere', []);

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    var googleMapsPrefix = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
    var googleSensorTrue = "&sensor=true";
    var googleResultIndex = 2;  // detail address format from google
    var googleArrayCityIndex = 1;
    var googleArrayStateIndex = 3;
    var googleArrayCountryIndex = 4;
    $scope.SearchCityWithCoordinates = function () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var location = "";
                var googleUrl = googleMapsPrefix + position.coords.latitude + "," + position.coords.longitude + googleSensorTrue;
                console.info(googleUrl);
                $http.post(googleUrl)
                .success(function(result){
                    console.dir(result);
                    location = result.results[googleResultIndex].address_components[googleArrayCityIndex].long_name + "-" +
                               result.results[googleResultIndex].address_components[googleArrayStateIndex].long_name + "-" +
                               result.results[googleResultIndex].address_components[googleArrayCountryIndex].long_name;
                    $http.post("getCityStatistics" + "?location=" + reformatAddress(location))
                    .success(function(result){
                        console.info("auto");
                        console.info(result.city);
                        console.info(result.state);
                        $scope.result = result;
                        loadModal(parseOutput(result));
                    })
                    .error(function(data){
                        console.error('fail to connect to backend for auto');
                        loadModal(parseServerConnectError());
                    });
                })
                .error(function(data){
                    console.error('fail to get coords from google map');
                    loadModal(parseNoGpsError());
                });
            });
        } else {
            console.error("Unable to access your current locaitons.\nPlease use the search box.");
            loadModal(parseNoGpsError());
        }
    }

    $scope.SearchCityWithAddress = function () {
        $http.post("getCityStatistics" + "?location=" + reformatAddress($('#selectedCity').text()))
            .success(function(data){
                console.info("manual");
                console.info(data.city);
                console.info(data.state);
                $scope.result = data;
                loadModal(parseOutput(data));
            })
            .error(function(data){
                console.error('fail to connect to backend for manual');
                loadModal(parseServerConnectError());
            });
    }
});