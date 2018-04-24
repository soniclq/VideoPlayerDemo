package com.sonic.videoplayerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoplayer;
    private String mPath = "/sdcard/mini___2.mp4";
    private ImageView pause_control_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
        setPlayerEvent();

        videoplayer.setVideoPath(mPath);

//        videoplayer.setVideoURI(Uri.parse(""));

//        MediaController mediaController = new MediaController(this);
//        videoplayer.setMediaController(mediaController);
//
//
//        mediaController.setMediaPlayer(videoplayer);

    }

    private void setPlayerEvent() {
        pause_control_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoplayer.isPlaying()){
                    videoplayer.pause();
                    pause_control_img.setImageResource(R.drawable.play_btn_style);
                }else {
                    pause_control_img.setImageResource(R.drawable.pause_btn_style);
                    videoplayer.start();
                }
            }
        });
    }

    /**
     * 初始化UI
     */

    private void initUI() {
        videoplayer = (VideoView) findViewById(R.id.videoplayer);
        pause_control_img = (ImageView)findViewById(R.id.pause_control_img);
    }
}
