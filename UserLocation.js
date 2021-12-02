// function callPost() {
//   $.ajax({
//     url: "DetailMap",
//     type: "POST",
//     data: "{}",
//     success: function (msg) {
//       console.log("success ajax call;", msg);
//       createDataTable(msg);
//     },
//     error: function () {
//       console.log("error occurred while making ajax call;");
//     },
//   });
// }

// function createDataTable(jsonData) {
//   var parsedData = $.parseJSON(jsonData);
//   var locations = [
//     ['Bondi Beach', -33.890542, 151.274856, 4],
//     ['Coogee Beach', -33.923036, 151.259052, 5],
//     ['Cronulla Beach', -34.028249, 151.157507, 3],
//     ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
//     ['Maroubra Beach', -33.950198, 151.259302, 1]
//   ];
//   for(i=0; i < parsedData.length;i++)
//   {
//     locations.push([parsedData[i]["stopName"], parsedData[i]["stoplat"], parsedData[i]["stoplon"], i+1]);
//   }
//   var map = new google.maps.Map(document.getElementById('map'), {
//     zoom: 10,
//     center: new google.maps.LatLng(-33.92, 151.25),
//     mapTypeId: google.maps.MapTypeId.ROADMAP
//   });

//   var infowindow = new google.maps.InfoWindow();

//   var marker, i;

//   for (i = 0; i < locations.length; i++) {
//     marker = new google.maps.Marker({
//       position: new google.maps.LatLng(locations[i][1], locations[i][2]),
//       map: map
//     });

//     google.maps.event.addListener(marker, 'click', (function (marker, i) {
//       return function () {
//         infowindow.setContent(locations[i][0]);
//         infowindow.open(map, marker);
//       }
//     })(marker, i));
//   }
// }
var x = document.getElementById("locationForm");





function getLocation() {
  if (navigator.geolocation) {
    position = navigator.geolocation.getCurrentPosition();
    $("#locationLat").value(position.coords.latitude); 
    $("#locationLon").value(position.coords.longitude); 
    
    // Submit the form using javascript
    // var form = document.getElementById("vehicles");
    form.submit();
    
    //jQuery
    $("#locationForm").submit(); 
    
    x.innerHTML = "Latitude: " + position.coords.latitude + 
    "<br>Longitude: " + position.coords.longitude; 
  } else {
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
  $("#locationLat").value(position.coords.latitude); 
  $("#locationLon").value(position.coords.longitude); 
  
  // Submit the form using javascript
  // var form = document.getElementById("vehicles");
  form.submit();
  
  //jQuery
  $("#locationForm").submit(); 
  
  x.innerHTML = "Latitude: " + position.coords.latitude + 
  "<br>Longitude: " + position.coords.longitude; 
}
// function showPosition(position) {
//   var latlon = position.coords.latitude + "," + position.coords.longitude;

//   var img_url = "https://maps.googleapis.com/maps/api/staticmap?center=
//   "+latlon+"&zoom=14&size=400x300&sensor=false&key=YOUR_KEY";

//   document.getElementById("mapholder").innerHTML = "<img src='"+img_url+"'>";
// }
function getUserLocation()
{
  
  console.log('lat' + document.getElementById("locationLat").value);
  console.log('long '+document.getElementById("locationLon").value);
  
  
  // getLocation();
  // // var form = document.getElementsByName("locationForm");
  
  
  // // var hiddenSelectedVehicles = document.getElementById("location");
  // // hiddenSelectedVehicles.value = selectedVehicleTypes.join(",");
  
  // // jQuery
  // $("#locationLat").value(selectedVehicleTypes.join(",")); 
  // $("#locationLon").value(selectedVehicleTypes.join(",")); 
  
  // // Submit the form using javascript
  // // var form = document.getElementById("vehicles");
  // form.submit();
  
  // //jQuery
  //  $("#locationForm").submit(); 
}




// window.onload = callPost;

window.onload = function() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      // alert('it works' + position.coords.latitude + position.coords.longitude);
      
      // document.getElementById("locationLat").value = position.coords.latitude
      // document.getElementById("locationLon").value = position.coords.longitude
      
      
      $.ajax({
        url: "Home",
        type: "POST",
        data: {'locationLon': position.coords.longitude, 'locationLat': position.coords.latitude},
        
      });
      
      // var hiddenSelectedVehicles = document.getElementById("locationLat");
      // hiddenSelectedVehicles.value = selectedVehicleTypes.join(",");
      
      
      // $("#locationLat").value(position.coords.latitude); 
      // $("#locationLon").value(position.coords.longitude); 
      
      // Submit the form using javascript
      // var form = document.getElementById("locationForm");
      // form.submit();
      
      //jQuery
      // $("#locationForm").submit(); 
      
      // x.innerHTML = "Latitude: " + position.coords.latitude + 
      // "<br>Longitude: " + position.coords.longitude; 
    }, function(error) {
      alert('Error occurred. Error code: ' + error.code);         
    },{timeout:5000});
  }else{
    alert('no geolocation support');
  }
};