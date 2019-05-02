package com.example.tatangit.lautannusantara.Library.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.example.tatangit.lautannusantara.R;

public class H_Notification {

    public H_Notification(){}

    public void eNotif(Context mcontext, String Title, String Subject ,String Content){

        NotificationManager notif=(NotificationManager)mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (mcontext.getApplicationContext()).setContentTitle(Title).setContentText(Content).
                setContentTitle(Subject).setSmallIcon(R.drawable.ic_location).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);

    }
}
