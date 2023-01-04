<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
        <title>City Map finder</title>
        <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/0/05/PricewaterhouseCoopers_Logo.svg"/>
        <script src='https://api.mapbox.com/mapbox-gl-js/v2.11.0/mapbox-gl.js'></script>
        <link href='https://api.mapbox.com/mapbox-gl-js/v2.11.0/mapbox-gl.css' rel='stylesheet' />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div id='map' style='height: 800px;' class="rounded mx-3 my-3 d-block"></div>
            <script>
            mapboxgl.accessToken = 'pk.eyJ1IjoiYXdzYWhtYWQ3NTEiLCJhIjoiY2xjaHdlejl5MDJ2YjNwbGczYmQyYTRrZSJ9.o20ut4uYrfDqsHtTybeoJg';
            const map = new mapboxgl.Map({
            container: 'map', // container ID
            style: 'mapbox://styles/mapbox/streets-v12', // style URL
            center: [${lng}, ${lat}], // starting position [lng, lat]
            zoom: 12, // starting zoom
            });

            </script>
        <hr class="mb-4 solid">
        <form class="form-inline justify-content-center"  action="/findCity" method="get">
              <br><label for="city" class="h5 mx-1">  Enter City:</label >
              <input type="text" id="city" name="city" value="Dubai" class="mx-2">
              <br><input class="btn btn-primary" type="submit" value="Search" class="mx-2">
        </form>
        <p class=" h5 text-danger text-center">${error}</p>

    </body>

</html>