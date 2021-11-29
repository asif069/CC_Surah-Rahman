package com.surah.rahman.audio.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.surah.rahman.audio.app.Adapters.MusicAdapter;
import com.surah.rahman.audio.app.R;
import com.surah.rahman.audio.app.Modelclasses.SongInfoModel;


import java.util.ArrayList;
import java.util.List;

public class Player_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_);
        List<SongInfoModel> data = fill_with_data();
        Intent intent = getIntent();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MusicAdapter adapter = new MusicAdapter(Player_Activity.this, data, "");
        recyclerView.setAdapter(adapter);

    }

    public List<SongInfoModel> fill_with_data() {

        List<SongInfoModel> data = new ArrayList<>();

        data.add(new SongInfoModel("Surah e Rehman by AbdelHamid hussain", "AbdelHamid hussain", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/abdelhamid-hssain-055-muslimcentral.com.mp3?alt=media&token=5898b32b-ed65-47e4-80cd-2dd82861f906"));
        data.add(new SongInfoModel("Surah e Rahman by Anas Younus ", "Anas Younus ", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Anas%20Younus%20-%20Surah%20e%20Rahman%20-%20With%20Urdu%20Translation.mp3?alt=media&token=5cb0bb12-73e6-439c-9775-1e4419dd50a3"));
        data.add(new SongInfoModel("Surah Al Rahman by Qari Abdul Bas", "Qari Abdul Bas", "https://firebasestorage.googleapis.com/v0/b/musicplayer-acadc.appspot.com/o/Surah%20Al%20Rahman%20-%20Qari%20Abdul%20BasitSufism.mp3?alt=media&token=1e066ab8-c960-4863-80fa-25bd34f6bfd5"));
        data.add(new SongInfoModel("Surah Ar-Rehman by Sheikh Noreen Muhammad Siddi", "Sheikh Noreen Muhammad Siddi", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Ar-Rehman%20Beautiful%20and%20Heart%20Touching%20Quran%20recitation%20by%20Sheikh%20Noreen%20Muhammad%20Siddi.mp3?alt=media&token=44ac7806-1e58-4b3e-b474-23602e2da6e5"));
        data.add(new SongInfoModel("Surah Al-Rehman by Abdul rehman Al-suddais", "Abdul rehman Al-suddais", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Ar-Rehman%20FullAbdul%20Rahman%20Al-Sudais%20(HD)%D8%B3%D9%88%D8%B1%D8%A9%20%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D8%A7%D9%86.mp3?alt=media&token=bcd99c6c-1eda-4f8e-a305-ef665d8af0d7"));
        data.add(new SongInfoModel("Surah Rahman By Sheikh Saud Ash-ShuraimSurah Rahman", "Sheikh Saud Ash-ShuraimSurah Rahman ", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Rahman%20By%20Sheikh%20Saud%20Ash-Shuraim%20Full%20With%20Arabic%20Text%20(HD)%2055-%D8%B3%D9%88%D8%B1%DB%83%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D9%86.mp3?alt=media&token=4cecf2ed-4075-4c7c-9df2-aa91c50a6105"));
        data.add(new SongInfoModel("Surah e Rehman by Qari Abdul Basit", "Qari Abdul Basit", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Al%20Rahman%20-%20Qari%20Abdul%20BasitSufism.mp3?alt=media&token=fac6d53d-772b-4594-bde4-b3cc0987746b"));
        data.add(new SongInfoModel("Surah r Rehman by Qari Asad Attari", "Qari Asad Attari", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Rehman%20recited%20by%20qari%20asad%20attari.%20(dawateislami).mp3?alt=media&token=8d9840bc-83bf-4b44-86c4-b278c23a3c0b"));
        data.add(new SongInfoModel("Surah e Rehman by Mishary Al Afsa", "Mishary Al Afsa", "https://firebasestorage.googleapis.com/v0/b/music-7ba61.appspot.com/o/Surah%20Rahman%20Mishary%20Al%20Afasy%20%D8%B3%D9%88%D8%B1%D8%A9%20%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D9%86%20%D8%A7%D9%84%D8%B9%D9%81%D8%A7%D8%B3%D9%8A%20NEW%20VIDEO%20LINK%20BELOW.mp3?alt=media&token=eda26b7e-d0bc-4ec6-bbab-6e896fffc69f"));
        return data;
    }
}