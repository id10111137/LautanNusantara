package com.example.tatangit.lautannusantara.Home.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.ListItem;
import com.example.tatangit.lautannusantara.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class Adapter_InfoWindows  implements GoogleMap.InfoWindowAdapter {

    Context context;

    public Adapter_InfoWindows(Context context) {
        this.context = context;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        // Getting view from the layout file info_window_layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_infowindow, null);

        // Getting the position from the marker
//        LatLngBean bean=hashMapMarker.get(marker);

//        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
//        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
//        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
//
//        tv_title.setText("Title:" + bean.getTitle());
//        tvLat.setText("Latitude:" + bean.getLatitude());
//        tvLng.setText("Longitude:"+ bean.getLongitude());

        // Returning the view containing InfoWindow contents
        return v;
    }


}
