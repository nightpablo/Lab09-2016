package com.example.pablo.lab09_2016.Servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.pablo.lab09_2016.MainActivity;
import com.example.pablo.lab09_2016.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Pablo on 24/11/2016.
 */

public class Notificacion extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("FIREBASE",remoteMessage.getNotification().getBody());
        NotificationManager nManager = (NotificationManager) getBaseContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent targetIntent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0,
                targetIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getBaseContext())
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentIntent(contentIntent)
                .setContentTitle("Mensaje de Firebase")
                .setContentText(remoteMessage.getNotification().getBody())
                .setWhen(System.currentTimeMillis());




        builder.setAutoCancel(true);
        nManager.notify(123456,builder.build());
    }
}
