package com.surah.rahman.audio.app.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.surah.rahman.audio.app.Activities.ListenActivity;
import com.surah.rahman.audio.app.R;

import static com.surah.rahman.audio.app.Activities.ListenActivity.music;
import static com.surah.rahman.audio.app.Activities.ListenActivity.name;
import static com.surah.rahman.audio.app.Activities.ListenActivity.title;


public class MediaPlayerService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    private AudioManager audioManager;
    MediaPlayer mediaPlayer;
    static String TAG = "*-*";
    public static int current_pos;
    static int total_duration;
    static int duration;

    static long currentTime;
    RemoteViews notificationLayout;
    String action, pauseStop, name1, title1;
    Intent serviceIntent;

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        action = intent.getStringExtra("Class");
        name1 = name;
        title1 = title;
        Log.d(TAG, "name" + name);
        pauseStop = intent.getAction();
        notificationLayout = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification_view);
        notificationLayout.setTextViewText(R.id.nottitle, title);
        notificationLayout.setTextViewText(R.id.notname, name);

        Log.d(TAG, "onStartCommand: duration" + name);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();
        Log.d(TAG, "onStartCommand: " + name);

        Log.d(TAG, "onStartCommand: action" + action);
        if (pauseStop != null) {
            if (pauseStop.equals("pause")) {
                if (music != null) {
                    if (music.isPlaying()) {
                        music.pause();
                        notificationLayout.setImageViewResource(R.id.pause, R.drawable.play);
                        notificationLayout.setTextViewText(R.id.nottitle, title);
                        notificationLayout.setTextViewText(R.id.notname, name);

                    } else {
                        notificationLayout.setTextViewText(R.id.nottitle, title);
                        notificationLayout.setTextViewText(R.id.notname, name);
                        Log.d(TAG, "Hello" + name);
                        music.start();
                        notificationLayout.setImageViewResource(R.id.pause, R.drawable.pause);

                    }
                }
            } else if (pauseStop.equals("stop")) {
                if (music != null) {
                    Log.d(TAG, "onStartCommand: action" + "my call");
                    music.pause();
                    music.seekTo(0);

                    Intent stopIntent = new Intent(MediaPlayerService.this, MediaPlayerService.class);
                    this.stopForeground(true);
                    this.stopService(stopIntent);
                    onDestroy();

                }
            }
        }

        Intent notificationIntent = new Intent(this, ListenActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        notificationLayout.setOnClickPendingIntent(R.id.notlayout, pendingIntent);
        notificationLayout.setTextViewText(R.id.nottitle, title);
        notificationLayout.setTextViewText(R.id.notname, name);

        Intent pauseIntent = new Intent(MediaPlayerService.this, MediaPlayerService.class);
        String pause = "pause";
        pauseIntent.setAction(pause);
        PendingIntent pendingPauseIntent = PendingIntent.getService(MediaPlayerService.this, 0, pauseIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.pause, pendingPauseIntent);

        Intent stopIntent = new Intent(MediaPlayerService.this, MediaPlayerService.class);
        String stop = "stop";
        stopIntent.setAction(stop);
        PendingIntent pendingStopIntent = PendingIntent.getService(MediaPlayerService.this, 0, stopIntent, 0);
        notificationLayout.setOnClickPendingIntent(R.id.cancel_btn, pendingStopIntent);

        notificationLayout.setTextViewText(R.id.nottitle, title);
        notificationLayout.setTextViewText(R.id.notname, name);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)

                .setSmallIcon(R.drawable.logo)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContent(notificationLayout)
                .setWhen(System.currentTimeMillis())
                .setSilent(true)
                .build();
        startForeground(1, notification);
        notification.defaults = 0;
        return START_NOT_STICKY;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    //CREAT NOTIFICATION
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}