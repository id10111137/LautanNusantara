package com.example.tatangit.lautannusantara.Library.Retrofit.Utils;
import com.example.tatangit.lautannusantara.Library.Retrofit.Api.Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;

public class Utils {

    public static final String BASE_URL = "http://apilautan.umrota.com/index.php/";


    public static Interface_Api getSOService(String p) {
        return Api.getClient(BASE_URL).create(Interface_Api.class);
    }



}
