package com.example.tatangit.lautannusantara.Library.SqlLite.Model;

public class mWeather {

    int NoWeather;
    String Latitude;
    String Longtitude;
    String Tanggal;
    String Temperature;
    String IconSet;
    String humidity;
    String pressure;
    String sea_level;
    String grnd_level;
    Double temp_kf;

    public mWeather(){}

    public mWeather(int noWeather, String latitude, String longtitude, String tanggal, String temperature, String iconSet, String humidity, String pressure, String sea_level, String grnd_level, Double temp_kf) {
        NoWeather = noWeather;
        Latitude = latitude;
        Longtitude = longtitude;
        Tanggal = tanggal;
        Temperature = temperature;
        IconSet = iconSet;
        this.humidity = humidity;
        this.pressure = pressure;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
        this.temp_kf = temp_kf;
    }


    public int getNoWeather() {
        return NoWeather;
    }

    public void setNoWeather(int noWeather) {
        NoWeather = noWeather;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getIconSet() {
        return IconSet;
    }

    public void setIconSet(String iconSet) {
        IconSet = iconSet;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSea_level() {
        return sea_level;
    }

    public void setSea_level(String sea_level) {
        this.sea_level = sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(String grnd_level) {
        this.grnd_level = grnd_level;
    }

    public Double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(Double temp_kf) {
        this.temp_kf = temp_kf;
    }




}
