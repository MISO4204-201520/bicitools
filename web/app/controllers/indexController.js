app.controller('indexController', ['$scope', '$http', '$state', '$localStorage', function($scope, $http, $state, $localStorage) {
    $scope.$storage = $localStorage;
    $('#alerta').hide();

    $scope.featureExists = function(feature){
        return variabilidad.indexOf(feature) > -1;
    }

    var domain = "http://localhost:8080/";  //"http://192.168.0.11:8080/"; //

    $scope.setUsersPath = function(path) {
        return domain + "bicitoolsgu/webresources/gestionusuario/" + path;
    }

    $scope.setReportsPath = function(path) {
        return domain + "bicitoolsRe/serviciosRest/reportes/" + path;
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

    $scope.setBikeConfPath = function(path) {
        return domain + "bicitoolsMi/serviciosRest/miBici/" + path;
    }



    $scope.showSuccessAlert = function(mensaje){
        $scope.alert = {
            tipo: 'alert-success',
            mensaje: mensaje
        };
        $('#alerta').show();
        $('#alerta').fadeTo(3250, 500).slideUp(500, function(){
            $('#alerta').hide();
        });
    }

    function invokeService(promise, action){
        return promise.then(
            function(response) {
                console.log('\nData:');
                console.log(response.data);

                if(response.data.codigo === 0 || response.data.code === 0){
                    action(response);
                    // $scope.alert = {
                    //     tipo: 'alert-success',
                    //     mensaje: response.data.descripcion
                    // }
                } else {
                    console.log('Código diferente a cero - ' + response.descripcion);
                    // $scope.alert = {
                    //     tipo: 'alert-warning',
                    //     mensaje: response.descripcion
                    // }
                }
                // $('#alerta').show();
                // $('#alerta').fadeTo(2000, 500).slideUp(500, function(){
                //     $('#alerta').hide();
                // });
            }, function(response){
                console.log('SERVICE ERROR');
                console.log(response);
                //$scope.error = "Error en la invocación";
                //window.location.hash = '#body';
                // $scope.alert = {
                //     tipo: 'alert-danger',
                //     mensaje: "Error en la invocación"
                // };
                // $('#alerta').show();
                // $('#alerta').fadeTo(2000, 500).slideUp(500, function(){
                //     $('#alerta').hide();
                //     //$('#alerta').alert('close');
                // });
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


        $scope.r = {};
        $scope.r.usuario = "toroj"; //$scope.$storage.user.usuario; //
        $scope.map = { center: { latitude: 4.603063, longitude:-74.064863 }, zoom: 15 };

        $scope.map.markers = [
            {
                id: Date.now(),
                coords: {
                    latitude: 4.603063,
                    longitude: -74.064863
                }
            }
        ];

        $scope.mapa = true;
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


        $scope.r = {
            usuario: "toroj",
            inicio: "",
            fin: ""
        }
        $scope.post($scope.setReportsPath('consultarRecorridosUsuario'), $scope.r, function(response){
            $scope.datos = response.data.datos;
        });


        if($scope.$storage.user === undefined){
            $scope.$storage.user = {
                id: 0,
                nombres: 'Jorge',
                apellidos: 'Castro'
            }
        }

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

        $scope.iniciarRecorrido = function(){
            $scope.recorridoIniciado = true;
            $scope.mostrarDatosRecorrido = false;
        }

        $scope.detenerRecorrido = function(){
            $scope.recorridoIniciado = false;
            $scope.mostrarDatosRecorrido = true;
        }
    });

    app.controller('MenuController', function($scope, $http, $state) {
        console.log('menu controller');
        $scope.logout = function(){
            FB.logout(function(response) {
                appId: '471697476347037'
                // user is now logged out
            });
            $scope.$storage.user = undefined;
            $state.go('intro');
        }
    });
