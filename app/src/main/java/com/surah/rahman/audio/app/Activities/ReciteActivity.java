package com.surah.rahman.audio.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.surah.rahman.audio.app.R;
import com.surah.rahman.audio.app.Adapters.ViewPagerAdapter;

public class ReciteActivity extends AppCompatActivity {


    ViewPager mViewPager;
    int a;
    String TAG = "******abc";
    private SharedPreferences mPreferences;


    // images array
    int[] images = {R.drawable.a8, R.drawable.a7, R.drawable.a6, R.drawable.a5, R.drawable.a4, R.drawable.a3, R.drawable.a2, R.drawable.a1};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();



        // Initializing the ViewPager Object
        mViewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        mPreferences = getSharedPreferences("mPreferences", MODE_PRIVATE);
        int page = mPreferences.getInt("position", 7);
        Log.e(TAG, "slast value= " + page);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(ReciteActivity.this, images);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "selected page = " + position);

                mPreferences = getSharedPreferences("mPreferences", MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putInt("position", position);
                preferencesEditor.apply();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });



        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(page);


    }



}