app.controller('ReportesActividadController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('ReportesRutasFrecuentesController', function($scope, $http, $state) {

    $('.input-group.date.d-inicio').datepicker({
        format: "yyyy-mm-dd",
        startDate: "today",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });
    $('.input-group.date.d-fin').datepicker({
        format: "yyyy-mm-dd",
        startDate: "today",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });

    $scope.map = { center: { latitude: 4.603063, longitude:-74.064863 }, zoom: 15 };
    // $scope.datos = [
    //     {
    //         nombre: "casa trabajo",
    //         fechaHora: "Tue Oct 27 21:35:16 COT 2015",
    //         distancia: "13.9 km",
    //         tiempo: "33 mins",
    //         lugares: [
    //             {
    //                 latitud: "4.634992",
    //                 longitud: "-74.080758"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080858"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080966"
    //             }
    //         ]
    //     },
    //     {
    //         nombre: "casa trabajo",
    //         fechaHora: "Tue Oct 27 21:35:16 COT 2015",
    //         distancia: "13.9 km",
    //         tiempo: "33 mins",
    //         lugares: [
    //             {
    //                 latitud: "4.734992",
    //                 longitud: "-74.080758"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080858"
    //             },
    //             {
    //                 latitud: "4.56113",
    //                 longitud: "-74.080966"
    //             }
    //         ]
    //     }
    // ]

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
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('ReportesNotificacionesController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});
