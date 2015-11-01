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
