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

public class CarParkActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_park);

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
        LatLng carPark1 = new LatLng(13.761272, 100.417200);
        mMap.addMarker(new MarkerOptions().position(carPark1).title("ลานจอดรถ ISTYLE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(carPark1));

        LatLng carPark2 = new LatLng(13.761108, 100.416658);
        mMap.addMarker(new MarkerOptions().position(carPark2).title("ลานจอดรถนาบัว"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(carPark2));

        LatLng center = new LatLng(13.761272, 100.417200);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));

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
                if ("ลานจอดรถ ISTYLE".equals(marker.getTitle())) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground);
                } if ("ลานจอดรถนาบัว".equals(marker.getTitle())) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground);
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
