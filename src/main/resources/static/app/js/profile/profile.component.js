angular.module("profile")
    .component("profile", {
        templateUrl: "/app/template/profile/profile.html",
        controller: function ($scope, ProfileApi) {

            $scope.onAfterValidateFunc = function (event, fileList) {
                if ($scope.base64 === fileList[0].base64) {
                    $scope.user.base64 = null;
                } else {
                    $scope.base64 = fileList[0].base64;
                    $scope.user.base64 = $scope.base64;
                }
            };

            $scope.saveProfile = function () {
                userDto = {
                    username: $scope.user.username,
                    name: $scope.user.name,
                    surname: $scope.user.surname,
                    base64: $scope.user.base64,
                }
                ProfileApi.updateUser(userDto, function (response) {
                    if (response.code === 0) {
                        swal.fire("Saved");
                    }
                })
            };

            $scope.changePhoto = function () {
                console.log("cift tiklaninca calisan fonk");

                angular.element("#img").click();

            };

            $scope.init = function () {
                ProfileApi.userProfile(function (response) {
                    $scope.user = response;
                    //console.log(user);
                    //console.log("user --> name : " + user.name);
                    ProfileApi.base64({uuid: $scope.user.profilPictureUuid}, function (res) {
                        $scope.base64 = res.message;
                        //console.log("res --> " + res.message);
                        //console.log("base64 --> " + base64);
                        // $scope.base64 = base64;
                    });
                    //$scope.user = user;
                })
            };

            $scope.init();
        }
    })