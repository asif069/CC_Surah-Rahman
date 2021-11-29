package com.surah.rahman.audio.app.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.surah.rahman.audio.app.Apikeys.YoutubePlayer;
import com.surah.rahman.audio.app.R;
import com.surah.rahman.audio.app.Services.MediaPlayerService;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import static com.surah.rahman.audio.app.Activities.ListenActivity.music;
import static com.surah.rahman.audio.app.Apikeys.YoutubePlayer.API_KEY;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoInfoHolder> {


    String[] VideoId = {"Lbcta_bVxP4", "Iy4rKhclG_w", "JHI_g0QMPQk", "svthOetNi5k", "IKZ57sdcFK4", "oEO5cOSQ_E8", "EOy77foLSEU", "NHGIU6apsxU", "2BrCE_zxM0U", "2-dndZVOPEM"};
    Context ctx;

    public VideoAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public VideoInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail, parent, false);
        return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoInfoHolder holder, int position) {

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };
        holder.youTubeThumbnailView.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(VideoId[position]);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return VideoId.length;
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        YouTubePlayerView youTubePlayerView;
        protected ImageView playButton;

        public VideoInfoHolder(View itemView) {
            super(itemView);
            ProgressBar prograssbar;
            prograssbar = (ProgressBar) itemView.findViewById(R.id.progress_circular);
            playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    prograssbar.setVisibility(View.INVISIBLE);
                    playButton.setVisibility(View.VISIBLE);
                }
            }, 2000);


        }

        @Override
        public void onClick(View v) {
            if (music.isPlaying()) {
                music.pause();
                Intent myService = new Intent(ctx, MediaPlayerService.class);
                ctx.stopService(myService);
            }
            ConnectivityManager cm =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx,
                        YoutubePlayer.API_KEY,
                        VideoId[getLayoutPosition()],
                        100,     //after this time, video will start automatically
                        true,               //autoplay or not
                        false);             //lightbox mode or not; show the video in a small box
                ctx.startActivity(intent);

            } else {
                Toast.makeText(ctx, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }


        }


    }
}
