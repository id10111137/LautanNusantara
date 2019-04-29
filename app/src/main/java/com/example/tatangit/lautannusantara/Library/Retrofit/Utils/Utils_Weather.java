package com.example.tatangit.lautannusantara.Library.Retrofit.Utils;

import com.example.tatangit.lautannusantara.Library.Retrofit.Api.Api_Weather;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;

public class Utils_Weather {

    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/";

    public static Interface_Api getWeather() {
        return Api_Weather.getClientWeather(BASE_URL_WEATHER).create(Interface_Api.class);
    }
}
