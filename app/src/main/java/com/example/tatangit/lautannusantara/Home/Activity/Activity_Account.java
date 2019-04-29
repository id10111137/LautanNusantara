package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Response.ResponseLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Account extends AppCompatActivity {

    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;

    @BindView(R.id.id_nama_member)
    EditText id_nama_member;
    @BindView(R.id.id_username_member)
    EditText id_username_member;
    @BindView(R.id.id_password_member)
    EditText id_password_member;
    @BindView(R.id.id_email_member)
    EditText id_email_member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Pengaturan Account");

        interface_api = Utils.getSOService("p");
        messageItemLogin = ModelManager.getInstance(getApplicationContext()).getUser();

        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_back));
        id_icon_toolbar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(null);
        toolbar_iconView.setOnClickListener(null);




        /*
            Loading
         */
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Mohon Menunggu");
        pDialog.setCancelable(false);



        /*
            Session
        */

        try {
            if (!ModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
                startActivity(new Intent(getApplicationContext(), Activity_Login.class));
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
            set Profil
         */
        goSetProfil();
    }


    private void goSetProfil(){
        id_nama_member.setText(messageItemLogin.getNamaMember());
        id_username_member.setText(messageItemLogin.getUsernameMember());
        id_password_member.setText(messageItemLogin.getPasswordMember());
        id_email_member.setText(messageItemLogin.getEmailMember());
    }



    @OnClick(R.id.id_goProfil)
    public void goChangeProfil() {

        pDialog.show();
        if (id_nama_member.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_nama_member.setError("Sepertinya Nama Anda Belum Terisi");
        } else if (id_username_member.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_username_member.setError("Sepertinya Username Anda Belum Terisi");
        } else if (id_password_member.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_username_member.setError("Sepertinya Password Anda Belum Terisi");
        } else if (id_email_member.getText().toString().isEmpty()) {
            pDialog.dismiss();
            id_email_member.setError("Sepertinya Email Anda Belum Terisi");
        }else{
            Log.d("Tampilkan",
                    "Nomor Member : "+messageItemLogin.getNomorMember()+
            " Nama Member : "+id_nama_member.getText().toString()+
            " Username : "+id_username_member.getText().toString()+
            " Password : "+id_password_member.getText().toString()+
            " Email : "+id_email_member.getText().toString());

            pDialog.dismiss();
            interface_api.cProfil(messageItemLogin.getNomorMember(),id_nama_member.getText().toString(),id_username_member.getText().toString(),id_password_member.getText().toString(),id_email_member.getText().toString()).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.isSuccessful()) {
                        pDialog.dismiss();
                        final List<MessageItemLogin> lLogin = response.body().getMessage();
                        for (int i = 0; i < lLogin.size(); i++) {
                            modelLogin = new MessageItemLogin(lLogin.get(i).getEmailMember(),
                                    lLogin.get(i).getNomorMember(),
                                    lLogin.get(i).getNamaMember(),
                                    lLogin.get(i).getPasswordMember(),
                                    lLogin.get(i).getUsernameMember(),
                                    lLogin.get(i).getStatusMember());
                        }

                        ModelManager.getInstance(getApplicationContext()).UserLogin(modelLogin);
                        goSetProfil();

                    } else {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Untuk Menghubungkan Ke Server", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    pDialog.dismiss();
                    Log.d("Tampilkan",""+t.getMessage());
                    Toast.makeText(getApplicationContext(), "Ups, Mungkin Koneksi Anda Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}