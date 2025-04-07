package com.example.labsegiz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private MediaPlayer soundPlayer;
    private static final String CHANNEL_ID = "foreground_service_channel";
    private NotificationManager notificationManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Сервис запущен", Toast.LENGTH_SHORT).show();
        Log.i("MyService", "Foreground service started...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Foreground Music Service";
            String channelDescription = "Channel for music playback";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle("Музыкальный сервис")
                .setContentText("Музыка играет...")
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        startForeground(1, builder.build());

        // Убеждаемся, что музыка продолжает играть
        try {
            if (soundPlayer != null && !soundPlayer.isPlaying()) {
                soundPlayer.start();
            }
        } catch (Exception e) {
            Log.e("MyService", "Ошибка при запуске музыки", e);
        }

        return START_STICKY;
    }

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
