package com.techkid.tqdu.tripadvisor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    TabLayout mainTab;
    TabLayout.Tab hotelTab;
    TabLayout.Tab restaurantTab;
    TabLayout.Tab airportTab;
    TabLayout.Tab parkTab;
    static LatLng MyPoints;
    private Double MyPoints_Laititude, MyPoints_Longtitude;
    android.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPSTracker gpsTracker = new GPSTracker(this);
        if (!gpsTracker.canGetlocation1()) {
            gpsTracker.showSettingAlert();
        } else {
            gpsTracker.getLaititude();
            MyPoints_Laititude = gpsTracker.getLaititude();

            Log.d("LAT", "NULL");

            gpsTracker.getLongitude();
            MyPoints_Longtitude = gpsTracker.getLongitude();
        }


        Toolbar toolbar=(Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        AutoCompleteTextView autocompleteView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        autocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.auto_complete_list_item));
        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String description = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });
        initialize();


    }

    @Override
    protected void onResume() {
        super.onResume();
        MapViewFragment mapViewFragment = new MapViewFragment();
        PushingMapView(mapViewFragment);
    }

    private void initialize() {

        //Custom view for Tabs, set wanted icon size
        View viewHotel = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        ((ImageView) viewHotel.findViewById(R.id.icon)).setImageResource(R.drawable.icon_home_hotels);
        mainTab = (TabLayout) findViewById(R.id.tab_layout);
        hotelTab = mainTab.newTab();
        hotelTab.setCustomView(viewHotel);

        restaurantTab = mainTab.newTab();
        View viewRes = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        ((ImageView) viewRes.findViewById(R.id.icon)).setImageResource(R.drawable.icon_home_restaurants);
        restaurantTab.setCustomView(viewRes);

        airportTab = mainTab.newTab();
        View viewAir = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        ((ImageView) viewAir.findViewById(R.id.icon)).setImageResource(R.drawable.icon_home_flights);
        airportTab.setCustomView(viewAir);

        parkTab = mainTab.newTab();
        View viewPark = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        ((ImageView) viewPark.findViewById(R.id.icon)).setImageResource(R.drawable.icon_home_things_to_do);
        parkTab.setCustomView(viewPark);

        mainTab.addTab(hotelTab);
        mainTab.addTab(restaurantTab);
        mainTab.addTab(airportTab);
        mainTab.addTab(parkTab);

        //change action bar color
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B5E20")));

        //Set fragment welcome page
        MainPageFragment mainPageFragment = new MainPageFragment();
        openFragment(mainPageFragment);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    public class MapViewFragment extends Fragment {
        MapView mapView;
        private GoogleMap googleMap;

        public MapViewFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.map_fragment, container, false);

            mapView = (MapView) rootView.findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);

            mapView.onResume();

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;

                    // For showing a move to my location button

                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    googleMap.setMyLocationEnabled(true);

                    LatLng sydney = new LatLng(-33.8474039, 150.6517751);
                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Mylocation").snippet("Mylocation this"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12f).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                }
            });
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            mapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }
    }

    // Pushing MapView fragment
    private void PushingMapView(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_map, fragment);
        fragmentTransaction.commit();
    }

    private void checkSelectedTab(){
        mainTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);

                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        intent.putExtra("param","hotel");
                        break;
                    case 1:
                        intent.putExtra("param","restaurant");
                        break;
                    case 2:
                        intent.putExtra("param","station");
                        break;
                    case 3:
                        intent.putExtra("param","entertainment");
                        break;
                }
                MainActivity.this.startActivity(intent);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
