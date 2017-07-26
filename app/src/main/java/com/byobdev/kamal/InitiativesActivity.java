package com.byobdev.kamal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
<<<<<<< HEAD
import android.test.suitebuilder.TestMethod;
=======
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
>>>>>>> cc24f7a9c1be1f556ebed2a5f84eedbc815b4789
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
=======
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.byobdev.kamal.helpers.DrawerItemClickListener;
>>>>>>> cc24f7a9c1be1f556ebed2a5f84eedbc815b4789
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
<<<<<<< HEAD

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class InitiativesActivity extends FragmentActivity implements OnMapReadyCallback, View.OnTouchListener {

=======
import static android.location.LocationManager.GPS_PROVIDER;

public class InitiativesActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnTouchListener {
    //Menu
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //Maps
>>>>>>> cc24f7a9c1be1f556ebed2a5f84eedbc815b4789
    GoogleMap initiativesMap;
    LocationManager locationManager;
    LocationListener locationListener;
    SupportMapFragment mapFragment;
    //Others
    Marker interestedMarker;
    FrameLayout shortDescriptionFragment;
    private float mLastPosY;

<<<<<<< HEAD
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

=======
>>>>>>> cc24f7a9c1be1f556ebed2a5f84eedbc815b4789
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiatives);

        //Maps
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (interestedMarker != null) {
                    interestedMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
            mapFragment.getMapAsync(this);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
                mapFragment.getMapAsync(this);
            }
        }
        //Menu
        mPlanetTitles = getResources().getStringArray(R.array.menu_options);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //Short description fragment set
        shortDescriptionFragment = (FrameLayout) findViewById(R.id.shortDescriptionFragment);
        shortDescriptionFragment.setOnTouchListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
                        mapFragment.getMapAsync(this);
                    }
                } else {
                    finish();
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initiativesMap = googleMap;
        final LatLng interested, initiative1;
        Location start = new Location(GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            start = locationManager.getLastKnownLocation(GPS_PROVIDER);
        }

        //Dummy points
        //interested = new LatLng(start.getLatitude(),start.getLongitude());
        interested = new LatLng(-33.4836473,-70.6558445);
        //initiative1 = new LatLng(start.getLatitude()-0.005000,start.getLongitude()+0.005000);
        initiative1 = new LatLng(-33.4823026,-70.6558445);
        interestedMarker = initiativesMap.addMarker(new MarkerOptions().position(interested).title("interested"));
        initiativesMap.addMarker(new MarkerOptions().position(initiative1).title("initiative1"));
        initiativesMap.moveCamera(CameraUpdateFactory.newLatLngZoom(interested,15));

        initiativesMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Hago aparecer fragment
                if (!marker.getTitle().equals("interested")){
                    FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.shortDescriptionFragment, new DescriptionFragment());
                    //Log
                    if (shortDescriptionFragment.getTranslationY() >= shortDescriptionFragment.getHeight()){
                        OvershootInterpolator interpolator;
                        interpolator = new OvershootInterpolator(5);
                        shortDescriptionFragment.animate().setInterpolator(interpolator).translationYBy(-200).setDuration(500);
                    }
                    trans.addToBackStack(null);
                    trans.commit();
                    Log.d("MAP", "Entro a " + marker.getTitle());
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastPosY = event.getY();
                return true;
            case (MotionEvent.ACTION_MOVE):
                float currentPosition = event.getY();
                float deltaY = mLastPosY - currentPosition;
                float transY = View.TRANSLATION_Y.get(v);
                transY -= deltaY;

                if (transY < 0){
                    transY = 0;
                }
                v.setTranslationY(transY);
                return true;
            default:
                return v.onTouchEvent(event);
        }
    }
}

