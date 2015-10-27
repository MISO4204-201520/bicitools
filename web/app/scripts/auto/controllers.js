var app = angular.module('bicitools', ['ui.router', 'angular-loading-bar']);

// It's very handy to add references to $state and $stateParams to the $rootScope
// so that you can access them from any scope within your applications.
// For example, <li ng-class="{ active: $state.includes('contacts.list') }">
// will set the <li> to active whenever 'contacts.list' or one of its
// decendents is active.
app.run(["$rootScope", "$state", "$stateParams", function ($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
}]);

var $stateProviderRef = null;

app.run(["$q", "$rootScope", "$state", "$http", "$urlRouter", function ($q, $rootScope, $state, $http, $urlRouter) {
    var states = [
        // ==== General =============================================
        {
            name: "index",
            url: "/",
            abstract: true,
            template: '<ui-view />'
        },
        {
            name: "index.home",
            url: '',
            templateUrl : "app/home.html",
            controller : 'chatController'
        },
        {
            name: "index.404",
            url: "404",
            templateUrl : "app/404.html"
        },
        // ==== Usuario =============================================
        {
            name: "index.login",
            url: "login",
            templateUrl : "app/modulos/usuarios/iniciar-sesion.html"
        },
        {
            name: "index.registrar",
            url: "registrar",
            templateUrl : "app/modulos/usuarios/registrar.html"
        },
        {
            name: "index.perfil",
            url: "perfil",
            templateUrl : "app/modulos/usuarios/perfil.html"
        },
        // ==== Comunicaci�n ========================================
        {
            name: "index.amigos",
            url: "amigos",
            templateUrl : "app/modulos/comunicacion/amigos.html"
        },
        {
            name: "index.chat",
            url: "chat",
            templateUrl : "app/modulos/comunicacion/chat.html"
        },
        {
            name: "index.solicitudesAmistad",
            url: "amigos/solicitudes",
            templateUrl : "app/modulos/comunicacion/solicitudes-amistad.html"
        },
        // ==== Reportes ============================================
        {
            name: "index.reportes",
            url: "reportes",
            templateUrl : "app/modulos/reportes/reportes.html"
        },
        // ==== Configurador de bicicletas ==========================
        {
            name: "index.configurador",
            url: "configurador",
            templateUrl : "app/modulos/configuracion-bicicletas/configuracion.html"
        },
        // ==== Domicilios ==========================================
        {
            name: "index.domicilios",
            url: "domicilios",
            templateUrl : "app/modulos/domicilios/domicilios.html"
        },
        // ==== SWAS ==========================================
        {
            name: "swas-index",
            parent: "index",
            url: "swas/usuarios",
            templateUrl : "app/swas/usuarios/index.html"
        },
        {
            name: "swas-nuevo-usuario",
            parent: "index",
            url: "swas/nuevo-usuario",
            templateUrl : "app/swas/usuarios/nuevo-usuario.html"
        },
        {
            name: "swas-persona",
            parent: "index",
            url: "swas/persona",
            templateUrl : "app/swas/usuarios/persona-form.html"
        },
        {
            name: "swas-usu-ejecutor",
            parent: "index",
            url: "swas/usuario-ejecutor",
            templateUrl : "app/swas/usuarios/usu-ejecutor.html"
        }
    ];
    states.forEach(function(state){
        $stateProviderRef.state(state);
    });

    // Configures $urlRouter's listener *after* your custom listener
    $urlRouter.sync();
    $urlRouter.listen();
}]);


app.config(["$stateProvider", "$httpProvider", "$locationProvider", "$urlMatcherFactoryProvider", "$urlRouterProvider",
    function ($stateProvider, $httpProvider, $locationProvider, $urlMatcherFactoryProvider, $urlRouterProvider) {
        $urlRouterProvider.deferIntercept();
        $urlRouterProvider.otherwise("/404");
        $urlMatcherFactoryProvider.caseInsensitive(true);
        $locationProvider.hashPrefix("!").html5Mode(true);
        $stateProviderRef = $stateProvider;
        //$httpProvider.interceptors.push("AuthHttpResponseInterceptor");
    }]);


app.controller('chatController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.texto = 'Hola, soy una variable de Angular.js';
    $scope.usu = {};

    $scope.postTest = function(){
        console.log("Este es el json que se va a enviar");
        console.log($scope.usu);

        $http.post('http://localhost:8080/api/test', $scope.usu).then(function(m){
            $scope.variable = m.apellido;
            $scope.v2 = m.id;
        }).catch(function(){
            $scope.errorMessage = "Ruta no encontrada";
            $scope.error = true;
        });
    }

    $scope.goTo = function(){    
        $state.go('index.reportes');
    }

    $scope.getTest = function(){
        console.log("Este es el json que se va a enviar");
        console.log($scope.usu);

        $http.get('http://localhost:8080/api/test', $scope.usu).then(function(m){
            $scope.variable = m.apellido;
            $scope.v2 = m.id;
        }).catch(function(){
            //$scope.errorMessage = "Lista Retornada";
            $scope.usuarios = [
                {
                    "nombre": "Jorge",
                    "apellido" : "Castro",
                    "foto": "http://lorempixel.com/50/50/"
                },
                {
                    "nombre": "Pedro",
                    "apellido" : "Pineda",
                    "foto": "http://lorempixel.com/51/50/"
                },
                {
                    "nombre": "Jhony",
                    "apellido" : "Toro",
                    "foto": "http://lorempixel.com/52/50/"
                },
                {
                    "nombre": "Taidy",
                    "apellido" : "Marrugo",
                    "foto": "http://lorempixel.com/53/50/"
                },
                {
                    "nombre": "Ludwing",
                    "apellido" : "Badillo",
                    "foto": "http://lorempixel.com/54/50/"
                }
            ];
        });
    }

}]);


app.directive('zxTextbox', function() {
  return {
    restrict: 'E',
    scope: {
        name: '@',
        model: '=',
        label : '@',
        required: '@',
        type: '@'
    },
    templateUrl: "app/custom-controls/zx-textbox.html"
  };
});

app.directive('zxCombobox', function() {
  return {
    restrict: 'E',
    scope: {
        name: '@',
        model: '=',
        label : '@',
        required: '@',
        list:'='
    },
    templateUrl: "app/custom-controls/zx-combobox.html"
  };
});


app.controller('registrarController', ['$scope', '$http', '$state', 'cfpLoadingBar', function($scope, $http, $state, cfpLoadingBar) {
	$scope.tiposIdentificacion = [
	{ id: 1, value: "Cédula de ciudadanía" },
	{ id: 2, value: "Tarjeta de identidad" },
	{ id: 3, value: "Cédula de extranjería" },
	{ id: 4, value: "Pasaporte" }
	];

	$scope.tiposPerfil = [
	{ id: 1, value: "Ciclista domiciliario" },
	{ id: 2, value: "Vendedor" },
	{ id: 3, value: "Ciclista recurrente" },
	];

	$scope.generos = [
	{ id: 1, value: "Hombre" },
	{ id: 2, value: "Mujer" }
	];

	$scope.submit = function(){
		console.log("todo bien");
		//cfpLoadingBar.start();
		$http.get('http://localhost:8080', $scope.usu).then(function(m){
			$scope.variable = m.apellido;
			$scope.v2 = m.id;
		}).catch(function(){
		});
	}

}]);

// {
// 	"numeroIdentificacion":1025473,
// 	"tipoIdentificacion": 1,
// 	"tipoPerfil": 3,
// 	"genero": 1,
// 	"nombres": "Pepito",
// 	"apellidos": "Perez",
// 	"foto": "perfil.jpg",
// 	"correo": "carlos.parra@otrouniandes.com",
// 	"fechaNacimiento": "2015-09-27",
// 	"direccionCasa": "Carrera 1 No. 2-34 Apto. 506",
// 	"direccionTrabajo": "Calle 7. No. 8-90 Of. 123",
// 	"telefonoFijo": "3111111",
// 	"telefonoMovil": "3109876542",
// 	"facebookUser": "tmarrugos",
// 	"twitterUser": "tmarrugos",
// 	"usuario": "tmarrugos",
// 	"contrasenia": "fabricas"
// }