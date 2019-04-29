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
        editor.putString("nomor_member", modelUserItem.getNomorMember());
        editor.putString("nama_member", modelUserItem.getNamaMember());
        editor.putString("username_member", modelUserItem.getUsernameMember());
        editor.putString("password_member", modelUserItem.getPasswordMember());
        editor.putString("email_member", modelUserItem.getEmailMember());
        editor.putString("status_member", modelUserItem.getStatusMember());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        return sharedPreferences.getString("nomor_member", null) != null;
    }

    public MessageItemLogin getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LautanNusantara", Context.MODE_PRIVATE);
        return new MessageItemLogin(
                sharedPreferences.getString("nomor_member", null),
                sharedPreferences.getString("nama_member", null),
                sharedPreferences.getString("username_member", null),
                sharedPreferences.getString("password_member", null),
                sharedPreferences.getString("email_member", null),
                sharedPreferences.getString("status_member", null));

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
