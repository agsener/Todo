var module = angular.module("login",
    ["ngResource"]);

module.factory("AuthApi", function ($resource) {
    var baseUrl = "/login";

    return $resource(baseUrl, {}, {
        login: {
            method: "POST",
            url: baseUrl
        },
        logout: {
            method: "POST",
            url: baseUrl + "/logout"
        }
    });
});
