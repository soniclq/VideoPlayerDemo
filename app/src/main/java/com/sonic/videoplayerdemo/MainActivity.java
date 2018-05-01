package com.sonic.videoplayerdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.sonic.videoplayerdemo.util.PixelUtil;

public class MainActivity extends AppCompatActivity {

    private VideoView videoplayer;
    private String mPath = "/sdcard/mini___2.mp4";
    private ImageView pause_control_img;
    private SeekBar playerProgress;
    private TextView current_time_tv, total_time_tv;

    public static final int UPDATE_UI = 1;
    private int screen_width ,screen_height;
    private RelativeLayout videoLayout;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.test_btn);
    }
}
