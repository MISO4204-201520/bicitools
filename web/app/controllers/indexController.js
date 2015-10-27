app.controller('indexController', ['$scope', '$http', '$state', '$localStorage', function($scope, $http, $state, $localStorage) {
    $scope.$storage = $localStorage;

    var domain = "http://192.168.0.11:8080/"; //"http://localhost:8080/";

    $scope.setUsersPath = function(path) {
        return domain + "bicitoolsgu/webresources/gestionusuario/" + path;
    }

    $scope.setRoutesPath = function(path) {
        return domain + "bicitoolsRU/webresources/myresource/" + path;
    }

    $scope.setCommunicationPath = function(path) {
        return domain + "bicitoolsco/serviciosRest/" + path;
    }

    $scope.setDomiciliosPath = function(path) {
        return domain + "bicitoolsdo/serviciosRest/domicilios/" + path;
    }

    function invokeService(promise, action){
        return promise.then(
            function(response) {
                console.log('\nData:');
                console.log(response.data);

                if(response.data.codigo === 0 || response.data.code === 0){
                    action(response);
                } else {
                    console.log('Código diferente a cero - ' + response.descripcion);
                    //$scope.error = "Error: " + response.data.descripcion;
                }
                alert(response.data.descripcion);
            }, function(response){
                console.log('SERVICE ERROR');
                console.log(response);
                //$scope.error = "Error en la invocación";
                //window.location.hash = '#body';
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


app.controller('indexController', ['$scope', '$http', '$state', '$localStorage', function($scope, $http, $state, $localStorage) {
    $scope.$storage = $localStorage;

    var domain = "http://192.168.0.11:8080/"; //"http://localhost:8080/";

    $scope.setUsersPath = function(path) {
        return domain + "bicitoolsgu/webresources/gestionusuario/" + path;
    }

    $scope.setRoutesPath = function(path) {
        return domain + "bicitoolsRU/webresources/myresource/" + path;
    }

    $scope.setCommunicationPath = function(path) {
        return domain + "bicitoolsco/serviciosRest/" + path;
    }

    $scope.setDomiciliosPath = function(path) {
        return domain + "bicitoolsdo/serviciosRest/domicilios/" + path;
    }

    function invokeService(promise, action){
        return promise.then(
            function(response) {
                console.log('\nData:');
                console.log(response.data);

                if(response.data.codigo === 0 || response.data.code === 0){
                    action(response);
                } else {
                    console.log('Código diferente a cero - ' + response.descripcion);
                    //$scope.error = "Error: " + response.data.descripcion;
                }
                alert(response.data.descripcion);
            }, function(response){
                console.log('SERVICE ERROR');
                console.log(response);
                //$scope.error = "Error en la invocación";
                //window.location.hash = '#body';
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
