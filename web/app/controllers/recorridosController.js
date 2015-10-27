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
