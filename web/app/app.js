var app = angular.module('bicitools', ['ui.router', 'angular-loading-bar', 'ngStorage']);

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
            name: "intro",
            parent: 'index',
            url: '',
            templateUrl : "views/intro.html"
        },
        {
            name: "404",
            parent: 'index',
            url: "404",
            templateUrl : "views/404.html"
        },
        {
            name: "footer",
            parent: 'index',
            abstract: true,
            //template: '<ui-view />'
            templateUrl : "views/footer.html"
        },
        {
            name: "menu",
            parent: 'index',
            abstract: true,
            //template: '<ui-view />'
            templateUrl : "views/menu.html"
        },
        {
            name: "menu-footer",
            parent: 'footer',
            abstract: true,
            //template: '<ui-view />'
            templateUrl : "views/menu.html"
        },
        {
            name: "home",
            parent: 'menu-footer',
            url: "home",
            templateUrl : "views/home.html"
        },

        // ==== Servicios ===========================================
        {
            name: 'servicios',
            parent: 'menu-footer',
            url: "servicios",
            templateUrl : "views/modulos/servicios.html"
        },
        // ==== Usuario =============================================
        {
            name: "login",
            parent: 'index',
            url: "login",
            templateUrl : "views/modulos/usuarios/iniciar-sesion.html"
        },
        {
            name: "registrar",
            parent: 'footer',
            url: "registrar",
            templateUrl : "views/modulos/usuarios/registrar.html"
        },
        {
            name: "perfil",
            parent: 'menu-footer',
            url: "perfil",
            templateUrl : "views/modulos/usuarios/perfil.html"
        },
        // ==== Comunicación ========================================
        {
            name: "amigos",
            parent: 'menu-footer',
            url: "amigos",
            templateUrl : "views/modulos/comunicacion/amigos.html"
        },
        {
            name: "chat",
            parent: 'menu',
            url: "chat",
            templateUrl : "views/modulos/comunicacion/chat.html"
        },
        {
            name: "solicitudesAmistad",
            parent: 'menu-footer',
            url: "amigos/solicitudes",
            templateUrl : "views/modulos/comunicacion/solicitudes-amistad.html"
        },
        // ==== Reportes ============================================
        {
            name: "reportes",
            parent: 'menu-footer',
            url: "reportes",
            templateUrl : "views/modulos/reportes/reportes.html"
        },
        // ==== Configurador de bicicletas ==========================
        {
            name: "configurador",
            parent: 'menu-footer',
            url: "configurador",
            templateUrl : "views/modulos/configuracion-bicicletas/configuracion.html"
        },
        // ==== Recorridos ==========================================
        {
            name: 'recorridos',
            parent: 'menu-footer',
            url: "recorridos",
            templateUrl : "views/modulos/recorridos/recorridos.html"
        },
        {
            name: 'mejor-ruta',
            parent: 'menu-footer',
            url: "mejor-ruta",
            templateUrl : "views/modulos/recorridos/mejor-ruta.html"
        },
        {
            name: 'crear-ruta',
            parent: 'menu-footer',
            url: "rutas/c",
            templateUrl : "views/modulos/recorridos/ruta-form.html"
        },
        {
            name: 'consultar-rutas',
            parent: 'menu-footer',
            url: "rutas",
            templateUrl : "views/modulos/recorridos/rutas.html"
        },
        {
            name: 'ruta-invitar',
            parent: 'menu-footer',
            url: "ruta-invitar",
            templateUrl : "views/modulos/recorridos/ruta-invitar.html"
        },
        {
            name: 'mi-posicion',
            parent: 'menu-footer',
            url: "miposicion",
            templateUrl : "views/modulos/recorridos/miposicion.html"
        },
        // ==== Domicilios ==========================================
        {
            name: 'domicilios',
            parent: 'menu-footer',
            url: "domicilios",
            templateUrl : "views/modulos/domicilios/domicilios.html"
        },
        {
            name: 'domiciliario-form',
            parent: 'menu-footer',
            url: "domicilios/domiciliario",
            templateUrl : "views/modulos/domicilios/domiciliario-form.html"
        },
        {
            name: 'pedir-servicio',
            parent: 'menu-footer',
            url: "domicilios/pedir-servicio",
            templateUrl : "views/modulos/domicilios/pedir-servicio.html"
        },
        {
            name: 'aceptar-servicio',
            parent: 'menu-footer',
            url: "domicilios/aceptar-servicio",
            templateUrl : "views/modulos/domicilios/aceptar-servicio.html"
        },
        {
            name: 'registrar-servicio',
            parent: 'menu-footer',
            url: "domicilios/registrar-servicio",
            templateUrl : "views/modulos/domicilios/registrar-servicio.html"
        },
        // ==== SWAS ==========================================
        {
            name: "swas-index",
            parent: "index",
            url: "swas/usuarios",
            templateUrl : "views/swas/usuarios/index.html"
        },
        {
            name: "swas-nuevo-usuario",
            parent: "index",
            url: "swas/nuevo-usuario",
            templateUrl : "views/swas/usuarios/nuevo-usuario.html"
        },
        {
            name: "swas-persona",
            parent: "index",
            url: "swas/persona",
            templateUrl : "views/swas/usuarios/persona-form.html"
        },
        {
            name: "swas-usu-ejecutor",
            parent: "index",
            url: "swas/usuario-ejecutor",
            templateUrl : "views/swas/usuarios/usu-ejecutor.html"
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
