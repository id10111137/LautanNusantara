package com.example.tatangit.lautannusantara.Library.Retrofit.Interface;


import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Current.ResponseCurrent;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ResponseHourly;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseKordinat;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Interface_Api {

    @POST("User/Member_Regisiter")
    @FormUrlEncoded
    Call<ResponseRegister> RegisterMember(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("User/Member_Login")
    @FormUrlEncoded
    Call<ResponseLogin> LoginMember(
            @Field("username") String username,
            @Field("password") String password
    );

    @PUT("User/Member_Update")
    @FormUrlEncoded
    Call<ResponseLogin> cProfil(
            @Field("id_user") String id_user,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("Kordinat/KordinatKlorofil")
    Call<ResponseKordinat> cKordinat();

    @GET("data/2.5/forecast")
    Call<ResponseHourly> cWeatherHurly(@Query("lat") String lat,
                                       @Query("lon") String lon,
                                       @Query("APPID") String appid);

    @GET("data/2.5/weather")
    Call<ResponseCurrent> cWeatherCurrent(@Query("lat") String lat,
                                          @Query("lon") String lon,
                                          @Query("APPID") String appid);
}
