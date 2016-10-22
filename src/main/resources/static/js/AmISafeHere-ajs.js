var amISafeHere = angular.module('AmISafeHere', []);

amISafeHere.controller('CityCtrl', function ($scope, $http) {
    $scope.SearchCityWithCoordinates = function () {
        var content;
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var location = reformatAddress(coordToCity(position.coords.latitude, position.coords.longitude));
                $http.post("getCityStatistics" + "?location=" + location)
                .success(function(data){
                    console.info(data.city);
                    console.info(data.state);
                    content = "<h3>" + data.city + ", " + data.state + "</h3>";
                    loadModal(content);
                })
                .error(function(data){
                    console.error('fail');
                    content = "<p>Error connecting to server. <br />Please try again later. </p>";
                    loadModal(content);
                });
            });
        } else {
            console.error("Unable to access your current locaitons.\nPlease use the search box.");
            content = "<p>Unable to access your current locaitons.<br/>Please use the search box.</p>";
            loadModal(content);
        }
        //loadModal(content);
    }

    $scope.SearchCityWithAddress = function () {
        $http.post("getCityStatistics" + "?location=" + reformatAddress($('#selectedCity').text()))
            .success(function(data){
                console.info(data.city);
                console.info(data.state);
            })
            .error(function(data){
                console.error('fail');
            });
    }
});