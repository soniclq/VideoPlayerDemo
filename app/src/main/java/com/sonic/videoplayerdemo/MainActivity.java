package com.sonic.videoplayerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoplayer;
    private String mPath = "/sdcard/mini___2.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoplayer = (VideoView) findViewById(R.id.videoplayer);


        videoplayer.setVideoPath(mPath);

//        videoplayer.setVideoURI(Uri.parse(""));

        MediaController mediaController = new MediaController(this);
        videoplayer.setMediaController(mediaController);


        mediaController.setMediaPlayer(videoplayer);

    }
}
