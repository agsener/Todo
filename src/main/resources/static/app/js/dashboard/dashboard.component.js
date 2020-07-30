angular.module("dashboard")
    .component("dashboard", {
        templateUrl: "/app/template/dashboard/dashboard.html",
        controller: function ($scope, TaskApi) {

            $scope.init = function () {

                $scope.tasks = TaskApi.list();

            };

            $scope.init();
        }
    });