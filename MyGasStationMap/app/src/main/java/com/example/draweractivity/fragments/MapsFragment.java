package com.example.draweractivity.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draweractivity.Database.DBHelper;
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
    DBHelper dbHelper;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            date = new Date();
            currentTime = date.getHours();

            dbHelper = new DBHelper(getContext());

         final GasStation gasStation  =new GasStation();
         gasStation.setNameGasStation("GazProm");
         gasStation.setAddressGasStation("Volgogradskiy prospekt");
         gasStation.setLattitude(37.786);
         gasStation.setLongitude(51.456);
         gasStation.setAi95(45.67);
         gasStation.setAi92(42.45);
         gasStation.setDisel(40.67);


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


            LatLng marker = new LatLng(gasStation.getLattitude(), gasStation.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(marker).title("Marker in Moscow"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 10));


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                    View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.layout_buttom_sheet, null);
                    bottomSheetDialog.setContentView(bottomSheetDialogView);

                    addressGasStation = bottomSheetDialog.findViewById(R.id.addressGasStationID);
                    nameGasStation = bottomSheetDialog.findViewById(R.id.nameGasStationID);
                    coordinateGasStation = bottomSheetDialog.findViewById(R.id.coordinatGasStation);

                    addressGasStation.setText(gasStation.getAddressGasStation());
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
                                dbHelper.addGasStation(gasStation);
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