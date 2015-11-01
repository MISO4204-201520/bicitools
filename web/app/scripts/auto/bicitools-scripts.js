var app = angular.module('bicitools', ['ui.router', 'angular-loading-bar', 'ngStorage', 'uiGmapgoogle-maps']);

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
            controller: "HomeController",
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
            controller: "perfilController",
            templateUrl : "views/modulos/usuarios/perfil.html"
        },
        // ==== Comunicación ========================================
        {
            name: "amigos",
            parent: 'menu-footer',
            url: "amigos",
            templateUrl : "views/modulos/comunicacion/menu-amigos.html"
        },
        {
            name: "listaAmigos",
            parent: 'menu-footer',
            url: "amigos/lista",
            templateUrl : "views/modulos/comunicacion/lista-amigos.html"
        },
        {
            name: "encontrarAmigos",
            parent: 'menu-footer',
            url: "amigos/buscador",
            templateUrl : "views/modulos/comunicacion/encontrar-amigos.html"
        },
        {
            name: "chat",
            parent: 'menu-footer',
            url: "chat",
            controller: 'chatController',
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
        {
            name: "reportes-actividad",
            parent: 'menu-footer',
            url: "reportes/actividad",
            controller: "ReportesActividadController",
            templateUrl : "views/modulos/reportes/actividad.html"
        },
        {
            name: "reportes-rutas-frecuentes",
            parent: 'menu-footer',
            url: "reportes/rutasfrecuentes",
            controller: "ReportesRutasFrecuentesController",
            templateUrl : "views/modulos/reportes/rutas-frecuentes.html"
        },
        {
            name: "reportes-notificaciones",
            parent: 'menu-footer',
            url: "reportes/notificaciones",
            controller: "ReportesNotificacionesController",
            templateUrl : "views/modulos/reportes/notificaciones.html"
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


app.filter('num', function() {
    return function(input) {
        return parseInt(input, 10);
    };
});

app.controller('chatController', function($scope, $http, $state) {

    $scope.nuevochat = {};
    $scope.m = {};
    $scope.m.usuario = $scope.$storage.user.usuario;

    $scope.obtenerChats = function(){
        $scope.get( $scope.setCommunicationPath('obtenerChatsUsuario/ivan'), function(response){
            $scope.chats = response.data.datos;
        });
    }
    $scope.obtenerChats();

    $scope.modalCrearChat = function(){
        $('#myModal').modal();
    };
    $scope.crearChat = function(){
        $scope.post( $scope.setCommunicationPath('crearChat'), $scope.nuevochat, function(response){
            $scope.obtenerChats();
            $('#myModal').modal('hide');
        });
    };
    $scope.verDetalles = function(){
        $('#modalChatDetalles').modal();
    }
    $scope.abrirChat = function(chatId){
        $scope.activeChat = chatId;
        $scope.m.idChat = chatId;
        $scope.get( $scope.setCommunicationPath('obtenerChatPorId/' + chatId), function(response){
            $scope.chat = response.data.datos[0];
            console.log($scope.chat);
            console.log($scope.chat.mensajes);
        });
    };
    $scope.enviarMensaje = function(){
        $scope.post( $scope.setCommunicationPath('enviarMensaje'), $scope.m, function(response){
            console.log(response.data);
            var mensaje = {
                fecha: Date.now(),
                nombreUsuario: $scope.m.usuario,
                texto: $scope.m.mensaje
            };

            $scope.chat.mensajes.push(mensaje);
            $scope.m.mensaje = "";
        });
    }
});

app.controller('domiciliarioController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/5625a1ee250000c603ccb2cc').then(function(response){
			$scope.d = response.data;
			$scope.d.horario[0].dia = {};
			$scope.d.horario[0].dia["1"] = true;
			$scope.d.horario[0].dia["2"] = true;
			$scope.d.horario[0].dia["3"] = true;
			$scope.d.horario[1].dia = {};
			$scope.d.horario[1].dia["2"] = true;
			$scope.d.horario[1].dia["5"] = true;
			$scope.d.horario[1].dia["6"] = true;
		});
	});


	$scope.days = [
		{name: 'L', value: '2'},
		{name: 'Ma', value: '3'},
		{name: 'Mi', value: '4'},
		{name: 'J', value: '5'},
		{name: 'V', value: '6'},
		{name: 'S', value: '7'},
		{name: 'D', value: '1'}
	];

	$scope.hours = [];
	for (var h = 0; h <= 23; h++) {
		var hh = h < 10 ? '0' + h : h;
		['00','30'].forEach(function(j){
			$scope.hours.push(hh + ':' + j);
		});
	};

	$scope.d = {};
	$scope.d.horario = [{}];

	$scope.submit = function(){
		$scope.d.horario.forEach(function(e){
			var numbers = [];

			for (var key in e.dia) {
				if(e.dia[key] === true){
					numbers.push(key);
				}
			}
			e.dia = numbers;
		});

		$scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
			$state.go('domicilios');
		});
	};

	$scope.addSchedule = function(){
		$scope.d.horario.push({});
	}
	$scope.removeSchedule = function(index){
		if (index > -1) {
			$scope.d.horario.splice(index, 1);
		}
	}
}]);

app.controller('pedirServicioController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	//$scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };

	angular.extend($scope, {
		map: {
			center: {
				latitude: 4.603063,
				longitude:-74.064863
			},
			zoom: 15,
			markers: [],
			events: {
				click: function (map, eventName, originalEventArgs) {
					var e = originalEventArgs[0];
					var lat = e.latLng.lat();
					var lon = e.latLng.lng();
					var address = '';

					var geocoder = new google.maps.Geocoder();
					var latlng = new google.maps.LatLng(lat, lon);

					geocoder.geocode({ 'latLng': latlng }, function (results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							if (results[1]) {
								console.log(results[1]);
								address = results[1].formatted_address;
							} else {
								console.log('Location not found');
							}
						} else {
							console.log('Geocoder failed due to: ' + status);
						}

						var marker = {
							id: Date.now(),
							coords: {
								latitude: lat,
								longitude: lon
							},
							address: address
						};

						if($scope.map.markers.length >= 2){
							$scope.map.markers = [];
						}
						$scope.map.markers.push(marker);


						console.log($scope.map.markers);
						$scope.$apply();
					});
				}
			}
		}
	});







	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/5625adc32500003a06ccb2d9').then(function(response){
			$scope.s = response.data;
		});
	});

	$scope.submit = function(){
		$scope.s.usuario = $scope.$storage.user.user;

		$scope.s.destino = $scope.map.markers[1].address;
		$scope.post($scope.setDomiciliosPath('pedirServicioDomicilio'), $scope.s, function(response){
			$state.go('domicilios');
		});
	};
}]);

app.controller('aceptarServicioController', ['$scope', '$http', '$state',
function($scope, $http, $state) {

	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/5625a5a9250000ab04ccb2d1').then(function(response){
			$scope.s = response.data;
		});
	});

	$scope.submit = function(){
		$scope.s.usuario = $scope.$storage.user.user;
		$scope.post($scope.setDomiciliosPath('aceptaServicioDomicilio'), $scope.s, function(response){
			$state.go('domicilios');
		});
	};
}]);

app.controller('registrarServicioController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/5625a65f250000d204ccb2d6').then(function(response){
			$scope.s = response.data;
		});
	});

	$scope.submit = function(){
		$scope.s.usuario = $scope.$storage.user.user;
		$scope.post($scope.setDomiciliosPath('registrarServicioDomicilio'), $scope.s, function(response){
			$state.go('domicilios');
		});
	};
}]);

app.controller('indexController', ['$scope', '$http', '$state', '$localStorage', function($scope, $http, $state, $localStorage) {
    $scope.$storage = $localStorage;
    $('#alerta').hide();

    var domain = "http://192.168.0.5:8080/";  //"http://192.168.0.11:8080/"; //

    $scope.setUsersPath = function(path) {
        return domain + "bicitoolsgu/webresources/gestionusuario/" + path;
    }

    $scope.setRoutesPath = function(path) {
        return domain + "bicitoolsRU/webresources/myresource/" + path;
    }

    $scope.setCommunicationPath = function(path) {
        return domain + "bicitoolsco/serviciosRest/comunicacion/" + path;
    }

    $scope.setDomiciliosPath = function(path) {
        return domain + "bicitoolsdo/serviciosRest/domicilios/" + path;
    }

    function invokeService(promise, action){
        return promise.then(
            function(response) {
                console.log('\nData:');
                console.log(response.data);

                if(response.data.codigo === 0 || response.data.code === 0){
                    action(response);
                    $scope.alert = {
                        tipo: 'alert-success',
                        mensaje: response.data.descripcion
                    }
                } else {
                    console.log('Código diferente a cero - ' + response.descripcion);
                    $scope.alert = {
                        tipo: 'alert-warning',
                        mensaje: response.descripcion
                    }
                }
                $('#alerta').show();
                $('#alerta').fadeTo(2000, 500).slideUp(500, function(){
                    $('#alerta').hide();
                });
            }, function(response){
                console.log('SERVICE ERROR');
                console.log(response);
                //$scope.error = "Error en la invocación";
                //window.location.hash = '#body';
                $scope.alert = {
                    tipo: 'alert-danger',
                    mensaje: "Error en la invocación"
                };
                $('#alerta').show();
                $('#alerta').fadeTo(2000, 500).slideUp(500, function(){
                    $('#alerta').hide();
                    //$('#alerta').alert('close');
                });
            });
    }

    $scope.post = function(path, input, action){
        console.log(path);
        console.log(input);
        return invokeService( $http.post(path, input), action );
    }

    $scope.get = function(path, action){
        console.log(path);
        return invokeService( $http.get(path), action );
    }

    $scope.bodyStyle = 'bg-black-dark-blue';
    $scope.setBodyStyle = function(style){
        $scope.bodyStyle = style;
    }

    $scope.setMock = function(action){
        $scope.invokeMock = action;
    };
}]);

app.controller('HomeController', function($scope, $http, $state, uiGmapGoogleMapApi) {
    var input = {
		id_usuario: $scope.$storage.user.id
	}
    $scope.post( $scope.setUsersPath('obtenerDetallesUsuario'), input, function(response){
		var r = response.data.datos;

		var tipoIdentificacion = 'Pasaporte';
		if(r[1] === '1'){
			tipoIdentificacion = 'Cédula de ciudadanía';
		} else if(r[1] === '2'){
			tipoIdentificacion = 'Tarjeta de identidad';
		} else if(r[1] === '3'){
			tipoIdentificacion = 'Cédula de extranjería';
		}

		var tipoPerfil = 'Ciclista recurrente';
		if(r[1] === '1'){
			tipoPerfil = 'Ciclista domiciliario';
		} else if(r[1] === '2'){
			tipoPerfil = 'Vendedor';
		}

		$scope.u = {
            id: $scope.$storage.user.id,
			numeroIdentificacion: r[0],
			tipoIdentificacion: tipoIdentificacion,
			tipoPerfil: tipoPerfil,
			genero: (r[3] === '1' ? 'Hombre' : 'Mujer'),
			nombres: r[4],
			apellidos: r[5],
			foto: r[0],
			correo: r[7],
			fechaNacimiento: r[8],
			direccionCasa: r[9],
			direccionTrabajo: r[10],
			telefonoFijo: r[11],
			telefonoMovil: r[12],
			facebookUser: r[13],
			twitterUser: r[14],
			usuario: r[15]
		};
        $scope.$storage.user = $scope.u;
	});
});

app.controller('calcularRutaController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	google.maps.event.addDomListener(window, 'load', initialize);

	$scope.d = {};
	$scope.setMock(function(){

		$scope.origen = {};
		$http.get('http://www.mocky.io/v2/5625b1fe2500001007ccb2da').then(function(response){
			$scope.origen.sitioDireccion = response.data.SitioDireccion;
		});

		$scope.destino = {};
		$http.get('http://www.mocky.io/v2/5625b55d250000b007ccb2db').then(function(response){
			$scope.destino.sitioDireccion = response.data.SitioDireccion;
		});
	});

	// $scope.directions = [
	// 	{
	// 		distance: "400 m",
	// 		time: "2 minutos",
	// 		details: "Gire a la derecha por carrera 45"
	// 	},
	// 	{
	// 		distance: "2 km",
	// 		time: "37 segundos",
	// 		details: "Gire a la otra izquierda querido usuario"
	// 	}
	// ];

	$scope.submit = function(){
		$scope.coordenadas = {};
		//$scope.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.origen,
		$http.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.origen).then( function(responseOrigen){
			console.log('\nOrigen');
			console.log(responseOrigen.data);
			$scope.coordenadas.latitudOrigen = responseOrigen.data.lat;
			$scope.coordenadas.longitudOrigen = responseOrigen.data.lng;

			//$scope.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.destino,
			$http.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.destino).then(function(responseDestino){
				console.log('\nDestino');
				console.log(responseDestino.data);
				$scope.coordenadas.latitudDestino = responseDestino.data.lat;
				$scope.coordenadas.longitudDestino = responseDestino.data.lng;

				$scope.post($scope.setRoutesPath('ObtenerDistanciaTiempoRuta'), $scope.coordenadas,
				//$http.post($scope.setRoutesPath('ObtenerDistanciaTiempoRuta'), $scope.destino).then(
				function(responseDistanciaTiempo){
					console.log('alo');
					console.log(responseDistanciaTiempo);
					$scope.distancia = responseDistanciaTiempo.data.data.distancia;
					$scope.tiempo = responseDistanciaTiempo.data.data.tiempo;
				});

				$scope.post($scope.setRoutesPath('ObtenerPuntosLatLongRuta'), $scope.coordenadas, function(responsePuntos){
					console.log('Servicio3');
					console.log(responsePuntos);
					$scope.directions = [];
					responsePuntos.data.data.forEach(function(e){
						var direction = {
							distance: e.distance.text,
							time: e.duration.text,
							details: e.html_instructions
						};
						$scope.directions.push(direction);
					}); // forEach
				}); // ObtenerPuntosLatLongRuta
			});
		}); // ObtenerCoordenadasPorSitioDireccion
	};
}]); // Controller

app.controller('crearRutaController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	google.maps.event.addDomListener(window, 'load', initialize);

	$scope.r = {};
	$scope.coordinates = [{}];

	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/5625d16e250000d70dccb2e2').then(function(response){
			$scope.r = response.data;
			$scope.coordinates = [];

			$http.get('http://www.mocky.io/v2/5625d284250000180eccb2e3').then(function(coordinate1){
				$scope.coordinates.push(coordinate1.data);

				$http.get('http://www.mocky.io/v2/5625d42d2500003c0eccb2e6').then(function(coordinate2){
					$scope.coordinates.push(coordinate2.data);

					$http.get('http://www.mocky.io/v2/5625d44e250000530eccb2e7').then(function(coordinate3){
						$scope.coordinates.push(coordinate3.data);
					});
				});
			});

			//http://www.mocky.io/v2/5625d284250000180eccb2e3
			//$scope.r.coordinates = [{}];
		});
	});

	$scope.addCoordinate = function(){
		$scope.coordinates.push({});
	}
	$scope.removeCoordinate = function(index){
		if (index > -1) {
			$scope.coordinates.splice(index, 1);
		}
	}

	$scope.submit = function(){
		//$state.go('recorridos');
		$scope.post($scope.setRoutesPath('CrearRuta'), $scope.r, function(response1){
			console.log('RUTA CREADA');
			$scope.coordinates.forEach(function(e){
				var coordinate = {
					nombre: $scope.r.nombre,
					latitudOrigen: e.latitudOrigen,
					longitudOrigen: e.longitudOrigen
				}
				//e.nombre = $scope.r.nombre;

				console.log(coordinate);
				$scope.post($scope.setRoutesPath('AgregarPuntoARuta'), coordinate, function(response2){
					console.log(response2.data);
				});
			});

		});
	};
}]);

app.controller('consultarRutasController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	$scope.routes = [
		{
			nombre: 'Uniandes - Centro Comercial Andino'
		},
		{
			nombre: 'Parque Simón Bolivar - Universidad de los Andes'
		},
		{
			nombre: 'La Candelaria - Municipio de Suba'
		}
	];
	// $scope.get('obtenerRutasUsuario', function(response1){
	// 	$scope.routes = response1.data;
	// 	console.log(response1.data);
	// });
}]);

app.controller('invitarARutaController', ['$scope', '$http', '$state',
function($scope, $http, $state) {

	$scope.users = [ 'nombre.usuario1', 'nombre.usuario2' ];


	$scope.get('obtenerUsuarioRuta', function(response){
		$scope.users = response.data;
		console.log(response.data);
	});

	$scope.addUser = function(){
		$scope.users.push('');
	}

	$scope.submit = function(){
		$scope.post('invitarARuta', $scope.users, function(response){
			console.log(response.data);
		});
	};
}]);


app.controller('miPosicionController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
}]);

app.controller('ReportesActividadController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('ReportesRutasFrecuentesController', function($scope, $http, $state) {

    $('.input-group.date.d-inicio').datepicker({
        format: "yyyy-mm-dd",
        startDate: "today",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });
    $('.input-group.date.d-fin').datepicker({
        format: "yyyy-mm-dd",
        startDate: "today",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });

    $scope.map = { center: { latitude: 4.603063, longitude:-74.064863 }, zoom: 15 };
    // $scope.datos = [
    //     {
    //         nombre: "casa trabajo",
    //         fechaHora: "Tue Oct 27 21:35:16 COT 2015",
    //         distancia: "13.9 km",
    //         tiempo: "33 mins",
    //         lugares: [
    //             {
    //                 latitud: "4.634992",
    //                 longitud: "-74.080758"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080858"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080966"
    //             }
    //         ]
    //     },
    //     {
    //         nombre: "casa trabajo",
    //         fechaHora: "Tue Oct 27 21:35:16 COT 2015",
    //         distancia: "13.9 km",
    //         tiempo: "33 mins",
    //         lugares: [
    //             {
    //                 latitud: "4.734992",
    //                 longitud: "-74.080758"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080858"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080966"
    //             }
    //         ]
    //     }
    // ]

    $scope.verMapa = function(i){
        $scope.mapa = true;
        $scope.map.center =  {
            latitude: $scope.datos[i].lugares[0].latitud,
            longitude: $scope.datos[i].lugares[0].longitud
        };

        $scope.map.markers = [];

        $scope.datos[i].lugares.forEach(function(e){
            var marker = {
                id: Date.now(),
                coords: {
                    latitude: e.latitud,
                    longitude: e.longitud
                }
            };
            $scope.map.markers.push(marker);
        });
        //$('#myModal').modal();
    };
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('ReportesNotificacionesController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('serviciosController', ['$scope', '$http', '$state',
	function($scope, $http, $state) {
		$scope.routes = [
		{
			nombre: 'Ruta 1'
		},
		{
			nombre: 'Ruta 2'
		}
		];

		$scope.obtenerUsuarioPorNickname = function(){
			$scope.get( $scope.setCommunicationPath('obtenerUsuarioPorNickname/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.filtrarUsuarios = function(){
			$scope.get( $scope.setCommunicationPath('filtrarUsuarios/palabra'), function(response){
				console.log(response);
			});
		};

		$scope.obtenerAmigosUsuario = function(){
			$scope.get( $scope.setCommunicationPath('obtenerAmigosUsuario/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.obtenerUsuariosBloqueados = function(){
			$scope.get( $scope.setCommunicationPath('obtenerUsuariosBloqueados/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.agregarUsuarioComoAmigo = function(){
			$scope.get( $scope.setCommunicationPath('agregarUsuarioComoAmigo/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.eliminarAmigo = function(){
			$scope.get( $scope.setCommunicationPath('eliminarAmigo/1'), function(response){
				console.log(response);
			});
		};

		$scope.bloquearUsuario = function(){
			$scope.post( $scope.setCommunicationPath('bloquearUsuario'), $scope.u, function(response){
				console.log(response);
			});
		};

		$scope.desbloquearUsuario = function(){
			$scope.get( $scope.setCommunicationPath('desbloquearUsuario/1'), function(response){
				console.log(response);
			});
		};
		
		$scope.obtenerSolicitudesAmistad = function(){
			$scope.get( $scope.setCommunicationPath('obtenerSolicitudesAmistad/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.aceptarSolicitudesAmistad = function(){
			$scope.get( $scope.setCommunicationPath('aceptarSolicitudesAmistad/1'), function(response){
				console.log(response);
			});
		};

		$scope.rechazarSolicitudesAmistad = function(){
			$scope.get( $scope.setCommunicationPath('rechazarSolicitudesAmistad/1'), function(response){
				console.log(response);
			});
		};

		$scope.obtenerChatsUsuario = function(){
			$scope.get( $scope.setCommunicationPath('obtenerChatsUsuario/nombre.usuario'), function(response){
				console.log(response);
			});
		};

		$scope.obtenerChatPorId = function(){
			$scope.get( $scope.setCommunicationPath('obtenerChatPorId/1'), function(response){
				console.log(response);
			});
		};

		$scope.crearChat = function(){
			$scope.post( $scope.setCommunicationPath('crearChat'), $scope.u, function(response){
				console.log(response);
			});
		};

		$scope.enviarMensaje = function(){
			$scope.post( $scope.setCommunicationPath('enviarMensaje'), $scope.u, function(response){
				console.log(response);
			});
		};

		// $scope.get('obtenerRutasUsuario', function(response1){
		// 	$scope.routes = response1.data;
		// 	console.log(response1.data);
		// });
	}]); 
app.controller('loginController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/562593c1250000b700ccb2c9').then(function(response){
			$scope.u = response.data;
		});
	});

	//$scope.u = $scope.setMock('http://www.mocky.io/v2/56258bb0100000fb5323eb32');

	$scope.submit = function(){
		$scope.$storage.user = $scope.u;
		$scope.post( $scope.setUsersPath('loginUsuario'), $scope.u, function(response){
			$scope.$storage.user.id = response.data.datos[0];
			$state.go('home');
		});
	}
}]);

app.controller('registrarController', ['$scope', '$http', '$state',
function($scope, $http, $state) {

	$scope.generos = [
		{ value: "1", label: "Hombre" },
		{ value: "2", label: "Mujer" }
	];

	$('.input-group.date').datepicker({
		format: "yyyy-mm-dd",
		//startDate: "today",
		clearBtn: true,
		//language: "es",
		orientation: "bottom auto"
	});

	$scope.setMock(function(){
		$http.get('http://www.mocky.io/v2/562594e8250000f800ccb2ca').then(function(response){
			$scope.u = response.data;
		});
	});
	$scope.setBodyStyle('bg-white');

	$scope.tiposIdentificacion = [
		{ id: 1, value: "Cédula de ciudadanía" },
		{ id: 2, value: "Tarjeta de identidad" },
		{ id: 3, value: "Cédula de extranjería" },
		{ id: 4, value: "Pasaporte" }
	];
	$scope.tiposIdentificacion.label = function(o){ return o.value; };
	$scope.tiposIdentificacion.track = function(o){ return o.id; };

	$scope.tiposPerfil = [
		{ id: 1, value: "Ciclista domiciliario" },
		{ id: 2, value: "Vendedor" },
		{ id: 3, value: "Ciclista recurrente" },
	];
	$scope.tiposPerfil.label = function(o){ return o.value; };
	$scope.tiposPerfil.track = function(o){ return o.id; };

	$scope.submit = function(){
		$scope.$storage.user = $scope.u;
		$scope.post( $scope.setUsersPath('registrarUsuario'), $scope.u, function(response){
			$state.go('home');
		});
	}
}]);


app.controller('perfilController', function($scope, $http, $state) {
	console.log($scope.$storage.user);
	var input = {
		id_usuario: $scope.$storage.user.id
	}
	$scope.post( $scope.setUsersPath('obtenerDetallesUsuario'), input, function(response){
		var r = response.data.datos;

		var tipoIdentificacion = 'Pasaporte';
		if(r[1] === '1'){
			tipoIdentificacion = 'Cédula de ciudadanía';
		} else if(r[1] === '2'){
			tipoIdentificacion = 'Tarjeta de identidad';
		} else if(r[1] === '3'){
			tipoIdentificacion = 'Cédula de extranjería';
		}

		var tipoPerfil = 'Ciclista recurrente';
		if(r[1] === '1'){
			tipoPerfil = 'Ciclista domiciliario';
		} else if(r[1] === '2'){
			tipoPerfil = 'Vendedor';
		}

		$scope.u = {
			numeroIdentificacion: r[0],
			tipoIdentificacion: tipoIdentificacion,
			tipoPerfil: tipoPerfil,
			genero: (r[3] === '1' ? 'Hombre' : 'Mujer'),
			nombres: r[4],
			apellidos: r[5],
			foto: r[0],
			correo: r[7],
			fechaNacimiento: r[8],
			direccionCasa: r[9],
			direccionTrabajo: r[10],
			telefonoFijo: r[11],
			telefonoMovil: r[12],
			facebookUser: r[13],
			twitterUser: r[14],
			usuario: r[15]
		};
	});
});

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

	// ==== Number box ============================
	$rootScope.numberboxDirective = defaultDirective({
		scope: formScope,
		templateUrl: getTemplateUrl('sg-numberbox')
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

app.directive('sgDatetime', ['$rootScope', function($rootScope) {
    return $rootScope.datetimeDirective;
}]);

app.directive('sgNumberbox', ['$rootScope', function($rootScope) {
    return $rootScope.numberboxDirective;
}]);

app.directive('sgPasswordbox', ['$rootScope', function($rootScope) {
    return $rootScope.passwordDirective;
}]);

app.directive('sgRadiobuttons', ['$rootScope', function($rootScope) {
    return $rootScope.radiobuttonsDirective;
}]);

app.directive('sgTextbox', ['$rootScope', function($rootScope) {
    return $rootScope.textboxDirective;
}]);

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
// app.run(['$rootScope', function ($rootScope) {
// 	var formScope = {
// 		model: '=',
// 		label : '@',
// 		required: '@',
// 		return: '@'
// 	};
//
// 	// ==== Text box ============================
// 	$rootScope.textboxDirective = {
// 		scope: formScope,
// 		templateUrl: "custom-controls/zx-textbox.html",
// 		alias: 'tb'
// 	};
//
// 	// ==== Password box ============================
// 	$rootScope.passwordboxDirective = {
// 		scope: formScope,
// 		templateUrl: "custom-controls/zx-passwordbox.html",
// 		alias: 'pb'
// 	};
//
// 	// ==== Combo box ===========================
// 	var comboboxScope = formScope;
// 	comboboxScope.list = '=';
//
// 	$rootScope.comboboxDirective = {
// 		scope: comboboxScope,
// 		templateUrl: "custom-controls/zx-combobox.html",
// 		alias: 'cb'
// 	};
//
//
// }]);

// Tipos de textbox (return)
// - simple
// - hideRequired
// - normal

app.directive('zxPasswordbox', ['$rootScope', function($rootScope) {
    var id = 1;
    var data = $rootScope.passwordboxDirective;
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
 function initialize() {
        var mapCanvas = document.getElementById('map');
        var mapOptions = {
          center: new google.maps.LatLng(4.603063, -74.064863),
          zoom: 18,
          mapTypeId: google.maps.MapTypeId.ROADMAP
      }
      var map = new google.maps.Map(mapCanvas, mapOptions);
  }
  //google.maps.event.addDomListener(window, 'load', initialize);
