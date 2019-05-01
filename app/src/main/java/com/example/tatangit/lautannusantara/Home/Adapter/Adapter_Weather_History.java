package com.example.tatangit.lautannusantara.Home.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ListItemHourly;
import com.example.tatangit.lautannusantara.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Weather_History extends ArrayAdapter<ListItemHourly> {

    private Context context;
    private List<ListItemHourly> dataSet = null;
    private ArrayList<ListItemHourly> originDataSet = null;
    LayoutInflater inflater;

    public Adapter_Weather_History(List<ListItemHourly> data, Context context) {
        super(context, R.layout.fragment_cuaca_item, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<>();
        this.originDataSet.addAll(data);
    }

    private static class ViewHolder {

        ImageView id_icon_weather;
        TextView id_clouds;
        TextView id_description;
        TextView id_tanggal;
        TextView id_temp;
        TextView id_temp_min;
        TextView id_temp_max;
        TextView id_pressure;
        TextView id_sea_level;
        TextView id_grnd_level;
        TextView id_wind;


    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ListItemHourly getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final ListItemHourly dataModel = getItem(position);
        final ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_cuaca_item, parent, false);

            viewHolder.id_icon_weather = convertView.findViewById(R.id.id_icon_weather);
            viewHolder.id_clouds = convertView.findViewById(R.id.id_clouds);
            viewHolder.id_description = convertView.findViewById(R.id.id_description);
            viewHolder.id_tanggal = convertView.findViewById(R.id.id_tanggal);
            viewHolder.id_temp = convertView.findViewById(R.id.id_temp);
            viewHolder.id_temp_min = convertView.findViewById(R.id.id_temp_min);
            viewHolder.id_temp_max = convertView.findViewById(R.id.id_temp_max);
            viewHolder.id_pressure = convertView.findViewById(R.id.id_pressure);
            viewHolder.id_sea_level = convertView.findViewById(R.id.id_sea_level);
            viewHolder.id_grnd_level = convertView.findViewById(R.id.id_grnd_level);
            viewHolder.id_wind = convertView.findViewById(R.id.id_wind);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        assert dataModel != null;


        for (int i = 0; i < dataModel.getWeather().size(); i++) {
            viewHolder.id_clouds.setText(dataModel.getWeather().get(i).getMain());
            viewHolder.id_description.setText(dataModel.getWeather().get(i).getDescription());
            Picasso.get().load(String.valueOf("http://openweathermap.org/img/w/" + dataModel.getWeather().get(i).getIcon() + ".png")).placeholder(R.drawable.ic_noimage).fit().into(viewHolder.id_icon_weather, new Callback() {
                @Override
                public void onSuccess() {

                }
                @Override
                public void onError(Exception e) {

                }
            });
        }


        Log.d("Tampilkan-coy", "" + position);
        viewHolder.id_tanggal.setText(dataModel.getDtTxt());
        viewHolder.id_temp.setText(Double.toString(dataModel.getMain().getTemp()));
        viewHolder.id_temp_min.setText(Double.toString(dataModel.getMain().getTempMin()));
        viewHolder.id_temp_max.setText(Double.toString(dataModel.getMain().getTempMax()));
        viewHolder.id_pressure.setText(Double.toString(dataModel.getMain().getPressure()));
        viewHolder.id_sea_level.setText(Double.toString(dataModel.getMain().getSeaLevel()));
        viewHolder.id_grnd_level.setText(Double.toString(dataModel.getMain().getGrndLevel()));
        viewHolder.id_wind.setText(Double.toString(dataModel.getWind().getSpeed()));


        return convertView;
    }
}
