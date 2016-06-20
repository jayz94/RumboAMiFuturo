
package sv.edu.ues.fia.rumboamifuturo;

/**
 * Created by carlos2 on 19/06/2016.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class fragmente_maps extends Fragment {

    View rootView;
    private MapView mMapView;
    public GoogleMap mGoogleMap;
    GPSTracker gpstracker;
    ArrayList<LatLng> mMarkerPoints;
    Button button;
    LatLng point;
    Polyline polyline;
    Double latitud, longitud;
    // SP_GetDireccionMapa spdireccion;
    public Vector<String> formatted_address = new Vector<String>();
    public String mandaLatitude, mandarlongitude;
    private SearchView searchView;
    public  Context mContext;

    public fragmente_maps(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mapas, container, false);


        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        gpstracker = new GPSTracker(getActivity());
        latitud = gpstracker.getLatitude();
        longitud = gpstracker.getLongitude();
        mGoogleMap = mMapView.getMap();
        // Initializing
        mMarkerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapView);

        // Enable MyLocation Button in the Map
        if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        mGoogleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        // LocationManager locationManager = (LocationManager) getSystemService(getActivity().LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        // String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location From GPS
        //Location location = locationManager.getLastKnownLocation(provider);

      /*  if(location!=null){
            onLocationChanged(location);
        }*/

        //locationManager.requestLocationUpdates(provider, 20000, 0, getActivity());
        point = new LatLng(gpstracker.getLatitude(), gpstracker.getLongitude());



        //LatLng origin = new LatLng(gpstracker.getLatitude(), gpstracker.getLongitude());

        LatLng dest = new LatLng(gpstracker.getLatitude(),gpstracker.getLongitude());

        MapsInitializer.initialize(getActivity());

        /**********************************************
         CAMERA POSITION
         **********************************************/
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(dest)// Sets the center of the map to
                .zoom(15)    // Sets the zoom
                .tilt(30)    // Sets the tilt of the camera to 30 degrees
                .build();    // Creates a CameraPosition from the builder
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        mMapView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                return false;
            }
        });

        mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                // TODO Auto-generated method stub
                Log.e("Lat", String.valueOf(mGoogleMap.getCameraPosition().target.latitude));
                Log.e("Long", String.valueOf(mGoogleMap.getCameraPosition().target.longitude));
                mandaLatitude = String.valueOf(mGoogleMap.getCameraPosition().target.latitude);
                mandarlongitude = String.valueOf(mGoogleMap.getCameraPosition().target.longitude);
                // SetAllMarker(mGoogleMap.getCameraPosition().target.latitude,mGoogleMap.getCameraPosition().target.longitude,"Inicio");
                // new SETLOGTask(String.valueOf(mGoogleMap.getCameraPosition().target.latitude),String.valueOf(mGoogleMap.getCameraPosition().target.longitude)).execute();
            }
        });

       /* mGoogleMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition arg0) {
				// TODO Auto-generated method stub


				  SetAllMarker(arg0.latitude,arg0.longitude,"Inicio");
	               // DirectionUrl("Destino",point, arg0);
	                new SETLOGTask(String.valueOf(arg0.latitude),String.valueOf(arg0.longitude)).execute();
			}
		});*/



        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
                //SetAllMarker(arg0.latitude,arg0.longitude,"Inicio");
                // DirectionUrl("Destino",point, arg0);
                // new SETLOGTask(String.valueOf(arg0.latitude),String.valueOf(arg0.longitude)).execute();
            }
        });


     /*   FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        /*button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), ActicityPedirCarga.class);
                startActivity(a);
            }
        });*/ /*Comentado porque no usare botones*/

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_buscar, menu);
      /*  MenuItem searchItem = menu.findItem(R.id.menu3_buscar);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        searchView.setQueryHint("Buscar…");*/
    //searchView.setSubmitButtonEnabled(true);
    //searchView.setOnQueryTextListener(this);*/


    // }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu3_buscar:


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void displayResults(String query) {

        //Cursor cursor = mDbHelper.searchByInputText((query != null ? query : "@@@@"));

    }


    public boolean onQueryTextSubmit(String query) {
        displayResults(query);
        return false;
    }


    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()){
            Log.i("newText", "" + newText);
            displayResults(newText);
        } else {

        }


        return true;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void DirectionUrl(String url, LatLng origin, LatLng dest) {
        // Getting URL to the Google Directions API
        url = getDirectionsUrl(origin, dest);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            try {
				/*cargar = new ProgressDialog(getApplicationContext());
				cargar.setMessage("Calculando ruta ...");
				cargar.setCancelable(false);
				cargar.show();*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        private String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();

                br.close();

            } catch (Exception e) {
                // Log.d("Exception while downloading url", e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

     /*   // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }*/


    /*private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            if (polyline != null) {
                Log.i("remover","remover");
                polyline.remove();
            }

            try {
                // Traversing through all the routes
                for (int i = 0; i < result.size(); i++) {
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(7);
                    lineOptions.color(Color.RED);
                }
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                Toast.makeText(getActivity(), "Conexión inválida", Toast.LENGTH_SHORT).show();
            }

            // Drawing polyline in the Google Map for the i-th route
            try {
                //mGoogleMap.addPolyline(lineOptions);
                polyline  = mGoogleMap.addPolyline(lineOptions);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                Log.e("ERROR addPolyline", "Imposible trazar ruta.");
            }

            //cargar.dismiss();
        }
    }*/

        public class InternetTask extends AsyncTask<Void, Void, Void> {
            String url;
            LatLng origin, dest;

            public InternetTask(String url, LatLng origin, LatLng dest) {
                this.url = url;
                this.origin = origin;
                this.dest = dest;
            }

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub

                DirectionUrl(url, origin, dest);

                return null;
            }

        }

        public void SetMarker(double latitude, double longitude, String title) {
            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.piker_verde))
                            //.snippet(getResources().getString(R.string.hasclick))
                    .title(title));


            mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                /*Intent i = new Intent(getApplicationContext(), GastronomiaDetalle.class);
                startActivity(i);*/
                }
            });
        }


        public void SetAllMarker(Double latitu, Double Longitude, final String titulo) {

            SetMarker(latitu, Longitude, titulo);

            mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    LatLng DAVAO1 = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                    //LatLng DAVAO1 = new LatLng(13.697802, -89.237193);
                    //LatLng DAVAO1 = new LatLng(Double.parseDouble(cursor.getString(7)), Double.parseDouble(cursor.getString(8)));
                    Log.i("coordenadas", marker.getPosition().latitude + "   " + marker.getPosition().latitude);

                    //DirectionUrl(titulo,point, DAVAO1);
                    return false;
                }
            });


        }


        /*public class SETLOGTask extends AsyncTask<Void, Void, Void> {

            int error;

            String lati, longi, detalle, titulo;

            public SETLOGTask(String lati, String longi) {
                this.lati = lati;
                this.longi = longi;
            }

            @Override
            protected void onPreExecute() {
                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    spdireccion = new SP_GetDireccionMapa();
                    spdireccion.getPromociones(lati, longi, getActivity());

                    for (int i = 0; i < spdireccion.formatted_address.size(); i++) {
                        formatted_address.addElement(spdireccion.formatted_address.get(i));
                        if (i == 0) {
                            detalle = spdireccion.formatted_address.get(i);
                        } else if (i == 1) {
                            titulo = spdireccion.formatted_address.get(i);
                        }
                        Log.i("posicion", "" + spdireccion.formatted_address.get(i));
                    }

                } catch (Exception ex) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {
                try {

                    if (error == 0) {
                        // Toast.makeText(PuntospagoActivity.this, "ok", Toast.LENGTH_LONG).show();

                    } else if (error == 10) {
                        //   Toast.makeText(PuntospagoActivity.this, "no", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {

                }
            }
        }*/
    }

}