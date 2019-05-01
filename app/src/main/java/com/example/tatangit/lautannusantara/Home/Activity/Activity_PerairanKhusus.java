package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_InfoWindows;
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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_PerairanKhusus extends AppCompatActivity implements OnMapReadyCallback {


    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;

    private LatLng bengkulu = new LatLng(-3.80044, 102.26554);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perairankhusu);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Perairan Khusus");

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
                Toast.makeText(getApplicationContext(), "Mohon Maaf Sedang Proses Deployment", Toast.LENGTH_SHORT).show();
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
    public void onMapReady(GoogleMap map) {

        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMinZoomPreference(11);


        map.addMarker(new MarkerOptions()
                .position(bengkulu)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Bengkulu Provinsi").snippet("Indonesian, Bengkulu Provinsi"));

        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMinZoomPreference(11);


        map.addMarker(new MarkerOptions()
                .position(bengkulu)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Bengkulu Provinsi").snippet("Indonesian, Bengkulu Provinsi"));

        map.setInfoWindowAdapter(new Adapter_InfoWindows(getApplicationContext()));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(bengkulu, 17));

    }


    @OnClick(R.id.id_goPerairanKhusus)
    public void goLautLepas(){
        mIntent = new Intent(getApplicationContext(), Activity_Detail.class);
        mIntent.putExtra("title", "activity_perairankhusus");
        startActivity(mIntent);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);

    }
}
