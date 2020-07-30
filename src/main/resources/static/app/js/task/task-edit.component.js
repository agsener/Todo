angular.module("task")
    .component("taskEdit", {
        templateUrl: "/app/template/task/task-edit.html",
        controller: function ($scope, TaskApi, $routeParams, $location) {

            $scope.save = function () {
                TaskApi.save($scope.task, function () {
                    $location.path("/");
                });

            };

            $scope.init = function () {
                var task;
                if($routeParams == 'new'){
                    task = {};
                }
                else {
                    task = TaskApi.findById({id: $routeParams.id});
                }
                $scope.task = task;
            };

            $scope.init();
        }
    });