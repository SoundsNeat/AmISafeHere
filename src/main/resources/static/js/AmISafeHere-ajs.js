var amISafeHere = angular.module('AmISafeHere', []);

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    $scope.SearchCityWithCoordinates = function () {

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var location = reformatAddress(coordToCity(position.coords.latitude, position.coords.longitude));
                $http.post("getCityStatistics" + "?location=" + location)
                .success(function(data){
                    console.info(data.city);
                    console.info(data.state);
                    loadModal(parseOutput(data));
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