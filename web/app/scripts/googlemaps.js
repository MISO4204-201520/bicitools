 function initialize() {
        var mapCanvas = document.getElementById('map');
        var mapOptions = {
          center: new google.maps.LatLng(4.603063, -74.064863),
          zoom: 18,
          mapTypeId: google.maps.MapTypeId.ROADMAP
      }
      var map = new google.maps.Map(mapCanvas, mapOptions);
  }
  //google.maps.event.addDomListener(window, 'load', initialize);
