package com.surah.rahman.audio.app.Activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.androidnetworking.AndroidNetworking;
import com.surah.rahman.audio.app.R;
import com.surah.rahman.audio.app.Services.MediaPlayerService;

import java.io.File;
import java.io.IOException;

public class ListenActivity extends AppCompatActivity {
    ImageView Play;
    static public String name, url, title;
    File directory;
    ImageView imageView;
    LottieAnimationView lottieAnimationView;
    public static String servicename, servicetitle;
    private Runnable mRunnable;
    static SeekBar mseekBar;
    static TextView currentsong;
    ImageView splash;
    static TextView duration, d1;
    static TextView text;
    int oneTimeOnly = 0;
    private Handler mHandler;
    String TAG = "***ABC";
    static int current_pos;
    int variable = 1;
    Intent myService;
    ProgressBar progressBar;
    static String currentLink;


    public static MediaPlayer music;
    static int total_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        AndroidNetworking.initialize(getApplicationContext());
        Intent notintent = getIntent();


        Intent Downloader = getIntent();
        name = Downloader.getStringExtra("name");
        url = Downloader.getStringExtra("url");
        title = Downloader.getStringExtra("title");


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        mHandler = new Handler();
        File path = Environment.getRootDirectory();
        Intent notification = getIntent();


        currentLink = "";


        duration = (TextView) findViewById(R.id.tv_duration);
        d1 = (TextView) findViewById(R.id.tv_pass);
        mseekBar = (SeekBar) findViewById(R.id.seekbar);
        Play = (ImageView) findViewById(R.id.play);
        imageView = (ImageView) findViewById(R.id.img);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animationView);
        currentsong = (TextView) findViewById(R.id.playing);

        if (url == null) {
            url = currentLink;
            name = servicename;
            title = servicetitle;
            currentsong.setText(title);

        }
        if (music == null) {

            try {


                music = new MediaPlayer();
                music.setAudioStreamType(AudioManager.STREAM_MUSIC);
                music.setDataSource(url);
                music.prepare();
                music.start();
                Play.setImageResource(R.drawable.pause);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (music != null) {
            if (music.isPlaying()) {
                if (!url.equals(currentLink)) {
                    try {
                        music.stop();
                        music.reset();
                        music.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        music.setDataSource(url);
                        music.prepare();
                        music.start();
                        Play.setImageResource(R.drawable.pause);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    music.stop();
                    music.reset();
                    music.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    music.setDataSource(url);
                    music.prepare();
                    music.start();
                    Play.setImageResource(R.drawable.pause);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        if (music != null && music.isPlaying()) {
            Play.setImageResource(R.drawable.pause);
            imageView.setVisibility(View.INVISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            myService = new Intent(ListenActivity.this, MediaPlayerService.class);
            myService.putExtra("title", title);
            myService.putExtra("name", name);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(myService);
            } else {
                startService(myService);
            }

        }


        setAudioProgress();
        setseekbar();
        currentsong.setText(title);

        currentsong.setVisibility(View.VISIBLE);


        Play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (music.isPlaying()) {

                    music.pause();
                    imageView.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.INVISIBLE);

                    Play.setImageResource(R.drawable.play);
                    Log.d(TAG, "oncreate********");
                    myService = new Intent(ListenActivity.this, MediaPlayerService.class);
                    stopService(myService);
                } else {
                    music.start();
                    Play.setImageResource(R.drawable.pause);
                    Log.d(TAG, "oncreate********yres");
                    imageView.setVisibility(View.INVISIBLE);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    myService = new Intent(ListenActivity.this, MediaPlayerService.class);
                    myService.putExtra("url", title);
                    myService.putExtra("name", name);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(myService);
                    } else {
                        startService(myService);
                    }
                }

            }

        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(this, Player_Activity.class);
        this.startActivity(intent);

    }

    public static void setAudioProgress() {
        currentLink = url;
        servicename = name;
        servicetitle = title;
        current_pos = music.getCurrentPosition();
        total_duration = music.getDuration();

        duration.setText(timerConversion((long) total_duration));
        d1.setText(timerConversion((long) current_pos));
        mseekBar.setMax((int) total_duration);
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = music.getCurrentPosition();

                    d1.setText(timerConversion((long) current_pos));
                    mseekBar.setProgress((int) current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public static void setseekbar() {
        mseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mseekBar.setProgress(progress);
                    mseekBar.setMax(total_duration);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current_pos = seekBar.getProgress();
                music.seekTo((int) current_pos);

            }
        });

    }

    public static String timerConversion(long value) {
        String audioTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            audioTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            audioTime = String.format("%02d:%02d", mns, scs);
        }
        return audioTime;
    }
}

