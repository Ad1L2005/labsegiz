package com.example.labsegiz;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer soundPlayer;
    private static final String CHANNEL_ID = "foreground_service_channel";
    private NotificationManager notificationManager;


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (soundPlayer != null) {
            soundPlayer.stop();
            soundPlayer.release();
            soundPlayer = null;
        }
        Toast.makeText(this, "Сервис остановлен", Toast.LENGTH_SHORT).show();
        Log.i("MyService", "Foreground service stopped...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
