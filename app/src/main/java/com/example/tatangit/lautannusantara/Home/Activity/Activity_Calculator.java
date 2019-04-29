package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_Calculator extends AppCompatActivity {

    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;

    @BindView(R.id.id_lykalkulasi)
    LinearLayout id_lykalkulasi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Hitung BBM");

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


        id_lykalkulasi.setVisibility(View.GONE);

    }


    @OnClick(R.id.id_goHitung)
    public void goHitung(){
        id_lykalkulasi.setVisibility(View.VISIBLE);
    }

}
