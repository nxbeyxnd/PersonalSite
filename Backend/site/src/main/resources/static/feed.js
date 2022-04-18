angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8190/api/v1';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/user',
            method: 'GET'
        }).then(function (response) {
            $scope.UserPage = response.data;
        });
    };

    $scope.fillTable();
});