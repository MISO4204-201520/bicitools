app.run(['$rootScope', function ($rootScope) {
	function getTemplateUrl(name){
		return "directives/" + name + "/" + name + ".html";
	}

	var formScope = {
		model: '=', // (Binding) ng-model
		label : '@', // (Text) <label></label>
		labelType : '@', // (Text) DEAFULT normal, hideRequired
		required: '@', // (Text) required attribute
		disabled: '='
	};

	function defaultDirective(data){
		return {
	        require: '^form',
	        restrict: 'E',
	        replace: true,
	        scope: data.scope,
	        templateUrl: data.templateUrl,
	        link: function(scope, elem, attrs, ctrl) {
	            scope.f = ctrl;
	        }
	    };
	}

	// ==== Text box ============================
	$rootScope.textboxDirective = defaultDirective({
		scope: formScope,
		templateUrl: getTemplateUrl('sg-textbox')
	});

	// ==== Password box ============================
	$rootScope.passwordDirective = defaultDirective({
		scope: formScope,
		templateUrl: getTemplateUrl('sg-passwordbox')
	});

	// ==== Datetime ===============================
	$rootScope.datetimeDirective = defaultDirective({
		scope: formScope,
		templateUrl: getTemplateUrl('sg-datetime')
	});
	// ==== Combo box ==============================
	var listScope = formScope;
	listScope.list = '=';

	var comboboxScope = listScope;
	comboboxScope.change = '&';

	$rootScope.comboboxDirective = defaultDirective({
		scope: comboboxScope,
		templateUrl: getTemplateUrl('sg-combobox')
	});

	// ==== Radio buttons =====================
	$rootScope.radiobuttonsDirective = defaultDirective({
		scope: listScope,
		templateUrl: getTemplateUrl('sg-radiobuttons')
	});
}]);
