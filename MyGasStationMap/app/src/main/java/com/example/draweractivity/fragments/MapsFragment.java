package com.example.draweractivity.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draweractivity.Model.GasStation;
import com.example.draweractivity.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    TextClock textViewDate;
    Date date;
    int currentTime;
    ImageView imageViewFavorite;
    TextView textViewFavorite;
    TextView addressGasStation;
    TextView coordinateGasStation;
    TextView nameGasStation;
    boolean flag;
    List<GasStation> arrayListGasStation;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            date = new Date();
            currentTime = date.getHours();
            final GasStation gasStation1 = new GasStation(45.5,34,"LukOil","Nikolskaya");
            GasStation gasStation2 = new GasStation(48.5,36,"TNK","Nikolskaya");
            GasStation gasStation3 = new GasStation(50.5,31,"GazProm","Nikolskaya");

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean sharedPreferenceTheme = sharedPreferences.getBoolean("theme", false);

            if (sharedPreferenceTheme) {
                Toast.makeText(getActivity(), "Ночной режим работает с 21:00 до 06:00", Toast.LENGTH_LONG).show();
                if (currentTime > 20 || currentTime < 6) {
                    boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.night));
                } else {
                    boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.day));
                }
            } else {
                boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.day));
            }


            LatLng marker1 = new LatLng(gasStation1.getLattitude(), gasStation1.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(marker1).title("Marker in Moscow"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1, 10));


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                    View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.layout_buttom_sheet, null);
                    bottomSheetDialog.setContentView(bottomSheetDialogView);

                    addressGasStation = bottomSheetDialog.findViewById(R.id.addressGasStationID);
                    nameGasStation = bottomSheetDialog.findViewById(R.id.nameGasStationID);
                    coordinateGasStation = bottomSheetDialog.findViewById(R.id.coordinatGasStation);

                    addressGasStation.setText(gasStation1.getAddressGasStation());
                    bottomSheetDialog.show();

                    imageViewFavorite = bottomSheetDialog.findViewById(R.id.image_favorite);
                    textViewFavorite = bottomSheetDialog.findViewById(R.id.text_favorit);

                    final LinearLayout linearLayoutButtonFavorite = bottomSheetDialog.findViewById(R.id.linearLayoutFavorite);
                    linearLayoutButtonFavorite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (flag) {
                                imageViewFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                textViewFavorite.setText("Добавить в избранное");
                                flag = false;
                            } else {
                                imageViewFavorite.setImageResource(R.drawable.ic_baseline_favorite_red);
                                textViewFavorite.setText("Избранное");
                                flag = true;
                            }
                        }
                    });
                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}