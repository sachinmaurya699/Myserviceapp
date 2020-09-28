package com.sossolution.serviceonway.Class;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class My_notification extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        createnotification();
    }

    private void createnotification()
    {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel= new NotificationChannel(Constants.CHANNEL_ID,Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }

    }
}
