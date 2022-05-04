let map;
var markersArray = [];

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
        

    refresh();
   

  }
function refresh(){
    resetMarkers();
    randomWalk();
    showMarkers();
    setTimeout(refresh, 5000);
}

//refresh(); 
function resetMarkers(){
    if (markersArray) {
    for (i in markersArray) {
      markersArray[i].setMap(null);
    }
    markersArray.length = 0;
  }    
}

function calcDist(latAntiga, lngAntiga, latNova, lngNova){
  var R = 6371; // Radius of the earth in km
  var dLat = deg2rad(latNova-latAntiga);  // deg2rad below
  var dLon = deg2rad(lngNova-lngAntiga); 
  var a = 
    Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(deg2rad(latAntiga)) * Math.cos(deg2rad(latAntiga)) * 
    Math.sin(dLon/2) * Math.sin(dLon/2)
    ; 
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  var d = R * c; // Distance in km
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180);
}

function randomWalk(){
    let req = new XMLHttpRequest();

    req.onreadystatechange = () => {
    if (req.readyState == XMLHttpRequest.DONE) {
        console.log(req.responseText);
    }
   };


    $.getJSON('https://api.jsonbin.io/b/6271c19425069545a32cf906', function(json1) {


    jsonStr = JSON.stringify(json1);

    for(var i in json1['marcadores']){
      var latAntiga = json1['marcadores'][i].lat;
      var lngAntiga = json1['marcadores'][i].lng;
      var dist = json1['marcadores'][i].distancia;
      var random = (Math.random() * (0.001 - 0.00001) + 0.00001).toFixed(4);
        random *= Math.round(Math.random()) ? 1 : -1;
    	var latNova = json1['marcadores'][i].lat;
        var lngNova = json1['marcadores'][i].lng;
      latNova = +random + +latNova;
      lngNova =  +random + +lngNova;
      dist = +dist + calcDist(+latAntiga, +lngAntiga, latNova, lngNova);
      json1['marcadores'][i].distancia = dist.toString();
      json1['marcadores'][i].lat = latNova.toString();
      json1['marcadores'][i].lng = lngNova.toString();

    }
    req.open("PUT", "https://api.jsonbin.io/v3/b/6271c19425069545a32cf906", true);
    req.setRequestHeader("Content-Type", "application/json");
    req.setRequestHeader("X-Master-Key", "$2b$10$Jn.s9m9qjc8TzU4e5hT6l./50kdb39Hja59kP43K/EvGGpVwEBIY6");
    jsonStr = JSON.stringify(json1);

    req.send(jsonStr);
  
     });
}
  
function showMarkers(){
        $.getJSON('https://api.jsonbin.io/b/6271c19425069545a32cf906', function(json1) {


    for(var i = 0; i < json1.marcadores.length; i++){
        var obj = json1.marcadores[i];
        var latLng = new google.maps.LatLng(obj.lat, obj.lng);

        var marker = new google.maps.Marker({
        position: latLng,
        icon: "./icons/front-of-bus.png",
        map: map
        }); 
        +markersArray.push(marker);

        var infowindow = new google.maps.InfoWindow()
        var content = 
                '<div id="content">' +
                '<div id="motorista">' + 
                "<b> Motorista: </b>" + obj.motorista  +
                "</div>" +
                '<div id="placa">' + 
                "<b>Onibus: </b>" + obj.placa +
                "</div>" +
                '<div id="linha">' + 
                "<b>Linha: </b>" + obj.linha +
                "</div>" +
                "</div>";
        
        marker.addListener('mouseover', function() {
            infowindow.setContent(content);
            infowindow.open(map, this);
        });

        marker.addListener('mouseout', function() {
            infowindow.close();
        });
        
        /*
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

