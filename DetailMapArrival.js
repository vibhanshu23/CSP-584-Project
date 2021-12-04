$("#btnGetNearestData").click(function () {
  callPostForNearestData();
});


function callPostForNearestData() {
  $.ajax({
    url: "CTAUIViewTrainLinesOnMap",
    type: "POST",
    data: "{}",
    success: function (msg) {
      console.log("success ajax call;",msg);
      var dataArray = $.parseJSON(msg);
      // var userLat = parsedData["userLat"];
      // var userLong = parsedData["userLong"];
      
      
      var locations = [];
      for(i=0; i < dataArray.length;i++)
      {
        console.log('lat' + dataArray[i]["location"]["coordinates"][0]);
        console.log('long' + dataArray[i]["location"]["coordinates"][1]);
        var some = (dataArray[i]["station_name"]);
        console.log('Marker Name' + dataArray[i]["location"]["coordinates"][1]);
        locations.push([some, dataArray[i]["location"]["coordinates"][1], dataArray[i]["location"]["coordinates"][0], i+1]);
        // locations.push([dataArray[i]["stop_name"], dataArray[i]["location"]["coordinates"][1], dataArray[i]["location"]["coordinates"][0], i+1]);
      }
      createDataTable(locations);
    },
    error: function () {
      console.log("error occurred while making ajax call;");
    },
  });
}

function createDataTable(locations) {
  
  var map;
  // navigator.geolocation.getCurrentPosition(function(position) {
    console.log()
    map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: new google.maps.LatLng(locations[0][1], locations[0][2]),
      // center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
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
  // }, function(error) {
  //   alert('Error occurred. Error code: ' + error.code);         
  // },{timeout:5000});
  
}


window.onload = callPostForNearestData;

