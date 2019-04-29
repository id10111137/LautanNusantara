package com.example.tatangit.lautannusantara.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tatangit.lautannusantara.R;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_InfoLautan extends Fragment {


    Intent mIntent;
    Toolbar toolbar;
    CircleImageView toolbar_iconView;
    View root;

    public Fragment_InfoLautan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
