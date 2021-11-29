package com.surah.rahman.audio.app.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surah.rahman.audio.app.Adapters.MusicAdapter;
import com.surah.rahman.audio.app.Modelclasses.SongInfoModel;
import com.surah.rahman.audio.app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Downloadedlist extends AppCompatActivity {
    Context context;
    List<SongInfoModel> data;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadedlist);
        Intent intent = getIntent();
        data = new ArrayList<>();
        File directory = new File(getExternalFilesDir("Surah e rehman"), "");
        File[] contents = directory.listFiles();
        if (contents != null && contents.length > 0) {
            for (File file : contents) {
                {
                    Log.d("mytag", "directory_Offline" + file);
                    if (file.exists()) {
                        String name = file.getName();
                        String path = file.getPath();
                        SongInfoModel model = new SongInfoModel(name, "", path);
                        data.add(model);
                    }

                }
            }
        }
        Log.d("######", "directory_Offline" + Arrays.toString(contents));
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MusicAdapter adapter = new MusicAdapter(Downloadedlist.this, data, "play");
        recyclerView.setAdapter(adapter);


    }

}