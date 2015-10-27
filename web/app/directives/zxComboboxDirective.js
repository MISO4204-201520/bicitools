app.directive('zxCombobox', ['$rootScope', function($rootScope) {
    var id = 1;
    var data = $rootScope.comboboxDirective;
    return {
        restrict: 'E',
        scope: data.scope,
        templateUrl: data.templateUrl,
        link: function(scope, elem, attrs) {           
            var item = data.alias + id++;
            elem.find('select').attr('id', item);
            elem.find('label').attr('for', item);
        }
    };
}]);