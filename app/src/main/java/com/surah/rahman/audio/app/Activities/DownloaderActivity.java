package com.surah.rahman.audio.app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.surah.rahman.audio.app.R;

import java.io.File;

public class DownloaderActivity extends AppCompatActivity {
    ImageView img;
    TextView Downloadingtext, titletext;
    ProgressBar progressBar;
    public static File directory;
    static public String name, url, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);
        AndroidNetworking.initialize(getApplicationContext());

        img = (ImageView) findViewById(R.id.Playimg);
        Downloadingtext = (TextView) findViewById(R.id.downloading);
        titletext = (TextView) findViewById(R.id.titlename);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        directory = new File(getExternalFilesDir("Surah e rehman"), "");
        String mypath = directory + "/" + name + ".mp3";
        File file = new File(mypath);


        if (!directory.exists()) {
            try {
                directory.mkdirs();
                if (directory.exists() && directory.isDirectory()) {
                    Toast.makeText(this, "Folder Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Folder Failed", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.getMessage();
            }
        }

        if (file.exists()) {
            Intent intent1 = new Intent(getApplicationContext(), ListenActivity.class);
            intent1.putExtra("name", name);
            intent1.putExtra("title", title);
            intent1.putExtra("url", mypath);
            startActivity(intent1);
            finish();
            Log.d("myTAG", "onCreate:notexsist");
        } else {
            Log.d("myTAG", "onCreate:exsist");
            downloadaudio();


        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ListenActivity.class);
                intent1.putExtra("name", name);
                intent1.putExtra("title", title);
                intent1.putExtra("url", mypath);
                startActivity(intent1);
                finish();
            }
        });
    }

    public void downloadaudio() {
        AndroidNetworking.download(url, String.valueOf(directory), name + ".mp3")
                .setTag("downloadTest")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Downloadingtext.setVisibility(View.VISIBLE);
                        titletext.setVisibility(View.VISIBLE);
                        titletext.setText(title);
                        img.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);


                        Log.d("TAG", "********downloading" + url);
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Downloadingtext.setVisibility(View.VISIBLE);
                        titletext.setVisibility(View.VISIBLE);
                        img.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Downloadingtext.setText("Download Completed");
                        Log.e("TAG", "***downloaded" + name);

                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(DownloaderActivity.this, "download failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }

}