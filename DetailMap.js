function callPost() {
  $.ajax({
    url: "DetailMap",
    type: "POST",
    data: "{}",
    success: function (msg) {
      console.log("success ajax call;", msg);
      createDataTable(msg);
    },
    error: function () {
      console.log("error occurred while making ajax call");  
    },
  });
}





function createDataTable(msg) {  
  var parsedData = $.parseJSON(msg);  
  
  var map = new google.maps.Map(document.getElementById('map'), {  
    zoom: 13,  
    center: new google.maps.LatLng(parsedData[0].stoplat, parsedData[0].stoplon),  
    mapTypeId: google.maps.MapTypeId.ROADMAP  
  });  
  
  var infowindow = new google.maps.InfoWindow();  
  
  var marker, i;  
  
  for (i = 0; i < parsedData.length; i++) {  
    marker = new google.maps.Marker({  
      position: new google.maps.LatLng(parsedData[i].stoplat, parsedData[i].stoplon),  
      map: map  
    });  
    
    google.maps.event.addListener(marker, 'click', (function (marker, i) {  
      return function () {  
        infowindow.setContent(parsedData[i].stopName);  
        infowindow.open(map, marker);  
      }  
    })(marker, i));  
  }  
}  





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

window.onload = callPost;