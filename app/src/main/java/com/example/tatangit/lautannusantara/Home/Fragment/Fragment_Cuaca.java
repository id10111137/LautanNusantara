package com.example.tatangit.lautannusantara.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_Weather_History;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ListItemHourly;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ResponseHourly;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils_Weather;
import com.example.tatangit.lautannusantara.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Cuaca extends Fragment {


    Intent mIntent;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView;
    View root;
    @BindView(R.id.id_lv_weather)
    ListView id_lv_weather;

    Adapter_Weather_History adapter_weatherHistory;
    Interface_Api interface_api;

    public Fragment_Cuaca() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cuaca, container, false);
        ButterKnife.bind(this, root);
        interface_api = Utils_Weather.getWeather();
        getWeather();
        return root;
    }


    private void getWeather(){

        interface_api.cWeatherHurly("-3.80044", "102.26554", "202aee9fbafda2e81aa448b7d79daf32").enqueue(new Callback<ResponseHourly>() {
            @Override
            public void onResponse(Call<ResponseHourly> call, Response<ResponseHourly> response) {
                Log.d("Tampilkan", "" + response.toString());
                if (response.code() == 200) {
                    final List<ListItemHourly> lItem = response.body().getList();
                    Log.d("Tampilkan", "" + lItem.toString());
                    adapter_weatherHistory = new Adapter_Weather_History(lItem, getContext());
                    adapter_weatherHistory.notifyDataSetChanged();
                    id_lv_weather.setAdapter(adapter_weatherHistory);

                } else {
                    Toast.makeText(getContext(), "Upps, Gagal Untuk Mengambil data dari Weather", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHourly> call, Throwable t) {
                Log.d("Tampilkan", "" + t.toString());
            }
        });

    }
}
