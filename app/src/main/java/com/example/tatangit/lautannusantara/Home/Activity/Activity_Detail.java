package com.example.tatangit.lautannusantara.Home.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils_Weather;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;

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

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;


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
            if (getIntent().getStringExtra("title").equalsIgnoreCase("activity_kelautan")) {
                mTitle.setText("Informasi Kelautan");
            }
        } else {
            onBackPressed();
            overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);
        }

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


        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Cuaca(), "Cuaca");
        adapter.addFragment(new Fragment_InfoLautan(), "History Cuaca");
        viewPager.setAdapter(adapter);
    }


    public String Lat() {
        return getIntent().getStringExtra("Latitude");
    }

    public String Lang() {
        return getIntent().getStringExtra("Longtitude");
    }


}
