package com.example.myapplication.Class;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.Fragment.ReminderFragment;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "CHANNEL_SAMPLE";
    @Override
    public void onReceive(Context context, Intent intent) {
        // Kiểm tra xem on hay off
        String action = intent.getExtras().getString("action");
        Log.e("Tôi trong AlarmService", "Xin chao");
        Log.e("Hành động", action);

        // Hiện thông báo
        if (action.equals("on")) {
            // Get message
            int notificationId = intent.getIntExtra("notificationId", 0);
            String name = intent.getExtras().getString("name");

            // Call MainActivity when notification is tapped.
            Intent moveToMain = new Intent(context, ReminderFragment.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, moveToMain, 0);

            // NotificationManager
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For API 26 and above
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH
                );
                notificationManager.createNotificationChannel(channel);
            }

            // Prepare Notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Nhắc nhở")
                    .setContentText(name)
                    .setContentIntent(contentIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setAutoCancel(true);

            // Notify
            notificationManager.notify(notificationId, builder.build());
        }

        Intent soundIntent = new Intent(context, AlarmSound.class);
        soundIntent.putExtra("action", action);
        context.startService(soundIntent);
    }
}
