package com.example.tatangit.lautannusantara.SignUp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Activity.Activity_Main;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Login extends AppCompatActivity {

    Intent mIntent;
    SweetAlertDialog pDialog;
    @BindView(R.id.id_username)
    EditText id_username;
    @BindView(R.id.id_password)
    EditText id_password;

    Interface_Api interface_api;
    MessageItemLogin modelLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        interface_api = Utils.getSOService("p");

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Mohon Menunggu");
        pDialog.setCancelable(false);

        if (ModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), Activity_Main.class));
            finish();
        }

    }


    @OnClick(R.id.id_goLogin)
    public void goLogin() {

        pDialog.show();
        if (id_username.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_username.setError("Sepertinya Username Belum Terisi");
        } else if (id_password.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_password.setError("Sepertinya Password Belum Terisi");
        } else {
            pDialog.dismiss();
            /*
                Action Login
             */
            interface_api.LoginMember(id_username.getText().toString(), id_password.getText().toString()).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.isSuccessful()) {
                        pDialog.dismiss();
                        final List<MessageItemLogin> lLogin = response.body().getMessage();
                        for (int i = 0; i < lLogin.size(); i++) {
                            modelLogin = new MessageItemLogin(
                                    lLogin.get(i).getNomorMember(),
                                    lLogin.get(i).getNamaMember(),
                                    lLogin.get(i).getUsernameMember(),
                                    lLogin.get(i).getPasswordMember(),
                                    lLogin.get(i).getEmailMember(),
                                    lLogin.get(i).getStatusMember());
                        }

                        ModelManager.getInstance(getApplicationContext()).UserLogin(modelLogin);
                        mIntent = new Intent(getApplicationContext(), Activity_Main.class);
                        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(mIntent);

                    } else {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Untuk Menghubungkan Ke Server", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure (Call < ResponseLogin > call, Throwable t){
                pDialog.dismiss();
                Log.d("Tampilkan", "" + t.toString());
                Toast.makeText(Activity_Login.this, "Upps,Sepertinya Jaringan Internet anda bermasalah", Toast.LENGTH_SHORT).show();

            }
        });

    }

}


    @OnClick(R.id.id_Goregister)
    public void goRegister() {
        mIntent = new Intent(getApplicationContext(), Activity_Register.class);
        startActivity(mIntent);
    }
}
