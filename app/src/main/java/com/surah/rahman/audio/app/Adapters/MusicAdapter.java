package com.surah.rahman.audio.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.surah.rahman.audio.app.Activities.DownloaderActivity;
import com.surah.rahman.audio.app.Activities.ListenActivity;
import com.surah.rahman.audio.app.Modelclasses.SongInfoModel;
import com.surah.rahman.audio.app.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.viewHolder> {

    Context context;
    List<SongInfoModel> audioArrayList;
    String b;
    String mypath;
    String file1, file2;


    public MusicAdapter(Context context, List<SongInfoModel> audioArrayList, String b) {
        this.context = context;
        this.audioArrayList = audioArrayList;
        this.b = b;
    }

    @Override
    public MusicAdapter.@NotNull viewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_song, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicAdapter.viewHolder holder, final int i) {

        holder.title.setText(audioArrayList.get(i).getSongName());
        holder.artist.setText(audioArrayList.get(i).getArtistName());
        holder.url.setText(audioArrayList.get(i).getUrl());

//        File directory = new File(context.getExternalFilesDir("Surah e rehman"), "");
//        String mypath = directory+"/"+audioArrayList.get(i).getSongName()+".mp3";
//        File file = new File(mypath);
//        if (!directory.exists()){
//            try {
//                directory.mkdirs();
//                if (directory.exists() && directory.isDirectory()) {
//                    Toast.makeText(context, "Folder Created", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Folder Failed", Toast.LENGTH_SHORT).show();
//                }
//
//
//            } catch (Exception e) {
//                e.getMessage();
//            }
//        }
        File directory = new File(context.getExternalFilesDir("Surah e rehman"), "");

        file1 = directory + "/" + audioArrayList.get(i).getArtistName() + ".mp3";
        file2 = audioArrayList.get(i).getUrl();
        File myfie = new File(file1);
        File myfile = new File(file2);
        if (myfie.exists()) {
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.play));
        } else {
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dwnad));
        }
//        if (myfie.exists()){
//            holder.imageView.setImageResource(R.drawable.play);
//
//        }
//        else {
//            holder.imageView.setImageResource(R.drawable.dwnad);
//        }
        if (myfile.exists()) {
            holder.imageView.setImageResource(R.drawable.play);

        }
//        else {
//            holder.imageView.setImageResource(R.drawable.dwnad);
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = new File(context.getExternalFilesDir("Surah e rehman"), "");
                if (b == "play") {
                    mypath = audioArrayList.get(i).getUrl();

                } else {
                    mypath = directory + "/" + audioArrayList.get(i).getSongName() + ".mp3";

                }


                File file = new File(mypath);
                if (!directory.exists()) {
                    try {
                        directory.mkdirs();
                        if (directory.exists() && directory.isDirectory()) {
                            Toast.makeText(context, "Folder Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Folder Failed", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
                if (file.exists()) {
                    Intent intent = new Intent(context, ListenActivity.class);

                    intent.putExtra("name", audioArrayList.get(i).getArtistName());
                    intent.putExtra("title", audioArrayList.get(i).getSongName());
                    intent.putExtra("url", mypath);
                    context.startActivity(intent);
                    Log.d("myTAG", "onCreate:notexsist");
                    //Toast.makeText(context, "Dwonloaded", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, DownloaderActivity.class);
                    intent.putExtra("name", audioArrayList.get(i).getArtistName());
                    intent.putExtra("title", audioArrayList.get(i).getSongName());
                    intent.putExtra("url", audioArrayList.get(i).getUrl());
                    context.startActivity(intent);

                    Log.d("myTAG", "onCreate:exsist");

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return audioArrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView title, artist, url;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            artist = (TextView) itemView.findViewById(R.id.artist);
            url = (TextView) itemView.findViewById(R.id.url);
            imageView = (ImageView) itemView.findViewById(R.id.image1);
        }
    }


}