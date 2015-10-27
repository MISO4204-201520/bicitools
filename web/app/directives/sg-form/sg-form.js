app.directive('sgForm', ['$rootScope', function($rootScope) {
    var directive = $rootScope.comboboxDirective;
    //directive.transclude = true;
    return {
        transclude: true,
        restrict: 'E',
        replace: true,
        scope: {
            submit: "&"
        },
        templateUrl: "directives/sg-form/sg-form.html",
        link: function(scope, elem, attrs, ctrl) {
            scope.f = ctrl;
        }
    };
}]);
