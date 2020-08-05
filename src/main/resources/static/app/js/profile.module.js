var module = angular.module("profile",
    ["ngResource", "naif.base64"]);

module.factory("ProfileApi", function ($resource) {
    var baseUrl = "/login"

    return $resource(baseUrl, {}, {
        userProfile: {
            method: "GET",
            url: baseUrl + "/profile"
        },
        base64: {
          method: "GET",
          url: baseUrl + "/:uuid"
        },
        updateUser: {
          method: "POST",
          url: baseUrl + "/update"
        }
    });
});