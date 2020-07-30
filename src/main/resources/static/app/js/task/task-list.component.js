angular.module("task")
    .component("taskList", {
        templateUrl: "/app/template/task/task-list.html",
        controller: function ($scope, TaskApi, $location, $uibModal) {

            $scope.taskDeleted = function(id, index){
                $scope.tasks.splice(index, 1);
            };

            $scope.newTask = function(){

                var inst = $uibModal.open({
                    templateUrl: '/app/template/task/task-modal.html',
                    controller: 'TaskModalController'
                });

                inst.result.then(function(task){
                    TaskApi.save(task, function () {
                        $scope.tasks = TaskApi.list();
                    });
                }, function(){
                    console.log('dismiss');
                })

            };

            $scope.addTask = function (task) {
                $location.path(task ? task.id : 'new');
            };

            $scope.init = function () {
                $scope.tasks = TaskApi.list();
                // manage tasks today|previous|next wt moment

                $scope.showDone = true;
            };

            $scope.init();
        }
    })
    .controller("TaskModalController", function($scope, $uibModalInstance){
        $scope.ok = function(){
            $uibModalInstance.close($scope.task);
        };

        $scope.cancel = function(){
            $uibModalInstance.dismiss('cancel');
        };

        $scope.init = function(){
            $scope.task = {};
        };

        $scope.init();
    });