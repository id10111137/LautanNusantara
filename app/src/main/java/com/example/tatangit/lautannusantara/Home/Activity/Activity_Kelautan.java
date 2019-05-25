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
import com.example.tatangit.lautannusantara.Library.Notification.H_Notification;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemKordinat;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseKordinat;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Kelautan extends AppCompatActivity implements
        OnMapReadyCallback {

    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;
    GoogleMap mMap;
    View v;

    H_Notification h_notification;
    Marker marker;



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
        h_notification = new H_Notification();


        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_info));
        id_icon_toolbar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressed();
//                finish();
                mIntent = new Intent(getApplicationContext(), Activity_Account.class);
                startActivity(mIntent);
            }
        });

        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_logout));
        toolbar_iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Activity_Kelautan.this, "Mohon Maaf Sedang Proses Deployment", Toast.LENGTH_SHORT).show();
                h_notification.eNotif(getApplicationContext(), "Logout", "Lautan Nusantara", "Terima Kasih Telah Berkunjung");
                ModelManager.getInstance(getApplicationContext()).LogOut();
                finish();
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


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                interface_api.cKordinat().enqueue(new Callback<ResponseKordinat>() {
                    @Override
                    public void onResponse(Call<ResponseKordinat> call, Response<ResponseKordinat> response) {
                        if (response.isSuccessful()) {
                            pDialog.dismiss();
                            final List<MessageItemKordinat> messageItemKordinats = response.body().getMessage();
                            for (int i = 0; i < messageItemKordinats.size(); i++) {
                                marker =  mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(messageItemKordinats.get(i).getLatitude()), Double.parseDouble(messageItemKordinats.get(i).getLongitude()))
                                        )
                                        .icon(BitmapDescriptorFactory
                                                .fromResource(R.drawable.ic_fish))
                                        .title("Belitung Provinsi").snippet(messageItemKordinats.get(i).getJumlahKlorofil()));


                            }
                        } else {
                            pDialog.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseKordinat> call, Throwable t) {
                        pDialog.dismiss();
                    }
                });
            }
        });


        mMap.setInfoWindowAdapter(new Adapter_InfoWindows(getApplicationContext(), mMap));

    }
}
