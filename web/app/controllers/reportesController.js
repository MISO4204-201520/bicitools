app.controller('ReportesActividadController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});

app.controller('ReportesRutasFrecuentesController', function($scope, $http, $state) {
    $scope.r = {};
    $scope.r.usuario = "toroj"; //$scope.$storage.user.usuario; //
    console.log($scope.r);

    $('.input-group.date.d-inicio').datepicker({
        format: "yyyy-mm-dd",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });
    $('.input-group.date.d-fin').datepicker({
        format: "yyyy-mm-dd",
        endDate: "today",
        clearBtn: true,
        //language: "es",
        orientation: "bottom auto"
    });

    $scope.map = { center: { latitude: 4.603063, longitude:-74.064863 }, zoom: 15 };

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
    $scope.descargar = function(){
        $scope.d = $scope.r;
        $scope.d.tipoArchivo = "1";
        $scope.d.tipoReporte = "1";
        $scope.d.rutaArchivo = "C:\\Users\\Jorge\\Downloads";

        $scope.post($scope.setReportsPath('reporteLocal'), $scope.d, function(response){
            console.log(response);
        });
    };
    $scope.submit = function(){
        $scope.post($scope.setReportsPath('consultarRutasFrecuentes'), $scope.r, function(response){
            $scope.datos = response.data.datos;
        });
    };
});

app.controller('ReportesNotificacionesController', function($scope, $http, $state) {
    $scope.submit = function(){
        // $scope.post($scope.setDomiciliosPath('inscribirDomiciliario'), $scope.d, function(response){
        // 	$state.go('domicilios');
        // });
    };
});
