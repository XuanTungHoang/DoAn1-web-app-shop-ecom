package com.example.doandidong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.example.doandidong.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ThongtinActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Toolbar toolbar_thongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thongtin );

        toolbar_thongtin=(Toolbar) findViewById( R.id.Toolbar_thongtin );
        ActionBar();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }

    private void ActionBar() {
        setSupportActionBar( toolbar_thongtin );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        toolbar_thongtin.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

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
//        mMap = googleMap;
//        mMap.setMyLocationEnabled( true );
//        // Add a marker in Sydney and move the camera
//        LatLng ktx_khuA = new LatLng( 10.878088, 106.807226 );
//        mMap.addMarker( new MarkerOptions().position( ktx_khuA ).title( "Kí túc xa khu A - ĐHQG HCM" ).snippet("Khu phố 6, phường Linh Trung,quận Thủ Đức").icon( BitmapDescriptorFactory.defaultMarker() ) );
//        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
//        CameraPosition cameraPosition=new CameraPosition.Builder(  ).target( ktx_khuA ).zoom( 90 ).build();
//        mMap.animateCamera( CameraUpdateFactory.newCameraPosition( cameraPosition ) );

    }
}
