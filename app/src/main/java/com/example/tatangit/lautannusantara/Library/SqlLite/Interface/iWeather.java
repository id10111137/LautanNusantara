package com.example.tatangit.lautannusantara.Library.SqlLite.Interface;

import com.example.tatangit.lautannusantara.Library.SqlLite.Model.mWeather;

import java.sql.SQLException;
import java.util.ArrayList;

public interface iWeather {

    void addWeather(mWeather mweather) throws SQLException;

    ArrayList<mWeather> getWeather() throws SQLException;

    ArrayList<mWeather> updateWeather(mWeather weather) throws SQLException;

    String delWeather(String NoWeather) throws SQLException;


}
