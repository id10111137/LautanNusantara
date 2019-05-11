package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemKordinat;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseKordinat;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_LautLepas extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    Toolbar toolbar;
    TextView mTitle;
    MessageItemKordinat modelkordinat;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;
    GoogleMap mMap;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lautlepas);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Laut Lepas");

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
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {


        interface_api.cKordinat().enqueue(new Callback<ResponseKordinat>() {
            @Override
            public void onResponse(Call<ResponseKordinat> call, Response<ResponseKordinat> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    final List<MessageItemKordinat> lLogin = response.body().getMessage();
                    for (int i = 0; i < lLogin.size(); i++) {
                        mMap.addMarker(new MarkerOptions()
                                .position(	new LatLng(Double.valueOf(lLogin.get(i).getLatitude()),Double.valueOf(lLogin.get(i).getLongtitude()))
                                )
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .title("Belitung Provinsi").snippet("Indonesian, Belitung Provinsi"));
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal Untuk Menghubungkan Ke Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKordinat> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ups, Gagal Koneksi Ke Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 17));

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        interface_api.cKordinat().enqueue(new Callback<ResponseKordinat>() {
            @Override
            public void onResponse(Call<ResponseKordinat> call, Response<ResponseKordinat> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    final List<MessageItemKordinat> lLogin = response.body().getMessage();
                    for (int i = 0; i < lLogin.size(); i++) {
                        mMap.addMarker(new MarkerOptions()
                                .position(	new LatLng(Double.valueOf(lLogin.get(i).getLatitude()),Double.valueOf(lLogin.get(i).getLongtitude()))
                                )
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .title("Belitung Provinsi").snippet("Indonesian, Belitung Provinsi"));
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal Untuk Menghubungkan Ke Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKordinat> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ups, Gagal Koneksi Ke Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 17));
    }

    @OnClick(R.id.id_goLautanLepas)
    public void goLautLepas(){
        mIntent = new Intent(getApplicationContext(), Activity_Detail.class);
        mIntent.putExtra("title", "activity_lautlepas");
        startActivity(mIntent);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);
    }

    }
