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