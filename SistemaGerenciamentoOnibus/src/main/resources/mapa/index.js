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
function randomWalk(){
    let req = new XMLHttpRequest();

    req.onreadystatechange = () => {
    if (req.readyState == XMLHttpRequest.DONE) {
        console.log(req.responseText);
    }
   };


    $.getJSON('https://api.jsonbin.io/b/6255c2fa21e89024ee8b8f35', function(json1) {


    jsonStr = JSON.stringify(json1);

    for(var i in json1['marcadores']){
      
      var random = (Math.random() * (0.001 - 0.00001) + 0.00001).toFixed(4);
        random *= Math.round(Math.random()) ? 1 : -1;
    	var string1 = json1['marcadores'][i].lat;
        var string2 = json1['marcadores'][i].lng;
      string1 = +random + +string1;
      string2 =  +random + +string2
      json1['marcadores'][i].lat = string1.toString();
      json1['marcadores'][i].lng = string2.toString();

    }
    req.open("PUT", "https://api.jsonbin.io/v3/b/6255c2fa21e89024ee8b8f35", true);
    req.setRequestHeader("Content-Type", "application/json");
    req.setRequestHeader("X-Master-Key", "$2b$10$I/.wsfiIExM9YArP5Hz55uQc5L.0l80Cb4Nt865TxeT39uPSd4Q5S");
    jsonStr = JSON.stringify(json1);

    req.send(jsonStr);
  
     });
}
  
function showMarkers(){
        $.getJSON('https://api.jsonbin.io/b/6255c2fa21e89024ee8b8f35', function(json1) {


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
                "<b>Ônibus: </b>" + obj.placa +
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

