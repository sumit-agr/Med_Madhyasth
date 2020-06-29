package com.cvrce.chatapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FromDonorToNeedy extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_donor_to_needy);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String data;
        Intent intent = getIntent();
        data = intent.getStringExtra("Data");
        String[] str = data.split(",");
        str[2] = str[2].substring(0,str[2].length()-1);
        for(int i = 0;i<str.length;i++){
        Log.i(null,"###############"+str[i]);
        }

        String[] lat = str[1].split("=");
        String[] longi = str[2].split("=");
        latitude = Double.parseDouble(lat[1]);
        longitude = Double.parseDouble(longi[1]);
        Log.i(null,"****************"+latitude+"::"+longitude);
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
        LatLng needy = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(needy).title("Marker in Needy"));
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(needy));
    }
}
