package com.example.myapplication.Class;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class AlarmSound extends Service {
    MediaPlayer mediaPlayer;
    int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Tôi trong AlarmSound", "Xin chao");

        String action = intent.getExtras().getString("action");
        Log.e("Nhận hành động: ", action);

        if (action.equals("on")) {
            id = 1;
        } else if (action.equals("off")) {
            id = 0;
        }

//        mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
        // Nếu id = 1 thì phát nhạc
        if (id == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
            mediaPlayer.start();
            id = 0;
        } else if (id == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }
}
