package com.lewgmail.romanenko.taxiservice.view.activity;

/**
 * Created by Lev on 28.11.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.MapGooglePresenter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends Activity implements OnMapReadyCallback, IView {

    private final static String FIRST_CORD = "longitude", SECONF_CORD = "latitude";
    @BindView(R.id.button_ok)
    Button buttonOK;

    // Technical Object
    private MapFragment googleMap;
    private LatLng position;
    private Geocoder geo;
    private MapGooglePresenter mapGooglePresenter;
    private SharedPreferences sPref;
    private Intent intentMy;

    // Business object
    private String addressFromMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);
        ButterKnife.bind(this);
        intentMy = getIntent();
        createMapView();
        //  mapGooglePresenter = new MapGooglePresenter(this);
        // addMarker();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(50.44854153, 30.50714493), 10));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //  googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.44854153, 30.50714493))
                .title("Marker")
                .draggable(true)
                .snippet("Hello"));


        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                Locale aLocale = new Locale.Builder().setLanguage("ru").setScript("Latn").setRegion("RS").build();
                position = marker.getPosition(); //
                try {
                    geo = new Geocoder(MapActivity.this.getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(position.latitude, position.longitude, 1);
                    if (addresses.isEmpty()) {
                        Toast.makeText(
                                MapActivity.this,
                                "Please wait",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (addresses.size() > 0) {
                          /*  Toast.makeText(getApplicationContext(),
                                    "Address:- " + addresses.get(0).getFeatureName() +
                                            addresses.get(0).getAddressLine(0)+
                                            addresses.get(0).getAdminArea() +
                                            addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                            addressFromMarker = addresses.get(0).getFeatureName() +
                                    addresses.get(0).getAdminArea() +
                                    addresses.get(0).getLocality();*/
                            Toast.makeText(getApplicationContext(),
                                    "Address:- " +
                                            addresses.get(0).getAddressLine(0) +
                                            addresses.get(0).getAdminArea() +
                                            addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                            //addresses.get(0).getSub

                            addressFromMarker = addresses.get(0).getAddressLine(0) +
                                    addresses.get(0).getAdminArea() +
                                    addresses.get(0).getLocality();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }
            }
        });

    }

   /* public LatLng culculateDistance() {

    }*/

    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = (MapFragment) getFragmentManager()
                        .findFragmentById(R.id.mapView);
                googleMap.getMapAsync(this);

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }

    }

    @OnClick(R.id.button_ok)
    public void buttonOk() {
        Intent returnIntent = getIntent();
        returnIntent.putExtra(getDataFromPreviousActivity(intentMy), addressFromMarker);
        returnIntent.putExtra(FIRST_CORD, position.longitude);
        returnIntent.putExtra(SECONF_CORD, position.latitude);
        // returnIntent.putExtra(FIRST_CORD,50.4546600 );
        //  returnIntent.putExtra(SECONF_CORD, 30.5238000);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    private String getDataFromPreviousActivity(Intent intent) {
        return intent.getStringExtra("keyAddressFromMarker");
    }

    ///////////////////////////////////// Up date View  ////////////////////////////////////
    @Override
    public void showError(String error) {
        Toast.makeText(
                MapActivity.this,
                error,
                Toast.LENGTH_LONG).show();
    }

    public void setPrice() {

    }

}
