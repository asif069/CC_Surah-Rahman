package com.surah.rahman.audio.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.surah.rahman.audio.app.R;

public class PrivacyPolicyActivity extends AppCompatActivity {
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        preferences = getSharedPreferences("policy", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("accept", false)) {
            setContentView(R.layout.activity_privacy_policy);
            Button agree=findViewById(R.id.agree);
            agree.setOnClickListener(v -> {
                preferences.edit().putBoolean("accept", true).apply();
                startActivity(new Intent(this, SplashActivity.class));
            });

        } else {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }
    }
}