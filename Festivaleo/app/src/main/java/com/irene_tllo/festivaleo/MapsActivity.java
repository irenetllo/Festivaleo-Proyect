package com.irene_tllo.festivaleo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       mMap.getUiSettings().setZoomControlsEnabled(true);

        double lati = Double.parseDouble(getIntent().getExtras().getString("lat"));
        double lngi = Double.parseDouble(getIntent().getExtras().getString("lng"));
        LatLng fest = new LatLng(lati, lngi);
        mMap.addMarker(new MarkerOptions().position(fest).title("Festival " + getIntent().getExtras().getString("nombre")+", "+getIntent().getExtras().getString("lugar")));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(fest));



    }


}
