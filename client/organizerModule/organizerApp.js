
var organizerApp = angular.module("organizerApp", ["ngRoute"]);

organizerApp.config(function($routeProvider){
    $routeProvider
        .when("/home", {
            templateUrl: "home.html"
        })
        .when("toDoList",{
            templateUrl: "toDoList.html"
        })
        .otherwise({
            redirectTo: '/home'
        });
});
