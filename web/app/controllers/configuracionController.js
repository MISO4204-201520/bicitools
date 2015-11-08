app.controller("menuConfiguracionController", function($scope, $http, $state) {
	//$scope.esUsuarioProveedor = false;
	var u = {
		usuario: 'toroj'//$scope.$storage.user.usuario
	};

	$scope.post($scope.setBikeConfPath('validarRolProveedor'), u, function(response){
		//$scope.esUsuarioProveedor = response.data.datos[0].esProveedor;
		console.log(response.data.datos[0].esProveedor);
		$scope.val = response.data.datos[0].esProveedor === "true" ? true : undefined;
		console.log($scope.val);
	});

	$scope.esUsuarioProveedor = function(){
		console.log('METHOD');
	}
});

app.controller('armarBiciController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.compra = {
		usuario: $scope.$storage.user.usuario,
		config: [
			{
				marco: "0",
				tijera: "0",
				ruedas: "0",
				frenos: "0",
				manubrio: "0",
				sillin: "0",
				cambios: "0",
				platoDelantero: "0",
				platoTrasero: "0",
				pedales: "0",
				cadena: "0"
			}
		]
	};
	$scope.calcularPrecio = function(){
		var invocarServicio = false;
		$scope.partes.forEach(function(e){
			if(e.parteSeleccionada){
				invocarServicio = true;
				return;
			}
		});
		//console.log($scope.compra.config);
		// $scope.compra.config[0].forEach(function(e){
		// 	console.log(e);
		// });

		if(invocarServicio === true){
			console.log('TRUE');
			console.log("============================================");
			console.log($scope.compra);
			$scope.compra.consulta = "1";
			$scope.post($scope.setBikeConfPath('verificarConfiguracion'), $scope.compra, function(response){
				$scope.valorTotal = response.data.datos[0].valor;
			});
		}
		else{
			console.log('FALSE');
			$scope.valorTotal = "0";
		}
	};
	$scope.valorTotal = "0";
	//$scope.calcularPrecio();

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
			id: 7,
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
			id: 10,
			alias: "pedales",
			nombre: "Pedales",
			plural: "Pedales"
		},
		{
			id: 11,
			alias: "cadena",
			nombre: "Cadena",
			plural: "Cadenas"
		},
		{
			id: 8,
			alias: "platoDelantero",
			nombre: "Plato delantero",
			plural: "Platos delanteros"
		},
		{
			id: 9,
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
			data: {
				usuario: $scope.$storage.user.usuario,
				tipoParte: parte.alias
			},
			titulo: parte.plural,
			nombre: parte.nombre,
			id: i
		};
		$scope.modal.partes = [];
		// $scope.modal.partes = [
		// 	{
		// 		idParte: 3,
		// 		nombre: "Marco el gato consectetur adipisicing elit",
		// 		descripcion: "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
		// 		valor: "190000",
		// 		imagen: "http://lorempixel.com/128/128/transport/1"
		// 	},
		// 	{
		// 		idParte: 3,
		// 		nombre: "Marco el perro",
		// 		descripcion: "",
		// 		valor: "30000",
		// 		imagen: "http://lorempixel.com/128/128/transport/2"
		// 	},
		// 	{
		// 		idParte: 3,
		// 		nombre: "Marco mi pez",
		// 		descripcion: "Consectetur adipisicing elit",
		// 		valor: "280000",
		// 		imagen: "http://lorempixel.com/128/128/transport/3"
		// 	}
		// ];
		$scope.post($scope.setBikeConfPath('consultarPartexTipo'), $scope.modal.data, function(response){
			console.log(response.data.datos);
			$scope.modal.partes = response.data.datos;
		});
	};
	$scope.removerParte = function(parte){
		parte.parteSeleccionada = null;
		//$scope.compra.config[0][$scope.modal.data.tipoParte] = "0";
		$scope.compra.config[0][parte.alias] = "0";
		$scope.calcularPrecio();
	};

	$scope.seleccionarParte = function(parte){
		$scope.partes[$scope.modal.id].parteSeleccionada = parte;
		$scope.compra.config[0][$scope.modal.data.tipoParte] = parte.idParte;
		$scope.calcularPrecio();
	};
	$scope.submit = function(){
		$scope.compra.consulta = "0";
		$scope.post($scope.setBikeConfPath('verificarConfiguracion'), $scope.compra, function(response){
			var m = "Compra realizada. Id de compra: " + response.data.datos[0].idOrden;
			$scope.showSuccessAlert(m);
			$state.go('configurador');
		});
	};
}]);

app.controller('ordenesController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.post($scope.setBikeConfPath('misOrdenes'), { usuario: $scope.$storage.user.usuario}, function(response){
		console.log(response.data.datos);
		$scope.datos = response.data.datos;
	});

	$scope.verDetalles = function(id){
		$scope.d = {
			usuario: "toroj", //$scope.$storage.user.usuario
			idOrden: id
		}
		$scope.post($scope.setBikeConfPath('detalleOrden'), $scope.d, function(response){
			console.log(response.data.datos[0].partes);
			$scope.detalles = response.data.datos[0].partes;
		});
	};
}]);

app.controller('parteBiciController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.u = {};
	$scope.u.imagen = "";

	$scope.tiposParte = [
		{ id: "marco", value: "Marco" },
		{ id: "tijera", value: "Tijera" },
		{ id: "ruedas", value: "Ruedas" },
		{ id: "frenos", value: "Frenos" },
		{ id: "manubrio", value: "Manubrio" },
		{ id: "sillin", value: "Sillin" },
		{ id: "cambios", value: "Cambios" },
		{ id: "platodelantero", value: "Plato delantero" },
		{ id: "platotrasero", value: "Plato trasero" },
		{ id: "pedales", value: "Pedales" },
		{ id: "cadena", value: "Cadena" },
	];
	$scope.tiposParte.label = function(o){ return o.value; };
	$scope.tiposParte.track = function(o){ return o.id; };

	$scope.submit = function(){
		$scope.u.usuario = $scope.$storage.user.usuario;
		console.log($scope.u);
		$scope.post($scope.setBikeConfPath('insertarProducto'), $scope.u, function(response){
			var m = "Parte ingresada exitósamente";
			$scope.showSuccessAlert(m);
			$state.go('configurador');
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
