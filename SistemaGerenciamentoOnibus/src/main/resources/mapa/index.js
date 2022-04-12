let map;
function initMap() {
    
    // Propriedades do mapa
    map = new google.maps.Map(document.getElementById("map"), {
        mapId: "f65341f68a260442",
        // Centro está no terminal
        center: { lat: -23.418590, lng: -51.938117 },
        zoom: 13,
        mapTypeControl: false,
        streetViewControl: false
    });
        

    showMarkers();
   

  }
  function showMarkers(){
        $.getJSON('https://api.jsonbin.io/b/6255c2fa21e89024ee8b8f35', function(json1) {


    for(var i = 0; i < json1.marcadores.length; i++){
        var obj = json1.marcadores[i];
        var latLng = new google.maps.LatLng(obj.lat, obj.lng);

        var marker = new google.maps.Marker({
        title: obj.motorista,
        position: latLng,
        icon: "./icons/front-of-bus.png",
        map: map
        }); 
        
        var infowindow = new google.maps.InfoWindow()
        var content = "Placa: " + obj.placa + " Linha: " + obj.linha;
        google.maps.event.addListener(marker,'mouseover', (function(marker,content,infowindow){ 
            return function() {
                infowindow.setContent(content);
                infowindow.open(map,marker);
        };
        })(marker, content, infowindow));
        /*
        google.maps.event.addListener(marker,'mouseout', (function(marker,infowindow){
            return function(){
                infowindow.close();
            };

        })(marker, infowindow));
        
        marker.addListener("mouseout", () => {
            infowindow.close();
        })
        */
        
    }
    });
  }
