(function() {

    var app = angular.module("sampleApp", []);

    app.controller("mainCtrl", [$scope, $http, function($scope, $http) {
       
    	$scope.username = "";
    	$scope.pass = "";
    	
        $scope.submit = function() {
        	console.log("submitted");
        	var form = {
                    "name" : $scope.username,
                    "pass" : $scope.pass,
                };
        	$http.set
            $http.post("http://localhost:8080/BoardroomBooking/loginProcess", form).then(onSuccess, onError);
        }

        $scope.onSuccess = function(response) {
            console.log(response);
        };

        $scope.onError = function(response) {
            console.log("error");
        };
    }]);

})();