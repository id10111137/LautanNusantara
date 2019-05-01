package com.example.tatangit.lautannusantara.Home.Adapter;


import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Current.ResponseCurrent;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils_Weather;
import com.example.tatangit.lautannusantara.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_InfoWindows implements GoogleMap.InfoWindowAdapter {

    Context context;
    Interface_Api interface_api;
    public String id_temp_saat_inis;
    public String id_kedalaman_lauts;
    public String id_bawah_lauts;
    public String id_tekanan_udaras;
    public String id_kecepatan_angins;
    public String id_tempats;
    public String id_icon_infowindowss;

    public Adapter_InfoWindows(Context context) {
        this.context = context;
        interface_api = Utils_Weather.getWeather();
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_infowindow, null);
        LatLng latLng = marker.getPosition();

        TextView tvLat = (TextView) v.findViewById(R.id.id_latitude);
        TextView tvLng = (TextView) v.findViewById(R.id.id_longtitude);
        TextView id_temp_saat_ini = (TextView) v.findViewById(R.id.id_temp_saat_ini);
        TextView id_kedalaman_laut = (TextView) v.findViewById(R.id.id_kedalaman_laut);
        TextView id_bawah_laut = (TextView) v.findViewById(R.id.id_bawah_laut);
        TextView id_tekanan_udara = (TextView) v.findViewById(R.id.id_tekanan_udara);
        TextView id_kecepatan_angin = (TextView) v.findViewById(R.id.id_kecepatan_angin);
        TextView id_tempat = (TextView) v.findViewById(R.id.id_tempat);
        ImageView id_icon_infowindows = (ImageView) v.findViewById(R.id.id_icon_infowindows);


        interface_api.cWeatherCurrent(Double.toString(latLng.latitude), Double.toString(latLng.longitude), "202aee9fbafda2e81aa448b7d79daf32").enqueue(new Callback<ResponseCurrent>() {
            @Override
            public void onResponse(Call<ResponseCurrent> call, Response<ResponseCurrent> response) {
                if (response.code() == 200) {


                    id_temp_saat_inis = ""+response.body().getMain().getTemp();
                    id_kedalaman_lauts = ""+response.body().getMain().getSeaLevel();
                    id_bawah_lauts = ""+response.body().getMain().getGrndLevel();
                    id_bawah_lauts= ""+response.body().getMain().getPressure();
                    id_tekanan_udaras= ""+response.body().getMain().getPressure();
                    id_kecepatan_angins = ""+response.body().getMain().getHumidity();
                    id_tempats = ""+response.body().getName();


                    for (int i = 0; i < response.body().getWeather().size(); i++) {
                    id_icon_infowindowss="http://openweathermap.org/img/w/"+response.body().getWeather().get(i).getIcon()+".png";
                    Log.d("Tampilkan",""+id_icon_infowindowss);
                    }
                } else {
                    Toast.makeText(context, "Upps, Gagal Untuk Mengambil data dari Weather", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCurrent> call, Throwable t) {
                Toast.makeText(context, "Upps, Sepertinya Koneksi Internet Anda Kurang Bagus", Toast.LENGTH_SHORT).show();
            }
        });

        id_temp_saat_ini.setText(id_temp_saat_inis);
        id_kedalaman_laut.setText(id_kedalaman_lauts);
        id_bawah_laut.setText(id_bawah_lauts);
        id_tekanan_udara.setText(id_tekanan_udaras);
        id_kecepatan_angin.setText(id_kecepatan_angins);
        id_tempat.setText(id_tempats);


        Picasso.get().load(String.valueOf(id_icon_infowindowss)).placeholder(R.drawable.ic_noimage).fit().into(id_icon_infowindows, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });


        tvLat.setText("" + latLng.latitude);
        tvLng.setText("" + latLng.longitude);
        return v;
    }
}
