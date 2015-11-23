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

app.controller('listaAmigosController', function($scope, $http, $state) {
    $scope.amigos = [
        {
                nombres: "Johny",
                apellidos: "Toro",
                bloqueado: false,
                image: "https://github.com/Levaneng/line-fact/raw/master/Miso.Fabricas.Principal.Web/Images/Jhony-sq.png"
        },
        {
                nombres: "Ludwing",
                apellidos: "Badillo",
                bloqueado: false,
                image: "https://raw.githubusercontent.com/Levaneng/line-fact/master/Miso.Fabricas.Principal.Web/Images/Ludwing-sq.png"
        },
        {
                nombres: "Pedro",
                apellidos: "Pineda",
                bloqueado: false,
                image: "https://raw.githubusercontent.com/Levaneng/line-fact/master/Miso.Fabricas.Principal.Web/Images/Pedro-sq.png"
        },
        {
                nombres: "Taidy",
                apellidos: "Marrugo",
                bloqueado: false,
                image: "https://raw.githubusercontent.com/Levaneng/line-fact/master/Miso.Fabricas.Principal.Web/Images/Taidy-sq.png"
        }
    ];

    $scope.bloquear = function(a){
        a.bloqueado = true;
        $scope.showSuccessAlert(a.nombres + ' ' + a.apellidos + ' ha sido bloqueado exitósamente');
    }

    $scope.eliminar = function(a, i){
        $scope.amigos.splice(i, 1);
        $scope.showSuccessAlert(a.nombres + ' ' + a.apellidos + ' ha sido eliminado exitósamente');
    }
});


app.controller('buscarAmigosController', function($scope, $http, $state) {
    $scope.amigos = [
        {
                nombres: "Juan Manuel",
                apellidos: "Acuña",
                bloqueado: false,
                image: "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xal1/v/t1.0-1/p160x160/12079276_10153603750395758_3318838087996862420_n.jpg?oh=f57d14c8b1aa476b3f919768b82bf520&oe=56EC85D2&__gda__=1458407520_87ccfa4c4bae2f6cb8a6842b2c9683e1"
        },
        {
                nombres: "Ronnall",
                apellidos: "Castro",
                bloqueado: false,
                image: "../images/user1.jpg"
        },
        {
                nombres: "Henry",
                apellidos: "Luque",
                bloqueado: false,
                image: "../images/user1.jpg"
        },
        {
                nombres: "Camilo",
                apellidos: "Vanegas",
                bloqueado: false,
                image: "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xtf1/v/t1.0-1/p160x160/10449893_10206418091714218_6200459834496026541_n.jpg?oh=d73bcc8d8c84c89416467125580aec37&oe=56F65786&__gda__=1454338576_8c8f4dabe0541a66693a4ae2da24829e"
        },
        {
                nombres: "Andres",
                apellidos: "Zerrate",
                bloqueado: false,
                image: "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpt1/v/t1.0-1/p160x160/12187807_10208003162017123_8462211656525346143_n.jpg?oh=05c7e45dfb9326cae9ce61adfabb7b5b&oe=56FAEB12&__gda__=1458583710_0c953c88f45ee50d2c7ffc905ff71788"
        },
        {
                nombres: "Natalia",
                apellidos: "Trujillo",
                bloqueado: false,
                image: "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xta1/v/t1.0-1/p160x160/12191693_10153699272989154_3585668772032327908_n.jpg?oh=4a12782e18dcf6ba2a12f872075edcb6&oe=56FB0B81&__gda__=1457874686_c95f4c89c35f986871d34edc5e54df42"
        },
        {
                nombres: "Andres",
                apellidos: "Ramirez",
                bloqueado: false,
                image: "../images/user1.jpg"
        }
    ];
});
