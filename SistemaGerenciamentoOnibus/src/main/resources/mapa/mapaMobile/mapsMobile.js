/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


let map
// Initialize and add the map
function initMap() {
  var myLatlng = {  lat: -23.418590, lng: -51.938117 };
  map = new google.maps.Map(document.getElementById("map"), {
    zoom: 13,
    center: myLatlng,
    mapTypeControl: false,
    streetViewControl: false  
  });
  }





window.initMap = initMap("a");