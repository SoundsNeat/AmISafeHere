var amISafeHere = angular.module('AmISafeHere', []);
var googleMaps = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    $scope.SearchCityWithCoordinates = function () {

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var lat = position.coords.latitude;
                var long = position.coords.longitude;
                var location;
                $http.post(googleMaps + lat + "," + long + "&sensor=true")
                .success(function(result){
                    location = result.results[0].address_components[2].long_name + "," +
                               result.results[0].address_components[3].long_name + "," +
                               result.results[0].address_components[5].long_name;
                    console.info(location);
                })
                .error(function(data){
                    console.error('fail');
                    loadModal(parseServerConnectError());
                });

                //TODO: Calling our own method here seems unnecessary?
                $http.get("getCityStatistics" + "?location=" + location)
                .success(function(result){
                    console.info(result.city);
                    console.info(result.state);
                    $scope.result = result;
                    loadModal(parseOutput(result));
                })
                .error(function(data){
                    console.error('fail');
                    loadModal(parseServerConnectError());
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
                console.info(data.city);
                console.info(data.state);
                loadModal(parseOutput(data));
            })
            .error(function(data){
                console.error('fail');
                loadModal(parseServerConnectError());
            });
    }

});