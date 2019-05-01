package com.example.tatangit.lautannusantara.Library.Retrofit.Interface;


import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Current.ResponseCurrent;
import com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly.ResponseHourly;
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

    @POST("Lautan_Member/Member_Regisiter")
    @FormUrlEncoded
    Call<ResponseRegister> RegisterMember(
            @Field("nama_member") String nama_member,
            @Field("username_member") String username_member,
            @Field("password_member") String password_member,
            @Field("email_member") String email_member
    );

    @POST("Lautan_Member/Member_Login")
    @FormUrlEncoded
    Call<ResponseLogin> LoginMember(
            @Field("username_member") String username_member,
            @Field("password_member") String password_member
    );

    @PUT("Lautan_Member/Member_Update")
    @FormUrlEncoded
    Call<ResponseLogin> cProfil(
            @Field("nomor_member") String nomor_member,
            @Field("nama_member") String nama_member,
            @Field("username_member") String username_member,
            @Field("password_member") String password_member,
            @Field("email_member") String email_member
    );

    @GET("data/2.5/forecast/hourly")
    Call<ResponseHourly> cWeatherHurly(@Query("lat") String lat,
                                       @Query("lon") String lon,
                                       @Query("APPID") String appid);

    @GET("data/2.5/weather")
    Call<ResponseCurrent> cWeatherCurrent(@Query("lat") String lat,
                                          @Query("lon") String lon,
                                          @Query("APPID") String appid);



}
