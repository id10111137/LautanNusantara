package com.example.tatangit.lautannusantara.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_Weather_Informasi;
import com.example.tatangit.lautannusantara.Library.SqlLite.Hellper.hWeather;
import com.example.tatangit.lautannusantara.Library.SqlLite.Model.mWeather;
import com.example.tatangit.lautannusantara.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_InfoLautan extends Fragment {


    Intent mIntent;
    Toolbar toolbar;
    CircleImageView toolbar_iconView;
    View root;

    @BindView(R.id.id_refresh)
    SwipeRefreshLayout id_refresh;

    @BindView(R.id.id_lv_weather)
    ListView id_lv_weather;

    Adapter_Weather_Informasi adapter_weather_informasi;

    public Fragment_InfoLautan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, root);
        getHistory();
        id_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                id_refresh.setRefreshing(true);
                getHistory();
            }
        });

        return root;
    }


    private void getHistory(){
        try {
            hWeather weathers = new hWeather(getContext());
            adapter_weather_informasi = new Adapter_Weather_Informasi(weathers.getWeather(), getContext());
            adapter_weather_informasi.notifyDataSetChanged();
            id_lv_weather.setAdapter(adapter_weather_informasi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
