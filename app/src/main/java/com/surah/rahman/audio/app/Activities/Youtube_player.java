//package com.surah.rahman.audio.app.Activities;
//
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.view.WindowManager;
//import android.widget.Toast;
//
//import com.surah.rahman.audio.app.R;
//import com.surah.rahman.audio.app.Apikeys.YoutubePlayer;
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;
//
//public class Youtube_player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
//    public static final String API_KEY = "AIzaSyCWwLjkkTH6HXjgUGXbW-MhlA3JFjUos04";
//    private static final int RECOVERY_DIALOG_REQUEST = 1;
//    YouTubePlayerView youTubePlayerView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube_player);
//        Intent intent=getIntent();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        youTubePlayerView=(YouTubePlayerView) findViewById(R.id.ytplayer);
//
//        youTubePlayerView.initialize(YoutubePlayer.API_KEY, this);
////        youTubePlayerView.initialize(
////                API_KEY,
////                new YouTubePlayer.OnInitializedListener() {
//
//
////                    @Override
////                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
////                        youTubePlayer.loadVideo("BVDqS_UxWz0");
////                        youTubePlayer.play();
////                    }
////
////                    @Override
////                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
////                        Toast.makeText(Youtube_player.this, "youtube player failed", Toast.LENGTH_SHORT).show();
////                    }
////                }
//    }
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        youTubePlayer.loadVideo("Lbcta_bVxP4");
////        youTubePlayer.cueVideo("BVDqS_UxWz0");
//        youTubePlayer.play();
//
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        Toast.makeText(Youtube_player.this, "youtube player failed", Toast.LENGTH_SHORT).show();
//
//    }
//
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RECOVERY_DIALOG_REQUEST) {
//            getYouTubePlayerProvider().initialize(YoutubePlayer.API_KEY, this);
//        }
//    }
//
//    private YouTubePlayer.Provider getYouTubePlayerProvider() {
//        return (YouTubePlayerView) findViewById(R.id.ytplayer);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//    }
//
//
//
//
//}