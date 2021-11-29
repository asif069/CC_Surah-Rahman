package com.surah.rahman.audio.app.dialogue;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.surah.rahman.audio.app.R;


public class CustomDialogue extends Dialog implements View.OnClickListener {
    Activity activity;
    CardView ratenow,exitbtn;
    public CustomDialogue(@NonNull Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialogue);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ratenow=findViewById(R.id.ratenow);
        exitbtn=findViewById(R.id.exitbtn);
        ratenow.setOnClickListener(this);
        exitbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ratenow) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName())));
        }  else if (v.getId() == R.id.exitbtn) {
            activity.finish();
            activity.finishAffinity();
            System.exit(0);
        }
    }
}
