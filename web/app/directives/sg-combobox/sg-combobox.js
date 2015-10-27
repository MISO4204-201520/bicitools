app.directive('sgCombobox', ['$rootScope', function($rootScope) {
    return $rootScope.comboboxDirective;
}]);

//var item = data.alias + scope.$id;
//elem.find('select').attr('id', item);
//elem.find('select').attr('name' , item);
//elem.find('label').attr('for', item);

// var validations = [ 'invalid', 'touched', 'dirty'  ];
// var name = ctrl.$name + '.n_' + scope.$id + '.$';
//
// for (var i = 0; i < validations.length; i++) {
//     validations[i] = name + validations[i];
// }
// scope.hasError = validations[0];
// for (var i = 1; i < validations.length; i++) {
//     scope.hasError += ' && ' + validations[i];
// }
// scope.hasError = ctrl.$name + '.$submitted || (' + scope.hasError + ')';
// scope.hasError = ctrl.$name + '.$submitted'; //name + 'valid';
