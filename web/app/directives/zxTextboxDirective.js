// Tipos de textbox (return)
// - simple
// - hideRequired
// - normal

app.directive('zxTextbox', ['$rootScope', function($rootScope) {
    var id = 1;
    var data = $rootScope.textboxDirective;
    return {
        restrict: 'E',
        scope: data.scope,
        templateUrl: data.templateUrl,
        link: function(scope, elem, attrs) {
            var item = data.alias + id++;
            elem.find('input').attr('id' , item);
            elem.find('label').attr('for', item);
        }
    };
}]);