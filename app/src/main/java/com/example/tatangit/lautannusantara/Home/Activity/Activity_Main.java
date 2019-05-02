package com.example.tatangit.lautannusantara.Home.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tatangit.lautannusantara.Library.Notification.H_Notification;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class Activity_Main extends AppCompatActivity {

    Intent mIntent;
    SweetAlertDialog pDialog;

    Interface_Api interface_api;
    MessageItemLogin modelLogin;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;
    H_Notification h_notification;

    @BindView(R.id.slider)
    Slider slider;

    @BindView(R.id.id_nama)
    TextView id_nama;

    @BindView(R.id.id_email)
    TextView id_email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Lautan Nusantara");

        interface_api = Utils.getSOService("p");
        messageItemLogin = ModelManager.getInstance(getApplicationContext()).getUser();
        h_notification = new H_Notification();

        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_info));
        id_icon_toolbar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent = new Intent(getApplicationContext(), Activity_OpenSource.class);
                startActivity(mIntent);
            }
        });

        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_logout));
        toolbar_iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h_notification.eNotif(getApplicationContext(),"Logout","Lautan Nusantara","Terima Kasih Telah Berkunjung");
                ModelManager.getInstance(getApplicationContext()).LogOut();
                finish();
            }
        });

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
            Slider
        */
        List<Slide> slideList = new ArrayList<>();
        slideList.add(new Slide(0, "https://1.bp.blogspot.com/-Oi9joFk4yAQ/Ves8VWsqxJI/AAAAAAAAEB0/YVmz_ieSVnI/s1600/Kepulauan%2BBangka%2BBelitung%2B%2B%2BGoogle%2BMaps.png", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        slideList.add(new Slide(1, "https://cdn1-production-images-kly.akamaized.net/0BgwGVvDH-mCo1AG1PcVB6olafw=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/1965174/original/006224900_1520306009-Kepulauan_bangka.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        slideList.add(new Slide(2, "https://2.bp.blogspot.com/-cOrcLpjNZmU/WYls4KOyCFI/AAAAAAAABak/Dz5udhQWcQsI0VoDTC84b8GV8vqAB3uXQCLcBGAs/s1600/IMG_7624.JPG", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));

        slider.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //do what you want
            }
        });
        slider.addSlides(slideList);

        setProfil();
    }

    private void setProfil() {
        id_nama.setText("Hai, " + messageItemLogin.getNamaMember());
        id_email.setText(messageItemLogin.getEmailMember());
    }


    @OnClick(R.id.id_account)
    public void goAccount() {
        mIntent = new Intent(getApplicationContext(), Activity_Account.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_calculator)
    public void goCalculator() {
        mIntent = new Intent(getApplicationContext(), Activity_Calculator.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_pesisir)
    public void goPesisir() {
        mIntent = new Intent(getApplicationContext(), Activity_Pesisir.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_kelautan)
    public void goKelautan() {
        mIntent = new Intent(getApplicationContext(), Activity_Kelautan.class);
        startActivity(mIntent);
    }


    @OnClick(R.id.id_laut_lepas)
    public void goLutLepas() {
        mIntent = new Intent(getApplicationContext(), Activity_LautLepas.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_perairan_khusus)
    public void goPerairanKhusus() {
        mIntent = new Intent(getApplicationContext(), Activity_PerairanKhusus.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_tangkapan)
    public void goTangkapan() {
        mIntent = new Intent(getApplicationContext(), Activity_Tangkapan.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.id_satuan)
    public void goSatuan() {
        mIntent = new Intent(getApplicationContext(), Activity_Satuan.class);
        startActivity(mIntent);
    }


}
