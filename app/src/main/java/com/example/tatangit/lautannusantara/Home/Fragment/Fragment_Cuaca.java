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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Activity.Activity_Detail;
import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_Weather_History;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ListItemHourly;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ResponseHourly;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils_Weather;
import com.example.tatangit.lautannusantara.Library.SqlLite.Hellper.hWeather;
import com.example.tatangit.lautannusantara.Library.SqlLite.Model.mWeather;
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

    Adapter_Weather_History adapter_weatherHistory;
    Interface_Api interface_api;

    @BindView(R.id.id_lv_weather)
    ListView id_lv_weather;

    @BindView(R.id.id_refresh)
    SwipeRefreshLayout id_refresh;

    public String Lat;
    public String Lan;


    public Fragment_Cuaca() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cuaca, container, false);
        ButterKnife.bind(this, root);
        interface_api = Utils_Weather.getWeather();

        Activity_Detail activity = (Activity_Detail) getActivity();
        Lat = activity.Lat();
        Lan = activity.Lang();


        SetData();
        id_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                id_refresh.setRefreshing(true);
                SetData();
            }
        });

        return root;
    }

    private void SetData() {
        interface_api.cWeatherHurly(Lat, Lan, "202aee9fbafda2e81aa448b7d79daf32").enqueue(new Callback<ResponseHourly>() {
            @Override
            public void onResponse(Call<ResponseHourly> call, Response<ResponseHourly> response) {
                if (response.code() == 200) {
                    final List<ListItemHourly> lItem = response.body().getList();
                    adapter_weatherHistory = new Adapter_Weather_History(lItem, getContext());
                    adapter_weatherHistory.notifyDataSetChanged();
                    id_lv_weather.setAdapter(adapter_weatherHistory);
                    AddDataHistory(lItem);

                } else {
                    Toast.makeText(getContext(), "Gagal Ambil Data" + Lat, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHourly> call, Throwable t) {
                Toast.makeText(getContext(), "Error Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void AddDataHistory(List<ListItemHourly> list) {

        mWeather mWeather = new mWeather();
        for (int i = 0; i < list.size(); i++) {

            for (int x = 0; x < list.get(i).getWeather().size(); x++) {
                mWeather.setIconSet(list.get(i).getWeather().get(x).getIcon());
            }

            mWeather.setLatitude(Lat);
            mWeather.setLongtitude(Lan);
            mWeather.setTemp_kf(Double.valueOf("1"));
            mWeather.setGrnd_level("contoh");
            mWeather.setSea_level("Contoh");
            mWeather.setPressure("Contoh");
            mWeather.setHumidity("Contoh");
            mWeather.setTanggal("");
            mWeather.setTemperature(String.valueOf(list.get(i).getMain().getTemp()));
            mWeather.setNoWeather(i);
        }
        hWeather weathers = new hWeather(getContext());
        weathers.addWeather(mWeather);
    }


}
