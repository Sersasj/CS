
let map;
//var json = "https://api.jsonbin.io/b/624e39c74c42ee0bc564d062";
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
    //$.getJSON("https://api.jsonbin.io/b/624e39c74c42ee0bc564d062", function(json1) {
        
 $.getJSON('https://api.jsonbin.io/b/624e39c74c42ee0bc564d062', function(json1) {
  
        // JSON result in `data` variable
    
//   var json1 = {
//        "marcadores" : [
//          {
//            "title": "DIN",
//            "lat": -23.405609,
//            "lng": -51.936445,
//            "icon": "./icons/front-of-bus.png"
//        },
//        {
//            "title": "D67",
//            "lat": -23.404939,
//            "lng":  -51.936602,
//            "icon": "./icons/front-of-bus.png"
//        }
//       ]
//    };
//    
//  
    for(var i = 0; i < json1.marcadores.length; i++){
        var obj = json1.marcadores[i];
        var latLng = new google.maps.LatLng(obj.lat, obj.lng);

        new google.maps.Marker({
        title: obj.title,
        position: latLng,
        icon: "./icons/front-of-bus.png",
        map: map
        });       
        
    }
    });
         
    // $.each(json1.marcadores, function(key,data) {
    // alert("aa");
//    var latLng = new google.maps.LatLng(data.lat, data.lng);
//
//    var marker = new google.maps.Marker({
//        title:      data.title,        
//        position:   latLng,
//        icon: "./icons/front-of-bus.png",
//        map:        map
//    });   
//     });
//    
    // Marcador de Onibus
    
//    const marker = new google.maps.Marker({
//        title: "DIN",
//        position: {lat: -23.405609, lng: -51.936445},
//        icon: "./icons/front-of-bus.png",
//        map: map
//     });
//    new google.maps.Marker({
//        title: "D67",
//        position: {lat: -23.404939, lng: -51.936602},
//        icon: "./icons/front-of-bus.png",
//        map: map
//     });
//     
     
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
  

