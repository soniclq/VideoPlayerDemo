package com.sonic.videoplayerdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
        setPlayerEvent();

        videoplayer.setVideoPath(mPath);
        videoplayer.start();
        handler.sendEmptyMessage(UPDATE_UI);

//        videoplayer.setVideoURI(Uri.parse(""));

//        MediaController mediaController = new MediaController(this);
//        videoplayer.setMediaController(mediaController);
//
//
//        mediaController.setMediaPlayer(videoplayer);

    }

    private void updateTime(TextView textView, int  millsecond) {
        int second = millsecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String str = null;
        if(hh != 0){
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        }else {
            str = String.format("%02d:%02d", mm, ss);

        }

        textView.setText(str);
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == UPDATE_UI) {

                int currentTime = videoplayer.getCurrentPosition();
                int totalTime = videoplayer.getDuration();


                updateTime(total_time_tv, totalTime);
                updateTime(current_time_tv, currentTime);

                playerProgress.setMax(totalTime);
                playerProgress.setProgress(currentTime);

                handler.sendEmptyMessageDelayed(UPDATE_UI, 500);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(UPDATE_UI);
    }



    private void setPlayerEvent() {
        pause_control_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoplayer == null){
                    return ;
                }
                if(videoplayer.isPlaying()){
                    videoplayer.pause();
                    pause_control_img.setImageResource(R.drawable.play_btn_style);
                    handler.removeMessages(UPDATE_UI);
                }else {
                    pause_control_img.setImageResource(R.drawable.pause_btn_style);
                    videoplayer.start();
                    handler.sendEmptyMessage(UPDATE_UI);
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
        total_time_tv = (TextView)findViewById(R.id.time_total_tv);
        current_time_tv = (TextView)findViewById(R.id.current_time);
        playerProgress = (SeekBar)findViewById(R.id.player_progress);
        videoLayout = (RelativeLayout)findViewById(R.id.video_layout);

        playerProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//                playerProgress.setProgress(progress);
                updateTime(current_time_tv, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeMessages(UPDATE_UI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                videoplayer.seekTo(progress);
                handler.sendEmptyMessage(UPDATE_UI);
            }
        });
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        PixelUtil.init(this);
    }


    private  void setVideoViewScale(int width, int height){
        ViewGroup.LayoutParams layoutParams = videoplayer.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        videoplayer.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 =  videoLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = height;
        videoLayout.setLayoutParams(layoutParams1);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        }else {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtil.dp2px(240));
        }
    }
}
