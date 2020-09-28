package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sossolution.serviceonway.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notification_Helper
{

    private Context context;
    private static final int id=100;
    int color = Color.argb(255, 228, 14, 18);
    Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public  Notification_Helper(Context context1)
    {
        this.context=context1;
    }

    public void trigared(String title, String body,String image)
    {

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,Constants.CHANNEL_ID)
                           .setContentTitle(title)
                           .setContentText(body)
                           .setSmallIcon(R.mipmap.ic_launcher)
                           .setColor(color)
                           .setSound(soundUri)
                           .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getBitmapFromURL(image)))
                           .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat managerCompat=  NotificationManagerCompat.from(context);
        managerCompat.notify(id,builder.build());


    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } // Autho



}
