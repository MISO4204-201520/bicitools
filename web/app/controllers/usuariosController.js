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

app.controller('registrarController', ['$scope', '$http', '$state', function($scope, $http, $state) {

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
			$state.go('intro');
			$scope.showSuccessAlert('Registrado exitósamente en Bicitools');
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
