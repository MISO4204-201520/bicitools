app.controller('calcularRutaController', function($scope, $http, $state, $sce) {
	//google.maps.event.addDomListener(window, 'load', initialize);

	angular.extend($scope, {
		map: {
			center: { latitude: 4.603063, longitude:-74.064863 },
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
							if (results[0]) {
								address = results[0].formatted_address;
							} else {
								console.log('Location not found');
							}
						} else {
							console.log('Geocoder failed due to: ' + status);
						}

						var marker = {
							id: Date.now(),
							coords: { latitude: lat, longitude: lon },
							address: address
						};

						if($scope.map.markers.length >= 2){
							$scope.map.markers = [];
						}
						$scope.map.markers.push(marker);
						$scope.$apply();
					});
				}
			}
		}
	});


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
		console.log($scope.map.markers[0].address);
		var json = { sitioDireccion: $scope.map.markers[0].address }
		//$scope.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.origen,
		$http.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), json).then( function(responseOrigen){
			console.log('\nOrigen');
			console.log(responseOrigen.data);
			$scope.coordenadas.latitudOrigen = responseOrigen.data.lat;
			$scope.coordenadas.longitudOrigen = responseOrigen.data.lng;

			json = { sitioDireccion: $scope.map.markers[1].address }
			//$scope.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), $scope.destino,
			$http.post($scope.setRoutesPath('ObtenerCoordenadasPorSitioDireccion'), json).then(function(responseDestino){
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
							details: $sce.trustAsHtml(e.html_instructions)
						};
						$scope.directions.push(direction);
					}); // forEach
				}); // ObtenerPuntosLatLongRuta
			});
		}); // ObtenerCoordenadasPorSitioDireccion
	};
}); // Controller

app.controller('crearRutaController', ['$scope', '$http', '$state', 'uiGmapGoogleMapApi',
function($scope, $http, $state, uiGmapGoogleMapApi) {
	//google.maps.event.addDomListener(window, 'load', initialize);

	$scope.polylines = [
		{
			id: 1,
			path: [],
			stroke: {
				color: '#6060FB',
				weight: 3
			},
			editable: true,
			draggable: true,
			geodesic: true,
			visible: true
		},
	];


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
							if (results[0]) {
								address = results[0].formatted_address;
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

						if($scope.map.markers.length <= 0){
							$scope.map.markers.push(marker);
						}


						uiGmapGoogleMapApi.then(function(){
						    $scope.polylines[0].path.push({ latitude: lat, longitude: lon })
						});


						console.log($scope.map.markers);
						$scope.$apply();
					});
				}
			}
		}
	});


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
		$scope.$storage.routes.push({ nombre: $scope.r.nombre});
		$scope.showSuccessAlert('Ruta creada exitósamente');
		$state.go('recorridos');

		// $scope.post($scope.setRoutesPath('CrearRuta'), $scope.r, function(response1){
		// 	console.log('RUTA CREADA');
		// 	$scope.coordinates.forEach(function(e){
		// 		var coordinate = {
		// 			nombre: $scope.r.nombre,
		// 			latitudOrigen: e.latitudOrigen,
		// 			longitudOrigen: e.longitudOrigen
		// 		}
		// 		//e.nombre = $scope.r.nombre;
		//
		// 		console.log(coordinate);
		// 		$scope.post($scope.setRoutesPath('AgregarPuntoARuta'), coordinate, function(response2){
		// 			console.log(response2.data);
		// 		});
		// 	});
		//
		// });
	};
}]);

app.controller('consultarRutasController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
	if($scope.$storage.routes === undefined || $scope.$storage.routes.length <= 0)
	{
		$scope.$storage.routes = [
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
	}

	// $scope.get('obtenerRutasUsuario', function(response1){
	// 	$scope.routes = response1.data;
	// 	console.log(response1.data);
	// });
}]);

app.controller('invitarARutaController', ['$scope', '$http', '$state',
function($scope, $http, $state) {

	$scope.users = [ 'nombre.usuario1', 'nombre.usuario2' ];
	$scope.usuarios = {};



	// $scope.get('obtenerUsuarioRuta', function(response){
	// 	$scope.users = response.data;
	// 	console.log(response.data);
	// });

	$scope.addUser = function(){
		$scope.users.push('');
	}

	$scope.submit = function(){
		console.log($scope.usuarios);
		// $scope.post('invitarARuta', $scope.users, function(response){
		// 	console.log(response.data);
		// });
	};
}]);


app.controller('miPosicionController', ['$scope', '$http', '$state',
function($scope, $http, $state) {
}]);
