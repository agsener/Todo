var app = angular.module("app",
    [
        "ngRoute",
        'ui.bootstrap',
        "task",
        "dashboard",
        "login"
    ]);

app.config(["$routeProvider", "$locationProvider",
    function ($routeProvider, $locationProvider) {

        $locationProvider.html5Mode({enabled: true});

        $routeProvider
            .when("/", {
                template: "<login></login>"
            })
            .when("/tasks", {
                template: "<task-list></task-list>"
            })
            .when("/dashboard", {
                template: "<dashboard></dashboard>"
            })
            .when("/:id", {
                template: "<task-edit></task-edit>"
            })
            .otherwise({
                redirectTo: "/tasks"
            });
    }]);

app.controller("AuthController", function($scope, AuthApi, $location){

    $scope.logout = function(){
        AuthApi.logout(function(response){
            if(response.code == 0){
                $location.path("/");
            }
        })
    }

    $scope.init = function(){}

    $scope.init();
});