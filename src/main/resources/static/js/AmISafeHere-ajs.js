var amISafeHere = angular.module('AmISafeHere', []);
var googleMaps = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    $scope.SearchCityWithCoordinates = function () {
        console.info("with Jenkins 0.2");
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var location = "";
                $http.post(googleMaps + position.coords.latitude + "," + position.coords.longitude + "&sensor=true")
                .success(function(result){
                    location = result.results[0].address_components[3].long_name + "-" +
                               result.results[0].address_components[5].long_name + "-" +
                               result.results[0].address_components[6].long_name;
                    console.info("used google map: " + location);
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