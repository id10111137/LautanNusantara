package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_InfoWindows;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Current.ResponseCurrent;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Current.WeatherItem;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Kelautan extends AppCompatActivity implements OnMapReadyCallback {
//public class Activity_Kelautan extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;
    GoogleMap mMap;


    private LatLng latLng1 = new LatLng(-2.566640, 108.148269);
    private LatLng latLng2 = new LatLng(-2.540898, 108.423066);
    private LatLng latLng3 = new LatLng(-2.725421, 108.494270);

    private LatLng latLng4 = new LatLng(-2.889485, 108.514879);
    private LatLng latLng5 = new LatLng(-3.052092, 108.477933);
    private LatLng latLng6 = new LatLng(-3.168144, 108.416266);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelautan);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Kelautan");

        interface_api = Utils.getSOService("p");
        messageItemLogin = ModelManager.getInstance(getApplicationContext()).getUser();

        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_back));
        id_icon_toolbar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_reload));
        toolbar_iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity_Kelautan.this, "Mohon Maaf Sedang Proses Deployment", Toast.LENGTH_SHORT).show();
            }
        });

        /*
            Loading
         */
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Mohon Menunggu");
        pDialog.setCancelable(false);

        /*
            Session
        */

        try {
            if (!ModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
                startActivity(new Intent(getApplicationContext(), Activity_Login.class));
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;

        mMap.addMarker(new MarkerOptions()
                .position(latLng1)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("test").snippet("test"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng1.latitude,latLng1.longitude), 17));
        mMap.animateCamera(CameraUpdateFactory.zoomOut());
        mMap.setInfoWindowAdapter(new Adapter_InfoWindows(getApplicationContext(), mMap));
    }
}
