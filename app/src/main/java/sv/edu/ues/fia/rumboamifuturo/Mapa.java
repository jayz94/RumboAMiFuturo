
package sv.edu.ues.fia.rumboamifuturo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.Vector;

public class Mapa extends AppCompatActivity {
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
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapas);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        gpstracker = new GPSTracker(this);
        latitud = gpstracker.getLatitude();
        longitud = gpstracker.getLongitude();
        mGoogleMap = mMapView.getMap();
        // Initializing
        mMarkerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main

        // Enable MyLocation Button in the Map

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
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

        MapsInitializer.initialize(this);

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




    }



}