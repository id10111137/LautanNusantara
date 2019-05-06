package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_Weather_History;
import com.example.tatangit.lautannusantara.Home.Adapter.ViewPagerAdapter;
import com.example.tatangit.lautannusantara.Home.Fragment.Fragment_Cuaca;
import com.example.tatangit.lautannusantara.Home.Fragment.Fragment_InfoLautan;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ListItemHourly;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ResponseHourly;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils_Weather;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Detail extends AppCompatActivity {


    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;
//    ViewPagerAdapter adapter;

    @BindView(R.id.id_lv_weather)
    ListView id_lv_weather;
    Adapter_Weather_History adapter_weatherHistory;
    String Lat;
    String Lon;

//    @BindView(R.id.tabs)
//    TabLayout tableLayout;
//
//    @BindView(R.id.viewpager)
//    ViewPager viewPager;

//    public String TabHostTitle_info;
//    public String TabHostTitle_History;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);


        if (getIntent().getStringExtra("title") != null) {
            if (getIntent().getStringExtra("title").equalsIgnoreCase("activity_pesisir")) {
                mTitle.setText("Informasi Pesisir");
//                TabHostTitle_info = "Informasi Pesisir";
//                TabHostTitle_History = "History Data Pesisir";
            } else if (getIntent().getStringExtra("title").equalsIgnoreCase("activity_kelautan")) {
                mTitle.setText("Informasi Kelautan");
//                TabHostTitle_info = "Informasi Kelautan";
//                TabHostTitle_History = "History Data Kelautan";
            } else if (getIntent().getStringExtra("title").equalsIgnoreCase("activity_lautlepas")) {
                mTitle.setText("Informasi Laut Lepas");
//                TabHostTitle_info = "Informasi Laut Lepas";
//                TabHostTitle_History = "History Laut Lepas";
            } else if (getIntent().getStringExtra("title").equalsIgnoreCase("activity_perairankhusus")) {
                mTitle.setText("Informasi Perairan Khusus");
//                TabHostTitle_info = "Informasi Perairan Khusus";
//                TabHostTitle_History = "History Perairan Khusus";
            }
        } else {
            onBackPressed();
            overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);
        }



        Log.d("Tampilkan", " Latitude "+getIntent().getStringExtra("Latitude")+" Longtitude "+getIntent().getStringExtra("Longtitude"));

        interface_api = Utils_Weather.getWeather();
        messageItemLogin = ModelManager.getInstance(getApplicationContext()).getUser();
        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(null);
        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_dwon));
        toolbar_iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);
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

        /*
            tab host
         */
//        tableLayout.setupWithViewPager(viewPager);
//        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new Fragment_InfoLautan(), TabHostTitle_info);
//        adapter.addFragment(new Fragment_Cuaca(), TabHostTitle_History);
//        viewPager.setAdapter(adapter);

        interface_api.cWeatherHurly(getIntent().getStringExtra("Latitude"), getIntent().getStringExtra("Longtitude"), "202aee9fbafda2e81aa448b7d79daf32").enqueue(new Callback<ResponseHourly>() {
            @Override
            public void onResponse(Call<ResponseHourly> call, Response<ResponseHourly> response) {
                if (response.code() == 200) {
                    final List<ListItemHourly> lItem = response.body().getList();
                    Log.d("Tampilkan", "" + lItem.toString());
                    adapter_weatherHistory = new Adapter_Weather_History(lItem, getApplicationContext());
                    adapter_weatherHistory.notifyDataSetChanged();
                    id_lv_weather.setAdapter(adapter_weatherHistory);

                } else {
                    Toast.makeText(getApplicationContext(), "Upps, Gagal Untuk Mengambil data dari Weather", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHourly> call, Throwable t) {
                Log.d("Tampilkan", "" + t.toString());
            }
        });



    }


    private void getWeather(){



    }


}
