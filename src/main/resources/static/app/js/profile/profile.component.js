angular.module("profile")
    .component("profile", {
        templateUrl: "/app/template/profile/profile.html",
        controller: function ($scope, ProfileApi) {

            var user = "";
            var base64 = "";

            $scope.profileFunc = function () {
                userDto = {
                    username: user.username,
                    name: user.name,
                    surname: user.surname,
                }
                ProfileApi.updateUser(userDto, function (response) {
                    if (response.code === 0) {
                        swal.fire("Saved");




                    }
                })
            };

            $scope.changePhoto = function () {
                console.log("cift tiklaninca calisan fonk");

            };

            $scope.init = function () {
                ProfileApi.userProfile(function (response) {
                    user = response;
                    console.log(user);
                    console.log("user --> name : " + user.name);
                    ProfileApi.base64({uuid: user.profilPictureUuid}, function (res) {
                        base64 = res.message;
                        console.log("res --> " + res.message);
                        console.log("base64 --> " + base64);
                        $scope.base64 = base64;
                    });
                    $scope.user = user;
                })
            };

            $scope.init();
        }
    })