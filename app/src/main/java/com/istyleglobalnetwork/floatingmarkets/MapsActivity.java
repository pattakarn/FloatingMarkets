package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(13.761868, 100.415591);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            return;
//        }
//        mMap.setMyLocationEnabled(true);

//        LatLng market = new LatLng(13.761868, 100.415591);
//        mMap.addMarker(new MarkerOptions().position(market).title("ตลาดน้ำคลองลัดมะยม"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(market));
//
//        LatLng center = new LatLng(13.761868, 100.415591);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));

        LatLng market = new LatLng(13.761868, 100.415591);
//        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(market, 13));

        mMap.addMarker(new MarkerOptions()
                .title("ตลาดน้ำคลองลัดมะยม")
//                .snippet("The most populous city in Australia.")
                .position(market));

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.info_map, null);


                TextView title = ((TextView) infoWindow.findViewById(R.id.textViewName));
                title.setText(marker.getTitle());


                TextView snippet = ((TextView) infoWindow.findViewById(R.id.textViewSnippet));
                snippet.setText(marker.getSnippet());

                ImageView imageView = (ImageView) infoWindow.findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
                if ("ตลาดน้ำคลองลัดมะยม".equals(marker.getTitle())) {
                    imageView.setImageResource(R.drawable.logo3);
                }

                return infoWindow;
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}
