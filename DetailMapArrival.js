function callPost() {
  $.ajax({
    url: "CTAUIViewTrainLinesOnMap",
    type: "POST",
    data: "{}",
    success: function (msg) {
      console.log("success ajax call;",msg);
      createDataTable(msg);
    },
    error: function () {
      console.log("error occurred while making ajax call;");
    },
  });
}

function createDataTable(jsonData) {
  var parsedData = $.parseJSON(jsonData);
  
  var dataArray = parsedData;
  // var userLat = parsedData["userLat"];
  // var userLong = parsedData["userLong"];
  
  
  var locations = [];
  for(i=0; i < dataArray.length;i++)
  {
    
    console.log('lat' + dataArray[i]["location"]["coordinates"][0]);
    console.log('long' + dataArray[i]["location"]["coordinates"][1]);
    
    var some = parseFloat((dataArray[i]["distance"]));
    locations.push([some, dataArray[i]["location"]["coordinates"][1], dataArray[i]["location"]["coordinates"][0], i+1]);
    // locations.push([dataArray[i]["stop_name"], dataArray[i]["location"]["coordinates"][1], dataArray[i]["location"]["coordinates"][0], i+1]);

    

  }
  var map;
  navigator.geolocation.getCurrentPosition(function(position) {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 10,
    center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  var infowindow = new google.maps.InfoWindow();
  
  var marker, i;
  
  for (i = 0; i < locations.length; i++) {
    marker = new google.maps.Marker({
      position: new google.maps.LatLng(locations[i][1], locations[i][2]),
      map: map
    });
    
    google.maps.event.addListener(marker, 'click', (function (marker, i) {
      return function () {
        infowindow.setContent(locations[i][0]);
        infowindow.open(map, marker);
      }
    })(marker, i));
  }
  
  // infoWindow = new google.maps.InfoWindow();
  
  // const locationButton = document.createElement("button");
  
  // locationButton.textContent = "Pan to Current Location";
  // locationButton.classList.add("custom-map-control-button");
  // map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  // locationButton.addEventListener("click", () => {
  //   // Try HTML5 geolocation.
  //   if (navigator.geolocation) {
  //     navigator.geolocation.getCurrentPosition(
  //       (position) => {
  //         const pos = {
  //           lat: position.coords.latitude,
  //           lng: position.coords.longitude,
  //         };
          
  //         infoWindow.setPosition(pos);
  //         infoWindow.setContent("Location found.");
  //         infoWindow.open(map);
  //         map.setCenter(pos);
  //       },
  //       () => {
  //         handleLocationError(true, infoWindow, map.getCenter());
  //       }
  //       );
  //     } else {
  //       // Browser doesn't support Geolocation
  //       handleLocationError(false, infoWindow, map.getCenter());
  //     }
  //   });
    
    }, function(error) {
      alert('Error occurred. Error code: ' + error.code);         
    },{timeout:5000});
    
    
    
    
  }
  
  
  
  
  
  function initMap() {
    
    
    
    
  }
  
  window.onload = callPost;