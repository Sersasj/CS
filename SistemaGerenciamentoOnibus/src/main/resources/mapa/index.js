
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
        
    $.getJSON('https://api.jsonbin.io/b/6250cfcfd20ace068f95aa94', function(json1) {


    for(var i = 0; i < json1.marcadores.length; i++){
        var obj = json1.marcadores[i];
        var latLng = new google.maps.LatLng(obj.lat, obj.lng);

        new google.maps.Marker({
        title: obj.motorista,
        position: latLng,
        icon: "./icons/front-of-bus.png",
        map: map
        });       
        
    }
    });
         
   
     
    // Texto no marcador
    const contentString =
      '<div id="content">' +
      '<div id="siteNotice">' +
      "</div>" +
      '<h1 id="firstHeading" class="firstHeading">Uluru</h1>' +
      '<div id="bodyContent">' +
      "<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large " +
      "sandstone rock formation in the southern part of the " +
      "Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) " +
      "south west of the nearest large town, Alice Springs; 450&#160;km " +
      "(280&#160;mi) by road. Kata Tjuta and Uluru are the two major " +
      "features of the Uluru - Kata Tjuta National Park. Uluru is " +
      "sacred to the Pitjantjatjara and Yankunytjatjara, the " +
      "Aboriginal people of the area. It has many springs, waterholes, " +
      "rock caves and ancient paintings. Uluru is listed as a World " +
      "Heritage Site.</p>" +
      '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">' +
      "https://en.wikipedia.org/w/index.php?title=Uluru</a> " +
      "(last visited June 22, 2009).</p>" +
      "</div>" +
      "</div>";
    const infowindow = new google.maps.InfoWindow({
      content: contentString
    });
    marker.addListener("mouseover", () => {
      infowindow.open({
        anchor: marker,
        map,
        shouldFocus: false
      });
    });
    marker.addListener("mouseout", () => {
      infowindow.close();
    });
  }

function sendLocalization(placa, linha, motorista, cpf, lat, lng){
 

    let req = new XMLHttpRequest();

req.onreadystatechange = () => {
  if (req.readyState == XMLHttpRequest.DONE) {
    console.log(req.responseText);
  }
   };


    $.getJSON('https://api.jsonbin.io/b/624f7df5d8a4cc06909d94b7', function(json1) {

    json1['marcadores'].push({"placa": placa, "linha": linha, "motorista" : motorista, "cpf": cpf, "lat": lat, "lng": lng, "icon":"./icons/front-of-bus.png"});
    jsonStr = JSON.stringify(json1);

    req.open("PUT", "https://api.jsonbin.io/v3/b/624f7df5d8a4cc06909d94b7", true);
    req.setRequestHeader("Content-Type", "application/json");
    req.setRequestHeader("X-Master-Key", "$2b$10$I/.wsfiIExM9YArP5Hz55uQc5L.0l80Cb4Nt865TxeT39uPSd4Q5S");
    console.log(json1);
    req.send(jsonStr);
  
     });
}

