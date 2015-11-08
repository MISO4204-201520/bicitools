app.controller('armarBiciController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.valorTotal = 0;
	$scope.compra = {
		user: $scope.$storage.user.usuario,
		config: {}
	};

	$scope.partes = [
		{
			id: 1,
			alias: "marco",
			nombre: "Marco",
			plural: "Marcos"
		},
		{
			id: 6,
			alias: "sillin",
			nombre: "Sillin",
			plural: "Sillines"
		},
		{
			id: 3,
			alias: "ruedas",
			nombre: "Ruedas",
			plural: "Ruedas"
		},
		{
			id: 4,
			alias: "frenos",
			nombre: "Frenos",
			plural: "Frenos"
		},
		{
			id: 8,
			alias: "cambios",
			nombre: "Cambios",
			plural: "Cambios"
		},
		{
			id: 2,
			alias: "tijera",
			nombre: "Tijera",
			plural: "Tijeras"
		},
		{
			id: 5,
			alias: "manubrio",
			nombre: "Manubrio",
			plural: "Manubrios"
		},
		{
			id: 11,
			alias: "pedales",
			nombre: "Pedales",
			plural: "Pedales"
		},
		{
			id: 12,
			alias: "cadena",
			nombre: "Cadena",
			plural: "Cadenas"
		},
		{
			id: 9,
			alias: "platoDelantero",
			nombre: "Plato delantero",
			plural: "Platos delanteros"
		},
		{
			id: 10,
			alias: "platoTrasero",
			nombre: "Plato trasero",
			plural: "Platos traseros"
		}
	];
	$scope.aplicarClearfix = function(id, columnas){
		var resultado = (id + 1) % columnas === 0;
		return resultado;
	}
	$scope.abrirSeleccionador = function(parte, i){
		$scope.modal = {
			usuario: $scope.$storage.user.usuario,
			titulo: parte.plural,
			nombre: parte.nombre,
			alias: parte.alias,
			id: i
		};
		$scope.modal.partes = [
			{
				idParte: 3,
				nombre: "Marco el gato consectetur adipisicing elit",
				descripcion: "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
				valor: "190000",
				imagen: "http://lorempixel.com/128/128/transport/1"
			},
			{
				idParte: 3,
				nombre: "Marco el perro",
				descripcion: "",
				valor: "30000",
				imagen: "http://lorempixel.com/128/128/transport/2"
			},
			{
				idParte: 3,
				nombre: "Marco mi pez",
				descripcion: "Consectetur adipisicing elit",
				valor: "280000",
				imagen: "http://lorempixel.com/128/128/transport/3"
			}
		];
		// $scope.post($scope.setBikeConfPath('consultarPartexTipo'), $scope.modal, function(response){
		// 	console.log(response);
		// });
	};
	$scope.removerParte = function(parte){
		$scope.valorTotal -= parte.parteSeleccionada.valor;
		parte.parteSeleccionada = null;
		$scope.compra.config[$scope.modal.alias] = 0;
	};
	$scope.seleccionarParte = function(parte){
		$scope.valorTotal += parseInt(parte.valor, 10);
		$scope.partes[$scope.modal.id].parteSeleccionada = parte;
		$scope.compra.config[$scope.modal.alias] = parte.idParte;
	};
	$scope.submit = function(){
		$scope.compra.consulta = "0";

		console.log($scope.compra);
		$state.go('configurador');
		var m = "Compra realizada. Id de compra: " + 123;
		$scope.showSuccessAlert(m);

		// $scope.post($scope.setBikeConfPath('verificarConfiguracion'), $scope.compra, function(response){
		// 	$state.go('domicilios');
		// });
	};
}]);

app.controller('ordenesController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.submit = function(){
		$scope.s.usuario = $scope.$storage.user.user;
		$scope.s.destino = $scope.map.markers[1].address;
		$scope.post($scope.setDomiciliosPath('pedirServicioDomicilio'), $scope.s, function(response){
			$state.go('domicilios');
		});
	};
}]);

app.controller('parteBiciController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.submit = function(){
		$scope.s.usuario = $scope.$storage.user.user;
		$scope.post($scope.setDomiciliosPath('aceptaServicioDomicilio'), $scope.s, function(response){
			$state.go('domicilios');
		});
	};
}]);

// app.controller('registrarServicioController', ['$scope', '$http', '$state', function($scope, $http, $state) {
// 	$scope.submit = function(){
// 		$scope.s.usuario = $scope.$storage.user.user;
// 		$scope.post($scope.setDomiciliosPath('registrarServicioDomicilio'), $scope.s, function(response){
// 			$state.go('domicilios');
// 		});
// 	};
// }]);
