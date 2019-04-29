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

import com.example.tatangit.lautannusantara.Home.Activity.Activity_Main;
import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_Weather;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.ListItem;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Response.ResponseOpenWeather;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
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

    Adapter_Weather adapter_weather;
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
        interface_api.cWeather("-3.80044", "102.26554", "202aee9fbafda2e81aa448b7d79daf32").enqueue(new Callback<ResponseOpenWeather>() {
            @Override
            public void onResponse(Call<ResponseOpenWeather> call, Response<ResponseOpenWeather> response) {

                Log.d("Tampilkan",""+response.toString());
                if (response.code() == 200) {
                    final List<ListItem> lItem = response.body().getList();
                    Log.d("Tampilkan",""+lItem.toString());
                    adapter_weather = new Adapter_Weather(lItem, getContext());
                    adapter_weather.notifyDataSetChanged();
                    id_lv_weather.setAdapter(adapter_weather);

                } else {
                    Toast.makeText(getContext(), "Upps, Gagal Untuk Mengambil data dari Weather", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseOpenWeather> call, Throwable t) {
                Toast.makeText(getContext(), "Upps, Koneksi anda buruk", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
