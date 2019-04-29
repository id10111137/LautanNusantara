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
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseRegister;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Register extends AppCompatActivity {

    Intent mIntent;
    SweetAlertDialog pDialog;


    @BindView(R.id.id_fullname)
    EditText id_fullname;
    @BindView(R.id.id_username)
    EditText id_username;
    @BindView(R.id.id_password)
    EditText id_password;
    @BindView(R.id.id_cpassword)
    EditText id_cpassword;
    @BindView(R.id.id_email)
    EditText id_email;

    Interface_Api interface_api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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


    @OnClick(R.id.id_goSimpan)
    public void goSimpan() {
        pDialog.show();

        if (id_fullname.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_fullname.setError("Sepertinya Nama Belum Terisi");
        } else if (id_username.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_username.setError("Sepertinya Username Belum Terisi");
        } else if (id_password.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_password.setError("Sepertinya Password Belum Terisi");
        } else if (id_cpassword.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_cpassword.setError("Sepertinya Password Confirmation Belum Terisi");
        } else if (id_email.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_email.setError("Sepertinya Email Belum Terisi");
        } else if (!id_password.getText().toString().equalsIgnoreCase(id_cpassword.getText().toString())) {
            pDialog.dismiss();
            id_cpassword.setError("Sepertinya Password anda tidak sama");
        } else {

            interface_api.RegisterMember(id_fullname.getText().toString(), id_username.getText().toString(), id_password.getText().toString(), id_email.getText().toString()).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    Log.d("Tampilkan", "" + response.message().toString());
                    if (response.isSuccessful()) {
                        pDialog.dismiss();
                        Toast.makeText(Activity_Register.this, "Terima Kasih Pendaftaran Sudah Berhasil, Silahkan Login ", Toast.LENGTH_SHORT).show();
                        ResetData();
                    } else {
                        pDialog.dismiss();
                        Toast.makeText(Activity_Register.this, "Mohon Maaf, Registrasi Anda Gagal. Mohon Coba Lain Kali ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    pDialog.dismiss();
                    Toast.makeText(Activity_Register.this, "Upps,Sepertinya Jaringan Internet anda bermasalah", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @OnClick(R.id.id_goMasuk)
    public void goMasuk() {
        mIntent = new Intent(getApplicationContext(), Activity_Login.class);
        startActivity(mIntent);
    }


    private void ResetData() {
        id_fullname.setText(null);
        id_username.setText(null);
        id_password.setText(null);
        id_cpassword.setText(null);
        id_email.setText(null);
    }
}
