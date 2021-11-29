package com.surah.rahman.audio.app.Activities;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.surah.rahman.audio.app.R;
import com.surah.rahman.audio.app.dialogue.CustomDialogue;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    RelativeLayout Recite, Listen, Recent, youtube;
    ViewPager mviewpager;
    String A;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Recite = (RelativeLayout) findViewById(R.id.btn1);
        Listen = (RelativeLayout) findViewById(R.id.btn2);
        Recent = (RelativeLayout) findViewById(R.id.btn3);
        youtube = (RelativeLayout) findViewById(R.id.btn4);
        Recite.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReciteActivity.class);
            startActivity(intent);
        });
        Listen.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Player_Activity.class);
            startActivity(i);
        });

        Recent.setOnClickListener(v -> {
            File directory = new File(getExternalFilesDir("Surah e rehman"), "");
            File[] contents = directory.listFiles();
            if (contents != null && contents.length > 0) {
                Intent intent = new Intent(getApplicationContext(), Downloadedlist.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No files Downloaded yet", Toast.LENGTH_SHORT).show();
            }
        });

        youtube.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), VedioActivity.class);
            startActivity(intent);
        });


    }
    @Override
    public void onBackPressed() {
        CustomDialogue customDialogue = new CustomDialogue(this);
        customDialogue.show();
    }
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setMessage("Do you want to Exit?");
//        builder.setPositiveButton(Html.fromHtml("<font color='#E0B580'>Yes</font>"), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.setNegativeButton(Html.fromHtml("<font color='#E0B580'>Rate Us</font>"), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Uri uri = Uri.parse("market://details?id=" + getPackageName());
//                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//                try {
//                    startActivity(goToMarket);
//                } catch (ActivityNotFoundException e) {
//                    startActivity(new Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse("http://play.google.com/store/apps/details?id="
//                                    + getPackageName())));
//                }
//
//                dialog.dismiss();
//                finish();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }


}