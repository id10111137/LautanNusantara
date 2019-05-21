package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;

public class ModelManager {

    @SuppressLint("StaticFieldLeak")
    private static ModelManager mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private ModelManager(Context context) {
        mContext = context;
    }

    public static synchronized ModelManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ModelManager(context);
        }
        return mInstance;
    }

    public void UserLogin(MessageItemLogin modelUserItem) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id_user", modelUserItem.getId_user());
        editor.putString("username", modelUserItem.getUsername());
        editor.putString("password", modelUserItem.getPassword());
        editor.putString("role", modelUserItem.getRole());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        return sharedPreferences.getString("id_user", null) != null;
    }

    public MessageItemLogin getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        return new MessageItemLogin(
                sharedPreferences.getString("id_user", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("role", null));

    }

    public void LogOut() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(mContext, Activity_Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }
}
