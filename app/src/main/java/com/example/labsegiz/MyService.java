package com.example.labsegiz;

import android.app.NotificationManager;
import android.media.MediaPlayer;

public class MyService {
    private MediaPlayer soundPlayer;
    private static final String CHANNEL_ID = "foreground_service_channel";
    private NotificationManager notificationManager;
}
