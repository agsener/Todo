angular.module("login")
    .component("login", {
        templateUrl: "/app/template/login/login.html",
        controller: function ($scope, AuthApi, $routeParams, $location) {

            $scope.loginFunc = function(){
                AuthApi.login($scope.loginDto, function (response) {
                    console.log("id: " + response.message);
                    if(response.code === 0){
                        $location.path("/tasks");
                    }else{
                        switch (response.code) {
                            default:
                            case 50:
                                return;
                        }
                    }
                })
            }

            $scope.init = function () {

                $scope.loginDto = {
                  username: undefined,
                  password: undefined
                };
            }

            $scope.init();
        }
    })