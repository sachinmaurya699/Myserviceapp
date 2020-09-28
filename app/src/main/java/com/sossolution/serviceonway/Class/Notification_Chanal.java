package com.sossolution.serviceonway.Class;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.sossolution.serviceonway.Activity.MainActivity;
import com.sossolution.serviceonway.R;

 public class Notification_Chanal extends ContextWrapper{

    //create chanal
    private final static String Channal_name="Main Notifications";
    private final static String Channal_id="MyNotificationChannel";
    private final static String CHANNEL_DESCRIPTION="Notification description";
    private static NotificationManager notificationManager;
    private Context base;

    public Notification_Chanal(Context base)
    {
        super(base);
        this.base = base;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createChannel();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel()
    {

        NotificationChannel notificationChannel = new NotificationChannel(Channal_id,Channal_name, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.canShowBadge();
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        notificationChannel.setLightColor(getResources().getColor(R.color.colorAccent));
        getNotificationManager().createNotificationChannel(notificationChannel);





    }

    private NotificationManager getNotificationManager()
    {
        if (notificationManager == null)
        {
            notificationManager = (NotificationManager) base.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }
    public void notify(String message, String title)
    {


        Intent intent= new Intent(base, MainActivity.class);

        PendingIntent intent1= PendingIntent.getActivity(base,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(base,Channal_id);
        Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.imglogo);
        builder.setLargeIcon(BitmapFactory. decodeResource (getResources() , R.drawable.logo4 )) ;
        builder.setColor(ContextCompat.getColor(base, R.color.colorAccent));
        //PendingIntent intent= new PendingIntent(MainActivity.class);
        // builder.setContentText("Your booking has been Sent for confirmation. once get confirmed, You will receive a message shortly");
        builder.setContentIntent(intent1);
        builder.setSound(notificationUri);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        getNotificationManager().notify(9001, builder.build());
    }




}
