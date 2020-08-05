var module = angular.module("task",
    ["ngResource"]);

module.factory("TaskApi", function ($resource) {
    var baseUrl = "/task";

    return $resource(baseUrl, {}, {
        list: {
            method: "GET",
            url: baseUrl + "/list",
            isArray: true
        },
        findById: {
            method: "GET",
            url: baseUrl + "/:id"
        },
        /*findByTopicName: {
            method: "GET",
            url: baseUrl + "/:topicName"
        },*/
        save: {
            method: "POST",
            url: baseUrl
        },
        delete: {
            method: "DELETE",
            url: baseUrl + "/:id"
        },
        setStatus: {
            method: "POST",
            url: baseUrl + "/set-status"
        }
    });
});


module.directive("task", function (TaskApi) {
    return {
        restrict: "E",
        templateUrl: '/app/template/task/task.html',
        scope: {
            ngModel: '=',
            checked: '=',
            onDelete: '&',
            aaa: '=',
            deletable: '='
        },
        link: function (scope, elem, attr) {

            scope.toggleStatus = function (task) {
                TaskApi.setStatus({id: task.id, done: !task.done}, function (response) {
                    if (response.code === 0) {

                    } else { // never execute

                    }
                });
            }
            scope.removeTask = function (id) {
                Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to delete task?",
                    icon: "warning",
                    showCancelButton: true
                }).then(function (result) {
                    if (result.value) {
                        TaskApi.delete({id: id}, function () {
                            Swal.fire(
                                'Deleted!',
                                'Your task has been deleted.',
                                'success'
                            )

                            // FIXME
                            if (scope.onDelete) {
                                scope.onDelete({id: id});
                            }
                        });
                    } else if (result.dismiss == 'cancel') {
                        Swal.fire(
                            'Don\'t worry!',
                            'Task not deleted.',
                            'success'
                        )
                    }
                });
            };

            scope.init = function () {
                scope.task = scope.ngModel;
            };

            scope.init();

        }
    }
});