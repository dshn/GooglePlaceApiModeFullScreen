package com.googleplaceapimodefullscreen;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity {

    private TextView tvAddress;
    private TextView tvLatLong;
    private TextView btnAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddress=(TextView)findViewById(R.id.tv_address);
        tvLatLong=(TextView)findViewById(R.id.tv_lat_lng);
        btnAddress=(TextView)findViewById(R.id.btn_address);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
              //  Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
              //  Log.i(TAG, "An error occurred: " + status);
            }
        });


        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPlaceAutoIntentKroid();
            }
        });

    }

    public void callPlaceAutoIntentKroid() {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();

            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, 1);

        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.

            Log.e("e:",e.getLocalizedMessage());

        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            Log.e("e:",e.getLocalizedMessage());

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //   super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode==1){
                Place place = PlaceAutocomplete.getPlace(MainActivity.this, data);
                final LatLng location = place.getLatLng();
                tvAddress.setText(place.getAddress());
                tvLatLong.setText(location.latitude+","+location.longitude);


            }
        }

        /*if(requestCode==PLACE_AUTOCOMPLETE_FROM_REQUEST_CODE_KROID)
        {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(Manager_Home_Activity.this, data);

                final LatLng location = place.getLatLng();
                googleMap.addMarker(new MarkerOptions().position(location)
                   *//* .title("Marker in Sydney")*//*.icon(BitmapDescriptorFactory.fromResource(R.drawable.img_userpin)));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,15f));
                locationtxt.setText((String) place.getAddress());
             *//*   Geocoder gcd = new Geocoder(Manager_Home_Activity.this, Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(location.latitude, location.longitude, 1);
                    if (addresses.size() > 0) {*//*
                Log.i("Name", (String) place.getAddress());

                // txtFromAPI.setText(place.getName());
                // strFromC = addresses.get(0).getCountryName();
                 *//*   }
                } catch (IOException e) {
                    e.printStackTrace();
                }*//*

            }
        }*/

    }

}
