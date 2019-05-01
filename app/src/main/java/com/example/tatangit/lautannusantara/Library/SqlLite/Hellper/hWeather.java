package com.example.tatangit.lautannusantara.Library.SqlLite.Hellper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tatangit.lautannusantara.Library.SqlLite.Interface.iWeather;
import com.example.tatangit.lautannusantara.Library.SqlLite.Model.mWeather;

import java.sql.SQLException;
import java.util.ArrayList;

public class hWeather extends SQLiteOpenHelper implements iWeather {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Db_Weather";
    private static final String TABLE_WEATHER = "t_weather";

    private static final String KEY_NOWEATHER = "NoWeather";
    private static final String KEY_LAT = "Latitude";
    private static final String KEY_LONG = "Longtitude";
    private static final String KEY_TANGGAL = "Tanggal";
    private static final String KEY_TEMPERATURE = "Temperature";
    private static final String KEY_HUMIDITY = "humidity";
    private static final String KEY_PRESSURE = "pressure";
    private static final String KEY_SEA = "sea_level";
    private static final String KEY_GRNDLEVEL = "grnd_level";
    private static final String KEY_TMPKF = "temp_kf";

    public hWeather(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BUKU_TABLE =
                "CREATE TABLE " + TABLE_WEATHER +
                        "("
                        + KEY_NOWEATHER +
                        " INTEGER PRIMARY KEY,"
                        + KEY_LAT +
                        " TEXT,"
                        + KEY_LONG +
                        " TEXT,"
                        + KEY_TANGGAL +
                        " TEXT,"
                        + KEY_TEMPERATURE +
                        " TEXT,"
                        + KEY_HUMIDITY +
                        " TEXT,"
                        + KEY_PRESSURE +
                        " TEXT,"
                        + KEY_SEA +
                        " TEXT,"
                        + KEY_GRNDLEVEL +
                        " TEXT,"
                        + KEY_TMPKF +
                        " TEXT" +
                        ")";
        db.execSQL(CREATE_BUKU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER);
        onCreate(db);
    }


    @Override
    public void addWeather(mWeather mweather) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOWEATHER, mweather.getNoWeather());
        values.put(KEY_LAT, mweather.getLatitude());
        values.put(KEY_LONG, mweather.getLongtitude());
        values.put(KEY_TANGGAL, mweather.getTanggal());
        values.put(KEY_TEMPERATURE, mweather.getTemperature());
        values.put(KEY_HUMIDITY, mweather.getHumidity());
        values.put(KEY_PRESSURE, mweather.getPressure());
        values.put(KEY_SEA, mweather.getSea_level());
        values.put(KEY_GRNDLEVEL, mweather.getGrnd_level());
        values.put(KEY_TMPKF, mweather.getTemp_kf());

        db.insert(TABLE_WEATHER, null, values);
        db.close();
    }

    @Override
    public ArrayList<mWeather> getWeather() throws SQLException {

        ArrayList<mWeather> listWeather = new ArrayList<mWeather>();
        String query = "SELECT * FROM " + TABLE_WEATHER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mWeather weathers = new mWeather();
                weathers.setNoWeather(Integer.valueOf(cursor.getString(0)));
                weathers.setLatitude(cursor.getString(1));
                weathers.setLongtitude(cursor.getString(2));
                weathers.setTanggal(cursor.getString(3));
                weathers.setTemperature(cursor.getString(4));
                weathers.setHumidity(cursor.getString(5));
                weathers.setPressure(cursor.getString(6));
                weathers.setSea_level(cursor.getString(7));
                weathers.setGrnd_level(cursor.getString(8));
                weathers.setTemp_kf(Double.valueOf(cursor.getString(9)));
                listWeather.add(weathers);
            } while (cursor.moveToNext());
        }
        return listWeather;
    }

    @Override
    public ArrayList<mWeather> updateWeather(mWeather weather) throws SQLException {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_LAT, weather.getLatitude());
        values.put(KEY_LONG, weather.getLongtitude());
        values.put(KEY_TANGGAL, weather.getTanggal());
        values.put(KEY_TEMPERATURE, weather.getTemperature());
        values.put(KEY_HUMIDITY, weather.getHumidity());
        values.put(KEY_PRESSURE, weather.getPressure());
        values.put(KEY_SEA, weather.getSea_level());
        values.put(KEY_GRNDLEVEL, weather.getGrnd_level());
        values.put(KEY_TMPKF, weather.getTemp_kf());
        db.update(TABLE_WEATHER, values, KEY_NOWEATHER+"=?", new String[]{String.valueOf(weather.getNoWeather())});
        db.close();


        ArrayList<mWeather> listWeather = new ArrayList<mWeather>();
        String query = "SELECT * FROM " + TABLE_WEATHER;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mWeather weathers = new mWeather();
                weathers.setNoWeather(Integer.valueOf(cursor.getString(0)));
                weathers.setLatitude(cursor.getString(1));
                weathers.setLongtitude(cursor.getString(2));
                weathers.setTanggal(cursor.getString(3));
                weathers.setTemperature(cursor.getString(4));
                weathers.setHumidity(cursor.getString(5));
                weathers.setPressure(cursor.getString(6));
                weathers.setSea_level(cursor.getString(7));
                weathers.setGrnd_level(cursor.getString(8));
                weathers.setTemp_kf(Double.valueOf(cursor.getString(9)));
                listWeather.add(weathers);
            } while (cursor.moveToNext());
        }
        return listWeather;


    }


    @Override
    public String delWeather(String NoWeather) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEATHER, KEY_NOWEATHER + "=?", new String[]{String.valueOf(NoWeather)});
        db.close();
        return null;
    }


}
