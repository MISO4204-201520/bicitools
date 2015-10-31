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
