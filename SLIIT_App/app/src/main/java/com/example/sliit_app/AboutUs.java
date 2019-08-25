//About us - Contains and shows the Google map view of SLIIT branches and Details
package com.example.sliit_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutUs extends Fragment implements OnMapReadyCallback {

    private TextView txtSliitName;
    private TextView txtSliitDescription;

    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private Marker previousMarker = null;

    static final LatLng SLIIT_METRO = new LatLng(6.912644, 79.850756);
    static final LatLng SLIIT_MALABE = new LatLng(6.914816, 79.973132);
    static final LatLng SLIIT_MATARA = new LatLng(5.949162, 80.546512);
    static final LatLng SLIIT_KANDY = new LatLng(7.275536, 80.612721);
    static final LatLng SLIIT_KURUNAGALA = new LatLng(7.484779, 80.362881);
    static final LatLng SLIIT_JAFFNA = new LatLng(9.666711, 80.017504);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        txtSliitName = view.findViewById(R.id.txt_sliit_name);
        txtSliitDescription = view.findViewById(R.id.txt_sliit_description);

        mapView = view.findViewById(R.id.sliit_map);
        initGoogleMap(savedInstanceState);

        return view;
    }

    //Initialize the google map
    private void initGoogleMap(Bundle savedInstanceState) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    /*Life Cycle methods used in android. Needed to implement Map view(google maps object)*/
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    //Sets up the markers, set the camera position and register on click listeners on markers to get the user selected master and update text fields
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions sliitMetro = new MarkerOptions().position(SLIIT_METRO).title("SLIIT Metro");
        MarkerOptions sliitMalabe = new MarkerOptions().position(SLIIT_MALABE).title("SLIIT Malabe");
        MarkerOptions sliitMatara = new MarkerOptions().position(SLIIT_MATARA).title("SLIIT Matara");
        MarkerOptions sliitKandy = new MarkerOptions().position(SLIIT_KANDY).title("SLIIT Kandy");
        MarkerOptions sliitKurunagala = new MarkerOptions().position(SLIIT_KURUNAGALA).title("SLIIT Kurunagala");
        MarkerOptions sliitJaffna = new MarkerOptions().position(SLIIT_JAFFNA).title("SLIIT Jaffna");

        googleMap.addMarker(sliitMetro);
        googleMap.addMarker(sliitMalabe);
        googleMap.addMarker(sliitMatara);
        googleMap.addMarker(sliitKandy);
        googleMap.addMarker(sliitKurunagala);
        googleMap.addMarker(sliitJaffna);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(SLIIT_MATARA));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(8.023929, 80.632426))
                .zoom(7)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String[] sliitBranchNames = getResources().getStringArray(R.array.sliit_branches_name);
                String[] sliitBranchDescriptions = getResources().getStringArray(R.array.sliit_branches_description);
                switch (marker.getTitle()) {
                    case "SLIIT Metro":
                        txtSliitName.setText(sliitBranchNames[0]);
                        txtSliitDescription.setText(sliitBranchDescriptions[0]);
                        break;
                    case "SLIIT Malabe":
                        txtSliitName.setText(sliitBranchNames[1]);
                        txtSliitDescription.setText(sliitBranchDescriptions[1]);
                        break;
                    case "SLIIT Matara":
                        txtSliitName.setText(sliitBranchNames[2]);
                        txtSliitDescription.setText(sliitBranchDescriptions[2]);
                        break;
                    case "SLIIT Kandy":
                        txtSliitName.setText(sliitBranchNames[3]);
                        txtSliitDescription.setText(sliitBranchDescriptions[3]);
                        break;
                    case "SLIIT Kurunagala":
                        txtSliitName.setText(sliitBranchNames[4]);
                        txtSliitDescription.setText(sliitBranchDescriptions[4]);
                        break;
                    case "SLIIT Jaffna":
                        txtSliitName.setText(sliitBranchNames[5]);
                        txtSliitDescription.setText(sliitBranchDescriptions[5]);
                        break;
                }


                if (previousMarker != null) {
                    previousMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                previousMarker = marker;

                return true;
            }
        });
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);


    }


    /*Life Cycle methods used in android. Needed to implement Map view(google maps object)*/
    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}