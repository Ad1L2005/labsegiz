package com.example.labsegiz;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RandomCharacterService extends Service {
    private boolean isRandomGeneratorOn;
    private final String TAG = "RandomCharacterService";
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static final String ACTION_TAG = "my.custom.action.tag.lab6";

    private void stopRandomGenerator() {
        isRandomGeneratorOn = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service destroyed...");
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
